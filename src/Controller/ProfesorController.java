/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author maria
 */

import DAO.profesorDAO;
import model.Profesor;



import DAO.profesorDAO;
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

        System.out.print("Ingrese la cédula del profesor: ");
        profesor.setCedula(scanner.nextLine());

        new profesorDAO().insertarProfesor(profesor);
    }

    public static void mostrarProfesores() {
        List<Profesor> profesores = new profesorDAO().listarProfesores();

        if (profesores.isEmpty()) {
            System.out.println("📭 No hay profesores registrados.");
            return;
        }

        System.out.println("\n📋 Lista de Profesores:");
        for (Profesor p : profesores) {
            System.out.println("ID: " + p.getIdUsuario());
            System.out.println("Nombre: " + p.getNombre());
            System.out.println("Departamento: " + p.getDepartamento());
            System.out.println("Cédula: " + p.getCedula());
            System.out.println("--------------------------");
        }
    }

    public static void eliminarProfesor() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del profesor a eliminar: ");
        int id = scanner.nextInt();

        new profesorDAO().eliminarProfesor(id);
    }
    public static void buscarPorNombre() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del profesor: ");
        String nombre = scanner.nextLine();
    
        List<Profesor> profesores = new profesorDAO().buscarPorNombre(nombre);

        if (profesores.isEmpty()) {
        System.out.println("❌ No se encontraron profesores con ese nombre.");
        } else {
             for (Profesor p : profesores) {
                System.out.println("ID: " + p.getIdUsuario() + ", Nombre: " + p.getNombre() + ", Departamento: " + p.getDepartamento() + ", Cédula: " + p.getCedula());
            }
        }   
    }
public static void buscarPorCedula() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la cédula del profesor: ");
        String cedula = scanner.nextLine();

        Profesor profesor = new profesorDAO().buscarPorCedula(cedula);

        if (profesor == null) {
         System.out.println("❌ Profesor no encontrado.");
        } else {
            System.out.println("ID: " + profesor.getIdUsuario() + ", Nombre: " + profesor.getNombre() + ", Departamento: " + profesor.getDepartamento() + ", Cédula: " + profesor.getCedula());
        }
    }

}

