package DAO;

import DB.dbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import model.Alumno;

public class alumnoDAO {

    public boolean insertarAlumno(Alumno alumno) {
        String sql = "INSERT INTO Alumno (idPersona, fechaNacimiento, fechaRegistro) VALUES (?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, alumno.getIdPersona());
            pstmt.setDate(2, alumno.getFechaNacimiento());
            pstmt.setDate(3, alumno.getFechaRegistro());

            pstmt.executeUpdate();
            System.out.println("Alumno insertado correctamente.");
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar el alumno: " + e.getMessage());
            return false;
        }
    }

    public static int obtenerIdAlumnoPorCedula(String cedula) {
        String sql = "SELECT a.idPersona FROM Persona p " +
                     "JOIN Alumno a ON p.idPersona = a.idPersona " +
                     "WHERE p.cedula = ?";
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cedula);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("idPersona");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el ID del alumno: " + e.getMessage());
            return -1;
        }
    }

    public ResultSet buscarPorNombre(String nombre) {
        String sql = "SELECT p.idPersona, p.nombre AS NombreAlumno, p.cedula, p.correo, p.telefono, " +
                     "c.nombreCarrera AS NombreCarrera, c.idCarrera AS CodigoCarrera " +
                     "FROM Persona p " +
                     "JOIN Alumno a ON p.idPersona = a.idPersona " +
                     "JOIN MatriculaCarrera mc ON a.idPersona = mc.idAlumno " +
                     "JOIN Carrera c ON mc.idCarrera = c.idCarrera " +
                     "WHERE p.nombre LIKE ?";
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + nombre + "%");
            return pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error al buscar por nombre: " + e.getMessage());
            return null;
        }
    }

    public ResultSet buscarPorCedula(String cedula) {
        String sql = "SELECT p.idPersona, p.nombre AS NombreAlumno, p.cedula, p.correo, p.telefono, " +
                     "c.nombreCarrera AS NombreCarrera, c.idCarrera AS CodigoCarrera " +
                     "FROM Persona p " +
                     "JOIN Alumno a ON p.idPersona = a.idPersona " +
                     "JOIN MatriculaCarrera mc ON a.idPersona = mc.idAlumno " +
                     "JOIN Carrera c ON mc.idCarrera = c.idCarrera " +
                     "WHERE p.cedula = ?";
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cedula);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error al buscar por cedula: " + e.getMessage());
            return null;
        }
    }

    public ResultSet buscarPorCarrera(String carrera) {
        String sql = "SELECT p.idPersona, p.nombre AS NombreAlumno, p.cedula, p.correo, p.telefono, " +
                     "c.nombreCarrera AS NombreCarrera, c.idCarrera AS CodigoCarrera " +
                     "FROM Persona p " +
                     "JOIN Alumno a ON p.idPersona = a.idPersona " +
                     "JOIN MatriculaCarrera mc ON a.idPersona = mc.idAlumno " +
                     "JOIN Carrera c ON mc.idCarrera = c.idCarrera " +
                     "WHERE c.nombreCarrera LIKE ?";
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + carrera + "%");
            return pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error al buscar por carrera: " + e.getMessage());
            return null;
        }
    }
}
