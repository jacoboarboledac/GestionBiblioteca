package co.edu.uniquindio.proyectofinal.proyectofinal.model;

import co.edu.uniquindio.proyectofinal.proyectofinal.decorator.PrioridadDecorator;
import co.edu.uniquindio.proyectofinal.proyectofinal.decorator.SeguroDecorator;
import co.edu.uniquindio.proyectofinal.proyectofinal.services.IEnvioComponente;
import co.edu.uniquindio.proyectofinal.proyectofinal.strategy.Tarifa;
import co.edu.uniquindio.proyectofinal.proyectofinal.strategy.TarifaEstandar;
import co.edu.uniquindio.proyectofinal.proyectofinal.strategy.TarifaPrioritaria;

public class Main {
    public static void main(String[] args) {
        System.out.println(" Demostracion de Patrones \n");


        System.out.println("1. Patrón Decorator: Servicios Adicionales en Envíos");


        // Envío base sin decoradores
        IEnvioComponente envioBase = new EnvioBase(10.0);
        System.out.println("Envío base: " + envioBase.getDescripcion() + " - Costo: $" + envioBase.calcularCosto());

        // Envío con solo seguro
        IEnvioComponente envioConSeguro = new SeguroDecorator(new EnvioBase(10.0));
        System.out.println("Envío con seguro: " + envioConSeguro.getDescripcion() + " - Costo: $" + envioConSeguro.calcularCosto());

        // Envío con seguro y prioridad
        IEnvioComponente envioCompleto = new PrioridadDecorator(new SeguroDecorator(new EnvioBase(10.0)));
        System.out.println("Envío completo (seguro + prioridad): " + envioCompleto.getDescripcion() + " - Costo: $" + envioCompleto.calcularCosto());

        // Otro envío
        IEnvioComponente envioPrioritario = new PrioridadDecorator(new EnvioBase(15.0));  // Costo base diferente
        System.out.println("Envío prioritario: " + envioPrioritario.getDescripcion() + " - Costo: $" + envioPrioritario.calcularCosto());

        System.out.println("\n");

        // Parte 2: Demostración del Patrón Strategy (Cálculo Dinámico de Tarifas)
        System.out.println("2. Patrón Strategy: Cálculo Dinámico de Tarifas");


        // Crear una tarifa con estrategia estándar
        Tarifa tarifa = new Tarifa(10.0, 5.0, 2.0, 1.0, new TarifaEstandar());
        System.out.println("Tarifa estándar (distancia: 10km, peso: 5kg, volumen: 2m³, adicionales: $1): $" + tarifa.calcularCostoTotal());

        // Cambiar a estrategia prioritaria en el mismo objeto
        tarifa.setStrategy(new TarifaPrioritaria());
        System.out.println("Cambio a tarifa prioritaria (mismo envío): $" + tarifa.calcularCostoTotal());

        // Simular envío local (parámetros bajos)
        tarifa = new Tarifa(5.0, 2.0, 1.0, 0.5, new TarifaEstandar());
        System.out.println("Envío local estándar (distancia: 5km, peso: 2kg, volumen: 1m³, adicionales: $0.5): $" + tarifa.calcularCostoTotal());

        // Cambiar a prioritario para el mismo envío local
        tarifa.setStrategy(new TarifaPrioritaria());
        System.out.println("Envío local prioritario: $" + tarifa.calcularCostoTotal());

        // Simular envío interurbano
        tarifa = new Tarifa(50.0, 20.0, 10.0, 5.0, new TarifaEstandar());
        System.out.println("Envío interurbano estándar (distancia: 50km, peso: 20kg, volumen: 10m³, adicionales: $5): $" + tarifa.calcularCostoTotal());

        tarifa.setStrategy(new TarifaPrioritaria());
        System.out.println("Envío interurbano prioritario: $" + tarifa.calcularCostoTotal());

        System.out.println("\n");

        // Integración de Ambos Patrones
        System.out.println("3. Integración: Envío Decorado con Tarifa Calculada");

        // Crear un envío decorado
        IEnvioComponente envioIntegrado = new PrioridadDecorator(new SeguroDecorator(new EnvioBase(10.0)));
        System.out.println("Envío integrado: " + envioIntegrado.getDescripcion());

        // Calcular tarifa para este envío usando Strategy
        Tarifa tarifaIntegrada = new Tarifa(20.0, 10.0, 5.0, 2.0, new TarifaEstandar());
        double costoTarifa = tarifaIntegrada.calcularCostoTotal();
        double costoTotalEnvio = envioIntegrado.calcularCosto() + costoTarifa;

        System.out.println("Tarifa calculada (estándar): $" + costoTarifa);
        System.out.println("Costo total del envío (servicios + tarifa): $" + costoTotalEnvio);

        // Cambiar estrategia
        tarifaIntegrada.setStrategy(new TarifaPrioritaria());
        costoTarifa = tarifaIntegrada.calcularCostoTotal();
        costoTotalEnvio = envioIntegrado.calcularCosto() + costoTarifa;

        System.out.println("Cambio a tarifa prioritaria: $" + costoTarifa);
        System.out.println("Costo total actualizado: $" + costoTotalEnvio);

        System.out.println("\nfin del sistema");
    }
}


