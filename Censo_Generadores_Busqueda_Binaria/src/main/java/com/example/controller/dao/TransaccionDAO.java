/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller.dao;

import com.example.models.Transaccion;
import com.example.tda.list.LinkedList;
import java.lang.reflect.Method;

/**
 *
 * @author Alexander Ludena
 */

public class TransaccionDAO {

    private LinkedList<Transaccion> transacciones;
    private int contador;

    public TransaccionDAO() {
        this.transacciones = new LinkedList<>();
        this.contador = 0;
    }

    // Métodos 
    public Transaccion crear(Transaccion transaccion) {
        transaccion.setId(++contador);
        transacciones.add(transaccion);
        return transaccion;
    }

    public Transaccion[] obtenerTodos() {
        return transacciones.toArray();
    }

    public Transaccion obtenerPorId(int id) {
        for (int i = 0; i < transacciones.getSize(); i++) {
            try {
                Transaccion transaccion = transacciones.get(i);
                if (transaccion.getId() == id) {
                    return transaccion;
                }
            } catch (Exception e) {
                // Manejo de excepciones
            }
        }
        return null;
    }

    public Transaccion[] obtenerPorFamiliaId(int familiaId) {
        LinkedList<Transaccion> resultado = new LinkedList<>();

        for (int i = 0; i < transacciones.getSize(); i++) {
            try {
                Transaccion transaccion = transacciones.get(i);
                if (transaccion.getFamiliaId() == familiaId) {
                    resultado.add(transaccion);
                }
            } catch (Exception e) {
                // Manejo de excepciones 
            }
        }
        return resultado.toArray();
    }

    public boolean actualizar(Transaccion transaccion) {
        for (int i = 0; i < transacciones.getSize(); i++) {
            try {
                if (transacciones.get(i).getId() == transaccion.getId()) {
                    transacciones.update(transaccion, i);
                    return true;
                }
            } catch (Exception e) {
                // Manejo de excepciones 
            }
        }
        return false;
    }

    public boolean eliminar(int id) {
        for (int i = 0; i < transacciones.getSize(); i++) {
            try {
                if (transacciones.get(i).getId() == id) {
                    transacciones.delete(i);
                    return true;
                }
            } catch (Exception e) {
                // Manejo de excepciones
            }
        }
        return false;
    }

    // Métodos de ordenamiento
    public void ordenarPorQuickSort(String atributo, boolean ascendente) throws Exception {
        if (ascendente) {
            transacciones.quickSortByAttribute(atributo);
        } else {
            transacciones = transacciones.order(atributo, 0);
        }
    }

    public void ordenarPorMergeSort(String atributo, boolean ascendente) throws Exception {
        if (ascendente) {
            transacciones.mergeSortByAttribute(atributo);
        } else {
            transacciones = transacciones.order(atributo, 0);
        }
    }

    public void ordenarPorShellSort(String atributo, boolean ascendente) throws Exception {
        if (ascendente) {
            transacciones.shellSortByAttribute(atributo);
        } else {
            transacciones = transacciones.order(atributo, 0);
        }
    }

    // Método de Búsqueda Binaria
    public Transaccion[] busquedaBinaria(String atributo, Object valorBuscado) throws Exception {
        // Ordenar lista antes de realizar la búsqueda
        try {
            ordenarPorQuickSort(atributo, true);
        } catch (Exception e) {
            System.err.println("Error al ordenar antes de búsqueda binaria: " + e.getMessage());
            throw e;
        }

        LinkedList<Transaccion> resultados = new LinkedList<>();

        
        String metodoNombre = "get" + atributo.substring(0, 1).toUpperCase() + atributo.substring(1);
        Method metodoGetter = Transaccion.class.getMethod(metodoNombre);

        // Realizar búsqueda binaria
        int left = 0;
        int right = transacciones.getSize() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            try {
                Transaccion transaccion = transacciones.get(mid);
                Object valorAtributo = metodoGetter.invoke(transaccion);

                // Comparar valores
                int comparacion = compareValues(valorAtributo, valorBuscado);

                if (comparacion == 0) {
                    // Si se encuentra una coincidencia, buscar todas las coincidencias adyacentes
                    resultados.add(transaccion);

                    // Buscar coincidencias a la izquierda
                    int leftIndex = mid - 1;
                    while (leftIndex >= 0) {
                        Transaccion leftEst = transacciones.get(leftIndex);
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
                    while (rightIndex < transacciones.getSize()) {
                        Transaccion rightEst = transacciones.get(rightIndex);
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
