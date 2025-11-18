package co.edu.uniquindio.proyectofinal2.proyectofinal2.model;
import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {
    protected String idUsuario;
    protected String nombreCompleto;
    protected String correoElectronico;
    protected String telefono;

    public Usuario(String idUsuario, String nombreCompleto, String correoElectronico, String telefono) {
        this.idUsuario = idUsuario;
        this.nombreCompleto = nombreCompleto;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
    }

    // Getters y Setters
    public String getIdUsuario() { return idUsuario; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getCorreoElectronico() { return correoElectronico; }
    public String getTelefono() { return telefono; }

    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public String toString() {
        return "ID: " + idUsuario + ", Nombre: " + nombreCompleto;
    }
}
