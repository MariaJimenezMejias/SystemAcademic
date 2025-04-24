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
import java.sql.*;
import java.util.*;

public class CursoDAO {
    public static boolean insertarCurso(Curso curso) {
    String sql = "INSERT INTO Curso (nombre, creditos, horasSemanales, idCiclo) VALUES (?, ?, ?, ?)";
    try (Connection conn = dbConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        // Verifica que los valores no sean nulos antes de establecer los parámetros
        if (curso.getNombre() == null || curso.getCreditos() == 0 || curso.getHorasSemanales() == 0 || curso.getIdCiclo() == 0) {
            System.out.println("Error: Uno de los valores es inválido.");
            return false;
        }
        
        pstmt.setString(1, curso.getNombre());
        pstmt.setInt(2, curso.getCreditos());
        pstmt.setInt(3, curso.getHorasSemanales());
        pstmt.setInt(4, curso.getIdCiclo());
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
                Curso curso = new Curso(
                    rs.getInt("idCurso"),
                    rs.getString("nombre"),
                    rs.getInt("creditos"),
                    rs.getInt("horasSemanales"),
                    rs.getInt("idCiclo")
                );
                cursos.add(curso);
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
                    rs.getInt("idCiclo")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener curso por ID: " + e.getMessage());
        }
        return null;
    }

    public static boolean actualizarCurso(Curso curso) {
        String sql = "UPDATE Curso SET nombre = ?, creditos = ?, horasSemanales = ?, idCiclo = ? WHERE idCurso = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, curso.getNombre());
            pstmt.setInt(2, curso.getCreditos());
            pstmt.setInt(3, curso.getHorasSemanales());
            pstmt.setInt(4, curso.getIdCiclo());
            pstmt.setInt(5, curso.getIdCurso());
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
}
