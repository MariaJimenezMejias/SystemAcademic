package DAO;

import java.sql.*;
import DB.dbConnection; // Importar la clase dbConnection

public class HistorialAdminDAO {

    // Metodo para consultar el historial del alumno por su nombre
    public void consultarHistorialAdmin(String nombreAlumno) {
        String sql = "SELECT " +
            "alumno_p.nombre AS NombreAlumno, " +
            "alumno_p.cedula AS Cedula, " +
            "c.nombreCarrera, " +
            "cu.nombre AS Curso, " +
            "cu.creditos, " +
            "g.numeroGrupo, " +
            "g.horario, " +
            "ci.numero AS CicloNumero, " +
            "ci.anio AS Anio, " +
            "n.nota AS Nota, " +
            "n.fechaRegistro AS FechaNota " +
            "FROM Notas n " +
            "LEFT JOIN Alumno a ON n.idAlumno = a.idPersona " +
            "LEFT JOIN Persona alumno_p ON a.idPersona = alumno_p.idPersona " +
            "LEFT JOIN MatriculaCarrera mc ON mc.idAlumno = a.idPersona " +
            "LEFT JOIN Carrera c ON mc.idCarrera = c.idCarrera " +
            "LEFT JOIN Alumno_Grupo ag ON ag.idAlumno = a.idPersona " +
            "LEFT JOIN Grupo g ON ag.idGrupo = g.idGrupo " +
            "LEFT JOIN Curso cu ON g.idCurso = cu.idCurso " +
            "LEFT JOIN Ciclo ci ON cu.idCiclo = ci.idCiclo " +
            "WHERE alumno_p.nombre = ? " +
            "ORDER BY ci.anio, ci.numero;";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombreAlumno);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Nombre del Alumno: " + rs.getString("NombreAlumno"));
                System.out.println("Cedula: " + rs.getString("Cedula"));
                System.out.println("Carrera: " + rs.getString("nombreCarrera"));
                System.out.println("Curso: " + rs.getString("Curso"));
                System.out.println("Creditos: " + rs.getInt("creditos"));
                System.out.println("Numero de Grupo: " + rs.getInt("numeroGrupo"));
                System.out.println("Horario: " + rs.getString("horario"));
                System.out.println("Ciclo Numero: " + rs.getInt("CicloNumero"));
                System.out.println("Anio: " + rs.getInt("Anio"));
                System.out.println("Nota: " + rs.getFloat("Nota"));
                System.out.println("Fecha de Registro: " + rs.getDate("FechaNota"));
                System.out.println("---------------------------------------------");
            }

        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
    }

    // Metodo para consultar el historial del alumno por su ID
    public void consultarHistorialAdminPorId(int idPersona) {
        String sql = "SELECT " +
            "alumno_p.nombre AS NombreAlumno, " +
            "alumno_p.cedula AS Cedula, " +
            "c.nombreCarrera, " +
            "cu.nombre AS Curso, " +
            "cu.creditos, " +
            "g.numeroGrupo, " +
            "g.horario, " +
            "ci.numero AS CicloNumero, " +
            "ci.anio AS Anio, " +
            "n.nota AS Nota, " +
            "n.fechaRegistro AS FechaNota " +
            "FROM Notas n " +
            "LEFT JOIN Alumno a ON n.idAlumno = a.idPersona " +
            "LEFT JOIN Persona alumno_p ON a.idPersona = alumno_p.idPersona " +
            "LEFT JOIN MatriculaCarrera mc ON mc.idAlumno = a.idPersona " +
            "LEFT JOIN Carrera c ON mc.idCarrera = c.idCarrera " +
            "LEFT JOIN Alumno_Grupo ag ON ag.idAlumno = a.idPersona " +
            "LEFT JOIN Grupo g ON ag.idGrupo = g.idGrupo " +
            "LEFT JOIN Curso cu ON g.idCurso = cu.idCurso " +
            "LEFT JOIN Ciclo ci ON cu.idCiclo = ci.idCiclo " +
            "WHERE alumno_p.idPersona = ? " +
            "ORDER BY ci.anio, ci.numero;";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPersona);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Nombre del Alumno: " + rs.getString("NombreAlumno"));
                System.out.println("Cedula: " + rs.getString("Cedula"));
                System.out.println("Carrera: " + rs.getString("nombreCarrera"));
                System.out.println("Curso: " + rs.getString("Curso"));
                System.out.println("Creditos: " + rs.getInt("creditos"));
                System.out.println("Numero de Grupo: " + rs.getInt("numeroGrupo"));
                System.out.println("Horario: " + rs.getString("horario"));
                System.out.println("Ciclo Numero: " + rs.getInt("CicloNumero"));
                System.out.println("Anio: " + rs.getInt("Anio"));
                System.out.println("Nota: " + rs.getFloat("Nota"));
                System.out.println("Fecha de Registro: " + rs.getDate("FechaNota"));
                System.out.println("---------------------------------------------");
            }

        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
    }
}
