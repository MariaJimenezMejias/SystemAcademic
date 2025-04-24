package Controller;

import DAO.CicloDAO;
import model.Ciclo;

import java.util.Scanner;

public class CicloController {

    static Scanner scanner = new Scanner(System.in);

    public static void crearCiclo() {
        Ciclo ciclo = new Ciclo();

        System.out.println("Ingrese fecha de inicio (YYYY-MM-DD):");
        ciclo.setFechaInicio(scanner.nextLine());

        System.out.println("Ingrese fecha de finalización (YYYY-MM-DD):");
        ciclo.setFechaFinalizacion(scanner.nextLine());

        System.out.println("Ingrese número del ciclo:");
        ciclo.setNumero(scanner.nextInt());

        System.out.println("Ingrese año del ciclo:");
        ciclo.setAnio(scanner.nextInt());

        System.out.println("Ingrese ID de la carrera:");
        ciclo.setIdCarrera(scanner.nextInt());
        scanner.nextLine(); // limpiar buffer

        CicloDAO.insertarCiclo(ciclo);
    }

    public static void listarCiclos() {
        CicloDAO.listarCiclos().forEach(c -> {
            System.out.println("ID: " + c.getIdCiclo() +
                    ", Inicio: " + c.getFechaInicio() +
                    ", Fin: " + c.getFechaFinalizacion() +
                    ", Número: " + c.getNumero() +
                    ", Año: " + c.getAnio() +
                    ", ID Carrera: " + c.getIdCarrera());
        });
    }

    public static void obtenerCicloPorId() {
        System.out.print("Ingrese ID del ciclo a buscar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Ciclo c = CicloDAO.obtenerCicloPorId(id);
        if (c != null) {
            System.out.println("ID: " + c.getIdCiclo() +
                    ", Inicio: " + c.getFechaInicio() +
                    ", Fin: " + c.getFechaFinalizacion() +
                    ", Número: " + c.getNumero() +
                    ", Año: " + c.getAnio() +
                    ", ID Carrera: " + c.getIdCarrera());
        } else {
            System.out.println("❌ Ciclo no encontrado.");
        }
    }

    public static void actualizarCiclo() {
        listarCiclos();

        System.out.print("Ingrese ID del ciclo a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Ciclo ciclo = new Ciclo();
        ciclo.setIdCiclo(id);

        System.out.println("Nueva fecha de inicio (YYYY-MM-DD):");
        ciclo.setFechaInicio(scanner.nextLine());

        System.out.println("Nueva fecha de finalización (YYYY-MM-DD):");
        ciclo.setFechaFinalizacion(scanner.nextLine());

        System.out.println("Nuevo número de ciclo:");
        ciclo.setNumero(scanner.nextInt());

        System.out.println("Nuevo año del ciclo:");
        ciclo.setAnio(scanner.nextInt());

        System.out.println("Nuevo ID de carrera:");
        ciclo.setIdCarrera(scanner.nextInt());
        scanner.nextLine();

        CicloDAO.actualizarCiclo(ciclo);
    }

    public static void eliminarCiclo() {
        listarCiclos();

        System.out.print("Ingrese ID del ciclo a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        CicloDAO.eliminarCiclo(id);
    }

    public static void listarCiclosPorCarrera() {
        System.out.print("Ingrese nombre de la carrera: ");
        String nombreCarrera = scanner.nextLine();

        CicloDAO.listarCiclosPorCarrera(nombreCarrera).forEach(c -> {
            System.out.println("ID: " + c.getIdCiclo() +
                    ", Inicio: " + c.getFechaInicio() +
                    ", Fin: " + c.getFechaFinalizacion() +
                    ", Número: " + c.getNumero() +
                    ", Año: " + c.getAnio() +
                    ", ID Carrera: " + c.getIdCarrera());
        });
    }
    
    
    public static void listarCiclosPorAnio() {
    System.out.print("Ingrese el año para filtrar los ciclos: ");
    int anio = scanner.nextInt();
    scanner.nextLine();  // Limpiar el buffer

    CicloDAO.listarCiclosPorAnio(anio).forEach(c -> {
        System.out.println("ID: " + c.getIdCiclo() +
                ", Año: " + c.getAnio());
    });
}

}
