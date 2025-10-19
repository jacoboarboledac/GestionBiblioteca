package co.edu.uniquindio.proyectofinal.proyectofinal.model;

import java.time.LocalDate;

public class Pago {

    private String idPago;
    private double monto;
    private LocalDate fecha;
    private MetodoPago metodoPago;



    public Pago(String idPago, double monto, LocalDate fecha,MetodoPago metodoPago) {
        this.idPago = idPago;
        this.monto = monto;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
    }
    public String getIdPago() {
        return idPago;
    }
    public void setIdPago(String idPago) {
        this.idPago = idPago;
    }
    public double getMonto() {
        return monto;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

}
