/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.models;

/**
 *
 * @author Alexander Ludena
 */

public class Familia {

    private int id;
    private String nombre;
    private int numeroMiembros;
    private boolean generadorAdquirido;

    public Familia(int id, String nombre, int numeroMiembros, boolean generadorAdquirido) {
        this.id = id;
        this.nombre = nombre;
        this.numeroMiembros = numeroMiembros;
        this.generadorAdquirido = generadorAdquirido;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroMiembros() {
        return numeroMiembros;
    }

    public void setNumeroMiembros(int numeroMiembros) {
        this.numeroMiembros = numeroMiembros;
    }

    public boolean isGeneradorAdquirido() {
        return generadorAdquirido;
    }

    public void setGeneradorAdquirido(boolean generadorAdquirido) {
        this.generadorAdquirido = generadorAdquirido;
    }
}
