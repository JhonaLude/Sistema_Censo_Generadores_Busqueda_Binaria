from flask import render_template, request, redirect, url_for, flash
import json
import os
import requests
from datetime import datetime
from app import app, FAMILIAS_FILE, GENERADORES_FILE, TRANSACCIONES_FILE

BASE_URL = "http://localhost:8080/api" 


def load_json(file_path):
    if os.path.exists(file_path):
        with open(file_path, 'r') as file:
            return json.load(file)
    return []

def save_json(data, file_path):
    os.makedirs(os.path.dirname(file_path), exist_ok=True)
    with open(file_path, 'w') as file:
        json.dump(data, file, indent=4)

def get_ordered_data(endpoint, order_by='id', order_type='ascendente', sort_method='quicksort'):
    """Obtener datos ordenados desde el REST endpoint"""
    try:
        response = requests.get(f"{BASE_URL}{endpoint}/ordenar/{sort_method}", 
                                params={'atributo': order_by, 'ascendente': order_type == 'ascendente'})
        return response.json() if response.status_code == 200 else []
    except requests.RequestException:
        flash(f"Error al ordenar {endpoint}")
        return []

def search_data(endpoint, attribute, value, search_method='secuencial'):
    """Buscar datos en el REST endpoint"""
    try:
        # Para generadores, usar la busqueda secuencial
        if endpoint == '/generadores':
            response = requests.get(f"{BASE_URL}{endpoint}/buscar", 
                                    params={'atributo': attribute, 'valor': value})
        # Para los demas ENDPOINTS usar la busqueda binaria
        else:
            response = requests.get(f"{BASE_URL}{endpoint}/buscar/binario", 
                                    params={'atributo': attribute, 'valor': value})
        
        return response.json() if response.status_code == 200 else []
    except requests.RequestException:
        flash(f"Error al buscar en {endpoint}")
        return []

# Ruta principal
@app.route('/')
def index():
    return render_template('index.html')

# Ruta de Familia
@app.route('/familia', methods=['GET', 'POST'])
def familia():
    if request.method == 'POST':
        familias = load_json(FAMILIAS_FILE)
        nueva_familia = {
            'id': len(familias) + 1,
            'nombre': request.form['nombre'],
            'numeroMiembros': int(request.form['numeroMiembros']),
            'generadorAdquirido': request.form.get('generadorAdquirido') == 'on'
        }
        familias.append(nueva_familia)
        save_json(familias, FAMILIAS_FILE)
        flash('Familia registrada exitosamente')
        return redirect(url_for('familia'))
    return render_template('familia_form.html')

# Ruta para Generador
@app.route('/generador', methods=['GET', 'POST'])
def generador():
    if request.method == 'POST':
        generadores = load_json(GENERADORES_FILE)
        nuevo_generador = {
            'id': len(generadores) + 1,
            'marca': request.form['marca'],
            'modelo': request.form['modelo'],
            'costo': float(request.form['costo']),
            'consumoPorHora': float(request.form['consumoPorHora']),
            'generacion': float(request.form['generacion']),
            'uso': request.form['uso']
        }
        generadores.append(nuevo_generador)
        save_json(generadores, GENERADORES_FILE)
        flash('Generador registrado exitosamente')
        return redirect(url_for('generador'))
    return render_template('generador_form.html')

# Ruta para Transaccion
@app.route('/transaccion', methods=['GET', 'POST'])
def transaccion():
    if request.method == 'POST':
        transacciones = load_json(TRANSACCIONES_FILE)
        nueva_transaccion = {
            'id': len(transacciones) + 1,
            'fecha': datetime.now().strftime('%Y-%m-%d %H:%M:%S'),
            'familiaId': int(request.form['familiaId']),
            'generadorId': int(request.form['generadorId']),
            'tipo': request.form['tipo']
        }
        transacciones.append(nueva_transaccion)
        save_json(transacciones, TRANSACCIONES_FILE)
        flash('Transacción registrada exitosamente')
        return redirect(url_for('transaccion'))
    
    familias = load_json(FAMILIAS_FILE)
    generadores = load_json(GENERADORES_FILE)
    return render_template('transaccion_form.html', familias=familias, generadores=generadores)

# Ruta para Estadisticas
@app.route('/estadisticas')
def estadisticas():
    def search_data(section, attribute, value):
        """
        Función para buscar datos en un archivo JSON
        """
        print(f"Searching in {section} for {attribute}: {value}")
        
        # Cargar datos de acuerdo a la seccion
        if section == '/familias':
            data = load_json(FAMILIAS_FILE)
        elif section == '/generadores':
            data = load_json(GENERADORES_FILE)
        elif section == '/transacciones':
            data = load_json(TRANSACCIONES_FILE)
        else:
            print(f"Invalid section: {section}")
            return []
        
        # Manejar búsqueda para generador adquirido
        if attribute == 'generadorAdquirido':
            if value.lower() in ['si', 'sí']:
                value = True
            elif value.lower() == 'no':
                value = False

        # Búsqueda de resultados
        results = []
        for item in data:
            # Obtener el valor del atributo para este item
            item_value = str(item.get(attribute, '')).lower()
            search_value = str(value).lower()

            # Realizar búsqueda parcial para strings
            if search_value in item_value:
                results.append(item)
            
            # Comparación exacta para otros tipos de datos
            elif item.get(attribute) == value:
                results.append(item)

        print(f"Found {len(results)} results")
        return results
    
    # Carga los datos desde archivos JSON
    familias = load_json(FAMILIAS_FILE)
    generadores = load_json(GENERADORES_FILE)
    transacciones = load_json(TRANSACCIONES_FILE)
    
    # Obtener parametros
    order_by = request.args.get('orderBy')
    order_type = request.args.get('order', 'ascendente')
    sort_method = request.args.get('sortMethod', 'quicksort')  
    section = request.args.get('section', 'familias')  

    # Realizar busqueda binaria
    search_attribute = request.args.get('searchAttribute')
    search_value = request.args.get('searchValue')
    search_results = []

    if search_attribute and search_value:
        if section == 'familias':
            search_results = search_data('/familias', search_attribute, search_value)
        elif section == 'generadores':
            search_results = search_data('/generadores', search_attribute, search_value)
        elif section == 'transacciones':
            search_results = search_data('/transacciones', search_attribute, search_value)
        
        if not search_results:
            flash(f"No se encontraron resultados para {search_attribute}: {search_value}")

    # Calcular estadisticas
    total_familias = len(familias)
    familias_con_generador = sum(1 for f in familias if f.get('generadorAdquirido', False))
    total_transacciones = len(transacciones)

    # Preparar estadisticas
    familias_stats = []
    for familia in familias:
        familias_stats.append({
            'id': familia.get('id', 0),
            'nombre': familia.get('nombre', 'Sin nombre'),
            'numeroMiembros': familia.get('numeroMiembros', 0),
            'generadorAdquirido': familia.get('generadorAdquirido', False)
        })

    generadores_stats = []
    
    # Preparar estadisticas de generadores
    marca_groups = {}
    for generador in generadores:
        marca = generador.get('marca', 'Sin marca')
        if marca not in marca_groups:
            marca_groups[marca] = {
                'marca': marca,
                'cantidad': 0,
                'costo_promedio': 0,
                'generacion_promedio': 0,
                'consumo_promedio': 0
            }
        
        grupo = marca_groups[marca]
        grupo['cantidad'] += 1
        grupo['costo_promedio'] += generador.get('costo', 0)
        grupo['generacion_promedio'] += generador.get('generacion', 0)
        grupo['consumo_promedio'] += generador.get('consumoPorHora', 0)
    
    # Calcular promedios
    for grupo in marca_groups.values():
        if grupo['cantidad'] > 0:
            grupo['costo_promedio'] /= grupo['cantidad']
            grupo['generacion_promedio'] /= grupo['cantidad']
            grupo['consumo_promedio'] /= grupo['cantidad']
        generadores_stats.append(grupo)

    # Lógica de clasificación para familias y generadores
    if section == 'familias' and order_by:
        reverse = order_type == 'descendente'
        if order_by == 'nombre':
            familias_stats.sort(key=lambda x: x['nombre'], reverse=reverse)
        elif order_by == 'numeroMiembros':
            familias_stats.sort(key=lambda x: x['numeroMiembros'], reverse=reverse)
        elif order_by == 'generadorAdquirido':
            familias_stats.sort(key=lambda x: x['generadorAdquirido'], reverse=reverse)
    
    if section == 'generadores' and order_by:
        reverse = order_type == 'descendente'
        if order_by == 'marca':
            generadores_stats.sort(key=lambda x: x['marca'], reverse=reverse)
        elif order_by == 'cantidad':
            generadores_stats.sort(key=lambda x: x['cantidad'], reverse=reverse)
        elif order_by == 'costo_promedio':
            generadores_stats.sort(key=lambda x: x['costo_promedio'], reverse=reverse)
        elif order_by == 'generacion_promedio':
            generadores_stats.sort(key=lambda x: x['generacion_promedio'], reverse=reverse)
        elif order_by == 'consumo_promedio':
            generadores_stats.sort(key=lambda x: x['consumo_promedio'], reverse=reverse)

    return render_template(
        'estadisticas.html',
        total_familias=total_familias,
        familias_con_generador=familias_con_generador,
        total_transacciones=total_transacciones,
        familias_stats=familias_stats,
        generadores_stats=generadores_stats,
        search_results=search_results,
        search_attribute=search_attribute,
        search_value=search_value,
        sort_method=sort_method,
        section=section
    )