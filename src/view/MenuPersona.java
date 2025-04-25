package view;

import Controller.PersonaController;
import java.util.Scanner;

public class MenuPersona {

    private PersonaController personaController;

    // Constructor
    public MenuPersona() {
        personaController = new PersonaController();
    }

    // Metodo para mostrar el menu y registrar una persona
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== Menu de Registro de Persona =====");
        System.out.print("Ingrese la cedula: ");
        String cedula = scanner.nextLine();

        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el telefono: ");
        String telefono = scanner.nextLine();

        System.out.print("Ingrese la direccion: ");
        String direccion = scanner.nextLine();

        System.out.print("Ingrese el correo: ");
        String correo = scanner.nextLine();

        System.out.print("Ingrese el usuario: ");
        String usuario = scanner.nextLine();

        // Llamar al metodo registrarPersona de PersonaController para registrar la persona
        personaController.registrarPersona(cedula, nombre, telefono, direccion, correo, usuario);
    }
}
