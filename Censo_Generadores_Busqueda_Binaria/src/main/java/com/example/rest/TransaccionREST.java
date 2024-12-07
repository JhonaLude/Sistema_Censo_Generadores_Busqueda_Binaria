/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.rest;

import com.example.controller.dao.services.TransaccionService;
import com.example.models.Transaccion;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Alexander Ludena
 */

@Path("/transacciones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransaccionREST {

    private final TransaccionService transaccionService = new TransaccionService();

    // Métodos de obtención 
    @GET
    public Response obtenerTransacciones() {
        return Response.ok(transaccionService.obtenerTodasTransacciones()).build();
    }

    @GET
    @Path("/familia/{familiaId}")
    public Response obtenerTransaccionesPorFamilia(@PathParam("familiaId") int familiaId) {
        Transaccion[] transacciones = transaccionService.obtenerTransaccionesPorFamilia(familiaId);
        if (transacciones != null && transacciones.length > 0) {
            return Response.ok(transacciones).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{id}")
    public Response obtenerTransaccion(@PathParam("id") int id) {
        Transaccion[] transacciones = transaccionService.obtenerTodasTransacciones();
        for (Transaccion t : transacciones) {
            if (t.getId() == id) {
                return Response.ok(t).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response crearTransaccion(Transaccion transaccion) {
        Transaccion nueva = transaccionService.registrarTransaccion(
                transaccion.getFamiliaId(),
                transaccion.getGeneradorId(),
                transaccion.getTipo()
        );
        return Response.status(Response.Status.CREATED).entity(nueva).build();
    }

    // Métodos de ordenamiento
    @GET
    @Path("/ordenar/quicksort")
    public Response ordenarPorQuickSort(
            @QueryParam("atributo") String atributo,
            @QueryParam("ascendente") @DefaultValue("true") boolean ascendente) {
        try {
            transaccionService.ordenarPorQuickSort(atributo, ascendente);
            return Response.ok(transaccionService.obtenerTodasTransacciones()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/ordenar/mergesort")
    public Response ordenarPorMergeSort(
            @QueryParam("atributo") String atributo,
            @QueryParam("ascendente") @DefaultValue("true") boolean ascendente) {
        try {
            transaccionService.ordenarPorMergeSort(atributo, ascendente);
            return Response.ok(transaccionService.obtenerTodasTransacciones()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/ordenar/shellsort")
    public Response ordenarPorShellSort(
            @QueryParam("atributo") String atributo,
            @QueryParam("ascendente") @DefaultValue("true") boolean ascendente) {
        try {
            transaccionService.ordenarPorShellSort(atributo, ascendente);
            return Response.ok(transaccionService.obtenerTodasTransacciones()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
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
            Transaccion[] resultados = transaccionService.busquedaBinaria(atributo, valor);
            return Response.ok(resultados).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error en búsqueda binaria: " + e.getMessage())
                    .build();
        }
    }
}
