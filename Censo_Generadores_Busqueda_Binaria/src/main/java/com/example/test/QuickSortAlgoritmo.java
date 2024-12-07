/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.test;

/**
 *
 * @author Alexander Ludena
 */

public class QuickSortAlgoritmo {

    public static void ordenar(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                intercambiar(arr, i, j);
            }
        }

        intercambiar(arr, i + 1, high);
        return i + 1;
    }

    private static void intercambiar(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

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
