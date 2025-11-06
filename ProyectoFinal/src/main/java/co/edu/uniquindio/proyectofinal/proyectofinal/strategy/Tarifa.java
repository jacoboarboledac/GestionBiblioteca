package co.edu.uniquindio.proyectofinal.proyectofinal.strategy;

import co.edu.uniquindio.proyectofinal.proyectofinal.services.ITarifa;

public  class Tarifa  {
    private double distancia;
    private double peso;
    private double volumen;
    private double adicionales;
    private ITarifa strategy;

    public Tarifa(double distancia, double peso, double volumen, double adicionales, ITarifa strategy) {
        this.distancia = distancia;
        this.peso = peso;
        this.volumen = volumen;
        this.adicionales = adicionales;
        this.strategy = strategy;
    }

    public double calcularCostoTotal() {
        return strategy.calcularTarifa(distancia, peso, volumen, adicionales);
    }

    public void setStrategy(ITarifa strategy) {
        this.strategy = strategy;
    }


    public double getDistancia() { return distancia; }
    public void setDistancia(double distancia) { this.distancia = distancia; }
    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }
    public double getVolumen() { return volumen; }
    public void setVolumen(double volumen) { this.volumen = volumen; }
    public double getAdicionales() { return adicionales; }
    public void setAdicionales(double adicionales) { this.adicionales = adicionales; }
}