package co.edu.uniquindio.proyectofinal.proyectofinal.model;

import co.edu.uniquindio.proyectofinal.proyectofinal.services.ITarifa;
import co.edu.uniquindio.proyectofinal.proyectofinal.strategy.Tarifa;
import co.edu.uniquindio.proyectofinal.proyectofinal.strategy.TarifaEstandar;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmpresaLogistica {
    private String nombre;
    private String id;
    private LinkedList<Cliente> clientes;
    private LinkedList<Direccion> direcciones;
    private LinkedList<Envio> envios;
    private static long contadorEnvios = 1;
    private LinkedList<Paquete> paquetes;
    private LinkedList<Pago> pagos;
    private ObservableList<Repartidor> listaRepartidores = FXCollections.observableArrayList();
    private LinkedList<Tarifa> tarifas;
    private LinkedList<Usuario> usuarios;
    private LinkedList<Administrador> administradores;
    private static long contadorPagos = 1; // Para pagos

    public EmpresaLogistica(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
        this.clientes = new LinkedList<>();
        this.direcciones = new LinkedList<>();
        this.envios = new LinkedList<>();
        this.paquetes = new LinkedList<>();
        this.pagos = new LinkedList<>();
        this.tarifas = new LinkedList<>();
        this.usuarios = new LinkedList<>();
        this.administradores = new LinkedList<>();
    }


    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public LinkedList<Cliente> getClientes() { return clientes; }
    public void setClientes(LinkedList<Cliente> clientes) { this.clientes = clientes; }
    public LinkedList<Direccion> getDirecciones() { return direcciones; }
    public void setDirecciones(LinkedList<Direccion> direcciones) { this.direcciones = direcciones; }
    public LinkedList<Envio> getEnvios() { return envios; }
    public void setEnvios(LinkedList<Envio> envios) { this.envios = envios; }
    public LinkedList<Paquete> getPaquetes() { return paquetes; }
    public void setPaquetes(LinkedList<Paquete> paquetes) { this.paquetes = paquetes; }
    public LinkedList<Pago> getPagos() { return pagos; }
    public void setPagos(LinkedList<Pago> pagos) { this.pagos = pagos; }
    public LinkedList<Tarifa> getTarifas() { return tarifas; }
    public void setTarifas(LinkedList<Tarifa> tarifas) { this.tarifas = tarifas; }
    public LinkedList<Usuario> getUsuarios() { return usuarios; }
    public void setUsuarios(LinkedList<Usuario> usuarios) { this.usuarios = usuarios; }
    public LinkedList<Administrador> getAdministradores() { return administradores; }
    public void setAdministradores(LinkedList<Administrador> administradores) { this.administradores = administradores; }


    public boolean crearCliente(String idCliente, String nombre, String correo,
                                String telefono, String contrasenia) {
        for (Cliente c : clientes) {
            if (c.getIdCliente().equals(idCliente)) {
                return false;
            }
        }
        Cliente nuevo = new Cliente(idCliente, correo, nombre, telefono, contrasenia);
        clientes.add(nuevo);
        usuarios.add(nuevo);
        return true;
    }

    public boolean actualizarCliente(String idCliente, String nuevoNombre,
                                     String nuevoCorreo, String nuevoTelefono) {
        for (Cliente c : clientes) {
            if (c.getIdCliente().equals(idCliente)) {
                if (nuevoNombre != null) c.setNombre(nuevoNombre);
                if (nuevoCorreo != null) c.setCorreo(nuevoCorreo);
                if (nuevoTelefono != null) c.setNumTelefono(nuevoTelefono);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarCliente(String idCliente) {
        boolean eliminado = clientes.removeIf(c -> c.getIdCliente().equals(idCliente));
        if (eliminado) {
            usuarios.removeIf(u -> u instanceof Cliente && ((Cliente) u).getIdCliente().equals(idCliente));
        }
        return eliminado;
    }

    public List<Cliente> getListaClientes() {
        return new ArrayList<>(clientes);
    }

    public Cliente buscarCliente(String idCliente) {
        for (Cliente c : clientes) {
            if (c.getIdCliente().equals(idCliente)) {
                return c;
            }
        }
        return null;
    }


    public boolean agregarRepartidor(Repartidor repartidor, String idRepartidor) {
        repartidor.setIdRepartidor(idRepartidor);
        return listaRepartidores.add(repartidor);
    }

    public Optional<Repartidor> obtenerRepartidorPorId(String idRepartidor) {
        return listaRepartidores.stream()
                .filter(r -> r.getIdRepartidor().equals(idRepartidor))
                .findFirst();
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

    public boolean cambiarDisponibilidadRepartidor(String idRepartidor, DisponibilidadRepartidor nuevaDisponibilidad) {
        Optional<Repartidor> repartidorOpt = obtenerRepartidorPorId(idRepartidor);
        if (repartidorOpt.isPresent()) {
            repartidorOpt.get().setDisponibilidadRepartidor(nuevaDisponibilidad);
            return true;
        }
        return false;
    }

    public boolean eliminarRepartidor(String idRepartidor) {
        return listaRepartidores.removeIf(r -> r.getIdRepartidor().equals(idRepartidor));
    }

    public boolean crearRepartidor(String idRepartidor, String nombre, String numTelefono,
                                   String documentoRepartidor, String zonaCobertura,
                                   DisponibilidadRepartidor disponibilidad, String contrasenia) {
        for (Repartidor r : listaRepartidores) {
            if (r.getIdRepartidor().equals(idRepartidor)) {
                return false;
            }
        }
        Repartidor nuevo = new Repartidor(nombre, numTelefono, idRepartidor,
                documentoRepartidor, zonaCobertura, disponibilidad, contrasenia);
        listaRepartidores.add(nuevo);
        usuarios.add(nuevo);
        return true;
    }

    public Repartidor buscarRepartidor(String idRepartidor) {
        for (Repartidor r : listaRepartidores) {
            if (r.getIdRepartidor().equals(idRepartidor)) {
                return r;
            }
        }
        return null;
    }

    public List<Repartidor> getListaRepartidores() {
        return new ArrayList<>(listaRepartidores);
    }

    public List<Repartidor> listarRepartidoresPorDisponibilidad(DisponibilidadRepartidor disponibilidad) {
        if (disponibilidad == null) {
            return new ArrayList<>();
        }
        return listaRepartidores.stream()
                .filter(r -> r.getDisponibilidadRepartidor() == disponibilidad)
                .collect(Collectors.toList());
    }


    public boolean agregarAdministrador(Administrador administrador, String idAdmin) {
        if (administrador != null) {
            administradores.add(administrador);
            usuarios.add(administrador);
            return true;
        }
        return false;
    }


    public boolean agregarEnvio(Envio envio, String idEnvio) {
        if (envio != null) {
            envios.add(envio);
            return true;
        }
        return false;
    }

    public Envio crearSolicitud(String idCliente, String origen, String destino,
                                List<Paquete> paquetesParam, Prioridad prioridad, String zonaEnvio) {

        Cliente cliente = buscarCliente(idCliente);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no encontrado: " + idCliente);
        }


        double costoBase = 5.0;
        double pesoTotal = paquetesParam.stream().mapToDouble(Paquete::getPeso).sum();
        double volumenTotal = paquetesParam.stream().mapToDouble(Paquete::getDimensiones).sum();
        costoBase += pesoTotal * 2.0;
        costoBase += volumenTotal * 0.001;

        if (prioridad != null) {
            costoBase *= switch (prioridad) {
                case EXPRESS -> 3.0;
                case PRIORITARIO -> 2.5;
                case ESTANDAR -> 1.0;
            };
        }

        String fechaHoy = java.time.LocalDate.now().toString();
        String idEnvio = "ENV-" + contadorEnvios++;


        Envio nuevoEnvio = crearEnvio(idEnvio, origen, destino, fechaHoy, zonaEnvio, idCliente);
        nuevoEnvio.setPrioridad(prioridad);
        nuevoEnvio.setCostoBase(costoBase);
        nuevoEnvio.setPaquete(paquetesParam.isEmpty() ? null : paquetesParam.get(0));


        cliente.getEnvios().add(nuevoEnvio);


        envios.add(nuevoEnvio);


        this.paquetes.addAll(paquetesParam);

        return nuevoEnvio;
    }

    public boolean asignarEnvioRepartidor(String idEnvio, String idRepartidor) {
        Envio envio = buscarEnvioPorId(idEnvio);
        Repartidor repartidor = buscarRepartidor(idRepartidor);

        if (envio == null || repartidor == null) {
            return false;
        }
        if (envio.getEstadoEnvio() != EstadoEnvio.PENDIENTE) {
            return false;
        }

        envio.setIdRepartidorAsignado(idRepartidor);
        envio.setEstadoEnvio(EstadoEnvio.ASIGNADO);
        repartidor.asignarEnvio(envio);
        return true;
    }

    public boolean reasignarEnvio(String idEnvio, String nuevoIdRepartidor) {
        Envio envio = buscarEnvioPorId(idEnvio);
        Repartidor nuevoRepartidor = buscarRepartidor(nuevoIdRepartidor);

        if (envio == null || nuevoRepartidor == null) {
            return false;
        }
        if (envio.getIdRepartidorAsignado() == null ||
                envio.getEstadoEnvio() == EstadoEnvio.ENTREGADO ||
                envio.getEstadoEnvio() == EstadoEnvio.CANCELADO) {
            return false;
        }

        envio.setIdRepartidorAsignado(nuevoIdRepartidor);
        return true;
    }

    public boolean registrarIncidencia(String idEnvio) {
        Envio envio = buscarEnvioPorId(idEnvio);
        if (envio == null) return false;
        if (envio.getEstadoEnvio() == EstadoEnvio.ENTREGADO) return false;
        envio.reportarIncidencia();
        return true;
    }

    public boolean avanzarEstadoEnvio(String idEnvio) {
        Envio envio = buscarEnvioPorId(idEnvio);
        if (envio == null) return false;
        if (envio.getEstadoEnvio() == EstadoEnvio.ENTREGADO ||
                envio.getEstadoEnvio() == EstadoEnvio.CANCELADO ||
                envio.getEstadoEnvio() == EstadoEnvio.INCIDENCIA) {
            return false;
        }
        envio.avanzarEstado();
        return true;
    }

    private Envio buscarEnvioPorId(String idEnvio) {
        for (Envio e : envios) {
            if (e.getIdEnvio().equals(idEnvio)) {
                return e;
            }
        }
        return null;
    }


    public Pago registrarPagoEnvio(String idEnvio, double monto, MetodoPago metodoPago) {
        String idPago = "PAGO-" + contadorPagos++;
        Pago nuevoPago = new Pago(idPago, idEnvio, monto, LocalDate.now(), metodoPago);
        pagos.add(nuevoPago);

        Envio envio = buscarEnvioPorId(idEnvio);
        if (envio != null) {
            envio.setIdPago(idPago);
        }
        return nuevoPago;
    }

    public Pago obtenerPagoPorId(String idPago) {
        for (Pago p : pagos) {
            if (p.getIdPago().equals(idPago)) {
                return p;
            }
        }
        return null;
    }

    public List<Pago> listarPagosPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("Las fechas no pueden ser nulas.");
        }
        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin.");
        }
        return pagos.stream()
                .filter(pago -> !pago.getFecha().isBefore(fechaInicio) &&
                        !pago.getFecha().isAfter(fechaFin))
                .sorted(Comparator.comparing(Pago::getFecha))
                .collect(Collectors.toList());
    }


    public double cotizarTarifa(Paquete paquete, Prioridad prioridad, List<ServicioAdicional> servicios) {
        double distancia = 10.0; // o calcular
        double adicionales = servicios.stream().mapToDouble(ServicioAdicional::getCosto).sum();

        ITarifa estrategia = new TarifaEstandar();
        Tarifa tarifa = new Tarifa(distancia, paquete.getPeso(), paquete.getDimensiones(), adicionales, estrategia);
        double costo = tarifa.calcularCostoTotal();

        // Aplicar factor de prioridad
        return costo * (prioridad != null ? prioridad.getFactor() : 1.0);
    }

    public boolean generarReportePDF(String idCliente, String rutaArchivo) {
        List<Envio> enviosCliente = envios.stream()
                .filter(e -> idCliente.equals(e.getIdCliente()))
                .collect(Collectors.toList());

        if (enviosCliente.isEmpty()) {
            return false;
        }

        try {
            PdfWriter writer = new PdfWriter(rutaArchivo);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Reporte de Envíos - Cliente: " + idCliente)
                    .setBold().setFontSize(16));

            float[] columnWidths = {1, 2, 2, 2, 2, 2, 1, 2, 3};
            Table table = new Table(UnitValue.createPercentArray(columnWidths));
            table.setWidth(UnitValue.createPercentValue(100));

            String[] headers = {"ID", "Origen", "Destino", "Fecha Creación", "Fecha Entrega", "Estado", "Costo", "Prioridad", "Servicios"};
            for (String header : headers) {
                table.addHeaderCell(header);
            }

            for (Envio e : enviosCliente) {
                String servicios = e.getServiciosAdicionales().isEmpty() ?
                        "Ninguno" :
                        String.join(", ", e.getServiciosAdicionales().stream()
                                .map(Enum::name)
                                .toArray(String[]::new));

                table.addCell(e.getIdEnvio());
                table.addCell(e.getDireccionOrigen());
                table.addCell(e.getDireccionDestino());
                table.addCell(e.getFechaCreacion());
                table.addCell(e.getFechaEntrega());
                table.addCell(e.getEstadoEnvio().name());
                table.addCell(String.valueOf(e.getCostoBase()));
                table.addCell(e.getPrioridad() != null ? e.getPrioridad().name() : "N/A");
                table.addCell(servicios);
            }

            document.add(table);
            document.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String validarLogin(String nombreUsuario, String password) {
        for (Usuario u : usuarios) {
            if (u.getNombre().equals(nombreUsuario)) {
                if (u.getContrasenia().equals(password)) {
                    if (u instanceof Cliente) return "cliente";
                    if (u instanceof Repartidor) return "repartidor";
                    return "administrador";
                }
                return "contraseña incorrecta";
            }
        }
        return "Usuario no encontrado";
    }

    public ArrayList<Integer> calcularTiempoPromedio() {
        ArrayList<Integer> promedios = new ArrayList<>();
        long[] sumaDias = new long[12];
        int[] contador = new int[12];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Envio envio : envios) {
            if (envio.getFechaCreacion() == null || envio.getFechaEntrega() == null) continue;
            LocalDate salida = LocalDate.parse(envio.getFechaCreacion(), formatter);
            LocalDate entrega = LocalDate.parse(envio.getFechaEntrega(), formatter);
            long dias = ChronoUnit.DAYS.between(salida, entrega);
            int indiceMes = entrega.getMonthValue() - 1;
            sumaDias[indiceMes] += dias;
            contador[indiceMes]++;
        }

        for (int i = 0; i < 12; i++) {
            if (contador[i] > 0) {
                double promedio = (double) sumaDias[i] / contador[i];
                promedios.add((int) Math.round(promedio));
            } else {
                promedios.add(0);
            }
        }
        return promedios;
    }

    public ArrayList<Integer> cantidadServicios() {
        ArrayList<Integer> conteo = new ArrayList<>();
        conteo.add(0); // SEGURO
        conteo.add(0); // FRAGIL
        conteo.add(0); // FIRMA

        for (Envio envio : envios) {
            for (ServicioAdicional s : envio.getServiciosAdicionales()) {
                switch (s) {
                    case SEGURO -> conteo.set(0, conteo.get(0) + 1);
                    case FRAGIL -> conteo.set(1, conteo.get(1) + 1);
                    case FIRMA -> conteo.set(2, conteo.get(2) + 1);
                }
            }
        }
        return conteo;
    }

    public Usuario obtenerUsuarioPorCredenciales(String nombre, String contrasenia) {
        for (Usuario u : usuarios) {
            if (u.getNombre().equals(nombre) && u.getContrasenia().equals(contrasenia)) {
                return u;
            }
        }
        return null;
    }
    protected Envio crearEnvio(String idEnvio, String origen, String destino, String fecha, String zona, String idCliente) {
        return new Envio(idEnvio, origen, destino, fecha, zona, idCliente);
    }

}