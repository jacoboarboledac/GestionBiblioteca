package co.edu.uniquindio.GestionBiblioteca.model;

public class Libro {
    private final String titulo;
    private final String autor;
    private final String ISBN;
    private EstadoLibro estado;
    public Libro(String titulo, String autor, String ISBN) {
        this.titulo = titulo;
        this.autor = autor;
        this.ISBN = ISBN;
        this.estado = EstadoLibro.DISPONIBLE;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getAutor() {
        return autor;
    }
    public String getISBN() {
        return ISBN;
    }
    public EstadoLibro getEstado() {
        return estado;
    }
    public void setEstado(EstadoLibro estado) {
        this.estado = estado;
    }
    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", estado=" + estado +
                '}';
    }
}

