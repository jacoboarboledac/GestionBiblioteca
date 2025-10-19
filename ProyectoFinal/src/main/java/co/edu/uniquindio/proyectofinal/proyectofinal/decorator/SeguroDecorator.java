package co.edu.uniquindio.proyectofinal.proyectofinal.decorator;

import co.edu.uniquindio.proyectofinal.proyectofinal.services.IEnvioComponente;

public class SeguroDecorator extends ServicioAdicionalDecorator {
    private double costoSeguro = 5.0;

    public SeguroDecorator(IEnvioComponente envio) {
        super(envio);
    }

    @Override
    public double calcularCosto() {
        return super.calcularCosto() + costoSeguro;
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " + Seguro";
    }
}
