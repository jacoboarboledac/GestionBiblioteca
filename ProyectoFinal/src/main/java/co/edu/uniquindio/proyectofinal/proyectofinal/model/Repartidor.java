package co.edu.uniquindio.proyectofinal.proyectofinal.model;

import java.util.ArrayList;
import java.util.List;

public class Repartidor extends Usuario {
    private String idRepartidor;
    private String documentoRepartidor;
    private String zonaCobertura;
    private DisponibilidadRepartidor disponibilidadRepartidor;
    private List<Envio> enviosAsignados;

    public Repartidor(String nombre, String numTelefono,String idRepartidor, String documentoRepartidor, String zonaCobertura, DisponibilidadRepartidor disponibilidadRepartidor, String contrasenia) {
        super(nombre,numTelefono, contrasenia);
        this.idRepartidor = idRepartidor;
        this.documentoRepartidor = documentoRepartidor;
        this.zonaCobertura = zonaCobertura;
        this.disponibilidadRepartidor = disponibilidadRepartidor;
        this.enviosAsignados = new ArrayList<>();
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
    public DisponibilidadRepartidor getDisponibilidadRepartidor() {
        return disponibilidadRepartidor;
    }
    public void setDisponibilidadRepartidor(DisponibilidadRepartidor disponibilidadRepartidor){
        this.disponibilidadRepartidor = disponibilidadRepartidor;
    }
    public Repartidor() {
        this.enviosAsignados = new ArrayList<>();
    }


    public List<Envio> getEnviosAsignados() {
        return new ArrayList<>(enviosAsignados); // copia defensiva
    }


    public void asignarEnvio(Envio envio) {
        if (envio != null && !enviosAsignados.contains(envio)) {
            enviosAsignados.add(envio);
        }
    }
    public void cambiarDisponibilidad(DisponibilidadRepartidor nuevaDisponibilidad) {
        this.disponibilidadRepartidor = nuevaDisponibilidad;
    }
    public List<Envio> consultarEnviosAsignados() {
        return new ArrayList<>(this.enviosAsignados);
    }
}
