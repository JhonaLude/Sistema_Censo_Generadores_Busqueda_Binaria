/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.rest;

import com.example.controller.dao.services.FamiliaService;
import com.example.models.Familia;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Alexander Ludena
 */

@Path("/familias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FamiliaREST {

    private final FamiliaService familiaService = new FamiliaService();

    @GET
    public Response obtenerFamilias() {
        return Response.ok(familiaService.obtenerTodasFamilias()).build();
    }

    @GET
    @Path("/{id}")
    public Response obtenerFamilia(@PathParam("id") int id) {
        Familia familia = familiaService.obtenerFamilia(id);
        if (familia != null) {
            return Response.ok(familia).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response crearFamilia(Familia familia) {
        Familia nueva = familiaService.registrarFamilia(
                familia.getNombre(),
                familia.getNumeroMiembros()
        );
        return Response.status(Response.Status.CREATED).entity(nueva).build();
    }

    // Métodos para ordenamiento
    @GET
    @Path("/ordenar/quicksort")
    public Response ordenarPorQuickSort(
            @QueryParam("atributo") String atributo,
            @QueryParam("ascendente") @DefaultValue("true") boolean ascendente) {
        try {
            familiaService.ordenarPorQuickSort(atributo, ascendente);
            return Response.ok(familiaService.obtenerTodasFamilias()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al ordenar: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/ordenar/mergesort")
    public Response ordenarPorMergeSort(
            @QueryParam("atributo") String atributo,
            @QueryParam("ascendente") @DefaultValue("true") boolean ascendente) {
        try {
            familiaService.ordenarPorMergeSort(atributo, ascendente);
            return Response.ok(familiaService.obtenerTodasFamilias()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al ordenar: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/ordenar/shellsort")
    public Response ordenarPorShellSort(
            @QueryParam("atributo") String atributo,
            @QueryParam("ascendente") @DefaultValue("true") boolean ascendente) {
        try {
            familiaService.ordenarPorShellSort(atributo, ascendente);
            return Response.ok(familiaService.obtenerTodasFamilias()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al ordenar: " + e.getMessage())
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
            Familia[] resultados = familiaService.busquedaBinaria(atributo, valor);
            return Response.ok(resultados).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error en búsqueda binaria: " + e.getMessage())
                    .build();
        }
    }

}
