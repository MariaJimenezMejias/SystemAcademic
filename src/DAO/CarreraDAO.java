/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author maria
 */

import model.Carrera;
import DB.dbConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarreraDAO {

    // Método para insertar una nueva carrera
    public static boolean insertarCarrera(String titulo, String nombreCarrera) {
        String sql = "INSERT INTO Carrera (titulo, nombreCarrera) VALUES (?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, titulo);
            pstmt.setString(2, nombreCarrera);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar la carrera: " + e.getMessage());
        }
        return false;
    }

    // Método para obtener una lista de todas las carreras
    public static List<Carrera> obtenerCarreras() {
        String sql = "SELECT * FROM Carrera";
        List<Carrera> carreras = new ArrayList<>();

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int idCarrera = rs.getInt("idCarrera");
                String titulo = rs.getString("titulo");
                String nombreCarrera = rs.getString("nombreCarrera");

                carreras.add(new Carrera(idCarrera, titulo, nombreCarrera));
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener las carreras: " + e.getMessage());
        }
        return carreras;
    }

    // Método para obtener una carrera por ID
    public static Carrera obtenerCarreraPorId(int idCarrera) {
        String sql = "SELECT * FROM Carrera WHERE idCarrera = ?";
        Carrera carrera = null;

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idCarrera);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String titulo = rs.getString("titulo");
                String nombreCarrera = rs.getString("nombreCarrera");

                carrera = new Carrera(idCarrera, titulo, nombreCarrera);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener la carrera: " + e.getMessage());
        }
        return carrera;
    }

    // Método para actualizar una carrera
    public static boolean actualizarCarrera(int idCarrera, String titulo, String nombreCarrera) {
        String sql = "UPDATE Carrera SET titulo = ?, nombreCarrera = ? WHERE idCarrera = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, titulo);
            pstmt.setString(2, nombreCarrera);
            pstmt.setInt(3, idCarrera);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar la carrera: " + e.getMessage());
        }
        return false;
    }

    // Método para eliminar una carrera
    public static boolean eliminarCarrera(int idCarrera) {
        String sql = "DELETE FROM Carrera WHERE idCarrera = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idCarrera);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar la carrera: " + e.getMessage());
        }
        return false;
    }
}