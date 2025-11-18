package co.edu.uniquindio.proyectofinal2.proyectofinal2.model;

import java.util.ArrayList;
import java.util.List;

public class Tarifa {
    private String idTarifa;
    private double distancia;
    private double peso;
    private double volumen;
    private boolean prioridad;
    private List<Recargo> recargos;
    private double costoTotal;

    public Tarifa(String idTarifa, double distancia, double peso, double volumen, boolean prioridad) {
        this.idTarifa = idTarifa;
        this.distancia = distancia;
        this.peso = peso;
        this.volumen = volumen;
        this.prioridad = prioridad;
        this.recargos = new ArrayList<>();
        this.costoTotal = 0;
    }

    // Getters y Setters
    public String getIdTarifa() { return idTarifa; }
    public double getDistancia() { return distancia; }
    public double getPeso() { return peso; }
    public double getVolumen() { return volumen; }
    public boolean isPrioridad() { return prioridad; }
    public List<Recargo> getRecargos() { return recargos; }
    public double getCostoTotal() { return costoTotal; }

    public void agregarRecargo(Recargo recargo) {
        recargos.add(recargo);
    }

    public void calcularCostoTotal() {
        // Lógica para calcular el costo total según distancia, peso, volumen, prioridad y recargos
        //this.costoTotal = /* cálculo */;
    }
}

