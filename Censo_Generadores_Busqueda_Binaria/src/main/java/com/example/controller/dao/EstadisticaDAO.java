package com.example.controller.dao;

import com.example.models.Estadistica;
import com.example.tda.list.LinkedList;
import java.lang.reflect.Method;

/**
 *
 * @author Alexander Ludena
 */

public class EstadisticaDAO {

    private LinkedList<Estadistica> estadisticas;

    public EstadisticaDAO() {
        this.estadisticas = new LinkedList<>();
    }

    // Métodos 
    public boolean agregarEstadistica(Estadistica estadistica) {
        try {
            estadisticas.add(estadistica);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Estadistica[] obtenerTodas() {
        return estadisticas.toArray();
    }

    public Estadistica obtenerPorId(int id) {
        for (int i = 0; i < estadisticas.getSize(); i++) {
            try {
                Estadistica est = estadisticas.get(i);
                if (est.getId() == id) {
                    return est;
                }
            } catch (Exception e) {
                // Manejo de excepciones 
            }
        }
        return null;
    }

    // Métodos de ordenamiento
    public void ordenarPorQuickSort(String atributo, boolean ascendente) throws Exception {
        if (ascendente) {
            estadisticas.quickSortByAttribute(atributo);
        } else {
            estadisticas = estadisticas.order(atributo, 0);
        }
    }

    public void ordenarPorMergeSort(String atributo, boolean ascendente) throws Exception {
        if (ascendente) {
            estadisticas.mergeSortByAttribute(atributo);
        } else {
            estadisticas = estadisticas.order(atributo, 0);
        }
    }

    public void ordenarPorShellSort(String atributo, boolean ascendente) throws Exception {
        if (ascendente) {
            estadisticas.shellSortByAttribute(atributo);
        } else {
            estadisticas = estadisticas.order(atributo, 0);
        }
    }

    public boolean actualizarEstadistica(Estadistica estadistica) {
        for (int i = 0; i < estadisticas.getSize(); i++) {
            try {
                if (estadisticas.get(i).getId() == estadistica.getId()) {
                    estadisticas.update(estadistica, i);
                    return true;
                }
            } catch (Exception e) {
                // Manejo de excepciones 
            }
        }
        return false;
    }

    public boolean eliminarEstadistica(int id) {
        for (int i = 0; i < estadisticas.getSize(); i++) {
            try {
                if (estadisticas.get(i).getId() == id) {
                    estadisticas.delete(i);
                    return true;
                }
            } catch (Exception e) {
                // Manejo de excepciones 
            }
        }
        return false;
    }

    // Método de Búsqueda Binaria
    public Estadistica[] busquedaBinaria(String atributo, Object valorBuscado) throws Exception {
        
        // Ordenar lista antes de realizar la búsqueda
        try {
            ordenarPorQuickSort(atributo, true);
        } catch (Exception e) {
            System.err.println("Error al ordenar antes de búsqueda binaria: " + e.getMessage());
            throw e;
        }

        LinkedList<Estadistica> resultados = new LinkedList<>();

        
        String metodoNombre = "get" + atributo.substring(0, 1).toUpperCase() + atributo.substring(1);
        Method metodoGetter = Estadistica.class.getMethod(metodoNombre);

        // Realizar búsqueda binaria
        int left = 0;
        int right = estadisticas.getSize() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            try {
                Estadistica estadistica = estadisticas.get(mid);
                Object valorAtributo = metodoGetter.invoke(estadistica);

                // Comparar valores
                int comparacion = compareValues(valorAtributo, valorBuscado);

                if (comparacion == 0) {
                    // Si se encuentra una coincidencia, buscar todas las coincidencias adyacentes
                    resultados.add(estadistica);

                    // Buscar coincidencias a la izquierda
                    int leftIndex = mid - 1;
                    while (leftIndex >= 0) {
                        Estadistica leftEst = estadisticas.get(leftIndex);
                        Object leftValor = metodoGetter.invoke(leftEst);
                        if (compareValues(leftValor, valorBuscado) == 0) {
                            resultados.add(leftEst);
                            leftIndex--;
                        } else {
                            break;
                        }
                    }

                    // Buscar coincidencias a la derecha
                    int rightIndex = mid + 1;
                    while (rightIndex < estadisticas.getSize()) {
                        Estadistica rightEst = estadisticas.get(rightIndex);
                        Object rightValor = metodoGetter.invoke(rightEst);
                        if (compareValues(rightValor, valorBuscado) == 0) {
                            resultados.add(rightEst);
                            rightIndex++;
                        } else {
                            break;
                        }
                    }

                    break;
                } else if (comparacion < 0) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } catch (Exception e) {
                System.err.println("Error en búsqueda binaria: " + e.getMessage());
                throw e;
            }
        }

        return resultados.toArray();
    }

    // Método auxiliar para comparar valores de diferentes tipos
    private int compareValues(Object valor1, Object valor2) {
        if (valor1 == null && valor2 == null) {
            return 0;
        }
        if (valor1 == null) {
            return -1;
        }
        if (valor2 == null) {
            return 1;
        }

        
        if (valor1 instanceof Comparable && valor2 instanceof Comparable) {
            @SuppressWarnings("unchecked")
            Comparable<Object> comparable1 = (Comparable<Object>) valor1;
            return comparable1.compareTo(valor2);
        }

       
        return valor1.toString().compareTo(valor2.toString());
    }
}
