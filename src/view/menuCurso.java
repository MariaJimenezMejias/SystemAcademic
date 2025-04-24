package view;

import Controller.CursoController;
import model.Curso;
import java.util.List;
import java.util.Scanner;

public class menuCurso {

    public static void menuCurso() {
        Scanner scanner = new Scanner(System.in);
        CursoController controller = new CursoController();

        while (true) {
            System.out.println("\n=== Menu de Cursos ===");
            System.out.println("1. Crear curso");
            System.out.println("2. Listar cursos");
            System.out.println("3. Obtener curso por ID");
            System.out.println("4. Actualizar curso");
            System.out.println("5. Eliminar curso");
            System.out.println("6. Volver");

            System.out.print("Elija una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del curso: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Creditos: ");
                    int creditos = scanner.nextInt();
                    System.out.print("Horas semanales: ");
                    int horas = scanner.nextInt();
                    System.out.print("ID del ciclo: ");
                    int idCiclo = scanner.nextInt();

                    boolean creado = controller.agregarCurso(nombre, creditos, horas, idCiclo);
                    if (creado) {
                        System.out.println("Curso creado exitosamente.");
                    } else {
                        System.out.println("Error al crear el curso.");
                    }
                    break;

                case 2:
                    List<Curso> cursos = controller.listarCursos();
                    if (cursos.isEmpty()) {
                        System.out.println("No hay cursos registrados.");
                    } else {
                        for (Curso curso : cursos) {
                            System.out.println("ID: " + curso.getIdCurso() +
                                               " | Nombre: " + curso.getNombre() +
                                               " | Creditos: " + curso.getCreditos() +
                                               " | Horas: " + curso.getHorasSemanales() +
                                               " | ID Ciclo: " + curso.getIdCiclo());
                        }
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el ID del curso: ");
                    int idBuscar = scanner.nextInt();
                    Curso encontrado = controller.buscarCurso(idBuscar);
                    if (encontrado != null) {
                        System.out.println("Curso encontrado:");
                        System.out.println("ID: " + encontrado.getIdCurso());
                        System.out.println("Nombre: " + encontrado.getNombre());
                        System.out.println("Creditos: " + encontrado.getCreditos());
                        System.out.println("Horas: " + encontrado.getHorasSemanales());
                        System.out.println("ID Ciclo: " + encontrado.getIdCiclo());
                    } else {
                        System.out.println("Curso no encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("ID del curso a actualizar: ");
                    int idActualizar = scanner.nextInt();
                    scanner.nextLine(); // limpiar buffer
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = scanner.nextLine();
                    System.out.print("Nuevos creditos: ");
                    int nuevosCreditos = scanner.nextInt();
                    System.out.print("Nuevas horas semanales: ");
                    int nuevasHoras = scanner.nextInt();
                    System.out.print("Nuevo ID de ciclo: ");
                    int nuevoIdCiclo = scanner.nextInt();

                    boolean actualizado = controller.actualizarCurso(idActualizar, nuevoNombre, nuevosCreditos, nuevasHoras, nuevoIdCiclo);
                    if (actualizado) {
                        System.out.println("Curso actualizado correctamente.");
                    } else {
                        System.out.println("Error al actualizar el curso.");
                    }
                    break;

                case 5:
                    System.out.print("ID del curso a eliminar: ");
                    int idEliminar = scanner.nextInt();
                    boolean eliminado = controller.eliminarCurso(idEliminar);
                    if (eliminado) {
                        System.out.println("Curso eliminado correctamente.");
                    } else {
                        System.out.println("Error al eliminar el curso.");
                    }
                    break;

                case 6:
                    System.out.println("Volviendo al menu principal...");
                    return;

                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }
}
