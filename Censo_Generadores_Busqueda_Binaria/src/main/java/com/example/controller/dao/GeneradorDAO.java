/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller.dao;

import com.example.models.Generador;
import com.example.tda.list.LinkedList;
import java.lang.reflect.Method;

/**
 *
 * @author Alexander Ludena
 */

public class GeneradorDAO {

    private LinkedList<Generador> generadores;
    private int contador;

    public GeneradorDAO() {
        this.generadores = new LinkedList<>();
        this.contador = 0;
    }

    // Métodos
    public Generador crear(Generador generador) {
        generador.setId(++contador);
        generadores.add(generador);
        return generador;
    }

    public Generador[] obtenerTodos() {
        return generadores.toArray();
    }

    public Generador obtenerPorId(int id) {
        for (int i = 0; i < generadores.getSize(); i++) {
            try {
                Generador gen = generadores.get(i);
                if (gen.getId() == id) {
                    return gen;
                }
            } catch (Exception e) {
                // Manejo de excepciones
            }
        }
        return null;
    }

    public boolean actualizar(Generador generador) {
        for (int i = 0; i < generadores.getSize(); i++) {
            try {
                if (generadores.get(i).getId() == generador.getId()) {
                    generadores.update(generador, i);
                    return true;
                }
            } catch (Exception e) {
                // Manejo de excepciones 
            }
        }
        return false;
    }

    public boolean eliminar(int id) {
        for (int i = 0; i < generadores.getSize(); i++) {
            try {
                if (generadores.get(i).getId() == id) {
                    generadores.delete(i);
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
            generadores.quickSortByAttribute(atributo);
        } else {
            generadores = generadores.order(atributo, 0);
        }
    }

    public void ordenarPorMergeSort(String atributo, boolean ascendente) throws Exception {
        if (ascendente) {
            generadores.mergeSortByAttribute(atributo);
        } else {
            generadores = generadores.order(atributo, 0);
        }
    }

    public void ordenarPorShellSort(String atributo, boolean ascendente) throws Exception {
        if (ascendente) {
            generadores.shellSortByAttribute(atributo);
        } else {
            generadores = generadores.order(atributo, 0);
        }
    }

    // Método de Búsqueda Binaria
    public Generador[] busquedaBinaria(String atributo, Object valorBuscado) throws Exception {
        // Ordenar lista antes de realizar la búsqueda
        try {
            ordenarPorQuickSort(atributo, true);
        } catch (Exception e) {
            System.err.println("Error al ordenar antes de búsqueda binaria: " + e.getMessage());
            throw e;
        }

        LinkedList<Generador> resultados = new LinkedList<>();

        
        String metodoNombre = "get" + atributo.substring(0, 1).toUpperCase() + atributo.substring(1);
        Method metodoGetter = Generador.class.getMethod(metodoNombre);

        // Realizar búsqueda binaria
        int left = 0;
        int right = generadores.getSize() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            try {
                Generador generador = generadores.get(mid);
                Object valorAtributo = metodoGetter.invoke(generador);

                // Comparar valores
                int comparacion = compareValues(valorAtributo, valorBuscado);

                if (comparacion == 0) {
                    // Si se encuentra una coincidencia, buscar todas las coincidencias adyacentes
                    resultados.add(generador);

                    // Buscar coincidencias a la izquierda
                    int leftIndex = mid - 1;
                    while (leftIndex >= 0) {
                        Generador leftEst = generadores.get(leftIndex);
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
                    while (rightIndex < generadores.getSize()) {
                        Generador rightEst = generadores.get(rightIndex);
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
