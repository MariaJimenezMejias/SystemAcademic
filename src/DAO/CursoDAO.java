/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author maria
 */

import model.Curso;
import DB.dbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    public static boolean insertarCurso(Curso curso) {
        String sql = "INSERT INTO Curso (nombre, creditos, horasSemanales, idCiclo, idCarrera) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, curso.getNombre());
            pstmt.setInt(2, curso.getCreditos());
            pstmt.setInt(3, curso.getHorasSemanales());
            pstmt.setInt(4, curso.getIdCiclo());
            pstmt.setInt(5, curso.getIdCarrera());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar curso: " + e.getMessage());
            return false;
        }
    }

    public static List<Curso> obtenerCursos() {
        String sql = "SELECT * FROM Curso";
        List<Curso> cursos = new ArrayList<>();
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                cursos.add(new Curso(
                    rs.getInt("idCurso"),
                    rs.getString("nombre"),
                    rs.getInt("creditos"),
                    rs.getInt("horasSemanales"),
                    rs.getInt("idCiclo"),
                    rs.getInt("idCarrera")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener cursos: " + e.getMessage());
        }
        return cursos;
    }

    public static Curso obtenerCursoPorId(int idCurso) {
        String sql = "SELECT * FROM Curso WHERE idCurso = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCurso);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Curso(
                    rs.getInt("idCurso"),
                    rs.getString("nombre"),
                    rs.getInt("creditos"),
                    rs.getInt("horasSemanales"),
                    rs.getInt("idCiclo"),
                    rs.getInt("idCarrera")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener curso por ID: " + e.getMessage());
        }
        return null;
    }

    public static boolean actualizarCurso(Curso curso) {
        String sql = "UPDATE Curso SET nombre = ?, creditos = ?, horasSemanales = ?, idCiclo = ?, idCarrera = ? WHERE idCurso = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, curso.getNombre());
            pstmt.setInt(2, curso.getCreditos());
            pstmt.setInt(3, curso.getHorasSemanales());
            pstmt.setInt(4, curso.getIdCiclo());
            pstmt.setInt(5, curso.getIdCarrera());
            pstmt.setInt(6, curso.getIdCurso());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar curso: " + e.getMessage());
            return false;
        }
    }

    public static boolean eliminarCurso(int idCurso) {
        String sql = "DELETE FROM Curso WHERE idCurso = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCurso);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar curso: " + e.getMessage());
            return false;
        }
    }

    public static List<Curso> buscarPorNombre(String nombre) {
        String sql = "SELECT * FROM Curso WHERE nombre LIKE ?";
        List<Curso> cursos = new ArrayList<>();
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + nombre + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                cursos.add(new Curso(
                    rs.getInt("idCurso"),
                    rs.getString("nombre"),
                    rs.getInt("creditos"),
                    rs.getInt("horasSemanales"),
                    rs.getInt("idCiclo"),
                    rs.getInt("idCarrera")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar por nombre: " + e.getMessage());
        }
        return cursos;
    }

    public static Curso buscarPorCodigo(String codigo) {
        String sql = "SELECT * FROM Curso WHERE idCurso = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(codigo));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Curso(
                    rs.getInt("idCurso"),
                    rs.getString("nombre"),
                    rs.getInt("creditos"),
                    rs.getInt("horasSemanales"),
                    rs.getInt("idCiclo"),
                    rs.getInt("idCarrera")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar por código: " + e.getMessage());
        }
        return null;
    }

    public static List<Curso> buscarPorCarrera(String nombreCarrera) {
        String sql = "SELECT c.* FROM Curso c JOIN Carrera ca ON c.idCarrera = ca.idCarrera WHERE ca.nombre LIKE ?";
        List<Curso> cursos = new ArrayList<>();
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + nombreCarrera + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                cursos.add(new Curso(
                    rs.getInt("idCurso"),
                    rs.getString("nombre"),
                    rs.getInt("creditos"),
                    rs.getInt("horasSemanales"),
                    rs.getInt("idCiclo"),
                    rs.getInt("idCarrera")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar por carrera: " + e.getMessage());
        }
        return cursos;
    }
}
