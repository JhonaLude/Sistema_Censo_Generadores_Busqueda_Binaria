/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller.dao.services;

import com.example.controller.dao.GeneradorDAO;
import com.example.models.Generador;

/**
 *
 * @author Alexander Ludena
 */

public class GeneradorService {

    private final GeneradorDAO generadorDAO;

    public GeneradorService() {
        this.generadorDAO = new GeneradorDAO();
    }

    // Métodos 
    public Generador registrarGenerador(String marca, String modelo, double costo,
            double consumoPorHora, double generacion, String uso) {
        Generador generador = new Generador(0, marca, modelo, costo, consumoPorHora, generacion, uso);
        return generadorDAO.crear(generador);
    }

    public Generador[] obtenerTodosGeneradores() {
        return generadorDAO.obtenerTodos();
    }

    public Generador obtenerGenerador(int id) {
        return generadorDAO.obtenerPorId(id);
    }

    public boolean actualizarGenerador(Generador generador) {
        return generadorDAO.actualizar(generador);
    }

    // Métodos de ordenamiento
    
    public void ordenarPorQuickSort(String atributo, boolean ascendente) throws Exception {
        generadorDAO.ordenarPorQuickSort(atributo, ascendente);
    }

    public void ordenarPorMergeSort(String atributo, boolean ascendente) throws Exception {
        generadorDAO.ordenarPorMergeSort(atributo, ascendente);
    }

    public void ordenarPorShellSort(String atributo, boolean ascendente) throws Exception {
        generadorDAO.ordenarPorShellSort(atributo, ascendente);
    }

    public Generador[] busquedaSecuencial(String atributo, Object valor) throws Exception {
        return generadorDAO.busquedaBinaria(atributo, valor);
    }
    
    public void ordenarGeneradoresPorQuickSort(String atributo, boolean ascendente) throws Exception {
        generadorDAO.ordenarPorQuickSort(atributo, ascendente);
    }

    public void ordenarGeneradoresPorMergeSort(String atributo, boolean ascendente) throws Exception {
        generadorDAO.ordenarPorMergeSort(atributo, ascendente);
    }

    public void ordenarGeneradoresPorShellSort(String atributo, boolean ascendente) throws Exception {
        generadorDAO.ordenarPorShellSort(atributo, ascendente);
    }
    
    //Método de Búsqueda
    
    public Generador[] busquedaBinaria(String atributo, String valor) throws Exception {
        return generadorDAO.busquedaBinaria(atributo, valor);
    }

    
    public Generador[] buscarGeneradores(String atributo, Object valorBuscado) throws Exception {
        return generadorDAO.busquedaBinaria(atributo, valorBuscado);
    }

    public Generador[] buscarPorMarca(String marca) throws Exception {
        return buscarGeneradores("marca", marca);
    }

    public Generador[] buscarPorModelo(String modelo) throws Exception {
        return buscarGeneradores("modelo", modelo);
    }

    public Generador[] buscarPorUso(String uso) throws Exception {
        return buscarGeneradores("uso", uso);
    }

    public Generador[] buscarPorCostoMayor(double costo) throws Exception {
        return buscarGeneradores("costo", costo);
    }

}
