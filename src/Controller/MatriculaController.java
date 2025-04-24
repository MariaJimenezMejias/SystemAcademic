/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author maria
 */

import static Controller.LoginController.idRelacionado;
import static Controller.LoginController.tipoUsuario;
import model.Matricula;
import java.util.Scanner;
import DAO.alumnoDAO;
import DAO.matriculadorDAO;
import DAO.administradorDAO;

import DAO.profesorDAO;
import DAO.matriculadorDAO;

public class MatriculaController {
    static Scanner scanner = new Scanner(System.in);

    public static void matricularAlumno() {
        // Solicitar cédula del alumno
        System.out.println("Ingrese la cédula del Alumno:");
        String cedulaAlumno = scanner.nextLine();
        int idAlumno = alumnoDAO.obtenerIdAlumnoPorCedula(cedulaAlumno);

        if (idAlumno == -1) {
            System.out.println("❌ Alumno no encontrado.");
            return;
        }

        // Solicitar ID de la carrera
        System.out.println("Ingrese el ID de la Carrera:");
        int idCarrera = scanner.nextInt();

        // Llamamos al modelo para insertar la matrícula en la tabla MatriculaCarrera
        Matricula.insertarMatricula(idAlumno, idCarrera);
    }
}
