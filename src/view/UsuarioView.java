/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author maria
 */

import model.Usuario1;
import java.util.List;

public class UsuarioView {

    // Método para mostrar los profesores encontrados
    public void mostrarProfesores(List<Usuario1> profesores) {
        if (profesores.isEmpty()) {
            System.out.println("No se encontraron profesores.");
        } else {
            System.out.println("Profesores encontrados:");
            for (Usuario1 profesor : profesores) {
                System.out.println("ID: " + profesor.getIdPersona());
                System.out.println("Nombre: " + profesor.getNombreProfesor());
                System.out.println("Cédula: " + profesor.getCedula());
                System.out.println("Correo: " + profesor.getCorreo());
                System.out.println("Teléfono: " + profesor.getTelefono());
                System.out.println("Departamento: " + profesor.getDepartamento());
                System.out.println("-----------------------------------");
            }
        }
    }

    // Método para mostrar mensajes de error
    public void mostrarError(String mensaje) {
        System.out.println("ERROR: " + mensaje);
    }
}
