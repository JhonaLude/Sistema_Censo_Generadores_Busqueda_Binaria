/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller.dao.services;

import com.example.controller.dao.FamiliaDAO;
import com.example.models.Familia;

/**
 *
 * @author Alexander Ludena
 */

public class FamiliaService {

    private final FamiliaDAO familiaDAO;

    public FamiliaService() {
        this.familiaDAO = new FamiliaDAO();
    }

    // Métodos CRUD 
    public Familia registrarFamilia(String nombre, int numeroMiembros) {
        Familia familia = new Familia(0, nombre, numeroMiembros, false);
        return familiaDAO.crear(familia);
    }

    public Familia[] obtenerTodasFamilias() {
        return familiaDAO.obtenerTodos();
    }

    public Familia obtenerFamilia(int id) {
        return familiaDAO.obtenerPorId(id);
    }

    public boolean actualizarEstadoGenerador(int familiaId, boolean tieneGenerador) {
        Familia familia = familiaDAO.obtenerPorId(familiaId);
        if (familia != null) {
            familia.setGeneradorAdquirido(tieneGenerador);
            return familiaDAO.actualizar(familia);
        }
        return false;
    }

    // Métodos de Ordenamiento
    public void ordenarPorQuickSort(String atributo, boolean ascendente) throws Exception {
        familiaDAO.ordenarPorQuickSort(atributo, ascendente);
    }

    public void ordenarPorMergeSort(String atributo, boolean ascendente) throws Exception {
        familiaDAO.ordenarPorMergeSort(atributo, ascendente);
    }

    public void ordenarPorShellSort(String atributo, boolean ascendente) throws Exception {
        familiaDAO.ordenarPorShellSort(atributo, ascendente);
    }

    // Método de búsqueda
    public Familia[] busquedaBinaria(String atributo, Object valor) throws Exception {
        return familiaDAO.busquedaBinaria(atributo, valor);
    }

    public void ordenarFamiliasPorQuickSort(String atributo, boolean ascendente) throws Exception {
        familiaDAO.ordenarPorQuickSort(atributo, ascendente);
    }

    public void ordenarFamiliasPorMergeSort(String atributo, boolean ascendente) throws Exception {
        familiaDAO.ordenarPorMergeSort(atributo, ascendente);
    }

    public void ordenarFamiliasPorShellSort(String atributo, boolean ascendente) throws Exception {
        familiaDAO.ordenarPorShellSort(atributo, ascendente);
    }

    // Métodos de búsqueda específicos 
    public Familia[] buscarPorNombre(String nombre) throws Exception {
        return busquedaBinaria("nombre", nombre);
    }

    public Familia[] buscarPorNumeroMiembros(int numeroMiembros) throws Exception {
        return busquedaBinaria("numeroMiembros", numeroMiembros);
    }

    public Familia[] buscarPorGeneradorAdquirido(boolean tieneGenerador) throws Exception {
        return busquedaBinaria("generadorAdquirido", tieneGenerador);
    }

}
