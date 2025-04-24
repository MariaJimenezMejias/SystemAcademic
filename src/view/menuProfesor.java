/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author maria
 */
import Controller.ProfesorController;
import java.util.Scanner;

public class menuProfesor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("MENU DE PROFESORES");
            System.out.println("1. Insertar profesor");
            System.out.println("2. Mostrar profesores");
            System.out.println("3. Eliminar profesor");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> ProfesorController.crearProfesor();
                case 2 -> ProfesorController.mostrarProfesores();
                case 3 -> ProfesorController.eliminarProfesor();
                case 0 -> System.out.println("👋 Saliendo del menú de profesores...");
                default -> System.out.println("❌ Opción inválida.");
            }
        } while (opcion != 0);
    }
}
