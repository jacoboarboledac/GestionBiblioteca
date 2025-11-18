package co.edu.uniquindio.proyectofinal.proyectofinal.model;

public abstract class Usuario {
    protected String nombre;
    protected String numTelefono;
    protected String contrasenia;

public Usuario(String nombre, String numTelefono, String contrasenia) {
    this.nombre = nombre;
    this.numTelefono = numTelefono;
    this.contrasenia = contrasenia;
}
public Usuario() {}
public String getNombre() {
    return nombre;
    }
public void setNombre(String nombre) {
    this.nombre = nombre;
    }
public String getNumTelefono() {
    return numTelefono;
    }
public void setNumTelefono(String numTelefono) {
    this.numTelefono = numTelefono;
    }
    public String getContrasenia() {
    return contrasenia;
    }
    public void setContrasenia(String contrasenia) {
    this.contrasenia = contrasenia;
    }
}

