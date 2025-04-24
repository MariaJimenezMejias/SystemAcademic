package view;

import DB.dbConnection;
import java.sql.*;

public class Historial {

    public void mostrarHistorial(int idAlumno) {
        try (Connection conn = dbConnection.getConnection()) {
            if (conn == null) {
                System.out.println("No se pudo establecer la conexión.");
                return;
            }

            String consulta = "SELECT " +
                    "p.nombre AS NombreAlumno, " +
                    "p.cedula AS Cedula, " +
                    "c.nombreCarrera, " +
                    "g.numeroGrupo, " +
                    "cu.nombre AS NombreCurso, " +
                    "cu.creditos, " +
                    "n.nota AS Nota, " +
                    "n.fechaRegistro AS FechaNota " +
                    "FROM Notas n " +
                    "JOIN Alumno a ON n.idAlumno = a.idPersona " +
                    "JOIN Persona p ON a.idPersona = p.idPersona " +
                    "JOIN MatriculaCarrera mc ON mc.idAlumno = a.idPersona " +
                    "JOIN Carrera c ON mc.idCarrera = c.idCarrera " +
                    "JOIN Grupo g ON n.idGrupo = g.idGrupo " +
                    "JOIN Curso cu ON g.idCurso = cu.idCurso " +
                    "WHERE a.idPersona = ?";

            PreparedStatement stmt = conn.prepareStatement(consulta);
            stmt.setInt(1, idAlumno);

            ResultSet rs = stmt.executeQuery();

            System.out.println("=== Historial Académico del Alumno ===");

            while (rs.next()) {
                System.out.println("Nombre: " + rs.getString("NombreAlumno"));
                System.out.println("Cédula: " + rs.getString("Cedula"));
                System.out.println("Carrera: " + rs.getString("nombreCarrera"));
                System.out.println("Grupo: " + rs.getInt("numeroGrupo"));
                System.out.println("Curso: " + rs.getString("NombreCurso"));
                System.out.println("Créditos: " + rs.getInt("creditos"));
                System.out.println("Nota: " + rs.getFloat("Nota"));
                System.out.println("Fecha: " + rs.getDate("FechaNota"));
                System.out.println("---------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener historial: " + e.getMessage());
        }
    }
}
