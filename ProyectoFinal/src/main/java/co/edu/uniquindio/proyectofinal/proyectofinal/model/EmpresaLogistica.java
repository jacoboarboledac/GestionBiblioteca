package co.edu.uniquindio.proyectofinal.proyectofinal.model;

import java.util.LinkedList;

public class EmpresaLogistica {
    private String nombre;
    private String id;
    private LinkedList<Cliente> clientes;
    private LinkedList<Direccion> direcciones;
    private LinkedList<Envio> envios;
    private LinkedList<Paquete> paquetes;
    private LinkedList<Pago> pagos;
    private LinkedList<Repartidor> repartidores;
    private LinkedList<Tarifa> tarifas;
    private LinkedList<Usuario> usuarios;
    public EmpresaLogistica(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
        clientes = new LinkedList<>();
        direcciones = new LinkedList<>();
        envios = new LinkedList<>();
        paquetes = new LinkedList<>();
        pagos = new LinkedList<>();
        repartidores = new LinkedList<>();
        tarifas = new LinkedList<>();
        usuarios = new LinkedList<>();
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public LinkedList<Cliente> getClientes() {
        return clientes;
    }
    public void setClientes(LinkedList<Cliente> clientes) {
        this.clientes = clientes;
    }
    public LinkedList<Direccion> getDirecciones() {
        return direcciones;
    }
    public void setDirecciones(LinkedList<Direccion> direcciones) {
        this.direcciones = direcciones;
    }
    public LinkedList<Envio> getEnvios() {
        return envios;
    }
    public void setEnvios(LinkedList<Envio> envios) {
        this.envios = envios;
    }
    public LinkedList<Paquete> getPaquetes() {
        return paquetes;
    }
    public void setPaquetes(LinkedList<Paquete> paquetes) {
        this.paquetes = paquetes;
    }
    public LinkedList<Pago> getPagos() {
        return pagos;
    }
    public void setPagos(LinkedList<Pago> pagos) {
        this.pagos = pagos;
    }
    public LinkedList<Repartidor> getRepartidores() {
        return repartidores;
    }
    public void setRepartidores(LinkedList<Repartidor> repartidores) {
        this.repartidores = repartidores;
    }
    public LinkedList<Tarifa> getTarifas() {
        return tarifas;
    }
    public void setTarifas(LinkedList<Tarifa> tarifas) {
        this.tarifas = tarifas;
    }
    public LinkedList<Usuario> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(LinkedList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
