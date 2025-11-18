package co.edu.uniquindio.proyectofinal.proyectofinal.dto;


import co.edu.uniquindio.proyectofinal.proyectofinal.model.EstadoEnvio;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Prioridad;

public class EnvioDTO {
    private String idEnvio;
    private String origen;
    private String destino;
    private String fechaCreacion;
    private String fechaEntrega;
    private EstadoEnvio estadoEnvio;
    private double costo;
    private Prioridad prioridad;
    private String idRepartidor;
    private String idCliente;


    public EnvioDTO() {}

    public EnvioDTO(String idEnvio, String origen, String destino, String fechaCreacion,
                    String fechaEntrega, EstadoEnvio estadoEnvio, double costo,
                    Prioridad prioridad, String idRepartidor, String idCliente) {
        this.idEnvio = idEnvio;
        this.origen = origen;
        this.destino = destino;
        this.fechaCreacion = fechaCreacion;
        this.fechaEntrega = fechaEntrega;
        this.estadoEnvio = estadoEnvio;
        this.costo = costo;
        this.prioridad = prioridad;
        this.idRepartidor = idRepartidor;
        this.idCliente = idCliente;
    }

    // Getters y setters
    public String getIdEnvio() { return idEnvio; }
    public void setIdEnvio(String idEnvio) { this.idEnvio = idEnvio; }
    public String getOrigen() { return origen; }
    public void setOrigen(String origen) { this.origen = origen; }
    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }
    public String getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(String fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public String getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(String fechaEntrega) { this.fechaEntrega = fechaEntrega; }
    public EstadoEnvio getEstadoEnvio() { return estadoEnvio; }
    public void setEstadoEnvio(EstadoEnvio estadoEnvio) { this.estadoEnvio = estadoEnvio; }
    public double getCosto() { return costo; }
    public void setCosto(double costo) { this.costo = costo; }
    public Prioridad getPrioridad() { return prioridad; }
    public void setPrioridad(Prioridad prioridad) { this.prioridad = prioridad; }
    public String getIdRepartidor() { return idRepartidor; }
    public void setIdRepartidor(String idRepartidor) { this.idRepartidor = idRepartidor; }
    public String getIdCliente() { return idCliente; }
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }
}
