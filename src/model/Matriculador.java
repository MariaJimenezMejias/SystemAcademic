/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Matriculador {
    private int idUsuario;
    private String estado;

    public Matriculador(int idUsuario, String estado) {
        this.idUsuario = idUsuario;
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getEstado() {
        return estado;
    }
}
