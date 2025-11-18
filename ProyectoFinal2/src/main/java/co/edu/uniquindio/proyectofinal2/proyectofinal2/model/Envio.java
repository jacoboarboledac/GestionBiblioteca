package co.edu.uniquindio.proyectofinal2.proyectofinal2.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Envio {
    private String idEnvio;
    private Direccion origen;
    private Direccion destino;
    private double peso;
    private String dimensiones;
    private double costo;
    private EstadoEnvio estadoEnvio;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEstimadaEntrega;
    private Repartidor repartidor;
    private Usuario usuario;
    private List<ServicioAdicional> serviciosAdicionales;

    public Envio(String idEnvio, Direccion origen, Direccion destino, double peso, String dimensiones, double costo, Usuario usuario) {
        this.idEnvio = idEnvio;
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
        this.dimensiones = dimensiones;
        this.costo = costo;
        this.estadoEnvio = EstadoEnvio.SOLICITADO;
        this.fechaCreacion = LocalDateTime.now();
        this.usuario = usuario;
        this.serviciosAdicionales = new ArrayList<>();
    }

    // Getters y Setters
    public String getIdEnvio() { return idEnvio; }
    public Direccion getOrigen() { return origen; }
    public Direccion getDestino() { return destino; }
    public double getPeso() { return peso; }
    public String getDimensiones() { return dimensiones; }
    public double getCosto() { return costo; }
    public EstadoEnvio getEstadoEnvio() { return estadoEnvio; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public LocalDateTime getFechaEstimadaEntrega() { return fechaEstimadaEntrega; }
    public Repartidor getRepartidor() { return repartidor; }
    public Usuario getUsuario() { return usuario; }
    public List<ServicioAdicional> getServiciosAdicionales() { return serviciosAdicionales; }

    public void setEstadoEnvio(EstadoEnvio estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    public void setFechaEstimadaEntrega(LocalDateTime fechaEstimadaEntrega) {
        this.fechaEstimadaEntrega = fechaEstimadaEntrega;
    }

    public void agregarServicioAdicional(ServicioAdicional servicio) {
        serviciosAdicionales.add(servicio);
    }
}



