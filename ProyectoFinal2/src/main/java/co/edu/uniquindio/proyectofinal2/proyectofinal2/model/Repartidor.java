package co.edu.uniquindio.proyectofinal2.proyectofinal2.model;
import java.util.ArrayList;
import java.util.List;

public class Repartidor extends Usuario {
    private String documento;
    private EstadoDisponibilidad estadoDisponibilidad;
    private String zonaCobertura;
    private List<Envio> enviosAsignados;

    public Repartidor(String idUsuario, String nombreCompleto, String correoElectronico, String telefono, String documento, String zonaCobertura) {
        super(idUsuario, nombreCompleto, correoElectronico, telefono);
        this.documento = documento;
        this.estadoDisponibilidad = EstadoDisponibilidad.ACTIVO;
        this.zonaCobertura = zonaCobertura;
        this.enviosAsignados = new ArrayList<>();
    }

    // Métodos para gestionar disponibilidad
    public void cambiarEstadoDisponibilidad(EstadoDisponibilidad estado) {
        this.estadoDisponibilidad = estado;
    }

    // Métodos para gestionar envíos asignados
    public void asignarEnvio(Envio envio) {
        enviosAsignados.add(envio);
    }

    // Getters
    public String getDocumento() { return documento; }
    public EstadoDisponibilidad getEstadoDisponibilidad() { return estadoDisponibilidad; }
    public String getZonaCobertura() { return zonaCobertura; }
    public List<Envio> getEnviosAsignados() { return enviosAsignados; }
}
