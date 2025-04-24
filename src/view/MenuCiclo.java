package view;

import Controller.CicloController;
import java.util.Scanner;

public class MenuCiclo {

    public static void menuCiclo() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Menú de Ciclos ===");
            System.out.println("1. Crear ciclo");
            System.out.println("2. Listar ciclos");
            System.out.println("3. Buscar ciclo por ID");
            System.out.println("4. Buscar ciclos por nombre de carrera");
            System.out.println("5. Actualizar ciclo");
            System.out.println("6. Eliminar ciclo");
            System.out.println("7. Volver");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    CicloController.crearCiclo();
                    break;
                case 2:
                    CicloController.listarCiclos();
                    break;
                case 3:
                    CicloController.obtenerCicloPorId();
                    break;
                case 4:
                    CicloController.listarCiclosPorCarrera();
                    break;
                case 5:
                    CicloController.actualizarCiclo();
                    break;
                case 6:
                    CicloController.eliminarCiclo();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("❌ Opción inválida.");
            }
        }
    }
}
