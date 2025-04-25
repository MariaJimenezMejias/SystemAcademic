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
        
        // Verifica que los valores no sean nulos antes de establecer los parametros
        if (curso.getNombre() == null || curso.getCreditos() == 0 || curso.getHorasSemanales() == 0 || curso.getIdCiclo() == 0) {
            System.out.println("Error: Uno de los valores es invalido.");
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
public static List<Curso> obtenerCursosPorCarrera(String nombreCarrera) {
    String sql = "SELECT c.idCurso, c.nombre, c.creditos, c.horasSemanales " +
                 "FROM Curso c " +
                 "JOIN Ciclo ci ON c.idCiclo = ci.idCiclo " +
                 "JOIN Carrera ca ON ci.idCarrera = ca.idCarrera " +
                 "WHERE LOWER(ca.nombreCarrera) = LOWER(?)";  // Usamos LOWER() para hacer la comparacion insensible a mayusculas

    List<Curso> cursos = new ArrayList<>();
    try (Connection conn = dbConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        // Establece el valor del parametro en la consulta (nombreCarrera)
        pstmt.setString(1, nombreCarrera.toLowerCase());  // Convertimos el nombre de la carrera a minusculas

        // Ejecuta la consulta y procesa los resultados
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Curso curso = new Curso(
                    rs.getInt("idCurso"),            // idCurso de la tabla Curso
                    rs.getString("nombre"),          // nombre del curso
                    rs.getInt("creditos"),           // creditos del curso
                    rs.getInt("horasSemanales"),     // horasSemanales del curso
                    -1  // Como no estamos utilizando el idCiclo aqui, pasamos un valor negativo
                );
                cursos.add(curso);
            }
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener cursos por carrera: " + e.getMessage());
    }
    return cursos;
}

public static List<Curso> obtenerCursosPorNombre(String nombreCurso) {
    String sql = "SELECT c.idCurso, c.nombre, c.creditos, c.horasSemanales " +
                 "FROM Curso c " +
                 "WHERE LOWER(c.nombre) LIKE LOWER(?)";  // Usamos LOWER() para hacer la comparacion insensible a mayusculas

    List<Curso> cursos = new ArrayList<>();
    try (Connection conn = dbConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        // Establece el valor del parametro en la consulta (nombreCurso)
        pstmt.setString(1, "%" + nombreCurso.toLowerCase() + "%");  // El signo '%' permite la busqueda parcial (por nombre similar)

        // Ejecuta la consulta y procesa los resultados
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Curso curso = new Curso(
                    rs.getInt("idCurso"),            // idCurso de la tabla Curso
                    rs.getString("nombre"),          // nombre del curso
                    rs.getInt("creditos"),           // creditos del curso
                    rs.getInt("horasSemanales"),     // horasSemanales del curso
                    -1  // No se necesita idCiclo en este caso
                );
                cursos.add(curso);
            }
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener cursos por nombre: " + e.getMessage());
    }
    return cursos;
}



}
