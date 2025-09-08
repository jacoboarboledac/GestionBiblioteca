package co.edu.uniquindio.GestionBiblioteca.model;

import java.util.Date;

public class Prestamo {
    private Libro libro;
    private Usuario usuario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;

public Prestamo(Libro libro, Usuario usuario, Date fechaPrestamo) {
    this.libro = libro;
    this.usuario = usuario;
    this.fechaPrestamo = fechaPrestamo;
    this.fechaDevolucion = null;
}
public void devolverLibro(Date fechaDevolucion) {
    this.fechaDevolucion = fechaDevolucion;
    libro.setEstado(EstadoLibro.DISPONIBLE);
    usuario.eliminarPrestamo(this);
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
