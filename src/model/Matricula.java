/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author maria
 */

import java.sql.*;
import DB.dbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import DB.dbConnection;
;

public class Matricula {
    // Método para insertar una matrícula en la tabla MatriculaCarrera
    public static void insertarMatricula(int idAlumno, int idCarrera) {
        // SQL para insertar en la tabla MatriculaCarrera
        String sql = "INSERT INTO MatriculaCarrera (idAlumno, idCarrera, fechaMatricula) VALUES (?, ?, GETDATE())";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idAlumno);
            pstmt.setInt(2, idCarrera);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Matrícula registrada correctamente.");
            } else {
                System.out.println("❌ No se pudo registrar la matrícula.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar la matrícula: " + e.getMessage());
        }
    }
}

