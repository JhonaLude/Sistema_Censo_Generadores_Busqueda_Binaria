/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller.dao;

import com.example.models.Familia;
import com.example.tda.list.LinkedList;
import java.lang.reflect.Method;

/**
 *
 * @author Alexander Ludena
 */

public class FamiliaDAO {

    private LinkedList<Familia> familias;
    private int contador;

    public FamiliaDAO() {
        this.familias = new LinkedList<>();
        this.contador = 0;
    }

    // Métodos 
    public Familia crear(Familia familia) {
        familia.setId(++contador);
        familias.add(familia);
        return familia;
    }

    public Familia[] obtenerTodos() {
        return familias.toArray();
    }

    public Familia obtenerPorId(int id) {
        for (int i = 0; i < familias.getSize(); i++) {
            try {
                Familia fam = familias.get(i);
                if (fam.getId() == id) {
                    return fam;
                }
            } catch (Exception e) {
                // Manejo de excepciones 
            }
        }
        return null;
    }

    public boolean actualizar(Familia familia) {
        for (int i = 0; i < familias.getSize(); i++) {
            try {
                if (familias.get(i).getId() == familia.getId()) {
                    familias.update(familia, i);
                    return true;
                }
            } catch (Exception e) {
                // Manejo de excepciones 
            }
        }
        return false;
    }

    public boolean eliminar(int id) {
        for (int i = 0; i < familias.getSize(); i++) {
            try {
                if (familias.get(i).getId() == id) {
                    familias.delete(i);
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
            familias.quickSortByAttribute(atributo);
        } else {
            familias = familias.order(atributo, 0);
        }
    }

    public void ordenarPorMergeSort(String atributo, boolean ascendente) throws Exception {
        if (ascendente) {
            familias.mergeSortByAttribute(atributo);
        } else {
            familias = familias.order(atributo, 0);
        }
    }

    public void ordenarPorShellSort(String atributo, boolean ascendente) throws Exception {
        if (ascendente) {
            familias.shellSortByAttribute(atributo);
        } else {
            familias = familias.order(atributo, 0);
        }
    }

    // Método de Búsqueda Binaria
    public Familia[] busquedaBinaria(String atributo, Object valorBuscado) throws Exception {
        
        // Ordenar lista antes de realizar la búsqueda
        try {
            ordenarPorQuickSort(atributo, true);
        } catch (Exception e) {
            System.err.println("Error al ordenar antes de búsqueda binaria: " + e.getMessage());
            throw e;
        }

        LinkedList<Familia> resultados = new LinkedList<>();

        String metodoNombre = "get" + atributo.substring(0, 1).toUpperCase() + atributo.substring(1);
        Method metodoGetter = Familia.class.getMethod(metodoNombre);

        // Realizar búsqueda binaria
        int left = 0;
        int right = familias.getSize() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            try {
                Familia familia = familias.get(mid);
                Object valorAtributo = metodoGetter.invoke(familia);

                // Comparar valores
                int comparacion = compareValues(valorAtributo, valorBuscado);

                if (comparacion == 0) {
                    // Si se encuentra una coincidencia, buscar todas las coincidencias adyacentes
                    resultados.add(familia);

                    // Buscar coincidencias a la izquierda
                    int leftIndex = mid - 1;
                    while (leftIndex >= 0) {
                        Familia leftEst = familias.get(leftIndex);
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
                    while (rightIndex < familias.getSize()) {
                        Familia rightEst = familias.get(rightIndex);
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
