/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author maria
 */


public class Carrera {
    private int idCarrera;
    private String titulo;
    private String nombreCarrera;

    // Constructor
    public Carrera(int idCarrera, String titulo, String nombreCarrera) {
        this.idCarrera = idCarrera;
        this.titulo = titulo;
        this.nombreCarrera = nombreCarrera;
    }

    // Getters y Setters
    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }
}

