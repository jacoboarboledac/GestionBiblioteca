package co.edu.uniquindio.proyectofinal.proyectofinal.model;

import co.edu.uniquindio.proyectofinal.proyectofinal.strategy.Tarifa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    // CRUD para Repartidores
    // Create
    public boolean agregarRepartidor(Repartidor repartidor, String idRepartidor) {
        if (repartidor != null &&(repartidor.getIdRepartidor()).equals((idRepartidor))) {
            repartidores.add(repartidor);
            return true;
        }
        return false;
    }
    // Read
    public List<Repartidor> obtenerTodosRepartidores() {
        return new ArrayList<>(repartidores);
    }
    // Read: Obtener un repartidor por ID
    public Optional<Repartidor> obtenerRepartidorPorId(String idRepartidor) {
        return repartidores.stream()
                .filter(r -> r.getIdRepartidor().equals(idRepartidor))
                .findFirst();
    }
    // Update: Modificar un repartidor existente por ID (incluye disponibilidad)
    public boolean actualizarRepartidor(String idRepartidor, String nuevoNombre, String nuevoTelefono, String nuevoVehiculo, DisponibilidadRepartidor nuevaDisponibilidad) {
        Optional<Repartidor> repartidorOpt = obtenerRepartidorPorId(idRepartidor);
        if (repartidorOpt.isPresent()) {
            Repartidor repartidor = repartidorOpt.get();
            repartidor.setDisponibilidadRepartidor(nuevaDisponibilidad);
            return true;
        }
        return false;
    }
    public boolean cambiarDisponibilidadRepartidor(String idRepartidor, DisponibilidadRepartidor nuevaDisponibilidad) {
        Optional<Repartidor> repartidorOpt = obtenerRepartidorPorId(idRepartidor);
        if (repartidorOpt.isPresent()) {
            repartidorOpt.get().setDisponibilidadRepartidor(nuevaDisponibilidad);
            return true;
        }
        return false;
    }
    // Delete: Eliminar un repartidor por ID
    public boolean eliminarRepartidor(String idRepartidor) {
        return repartidores.removeIf(r -> r.getIdRepartidor().equals(idRepartidor));
    }
    public List<Envio> consultarEnviosAsignados(String idRepartidor) {
        if (idRepartidor == null || idRepartidor.isEmpty()) {
            return new ArrayList<>();  // Retorna lista vacía si ID inválido
        }
        return envios.stream()
                .filter(envio -> idRepartidor.equals(envio.getIdRepartidorAsignado()))
                .collect(Collectors.toList());
    }
    public boolean crearSolicitudEnvio(String idEnvio, String direccionOrigen, String direccionDestino, double costoBase, String fechaCreacion, String fechaEntrega, String zona) {
        for(Envio envio : envios) {
            if (idEnvio != null && (envio.getIdEnvio().equals(idEnvio))) {
                Envio nuevoEnvio = new Envio(idEnvio, direccionOrigen, direccionDestino, costoBase, fechaCreacion, fechaEntrega, zona, EstadoEnvio.PENDIENTE, null);  // Asume constructor actualizado
                envios.add(nuevoEnvio);
                return true;
            }
        }
        return false;
    }
    public Optional<Envio> obtenerEnvioPorId(String idEnvio) {
        return envios.stream()
                .filter(r -> r.getIdEnvio().equals(idEnvio))
                .findFirst();
    }
    public boolean actualizarEstadoEnvio(String idEnvio, EstadoEnvio nuevoEstado) {
        Optional<Envio> envioOpt = obtenerEnvioPorId(idEnvio);
        if (envioOpt.isPresent()) {
            envioOpt.get().setEstadoEnvio(nuevoEstado);
            return true;
        }
        return false;
    }
    public boolean cancelarEnvio(String idEnvio) {
        Optional<Envio> envioOpt = obtenerEnvioPorId(idEnvio);
        if (envioOpt.isPresent() && envioOpt.get().getEstadoEnvio() == EstadoEnvio.PENDIENTE && envioOpt.get().getIdRepartidorAsignado() == null) {
            envioOpt.get().setEstadoEnvio(EstadoEnvio.CANCELADO);
            return true;
        }
        return false;
    }
    public List<Envio> filtrarEnvios(String fecha, EstadoEnvio estado, String zona) {
        return envios.stream()
                .filter(envio -> (fecha == null || envio.getFechaCreacion().equals(fecha)))
                .filter(envio -> (estado == null || envio.getEstadoEnvio() == estado))
                .filter(envio -> (zona == null || envio.getZonaEnvio().equals(zona)))
                .collect(Collectors.toList());
    }
    public Optional<Envio> consultarDetalleEnvio(String idEnvio) {
        return obtenerEnvioPorId(idEnvio);
    }

    //MODIFICAR
    // 3. Cotizar tarifa de envío
    public double cotizarTarifa(String origen, String destino, double peso, double volumen, Prioridad prioridad) {
        double base = 10.0;
        double factorDistancia = calcularDistancia(origen, destino) * 0.5;
        double factorPeso = peso * 1.0;
        double factorVolumen = volumen * 0.2;
        double factorPrioridad = (prioridad == Prioridad.PRORIETARIO) ? 1.2 : 1.0;
        return (base + factorDistancia + factorPeso + factorVolumen) * factorPrioridad;
    }

    // 4. Crear solicitud de envío (para cliente)
    public boolean crearSolicitudEnvioCliente(Cliente cliente, String idEnvio, String origen, String destino, double peso, double volumen, Prioridad prioridad) {
        double tarifa = cotizarTarifa(origen, destino, peso, volumen, prioridad);
        Envio envio = new Envio(idEnvio, origen, destino, tarifa, "fechaActual", "fechaEntrega", EstadoEnvio.ASIGNADO, null, cliente.getIdCliente());
        if (crearSolicitudEnvio(idEnvio, origen, destino, tarifa, "fechaActual", "fechaEntrega", "zona")) {
            cliente.agregarEnvio(envio);
            return true;
        }
        return false;
    }

    // 4. Modificar solicitud de envío (antes de asignar)
    public boolean modificarSolicitudEnvioCliente(Cliente cliente, String idEnvio, String nuevoOrigen, String nuevoDestino, double nuevoPeso, double nuevoVolumen, Prioridad nuevaPrioridad) {
        Optional<Envio> envioOpt = cliente.getEnvios().stream().filter(e -> e.getIdEnvio().equals(idEnvio)).findFirst();
        if (envioOpt.isPresent() && envioOpt.get().getEstadoEnvio() == EstadoEnvio.ASIGNADO) {
            Envio envio = envioOpt.get();
            envio.setDireccionOrigen(nuevoOrigen);
            envio.setDireccionDestino(nuevoDestino);
            envio.setPeso(nuevoPeso);
            envio.setVolumen(nuevoVolumen);
            envio.setPrioridad(nuevaPrioridad);
            envio.setCostoBase(cotizarTarifa(nuevoOrigen, nuevoDestino, nuevoPeso, nuevoVolumen, nuevaPrioridad));
            return true;
        }
        return false;
    }

}
