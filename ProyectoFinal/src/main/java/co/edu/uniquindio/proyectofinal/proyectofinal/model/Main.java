package co.edu.uniquindio.proyectofinal.proyectofinal.model;

import co.edu.uniquindio.proyectofinal.proyectofinal.decorator.PrioridadDecorator;
import co.edu.uniquindio.proyectofinal.proyectofinal.decorator.SeguroDecorator;
import co.edu.uniquindio.proyectofinal.proyectofinal.services.IEnvioComponente;

public class Main {
    public static void main(String[] args) {
        // Demostración Decorator
        IEnvioComponente envio = new EnvioBase(10.0);
        envio = new SeguroDecorator(envio);
        envio = new PrioridadDecorator(envio);
        System.out.println("Costo total envío: " + envio.calcularCosto());  // 18.0
        System.out.println("Descripción: " + envio.getDescripcion());  // Envío base estándar + Seguro + Prioridad

        // Demostración Strategy
        Tarifa tarifa = new Tarifa(10.0, 5.0, 2.0, 1.0, new TarifaEstandar());
        System.out.println("Costo tarifa estándar: " + tarifa.calcularCostoTotal());  // 8.4
        tarifa.setStrategy(new TarifaPrioritaria());
        System.out.println("Costo tarifa prioritaria: " + tarifa.calcularCostoTotal());  // 10.08
    }
}

