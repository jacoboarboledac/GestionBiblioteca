package co.edu.uniquindio.proyectofinal2.proyectofinal2.model;

public class Recargo {
    private String idRecargo;
    private String descripcion;
    private double valor;

    // Constructor
    public Recargo(String idRecargo, String descripcion, double valor) {
        this.idRecargo = idRecargo;
        this.descripcion = descripcion;
        this.valor = valor;
    }

    // Getters y Setters
    public String getIdRecargo() {
        return idRecargo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

