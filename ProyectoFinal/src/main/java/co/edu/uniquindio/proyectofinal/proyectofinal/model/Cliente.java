package co.edu.uniquindio.proyectofinal.proyectofinal.model;

import java.util.LinkedList;
import java.util.List;

public class Cliente extends Usuario{
    private String idCliente;
    private String correo;
    LinkedList<Envio> envios;
    List<?> direccionesFrecuentes;
    public Cliente(String idCliente, String correo, String nombre, String numTelefono) {
        super(nombre, numTelefono);
        this.idCliente = idCliente;
        this.correo = correo;
        envios = new LinkedList<>();
    }
    public String getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String Correo) {
        this.correo = Correo;
    }

    public LinkedList<Envio> getEnvios() {
        return envios;
    }

    public void setEnvios(LinkedList<Envio> envios) {
        this.envios = envios;
    }

    public boolean agregarServiciosEnvio(String idEnvioActualizar, Prioridad prioridad,
                                         ServicioAdicional servicioAdicional) {

        for (Envio envio : envios) {
            if (envio.getIdEnvio().equals(idEnvioActualizar)) {
                envio.setPrioridad(prioridad);
                envio.setServicioAdicional(servicioAdicional);
                return true;
            }

        }
        return false;
    }

    // 1. Registrarse (crear cliente)
    public static Cliente registrarse(String idCliente, String nombre, String correo, String telefono) {
        return new Cliente(idCliente, nombre, correo, telefono);
    }

    // 2. Gestionar perfil
    public boolean actualizarPerfil(String nuevoNombre, String nuevoCorreo, String nuevoTelefono, List<String> nuevasDirecciones) {
        this.nombre = nuevoNombre;
        this.correo = nuevoCorreo;
        this.numTelefono = nuevoTelefono;
        this.direccionesFrecuentes = nuevasDirecciones != null ? nuevasDirecciones : this.direccionesFrecuentes;
        return true;
    }

    // 8. Consultar historial de envíos con filtros
    public List<Envio> consultarHistorialEnvios(String fecha, EstadoEnvio estado) {
        return envios.stream()
                .filter(envio -> (fecha == null || envio.getFechaCreacion().equals(fecha)))
                .filter(envio -> (estado == null || envio.getEstadoEnvio() == estado))
                .toList();
    }
}
