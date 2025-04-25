package DAO;

import model.Carrera;
import DB.dbConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Controller.CursoController; // Asegurate que exista
import java.util.HashMap;
import java.util.Map;

public class CarreraDAO {

    Connection conn = dbConnection.getConnection();
    Scanner scanner = new Scanner(System.in);
    CursoController controller = new CursoController(); // Instancia del controlador

    public static boolean insertarCarrera(String titulo, String nombreCarrera) {
        String sql = "INSERT INTO Carrera (titulo, nombreCarrera) VALUES (?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, titulo);
            pstmt.setString(2, nombreCarrera);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar la carrera: " + e.getMessage());
        }
        return false;
    }

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
            System.out.println("Error al obtener las carreras: " + e.getMessage());
        }
        return carreras;
    }

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
            System.out.println("Error al obtener la carrera: " + e.getMessage());
        }
        return carrera;
    }

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
            System.out.println("Error al actualizar la carrera: " + e.getMessage());
        }
        return false;
    }

    public static boolean eliminarCarrera(int idCarrera) {
        String sql = "DELETE FROM Carrera WHERE idCarrera = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idCarrera);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar la carrera: " + e.getMessage());
        }
        return false;
    }

   public void listarCursosPorCarrera(int idCarrera) {
    String sql = "SELECT cu.idCurso, cu.nombre AS nombreCurso, cu.creditos, cu.horasSemanales " +
                 "FROM Carrera ca " +
                 "JOIN Ciclo ci ON ca.idCarrera = ci.idCarrera " +
                 "JOIN Curso cu ON ci.idCiclo = cu.idCiclo " +
                 "WHERE ca.idCarrera = ?";

    try (Connection conn = dbConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idCarrera);
        ResultSet rs = stmt.executeQuery();

        List<Integer> listaIds = new ArrayList<>();
        Map<Integer, String> cursosMap = new HashMap<>();

        System.out.println("Cursos disponibles para la carrera con ID: " + idCarrera);
        while (rs.next()) {
            int idCurso = rs.getInt("idCurso");
            String nombreCurso = rs.getString("nombreCurso");
            int creditos = rs.getInt("creditos");
            int horasSemanales = rs.getInt("horasSemanales");

            System.out.println("ID: " + idCurso +
                               " | Nombre: " + nombreCurso +
                               " | Creditos: " + creditos +
                               " | Horas por semana: " + horasSemanales);

            listaIds.add(idCurso);
            cursosMap.put(idCurso, nombreCurso);
        }

        if (listaIds.isEmpty()) {
            System.out.println("No hay cursos registrados para esta carrera.");
            return;
        }

        // Aquí sólo realizas la parte de la lógica sin recibir Scanner en este método
        int idSeleccionado = obtenerIdSeleccionado();  // Llamada a método que maneja la entrada de usuario
        if (idSeleccionado == 0) return;

        if (!listaIds.contains(idSeleccionado)) {
            System.out.println("ID no válido, debe pertenecer a esta carrera. Intente de nuevo.");
            return;
        }

        System.out.println("Editando curso: " + cursosMap.get(idSeleccionado));
        // Aquí puedes continuar con la edición del curso
        editarCurso(idSeleccionado);  // Llamada a otro método que maneja la edición del curso

    } catch (SQLException e) {
        System.out.println("Error al obtener los cursos: " + e.getMessage());
    }
}

private int obtenerIdSeleccionado() {
    Scanner scanner = new Scanner(System.in);
    int idSeleccionado;

    while (true) {
        System.out.print("Ingrese el ID del curso que desea editar (0 para salir): ");
        if (scanner.hasNextInt()) {
            idSeleccionado = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            if (idSeleccionado == 0) return idSeleccionado;

            break;
        } else {
            System.out.println("Entrada no válida, intente de nuevo.");
            scanner.nextLine(); // Limpiar el buffer
        }
    }

    return idSeleccionado;
}

private void editarCurso(int idSeleccionado) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Editando curso con ID: " + idSeleccionado);
    System.out.print("Nuevo nombre: ");
    String nuevoNombre = scanner.nextLine();
    System.out.print("Nuevos créditos: ");
    int nuevosCreditos = scanner.nextInt();
    System.out.print("Nuevas horas semanales: ");
    int nuevasHoras = scanner.nextInt();
    System.out.print("Nuevo ID de ciclo: ");
    int nuevoIdCiclo = scanner.nextInt();

    // Aquí va la lógica para actualizar el curso
    boolean actualizado = controller.actualizarCurso(idSeleccionado, nuevoNombre, nuevosCreditos, nuevasHoras, nuevoIdCiclo);
    if (actualizado) {
        System.out.println("Curso actualizado correctamente.");
    } else {
        System.out.println("Error al actualizar el curso.");
    }
}



    public static Carrera busquedaCarreraCodigo(int idCarrera) {
        String sql = "SELECT idCarrera, titulo, nombreCarrera FROM Carrera WHERE idCarrera = ?";
        Carrera carrera = null;

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCarrera);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                carrera = new Carrera(
                    rs.getInt("idCarrera"),
                    rs.getString("titulo"),
                    rs.getString("nombreCarrera")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error en la busqueda de carrera por codigo: " + e.getMessage());
        }

        return carrera;
    }

    public static Carrera busquedaCarreraPorNombre(String nombreCarrera) {
        String sql = "SELECT idCarrera, titulo, nombreCarrera FROM Carrera WHERE nombreCarrera = ?";
        Carrera carrera = null;

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombreCarrera);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                carrera = new Carrera(
                    rs.getInt("idCarrera"),
                    rs.getString("titulo"),
                    rs.getString("nombreCarrera")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error en la busqueda de carrera por nombre: " + e.getMessage());
        }

        return carrera;
    }
}
