/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.test;

/**
 *
 * @author Alexander Ludena
 */

public class ShellSortAlgoritmo {

    public static void ordenar(int[] arr) {
        int n = arr.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;

                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }

                arr[j] = temp;
            }
        }
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
