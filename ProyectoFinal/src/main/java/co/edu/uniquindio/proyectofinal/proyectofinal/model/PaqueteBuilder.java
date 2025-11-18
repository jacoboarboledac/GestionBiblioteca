package co.edu.uniquindio.proyectofinal.proyectofinal.model;

public class PaqueteBuilder {
    private double peso;
    private double dimensiones;

    public Paquete build() {
        return new Paquete(peso, dimensiones);
    }
    public PaqueteBuilder peso(double peso){
        this.peso = peso;
        return this;
    }
    public PaqueteBuilder dimensiones(double dimensiones){
        this.dimensiones = dimensiones;
        return this;
    }
}
