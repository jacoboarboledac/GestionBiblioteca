package co.edu.uniquindio.proyectofinal.proyectofinal.strategy;

import co.edu.uniquindio.proyectofinal.proyectofinal.services.ITarifa;

public class TarifaEstandar implements ITarifa {
    @Override
    public double calcularTarifa(double distancia, double peso, double volumen, double adicionales) {
        return (distancia * 0.5) + (peso * 1.0) + (volumen * 0.2) + adicionales;
    }
}
