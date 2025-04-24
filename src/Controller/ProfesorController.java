package Controller;

import DAO.profesorDAO;
import model.Usuario1;
import model.Profesor;
import java.util.List;
import java.util.Scanner;

public class ProfesorController {

    public static void crearProfesor() {
        Scanner scanner = new Scanner(System.in);
        Profesor profesor = new Profesor();

        System.out.print("Ingrese el ID de usuario (idUsuario): ");
        profesor.setIdUsuario(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Ingrese el departamento: ");
        profesor.setDepartamento(scanner.nextLine());

        System.out.print("Ingrese la cedula del profesor: ");
        profesor.setCedula(scanner.nextLine());

        new profesorDAO().insertarProfesor(profesor);
    }

    public static void mostrarProfesores() {
        List<Usuario1> profesores = new profesorDAO().listarProfesores();

        if (profesores.isEmpty()) {
            System.out.println("No hay profesores registrados.");
            return;
        }

        System.out.println("\nLista de Profesores:");
        for (Usuario1 p : profesores) {
            System.out.println("ID: " + p.getIdPersona());
            System.out.println("Nombre: " + p.getNombreProfesor());
            System.out.println("Cedula: " + p.getCedula());
            System.out.println("Correo: " + p.getCorreo());
            System.out.println("Telefono: " + p.getTelefono());
            System.out.println("Departamento: " + p.getDepartamento());
            System.out.println("--------------------------");
        }
    }

    public static void eliminarProfesor() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del profesor a eliminar: ");
        int id = scanner.nextInt();

        new profesorDAO().eliminarProfesor(id);
    }

    public static void buscarProfesorPorCedula() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la cedula del profesor: ");
        String cedula = scanner.nextLine();

        profesorDAO dao = new profesorDAO();
        try {
            List<Usuario1> profesores = dao.buscarProfesorPorCedula(cedula);
            if (profesores.isEmpty()) {
                System.out.println("No se encontro ningun profesor con esa cedula.");
            } else {
                for (Usuario1 profesor : profesores) {
                    System.out.println("ID: " + profesor.getIdPersona());
                    System.out.println("Nombre: " + profesor.getNombreProfesor());
                    System.out.println("Cedula: " + profesor.getCedula());
                    System.out.println("Correo: " + profesor.getCorreo());
                    System.out.println("Telefono: " + profesor.getTelefono());
                    System.out.println("Departamento: " + profesor.getDepartamento());
                    System.out.println("--------------------------");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar profesor: " + e.getMessage());
        }
    }

    public static void buscarProfesorPorNombre() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del profesor: ");
        String nombre = scanner.nextLine();

        profesorDAO dao = new profesorDAO();
        try {
            List<Usuario1> profesores = dao.buscarProfesorPorNombre(nombre);
            if (profesores.isEmpty()) {
                System.out.println("No se encontro ningun profesor con ese nombre.");
            } else {
                for (Usuario1 profesor : profesores) {
                    System.out.println("ID: " + profesor.getIdPersona());
                    System.out.println("Nombre: " + profesor.getNombreProfesor());
                    System.out.println("Cedula: " + profesor.getCedula());
                    System.out.println("Correo: " + profesor.getCorreo());
                    System.out.println("Telefono: " + profesor.getTelefono());
                    System.out.println("Departamento: " + profesor.getDepartamento());
                    System.out.println("--------------------------");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar profesor: " + e.getMessage());
        }
    }
}
