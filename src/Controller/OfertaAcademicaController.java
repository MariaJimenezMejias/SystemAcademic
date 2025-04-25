package Controller;

import java.util.List;
import java.util.Scanner;

import Controller.CarreraController;
import Controller.CicloController;
import Controller.CursoController;
import Controller.GrupoController;
import model.Carrera;
import model.Ciclo;
import model.Curso;
import model.Grupo;

public class OfertaAcademicaController {

    private static Scanner scanner = new Scanner(System.in);

    private Integer idCarreraSeleccionada = null;
    private Integer idCicloSeleccionado = null;
    private Integer idCursoSeleccionado = null;

    private CarreraController carreraController = new CarreraController();
    private CicloController cicloController = new CicloController();
    private CursoController cursoController = new CursoController();
    private GrupoController grupoController = new GrupoController();

    public void seleccionarCarrera() {
        System.out.println("Listado de Carreras:");
        List<Carrera> carreras = CarreraController.obtenerCarrerasStatic();
        if (carreras.isEmpty()) {
            System.out.println("No hay carreras registradas.");
            return;
        }
        for (Carrera c : carreras) {
            System.out.println(c.getIdCarrera() + ". " + c.getNombreCarrera());
        }
        System.out.print("Ingrese el ID de la carrera a seleccionar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        boolean existe = carreras.stream().anyMatch(c -> c.getIdCarrera() == id);
        if (existe) {
            idCarreraSeleccionada = id;
            System.out.println("Carrera seleccionada: " + idCarreraSeleccionada);
        } else {
            System.out.println("ID de carrera no válido.");
        }
    }

    public void seleccionarCiclo() {
        if (idCarreraSeleccionada == null) {
            System.out.println("Primero seleccione una carrera.");
            return;
        }
        System.out.println("Listado de Ciclos para la carrera seleccionada:");
        List<Ciclo> ciclos = CicloController.listarCiclosPorCarreraStatic(idCarreraSeleccionada);
        if (ciclos.isEmpty()) {
            System.out.println("No hay ciclos registrados para esta carrera.");
            return;
        }
        for (Ciclo c : ciclos) {
            System.out.println(c.getIdCiclo() + ". Año: " + c.getAnio() + ", Número: " + c.getNumero());
        }
        System.out.print("Ingrese el ID del ciclo a seleccionar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        boolean existe = ciclos.stream().anyMatch(c -> c.getIdCiclo() == id);
        if (existe) {
            idCicloSeleccionado = id;
            System.out.println("Ciclo seleccionado: " + idCicloSeleccionado);
        } else {
            System.out.println("ID de ciclo no válido.");
        }
    }

    public void listarCursos() {
        if (idCarreraSeleccionada == null || idCicloSeleccionado == null) {
            System.out.println("Primero seleccione carrera y ciclo.");
            return;
        }
        System.out.println("Listado de cursos para la carrera y ciclo seleccionados:");
        List<Curso> cursos = cursoController.listarCursosPorCarreraYCiclo(idCarreraSeleccionada, idCicloSeleccionado);
        if (cursos.isEmpty()) {
            System.out.println("No hay cursos registrados para esta carrera y ciclo.");
            return;
        }
        for (Curso c : cursos) {
            System.out.println(c.getIdCurso() + ". " + c.getNombre());
        }
    }

    public void seleccionarCurso() {
        if (idCarreraSeleccionada == null || idCicloSeleccionado == null) {
            System.out.println("Primero seleccione carrera y ciclo.");
            return;
        }
        System.out.print("Ingrese el ID del curso a seleccionar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        List<Curso> cursos = cursoController.listarCursosPorCarreraYCiclo(idCarreraSeleccionada, idCicloSeleccionado);
        boolean existe = cursos.stream().anyMatch(c -> c.getIdCurso() == id);
        if (existe) {
            idCursoSeleccionado = id;
            System.out.println("Curso seleccionado: " + idCursoSeleccionado);
        } else {
            System.out.println("ID de curso no válido.");
        }
    }

    public void listarGrupos() {
        if (idCarreraSeleccionada == null || idCicloSeleccionado == null || idCursoSeleccionado == null) {
            System.out.println("Primero seleccione carrera, ciclo y curso.");
            return;
        }
        System.out.println("Listado de grupos para la seleccion:");
        List<Grupo> grupos = grupoController.listarGruposPorCarreraCicloCurso(idCarreraSeleccionada, idCicloSeleccionado, idCursoSeleccionado);
        if (grupos.isEmpty()) {
            System.out.println("No hay grupos registrados para esta seleccion.");
            return;
        }
        for (Grupo g : grupos) {
            System.out.println("ID Grupo: " + g.getIdGrupo() + ", Numero: " + g.getNumeroGrupo() + ", Horario: " + g.getHorario());
        }
    }

    public void agregarGrupo() {
        if (idCarreraSeleccionada == null || idCicloSeleccionado == null || idCursoSeleccionado == null) {
            System.out.println("Primero seleccione carrera, ciclo y curso.");
            return;
        }
        System.out.println("Agregando nuevo grupo para la seleccion:");
        grupoController.crearGrupoConDatos(idCarreraSeleccionada, idCicloSeleccionado, idCursoSeleccionado);
    }

    public void modificarGrupo() {
        if (idCarreraSeleccionada == null || idCicloSeleccionado == null || idCursoSeleccionado == null) {
            System.out.println("Primero seleccione carrera, ciclo y curso.");
            return;
        }
        System.out.print("Ingrese el ID del grupo a modificar: ");
        int idGrupo = scanner.nextInt();
        scanner.nextLine();
        grupoController.modificarGrupoPorId(idGrupo);
    }
}
