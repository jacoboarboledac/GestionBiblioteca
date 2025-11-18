package co.edu.uniquindio.proyectofinal2.proyectofinal2.model;

import java.util.*;
import java.time.LocalDateTime;

public class EmpresaLogistica {
    private List<Usuario> usuarios;
    private List<Repartidor> repartidores;
    private List<Envio> envios;
    private List<Direccion> direcciones;
    private List<Tarifa> tarifas;
    private List<Pago> pagos;
    private List<ServicioAdicional> serviciosAdicionales;
    private List<Cliente> clientes;

    public EmpresaLogistica() {
        this.usuarios = new ArrayList<>();
        this.repartidores = new ArrayList<>();
        this.envios = new ArrayList<>();
        this.direcciones = new ArrayList<>();
        this.tarifas = new ArrayList<>();
        this.pagos = new ArrayList<>();
        this.serviciosAdicionales = new ArrayList<>();
        this.clientes = new ArrayList<>();

    }
    // Create
    public boolean registrarCliente(Cliente cliente) {
        if (clientes.stream().anyMatch(c -> c.getCorreoElectronico().equals(cliente.getCorreoElectronico()))) {
            return false;
        }
        clientes.add(cliente);
        return true;
    }

    // Read
    public Cliente buscarClientePorId(String idUsuario) {
        return clientes.stream()
                .filter(c -> c.getIdUsuario().equals(idUsuario))
                .findFirst()
                .orElse(null);
    }

    // Update
    public boolean actualizarCliente(Cliente clienteActualizado) {
        Cliente cliente = buscarClientePorId(clienteActualizado.getIdUsuario());
        if (cliente != null) {
            cliente.setNombre(clienteActualizado.getNombreCompleto());
            usuario.setCorreoElectronico(usuarioActualizado.getCorreoElectronico());
            usuario.setTelefono(usuarioActualizado.getTelefono());
            return true;
        }
        return false;
    }

    // Delete
    public boolean eliminarUsuario(String idUsuario) {
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        if (usuario != null) {
            usuarios.remove(usuario);
            return true;
        }
        return false;
    }

    // List
    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(usuarios);
    }

}

