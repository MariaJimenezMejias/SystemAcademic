package view;
import java.util.Scanner;
import Controller.CarreraController;

public class menuCarrera {
   CarreraController carreraController = new CarreraController();

    private  Scanner scanner = new Scanner(System.in);

    public  void mostrarMenu() {
        while (true) {
            System.out.println("\n--- Menu de Gestion de Carreras ---");
            System.out.println("1. Crear nueva carrera");
            System.out.println("2. Listar todas las carreras");
            System.out.println("3. Obtener carrera por Codigo");
            System.out.println("4. Listar carrera por nombre");
            System.out.println("5. Actualizar carrera");
            System.out.println("6. Eliminar carrera");
            System.out.println("7. Volver al menu principal");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opcion: ");
            
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
                    System.out.print("Ingrese el nombre de la carrera: ");
                    String nombreCarrera = scanner.nextLine();
                    carreraController.listarCarreraPorNombre(nombreCarrera);
                    break;
                case 5:
                    carreraController.actualizarCarrera();
                    break;
                case 6:
                    CarreraController.eliminarCarrera();
                    break;
                    
                case 7:
                    MenuAdmin.menuAdmin();
                break;
                case 8:
                    System.out.println("Hasta luego!");
                    scanner.close();  // Cerrar el scanner
                    return;
                default:
                    System.out.println("Opcion no valida. Intente nuevamente.");
            }
        }
    }
}
