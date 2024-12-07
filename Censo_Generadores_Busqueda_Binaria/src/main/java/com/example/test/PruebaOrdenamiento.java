/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.test;

import java.util.Random;

/**
 *
 * @author Alexander Ludena
 */

public class PruebaOrdenamiento {

    private static final int[] SIZES = {10000, 20000, 25000};
    private static StringBuilder resultadosResumen = new StringBuilder();

    public static void main(String[] args) {
        // Encabezado del resumen
        resultadosResumen.append("\n--- RESUMEN FINAL DE RESULTADOS ---\n");
        resultadosResumen.append(String.format("%-15s %-25s %-25s %-25s %-25s\n",
                "Tamaño Array", "Algoritmo", "Tiempo (nanosegundos)", "Tiempo (segundos)", "Primer/Último Elemento"));
        resultadosResumen.append("-".repeat(120)).append("\n");

        for (int size : SIZES) {
            System.out.println("\n--- Tamaño del arreglo: " + size + " ---");

            // Generar arreglo original aleatorio
            int[] originalArray = generarArregloAleatorio(size);

            // Copias para cada algoritmo
            int[] quickSortArray = originalArray.clone();
            int[] mergeSortArray = originalArray.clone();
            int[] shellSortArray = originalArray.clone();

            // Mostrar arreglo original
            QuickSortAlgoritmo.mostrarArreglo(originalArray, "Arreglo original:");

            // Resultados para guardar
            long tiempoQuickSort = 0, tiempoMergeSort = 0, tiempoShellSort = 0;

            // QuickSort
            System.out.println("\n--- QuickSort ---");
            long inicioQuickSort = System.nanoTime();
            QuickSortAlgoritmo.ordenar(quickSortArray);
            long finQuickSort = System.nanoTime();
            tiempoQuickSort = finQuickSort - inicioQuickSort;
            QuickSortAlgoritmo.mostrarArreglo(quickSortArray, "Arreglo ordenado QuickSort:");

            // Agregar resultado de QuickSort al resumen
            resultadosResumen.append(String.format("%-15d %-25s %-25d %-25.4f %-25s\n",
                    size, "QuickSort",
                    tiempoQuickSort,
                    tiempoQuickSort / 1_000_000_000.0,
                    quickSortArray[0] + " / " + quickSortArray[quickSortArray.length - 1]));

            // MergeSort
            System.out.println("\n--- MergeSort ---");
            long inicioMergeSort = System.nanoTime();
            MergeSortAlgoritmo.ordenar(mergeSortArray);
            long finMergeSort = System.nanoTime();
            tiempoMergeSort = finMergeSort - inicioMergeSort;
            MergeSortAlgoritmo.mostrarArreglo(mergeSortArray, "Arreglo ordenado MergeSort:");

            // Agregar resultado de MergeSort al resumen
            resultadosResumen.append(String.format("%-15d %-25s %-25d %-25.4f %-25s\n",
                    size, "MergeSort",
                    tiempoMergeSort,
                    tiempoMergeSort / 1_000_000_000.0,
                    mergeSortArray[0] + " / " + mergeSortArray[mergeSortArray.length - 1]));

            // ShellSort
            System.out.println("\n--- ShellSort ---");
            long inicioShellSort = System.nanoTime();
            ShellSortAlgoritmo.ordenar(shellSortArray);
            long finShellSort = System.nanoTime();
            tiempoShellSort = finShellSort - inicioShellSort;
            ShellSortAlgoritmo.mostrarArreglo(shellSortArray, "Arreglo ordenado ShellSort:");

            // Agregar resultado de ShellSort al resumen
            resultadosResumen.append(String.format("%-15d %-25s %-25d %-25.4f %-25s\n",
                    size, "ShellSort",
                    tiempoShellSort,
                    tiempoShellSort / 1_000_000_000.0,
                    shellSortArray[0] + " / " + shellSortArray[shellSortArray.length - 1]));

            // Imprimir tiempos de ejecución
            System.out.println("\nTiempos de ejecución:");
            System.out.printf("QuickSort: %d nanosegundos (%.4f segundos)%n",
                    tiempoQuickSort,
                    tiempoQuickSort / 1_000_000_000.0);
            System.out.printf("MergeSort: %d nanosegundos (%.4f segundos)%n",
                    tiempoMergeSort,
                    tiempoMergeSort / 1_000_000_000.0);
            System.out.printf("ShellSort: %d nanosegundos (%.4f segundos)%n",
                    tiempoShellSort,
                    tiempoShellSort / 1_000_000_000.0);
        }

        // Imprimir resumen final
        System.out.println(resultadosResumen.toString());
    }

    private static int[] generarArregloAleatorio(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100000);
        }
        return array;
    }
}
