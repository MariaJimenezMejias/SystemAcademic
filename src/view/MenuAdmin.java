/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author maria
 */
import java.util.Scanner;
import Controller.MatriculaController;
import Controller.UsuarioController;
import Controller.AlumnoController;
import Controller.ProfesorController;
public class MenuAdmin {

    public static void menuAdmin() {
        Scanner scanner = new Scanner(System.in);
        MenuPersona menuPersona = new MenuPersona();
        UsuarioController usuarioController = new UsuarioController();
        ProfesorController profesorController = new ProfesorController();
        while (true) {
            System.out.println("\n=== Menu de Administracion ===");
            System.out.println("1. Trabajar con cursos");
            System.out.println("2. Trabajar con carreras");
            System.out.println("3. Mantenimiento de alumnos");
            System.out.println("4. Mantenimiento de profesores");
            System.out.println("5. Mantenimiento de ciclos");
            System.out.println("6. Matricula");
            System.out.println("7. Salir");

            System.out.print("Elija una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    // Ver usuarios
                    System.out.println("Mostrando menu de cursos");
                    menuCurso.menuCurso();
                    break;

                case 2:
                    menuCarrera menuCarrera = new menuCarrera();
                    menuCarrera.mostrarMenu();
                    break;

                case 3:
                   
                     menuPersona.mostrarMenu();
                     usuarioController.registrarUsuario();
                     AlumnoController alumnoController = new AlumnoController();
                     alumnoController.registrarAlumnoDesdeConsola();
                   
                    break;

                case 4:
                    menuProfesor menuProfesor = new menuProfesor();
                    menuProfesor.mostrarMenu();
                    
                case 5:
                    MenuCiclo menuCiclo = new MenuCiclo();
                    menuCiclo.menuCiclo();
                            
                    break;
                    case 6:
                      MatriculaController matricula = new MatriculaController();
                      matricula.matricularAlumno();
                    
                    return;
                    case 7:
                    System.out.println("Saliendo del menu de administracion...");
                    return;


                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }
}
