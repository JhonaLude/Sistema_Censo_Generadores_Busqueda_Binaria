package com.example.rest;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import com.example.rest.monitoring.MemoryMonitor;

import java.io.IOException;
import java.net.URI;

public class Main {
    public static final String BASE_URI = "http://localhost:8081/myapp/";
    private static MemoryMonitor memoryMonitor;

    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages("com.example.rest");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        // Iniciar el monitor de memoria
        memoryMonitor = new MemoryMonitor();
        memoryMonitor.startMonitoring(30); // Monitorear cada 30 segundos

        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        
        // Esperar entrada del usuario
        System.in.read();
        
        // Detener el monitor y el servidor
        memoryMonitor.stopMonitoring();
        server.stop();
    }
}

