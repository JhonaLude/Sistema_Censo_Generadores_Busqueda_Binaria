/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller.dao.services;

import com.example.controller.dao.TransaccionDAO;
import com.example.models.Transaccion;
import java.util.Date;

/**
 *
 * @author Alexander Ludena
 */

public class TransaccionService {

    private final TransaccionDAO transaccionDAO;

    public TransaccionService() {
        this.transaccionDAO = new TransaccionDAO();
    }

    // Métodos CRUD 
    public Transaccion registrarTransaccion(int familiaId, int generadorId, String tipo) {
        Transaccion transaccion = new Transaccion(0, new Date(), familiaId, generadorId, tipo);
        return transaccionDAO.crear(transaccion);
    }

    public Transaccion[] obtenerTransaccionesPorFamilia(int familiaId) {
        return transaccionDAO.obtenerPorFamiliaId(familiaId);
    }

    public Transaccion[] obtenerTodasTransacciones() {
        return transaccionDAO.obtenerTodos();
    }

    // Métodos de ordenamiento
    public void ordenarPorQuickSort(String atributo, boolean ascendente) throws Exception {
        transaccionDAO.ordenarPorQuickSort(atributo, ascendente);
    }

    public void ordenarPorMergeSort(String atributo, boolean ascendente) throws Exception {
        transaccionDAO.ordenarPorMergeSort(atributo, ascendente);
    }

    public void ordenarPorShellSort(String atributo, boolean ascendente) throws Exception {
        transaccionDAO.ordenarPorShellSort(atributo, ascendente);
    }

    public Transaccion[] busquedaSecuencial(String atributo, Object valorConvertido) throws Exception {
        return transaccionDAO.busquedaBinaria(atributo, valorConvertido);
    }

    public void ordenarTransaccionesPorQuickSort(String atributo, boolean ascendente) throws Exception {
        transaccionDAO.ordenarPorQuickSort(atributo, ascendente);
    }

    public void ordenarTransaccionesPorMergeSort(String atributo, boolean ascendente) throws Exception {
        transaccionDAO.ordenarPorMergeSort(atributo, ascendente);
    }

    public void ordenarTransaccionesPorShellSort(String atributo, boolean ascendente) throws Exception {
        transaccionDAO.ordenarPorShellSort(atributo, ascendente);
    }

    //Método de Búsqueda Binaria
    public Transaccion[] busquedaBinaria(String atributo, String valor) throws Exception {
        return transaccionDAO.busquedaBinaria(atributo, valor);
    }

    // Métodos de búsqueda
    public Transaccion[] buscarTransacciones(String atributo, Object valorBuscado) throws Exception {
        return transaccionDAO.busquedaBinaria(atributo, valorBuscado);
    }

    // Métodos de búsqueda específicos 
    public Transaccion[] buscarTransaccionesPorFamiliaId(int familiaId) throws Exception {
        return buscarTransacciones("familiaId", familiaId);
    }

    public Transaccion[] buscarTransaccionesPorTipo(String tipo) throws Exception {
        return buscarTransacciones("tipo", tipo);
    }

    public Transaccion[] buscarTransaccionesPorGeneradorId(int generadorId) throws Exception {
        return buscarTransacciones("generadorId", generadorId);
    }

    public Transaccion[] buscarTransaccionesPorFecha(Date fecha) throws Exception {
        return buscarTransacciones("fecha", fecha);
    }

}
