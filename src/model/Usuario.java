/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author maria
 */
// File: src/model/Usuario.java

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DB.dbConnection;

public class Usuario {
    private int idPersona;
    private String clave;
    private String tipo;

    public Usuario(int idPersona, String clave, String tipo) {
        this.idPersona = idPersona;
        this.clave = clave;
        this.tipo = tipo;
    }

public int insertarUsuario() throws SQLException {
    String sqlUsuario = "INSERT INTO Usuario (idPersona, clave, tipo) VALUES (?, ?, ?)";
    try (Connection conn = dbConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sqlUsuario, PreparedStatement.RETURN_GENERATED_KEYS)) {

        pstmt.setInt(1, idPersona);
        pstmt.setString(2, clave);
        pstmt.setString(3, tipo);

        pstmt.executeUpdate();

        try (ResultSet rs = pstmt.getGeneratedKeys()) {
            if (rs.next()) {
                int idGenerado = rs.getInt(1);
                System.out.println("ID de usuario generado: " + idGenerado); // 👈 Aquí imprimís
                return idGenerado;
            } else {
                System.out.println("No se pudo obtener el ID generado.");
                return -1;
            }
        }
    }
}

}

