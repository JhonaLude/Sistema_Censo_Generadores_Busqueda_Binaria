/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.test;

/**
 *
 * @author Alexander Ludena
 */
import java.util.Arrays;

public class BusquedaBinaria {

    // Búsqueda binaria
    public static int busquedaBinaria(int[] arr, int elemento) {
        
        // Ordenamos el arreglo
        int[] arrOrdenado = Arrays.copyOf(arr, arr.length);
        Arrays.sort(arrOrdenado);

        int izquierda = 0;
        int derecha = arrOrdenado.length - 1;

        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;

            if (arrOrdenado[medio] == elemento) {
                return medio; // Retorna el índice si encuentra el elemento
            }

            if (arrOrdenado[medio] < elemento) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }

        return -1; // Retorna -1 si no encuentra el elemento
    }

    // Método para mostrar todos los elementos del arreglo 
    public static void mostrarArreglo(int[] arr, String mensaje) {
        System.out.println(mensaje);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
            if ((i + 1) % 20 == 0) {
                System.out.println();
            }
        }
        System.out.println("\nTotal de elementos: " + arr.length);
    }
}
