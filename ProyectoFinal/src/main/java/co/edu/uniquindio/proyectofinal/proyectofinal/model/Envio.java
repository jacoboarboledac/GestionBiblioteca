package co.edu.uniquindio.proyectofinal.proyectofinal.model;

import co.edu.uniquindio.proyectofinal.proyectofinal.decorator.FirmaDecorator;
import co.edu.uniquindio.proyectofinal.proyectofinal.decorator.FragilDecorator;
import co.edu.uniquindio.proyectofinal.proyectofinal.decorator.PrioridadDecorator;
import co.edu.uniquindio.proyectofinal.proyectofinal.decorator.SeguroDecorator;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.observer.IEnvioObserver;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.observer.ISujeto;
import co.edu.uniquindio.proyectofinal.proyectofinal.services.IEnvioComponente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Envio implements IEnvioComponente, ISujeto {
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
    private List<ServicioAdicional> serviciosAdicionales = new ArrayList<>();
    private EstadoEnvio estadoEnvio;
    private String idCliente;
    private final List<IEnvioObserver> observers = new ArrayList<>();
    private Paquete paquete;
    private MetodoPago metodoPago;
    private String idPago;

    public Envio(String idEnvio, String direccionOrigen, String direccionDestino,
                 String fechaCreacion, String zonaEnvio, String idCliente) {
        this.idEnvio = idEnvio;
        this.direccionOrigen = direccionOrigen;
        this.direccionDestino = direccionDestino;
        this.paquete = paquete;
        this.fechaCreacion = fechaCreacion;
        this.zonaEnvio = zonaEnvio;
        this.idCliente = idCliente;


        this.costoBase = 0.0;
        this.estadoEnvio = EstadoEnvio.PENDIENTE;
        this.fechaEntrega = null;
        this.idRepartidorAsignado = null;
        this.prioridad = null;
        this.metodoPago = null;
        this.idPago = null;
    }


    @Override
    public double calcularCosto() {

        double costoBase = 5.0;
        if (paquete != null) {
            costoBase += paquete.getPeso() * 2.0;
            costoBase += paquete.getDimensiones() * 0.001;
        }

        IEnvioComponente componente = new EnvioBase(costoBase);


        if (this.prioridad != null) {

            double factor = this.prioridad.getFactor();

            componente = new PrioridadDecorator(componente) {
                @Override
                public double calcularCosto() {
                    return super.calcularCosto() * factor;
                }
                @Override
                public String getDescripcion() {
                    return super.getDescripcion() + " + Prioridad " + prioridad.name();
                }
            };
        }


        for (ServicioAdicional servicio : this.serviciosAdicionales) {
            switch (servicio) {
                case SEGURO -> componente = new SeguroDecorator(componente);
                case FRAGIL -> componente = new FragilDecorator(componente);
                case FIRMA -> componente = new FirmaDecorator(componente);
            }
        }

        return componente.calcularCosto();
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
    public List<ServicioAdicional> getServiciosAdicionales() {return new ArrayList<>(serviciosAdicionales);}
    public EstadoEnvio getEstadoEnvio() { return estadoEnvio; }
    public void setEstadoEnvio(EstadoEnvio estadoEnvio) { this.estadoEnvio = estadoEnvio; }
    public String getZonaEnvio() { return zonaEnvio; }
    public void setZonaEnvio(String zonaEnvio) { this.zonaEnvio = zonaEnvio; }
    public List<Cliente> getCliente() { return clientes; }
    public String getIdCliente() { return idCliente; }
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }
    public Paquete getPaquete() { return paquete; }
    public void setPaquete(Paquete paquete) { this.paquete = paquete; }
    public void setMetodoPago(MetodoPago metodo) {
        this.metodoPago = metodo;
    }
    public MetodoPago getMetodoPago() {
        return metodoPago;
    }
    public String getIdPago() { return idPago; }
    public void setIdPago(String idPago) { this.idPago = idPago; }


    public void agregarServicioAdicional(ServicioAdicional servicio) {
        if (servicio != null && !serviciosAdicionales.contains(servicio)) {
            serviciosAdicionales.add(servicio);
        }
    }
    public void avanzarEstado() {
        this.estadoEnvio = this.estadoEnvio.siguienteEstado();
        notificarObservers();
    }

    public void reportarIncidencia() {
        this.estadoEnvio = EstadoEnvio.INCIDENCIA;
        notificarObservers();
    }
    public boolean cancelar() {
        if (this.estadoEnvio == EstadoEnvio.PENDIENTE) {
            this.estadoEnvio = EstadoEnvio.CANCELADO;
            notificarObservers();
            return true;
        }
        return false;
    }

    @Override
    public void registrarObserver(IEnvioObserver observer) {
        observers.add(observer);
    }

    @Override
    public void eliminarObserver(IEnvioObserver observer) {
         observers.remove(observer);
    }

    @Override
    public void notificarObservers() {
        for (IEnvioObserver observer : observers) {
            observer.actualizar(this);
        }
    }
    public long calcularDiasEnvio() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate fechaSalida  = LocalDate.parse(this.fechaCreacion, formatter);
        LocalDate fechaEntrega = LocalDate.parse(this.fechaEntrega, formatter);

        return ChronoUnit.DAYS.between(fechaSalida, fechaEntrega);
    }
    @Override
    public String toString() {
        return "Envio{" +
                "id='" + idEnvio + '\'' +
                ", origen='" + direccionOrigen + '\'' +

                '}';
    }
}
