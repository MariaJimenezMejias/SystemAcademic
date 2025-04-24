package Controller;

import DAO.PersonaDAO;
import model.Persona;

public class PersonaController {

    public PersonaDAO personaDAO;

    // Constructor
    public PersonaController() {
        // Inicializa el objeto PersonaDAO para usarlo en el controlador
        personaDAO = new PersonaDAO();
    }

    // Método para registrar una persona
    public void registrarPersona(String cedula, String nombre, String telefono, String direccion, String correo, String usuario) {
        // Crear el objeto Persona con los datos proporcionados
        Persona persona = new Persona();
        persona.setCedula(cedula);
        persona.setNombre(nombre);
        persona.setTelefono(telefono);
        persona.setDireccion(direccion);
        persona.setCorreo(correo);
        persona.setUsuario(usuario);

        // Llamar al método insertarPersona de PersonaDAO para registrar la persona
        personaDAO.insertarPersona(persona);

        // Mostrar mensaje con el nombre de usuario

    }
}
