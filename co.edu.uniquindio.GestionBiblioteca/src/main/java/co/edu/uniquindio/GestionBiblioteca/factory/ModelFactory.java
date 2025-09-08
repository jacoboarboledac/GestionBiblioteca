package co.edu.uniquindio.GestionBiblioteca.factory;

import co.edu.uniquindio.GestionBiblioteca.builder.LibroBuilder;
import co.edu.uniquindio.GestionBiblioteca.builder.UsuarioBuilder;

import co.edu.uniquindio.GestionBiblioteca.model.Libro;
import co.edu.uniquindio.GestionBiblioteca.model.Prestamo;
import co.edu.uniquindio.GestionBiblioteca.model.Usuario;


import java.util.*;


// Clase ModelFactory para crear instancias de modelos
public class ModelFactory {

    public static Libro crearLibro(String titulo, String autor, String ISBN) {
        return new LibroBuilder()
                .setTitulo(titulo)
                .setAutor(autor)
                .setISBN(ISBN)
                .build();
    }
    public static Usuario crearUsuario(String nombre, String idUsuario) {
        return new UsuarioBuilder()
                .setNombre(nombre)
                .setIdUsuario(idUsuario)
                .build();
    }
    public static Prestamo crearPrestamo(Libro libro, Usuario usuario) {
        return new Prestamo(libro, usuario, new Date());
    }
}