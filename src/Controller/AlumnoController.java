/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


/**
 *
 * @author maria
 */

import DAO.alumnoDAO;
import model.Alumno;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AlumnoController {

    private final alumnoDAO alumnoDAO = new alumnoDAO();

    // Método para registrar alumno
    public void registrarAlumno(int idPersona, java.sql.Date fechaNacimiento) {
        java.sql.Date fechaRegistro = new java.sql.Date(System.currentTimeMillis()); // Fecha actual
        Alumno alumno = new Alumno(idPersona, fechaNacimiento, fechaRegistro);
        alumnoDAO.insertarAlumno(alumno);
    }

    // Método para manejar la búsqueda dependiendo de lo que el usuario elija
    public void buscarAlumno() {
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario qué tipo de búsqueda desea realizar
        System.out.println("Seleccione el tipo de búsqueda:");
        System.out.println("1. Buscar por nombre");
        System.out.println("2. Buscar por cédula");
        System.out.println("3. Buscar por carrera");
        System.out.print("Ingrese el número correspondiente: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer del scanner

        // Solicitar el dato de búsqueda según la opción elegida
        String datoBusqueda = "";
        switch (opcion) {
            case 1:
                System.out.print("Ingrese el nombre del alumno: ");
                datoBusqueda = scanner.nextLine();
                buscarPorNombre(datoBusqueda);
                break;
            case 2:
                System.out.print("Ingrese la cédula del alumno: ");
                datoBusqueda = scanner.nextLine();
                buscarPorCedula(datoBusqueda);
                break;
            case 3:
                System.out.print("Ingrese la carrera del alumno: ");
                datoBusqueda = scanner.nextLine();
                buscarPorCarrera(datoBusqueda);
                break;
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
        }
    }

    // Método para buscar por nombre
    private void buscarPorNombre(String nombre) {
        ResultSet rs = alumnoDAO.buscarPorNombre(nombre);
        mostrarResultados(rs);
    }

    // Método para buscar por cédula
    private void buscarPorCedula(String cedula) {
        ResultSet rs = alumnoDAO.buscarPorCedula(cedula);
        mostrarResultados(rs);
    }

    // Método para buscar por carrera
    private void buscarPorCarrera(String carrera) {
        ResultSet rs = alumnoDAO.buscarPorCarrera(carrera);
        mostrarResultados(rs);
    }

    // Método para mostrar los resultados obtenidos de la base de datos
    private void mostrarResultados(ResultSet rs) {
        try {
            boolean encontrado = false;
            while (rs.next()) {
                System.out.println("ID Persona: " + rs.getInt("idPersona"));
                System.out.println("Nombre Alumno: " + rs.getString("NombreAlumno"));
                System.out.println("Cédula: " + rs.getString("cedula"));
                System.out.println("Correo: " + rs.getString("correo"));
                System.out.println("Teléfono: " + rs.getString("telefono"));
                System.out.println("Nombre Carrera: " + rs.getString("NombreCarrera"));
                System.out.println("Código Carrera: " + rs.getInt("CodigoCarrera"));
                System.out.println("----------");
                encontrado = true;
            }
            if (!encontrado) {
                System.out.println("No se encontraron resultados.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al mostrar los resultados: " + e.getMessage());
        }
    }
    public void consultarHistorialAcademico() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la cédula del alumno: ");
        String cedula = scanner.nextLine();

        int idAlumno = alumnoDAO.obtenerIdAlumnoPorCedula(cedula);
        if (idAlumno == -1) {
            System.out.println("❌ Alumno no encontrado.");
            return;
        }

        ResultSet rs = alumnoDAO.obtenerHistorialAcademico(idAlumno);
        try {
            boolean tieneRegistros = false;
            while (rs.next()) {
                System.out.println("Curso: " + rs.getString("curso"));
                System.out.println("Grupo: " + rs.getInt("numeroGrupo"));
                System.out.println("Ciclo: " + rs.getString("nombreCiclo"));
                System.out.println("Nota: " + rs.getDouble("nota"));
                System.out.println("-----------------------------");
                tieneRegistros = true;
            }

            if (!tieneRegistros) {
                System.out.println("⚠️ No hay historial académico registrado para este alumno.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al mostrar historial: " + e.getMessage());
        }
    }
}

