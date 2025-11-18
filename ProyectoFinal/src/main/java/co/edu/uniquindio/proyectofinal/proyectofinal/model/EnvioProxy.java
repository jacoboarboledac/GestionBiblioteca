package co.edu.uniquindio.proyectofinal.proyectofinal.model;

import co.edu.uniquindio.proyectofinal.proyectofinal.services.IEnvioComponente;

public class EnvioProxy implements IEnvioComponente {
    private Envio envio;
    private boolean acceso;

    public EnvioProxy(Envio envio, boolean acceso) {
        this.envio = envio;
        this.acceso = acceso;
    }

    @Override
    public double calcularCosto() {
        if (!acceso ) {
            throw new SecurityException("No se puede calcular el costo");
        }
        return envio.calcularCosto();
    }

    @Override
    public String getDescripcion() {
        if (!acceso) {
            throw new SecurityException("No se puede dar una descripcion del el costo");
        }
        return envio.getDescripcion();
    }
}
