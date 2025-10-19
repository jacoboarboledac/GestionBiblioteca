package co.edu.uniquindio.proyectofinal.proyectofinal.decorator;

import co.edu.uniquindio.proyectofinal.proyectofinal.services.IEnvioComponente;

public class PrioridadDecorator extends ServicioAdicionalDecorator {
    private double costoPrioridad = 3.0;

    public PrioridadDecorator(IEnvioComponente envio) {
        super(envio);
    }

    @Override
    public double calcularCosto() {
        return super.calcularCosto() + costoPrioridad;
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " + Prioridad";
    }
}
