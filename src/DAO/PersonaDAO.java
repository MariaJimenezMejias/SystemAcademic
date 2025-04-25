package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Persona;
import DB.dbConnection;
import Controller.LoginController;
public class PersonaDAO {
    private static int idUsuarioGuardar;

    public static int getIdUsuarioGuardado() {
        return idUsuarioGuardar;
    }

    public void insertarPersona(Persona persona) {
        String sql = "INSERT INTO Persona (cedula, nombre, telefono, direccion, correo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, persona.getCedula());
            stmt.setString(2, persona.getNombre());
            stmt.setString(3, persona.getTelefono());
            stmt.setString(4, persona.getDireccion());
            stmt.setString(5, persona.getCorreo());

            int filasInsertadas = stmt.executeUpdate();

            if (filasInsertadas > 0) {
                try (var rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idUsuario = rs.getInt(1);
                        persona.setIdUsuario(idUsuario);
                        idUsuarioGuardar = idUsuario;
                        System.out.println("Se ha registrado a la persona con el ID de usuario: " + persona.getIdUsuario());
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al insertar persona: " + e.getMessage());
        }
    }
}
