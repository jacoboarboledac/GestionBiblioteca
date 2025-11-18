package co.edu.uniquindio.proyectofinal.proyectofinal.viewController;

import co.edu.uniquindio.proyectofinal.proyectofinal.App;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ClienteViewController implements Initializable {

    @FXML
    private App app;

    // ===== Cotizador =====
    @FXML private TextField txtOrigen;
    @FXML private TextField txtDestino;
    @FXML private TextField txtPeso;
    @FXML private TextField txtVolumen;
    @FXML private ComboBox<Prioridad> comboPrioridad;
    @FXML private Button btnCotizar;
    @FXML private Label lblResultadoCotizacion;

    // ===== Solicitudes =====
    @FXML private TextField txtRemitente;
    @FXML private TextField txtDestinatario;
    @FXML private CheckBox chkSeguro;
    @FXML private CheckBox chkFragil;
    @FXML private CheckBox chkFirma;
    @FXML private CheckBox chkPrioritario;
    @FXML private Button btnCrear;
    @FXML private Button btnModificar;
    @FXML private Button btnCancelar;
    @FXML private TableView<Envio> tablaSolicitudes;
    @FXML private TableColumn<Envio, String> colIdSolicitud;
    @FXML private TableColumn<Envio, String> colRemitente;
    @FXML private TableColumn<Envio, String> colDestinatario;
    @FXML private TableColumn<Envio, String> colEstadoSolicitud;
    @FXML private TableColumn<Envio, String> colFechaSolicitud;

    // ===== Comprobantes =====
    @FXML private TableView<Pago> tablaPago;
    @FXML private TableColumn<Pago, String> colIdEnvioPago;
    @FXML private TableColumn<Pago, String> colMontoPago;
    @FXML private TableColumn<Pago, String> colMetodoPago;
    @FXML private TableColumn<Pago, String> colEstadoPago;

    // ===== Pagos =====
    @FXML private TextField txtIdEnvioPagar;
    @FXML private ComboBox<MetodoPago> comboMetodoPago;
    @FXML private TableView<Pago> tablaPagos;
    @FXML private TableColumn<Pago, String> colIdEnvioTablaPagos;
    @FXML private TableColumn<Pago, String> colMontoTablaPagos;
    @FXML private TableColumn<Pago, String> colMetodoTablaPagos;
    @FXML private TableColumn<Pago, String> colEstadoTablaPagos;
    @FXML private Button btnPagar;

    // ===== Rastreo =====
    @FXML private TextField txtIdRastreo;
    @FXML private Button btnBuscarRastreo;
    @FXML private Label lblEstadoEnvio;

    // ===== Historial =====
    @FXML private DatePicker dateDesde;
    @FXML private DatePicker dateHasta;
    @FXML private ComboBox<EstadoEnvio> comboEstado;
    @FXML private TableView<Envio> tablaHistorial;
    @FXML private TableColumn<Envio, String> colIdHistorial;
    @FXML private TableColumn<Envio, String> colFechaHistorial;
    @FXML private TableColumn<Envio, String> colOrigenHistorial;
    @FXML private TableColumn<Envio, String> colDestinoHistorial;
    @FXML private TableColumn<Envio, String> colEstadoHistorial;
    @FXML private Button btnFiltrar;
    @FXML private Button btnExportarPDF;
    @FXML private Button btnExportarCSV;

    // ===== Perfil =====
    @FXML private TextField txtNombre;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtDireccion;
    @FXML private Button btnGuardarPerfil;
    // ===== Direcciones =====
    @FXML private TextField txtNombreDir;
    @FXML private TextField txtCoordenadasDir;
    @FXML private Button btnAgregarDireccion;
    @FXML private Button btnActualizarDireccion;
    @FXML private Button btnEliminarDireccion;
    @FXML private TableView<Direccion> tablaDirecciones;
    @FXML private TableColumn<Direccion, String> colIdDir;
    @FXML private TableColumn<Direccion, String> colNombreDir;
    @FXML private TableColumn<Direccion, String> colCoordenadasDir;

    // ==================== MÉTODOS ====================

    public void setApp(App app) {
        this.app = app;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        comboPrioridad.setItems(FXCollections.observableArrayList(Prioridad.values()));
        comboEstado.setItems(FXCollections.observableArrayList(EstadoEnvio.values()));
        comboMetodoPago.setItems(FXCollections.observableArrayList(MetodoPago.values()));


        comboEstado.setConverter(new StringConverter<>() {
            @Override
            public String toString(EstadoEnvio estado) {
                return estado != null ? estado.name() : "";
            }
            @Override
            public EstadoEnvio fromString(String string) {
                return null;
            }
        });


        colIdSolicitud.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getIdEnvio()));
        colRemitente.setCellValueFactory(cell -> new SimpleStringProperty(Objects.toString(cell.getValue().getDireccionOrigen(), "N/A")));
        colDestinatario.setCellValueFactory(cell -> new SimpleStringProperty(Objects.toString(cell.getValue().getDireccionDestino(), "N/A")));
        colEstadoSolicitud.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEstadoEnvio().name()));
        colFechaSolicitud.setCellValueFactory(cell -> new SimpleStringProperty(Objects.toString(cell.getValue().getFechaCreacion(), "N/A")));


        colIdEnvioPago.setCellValueFactory(cell -> new SimpleStringProperty(Objects.toString(cell.getValue().getIdEnvio(), "N/A")));
        colMontoPago.setCellValueFactory(cell -> new SimpleStringProperty(String.valueOf(cell.getValue().getMonto())));
        colMetodoPago.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getMetodoPago().name()));
        colEstadoPago.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEstadoPago().name()));


        colIdEnvioTablaPagos.setCellValueFactory(cell -> new SimpleStringProperty(Objects.toString(cell.getValue().getIdEnvio(), "N/A")));
        colMontoTablaPagos.setCellValueFactory(cell -> new SimpleStringProperty(String.valueOf(cell.getValue().getMonto())));
        colMetodoTablaPagos.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getMetodoPago().name()));
        colEstadoTablaPagos.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEstadoPago().name()));


        colIdHistorial.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getIdEnvio()));
        colFechaHistorial.setCellValueFactory(cell -> new SimpleStringProperty(Objects.toString(cell.getValue().getFechaCreacion(), "N/A")));
        colOrigenHistorial.setCellValueFactory(cell -> new SimpleStringProperty(Objects.toString(cell.getValue().getDireccionOrigen(), "N/A")));
        colDestinoHistorial.setCellValueFactory(cell -> new SimpleStringProperty(Objects.toString(cell.getValue().getDireccionDestino(), "N/A")));
        colEstadoHistorial.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEstadoEnvio().name()));
        colIdDir.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getIdDireccion()));
        colNombreDir.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNombreDireccion()));
        colCoordenadasDir.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCoordenadas()));


        inicializarTablas();
        cargarDirecciones();


        tablaSolicitudes.getSelectionModel().selectedItemProperty().addListener((obs, old, nuevo) -> {
            if (nuevo != null) {
                cargarDatosEnvioEnFormulario(nuevo);
            }
        });
    }

    private void inicializarTablas() {
        tablaSolicitudes.setItems(FXCollections.observableArrayList(new ArrayList<>()));
        tablaPago.setItems(FXCollections.observableArrayList(new ArrayList<>()));
        tablaPagos.setItems(FXCollections.observableArrayList(new ArrayList<>()));
        tablaHistorial.setItems(FXCollections.observableArrayList(new ArrayList<>()));
    }

    private void cargarDatosEnvioEnFormulario(Envio envio) {
        txtRemitente.setText(Objects.toString(envio.getDireccionOrigen(), ""));
        txtDestinatario.setText(Objects.toString(envio.getDireccionDestino(), ""));
        chkPrioritario.setSelected(envio.getPrioridad() == Prioridad.PRIORITARIO);

        List<ServicioAdicional> servicios = envio.getServiciosAdicionales();
        chkSeguro.setSelected(servicios.contains(ServicioAdicional.SEGURO));
        chkFragil.setSelected(servicios.contains(ServicioAdicional.FRAGIL));
        chkFirma.setSelected(servicios.contains(ServicioAdicional.FIRMA));
    }


    @FXML
    private void cotizarEnvio() {
        try {
            String origen = txtOrigen.getText().trim();
            String destino = txtDestino.getText().trim();
            String pesoStr = txtPeso.getText().trim();
            String volumenStr = txtVolumen.getText().trim();
            Prioridad prioridad = comboPrioridad.getValue();

            if (origen.isEmpty() || destino.isEmpty() || pesoStr.isEmpty() || volumenStr.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Complete todos los campos.").showAndWait();
                return;
            }

            double peso = Double.parseDouble(pesoStr);
            double volumen = Double.parseDouble(volumenStr);
            if (peso <= 0 || volumen <= 0) {
                new Alert(Alert.AlertType.ERROR, "Peso y volumen deben ser > 0.").showAndWait();
                return;
            }

            double costoBase = 5.0;
            double costoPeso = peso * 2.0;
            double costoVolumen = volumen * 0.001;
            double subtotal = costoBase + costoPeso + costoVolumen;
            double recargo = 0.0;

            if (prioridad != null) {
                recargo = switch (prioridad) {
                    case EXPRESS -> subtotal * 2.0;
                    case PRIORITARIO -> subtotal * 1.5;
                    case ESTANDAR -> 0.0;
                };
            }

            double total = subtotal + recargo;
            lblResultadoCotizacion.setText(String.format("Costo estimado: $%.2f", total));

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Ingrese valores numéricos válidos.").showAndWait();
        }
    }


    @FXML
    private void crearSolicitud() {
        if (app == null || app.getClienteActual() == null) {
            new Alert(Alert.AlertType.ERROR, "Sesión no válida.").showAndWait();
            return;
        }

        String origen = txtRemitente.getText().trim();
        String destino = txtDestinatario.getText().trim();
        if (origen.isEmpty() || destino.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Complete remitente y destinatario.").showAndWait();
            return;
        }

        Cliente cliente = app.getClienteActual();
        String idEnvio = "ENV-" + System.currentTimeMillis();
        String fecha = LocalDate.now().toString();
        Paquete paquete = new Paquete(1.0, 1000);
        Prioridad prioridad = chkPrioritario.isSelected() ? Prioridad.PRIORITARIO : Prioridad.ESTANDAR;

        Envio nuevo = cliente.crearSolicitudEnvio(origen, destino, paquete, fecha, "Urbana", prioridad);

        if (chkSeguro.isSelected()) nuevo.agregarServicioAdicional(ServicioAdicional.SEGURO);
        if (chkFragil.isSelected()) nuevo.agregarServicioAdicional(ServicioAdicional.FRAGIL);
        if (chkFirma.isSelected()) nuevo.agregarServicioAdicional(ServicioAdicional.FIRMA);


        tablaSolicitudes.getItems().setAll(cliente.getEnvios());
        new Alert(Alert.AlertType.INFORMATION, "Creado con ID: " + nuevo.getIdEnvio()).showAndWait();
    }

    @FXML
    private void modificarSolicitud() {
        Envio envio = tablaSolicitudes.getSelectionModel().getSelectedItem();
        if (envio == null) {
            new Alert(Alert.AlertType.ERROR, "Seleccione un envío.").showAndWait();
            return;
        }

        String origen = txtRemitente.getText().trim();
        String destino = txtDestinatario.getText().trim();
        if (origen.isEmpty() || destino.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Campos incompletos.").showAndWait();
            return;
        }

        Cliente cliente = app.getClienteActual();
        boolean exito = cliente.modificarSolicitudEnvio(
                envio.getIdEnvio(),
                origen, destino,
                1.0, 1000,
                chkPrioritario.isSelected() ? Prioridad.PRIORITARIO : Prioridad.ESTANDAR
        );

        if (!exito) {
            new Alert(Alert.AlertType.ERROR, "Solo se modifican envíos en estado PENDIENTE.").showAndWait();
            return;
        }


        List<ServicioAdicional> servicios = new ArrayList<>();
        if (chkSeguro.isSelected()) servicios.add(ServicioAdicional.SEGURO);
        if (chkFragil.isSelected()) servicios.add(ServicioAdicional.FRAGIL);
        if (chkFirma.isSelected()) servicios.add(ServicioAdicional.FIRMA);
        cliente.actualizarServiciosEnvio(envio.getIdEnvio(), servicios);


        tablaSolicitudes.getItems().setAll(cliente.getEnvios());
        new Alert(Alert.AlertType.INFORMATION, "Modificado correctamente.").showAndWait();
    }

    @FXML
    private void cancelarSolicitud() {
        Envio envio = tablaSolicitudes.getSelectionModel().getSelectedItem();
        if (envio == null) {
            new Alert(Alert.AlertType.ERROR, "Seleccione un envío.").showAndWait();
            return;
        }

        Cliente cliente = app.getClienteActual();
        boolean exito = cliente.cancelarEnvio(envio.getIdEnvio());

        if (exito) {
            tablaSolicitudes.getItems().setAll(cliente.getEnvios());
            new Alert(Alert.AlertType.INFORMATION, "Cancelado.").showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR, "Solo se cancelan envíos en estado PENDIENTE.").showAndWait();
        }
    }


    public void cargarComprobantes() {
        if (app == null || app.getClienteActual() == null) return;
        List<Pago> comprobantes = app.getClienteActual().consultarTodosLosComprobantes();
        tablaPago.getItems().setAll(comprobantes);
    }

    public void cargarHistorialPagos() {
        if (app == null || app.getClienteActual() == null) return;
        List<Pago> pagos = app.getClienteActual().consultarTodosLosComprobantes();
        tablaPagos.getItems().setAll(pagos);
    }

    @FXML
    private void pagarEnvio() {
        if (app == null || app.getClienteActual() == null) {
            new Alert(Alert.AlertType.ERROR, "Sesión no válida.").showAndWait();
            return;
        }

        String idEnvio = txtIdEnvioPagar.getText().trim();
        MetodoPago metodo = comboMetodoPago.getValue();

        if (idEnvio.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Ingrese el ID del envío.").showAndWait();
            return;
        }
        if (metodo == null) {
            new Alert(Alert.AlertType.ERROR, "Seleccione un método de pago.").showAndWait();
            return;
        }

        Cliente cliente = app.getClienteActual();
        Pago pago = cliente.pagarEnvio(idEnvio, metodo);

        if (pago != null) {
            cargarComprobantes();
            cargarHistorialPagos();
            txtIdEnvioPagar.clear();
            comboMetodoPago.getSelectionModel().clearSelection();
            new Alert(Alert.AlertType.INFORMATION, "Pago registrado: " + pago.getIdPago()).showAndWait();
        } else {
            boolean existe = cliente.getEnvios().stream()
                    .anyMatch(e -> e.getIdEnvio().equals(idEnvio));
            if (!existe) {
                new Alert(Alert.AlertType.ERROR, "Envío no encontrado.").showAndWait();
            } else {
                new Alert(Alert.AlertType.WARNING, "El envío ya fue pagado.").showAndWait();
            }
        }
    }


    @FXML
    private void buscarRastreo() {
        // Verificar sesión
        if (app == null || app.getClienteActual() == null) {
            lblEstadoEnvio.setText("Estado actual: Sesión no válida.");
            return;
        }


        String idEnvio = txtIdRastreo.getText().trim();
        if (idEnvio.isEmpty()) {
            lblEstadoEnvio.setText("Estado actual: Ingrese un ID de envío.");
            return;
        }


        Cliente cliente = app.getClienteActual();
        String mensaje = cliente.rastrearEstadoConMensaje(idEnvio);


        lblEstadoEnvio.setText("Estado actual:" + mensaje);
    }


    @FXML
    private void filtrarHistorial() {
        if (app == null || app.getClienteActual() == null) return;

        Cliente cliente = app.getClienteActual();
        List<Envio> envios = new ArrayList<>(cliente.getEnvios());

        LocalDate desde = dateDesde.getValue();
        LocalDate hasta = dateHasta.getValue();
        EstadoEnvio estado = comboEstado.getValue();

        if (desde != null) {
            envios.removeIf(e -> {
                try {
                    LocalDate f = LocalDate.parse(e.getFechaCreacion());
                    return f.isBefore(desde);
                } catch (Exception ex) {
                    return true;
                }
            });
        }
        if (hasta != null) {
            envios.removeIf(e -> {
                try {
                    LocalDate f = LocalDate.parse(e.getFechaCreacion());
                    return f.isAfter(hasta);
                } catch (Exception ex) {
                    return true;
                }
            });
        }
        if (estado != null) {
            envios.removeIf(e -> e.getEstadoEnvio() != estado);
        }

        tablaHistorial.getItems().setAll(envios);
    }

    @FXML
    private void exportarPDF() {
        if (app == null || app.getClienteActual() == null) {
            new Alert(Alert.AlertType.ERROR, "Sesión no válida.").showAndWait();
            return;
        }

        Cliente cliente = app.getClienteActual();
        String nombreArchivo = "reporte_envios_" + cliente.getIdCliente() + ".pdf";

        boolean exito = cliente.generarReportePDF(nombreArchivo);

        if (exito) {
            new Alert(Alert.AlertType.INFORMATION,
                    "PDF generado en: " + System.getProperty("user.dir") + "/" + nombreArchivo)
                    .showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR, "Error al generar el PDF.").showAndWait();
        }
    }


    @FXML
    private void guardarPerfil() {
        if (app == null || app.getClienteActual() == null) return;

        Cliente cliente = app.getClienteActual();
        cliente.actualizarNombre(txtNombre.getText().trim());
        cliente.actualizarCorreo(txtCorreo.getText().trim());
        cliente.actualizarTelefono(txtTelefono.getText().trim());

        new Alert(Alert.AlertType.INFORMATION, "Perfil actualizado.").showAndWait();
    }


    public void cargarDatos() {
        if (app == null || app.getClienteActual() == null) return;

        Cliente cliente = app.getClienteActual();
        tablaSolicitudes.getItems().setAll(cliente.getEnvios());
        cargarComprobantes();
        cargarHistorialPagos();
        cargarDirecciones();
        tablaHistorial.getItems().setAll(cliente.getEnvios());


               txtNombre.setText(cliente.getNombre());
        txtCorreo.setText(cliente.getCorreo());
        txtTelefono.setText(cliente.getNumTelefono());
    }
    public void cargarDirecciones() {
        if (app != null && app.getClienteActual() != null) {
            List<Direccion> direcciones = app.getClienteActual().consultarTodasLasDirecciones();
            if (tablaDirecciones.getItems() == null) {
                tablaDirecciones.setItems(FXCollections.observableArrayList(new ArrayList<>()));
            }
            tablaDirecciones.getItems().setAll(direcciones);
        }
    }

    @FXML
    public void agregarDireccion() {
        String nombre = txtNombreDir.getText().trim();
        String coords = txtCoordenadasDir.getText().trim();

        if (nombre.isEmpty() || coords.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Complete todos los campos.").showAndWait();
            return;
        }

        Cliente cliente = app.getClienteActual();
        Direccion nueva = cliente.crearNuevaDireccion(nombre, coords);

        if (nueva != null) {
            cargarDirecciones();
            txtNombreDir.clear();
            txtCoordenadasDir.clear();
            new Alert(Alert.AlertType.INFORMATION, "Dirección agregada.").showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR, "Error al crear la dirección.").showAndWait();
        }
    }

    @FXML
    public void actualizarDireccion() {
        Direccion seleccionada = tablaDirecciones.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            new Alert(Alert.AlertType.ERROR, "Seleccione una dirección.").showAndWait();
            return;
        }

        String nombre = txtNombreDir.getText().trim();
        String coords = txtCoordenadasDir.getText().trim();

        if (nombre.isEmpty() || coords.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Complete todos los campos.").showAndWait();
            return;
        }

        Cliente cliente = app.getClienteActual();
        boolean exito = cliente.actualizarDireccion(seleccionada.getIdDireccion(), nombre, coords);

        if (exito) {
            cargarDirecciones();
            new Alert(Alert.AlertType.INFORMATION, "Dirección actualizada.").showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR, "No se pudo actualizar.").showAndWait();
        }
    }

    @FXML
    public void eliminarDireccion() {
        Direccion seleccionada = tablaDirecciones.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            new Alert(Alert.AlertType.ERROR, "Seleccione una dirección.").showAndWait();
            return;
        }

        Cliente cliente = app.getClienteActual();
        boolean exito = cliente.eliminarDireccion(seleccionada.getIdDireccion());

        if (exito) {
            cargarDirecciones();
            new Alert(Alert.AlertType.INFORMATION, "Dirección eliminada.").showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR, "No se pudo eliminar.").showAndWait();
        }
    }

}