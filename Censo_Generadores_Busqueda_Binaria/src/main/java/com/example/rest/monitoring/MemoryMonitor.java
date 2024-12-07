/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.rest.monitoring;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 *
 * @author Alexander Ludena
 */

public class MemoryMonitor {

    private static final Logger LOGGER = Logger.getLogger(MemoryMonitor.class.getName());
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static final String PROCESS_ID = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];

    public void startMonitoring(int intervalSeconds) {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                // Ejecutar jcmd para obtener estadísticas de memoria
                ProcessBuilder processBuilder = new ProcessBuilder(
                        "jcmd",
                        PROCESS_ID,
                        "GC.heap_info"
                );
                Process process = processBuilder.start();

                // Leer la salida del comando
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()))) {
                    StringBuilder output = new StringBuilder();
                    output.append("\n=== Memoria JVM ").append(getCurrentTimestamp()).append(" ===\n");
                    String line;
                    while ((line = reader.readLine()) != null) {
                        output.append(line).append("\n");
                    }
                    LOGGER.info(output.toString());
                }

                // Ejecutar jcmd para estadísticas detalladas de memoria
                processBuilder = new ProcessBuilder(
                        "jcmd",
                        PROCESS_ID,
                        "VM.native_memory"
                );
                process = processBuilder.start();

                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()))) {
                    StringBuilder output = new StringBuilder();
                    output.append("\n=== Memoria Nativa ").append(getCurrentTimestamp()).append(" ===\n");
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("Total")) {
                            output.append(line).append("\n");
                        }
                    }
                    LOGGER.info(output.toString());
                }

                // Recolectar estadísticas de memoria de la JVM
                Runtime runtime = Runtime.getRuntime();
                long totalMemory = runtime.totalMemory();
                long freeMemory = runtime.freeMemory();
                long usedMemory = totalMemory - freeMemory;

               
                LOGGER.info(String.format("\n=== Resumen de Memoria ===\n" +
                    "Memoria Total: %d MB\n" +
                    "Memoria Usada: %d MB\n" +
                    "Memoria Libre: %d MB",
                    totalMemory / (1024 * 1024),
                    usedMemory / (1024 * 1024),
                    freeMemory / (1024 * 1024)));

            } catch (Exception e) {
                LOGGER.severe("Error al monitorear la memoria: " + e.getMessage());
            }
        }, 0, intervalSeconds, TimeUnit.SECONDS);
    }

    public void stopMonitoring() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    private String getCurrentTimestamp() {
        return java.time.LocalDateTime.now().toString();
    }
}