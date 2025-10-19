package co.edu.uniquindio.proyectofinal.proyectofinal.model;

import co.edu.uniquindio.proyectofinal.proyectofinal.decorator.ServicioAdicionalDecorator;
import co.edu.uniquindio.proyectofinal.proyectofinal.services.IEnvioComponente;

public enum MetodoPago {
    EFECTIVO,
    TRANSFERENCIA;

    public static class SeguroDecorator extends ServicioAdicionalDecorator {
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
}
