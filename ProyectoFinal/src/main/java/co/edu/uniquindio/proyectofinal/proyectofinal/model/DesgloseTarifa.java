package co.edu.uniquindio.proyectofinal.proyectofinal.model;

public class DesgloseTarifa {
    private final double costoBaseFijo;
    private final double costoPeso;
    private final double costoVolumen;
    private final double recargoPrioridad;
    private final double recargosServicios;
    private final double total;

    public DesgloseTarifa(double costoBaseFijo, double costoPeso, double costoVolumen,
                          double recargoPrioridad, double recargosServicios) {
        this.costoBaseFijo = costoBaseFijo;
        this.costoPeso = costoPeso;
        this.costoVolumen = costoVolumen;
        this.recargoPrioridad = recargoPrioridad;
        this.recargosServicios = recargosServicios;
        this.total = costoBaseFijo + costoPeso + costoVolumen + recargoPrioridad + recargosServicios;
    }


    public double getCostoBaseFijo() { return costoBaseFijo; }
    public double getCostoPeso() { return costoPeso; }
    public double getCostoVolumen() { return costoVolumen; }
    public double getRecargoPrioridad() { return recargoPrioridad; }
    public double getRecargosServicios() { return recargosServicios; }
    public double getTotal() { return total; }

    @Override
    public String toString() {
        return String.format(
                "Base: $%.2f\n" +
                        "Peso: $%.2f\n" +
                        "Volumen: $%.2f\n" +
                        "Prioridad: $%.2f\n" +
                        "Servicios: $%.2f\n" +
                        "------------------\n" +
                        "TOTAL: $%.2f",
                costoBaseFijo, costoPeso, costoVolumen, recargoPrioridad, recargosServicios, total
        );
    }
}