/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author maria
 */

import DAO.CarreraDAO;
import model.Carrera;
import java.util.List;
import java.util.Scanner;

public class CarreraController {
    static Scanner scanner = new Scanner(System.in);

    // Método para crear una carrera
    public static void crearCarrera() {
        System.out.println("Ingrese el título de la carrera:");
        String titulo = scanner.nextLine();
        System.out.println("Ingrese el nombre de la carrera:");
        String nombreCarrera = scanner.nextLine();

        boolean resultado = CarreraDAO.insertarCarrera(titulo, nombreCarrera);

        if (resultado) {
            System.out.println("✅ Carrera creada correctamente.");
        } else {
            System.out.println("❌ Error al crear la carrera.");
        }
    }

    // Método para obtener todas las carreras
    public static void listarCarreras() {
        List<Carrera> carreras = CarreraDAO.obtenerCarreras();

        if (carreras.isEmpty()) {
            System.out.println("❌ No hay carreras registradas.");
        } else {
            System.out.println("Listado de Carreras:");
            for (Carrera carrera : carreras) {
                System.out.println("ID: " + carrera.getIdCarrera() + " - " + carrera.getNombreCarrera());
            }
        }
    }

    // Método para obtener una carrera por ID
    public static void obtenerCarreraPorId() {
        System.out.println("Ingrese el ID de la carrera:");
        int idCarrera = scanner.nextInt();

        Carrera carrera = CarreraDAO.obtenerCarreraPorId(idCarrera);

        if (carrera == null) {
            System.out.println("❌ Carrera no encontrada.");
        } else {
            System.out.println("Carrera encontrada: " + carrera.getTitulo() + " - " + carrera.getNombreCarrera());
        }
    }

    // Método para actualizar una carrera
    public static void actualizarCarrera() {
        System.out.println("Ingrese el ID de la carrera a actualizar:");
        int idCarrera = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        System.out.println("Ingrese el nuevo título de la carrera:");
        String titulo = scanner.nextLine();
        System.out.println("Ingrese el nuevo nombre de la carrera:");
        String nombreCarrera = scanner.nextLine();

        boolean resultado = CarreraDAO.actualizarCarrera(idCarrera, titulo, nombreCarrera);

        if (resultado) {
            System.out.println("✅ Carrera actualizada correctamente.");
        } else {
            System.out.println("❌ Error al actualizar la carrera.");
        }
    }

    // Método para eliminar una carrera
    public static void eliminarCarrera() {
        System.out.println("Ingrese el ID de la carrera a eliminar:");
        int idCarrera = scanner.nextInt();

        boolean resultado = CarreraDAO.eliminarCarrera(idCarrera);

        if (resultado) {
            System.out.println("✅ Carrera eliminada correctamente.");
        } else {
            System.out.println("❌ Error al eliminar la carrera.");
        }
    }
    public static void buscarCarreraPorNombre() {
        System.out.println("Ingrese el nombre de la carrera a buscar:");
        String nombre = scanner.nextLine();

        List<Carrera> carreras = CarreraDAO.buscarPorNombre(nombre);
    
        if (carreras.isEmpty()) {
            System.out.println("❌ No se encontraron carreras.");
        } else {
             System.out.println("Resultados:");
             for (Carrera carrera : carreras) {
             System.out.println("ID: " + carrera.getIdCarrera() + " - " + carrera.getNombreCarrera());
            }
        }
    }
    public static void administrarCursosPorCarrera() {
    System.out.println("Ingrese el ID de la carrera:");
    int idCarrera = scanner.nextInt();
    scanner.nextLine(); // Consumir salto de línea

    int opcion;
    do {
        System.out.println("\n1. Ver cursos");
        System.out.println("2. Agregar curso existente a la carrera");
        System.out.println("3. Quitar curso de la carrera");
        System.out.println("4. Salir");
        opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
                case 1:
                List<Curso> cursos = CursoDAO.buscarPorCarrera(idCarrera); // Debemos modificar ese método
                for (Curso c : cursos) {
                    System.out.println("- " + c.getNombre());
                }
                break;
                case 2:
                System.out.println("ID del curso a asociar:");
                int idCurso = scanner.nextInt();
                CursoDAO.asociarCursoACarrera(idCurso, idCarrera);
                break;
                case 3:
                System.out.println("ID del curso a quitar:");
                int idCursoQuitar = scanner.nextInt();
                CursoDAO.quitarCursoDeCarrera(idCursoQuitar);
                break;
            }

        } while (opcion != 4);
    }

}
