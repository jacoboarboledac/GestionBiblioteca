package co.edu.uniquindio.GestionBiblioteca.model;

public abstract class Empleado {
    protected String nombre;
    protected String idEmpleado;

    public Empleado(String nombre, String idEmpleado) {
        this.nombre = nombre;
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
}
