/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author maria
 */
import java.util.Scanner;
import DAO.HistorialAdminDAO;

public class HistorialAdminController {


    

    // Método que recibe el idPersona y llama al DAO para consultar el historial
    public void buscarHistorialPorId(int idPersona) {
        // Crear una instancia del DAO
        HistorialAdminDAO dao = new HistorialAdminDAO();
        dao.consultarHistorialAdminPorId(idPersona);  
    
}

}
