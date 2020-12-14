package com.example.infact.modelos;

public class Formulario {
    private String nombre;
    private int mes,anio,id;
    private boolean estado;

    public Formulario()
    {

    }
    public Formulario(String nombre, int mes, int anio, boolean estado) {
        this.nombre = nombre;
        this.mes = mes;
        this.anio = anio;
        this.estado = estado;
    }

    public Formulario(int id, String nombre, int mes, int anio, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.mes = mes;
        this.anio = anio;
        this.estado = estado;
    }
    //Para enlistar
    public Formulario(String nombre, int mes, int anio) {
        this.nombre = nombre;
        this.mes = mes;
        this.anio = anio;
    }
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

    public int getMes() {
        return mes;
    }
    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }
    public void setAnio(int anio) {
        this.anio = anio;
    }

    public boolean getEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }


}