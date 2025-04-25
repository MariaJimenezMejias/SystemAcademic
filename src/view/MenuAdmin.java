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
              System.out.println("6. Mantenimiento de grupos");
              
            System.out.println("7. Matricula");
            System.out.println("8. Consultar historial");
            System.out.println("9. Salir");

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
                   
                    AlumnoController alumnoController = new AlumnoController();
                    alumnoController.buscarAlumno();
                   
                    break;

                case 4:
                    menuProfesor menuProfesor = new menuProfesor();
                    menuProfesor.mostrarMenu();
                    
                case 5:
                    MenuCiclo menuCiclo = new MenuCiclo();
                    menuCiclo.menuCiclo();
                            
                    break;
                    case 7:
                      MatriculaController matricula = new MatriculaController();
                      matricula.matricularAlumno();
                    
                    return;
                    
                      case 8:
                      menuHistorialAdmin menuHistorialAdmin = new menuHistorialAdmin();
                      menuHistorialAdmin.mostrarMenu();
                    return;
                    
                    case 9:
                    System.out.println("Saliendo del menu de administracion...");
                    System.exit(-1);
                    break;
                    
                     case 6:
                     menuGrupo menuGrupo = new menuGrupo();
                      menuGrupo.menuGrupo();
                    



                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }
}
