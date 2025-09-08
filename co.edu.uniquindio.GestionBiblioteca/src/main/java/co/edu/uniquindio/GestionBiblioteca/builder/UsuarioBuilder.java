package co.edu.uniquindio.GestionBiblioteca.builder;

import co.edu.uniquindio.GestionBiblioteca.model.Usuario;

public class UsuarioBuilder {
    private String nombre;
    private String idUsuario;
    public UsuarioBuilder setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }
    public UsuarioBuilder setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }
    public Usuario build() {
        // Validar campos obligatorios si es necesario
        return new Usuario(nombre, idUsuario);
    }
}
