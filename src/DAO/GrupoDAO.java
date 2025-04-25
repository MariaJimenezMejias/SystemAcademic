package DAO;

import DB.dbConnection;
import model.Grupo;
import java.sql.*;
import java.util.ArrayList;

public class GrupoDAO {

    public void insertarGrupo(Grupo grupo) {
        String sql = "INSERT INTO Grupo (numeroGrupo, horario, idProfesor, idCiclo, idCarrera, idCurso) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, grupo.getNumeroGrupo());
            pstmt.setString(2, grupo.getHorario());
            pstmt.setInt(3, grupo.getIdProfesor());
            pstmt.setInt(4, grupo.getIdCiclo());
            pstmt.setInt(5, grupo.getIdCarrera());
            pstmt.setInt(6, grupo.getIdCurso());

            pstmt.executeUpdate();
            System.out.println("Grupo insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar el grupo: " + e.getMessage());
        }
    }

    public ArrayList<Grupo> listarGrupos() {
        ArrayList<Grupo> lista = new ArrayList<>();
        String sql = "SELECT * FROM Grupo";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Grupo grupo = new Grupo();
                grupo.setIdGrupo(rs.getInt("idGrupo"));
                grupo.setNumeroGrupo(rs.getInt("numeroGrupo"));
                grupo.setHorario(rs.getString("horario"));
                grupo.setIdProfesor(rs.getInt("idProfesor"));
                grupo.setIdCiclo(rs.getInt("idCiclo"));
                lista.add(grupo);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar grupos: " + e.getMessage());
        }

        return lista;
    }
}
