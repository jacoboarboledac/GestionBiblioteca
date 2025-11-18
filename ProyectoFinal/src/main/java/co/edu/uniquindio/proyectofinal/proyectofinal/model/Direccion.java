package co.edu.uniquindio.proyectofinal.proyectofinal.model;

public class Direccion {
    private String idDireccion;
    private String nombreDireccion;
    private String coordenadas;
    public Direccion(String idDireccion, String nombreDireccion, String coordenadas) {
        this.idDireccion = idDireccion;
        this.nombreDireccion = nombreDireccion;
        this.coordenadas = coordenadas;
    }
    public String getIdDireccion() {
        return idDireccion;
    }
    public void setIdDireccion(String idDireccion) {
        this.idDireccion = idDireccion;
    }
    public String getNombreDireccion() {
        return nombreDireccion;
    }
    public void setNombreDireccion(String nombreDireccion) {
        this.nombreDireccion = nombreDireccion;
    }
    public String getCoordenadas() {
        return coordenadas;
    }
    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }
}

