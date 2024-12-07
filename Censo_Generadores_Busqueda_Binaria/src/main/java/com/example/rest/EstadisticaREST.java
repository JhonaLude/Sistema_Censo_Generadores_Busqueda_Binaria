/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.rest;

/**
 *
 * @author Alexander Ludena
 */

import com.example.models.Estadistica;
import com.example.controller.dao.services.EstadisticaService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/estadisticas")
public class EstadisticaREST {

    private final EstadisticaService estadisticaService;

    public EstadisticaREST() {
        this.estadisticaService = new EstadisticaService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodas() {
        return Response.ok(estadisticaService.obtenerTodasEstadisticas()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorId(@PathParam("id") int id) {
        Estadistica estadistica = estadisticaService.obtenerEstadisticaPorId(id);
        if (estadistica != null) {
            return Response.ok(estadistica).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crear(Estadistica estadistica) {
        if (estadisticaService.agregarEstadistica(estadistica)) {
            return Response.status(Response.Status.CREATED).entity(estadistica).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizar(@PathParam("id") int id, Estadistica estadistica) {
        estadistica.setId(id);
        if (estadisticaService.actualizarEstadistica(estadistica)) {
            return Response.ok(estadistica).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") int id) {
        if (estadisticaService.eliminarEstadistica(id)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/categoria/{categoria}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorCategoria(@PathParam("categoria") String categoria) {
        return Response.ok(estadisticaService.obtenerEstadisticasPorCategoria(categoria)).build();
    }

    // Endpoints para ordenamiento
    @GET
    @Path("/ordenar/quicksort")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ordenarPorQuickSort(
            @QueryParam("atributo") String atributo,
            @QueryParam("ascendente") @DefaultValue("true") boolean ascendente
    ) {
        try {
            estadisticaService.ordenarPorQuickSort(atributo, ascendente);
            return Response.ok(estadisticaService.obtenerTodasEstadisticas()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error en ordenamiento QuickSort: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/ordenar/mergesort")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ordenarPorMergeSort(
            @QueryParam("atributo") String atributo,
            @QueryParam("ascendente") @DefaultValue("true") boolean ascendente
    ) {
        try {
            estadisticaService.ordenarPorMergeSort(atributo, ascendente);
            return Response.ok(estadisticaService.obtenerTodasEstadisticas()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error en ordenamiento MergeSort: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/ordenar/shellsort")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ordenarPorShellSort(
            @QueryParam("atributo") String atributo,
            @QueryParam("ascendente") @DefaultValue("true") boolean ascendente
    ) {
        try {
            estadisticaService.ordenarPorShellSort(atributo, ascendente);
            return Response.ok(estadisticaService.obtenerTodasEstadisticas()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error en ordenamiento ShellSort: " + e.getMessage())
                    .build();
        }
    }
    
    //Método de Búsqueda Binaria
    @GET
    @Path("/buscar/binario")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarBinario(
            @QueryParam("atributo") String atributo,
            @QueryParam("valor") String valor) {
        try {
            Estadistica[] resultados = estadisticaService.busquedaBinaria(atributo, valor);
            return Response.ok(resultados).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error en búsqueda binaria: " + e.getMessage())
                    .build();
        }
    }
}
