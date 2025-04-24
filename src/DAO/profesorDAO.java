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

    public List<Profesor> listarProfesores() {
        List<Profesor> lista = new ArrayList<>();
        String sql = """
            SELECT p.idProfesor, u.nombre, p.departamento, p.cedula
            FROM Profesor p
            JOIN Usuario u ON p.idProfesor = u.idUsuario
        """;

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Profesor profesor = new Profesor();
                profesor.setIdUsuario(rs.getInt("idProfesor"));
                profesor.setNombre(rs.getString("nombre"));
                profesor.setDepartamento(rs.getString("departamento"));
                profesor.setCedula(rs.getString("cedula"));
                lista.add(profesor);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar profesores: " + e.getMessage());
        }

        return lista;
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
    public List<Profesor> buscarPorNombre(String nombre) {
        List<Profesor> lista = new ArrayList<>();
        String sql = """
            SELECT p.idProfesor, u.nombre, p.departamento, p.cedula
          FROM Profesor p
          JOIN Usuario u ON p.idProfesor = u.idUsuario
          WHERE u.nombre LIKE ?
      """;

     try (Connection conn = dbConnection.getConnection();
          PreparedStatement pstmt = conn.prepareStatement(sql)) {
         
         pstmt.setString(1, "%" + nombre + "%");
          ResultSet rs = pstmt.executeQuery();
          
          while (rs.next()) {
             Profesor profesor = new Profesor();
             profesor.setIdUsuario(rs.getInt("idProfesor"));
              profesor.setNombre(rs.getString("nombre"));
             profesor.setDepartamento(rs.getString("departamento"));
             profesor.setCedula(rs.getString("cedula"));
             lista.add(profesor);
            }     
          
        } catch (SQLException e) {
         System.out.println("❌ Error al buscar profesor por nombre: " + e.getMessage());
        }

        return lista;
    }
    public Profesor buscarPorCedula(String cedula) {
     String sql = """
        SELECT p.idProfesor, u.nombre, p.departamento, p.cedula
        FROM Profesor p
        JOIN Usuario u ON p.idProfesor = u.idUsuario
        WHERE p.cedula = ?
     """;

     try (Connection conn = dbConnection.getConnection();
          PreparedStatement pstmt = conn.prepareStatement(sql)) {

         pstmt.setString(1, cedula);
         ResultSet rs = pstmt.executeQuery();

         if (rs.next()) {
              Profesor profesor = new Profesor();
             profesor.setIdUsuario(rs.getInt("idProfesor"));
             profesor.setNombre(rs.getString("nombre"));
             profesor.setDepartamento(rs.getString("departamento"));
              profesor.setCedula(rs.getString("cedula"));
              return profesor;
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar profesor por cédula: " + e.getMessage());
        }

     return null;
    }

}

