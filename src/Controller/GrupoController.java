package Controller;

import DAO.GrupoDAO;
import DAO.profesorDAO;
import model.Grupo;

import java.util.Scanner;

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

}

