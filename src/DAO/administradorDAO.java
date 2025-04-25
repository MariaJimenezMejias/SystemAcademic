package DAO;

import java.sql.*;
import java.time.LocalDate;
import DB.dbConnection;
import model.Administrador;

public class administradorDAO {
    public void insertarAdministrador(Administrador admin) {
        String sql = "INSERT INTO Administrador (idUsuario, fechaUltimoAcceso, rol) VALUES (?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, admin.getIdUsuario());
            pstmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            pstmt.setString(3, "Administrador");

            int filas = pstmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Administrador insertado correctamente.");
            } else {
                System.out.println("No se pudo insertar el administrador.");
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar administrador: " + e.getMessage());
        }
    }
    
    public static boolean existeAdminPorId(int idAdmin) {
        String sql = "SELECT COUNT(*) FROM Administrador WHERE idUsuario = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAdmin);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error verificando admin: " + e.getMessage());
        }
        return false;
    }
}
