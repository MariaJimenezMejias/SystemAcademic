/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import DB.dbConnection; // Importar la clase dbConnection

public class HistorialAdminDAO {

    // Método para consultar el historial del alumno por su nombre
    public void consultarHistorialAdmin(String nombreAlumno) {
        // SQL de la consulta con el filtro por nombre de alumno
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
            "prof_p.nombre AS NombreProfesor, " +
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
            "LEFT JOIN Profesor pr ON n.idProfesor = pr.idProfesor " +
            "LEFT JOIN Persona prof_p ON pr.idProfesor = prof_p.idPersona " +
            "WHERE alumno_p.nombre = ? " +  // Filtro por nombre
            "ORDER BY ci.anio, ci.numero;";

        // Usamos la conexión proporcionada por dbConnection
        try (Connection conn = dbConnection.getConnection();  // Usar la conexión de dbConnection
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Establecer el parámetro del nombre del alumno
            stmt.setString(1, nombreAlumno);
            
            // Ejecutar la consulta
            ResultSet rs = stmt.executeQuery();
            
            // Procesar los resultados
            while (rs.next()) {
                System.out.println("Nombre del Alumno: " + rs.getString("NombreAlumno"));
                System.out.println("Cédula: " + rs.getString("Cedula"));
                System.out.println("Carrera: " + rs.getString("nombreCarrera"));
                System.out.println("Curso: " + rs.getString("Curso"));
                System.out.println("Créditos: " + rs.getInt("creditos"));
                System.out.println("Número de Grupo: " + rs.getInt("numeroGrupo"));
                System.out.println("Horario: " + rs.getString("horario"));
                System.out.println("Ciclo Número: " + rs.getInt("CicloNumero"));
                System.out.println("Año: " + rs.getInt("Anio"));
                System.out.println("Nombre del Profesor: " + rs.getString("NombreProfesor"));
                System.out.println("Nota: " + rs.getFloat("Nota"));
                System.out.println("Fecha de Registro: " + rs.getDate("FechaNota"));
                System.out.println("---------------------------------------------");
            }

        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
    }

    // Método para consultar el historial del alumno por su ID
    public void consultarHistorialAdminPorId(int idPersona) {
        // SQL de la consulta con el filtro por idPersona
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
            "prof_p.nombre AS NombreProfesor, " +
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
            "LEFT JOIN Profesor pr ON n.idProfesor = pr.idProfesor " +
            "LEFT JOIN Persona prof_p ON pr.idProfesor = prof_p.idPersona " +
            "WHERE alumno_p.idPersona = ? " +  // Filtro por ID
            "ORDER BY ci.anio, ci.numero;";

        // Usamos la conexión proporcionada por dbConnection
        try (Connection conn = dbConnection.getConnection();  // Usar la conexión de dbConnection
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Establecer el parámetro del idPersona
            stmt.setInt(1, idPersona);
            
            // Ejecutar la consulta
            ResultSet rs = stmt.executeQuery();
            
            // Procesar los resultados
            while (rs.next()) {
                System.out.println("Nombre del Alumno: " + rs.getString("NombreAlumno"));
                System.out.println("Cédula: " + rs.getString("Cedula"));
                System.out.println("Carrera: " + rs.getString("nombreCarrera"));
                System.out.println("Curso: " + rs.getString("Curso"));
                System.out.println("Créditos: " + rs.getInt("creditos"));
                System.out.println("Número de Grupo: " + rs.getInt("numeroGrupo"));
                System.out.println("Horario: " + rs.getString("horario"));
                System.out.println("Ciclo Número: " + rs.getInt("CicloNumero"));
                System.out.println("Año: " + rs.getInt("Anio"));
                System.out.println("Nombre del Profesor: " + rs.getString("NombreProfesor"));
                System.out.println("Nota: " + rs.getFloat("Nota"));
                System.out.println("Fecha de Registro: " + rs.getDate("FechaNota"));
                System.out.println("---------------------------------------------");
            }

        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
    }
}
