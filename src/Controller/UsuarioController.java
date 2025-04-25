package Controller;

import DAO.UsuarioDAO;
import model.Usuario1;
import view.UsuarioView;
import java.util.List;
import java.sql.*;
import java.util.Scanner;
import model.Usuario;

public class UsuarioController {

    // Metodo para registrar un nuevo usuario
    public void registrarUsuario() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el ID de la persona:");
        int idPersona = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        System.out.println("Ingrese la clave del usuario:");
        String clave = scanner.nextLine();

        // Validacion del tipo de usuario
        String tipo = "";
        while (!(tipo.equalsIgnoreCase("admin") || tipo.equalsIgnoreCase("matriculador")
                 || tipo.equalsIgnoreCase("alumno") || tipo.equalsIgnoreCase("profesor"))) {
            System.out.println("Tipo de usuario no valido. Por favor, ingrese un tipo valido (admin, matriculador, alumno, profesor):");
            tipo = scanner.nextLine(); // Solicitar el tipo de usuario nuevamente
        }

        // Crear un objeto Usuario
        Usuario usuario = new Usuario(idPersona, clave, tipo);

        try {
            int idUsuario = usuario.insertarUsuario();
            if (idUsuario != -1) {
                System.out.println("Usuario registrado correctamente con ID: " + idUsuario);
            } else {
                System.out.println("No se pudo registrar el usuario.");
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar el usuario: " + e.getMessage());
        }
    }

    // Metodo para mostrar el menu y realizar acciones
   
}

