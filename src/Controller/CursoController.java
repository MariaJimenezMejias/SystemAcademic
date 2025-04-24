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
    
    public List<Curso> buscarPorNombre(String nombre) {
    return CursoDAO.buscarPorNombre(nombre);
    }
    
    public Curso buscarPorCodigo(String codigo) {
    return CursoDAO.buscarPorCodigo(codigo);
    }
    
public List<Curso> buscarPorCarrera(String nombreCarrera) {
    return CursoDAO.buscarPorCarrera(nombreCarrera);
    }
}
