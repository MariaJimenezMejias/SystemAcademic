/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import java.util.Scanner;
import Controller.CarreraController;

public class menuCarrera {

    private static Scanner scanner = new Scanner(System.in);

    public static void mostrarMenu() {
        while (true) {
            System.out.println("\n--- Menú de Gestión de Carreras ---");
            System.out.println("1. Crear nueva carrera");
            System.out.println("2. Listar todas las carreras");
            System.out.println("3. Obtener carrera por ID");
            System.out.println("4. Actualizar carrera");
            System.out.println("5. Eliminar carrera");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer del scanner

            switch (opcion) {
                case 1:
                    CarreraController.crearCarrera();
                    break;
                case 2:
                    CarreraController.listarCarreras();
                    break;
                case 3:
                    CarreraController.obtenerCarreraPorId();
                    break;
                case 4:
                    CarreraController.actualizarCarrera();
                    break;
                case 5:
                    CarreraController.eliminarCarrera();
                    break;
                case 6:
                    System.out.println("👋 ¡Hasta luego!");
                    scanner.close();  // Cerrar el scanner
                    return;
                default:
                    System.out.println("❌ Opción no válida. Intente nuevamente.");
            }
        }
    }
}
