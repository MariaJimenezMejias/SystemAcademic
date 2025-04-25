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
            System.out.println("7. Obtener curso por ID de curso");
            System.out.println("8. Listar cursos por nombre");
            System.out.println("9. Volver al menu principal");

            System.out.print("Seleccione una opcion: ");
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
                    System.out.println("Listar cursos por carrera");
                    System.out.print("Escriba el nombre de la carrera: ");
                    String nombreCarrera = scanner.nextLine();  // Capturamos el nombre de la carrera
                    controller.listarCursosPorCarrera(nombreCarrera);  // Llamamos al controlador para listar los cursos de la carrera
                    break;

                case 7:
                    System.out.print("Ingrese el ID del curso: ");
                    int idCursoBusqueda = scanner.nextInt();  // Capturamos el ID del curso
                    controller.listarCursosPorIdCurso(idCursoBusqueda);  // Llamamos al controlador para listar el curso por ID
                    break;

                case 8:
                    System.out.print("Ingrese el nombre del curso: ");
                    String nombreCursoBusqueda = scanner.nextLine();  // Capturamos el nombre del curso
                    controller.listarCursosPorNombre(nombreCursoBusqueda);  // Llamamos al controlador para listar los cursos por nombre
                    break;

                case 9:
                    System.out.println("Volviendo al menu principal...");
                    
                    return;

                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }
    
    
       public static void menuCurso1(int idCurso) {
    Scanner scanner = new Scanner(System.in);
    CursoController controller = new CursoController();

    while (true) {
        System.out.println("\n=== Menu de Cursos ===");
        System.out.println("1. Actualizar curso");
        System.out.println("2. Eliminar curso");
        System.out.println("3. Volver al menu anterior");

        System.out.print("Seleccione una opcion: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();  // Limpiar buffer

        switch (opcion) {
            case 1:
                // Obtener la información actual del curso y actualizarla
                System.out.print("Nuevo nombre: ");
                String nuevoNombre = scanner.nextLine();
                System.out.print("Nuevos creditos: ");
                int nuevosCreditos = scanner.nextInt();
                System.out.print("Nuevas horas semanales: ");
                int nuevasHoras = scanner.nextInt();
                System.out.print("Nuevo ID de ciclo: ");
                int nuevoIdCiclo = scanner.nextInt();

                // Llamamos al controlador para actualizar el curso
                boolean actualizado = controller.actualizarCurso(idCurso, nuevoNombre, nuevosCreditos, nuevasHoras, nuevoIdCiclo);
                if (actualizado) {
                    System.out.println("Curso actualizado correctamente.");
                } else {
                    System.out.println("Error al actualizar el curso.");
                }
                break;

            case 2:
                // Eliminar el curso con el ID proporcionado
                boolean eliminado = controller.eliminarCurso(idCurso);
                if (eliminado) {
                    System.out.println("Curso eliminado correctamente.");
                } else {
                    System.out.println("Error al eliminar el curso.");
                }
                break;

            case 3:
                System.out.println("Volviendo al menu anterior...");
                return;  // Vuelve al menú anterior

            default:
                System.out.println("Opcion no valida.");
        }
    }
}

}
