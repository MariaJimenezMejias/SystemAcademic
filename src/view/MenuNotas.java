package view;

import model.Nota;
import Controller.LoginController;
import java.util.Scanner;
import view.menuProfesor;
public class MenuNotas {

    private Nota nota;

    public MenuNotas() {
        this.nota = new Nota(); // Crear una instancia de la clase Nota
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true; 
        while (continuar) {
            // Primero, listamos los grupos del profesor con el idProfesor predeterminado
            System.out.println("=== Menu Notas ===");
            int idProfesor = LoginController.idRelacionado; // Aqui usaremos el idProfesor de forma predeterminada
            System.out.println("Listando los grupos del Profesor con ID: " + idProfesor);
            nota.listarGruposPorProfesor(idProfesor);

            // Luego, pedimos al usuario que elija el grupo
            System.out.print("Introduce el ID del Grupo para ver los estudiantes: ");
            int idGrupo = scanner.nextInt();

            // Luego, listamos los estudiantes para el profesor y el grupo seleccionados
            nota.listarEstudiantesPorProfesorYGrupo(idProfesor, idGrupo);

            // Ahora pedimos al usuario que ingrese los datos para la insercion de la nota
            System.out.print("Introduce el ID del Alumno: ");
            int idAlumno = scanner.nextInt();

            System.out.print("Introduce la nota: ");
            float notaValor = scanner.nextFloat();

            System.out.print("Introduce la fecha de registro (formato: yyyy-MM-dd): ");
            String fechaRegistro = scanner.next();

            // Insertar la nota
            nota.insertarNota(idAlumno, idProfesor, notaValor, fechaRegistro, idGrupo);

            // Confirmacion de la insercion
            System.out.println("La nota se ha insertado correctamente.");

            // Preguntar si el usuario desea insertar otra nota
            System.out.print("Deseas insertar otra nota? (S/N): ");
            String respuesta = scanner.next();
            if (respuesta.equalsIgnoreCase("N")) {
                continuar = false; // Salir del bucle si la respuesta es 'N'
            }
        }
       
      
        System.out.println("Volviendo al menu principal...");
    }
}
