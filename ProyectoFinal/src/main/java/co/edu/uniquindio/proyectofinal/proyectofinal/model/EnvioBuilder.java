package co.edu.uniquindio.proyectofinal.proyectofinal.model;


import java.util.ArrayList;
import java.util.List;

public class EnvioBuilder {
    private String idEnvio;
    private String origen;
    private String destino;
    private String fechaCreacion;
    private String zonaEnvio;
    private String idCliente;
    private Prioridad prioridad;
    private Paquete paquete;
    private List<ServicioAdicional> servicios = new ArrayList<>();

    public EnvioBuilder id(String id) { this.idEnvio = id; return this; }
    public EnvioBuilder origen(String origen) { this.origen = origen; return this; }
    public EnvioBuilder destino(String destino) { this.destino = destino; return this; }
    public EnvioBuilder fecha(String fecha) { this.fechaCreacion = fecha; return this; }
    public EnvioBuilder zona(String zona) { this.zonaEnvio = zona; return this; }
    public EnvioBuilder cliente(String idCliente) { this.idCliente = idCliente; return this; }
    public EnvioBuilder prioridad(Prioridad p) { this.prioridad = p; return this; }
    public EnvioBuilder paquete(Paquete p) { this.paquete = p; return this; }
    public EnvioBuilder servicio(ServicioAdicional s) { this.servicios.add(s); return this; }

    public Envio build() {
        Envio envio = new Envio(idEnvio, origen, destino, fechaCreacion, zonaEnvio, idCliente);
        envio.setPrioridad(prioridad);
        envio.setPaquete(paquete);
        servicios.forEach(envio::agregarServicioAdicional);
        return envio;
    }
}