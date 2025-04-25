package view;

import Controller.OfertaAcademicaController;
import java.util.Scanner;

public class menuOfertaAcademica {

    private static Scanner scanner = new Scanner(System.in);
    private static OfertaAcademicaController controller = new OfertaAcademicaController();

    public static void mostrarMenu() {
        while (true) {
            System.out.println("\n=== Menu de Ofertas Academicas ===");
            System.out.println("1. Seleccionar Carrera");
            System.out.println("2. Seleccionar Ciclo");
            System.out.println("3. Listar Cursos");
            System.out.println("4. Seleccionar Curso");
            System.out.println("5. Listar Grupos");
            System.out.println("6. Agregar Grupo");
            System.out.println("7. Modificar Grupo");
            System.out.println("8. Volver al menu principal");

            System.out.print("Seleccione una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    controller.seleccionarCarrera();
                    break;
                case 2:
                    controller.seleccionarCiclo();
                    break;
                case 3:
                    controller.listarCursos();
                    break;
                case 4:
                    controller.seleccionarCurso();
                    break;
                case 5:
                    controller.listarGrupos();
                    break;
                case 6:
                    controller.agregarGrupo();
                    break;
                case 7:
                    controller.modificarGrupo();
                    break;
                case 8:
                    System.out.println("Volviendo al menu principal...");
                    return;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }
}
