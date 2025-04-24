package model;

public class Usuario1 {
    private int idPersona;
    private String nombreProfesor;
    private String cedula;
    private String correo;
    private String telefono;
    private String departamento;

    // Constructor
    public Usuario1(int idPersona, String nombreProfesor, String cedula, String correo, String telefono, String departamento) {
        this.idPersona = idPersona;
        this.nombreProfesor = nombreProfesor;
        this.cedula = cedula;
        this.correo = correo;
        this.telefono = telefono;
        this.departamento = departamento;
    }

    // Getters y Setters
    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
