package co.edu.uniquindio.proyectofinal.proyectofinal.model;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.observer.IEnvioObserver;
import co.edu.uniquindio.proyectofinal.proyectofinal.services.IEnvioComponente;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Cliente extends Usuario implements IEnvioObserver {
    private String idCliente;
    private String correo;
    LinkedList<Envio> envios;
    private List<Direccion> direccionesFrecuentes = new ArrayList<>();
    private static long contadorDirecciones = 1;

    public Cliente(String idCliente, String correo, String nombre, String numTelefono, String contrasenia) {
        super(nombre, numTelefono, contrasenia);
        this.idCliente = idCliente;
        this.correo = correo;
        this.envios = new LinkedList<>();
    }

    public Cliente() {
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String Correo) {
        this.correo = Correo;
    }

    public LinkedList<Envio> getEnvios() {
        return envios;
    }

    public void setEnvios(LinkedList<Envio> envios) {
        this.envios = envios;
    }

    public boolean agregarServiciosEnvio(String idEnvioActualizar, Prioridad prioridad,
                                         ServicioAdicional servicioAdicional) {

        for (Envio envio : envios) {
            if (envio.getIdEnvio().equals(idEnvioActualizar)) {
                envio.setPrioridad(prioridad);
                return true;
            }

        }
        return false;
    }

    // 1. Registrarse
    public static Cliente registrarse(String idCliente, String nombre, String correo, String numTelefono, String contrasenia) {
        return new Cliente(idCliente, correo, nombre, numTelefono, contrasenia);
    }


    public void actualizarNombre(String nombre) {
        if (nombre != null) {
            this.setNombre(nombre);
        }
    }

    public void actualizarCorreo(String correo) {
        if (correo != null) {
            this.correo = correo;
        }
    }

    public void actualizarTelefono(String telefono) {
        if (telefono != null && !telefono.trim().isEmpty()) {
            this.setNumTelefono(telefono);
        }
    }


    public List<Envio> consultarHistorialEnvios(String fecha, EstadoEnvio estado) {
        return envios.stream()
                .filter(envio -> (fecha == null || envio.getFechaCreacion().equals(fecha)))
                .filter(envio -> (estado == null || envio.getEstadoEnvio() == estado))
                .toList();
    }

    @Override
    public void actualizar(Envio envio) {
        for (Envio envio2 : envios) {
            if (envio2.getIdEnvio().equals(envio.getIdEnvio())) {
                envio2.setEstadoEnvio(envio.getEstadoEnvio());
            }
        }

    }

    public boolean generarReportePDF(String rutaArchivo) {

        if (this.envios.isEmpty()) {
            return false;
        }

        try {
            PdfWriter writer = new PdfWriter(rutaArchivo);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Reporte de Envíos - Cliente: " + this.idCliente)
                    .setBold().setFontSize(16));

            // Tabla con 9 columnas
            float[] columnWidths = {1, 2, 2, 2, 2, 2, 1, 2, 3};
            Table table = new Table(UnitValue.createPercentArray(columnWidths));
            table.setWidth(UnitValue.createPercentValue(100));

            // Cabeceras
            String[] headers = {"ID", "Origen", "Destino", "Fecha Creación", "Fecha Entrega", "Estado", "Costo", "Prioridad", "Servicios"};
            for (String header : headers) {
                table.addHeaderCell(header);
            }
            // Filas: iteramos sobre los envíos del cliente
            for (Envio e : this.envios) {
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
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public Envio crearSolicitudEnvio(String origen, String destino, Paquete paquete,
                                     String fechaCreacion, String zonaEnvio, Prioridad prioridad) {
        String idEnvio = "DIR-" + contadorDirecciones++;// evita "123" fijo
        Envio nuevoEnvio = new EnvioBuilder()
                .id(idEnvio)
                .origen(origen)
                .destino(destino)
                .fecha(fechaCreacion)
                .zona(zonaEnvio)
                .cliente(this.idCliente)
                .prioridad(prioridad)
                .paquete(paquete)
                .build();
        nuevoEnvio.setPrioridad(prioridad);
        nuevoEnvio.registrarObserver(this);
        this.envios.add(nuevoEnvio);
        return nuevoEnvio;
    }


    public boolean modificarSolicitudEnvio(String idEnvio,
                                           String nuevoOrigen, String nuevoDestino,
                                           double nuevoPeso, double nuevoVolumen,
                                           Prioridad nuevaPrioridad) {

        Envio envio = null;
        for (Envio e : this.envios) {
            if (e.getIdEnvio().equals(idEnvio)) {
                envio = e;
                break;
            }
        }


        if (envio == null || envio.getIdRepartidorAsignado() != null) {
            return false;
        }

        double nuevoCosto = 5.0 + (nuevoPeso * 2.0) + (nuevoVolumen * 0.001);


        envio.setDireccionOrigen(nuevoOrigen);
        envio.setDireccionDestino(nuevoDestino);
        envio.setCostoBase(nuevoCosto);
        envio.setPrioridad(nuevaPrioridad);

        return true;
    }


    public boolean cancelarSolicitudEnvio(String idEnvio) {
        Envio envio = null;
        for (Envio e : this.envios) {
            if (e.getIdEnvio().equals(idEnvio)) {
                envio = e;
                break;
            }
        }

        if (envio == null || envio.getIdRepartidorAsignado() != null) {
            return false;
        }

        envio.setEstadoEnvio(EstadoEnvio.CANCELADO);

        return true;
    }

    public EstadoEnvio rastrearEstado(String idEnvio) {
        for (Envio envio : this.envios) {
            if (envio.getIdEnvio().equals(idEnvio)) {
                return envio.getEstadoEnvio();
            }
        }
        return null;
    }

    public String rastrearEstadoConMensaje(String idEnvio) {
        EstadoEnvio estado = rastrearEstado(idEnvio);
        if (estado == null) return "Envío no encontrado.";

        return switch (estado) {
            case PENDIENTE -> " Envío pendiente de asignación.";
            case ASIGNADO -> " Envío asignado a un repartidor.";
            case EN_RUTA -> " Envío en ruta de entrega.";
            case ENTREGADO -> " Envío entregado con éxito.";
            case CANCELADO -> " Envío cancelado.";
            case INCIDENCIA -> " Se ha reportado una incidencia.";
        };
    }

    public boolean cancelarEnvio(String idEnvio) {
        for (Envio e : envios) {
            if (e.getIdEnvio().equals(idEnvio)) {
                return e.cancelar();
            }
        }
        return false;
    }

    public List<Envio> consultarEnviosAsociados() {
        return new ArrayList<>(this.envios);
    }
    public DesgloseTarifa desglosarTarifaEnvio(String idEnvio) {
        for (Envio envio : this.envios) {
            if (envio.getIdEnvio().equals(idEnvio)) {

                double costoBaseFijo = 5.0;


                double peso = envio.getPaquete().getPeso();
                double volumen = envio.getPaquete().getDimensiones();

                double costoPeso = peso * 2.0;
                double costoVolumen = volumen * 0.001;


                double subtotal = costoBaseFijo + costoPeso + costoVolumen;
                double recargoPrioridad = 0.0;
                if (envio.getPrioridad() != null) {
                    recargoPrioridad = switch (envio.getPrioridad()) {
                        case EXPRESS -> subtotal * 2.0;  // +30%
                        case PRIORITARIO -> subtotal * 1.5;  // +60%
                        case ESTANDAR -> 1.0;
                    };
                }


                double recargosServicios = envio.getServiciosAdicionales().stream()
                        .mapToDouble(ServicioAdicional::getCosto)
                        .sum();

                return new DesgloseTarifa(
                        costoBaseFijo,
                        costoPeso,
                        costoVolumen,
                        recargoPrioridad,
                        recargosServicios
                );
            }
        }
        return null;
    }
    public Direccion crearNuevaDireccion(String nombreDireccion, String coordenadas) {
        if (nombreDireccion == null || nombreDireccion.trim().isEmpty()) {
            return null;
        }
        if (coordenadas == null || coordenadas.trim().isEmpty()) {
            return null;
        }

        String id = "DIR-" + contadorDirecciones++;

        Direccion nueva = new Direccion(id, nombreDireccion.trim(), coordenadas.trim());
        direccionesFrecuentes.add(nueva);
        return nueva;
    }
    public boolean eliminarDireccion(String idDireccion) {
        return direccionesFrecuentes.removeIf(d -> d.getIdDireccion().equals(idDireccion));
    }
    public boolean actualizarDireccion(String idDireccion, String nuevoNombre, String nuevasCoordenadas) {
        if (idDireccion == null) {
            return false;
        }

        for (Direccion d : direccionesFrecuentes) {
            if (d.getIdDireccion().equals(idDireccion)) {
                if (nuevoNombre != null) {
                    String nombreLimpio = nuevoNombre.trim();
                    if (nombreLimpio.isEmpty()) {
                        return false; // nombre inválido
                    }
                    d.setNombreDireccion(nombreLimpio);
                }
                if (nuevasCoordenadas != null) {
                    String coordLimpia = nuevasCoordenadas.trim();
                    if (coordLimpia.isEmpty()) {
                        return false;
                    }
                    d.setCoordenadas(coordLimpia);
                }

                return true;
            }
        }

        return false;
    }
    public List<Direccion> consultarTodasLasDirecciones() {
        return new ArrayList<>(this.direccionesFrecuentes);
    }
    public Direccion consultarDireccionPorId(String idDireccion) {
        if (idDireccion == null) {
            return null;
        }
        for (Direccion d : this.direccionesFrecuentes) {
            if (d.getIdDireccion().equals(idDireccion)) {
                return d;
            }
        }
        return null;
    }
    public Pago pagarEnvio(String idEnvio, MetodoPago metodoPago) {
        Envio envio = null;
        for (Envio e : this.envios) {
            if (e.getIdEnvio().equals(idEnvio)) {
                envio = e;
                break;
            }
        }

        if (envio == null) {
            return null;
        }

        if (envio.getIdPago() != null) {
            return null;
        }
        IEnvioComponente envioProxy = new EnvioProxySeguro(envio, "cliente");
        double monto = envioProxy.calcularCosto();


        Pago pago = Administrador.registrarPagoEnvio(idEnvio, monto, metodoPago);


        envio.setIdPago(pago.getIdPago());

        return pago;
    }
    public Pago consultarComprobantePorEnvio(String idEnvio) {
        Envio envio = null;
        for (Envio e : this.envios) {
            if (e.getIdEnvio().equals(idEnvio)) {
                envio = e;
                break;
            }
        }

        if (envio == null || envio.getIdPago() == null) {
            return null;
        }

        return Administrador.obtenerPagoPorId(envio.getIdPago());
    }
    public List<Pago> consultarTodosLosComprobantes() {
        List<Pago> comprobantes = new ArrayList<>();
        for (Envio e : this.envios) {
            if (e.getIdPago() != null) {
                Pago p = Administrador.obtenerPagoPorId(e.getIdPago());
                if (p != null) {
                    comprobantes.add(p);
                }
            }
        }
        return comprobantes;
    }
    public boolean actualizarServiciosEnvio(String idEnvio, List<ServicioAdicional> nuevosServicios) {
        Envio envio = null;
        for (Envio e : this.envios) {
            if (e.getIdEnvio().equals(idEnvio)) {
                envio = e;
                break;
            }
        }

        if (envio == null || envio.getEstadoEnvio() != EstadoEnvio.PENDIENTE) {
            return false;
        }

        envio.getServiciosAdicionales().clear();
        nuevosServicios.forEach(envio::agregarServicioAdicional);
        return true;
    }
    @Override
    public String toString() {
        return "Cliente{" +
                "id='" + idCliente + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
