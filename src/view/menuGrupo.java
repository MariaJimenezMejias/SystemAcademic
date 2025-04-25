/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author maria
 */

import Controller.GrupoController;
import java.util.Scanner;

public class menuGrupo {
    public static void menuGrupo() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Menu e Grupos ===");
            System.out.println("1. Crear grupo");
            System.out.println("2. Listar grupos");
            System.out.println("3. Volver");
            System.out.print("Seleccione una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    GrupoController.crearGrupo();
                    break;
                case 2:
                       GrupoController.listarGrupos();
                    break;
                case 3:
                    return;
                default:
                    System.out.println(" Opcion invalida.");
            }
        }
    }
}
