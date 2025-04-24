/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.Scanner;
import Controller.UsuarioController;

public class MenuUsuario {

    // Método para mostrar el menú y manejar las opciones
    public void mostrarMenu(Scanner scanner, UsuarioController usuarioController) {
        int opcion;

        do {
            System.out.println("\n--- Menú de Búsqueda de Profesores ---");
            System.out.println("1. Buscar por Nombre");
            System.out.println("2. Buscar por Cédula");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    // Buscar por nombre
                    System.out.print("Ingresa el nombre del profesor a buscar: ");
                    String nombreBuscado = scanner.nextLine();
                    usuarioController.buscarProfesorPorNombre(nombreBuscado);
                    break;

                case 2:
                    // Buscar por cédula
                    System.out.print("Ingresa la cédula del profesor a buscar: ");
                    String cedulaBuscada = scanner.nextLine();
                    usuarioController.buscarProfesorPorCedula(cedulaBuscada);
                    break;

                case 3:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 3);
    }
}
