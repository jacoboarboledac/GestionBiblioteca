package co.edu.uniquindio.GestionBiblioteca.builder;

import co.edu.uniquindio.GestionBiblioteca.model.Libro;

public class LibroBuilder {
    private String titulo;
    private String autor;
    private String ISBN;
    public LibroBuilder setTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }
    public LibroBuilder setAutor(String autor) {
        this.autor = autor;
        return this;
    }
    public LibroBuilder setISBN(String ISBN) {
        this.ISBN = ISBN;
        return this;
    }
    public Libro build() {
        // Validar campos obligatorios si es necesario
        return new Libro(titulo, autor, ISBN);
    }
}
