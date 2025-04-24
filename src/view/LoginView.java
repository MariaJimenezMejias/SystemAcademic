/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author maria
 */

import java.util.Scanner;
import Controller.LoginController;
public class LoginView {

    static Scanner scanner = new Scanner(System.in);

    // Método que muestra el menú de autenticación
    public static void mostrarMenuAutenticacion() {
        System.out.println("Ingrese su correo:");
        String correo = scanner.nextLine();

        System.out.println("Ingrese su clave:");
        String clave = scanner.nextLine();
        LoginController.autenticarUsuario(correo, clave);
    }
}
