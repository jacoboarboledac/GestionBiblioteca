package co.edu.uniquindio.proyectofinal2.proyectofinal2.model;

import java.time.LocalDateTime;

public class Pago {
    private String idPago;
    private double monto;
    private LocalDateTime fecha;
    private MetodoPago metodoPago;
    private ResultadoPago resultado;

    public Pago(String idPago, double monto, MetodoPago metodoPago) {
        this.idPago = idPago;
        this.monto = monto;
        this.fecha = LocalDateTime.now();
        this.metodoPago = metodoPago;
        this.resultado = ResultadoPago.APROBADO;
    }

    // Getters y Setters
    public String getIdPago() { return idPago; }
    public double getMonto() { return monto; }
    public LocalDateTime getFecha() { return fecha; }
    public MetodoPago getMetodoPago() { return metodoPago; }
    public ResultadoPago getResultado() { return resultado; }

    public void setResultado(ResultadoPago resultado) {
        this.resultado = resultado;
    }
}



