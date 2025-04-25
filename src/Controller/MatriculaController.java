package Controller;

import static Controller.LoginController.idRelacionado;
import static Controller.LoginController.tipoUsuario;
import view.MenuAdmin;
import model.Matricula;
import java.util.Scanner;
import DAO.alumnoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DB.dbConnection;

public class MatriculaController {
    static Scanner scanner = new Scanner(System.in);

    public static void matricularAlumno() {
        while (true) { // Bucle para mantener el menu activo
            System.out.println("Ingrese la cedula del alumno:");
            String cedulaAlumno = scanner.nextLine();
            int idAlumno = alumnoDAO.obtenerIdAlumnoPorCedula(cedulaAlumno);

            if (idAlumno == -1) {
                System.out.println("Alumno no encontrado.");
                return;
            }

            System.out.println("Seleccione una opcion:");
            System.out.println("1. Matricular al alumno en una carrera.");
            System.out.println("2. Matricular al alumno en un grupo.");
            System.out.println("3. Mostrar matriculas activas del estudiante.");
            System.out.println("4. Salir al menu principal.");
            System.out.print("Ingrese el numero correspondiente: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    matricularEnCarrera(idAlumno);
                    break;
                case 2:
                    matricularEnGrupo(idAlumno);
                    break;
                case 3:
                    mostrarMatriculasActivas(cedulaAlumno);
                    break;
                case 4:
                    System.out.println("Regresando al menu principal...");
                    if (LoginController.tipoUsuario.equalsIgnoreCase("admin")) {
                        MenuAdmin menuAdmin = new MenuAdmin();
                        menuAdmin.menuAdmin();
                    } else if (LoginController.tipoUsuario.equalsIgnoreCase("superadmin")){
                        MenuAdmin menuAdmin = new MenuAdmin();
                        menuAdmin.menuAdmin();
                        System.out.print(LoginController.tipoUsuario);
                        System.out.println("Estamos pasando aca");
                    } else {
                        matricularAlumno();
                    }
                    return; // Salir del bucle y regresar al menu principal
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }

    private static void matricularEnCarrera(int idAlumno) {
        System.out.println("Ingrese el ID de la carrera:");
        int idCarrera = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        Matricula.insertarMatricula(idAlumno, idCarrera);
    }

    private static void matricularEnGrupo(int idAlumno) {
        System.out.println("Ingrese el ID del grupo:");
        int idGrupo = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        Matricula.insertarMatriculaGrupo(idAlumno, idGrupo);
    }

    private static void mostrarMatriculasActivas(String cedula) {
        String sql = "SELECT p.nombre AS nombreEstudiante, " +
                     "cu.nombre AS nombreCurso, " +
                     "ca.nombreCarrera, " +
                     "ci.anio AS anioCiclo, " +
                     "ci.numero AS numeroCiclo " +
                     "FROM Ciclo ci " +
                     "JOIN Curso cu ON cu.idCiclo = ci.idCiclo " +
                     "JOIN Grupo g ON g.idCurso = cu.idCurso " +
                     "JOIN Alumno_Grupo ag ON ag.idGrupo = g.idGrupo " +
                     "JOIN Alumno a ON a.idPersona = ag.idAlumno " +
                     "JOIN Persona p ON p.idPersona = a.idPersona " +
                     "JOIN Carrera ca ON ca.idCarrera = g.idCarrera " +
                     "WHERE p.cedula = ? AND CAST(GETDATE() AS DATE) BETWEEN ci.fechaInicio AND ci.fechaFinalizacion";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cedula);
            ResultSet rs = stmt.executeQuery();

            System.out.println("=== Matriculas activas ===");
            boolean hayDatos = false;
            while (rs.next()) {
                hayDatos = true;
                System.out.println("Estudiante: " + rs.getString("nombreEstudiante"));
                System.out.println("Curso: " + rs.getString("nombreCurso"));
                System.out.println("Carrera: " + rs.getString("nombreCarrera"));
                System.out.println("Ciclo: " + rs.getInt("anioCiclo") + " - " + rs.getInt("numeroCiclo"));
                System.out.println("---------------------------");
            }

            if (!hayDatos) {
                System.out.println("No hay matriculas activas para este estudiante.");
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar las matriculas activas: " + e.getMessage());
        }
    }
}
