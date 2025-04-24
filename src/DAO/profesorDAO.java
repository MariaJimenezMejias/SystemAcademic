package DAO;

import model.Profesor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import DB.dbConnection;
import java.sql.ResultSet;


import model.Profesor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import DB.dbConnection;
import java.util.ArrayList;
import java.util.List;
import model.Usuario1;

public class profesorDAO {

    public void insertarProfesor(Profesor profesor) {
        String sql = "INSERT INTO Profesor (idProfesor, departamento, cedula) VALUES (?, ?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, profesor.getIdUsuario());
            pstmt.setString(2, profesor.getDepartamento());
            pstmt.setString(3, profesor.getCedula());

            pstmt.executeUpdate();
            System.out.println("✅ Profesor insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al insertar el profesor: " + e.getMessage());
        }
    }



    public void eliminarProfesor(int idProfesor) {
        String sql = "DELETE FROM Profesor WHERE idProfesor = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idProfesor);
            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("🗑️ Profesor eliminado correctamente.");
            } else {
                System.out.println("⚠️ No se encontró el profesor con ese ID.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar profesor: " + e.getMessage());
        }
    }
    
    public int obtenerIdProfesorPorCedula(String cedula) {
    String sql = "SELECT idProfesor FROM Profesor WHERE cedula = ?";
    try (Connection conn = dbConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, cedula);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            return rs.getInt("idProfesor");
        }
    } catch (SQLException e) {
        System.out.println("❌ Error al obtener idProfesor: " + e.getMessage());
    }
    return -1; // No encontrado
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

public List<Usuario1> listarProfesores() {
    List<Usuario1> lista = new ArrayList<>();

    String sql = "SELECT " +
                 "    p.idPersona, " +
                 "    p.nombre AS NombreProfesor, " +
                 "    p.cedula, " +
                 "    p.correo, " +
                 "    p.telefono, " +
                 "    pr.departamento " +
                 "FROM Profesor pr " +
                 "JOIN Usuario u ON pr.idProfesor = u.idUsuario " +
                 "JOIN Persona p ON u.idPersona = p.idPersona";

    try (Connection con = dbConnection.getConnection();  // Corrección aquí
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
           Usuario1 usuario = new Usuario1(
    rs.getInt("idPersona"),
    rs.getString("NombreProfesor"),
    rs.getString("cedula"),
    rs.getString("correo"),
    rs.getString("telefono"),
    rs.getString("departamento")
);
            lista.add(usuario);
        }

    } catch (Exception e) {
        System.out.println("❌ Error al listar profesores: " + e.getMessage());
    }

    return lista;
}


}

