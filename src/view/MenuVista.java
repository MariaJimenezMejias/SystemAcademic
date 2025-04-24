/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.Scanner;
import Controller.CrearUsuarioController;

/**
 *
 * @author maria
 */
public class MenuVista {

    public void mostrarMenuCrearUsuario() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Creación de Usuario ===");
        System.out.println("Ingrese la cédula:");
        String cedula = scanner.nextLine();

        System.out.println("Ingrese la clave:");
        String clave = scanner.nextLine();

        System.out.println("Ingrese el tipo de usuario (admin, matriculador, alumno, profesor):");
        String tipo = scanner.nextLine();

        // Llamar al controlador para crear el usuario
        CrearUsuarioController crearUsuarioController = new CrearUsuarioController();
        crearUsuarioController.crearUsuario(cedula, clave, tipo);  // Controlador maneja la lógica
    }
}

