package co.edu.uniquindio.proyectofinal.proyectofinal.decorator;

import co.edu.uniquindio.proyectofinal.proyectofinal.services.IEnvioComponente;

public class FirmaDecorator extends ServicioAdicionalDecorator {
    private double costoFirma = 2.0;

    public FirmaDecorator(IEnvioComponente envio) {
        super(envio);
    }
    public double calcularCosto() {
        return super.calcularCosto() + costoFirma;
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " + Firma";
    }
}

