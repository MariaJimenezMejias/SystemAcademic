package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DB.dbConnection;
import Controller.LoginController;

public class Login {

    // Método para autenticar al usuario en la base de datos
    public static boolean autenticarUsuario(String correo, String clave) {
        String sqlPersona = "SELECT idPersona FROM Persona WHERE correo = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmtPersona = conn.prepareStatement(sqlPersona)) {

            pstmtPersona.setString(1, correo);
            ResultSet rsPersona = pstmtPersona.executeQuery();

            if (rsPersona.next()) {
                int idPersona = rsPersona.getInt("idPersona");

                // Consultamos idUsuario, clave y tipo
                return verificarUsuario(conn, idPersona, clave);

            } else {
                System.out.println("❌ Correo no encontrado en la base de datos.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al autenticar el usuario: " + e.getMessage());
            return false;
        }
    }

    // Método para verificar la clave del usuario
    private static boolean verificarUsuario(Connection conn, int idPersona, String clave) throws SQLException {
        String sqlUsuario = "SELECT idUsuario, clave, tipo FROM Usuario WHERE idPersona = ?";

        try (PreparedStatement pstmtUsuario = conn.prepareStatement(sqlUsuario)) {
            pstmtUsuario.setInt(1, idPersona);
            ResultSet rsUsuario = pstmtUsuario.executeQuery();

            if (rsUsuario.next()) {
                String claveGuardada = rsUsuario.getString("clave");

                // Aquí deberías usar un hash de la clave (bcrypt, etc.) para la comparación
                if (clave.equals(claveGuardada)) {
                    LoginController.tipoUsuario = rsUsuario.getString("tipo");
                    LoginController.idUsuario = rsUsuario.getInt("idUsuario");

                    // Recuperar el ID relacionado según el tipo de usuario
                    return obtenerIdRelacionado(conn, idPersona, LoginController.tipoUsuario);

                } else {
                    System.out.println("❌ Clave incorrecta.");
                    return false;
                }
            } else {
                System.out.println("❌ No se encontró el usuario asociado al correo.");
                return false;
            }
        }
    }

    // Método para recuperar el ID relacionado dependiendo del tipo de usuario
    private static boolean obtenerIdRelacionado(Connection conn, int idPersona, String tipoUsuario) throws SQLException {
        String tabla = "";
        String campo = "";

        // Selección de la tabla y campo según el tipo de usuario
        switch (tipoUsuario.toLowerCase()) {
            case "matriculador":
                tabla = "Matriculador";
                campo = "idMatriculador";
                break;
            case "profesor":
                tabla = "Profesor";
                campo = "idProfesor";
                break;
            case "alumno":
                tabla = "Alumno";
                campo = "idPersona"; // En este caso usamos idPersona directamente
                LoginController.idRelacionado = idPersona; // Para alumnos, idPersona es el mismo
                return true;
            case "admin":
                // Para admins, solo usamos el idUsuario ya que es específico para este tipo
                LoginController.idRelacionado = LoginController.idUsuario;
                return true;
            default:
                System.out.println("❌ Tipo de usuario no reconocido.");
                return false;
        }

        // Realizamos la consulta para obtener el ID correspondiente
        String sql = "SELECT " + campo + " FROM " + tabla + " WHERE idPersona = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPersona);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int idRelacionado = rs.getInt(campo);
                LoginController.idRelacionado = idRelacionado;
                System.out.println("✅ ID recuperado de " + tabla + ": " + idRelacionado);
                return true;
            } else {
                System.out.println("⚠️ No se encontró el ID relacionado en la tabla " + tabla);
                return false;
            }
        }
    }
}
