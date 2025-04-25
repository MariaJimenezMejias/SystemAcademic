package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DB.dbConnection;

public class Nota {

    private Connection conexion;

    // Constructor sin parámetros, la conexión se obtiene desde dbConnection
    public Nota() {
        this.conexion = dbConnection.getConnection(); // Usar la clase dbConnection para obtener la conexión
    }

    // Método para listar los grupos del profesor
    public void listarGruposPorProfesor(int idProfesor) {
        String sql = "SELECT g.idGrupo, g.numeroGrupo, g.horario, g.idCurso " +
                     "FROM Grupo g " +
                     "WHERE g.idProfesor = ? " +
                     "ORDER BY g.numeroGrupo";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idProfesor); // Establecer el id del profesor como parámetro
            ResultSet rs = stmt.executeQuery();

            System.out.println("Grupos del Profesor con ID: " + idProfesor);
            while (rs.next()) {
                int idGrupo = rs.getInt("idGrupo");
                int numeroGrupo = rs.getInt("numeroGrupo");
                String horario = rs.getString("horario");
                int idCurso = rs.getInt("idCurso");

                // Mostrar la información de los grupos
                System.out.println("ID Grupo: " + idGrupo +
                                   ", Numero: " + numeroGrupo +
                                   ", Horario: " + horario +
                                   ", ID Curso: " + idCurso);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener los grupos: " + e.getMessage());
        }
    }

    // Método para listar los estudiantes de un grupo y profesor
    public void listarEstudiantesPorProfesorYGrupo(int idProfesor, int idGrupo) {
        String sql = "SELECT p.nombre, a.idPersona, g.idGrupo, g.numeroGrupo, g.horario " +
                     "FROM Persona p " +
                     "JOIN Alumno a ON p.idPersona = a.idPersona " +
                     "JOIN Alumno_Grupo ag ON a.idPersona = ag.idAlumno " +
                     "JOIN Grupo g ON ag.idGrupo = g.idGrupo " +
                     "WHERE g.idProfesor = ? AND g.idGrupo = ? " +
                     "ORDER BY a.idPersona";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idProfesor);  // Establecer el idProfesor como parámetro
            stmt.setInt(2, idGrupo);     // Establecer el idGrupo como parámetro
            ResultSet rs = stmt.executeQuery();

            System.out.println("Estudiantes del Profesor con ID: " + idProfesor + " en el Grupo: " + idGrupo);
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int idPersona = rs.getInt("idPersona");
                String horario = rs.getString("horario");

                // Mostrar la información de los estudiantes
                System.out.println("Nombre: " + nombre +
                                   ", ID Alumno: " + idPersona +
                                   ", Horario: " + horario);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener los estudiantes: " + e.getMessage());
        }
    }
    
    
     public void insertarNota(int idAlumno, int idProfesor, float nota, String fechaRegistro, int idGrupo) {
        String sql = "INSERT INTO Notas (idAlumno, idProfesor, nota, fechaRegistro, idGrupo) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idAlumno);         // Establecer id del alumno
            stmt.setInt(2, idProfesor);       // Establecer id del profesor
            stmt.setFloat(3, nota);           // Establecer la nota
            stmt.setString(4, fechaRegistro); // Establecer la fecha de registro
            stmt.setInt(5, idGrupo);          // Establecer id del grupo

            int filasInsertadas = stmt.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Nota insertada correctamente!");
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar la nota: " + e.getMessage());
        }
}

}