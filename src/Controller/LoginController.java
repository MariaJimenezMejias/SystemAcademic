package Controller;

import view.MenuAdmin;
import java.util.Scanner;
import model.Login;
import view.MenuNotas;
import view.MenuAlumno;
public class LoginController {
    static Scanner scanner = new Scanner(System.in);

    public static String tipoUsuario = "";      // admin, alumno, profesor, matriculador
    public static int idUsuario = -1;           // ID en tabla Usuario
    public static int idRelacionado = -1;       // ID en la tabla correspondiente (Alumno, Profesor, etc.)

    // Método para autenticar al usuario
    public static void autenticarUsuario(String correo, String clave) {
        // Bucle para seguir pidiendo el correo y la clave hasta que la autenticación sea exitosa
        while (true) {
            if (correo == null || clave == null || correo.isEmpty() || clave.isEmpty()) {
                System.out.println("️Debe ingresar correo y clave.");
                return;
            }

            // Intentamos autenticar al usuario
            if (Login.autenticarUsuario(correo, clave)) {
                System.out.println("Exito. Tipo de usuario: " + tipoUsuario);  // Verifica que tipoUsuario se haya asignado correctamente
                menuUsuario();  // Mostrar el menú del usuario
                break;  // Salir del bucle si la autenticación es exitosa
            } else {
                System.out.println("Error: Correo o clave incorrectos.");
                System.out.println("Por favor, ingresa nuevamente tus credenciales.");

                // Pedir el correo y la clave nuevamente
                System.out.print("Ingrese su correo: ");
                correo = scanner.nextLine();
                System.out.print("Ingrese su clave: ");
                clave = scanner.nextLine();
            }
        }
    }

    // Método para mostrar el menú del usuario
    private static void menuUsuario() {
        int opcion;
        // Aseguramos que tipoUsuario esté limpio de espacios y en minúsculas
        tipoUsuario = tipoUsuario.trim().toLowerCase();

        System.out.println("🧭 Menú de " + tipoUsuario + 
                           " | ID Usuario: " + idUsuario + 
                           " | ID Relacionado: " + idRelacionado);

        // Verificamos el tipo de usuario
        switch (tipoUsuario) {
            case "matriculador":
                System.out.println("...........................Mostrando menu de matriculador............................");
                MatriculaController matricula = new MatriculaController();
                matricula.matricularAlumno();
                break;

            // Agrupamos admin y superadmin en el mismo bloque
            case "admin":
            case "superadmin":
                System.out.println("Mostrando menu de admin/superadmin...");
                System.out.println("..........................Seleccione una opcion................................");
                MenuAdmin.menuAdmin();
                break;

            case "profesor":
                System.out.println("............................................Mostrando menu de profesor................");
                MenuNotas menuNotas = new MenuNotas();
                menuNotas.mostrarMenu();
                break;

            case "alumno":
                System.out.println("Mostrando menu de alumno...");
                MenuAlumno menuAlumno = new MenuAlumno();
                menuAlumno.mostrarMenuAlumno();
                break;

            default:
                System.out.println("Tipo de usuario no reconocido.");
        }
    }
}
