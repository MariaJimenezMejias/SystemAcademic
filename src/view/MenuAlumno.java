/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import Controller.LoginController;
/**
 *
 * @author maria
 */
import java.util.Scanner;
import Controller.HistorialAdminController;
public class MenuAlumno {

    private Scanner scanner;
    private Historial consultaHistorial;

    public MenuAlumno() {
        this.scanner = new Scanner(System.in);
        this.consultaHistorial = new Historial();
    }

    public void mostrarMenuAlumno() {
        boolean continuar = true;
 
        while (continuar) {
            System.out.println("=== Menu Alumno ===");
            System.out.println("1. Consultar Historial");
            System.out.println("2. Salir");

            System.out.print("Selecciona una opcion: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    int idAlumno = LoginController.idRelacionado;
                  HistorialAdminController historialAdminController = new HistorialAdminController();
                  historialAdminController.buscarHistorialPorId(idAlumno);
                    break;

                case 2:
                    System.out.println("Saliendo del menu...");
                    continuar = false;
                    break;

                default:
                    System.out.println("Opcion no valida, por favor elige nuevamente.");
                    break;
            }
        }
    }
}
