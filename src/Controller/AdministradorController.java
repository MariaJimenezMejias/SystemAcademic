/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.administradorDAO;
import model.Administrador;

public class AdministradorController {
    private administradorDAO adminDAO;

    public AdministradorController() {
        this.adminDAO = new administradorDAO();
    }

    public void crearAdministrador(int idUsuario) {
        Administrador admin = new Administrador(idUsuario);
        adminDAO.insertarAdministrador(admin);
    }
}
