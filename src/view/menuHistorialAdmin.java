package view;

import Controller.HistorialAdminController;
import java.util.Scanner;
import view.MenuAdmin;
public class menuHistorialAdmin {
    
    HistorialAdminController historialAdminController = new HistorialAdminController();

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("1. Consultar historial por ID");
            System.out.println("2. Volver");

            System.out.print("Seleccione una opcipn: ");
            int opcion = 0;

            // Manejo de errores para la entrada de opción
            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer
            } catch (Exception e) {
                System.out.println("Opcion invalida. Intente nuevamente.");
                scanner.nextLine(); // Limpiar el buffer en caso de entrada incorrecta
                continue;
            }

            switch (opcion) {
                case 1:
                    int id;
                    System.out.print("Ingrese el ID del alumno: ");
                    try {
                        id = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer
                        historialAdminController.buscarHistorialPorId(id);
                    } catch (Exception e) {
                        System.out.println("ID invalido. Intente nuevamente.");
                        scanner.nextLine(); // Limpiar el buffer en caso de entrada incorrecta
                    }
                    break;
                case 2:
                        MenuAdmin.menuAdmin();
                        break;
                default:
                    System.out.println("Opcion invalida.");
            }
        }
    }
}
