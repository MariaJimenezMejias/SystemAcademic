package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import DB.dbConnection;
import model.Matriculador;
import java.sql.ResultSet;

public class matriculadorDAO {

    public void insertarMatriculador(Matriculador matriculador) {
        String sql = "INSERT INTO Matriculador (idMatriculador, estado) VALUES (?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, matriculador.getIdUsuario());
            pstmt.setString(2, matriculador.getEstado());

            pstmt.executeUpdate();
            System.out.println("Matriculador insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar el matriculador: " + e.getMessage());
        }
    }
    
    public static boolean existeMatriculadorPorId(int idMatriculador) {
        String sql = "SELECT COUNT(*) FROM Matriculador WHERE idMatriculador = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idMatriculador);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error verificando matriculador: " + e.getMessage());
        }
        return false;
    }
}
