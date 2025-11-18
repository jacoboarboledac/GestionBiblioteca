package co.edu.uniquindio.proyectofinal.proyectofinal.model;

import co.edu.uniquindio.proyectofinal.proyectofinal.controller.EmpresaController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Administrador extends Usuario {
    private String idAdmin;
    private String correo;
    private LinkedList<Cliente> clientes;
    private ObservableList<Repartidor> listaRepartidores = FXCollections.observableArrayList();



    public Administrador(String idAdmin, String correo, String nombre, String numTelefono, String contrasenia) {
        super(nombre, numTelefono, contrasenia);
        this.idAdmin = idAdmin;
        this.correo = correo;
    }

    public Administrador() {
    }

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public static List<Cliente> obtenerListaClientes() {
        return EmpresaController.getInstance().getEmpresaLogistica().getClientes();
    }

    public static boolean crearCliente(String idCliente, String nombre, String correo,
                                       String telefono, String contrasenia) {
        return EmpresaController.getInstance().getEmpresaLogistica()
                .crearCliente(idCliente, nombre, correo, telefono, contrasenia);
    }

    public static boolean actualizarCliente(String idCliente, String nuevoNombre,
                                            String nuevoCorreo, String nuevoTelefono) {
        return EmpresaController.getInstance().getEmpresaLogistica()
                .actualizarCliente(idCliente, nuevoNombre, nuevoCorreo, nuevoTelefono);
    }

    public static boolean eliminarCliente(String idCliente) {
        return EmpresaController.getInstance().getEmpresaLogistica()
                .eliminarCliente(idCliente);
    }

    public static List<Cliente> listarClientes() {
        return EmpresaController.getInstance().getEmpresaLogistica().getListaClientes();
    }

    public static Cliente buscarCliente(String idCliente) {
        return EmpresaController.getInstance().getEmpresaLogistica().buscarCliente(idCliente);
    }


    public static boolean crearRepartidor(String idRepartidor, String nombre, String numTelefono,
                                          String documentoRepartidor, String zonaCobertura,
                                          DisponibilidadRepartidor disponibilidad, String contrasenia) {
        return EmpresaController.getInstance().getEmpresaLogistica()
                .crearRepartidor(idRepartidor, nombre, numTelefono, documentoRepartidor,
                        zonaCobertura, disponibilidad, contrasenia);
    }

    public boolean actualizarRepartidor(String idRepartidor, String nombre, String telefono,
                                        String zonaCobertura, DisponibilidadRepartidor disponibilidad) {
        for (Repartidor r : listaRepartidores) {
            if (r.getIdRepartidor().equals(idRepartidor)) {
                r.setNombre(nombre);
                r.setNumTelefono(telefono);
                r.setZonaCobertura(zonaCobertura);
                r.setDisponibilidadRepartidor(disponibilidad);
                return true;
            }
        }
        return false;
    }

    public static boolean eliminarRepartidor(String idRepartidor) {
        return EmpresaController.getInstance().getEmpresaLogistica()
                .eliminarRepartidor(idRepartidor);
    }

    public static List<Repartidor> listarRepartidores() {
        return EmpresaController.getInstance().getEmpresaLogistica().getListaRepartidores();
    }

    public static Repartidor buscarRepartidor(String idRepartidor) {
        return EmpresaController.getInstance().getEmpresaLogistica().buscarRepartidor(idRepartidor);
    }


    public static boolean asignarEnvioARepartidor(String idEnvio, String idRepartidor) {
        return EmpresaController.getInstance().getEmpresaLogistica()
                .asignarEnvioRepartidor(idEnvio, idRepartidor);
    }

    public static boolean reasignarEnvio(String idEnvio, String nuevoIdRepartidor) {
        return EmpresaController.getInstance().getEmpresaLogistica()
                .reasignarEnvio(idEnvio, nuevoIdRepartidor);
    }

    public static boolean registrarIncidencia(String idEnvio) {
        return EmpresaController.getInstance().getEmpresaLogistica()
                .registrarIncidencia(idEnvio);
    }

    public static boolean avanzarEstadoEnvio(String idEnvio) {
        return EmpresaController.getInstance().getEmpresaLogistica()
                .avanzarEstadoEnvio(idEnvio);
    }


    public static boolean cambiarDisponibilidadRepartidor(String idRepartidor,
                                                          DisponibilidadRepartidor nuevaDisponibilidad) {
        return EmpresaController.getInstance().getEmpresaLogistica()
                .cambiarDisponibilidadRepartidor(idRepartidor, nuevaDisponibilidad);
    }

    public static List<Repartidor> listarRepartidoresPorDisponibilidad(DisponibilidadRepartidor disponibilidad) {
        return EmpresaController.getInstance().getEmpresaLogistica()
                .listarRepartidoresPorDisponibilidad(disponibilidad);
    }


    public static Pago registrarPagoEnvio(String idEnvio, double monto, MetodoPago metodoPago) {
        return EmpresaController.getInstance().getEmpresaLogistica()
                .registrarPagoEnvio(idEnvio, monto, metodoPago);
    }

    public static List<Pago> listarPagosPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return EmpresaController.getInstance().getEmpresaLogistica()
                .listarPagosPorRangoFechas(fechaInicio, fechaFin);
    }

    public static Pago obtenerPagoPorId(String idPago) {
        return EmpresaController.getInstance().getEmpresaLogistica().obtenerPagoPorId(idPago);
    }
}