/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.models;

/**
 *
 * @author Alexander Ludena
 */

public class Generador {

    private int id;
    private String marca;
    private String modelo;
    private double costo;
    private double consumoPorHora;
    private double generacion;
    private String uso;

    public Generador(int id, String marca, String modelo, double costo,
            double consumoPorHora, double generacion, String uso) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.costo = costo;
        this.consumoPorHora = consumoPorHora;
        this.generacion = generacion;
        this.uso = uso;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getConsumoPorHora() {
        return consumoPorHora;
    }

    public void setConsumoPorHora(double consumoPorHora) {
        this.consumoPorHora = consumoPorHora;
    }

    public double getGeneracion() {
        return generacion;
    }

    public void setGeneracion(double generacion) {
        this.generacion = generacion;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }
}
