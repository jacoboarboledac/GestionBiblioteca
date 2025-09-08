package co.edu.uniquindio.GestionBiblioteca.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Usuario {
    private final String nombre;
    private final String idUsuario;
    private final List<Prestamo> prestamosActivos;
    public Usuario(String nombre, String idUsuario) {
        this.nombre = nombre;
        this.idUsuario = idUsuario;
        this.prestamosActivos = new ArrayList<>();
    }
    public String getNombre() {
        return nombre;
    }
    public String getIdUsuario() {
        return idUsuario;
    }
    public void agregarPrestamo(Prestamo prestamo) {
        prestamosActivos.add(prestamo);
    }
    public void eliminarPrestamo(Prestamo prestamo) {
        prestamosActivos.remove(prestamo);
    }
    public List<Prestamo> getPrestamosActivos() {
        return Collections.unmodifiableList(prestamosActivos);
    }
    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", idUsuario='" + idUsuario + '\'' +
                ", prestamosActivos=" + prestamosActivos.size() +
                '}';
    }
}

