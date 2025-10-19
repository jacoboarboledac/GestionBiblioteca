package co.edu.uniquindio.proyectofinal.proyectofinal.model;

import co.edu.uniquindio.proyectofinal.proyectofinal.services.IEnvioComponente;

public class EnvioBase implements IEnvioComponente {
    private double costoBase;

    public EnvioBase(double costoBase) {
        this.costoBase = costoBase;
    }

    @Override
    public double calcularCosto() {
        return costoBase;
    }

    @Override
    public String getDescripcion() {
        return "Envío base estándar";
    }

    public double getCostoBase() { return costoBase; }
    public void setCostoBase(double costoBase) { this.costoBase = costoBase; }
}
