/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller.dao.services;

import com.example.controller.dao.EstadisticaDAO;
import com.example.models.Estadistica;

/**
 *
 * @author Alexander Ludena
 */

public class EstadisticaService {

    private final EstadisticaDAO estadisticaDAO;

    public EstadisticaService() {
        this.estadisticaDAO = new EstadisticaDAO();
    }

    public Estadistica[] obtenerTodasEstadisticas() {
        return estadisticaDAO.obtenerTodas();
    }

    public Estadistica obtenerEstadisticaPorId(int id) {
        return estadisticaDAO.obtenerPorId(id);
    }

    public boolean agregarEstadistica(Estadistica estadistica) {

        if (estadistica.getValor() < 0) {
            return false;
        }
        return estadisticaDAO.agregarEstadistica(estadistica);
    }

    public boolean actualizarEstadistica(Estadistica estadistica) {
        
        if (estadistica.getValor() < 0) {
            return false;
        }
        return estadisticaDAO.actualizarEstadistica(estadistica);
    }

    public boolean eliminarEstadistica(int id) {
        return estadisticaDAO.eliminarEstadistica(id);
    }

    // Método de obtener por categoría 
    public Estadistica[] obtenerEstadisticasPorCategoria(String categoria) {
        Estadistica[] todas = estadisticaDAO.obtenerTodas();
        int count = 0;

        for (Estadistica est : todas) {
            if (est.getCategoria().equals(categoria)) {
                count++;
            }
        }

        Estadistica[] filtradas = new Estadistica[count];
        int index = 0;

        for (Estadistica est : todas) {
            if (est.getCategoria().equals(categoria)) {
                filtradas[index++] = est;
            }
        }

        return filtradas;
    }

    // Métodos de ordenamiento
    public void ordenarPorQuickSort(String atributo, boolean ascendente) throws Exception {
        estadisticaDAO.ordenarPorQuickSort(atributo, ascendente);
    }

    public void ordenarPorMergeSort(String atributo, boolean ascendente) throws Exception {
        estadisticaDAO.ordenarPorMergeSort(atributo, ascendente);
    }

    public void ordenarPorShellSort(String atributo, boolean ascendente) throws Exception {
        estadisticaDAO.ordenarPorShellSort(atributo, ascendente);
    }

    // Método de búsqueda
    public Estadistica[] busquedaBinaria(String atributo, Object valor) throws Exception {
        return estadisticaDAO.busquedaBinaria(atributo, valor);
    }

    public Estadistica[] buscarEstadisticas(String atributo, Object valor) throws Exception {
        return estadisticaDAO.busquedaBinaria(atributo, valor);
    }

}
