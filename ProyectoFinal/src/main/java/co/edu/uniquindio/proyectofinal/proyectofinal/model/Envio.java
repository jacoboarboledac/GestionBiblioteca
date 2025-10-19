package co.edu.uniquindio.proyectofinal.proyectofinal.model;

import co.edu.uniquindio.proyectofinal.proyectofinal.services.IEnvioComponente;
import java.util.LinkedList;
import java.util.List;

public class Envio implements IEnvioComponente {
    private String idEnvio;
    private String direccionOrigen;
    private String direccionDestino;
    private double costoBase;  // Cambié a double para cálculos
    private String fechaCreacion;
    private String fechaEntrega;
    private Prioridad prioridad;
    LinkedList<Cliente> clientes;
    private ServicioAdicional servicioAdicional;

    public Envio(String idEnvio, String direccionOrigen, String direccionDestino, double costoBase, String fechaCreacion, String fechaEntrega) {
        this.idEnvio = idEnvio;
        this.direccionOrigen = direccionOrigen;
        this.direccionDestino = direccionDestino;
        this.costoBase = costoBase;
        this.fechaCreacion = fechaCreacion;
        this.fechaEntrega = fechaEntrega;
    }

    @Override
    public double calcularCosto() {
        return costoBase;  // Costo base inicial
    }

    @Override
    public String getDescripcion() {
        return "Envío ID: " + idEnvio + " de " + direccionOrigen + " a " + direccionDestino;
    }

    // Getters y setters existentes (sin cambios mayores)
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
    public void setPrioridad(Prioridad prioridad) { this.prioridad = prioridad; }
    public ServicioAdicional getServicioAdicional() { return servicioAdicional; }
    public void setServicioAdicional(ServicioAdicional servicioAdicional) { this.servicioAdicional = servicioAdicional; }
    public List<Cliente> getCliente() { return clientes; }
}
