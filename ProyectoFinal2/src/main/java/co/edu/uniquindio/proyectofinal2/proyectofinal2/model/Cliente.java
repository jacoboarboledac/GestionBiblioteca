package co.edu.uniquindio.proyectofinal2.proyectofinal2.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {
    private List<Direccion> direccionesFrecuentes;
    private List<MetodoPago> metodosPago;
    private List<Envio> historialEnvios;

    public Cliente(String idUsuario, String nombreCompleto, String correoElectronico, String telefono) {
        super(idUsuario, nombreCompleto, correoElectronico, telefono);
        this.direccionesFrecuentes = new ArrayList<>();
        this.metodosPago = new ArrayList<>();
        this.historialEnvios = new ArrayList<>();
    }

    // Métodos para gestionar direcciones
    public void agregarDireccion(Direccion direccion) {
        direccionesFrecuentes.add(direccion);
    }

    public boolean eliminarDireccion(String idDireccion) {
        return direccionesFrecuentes.removeIf(d -> d.getIdDireccion().equals(idDireccion));
    }

    // Métodos para gestionar métodos de pago
    public void agregarMetodoPago(MetodoPago metodoPago) {
        metodosPago.add(metodoPago);
    }

    // Métodos para gestionar historial de envíos
    public void agregarEnvioAHistorial(Envio envio) {
        historialEnvios.add(envio);
    }

    // Getters
    public List<Direccion> getDireccionesFrecuentes() { return direccionesFrecuentes; }
    public List<MetodoPago> getMetodosPago() { return metodosPago; }
    public List<Envio> getHistorialEnvios() { return historialEnvios; }
}

