/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author maria
 */

public class Curso {
    private int idCurso;
    private String nombre;
    private int creditos;
    private int horasSemanales;
    private int idCiclo;

    // Constructor con ID (usado al recuperar de la base de datos)
    public Curso(int idCurso, String nombre, int creditos, int horasSemanales, int idCiclo) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.creditos = creditos;
        this.horasSemanales = horasSemanales;
        this.idCiclo = idCiclo;
    }

    // Constructor sin ID (usado al insertar)
    public Curso(String nombre, int creditos, int horasSemanales, int idCiclo) {
        this.nombre = nombre;
        this.creditos = creditos;
        this.horasSemanales = horasSemanales;
        this.idCiclo = idCiclo;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public int getHorasSemanales() {
        return horasSemanales;
    }

    public int getIdCiclo() {
        return idCiclo;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public void setHorasSemanales(int horasSemanales) {
        this.horasSemanales = horasSemanales;
    }

    public void setIdCiclo(int idCiclo) {
        this.idCiclo = idCiclo;
    }
}
