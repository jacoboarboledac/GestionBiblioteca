package co.edu.uniquindio.proyectofinal.proyectofinal.model;

public enum Prioridad {
    ESTANDAR(1.0),
    PRIORITARIO(1.5),
    EXPRESS(2.0);

    private final double factor;

    Prioridad(double factor) {
        this.factor = factor;
    }

    public double getFactor() {
        return factor;
    }
}
