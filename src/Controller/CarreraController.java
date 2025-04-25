/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author maria
 */

import DAO.CarreraDAO;
import model.Carrera;
import java.util.List;
import java.util.Scanner;

public class CarreraController {
    static Scanner scanner = new Scanner(System.in);

    // Metodo para crear una carrera
    public static void crearCarrera() {
        System.out.println("Ingrese el titulo de la carrera:");
        String titulo = scanner.nextLine();
        System.out.println("Ingrese el nombre de la carrera:");
        String nombreCarrera = scanner.nextLine();

        boolean resultado = CarreraDAO.insertarCarrera(titulo, nombreCarrera);

        if (resultado) {
            System.out.println("Carrera creada correctamente.");
        } else {
            System.out.println("Error al crear la carrera.");
        }
    }

    // Metodo para obtener todas las carreras
    public static void listarCarreras() {
        List<Carrera> carreras = CarreraDAO.obtenerCarreras();

        if (carreras.isEmpty()) {
            System.out.println("No hay carreras registradas.");
        } else {
            System.out.println("Listado de Carreras:");
            for (Carrera carrera : carreras) {
                System.out.println("ID: " + carrera.getIdCarrera() + " - " + carrera.getNombreCarrera());
            }
        }
    }

    // Metodo para obtener una carrera por ID
    public static void obtenerCarreraPorId() {
        System.out.println("Ingrese el ID de la carrera:");
        int idCarrera = scanner.nextInt();

        Carrera carrera = CarreraDAO.obtenerCarreraPorId(idCarrera);

        if (carrera == null) {
            System.out.println("Carrera no encontrada.");
        } else {
            System.out.println("Carrera encontrada: " + carrera.getTitulo() + " - " + carrera.getNombreCarrera());
        }
    }

    // Metodo para actualizar una carrera
    public void actualizarCarrera() {
        System.out.println("Ingrese el ID de la carrera a actualizar:");
        int idCarrera = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de linea

        System.out.println("Ingrese el nuevo titulo de la carrera:");
        String titulo = scanner.nextLine();
        System.out.println("Ingrese el nuevo nombre de la carrera:");
        String nombreCarrera = scanner.nextLine();

        boolean resultado = CarreraDAO.actualizarCarrera(idCarrera, titulo, nombreCarrera);

        if (resultado) {
            int opcion;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Carrera actualizada correctamente.");
             
            System.out.println("Desea editar un curso? Seleccione 1, si desea editarlo, seleccione 2 si desea volver al menu");
             opcion = scanner.nextInt();
                if(opcion ==1){
                    mostrarCursosDeCarrera();
                }else{
                
                }
        } else {
            System.out.println("Error al actualizar la carrera.");
        }
    }

    // Metodo para eliminar una carrera
    public static void eliminarCarrera() {
        System.out.println("Ingrese el ID de la carrera a eliminar:");
        int idCarrera = scanner.nextInt();

        boolean resultado = CarreraDAO.eliminarCarrera(idCarrera);

        if (resultado) {
            System.out.println("Carrera eliminada correctamente.");
        } else {
            System.out.println("Error al eliminar la carrera.");
        }
    }
    
    
    public  void mostrarCursosDeCarrera() {
    System.out.println("Ingrese el ID de la carrera para ver sus cursos:");
    int idCarrera = scanner.nextInt();
    CarreraDAO carreraDAO = new CarreraDAO();
    carreraDAO.listarCursosPorCarrera(idCarrera);
   
}
    

    public Carrera buscarCarreraPorNombre(String nombreCarrera) {
        CarreraDAO carreraDAO = new CarreraDAO();
        return carreraDAO.busquedaCarreraPorNombre(nombreCarrera);
    
}
    
// Cambia el metodo a static
public  void listarCarreraPorNombre(String nombreCarrera) {
    Carrera carrera = buscarCarreraPorNombre(nombreCarrera);  // Llamar al metodo estatico de buscarCarreraPorNombre
    if (carrera != null) {
        System.out.println("Carrera encontrada: ");
        System.out.println("Codigo: " + carrera.getIdCarrera());
        System.out.println("Titulo: " + carrera.getTitulo());
        System.out.println("Nombre de la carrera: " + carrera.getNombreCarrera());
    } else {
        System.out.println("Carrera no encontrada.");
    }
}


}
