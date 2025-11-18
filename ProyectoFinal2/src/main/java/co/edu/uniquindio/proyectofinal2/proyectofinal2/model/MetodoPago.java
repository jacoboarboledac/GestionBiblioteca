package co.edu.uniquindio.proyectofinal2.proyectofinal2.model;

public class MetodoPago {
    private String idMetodoPago;
    private TipoMetodoPago tipo;
    private String detalles;

    public MetodoPago(String idMetodoPago, TipoMetodoPago tipo, String detalles) {
        this.idMetodoPago = idMetodoPago;
        this.tipo = tipo;
        this.detalles = detalles;
    }

    // Getters y Setters
    public String getIdMetodoPago() { return idMetodoPago; }
    public TipoMetodoPago getTipo() { return tipo; }
    public String getDetalles() { return detalles; }
}



