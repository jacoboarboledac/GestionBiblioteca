// co.edu.uniquindio.proyectofinal.proyectofinal.model.Pago.java
package co.edu.uniquindio.proyectofinal.proyectofinal.model;

import java.time.LocalDate;

public class Pago {

    private String idPago;
    private String idEnvio;
    private double monto;
    private LocalDate fecha;
    private MetodoPago metodoPago;
    private EstadoPago estadoPago;

    public Pago(String idPago, String idEnvio, double monto, LocalDate fecha, MetodoPago metodoPago) {
        this.idPago = idPago;
        this.idEnvio = idEnvio;
        this.monto = monto;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
        this.estadoPago = EstadoPago.PAGADO;
    }


    public String getIdPago() { return idPago; }
    public String getIdEnvio() { return idEnvio; }
    public double getMonto() { return monto; }
    public LocalDate getFecha() { return fecha; }
    public MetodoPago getMetodoPago() { return metodoPago; }
    public EstadoPago getEstadoPago() { return estadoPago; }


    public void setIdPago(String idPago) { this.idPago = idPago; }
    public void setIdEnvio(String idEnvio) { this.idEnvio = idEnvio; }
    public void setMonto(double monto) { this.monto = monto; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setMetodoPago(MetodoPago metodoPago) { this.metodoPago = metodoPago; }
    public void setEstadoPago(EstadoPago estadoPago) { this.estadoPago = estadoPago; }
}