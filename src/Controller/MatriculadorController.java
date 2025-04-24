/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.matriculadorDAO;
import model.Matriculador;

public class MatriculadorController {

    private matriculadorDAO matriculadorDAO;

    public MatriculadorController() {
        this.matriculadorDAO = new matriculadorDAO();
    }

    public void crearMatriculador(int idUsuario, String estado) {
        Matriculador matriculador = new Matriculador(idUsuario, estado);
        matriculadorDAO.insertarMatriculador(matriculador);
    }
}
