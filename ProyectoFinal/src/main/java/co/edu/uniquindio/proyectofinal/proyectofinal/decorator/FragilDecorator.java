package co.edu.uniquindio.proyectofinal.proyectofinal.decorator;

import co.edu.uniquindio.proyectofinal.proyectofinal.services.IEnvioComponente;

public class FragilDecorator extends ServicioAdicionalDecorator{
    private double costoFragil = 3.0;

    public FragilDecorator(IEnvioComponente envio) {
        super(envio);
    }
    public double calcularCosto() {
        return super.calcularCosto() + costoFragil;
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " + Fragil";
    }
}

