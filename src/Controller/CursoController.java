/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author maria
 */

import DAO.CursoDAO;
import model.Curso;

import java.util.List;
import java.util.Scanner;
import model.Curso;





public class CursoController {
   public boolean agregarCurso(String nombre, int creditos, int horasSemanales, int idCiclo) {
    // Verifica los parámetros antes de crear el objeto Curso
    if (nombre == null || nombre.isEmpty() || creditos <= 0 || horasSemanales <= 0 || idCiclo <= 0) {
        System.out.println("Los valores proporcionados no son válidos.");
        return false;
    }
    
    Curso curso = new Curso(nombre, creditos, horasSemanales, idCiclo);
    return CursoDAO.insertarCurso(curso);
}

    public List<Curso> listarCursos() {
        return CursoDAO.obtenerCursos();
    }

    public Curso buscarCurso(int idCurso) {
        return CursoDAO.obtenerCursoPorId(idCurso);
    }

    public boolean actualizarCurso(int idCurso, String nombre, int creditos, int horasSemanales, int idCiclo) {
        Curso curso = new Curso(idCurso, nombre, creditos, horasSemanales, idCiclo);
        return CursoDAO.actualizarCurso(curso);
    }

    public boolean eliminarCurso(int idCurso) {
        return CursoDAO.eliminarCurso(idCurso);
       
    }
    
    public List<Curso> listarCursosPorCarrera(String nombreCarrera) {
    return CursoDAO.obtenerCursosPorCarrera(nombreCarrera);
    
    
    
}
    
    public void listarCursosPorIdCurso(int idCurso) {
        // Aquí puedes hacer la consulta a la base de datos para obtener el curso por ID
        Curso curso = CursoDAO.obtenerCursoPorId(idCurso);

        if (curso == null) {
            System.out.println("No se encontró el curso con ID: " + idCurso);
        } else {
            System.out.println("Curso encontrado:");
            System.out.println("ID: " + curso.getIdCurso());
            System.out.println("Nombre: " + curso.getNombre());
            System.out.println("Créditos: " + curso.getCreditos());
            System.out.println("Horas: " + curso.getHorasSemanales());
            System.out.println("ID Ciclo: " + curso.getIdCiclo());
        }
    }
    

    public void listarCursosPorNombre(String nombreCurso) {
        // Llamar al DAO para obtener los cursos que coincidan con el nombre
        List<Curso> cursos = CursoDAO.obtenerCursosPorNombre(nombreCurso);

        if (cursos.isEmpty()) {
            System.out.println("No se encontraron cursos con el nombre: " + nombreCurso);
        } else {
            System.out.println("Cursos encontrados:");
            for (Curso curso : cursos) {
                System.out.println("ID: " + curso.getIdCurso() +
                                   " | Nombre: " + curso.getNombre() +
                                   " | Creditos: " + curso.getCreditos() +
                                   " | Horas: " + curso.getHorasSemanales() +
                                   " | ID Ciclo: " + curso.getIdCiclo());
            }
        }
    }
}
