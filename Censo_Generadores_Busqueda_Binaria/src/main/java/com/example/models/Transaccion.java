/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.models;

/**
 *
 * @author Alexander Ludena
 */

import java.util.Date;

public class Transaccion {

    private int id;
    private Date fecha;
    private int familiaId;
    private int generadorId;
    private String tipo;

    public Transaccion(int id, Date fecha, int familiaId, int generadorId, String tipo) {
        this.id = id;
        this.fecha = fecha;
        this.familiaId = familiaId;
        this.generadorId = generadorId;
        this.tipo = tipo;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getFamiliaId() {
        return familiaId;
    }

    public void setFamiliaId(int familiaId) {
        this.familiaId = familiaId;
    }

    public int getGeneradorId() {
        return generadorId;
    }

    public void setGeneradorId(int generadorId) {
        this.generadorId = generadorId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
