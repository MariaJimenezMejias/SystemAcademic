package view;

import Controller.CicloController;
import model.Nota;
import Controller.LoginController;
import java.util.Scanner;
import Controller.HistorialAdminController;
public class menuHistorialAdmin {


HistorialAdminController historialAdminController = new HistorialAdminController();
 
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true; 
             while (true) {
            System.out.println("1. Consultar historial por id");
            System.out.println("7. Volver");

            System.out.print("Seleccione una opcion: ");
            int opcion = scanner.nextInt();
        
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 2:
                    int id;
                     System.out.print("Ingrese el id del alumno");
                     id =  scanner.nextInt();
                    historialAdminController.buscarHistorialPorId(id);
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        }
    }
    }
