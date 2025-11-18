package co.edu.uniquindio.proyectofinal.proyectofinal.dto;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.DisponibilidadRepartidor;

public class RepartidorDTO {
    private String idRepartidor;
    private String nombre;
    private String telefono;
    private String zonaCobertura;
    private DisponibilidadRepartidor disponibilidad;

    public RepartidorDTO(String idRepartidor, String nombre, String telefono,
                         String zonaCobertura, DisponibilidadRepartidor disponibilidad) {
        this.idRepartidor = idRepartidor;
        this.nombre = nombre;
        this.telefono = telefono;
        this.zonaCobertura = zonaCobertura;
        this.disponibilidad = disponibilidad;
    }


}
