package co.edu.uniquindio.proyectofinal.proyectofinal.model;

public enum ServicioAdicional {
    SEGURO(5.0),
    FRAGIL(3.0),
    FIRMA(2.0);

    private final double costo;

    ServicioAdicional(double costo) {
        this.costo = costo;
    }

    public double getCosto() {
        return costo;
    }
}
