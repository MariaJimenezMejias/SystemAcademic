/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

// File: src/controller/CrearUsuarioControlador.java

import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.Scanner;
import DB.dbConnection;
import model.Profesor;
import model.Administrador;
import Controller.AdministradorController;
import Controller.AlumnoController;

public class CrearUsuarioController {

    public void crearUsuario(String cedula, String clave, String tipo) {
        // Verificar si la cedula existe en la tabla Persona y obtener el idPersona
        int idPersona = obtenerIdPersonaPorCedula(cedula);

        if (idPersona == -1) {
            System.out.println("Error: La cedula no existe en la tabla Persona.");
            return;
        }

        // Ciclo while para asegurarnos de que el tipo de usuario es valido
        while (!(tipo.equalsIgnoreCase("admin") || tipo.equalsIgnoreCase("matriculador")
                 || tipo.equalsIgnoreCase("alumno") || tipo.equalsIgnoreCase("profesor"))) {
            // Si el tipo no es valido, volver a preguntar por el tipo
            System.out.println("Por favor, ingrese un tipo valido (admin, matriculador, alumno, profesor):");
            tipo = new Scanner(System.in).nextLine(); // Solicitar el tipo de usuario nuevamente
        }

        // Crear el objeto Usuario y llamar al metodo de insercion
        Usuario usuario = new Usuario(idPersona, clave, tipo);
        try {
            int idUsuario = usuario.insertarUsuario();
            if (idUsuario > 0) {
                System.out.println("Usuario creado con exito.");

                // Si el tipo es "admin", insertar en la tabla Administrador
                if ("admin".equalsIgnoreCase(tipo)) {
                    AdministradorController administradorController = new AdministradorController();
                    administradorController.crearAdministrador(idUsuario);

                }
                // Si el tipo es "alumno", insertar en la tabla Alumno
                else if ("alumno".equalsIgnoreCase(tipo)) {
                    // Pedir la fecha de nacimiento del alumno
                    System.out.println("Por favor, ingrese la fecha de nacimiento del alumno (YYYY-MM-DD):");
                    String fechaNacimientoStr = new Scanner(System.in).nextLine();
                    
                    // Convertir la fecha de nacimiento en tipo java.sql.Date
                    Date fechaNacimiento = Date.valueOf(fechaNacimientoStr); 
                    
                    Date fechaRegistro = Date.valueOf(java.time.LocalDate.now()); 

                    // Crear el objeto Alumno y llamar al metodo de insercion
                    AlumnoController alumnoController = new AlumnoController();
                    alumnoController.registrarAlumno(idPersona, fechaNacimiento);
                } 
                else if ("profesor".equalsIgnoreCase(tipo)) {
                    ProfesorController.crearProfesor(); // metodo sin parametros
                } 
                else if ("matriculador".equalsIgnoreCase(tipo)) {
                    MatriculadorController controller = new MatriculadorController();
                    System.out.println("Ingrese el estado del matriculador:");
                    String estado = new Scanner(System.in).nextLine();
                    controller.crearMatriculador(idUsuario, estado);
                }
            } else {
                System.out.println("Error: No se pudo obtener el idUsuario.");
            }
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e.getMessage());
        }
    }

    // Metodo para obtener el idPersona por la cedula
    public static int obtenerIdPersonaPorCedula(String cedula) {
        String sql = "SELECT idPersona FROM Persona WHERE cedula = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, cedula);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("idPersona"); 
            } else {
                return -1; 
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar la cedula: " + e.getMessage());
            return -1;
        }
    }
}
