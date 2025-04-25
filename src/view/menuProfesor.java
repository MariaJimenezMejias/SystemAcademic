package view;

import Controller.ProfesorController;
import java.util.Scanner;

import Controller.PersonaController;
import Controller.UsuarioController;
import view.MenuAdmin;
public class menuProfesor {
    UsuarioController usuarioController = new UsuarioController();
    ProfesorController profesorController = new ProfesorController();
    MenuPersona menuPersona = new MenuPersona();
    MenuAdmin menuAdmin = new MenuAdmin();
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("MENU DE PROFESORES");
            System.out.println("1. Insertar profesor");
            System.out.println("2. Mostrar profesores");
            System.out.println("3. Eliminar profesor");
            System.out.println("4. Buscar profesor por cedula");
            System.out.println("5. Buscar profesor por nombre");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    menuPersona.mostrarMenu();
                    usuarioController.registrarUsuario();
                    profesorController.crearProfesor();
                    break;
                case 2:
                    ProfesorController.mostrarProfesores();
                    break;
                case 3:
                    ProfesorController.eliminarProfesor();
                    break;
                case 4:
                    ProfesorController.buscarProfesorPorCedula();
                    break;
                case 5:
                    ProfesorController.buscarProfesorPorNombre();
                    break;
                case 6:
                    System.out.println("Saliendo");
                    menuAdmin.menuAdmin();
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        } while (opcion != 0);
    }
}
