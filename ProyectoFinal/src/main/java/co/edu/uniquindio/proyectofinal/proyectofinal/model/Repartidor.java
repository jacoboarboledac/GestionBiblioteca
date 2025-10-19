package co.edu.uniquindio.proyectofinal.proyectofinal.model;

public class Repartidor extends Usuario {
    private String idRepartidor;
    private String documentoRepartidor;
    private String zonaCobertura;

    public Repartidor(String nombre, String numTelefono,String idRepartidor, String documentoRepartidor, String zonaCobertura) {
        super(nombre,numTelefono);
        this.idRepartidor = idRepartidor;
        this.documentoRepartidor = documentoRepartidor;
        this.zonaCobertura = zonaCobertura;
    }
    public String getIdRepartidor() {
        return idRepartidor;
    }
    public void setIdRepartidor(String idRepartidor) {
        this.idRepartidor = idRepartidor;
    }
    public String getDocumentoRepartidor() {
        return documentoRepartidor;
    }
    public void setDocumentoRepartidor(String documentoRepartidor) {
        this.documentoRepartidor = documentoRepartidor;
    }
    public String getZonaCobertura() {
        return zonaCobertura;
    }
    public void setZonaCobertura(String zonaCobertura) {
        this.zonaCobertura = zonaCobertura;
    }
}
