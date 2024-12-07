/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.test;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Alexander Ludena
 */

public class PruebaBusquedaBinaria {

    private static final int[] SIZES = {10000, 20000, 25000};
    private static StringBuilder resultadosResumen = new StringBuilder();

    public static void main(String[] args) {
        
        resultadosResumen.append("\n--- RESUMEN FINAL DE RESULTADOS ---\n");
        resultadosResumen.append(String.format("%-15s %-20s %-25s %-25s\n", 
            "Tamaño Array", "Elemento Buscado", "Índice Encontrado", "Tiempo (nanosegundos)"));
        resultadosResumen.append("-".repeat(85)).append("\n");

        
        for (int size : SIZES) {
            System.out.println("\n--- Tamaño del arreglo: " + size + " ---");

            // Generar arreglo aleatorio
            int[] originalArray = generarArregloAleatorio(size);
            
            // Ordenar el arreglo para la búsqueda binaria
            int[] sortedArray = Arrays.copyOf(originalArray, originalArray.length);
            Arrays.sort(sortedArray);

            // Mostrar arreglo original y ordenado
            BusquedaBinaria.mostrarArreglo(originalArray, "Arreglo original:");
            BusquedaBinaria.mostrarArreglo(sortedArray, "Arreglo ordenado:");

            // Elemento a buscar 
            int elementoBuscar = sortedArray[new Random().nextInt(size)];
            System.out.println("\nElemento a buscar: " + elementoBuscar);

            // Búsqueda Binaria
            System.out.println("\n--- Búsqueda Binaria ---");
            long inicioBinaria = System.nanoTime();
            int indiceBinario = BusquedaBinaria.busquedaBinaria(sortedArray, elementoBuscar);
            long finBinaria = System.nanoTime();

            // Calcular tiempo de ejecución
            long tiempoEjecucion = finBinaria - inicioBinaria;

            // Imprimir resultados
            System.out.println("\nResultados de búsqueda:");
            System.out.println("Búsqueda Binaria - Índice: " + indiceBinario);

            // Imprimir tiempos de ejecución
            System.out.printf("Búsqueda Binaria: %d nanosegundos (%.4f segundos)%n",
                    tiempoEjecucion,
                    tiempoEjecucion / 1_000_000_000.0);

            
            resultadosResumen.append(String.format("%-15d %-20d %-25d %-25d\n", 
                size, elementoBuscar, indiceBinario, tiempoEjecucion));
        }

        System.out.println(resultadosResumen.toString());
    }

    // Generar arreglo aleatorio
    private static int[] generarArregloAleatorio(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100000); 
        }
        return array;
    }
}