package co.edu.uniquindio.proyectofinal2.proyectofinal2.model;

public class ServicioAdicional {
    private String idServicio;
    private String nombre;
    private double costoAdicional;

    public ServicioAdicional(String idServicio, String nombre, double costoAdicional) {
        this.idServicio = idServicio;
        this.nombre = nombre;
        this.costoAdicional = costoAdicional;
    }

    // Getters y Setters
    public String getIdServicio() { return idServicio; }
    public String getNombre() { return nombre; }
    public double getCostoAdicional() { return costoAdicional; }
}

