package co.edu.uniquindio.proyectofinal.proyectofinal.model;

public class Paquete {
    private double peso;
    private double dimensiones;
    public Paquete(double peso, double dimensiones) {
        this.peso = peso;
        this.dimensiones = dimensiones;
    }
    public double getPeso() {
        return peso;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }
    public double getDimensiones() {
        return dimensiones;
    }
    public void setDimensiones(double dimensiones) {
        this.dimensiones = dimensiones;
    }

}
