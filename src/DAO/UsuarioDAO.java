package DAO;



import model.Usuario1;
import DB.dbConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // Método para buscar profesores por nombre
    public List<Usuario1> buscarProfesorPorNombre(String nombre) throws SQLException {
        List<Usuario1> profesores = new ArrayList<>();
        String sql = "SELECT p.idPersona, p.nombre AS NombreProfesor, p.cedula, p.correo, p.telefono, pr.departamento "
                   + "FROM Persona p "
                   + "JOIN Usuario u ON p.idPersona = u.idPersona "
                   + "JOIN Profesor pr ON u.idUsuario = pr.idProfesor "
                   + "WHERE p.nombre LIKE ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nombre + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario1 usuario = new Usuario1(
                    rs.getInt("idPersona"),
                    rs.getString("NombreProfesor"),
                    rs.getString("cedula"),
                    rs.getString("correo"),
                    rs.getString("telefono"),
                    rs.getString("departamento")
                );
                profesores.add(usuario);
            }
        }

        return profesores;
    }

    // Método para buscar profesores por cédula
    public List<Usuario1> buscarProfesorPorCedula(String cedula) throws SQLException {
        List<Usuario1> profesores = new ArrayList<>();
        String sql = "SELECT p.idPersona, p.nombre AS NombreProfesor, p.cedula, p.correo, p.telefono, pr.departamento "
                   + "FROM Persona p "
                   + "JOIN Usuario u ON p.idPersona = u.idPersona "
                   + "JOIN Profesor pr ON u.idUsuario = pr.idProfesor "
                   + "WHERE p.cedula = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cedula);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario1 usuario = new Usuario1(
                    rs.getInt("idPersona"),
                    rs.getString("NombreProfesor"),
                    rs.getString("cedula"),
                    rs.getString("correo"),
                    rs.getString("telefono"),
                    rs.getString("departamento")
                );
                profesores.add(usuario);
            }
        }

        return profesores;
    }
}
