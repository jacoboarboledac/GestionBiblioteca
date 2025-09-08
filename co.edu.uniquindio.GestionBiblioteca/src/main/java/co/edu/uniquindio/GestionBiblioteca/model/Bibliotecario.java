package co.edu.uniquindio.GestionBiblioteca.model;

import co.edu.uniquindio.GestionBiblioteca.services.GestionInventario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bibliotecario extends Empleado implements GestionInventario {
    private static Bibliotecario instancia;
    private final List<Prestamo> prestamosGestionados;
    public final List<Libro> inventarioLibros;
    private Bibliotecario(String nombre, String idEmpleado) {
        super(nombre, idEmpleado);
        prestamosGestionados = new ArrayList<>();
        inventarioLibros = new ArrayList<>();
    }
    public static synchronized Bibliotecario getInstancia(String nombre, String idEmpleado) {
        if (instancia == null) {
            instancia = new Bibliotecario(nombre, idEmpleado);
        }
        return instancia;
    }
    public void gestionarPréstamos(Prestamo prestamo) {
        if (prestamo.getLibro().getEstado() == EstadoLibro.DISPONIBLE) {
            prestamosGestionados.add(prestamo);
            prestamo.getLibro().setEstado(EstadoLibro.PRESTADO);
            prestamo.getUsuario().agregarPrestamo(prestamo);
            System.out.println("Préstamo gestionado: " + prestamo);
        } else {
            System.out.println("El libro '" + prestamo.getLibro().getTitulo() + "' no está disponible.");
        }
    }
    public void gestionarDevolucion(Prestamo prestamo, Date fechaDevolucion) {
        if (prestamosGestionados.contains(prestamo) && prestamo.getFechaDevolucion() == null) {
            prestamo.devolverLibro(fechaDevolucion);
            System.out.println("Devolución gestionada: " + prestamo);
        } else {
            System.out.println("Préstamo no válido o ya devuelto.");
        }
    }
    public void eliminarLibro(Libro libro) {
        if (inventarioLibros.remove(libro)) {
            System.out.println("Libro eliminado del inventario: " + libro.getTitulo());
        } else {
            System.out.println("El libro no se encontró en el inventario: " + libro.getTitulo());
        }
    }
    public void listarLibrosDisponibles() {
        System.out.println("Libros disponibles en inventario:");
        for (Libro libro : inventarioLibros) {
            if (libro.getEstado() == EstadoLibro.DISPONIBLE) {
                System.out.println(libro);
            }
        }
    }

    @Override
    public void gestionarItem(Libro libro) {
        System.out.println("Gestionando libro: " + libro.getTitulo());
    }
}
