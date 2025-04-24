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

    public void buscarHistorial() {
        // Crear un objeto Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario que ingrese el nombre del alumno
        System.out.print("Ingrese el nombre del alumno para consultar su historial: ");
        String nombreAlumno = scanner.nextLine();  // Leer el nombre del alumno

        // Crear una instancia del DAO y llamar al método para consultar el historial
        HistorialAdminDAO dao = new HistorialAdminDAO();
        dao.consultarHistorialAdmin(nombreAlumno);  // Pasar el nombre del alumno al método DAO

        // Cerrar el scanner
        scanner.close();
    }
    
    

    // Método que recibe el idPersona y llama al DAO para consultar el historial
    public void buscarHistorialPorId(int idPersona) {
        // Crear una instancia del DAO
        HistorialAdminDAO dao = new HistorialAdminDAO();
        dao.consultarHistorialAdminPorId(idPersona);  
    
}

}
