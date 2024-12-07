from flask import Flask
import os

app = Flask(__name__)
app.secret_key = 'censo_generadores_key'

# Path para archivos JSON
JSON_PATH = '/home/blach/NetBeansProjects/Censo_Generadores_Busqueda_Binaria/src/main/java/com/example/JSON'
FAMILIAS_FILE = os.path.join(JSON_PATH, 'familias.json')
GENERADORES_FILE = os.path.join(JSON_PATH, 'generadores.json')
TRANSACCIONES_FILE = os.path.join(JSON_PATH, 'transacciones.json')
