package Controller;

import model.Login;

public class LoginController {

    public static String tipoUsuario = "";      // admin, alumno, profesor, matriculador
    public static int idUsuario = -1;           // ID en tabla Usuario
    public static int idRelacionado = -1;       // ID en la tabla correspondiente (Alumno, Profesor, etc.)

    public static void autenticarUsuario(String correo, String clave) {
        if (Login.autenticarUsuario(correo, clave)) {
            System.out.println("✅ Autenticación exitosa. Tipo de usuario: " + tipoUsuario);
            menuUsuario();
        } else {
            System.out.println("❌ Error: Correo o clave incorrectos.");
        }
    }

    private static void menuUsuario() {
        System.out.println("🧭 Menú de " + tipoUsuario + " | ID Usuario: " + idUsuario + " | ID Relacionado: " + idRelacionado);

        // Aquí se llama el menú correspondiente (solo ejemplo con matriculador)
        if ("matriculador".equalsIgnoreCase(tipoUsuario)||"admin".equalsIgnoreCase(tipoUsuario)) {
            MatriculaController.matricularAlumno();
        }

        // Podés agregar más casos si tenés otros tipos:
        // if ("admin".equalsIgnoreCase(tipoUsuario)) {...}
    }
}
