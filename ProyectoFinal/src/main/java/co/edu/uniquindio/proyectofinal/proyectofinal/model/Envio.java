package co.edu.uniquindio.proyectofinal.proyectofinal.model;

import co.edu.uniquindio.proyectofinal.proyectofinal.services.IEnvioComponente;
import java.util.LinkedList;
import java.util.List;

public class Envio implements IEnvioComponente {
    private String idEnvio;
    private String direccionOrigen;
    private String direccionDestino;
    private double costoBase;
    private String fechaCreacion;
    private String fechaEntrega;
    private Prioridad prioridad;
    private String idRepartidorAsignado;
    private String zonaEnvio;
    LinkedList<Cliente> clientes;
    private ServicioAdicional servicioAdicional;
    private EstadoEnvio estadoEnvio;

    public Envio(String idEnvio, String direccionOrigen, String direccionDestino, double costoBase, String fechaCreacion, String fechaEntrega, EstadoEnvio idRepartidorAsignado, EstadoEnvio estadoEnvio, String zonaEnvio) {
        this.idEnvio = idEnvio;
        this.direccionOrigen = direccionOrigen;
        this.direccionDestino = direccionDestino;
        this.costoBase = costoBase;
        this.fechaCreacion = fechaCreacion;
        this.fechaEntrega = fechaEntrega;
        this.idRepartidorAsignado = idRepartidorAsignado;
        this.estadoEnvio = estadoEnvio;
        this.zonaEnvio = zonaEnvio;
    }

    @Override
    public double calcularCosto() {
        return costoBase;
    }

    @Override
    public String getDescripcion() {
        return "Envío ID: " + idEnvio + " de " + direccionOrigen + " a " + direccionDestino;
    }


    public String getIdEnvio() { return idEnvio; }
    public void setIdEnvio(String idEnvio) { this.idEnvio = idEnvio; }
    public String getDireccionOrigen() { return direccionOrigen; }
    public void setDireccionOrigen(String direccionOrigen) { this.direccionOrigen = direccionOrigen; }
    public String getDireccionDestino() { return direccionDestino; }
    public void setDireccionDestino(String direccionDestino) { this.direccionDestino = direccionDestino; }
    public double getCostoBase() { return costoBase; }
    public void setCostoBase(double costoBase) { this.costoBase = costoBase; }
    public String getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(String fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public String getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(String fechaEntrega) { this.fechaEntrega = fechaEntrega; }
    public Prioridad getPrioridad() { return prioridad; }
    public String getIdRepartidorAsignado() { return idRepartidorAsignado; }
    public void setIdRepartidorAsignado(String idRepartidorAsignado) { this.idRepartidorAsignado = idRepartidorAsignado; }
    public void setPrioridad(Prioridad prioridad) { this.prioridad = prioridad; }
    public ServicioAdicional getServicioAdicional() { return servicioAdicional; }
    public void setServicioAdicional(ServicioAdicional servicioAdicional) { this.servicioAdicional = servicioAdicional; }
    public EstadoEnvio getEstadoEnvio() { return estadoEnvio; }
    public void setEstadoEnvio(EstadoEnvio estadoEnvio) { this.estadoEnvio = estadoEnvio; }
    public String getZonaEnvio() { return zonaEnvio; }
    public void setZonaEnvio(String zonaEnvio) { this.zonaEnvio = zonaEnvio; }
    public List<Cliente> getCliente() { return clientes; }

}
