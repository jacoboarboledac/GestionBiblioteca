package co.edu.uniquindio.proyectofinal.proyectofinal.decorator;

import co.edu.uniquindio.proyectofinal.proyectofinal.services.IEnvioComponente;

public abstract class ServicioAdicionalDecorator implements IEnvioComponente {
    protected IEnvioComponente envio;

    public ServicioAdicionalDecorator(IEnvioComponente envio) {
        this.envio = envio;
    }

    @Override
    public double calcularCosto() {
        return envio.calcularCosto();
    }

    @Override
    public String getDescripcion() {
        return envio.getDescripcion();
    }
}
