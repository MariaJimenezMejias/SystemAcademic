package Controller;

import DAO.UsuarioDAO;
import model.Usuario1;
import view.UsuarioView;
import java.util.List;
import java.sql.*;

public class UsuarioController {

    private UsuarioDAO usuarioDAO;
    private UsuarioView usuarioView;

    // Constructor
    public UsuarioController(UsuarioDAO usuarioDAO, UsuarioView usuarioView) {
        this.usuarioDAO = usuarioDAO;
        this.usuarioView = usuarioView;
    }

    // Método para buscar profesores por nombre
    public void buscarProfesorPorNombre(String nombre) {
        try {
            List<Usuario1> profesores = usuarioDAO.buscarProfesorPorNombre(nombre);
            usuarioView.mostrarProfesores(profesores);
        } catch (SQLException e) {
            usuarioView.mostrarError("Error al buscar por nombre: " + e.getMessage());
        }
    }

    // Método para buscar profesores por cédula
    public void buscarProfesorPorCedula(String cedula) {
        try {
            List<Usuario1> profesores = usuarioDAO.buscarProfesorPorCedula(cedula);
            usuarioView.mostrarProfesores(profesores);
        } catch (SQLException e) {
            usuarioView.mostrarError("Error al buscar por cédula: " + e.getMessage());
        }
    }
}
