package co.edu.uniquindio.proyectofinal.proyectofinal.model;

public class FactoryUsuario {
    public static Usuario crearUsuario(String tipoUsuario) {
        Usuario usuario = null;
        switch (tipoUsuario.toLowerCase()) {
            case "administrador":
                usuario = new Administrador();
                break;
            case "cliente":
                usuario = new Cliente();
                break;
            case "repartidor":
                usuario = new Repartidor();
                break;
        }
        return usuario;
    }
}
