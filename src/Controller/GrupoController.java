package Controller;

import DAO.GrupoDAO;
import DAO.profesorDAO;
import model.Grupo;

import java.util.Scanner;
import java.util.List;

public class GrupoController {
    public static void crearGrupo() {
        Scanner scanner = new Scanner(System.in);
        Grupo grupo = new Grupo();

        System.out.print("Ingrese el número del grupo: ");
        grupo.setNumeroGrupo(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Ingrese el horario del grupo: ");
        grupo.setHorario(scanner.nextLine());

        System.out.print("Ingrese la cédula del profesor encargado: ");
        String cedulaProfesor = scanner.nextLine();

        profesorDAO pdao = new profesorDAO();
        int idProfesor = pdao.obtenerIdProfesorPorCedula(cedulaProfesor);

        if (idProfesor == -1) {
            System.out.println("❌ Profesor no encontrado con esa cédula.");
            return;
        }

        grupo.setIdProfesor(idProfesor);

        System.out.print("Ingrese el ID del ciclo: ");
        grupo.setIdCiclo(scanner.nextInt());

        System.out.print("Ingrese el ID de la carrera: ");
        grupo.setIdCarrera(scanner.nextInt());

        System.out.print("Ingrese el ID del curso: ");
        grupo.setIdCurso(scanner.nextInt());

        GrupoDAO gdao = new GrupoDAO();
        gdao.insertarGrupo(grupo);
    }
    
    
    public static void listarGrupos() {
    GrupoDAO gdao = new GrupoDAO();
    var lista = gdao.listarGrupos();

    if (lista.isEmpty()) {
        System.out.println("⚠️ No hay grupos registrados.");
    } else {
        System.out.println("\n📋 Lista de Grupos:");
        for (Grupo g : lista) {
            System.out.println("----------------------------------");
            System.out.println("ID Grupo: " + g.getIdGrupo());
            System.out.println("Número Grupo: " + g.getNumeroGrupo());
            System.out.println("Horario: " + g.getHorario());
            System.out.println("ID Profesor: " + g.getIdProfesor());
            System.out.println("ID Ciclo: " + g.getIdCiclo());
            System.out.println("ID Carrera: " + g.getIdCarrera());
            System.out.println("ID Curso: " + g.getIdCurso());
        }
    }
}

    // New method to list groups filtered by career, cycle, and course
    public List<Grupo> listarGruposPorCarreraCicloCurso(int idCarrera, int idCiclo, int idCurso) {
        GrupoDAO gdao = new GrupoDAO();
        return gdao.listarGruposPorCarreraCicloCurso(idCarrera, idCiclo, idCurso);
    }

    // New method to create a group with given career, cycle, and course IDs
    public void crearGrupoConDatos(int idCarrera, int idCiclo, int idCurso) {
        Scanner scanner = new Scanner(System.in);
        Grupo grupo = new Grupo();

        System.out.print("Ingrese el número del grupo: ");
        grupo.setNumeroGrupo(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Ingrese el horario del grupo: ");
        grupo.setHorario(scanner.nextLine());

        System.out.print("Ingrese la cédula del profesor encargado: ");
        String cedulaProfesor = scanner.nextLine();

        profesorDAO pdao = new profesorDAO();
        int idProfesor = pdao.obtenerIdProfesorPorCedula(cedulaProfesor);

        if (idProfesor == -1) {
            System.out.println("❌ Profesor no encontrado con esa cédula.");
            return;
        }

        grupo.setIdProfesor(idProfesor);
        grupo.setIdCiclo(idCiclo);
        grupo.setIdCarrera(idCarrera);
        grupo.setIdCurso(idCurso);

        GrupoDAO gdao = new GrupoDAO();
        gdao.insertarGrupo(grupo);
    }

    // New method to modify a group by ID
    public void modificarGrupoPorId(int idGrupo) {
        Scanner scanner = new Scanner(System.in);
        GrupoDAO gdao = new GrupoDAO();
        Grupo grupo = gdao.obtenerGrupoPorId(idGrupo);

        if (grupo == null) {
            System.out.println("Grupo no encontrado.");
            return;
        }

        System.out.print("Nuevo número del grupo (actual: " + grupo.getNumeroGrupo() + "): ");
        int nuevoNumero = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nuevo horario (actual: " + grupo.getHorario() + "): ");
        String nuevoHorario = scanner.nextLine();

        System.out.print("Nueva cédula del profesor encargado: ");
        String cedulaProfesor = scanner.nextLine();

        profesorDAO pdao = new profesorDAO();
        int idProfesor = pdao.obtenerIdProfesorPorCedula(cedulaProfesor);

        if (idProfesor == -1) {
            System.out.println("❌ Profesor no encontrado con esa cédula.");
            return;
        }

        grupo.setNumeroGrupo(nuevoNumero);
        grupo.setHorario(nuevoHorario);
        grupo.setIdProfesor(idProfesor);

        gdao.actualizarGrupo(grupo);
    }
}

