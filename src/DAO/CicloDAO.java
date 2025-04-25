package DAO;

import DB.dbConnection;
import model.Ciclo;

import java.sql.*;
import java.util.ArrayList;

public class CicloDAO {

    public static void insertarCiclo(Ciclo ciclo) {
        String sql = "INSERT INTO Ciclo (fechaInicio, fechaFinalizacion, numero, anio, idCarrera) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ciclo.getFechaInicio());
            stmt.setString(2, ciclo.getFechaFinalizacion());
            stmt.setInt(3, ciclo.getNumero());
            stmt.setInt(4, ciclo.getAnio());
            stmt.setInt(5, ciclo.getIdCarrera());

            stmt.executeUpdate();
            System.out.println("Ciclo registrado con exito.");
        } catch (SQLException e) {
            System.out.println("Error al registrar ciclo: " + e.getMessage());
        }
    }

    public static ArrayList<Ciclo> listarCiclos() {
        ArrayList<Ciclo> ciclos = new ArrayList<>();
        String sql = "SELECT * FROM Ciclo";

        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Ciclo c = new Ciclo();
                c.setIdCiclo(rs.getInt("idCiclo"));
                c.setFechaInicio(rs.getString("fechaInicio"));
                c.setFechaFinalizacion(rs.getString("fechaFinalizacion"));
                c.setNumero(rs.getInt("numero"));
                c.setAnio(rs.getInt("anio"));
                c.setIdCarrera(rs.getInt("idCarrera"));
                ciclos.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar ciclos: " + e.getMessage());
        }

        return ciclos;
    }

    public static Ciclo obtenerCicloPorId(int id) {
        String sql = "SELECT * FROM Ciclo WHERE idCiclo = ?";
        Ciclo c = null;

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                c = new Ciclo();
                c.setIdCiclo(rs.getInt("idCiclo"));
                c.setFechaInicio(rs.getString("fechaInicio"));
                c.setFechaFinalizacion(rs.getString("fechaFinalizacion"));
                c.setNumero(rs.getInt("numero"));
                c.setAnio(rs.getInt("anio"));
                c.setIdCarrera(rs.getInt("idCarrera"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener ciclo: " + e.getMessage());
        }

        return c;
    }

    public static void actualizarCiclo(Ciclo ciclo) {
        String sql = "UPDATE Ciclo SET fechaInicio = ?, fechaFinalizacion = ?, numero = ?, anio = ?, idCarrera = ? WHERE idCiclo = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ciclo.getFechaInicio());
            stmt.setString(2, ciclo.getFechaFinalizacion());
            stmt.setInt(3, ciclo.getNumero());
            stmt.setInt(4, ciclo.getAnio());
            stmt.setInt(5, ciclo.getIdCarrera());
            stmt.setInt(6, ciclo.getIdCiclo());

            stmt.executeUpdate();
            System.out.println("Ciclo actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar ciclo: " + e.getMessage());
        }
    }

    public static void eliminarCiclo(int id) {
        String sql = "DELETE FROM Ciclo WHERE idCiclo = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Ciclo eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar ciclo: " + e.getMessage());
        }
    }

    public static ArrayList<Ciclo> listarCiclosPorCarrera(String nombreCarrera) {
        ArrayList<Ciclo> ciclos = new ArrayList<>();
        String sql = """
            SELECT c.* FROM Ciclo c
            INNER JOIN Carrera ca ON ca.idCarrera = c.idCarrera
            WHERE ca.nombreCarrera = ?
        """;

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombreCarrera);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Ciclo c = new Ciclo();
                c.setIdCiclo(rs.getInt("idCiclo"));
                c.setFechaInicio(rs.getString("fechaInicio"));
                c.setFechaFinalizacion(rs.getString("fechaFinalizacion"));
                c.setNumero(rs.getInt("numero"));
                c.setAnio(rs.getInt("anio"));
                c.setIdCarrera(rs.getInt("idCarrera"));
                ciclos.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar ciclos por carrera: " + e.getMessage());
        }

        return ciclos;
    }
    
    
    public static ArrayList<Ciclo> listarCiclosPorAnio(int anio) {
    ArrayList<Ciclo> ciclos = new ArrayList<>();
    String sql = """
        SELECT c.idCiclo, c.anio, ca.nombreCarrera 
        FROM Ciclo c
        JOIN Carrera ca ON c.idCarrera = ca.idCarrera
        WHERE c.anio = ?;
    """;

    try (Connection conn = dbConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, anio);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Ciclo c = new Ciclo();
            c.setIdCiclo(rs.getInt("idCiclo"));
            c.setAnio(rs.getInt("anio"));
            String carreraNombre = rs.getString("nombreCarrera");
            // Aqui se supone que el ciclo tiene un atributo de carrera
            // Si no, podrias agregar uno o simplemente imprimir el nombre
            System.out.println("Ciclo ID: " + c.getIdCiclo() +
                    ", Ano: " + c.getAnio() +
                    ", Carrera: " + carreraNombre);
            ciclos.add(c);
        }
    } catch (SQLException e) {
        System.out.println("Error al listar ciclos por ano: " + e.getMessage());
    }

    return ciclos;
}

}
