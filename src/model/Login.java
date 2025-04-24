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
        try (Connection conn = dbConnection.getConnection()) {
            return verificarUsuario(conn, correo, clave);
        } catch (SQLException e) {
            System.out.println("Error al autenticar el usuario: " + e.getMessage());
            return false;
        }
    }

    // Método para verificar el usuario y clave usando el correo
    private static boolean verificarUsuario(Connection conn, String correo, String clave) throws SQLException {
        String sql = "SELECT u.idUsuario, u.clave, u.tipo, u.idPersona " +
                     "FROM Usuario u " +
                     "JOIN Persona p ON u.idPersona = p.idPersona " +
                     "WHERE p.correo = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, correo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String claveGuardada = rs.getString("clave");

                // Imprimir para depuración
                System.out.println("Correo encontrado: " + correo);
                System.out.println("Clave proporcionada: " + clave);
                System.out.println("Clave guardada: " + claveGuardada);

                if (clave.equals(claveGuardada)) {
                    // Asignamos tipo, idUsuario y procesamos idPersona si es alumno o profesor
                    LoginController.tipoUsuario = rs.getString("tipo").trim().toLowerCase();
                    LoginController.idUsuario = rs.getInt("idUsuario");
                    int idPersona = rs.getInt("idPersona");

                    // Imprimir tipo de usuario para depuración
                    System.out.println("Tipo de usuario: " + LoginController.tipoUsuario);

                    // Si el tipo es "alumno", asignamos idPersona a idRelacionado
                    if (LoginController.tipoUsuario.equals("alumno")) {
                        LoginController.idRelacionado = idPersona;
                        System.out.println("ID relacionado asignado al alumno: " + idPersona);
                    }

                    // Si el tipo es "profesor", asignamos idUsuario a idRelacionado
                    if (LoginController.tipoUsuario.equals("profesor")) {
                        LoginController.idRelacionado = LoginController.idUsuario;
                        System.out.println("ID relacionado asignado al profesor: " + LoginController.idUsuario);
                    }

                    return true;
                } else {
                    System.out.println("Clave incorrecta.");
                    return false;
                }
            } else {
                System.out.println("No se encontró un usuario con ese correo.");
                return false;
            }
        }
    }
}
