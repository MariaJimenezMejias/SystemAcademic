package model;


public class Grupo {
    private int idGrupo;
    private int numeroGrupo;
    private String horario;
    private int idProfesor;
    private int idCiclo;
    private int idCarrera;
    private int idCurso;

    // Getters y Setters
    public int getIdGrupo() { return idGrupo; }
    public void setIdGrupo(int idGrupo) { this.idGrupo = idGrupo; }

    public int getNumeroGrupo() { return numeroGrupo; }
    public void setNumeroGrupo(int numeroGrupo) { this.numeroGrupo = numeroGrupo; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public int getIdProfesor() { return idProfesor; }
    public void setIdProfesor(int idProfesor) { this.idProfesor = idProfesor; }

    public int getIdCiclo() { return idCiclo; }
    public void setIdCiclo(int idCiclo) { this.idCiclo = idCiclo; }

    public int getIdCarrera() { return idCarrera; }
    public void setIdCarrera(int idCarrera) { this.idCarrera = idCarrera; }

    public int getIdCurso() { return idCurso; }
    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }
}
