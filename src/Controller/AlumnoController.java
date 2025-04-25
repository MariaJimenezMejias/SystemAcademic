/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change esta license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java para editar esta plantilla
 */
package Controller;

/**
 *
 * @author maria
 */

import DAO.alumnoDAO;
import model.Alumno;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import view.MenuPersona;
import java.sql.Date;
import view.MenuAdmin;
public class AlumnoController {
   UsuarioController usuarioController = new UsuarioController();
   MenuPersona menuPersona = new MenuPersona();
   MenuAdmin menuAdmin = new MenuAdmin();
    private final alumnoDAO alumnoDAO = new alumnoDAO();

    // Metodo para registrar alumno
    public void registrarAlumno(int idPersona, java.sql.Date fechaNacimiento) {
       
        java.sql.Date fechaRegistro = new java.sql.Date(System.currentTimeMillis()); // Fecha actual
        Alumno alumno = new Alumno(idPersona, fechaNacimiento, fechaRegistro);
        alumnoDAO.insertarAlumno(alumno);
    }
    
        public void llamarRegistro(){
            
        int idPersona=0;
         Scanner scanner = new Scanner(System.in);
     System.out.println("Ingrese el id del alumno");
    idPersona = scanner.nextInt();
     
       System.out.println("Ingrese la fecha de nacimiento del alumno (formato yyyy-mm-dd):");
        String fechaNacimientoStr = scanner.next();  // Se lee como String

        // Convertir la cadena de texto a java.sql.Date
            java.sql.Date fechaNacimiento = Date.valueOf(fechaNacimientoStr);
         AlumnoController alumnoController = new AlumnoController();
        // Crear la fecha de registro con la fecha actual
      alumnoController.registrarAlumno(idPersona, fechaNacimiento);
    }
    


    // Metodo para manejar la busqueda dependiendo de lo que el usuario elija
public void buscarAlumno() {
    Scanner scanner = new Scanner(System.in);

    // Solicitar al usuario que tipo de busqueda desea realizar
    System.out.println("Seleccione el tipo de busqueda:");
    System.out.println("1. Buscar por nombre");
    System.out.println("2. Buscar por cedula");
    System.out.println("3. Buscar por carrera");
    System.out.println("4. Registrar alumno");
        System.out.println("5.Volver");
    System.out.print("Ingrese el numero correspondiente: ");
    int opcion = scanner.nextInt();
    scanner.nextLine();  // Limpiar el buffer del scanner

    String datoBusqueda = "";

    switch (opcion) {
        case 1:
            System.out.print("Ingrese el nombre del alumno: ");
            datoBusqueda = scanner.nextLine();
            buscarPorNombre(datoBusqueda);
            break;
        case 2:
            System.out.print("Ingrese la cedula del alumno: ");
            datoBusqueda = scanner.nextLine();
            buscarPorCedula(datoBusqueda);
            break;
        case 3:
            System.out.print("Ingrese la carrera del alumno: ");
            datoBusqueda = scanner.nextLine();
            buscarPorCarrera(datoBusqueda);
            break;
        case 4:
            menuPersona.mostrarMenu();
            usuarioController.registrarUsuario();
            AlumnoController alumnoController = new AlumnoController();
            alumnoController.llamarRegistro();
            break;
         case 5:
            menuAdmin.menuAdmin();
            break;
            
        default:
            System.out.println("Opcion no valida. Intente de nuevo.");
    }
}



    // Metodo para buscar por nombre
    private void buscarPorNombre(String nombre) {
        ResultSet rs = alumnoDAO.buscarPorNombre(nombre);
        mostrarResultados(rs);
    }

    // Metodo para buscar por cedula
    private void buscarPorCedula(String cedula) {
        ResultSet rs = alumnoDAO.buscarPorCedula(cedula);
        mostrarResultados(rs);
    }

    // Metodo para buscar por carrera
    private void buscarPorCarrera(String carrera) {
        ResultSet rs = alumnoDAO.buscarPorCarrera(carrera);
        mostrarResultados(rs);
    }

    // Metodo para mostrar los resultados obtenidos de la base de datos
    private void mostrarResultados(ResultSet rs) {
        try {
            boolean encontrado = false;
            while (rs.next()) {
                System.out.println("ID Persona: " + rs.getInt("idPersona"));
                System.out.println("Nombre Alumno: " + rs.getString("NombreAlumno"));
                System.out.println("Cedula: " + rs.getString("cedula"));
                System.out.println("Correo: " + rs.getString("correo"));
                System.out.println("Telefono: " + rs.getString("telefono"));
                System.out.println("Nombre Carrera: " + rs.getString("NombreCarrera"));
                System.out.println("Codigo Carrera: " + rs.getInt("CodigoCarrera"));
                System.out.println("----------");
                encontrado = true;
            }
            if (!encontrado) {
                System.out.println("No se encontraron resultados.");
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar los resultados: " + e.getMessage());
        }
    }
}
