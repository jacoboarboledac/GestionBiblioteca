package co.edu.uniquindio.proyectofinal.proyectofinal.viewController;

import co.edu.uniquindio.proyectofinal.proyectofinal.App;
import co.edu.uniquindio.proyectofinal.proyectofinal.controller.EmpresaController;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.chart.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GestorAplicacionViewController implements Initializable {

    // ===== CLIENTES =====
    @FXML App app;
    @FXML private TextField txtNombreCliente;
    @FXML private TextField txtCorreoCliente;
    @FXML private Button btnCrearCliente;
    @FXML private TableView<Cliente> tablaClientes;
    @FXML private TableColumn<Cliente, String> colIdCliente;
    @FXML private TableColumn<Cliente, String> colNombreCliente;
    @FXML private TableColumn<Cliente, String> colCorreoCliente;

    // ===== REPARTIDORES =====
    @FXML private TextField txtNombreRepartidor;
    @FXML private TextField txtVehiculoRepartidor;
    @FXML private Button btnAgregarRepartidor;
    @FXML private TableView<Repartidor> tablaRepartidores;
    @FXML private TableColumn<Repartidor, String> colIdRepartidor;
    @FXML private TableColumn<Repartidor, String> colNombreRepartidorTabla;
    @FXML private TableColumn<Repartidor, String> colDisponibilidadRepartidor;

    // ===== ENVÍOS =====
    @FXML private TextField txtIdEnvioAsignar;
    @FXML private TextField txtIdRepartidorAsignar;
    @FXML private TextField txtIdEnvioIncidencia;
    @FXML private Button btnAsignarEnvio;
    @FXML private Button btnReasignarEnvio;
    @FXML private Button btnRegistrarIncidencia;
    @FXML private Button btnActualizarEstado;
    @FXML private TableView<Envio> tablaEnviosGlobales;
    @FXML private TableColumn<Envio, String> colIdEnvioG;
    @FXML private TableColumn<Envio, String> colClienteG;
    @FXML private TableColumn<Envio, String> colRepartidorG;
    @FXML private TableColumn<Envio, String> colEstadoG;
    @FXML private TableColumn<Envio, String> colIncidenciaG;

    // ===== GRÁFICAS =====
    @FXML private LineChart<String, Number> lineChartTiempoPeriodo;
    @FXML private BarChart<String, Number> barChartServicios;
    @FXML private PieChart pieChartIncidencias;
    public void setApp(App app) {
        this.app = app;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        barChartServicios.setAnimated(false);
        lineChartTiempoPeriodo.setAnimated(false);
        pieChartIncidencias.setAnimated(false);

        configurarTablas();
        cargarDatos(); // Ahora es seguro: usa el Singleton
        System.out.println(" GestorAplicacionViewController inicializado.");
    }

    private void configurarTablas() {
        // Clientes
        colIdCliente.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getIdCliente()));
        colNombreCliente.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNombre()));
        colCorreoCliente.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCorreo()));

        // Repartidores
        colIdRepartidor.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getIdRepartidor()));
        colNombreRepartidorTabla.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNombre()));
        colDisponibilidadRepartidor.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getDisponibilidadRepartidor().name()));

        // Envíos
        colIdEnvioG.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getIdEnvio()));
        colClienteG.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getIdCliente()));
        colRepartidorG.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getIdRepartidorAsignado() != null ?
                        cell.getValue().getIdRepartidorAsignado() : "Sin asignar"));
        colEstadoG.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEstadoEnvio().name()));
        colIncidenciaG.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getEstadoEnvio() == EstadoEnvio.INCIDENCIA ? "Sí" : "No"));
    }


    @FXML
    private void crearCliente() {
        String nombre = txtNombreCliente.getText().trim();
        String correo = txtCorreoCliente.getText().trim();

        if (nombre.isEmpty() || correo.isEmpty()) {
            mostrarError("Complete nombre y correo.");
            return;
        }

        String id = "CLI-" + System.currentTimeMillis();
        boolean exito = Administrador.crearCliente(id, nombre, correo, "123456789", "12345");

        if (exito) {
            limpiarCamposClientes();
            cargarDatos();
            mostrarMensaje("Cliente creado: " + id);
        } else {
            mostrarError("ID de cliente ya existe.");
        }
    }

    private void limpiarCamposClientes() {
        txtNombreCliente.clear();
        txtCorreoCliente.clear();
    }


    @FXML
    private void agregarRepartidor() {
        String nombre = txtNombreRepartidor.getText().trim();
        String vehiculo = txtVehiculoRepartidor.getText().trim();

        if (nombre.isEmpty() || vehiculo.isEmpty()) {
            mostrarError("Complete nombre y vehículo.");
            return;
        }

        String id = "REP-" + System.currentTimeMillis();
        boolean exito = Administrador.crearRepartidor(
                id, nombre, "123456789", "DOC-" + id, vehiculo,
                DisponibilidadRepartidor.ACTIVO, "12345"
        );

        if (exito) {
            limpiarCamposRepartidores();
            cargarDatos();
            mostrarMensaje("Repartidor creado: " + id);
        } else {
            mostrarError("ID de repartidor ya existe.");
        }
    }

    private void limpiarCamposRepartidores() {
        txtNombreRepartidor.clear();
        txtVehiculoRepartidor.clear();
    }


    @FXML
    private void asignarEnvio() {
        String idEnvio = txtIdEnvioAsignar.getText().trim();
        String idRepartidor = txtIdRepartidorAsignar.getText().trim();

        if (idEnvio.isEmpty() || idRepartidor.isEmpty()) {
            mostrarError("Ingrese ID de envío y repartidor.");
            return;
        }

        if (Administrador.asignarEnvioARepartidor(idEnvio, idRepartidor)) {
            limpiarCamposEnvios();
            cargarDatos();
            mostrarMensaje("Envío asignado correctamente.");
        } else {
            mostrarError("No se pudo asignar el envío.");
        }
    }

    @FXML
    private void reasignarEnvio() {
        String idEnvio = txtIdEnvioAsignar.getText().trim();
        String idRepartidor = txtIdRepartidorAsignar.getText().trim();

        if (idEnvio.isEmpty() || idRepartidor.isEmpty()) {
            mostrarError("Ingrese ID de envío y nuevo repartidor.");
            return;
        }

        if (Administrador.reasignarEnvio(idEnvio, idRepartidor)) {
            limpiarCamposEnvios();
            cargarDatos();
            mostrarMensaje("Envío reasignado correctamente.");
        } else {
            mostrarError("No se pudo reasignar el envío.");
        }
    }

    @FXML
    private void registrarIncidencia() {
        String idEnvio = txtIdEnvioIncidencia.getText().trim();
        if (idEnvio.isEmpty()) {
            mostrarError("Ingrese ID de envío.");
            return;
        }

        if (Administrador.registrarIncidencia(idEnvio)) {
            cargarDatos();
            mostrarMensaje("Incidencia registrada.");
        } else {
            mostrarError("No se pudo registrar la incidencia.");
        }
    }

    @FXML
    private void actualizarEstado() {
        String idEnvio = txtIdEnvioIncidencia.getText().trim();
        if (idEnvio.isEmpty()) {
            mostrarError("Ingrese ID de envío.");
            return;
        }

        if (Administrador.avanzarEstadoEnvio(idEnvio)) {
            cargarDatos();
            mostrarMensaje("Estado avanzado correctamente.");
        } else {
            mostrarError("No se pudo avanzar el estado.");
        }
    }

    private void limpiarCamposEnvios() {
        txtIdEnvioAsignar.clear();
        txtIdRepartidorAsignar.clear();
        txtIdEnvioIncidencia.clear();
    }

    public void cargarDatos() {
        try {
            EmpresaLogistica empresa = EmpresaController.getInstance().getEmpresaLogistica();
            if (empresa == null) {
                System.err.println("EmpresaLogistica es null");
                return;
            }

            List<Cliente> clientes = new ArrayList<>(empresa.getListaClientes());
            List<Repartidor> repartidores = new ArrayList<>(empresa.getListaRepartidores());

            // Cargar clientes
            tablaClientes.setItems(FXCollections.observableArrayList(clientes));

            // Cargar repartidores
            tablaRepartidores.setItems(FXCollections.observableArrayList(repartidores));

            // Cargar envíos
            List<Envio> envios = new ArrayList<>();
            for (Cliente c : clientes) {
                if (c.getEnvios() != null) {
                    envios.addAll(c.getEnvios());
                }
            }
            tablaEnviosGlobales.setItems(FXCollections.observableArrayList(envios));

            // Actualizar gráficas
            actualizarGraficas();

            System.out.println(" Datos cargados: " +
                    clientes.size() + " clientes, " +
                    repartidores.size() + " repartidores, " +
                    envios.size() + " envíos");

        } catch (Exception e) {
            System.err.println("Error al cargar datos: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private void actualizarGraficas() {
        actualizarGraficaTiempos();
        actualizarGraficaServicios();
        actualizarGraficaIncidencias();
    }

    private void actualizarGraficaTiempos() {
        XYChart.Series<String, Number> serie = new XYChart.Series<>();
        serie.setName("Tiempo promedio (días)");

        // Datos simulados
        String[] meses = {"Ene", "Feb", "Mar", "Abr", "May", "Jun"};
        for (int i = 0; i < 6; i++) {
            serie.getData().add(new XYChart.Data<>(meses[i], (int)(Math.random() * 5) + 1));
        }

        lineChartTiempoPeriodo.getData().setAll(serie);
    }

    private void actualizarGraficaServicios() {
        XYChart.Series<String, Number> serie = new XYChart.Series<>();
        serie.setName("Cantidad");

        int seguro = 0, fragil = 0, firma = 0;

        EmpresaLogistica empresa = EmpresaController.getInstance().getEmpresaLogistica();
        if (empresa != null) {
            for (Cliente c : empresa.getListaClientes()) {
                for (Envio e : c.getEnvios()) {
                    if (e.getServiciosAdicionales() != null) {
                        for (ServicioAdicional s : e.getServiciosAdicionales()) {
                            switch (s) {
                                case SEGURO -> seguro++;
                                case FRAGIL -> fragil++;
                                case FIRMA -> firma++;
                            }
                        }
                    }
                }
            }
        }

        serie.getData().addAll(
                new XYChart.Data<>("Seguro", seguro),
                new XYChart.Data<>("Frágil", fragil),
                new XYChart.Data<>("Firma", firma)
        );

        barChartServicios.getData().clear();
        barChartServicios.getData().add(serie);

    }

    private void actualizarGraficaIncidencias() {
        int norte = 0, sur = 0, centro = 0, rural = 0;

        EmpresaLogistica empresa = EmpresaController.getInstance().getEmpresaLogistica();
        if (empresa != null) {
            for (Cliente c : empresa.getListaClientes()) {
                for (Envio e : c.getEnvios()) {
                    if (e.getEstadoEnvio() == EstadoEnvio.INCIDENCIA) {
                        String zona = e.getZonaEnvio();
                        if (zona != null) {
                            switch (zona.toLowerCase()) {
                                case "norte" -> norte++;
                                case "sur" -> sur++;
                                case "centro" -> centro++;
                                case "rural" -> rural++;
                                default -> centro++;
                            }
                        }
                    }
                }
            }
        }

        ObservableList<PieChart.Data> datos = FXCollections.observableArrayList();
        if (norte > 0) datos.add(new PieChart.Data("Norte", norte));
        if (sur > 0) datos.add(new PieChart.Data("Sur", sur));
        if (centro > 0) datos.add(new PieChart.Data("Centro", centro));
        if (rural > 0) datos.add(new PieChart.Data("Rural", rural));
        if (datos.isEmpty()) datos.add(new PieChart.Data("Sin incidencias", 1));

        pieChartIncidencias.setData(datos);
    }


    private void mostrarMensaje(String mensaje) {
        new Alert(Alert.AlertType.INFORMATION, mensaje).showAndWait();
    }

    private void mostrarError(String mensaje) {
        new Alert(Alert.AlertType.ERROR, mensaje).showAndWait();
    }
    @FXML
    private void eliminarCliente() {
        Cliente clienteSeleccionado = tablaClientes.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado == null) {
            mostrarError("Seleccione un cliente de la tabla.");
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText("¿Eliminar cliente?");
        confirmacion.setContentText("¿Está seguro de que desea eliminar al cliente: " + clienteSeleccionado.getNombre() + "?");

        if (confirmacion.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            String idCliente = clienteSeleccionado.getIdCliente();
            boolean exito = EmpresaController.getInstance().getEmpresaLogistica().eliminarCliente(idCliente);
            if (exito) {
                cargarDatos();
                mostrarMensaje("Cliente eliminado correctamente.");
            } else {
                mostrarError("No se pudo eliminar el cliente.");
            }
        }
    }
    @FXML
    private void eliminarRepartidor() {
        Repartidor repartidorSeleccionado = tablaRepartidores.getSelectionModel().getSelectedItem();
        if (repartidorSeleccionado == null) {
            mostrarError("Seleccione un repartidor de la tabla.");
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText("¿Eliminar repartidor?");
        confirmacion.setContentText("¿Está seguro de que desea eliminar al repartidor: " + repartidorSeleccionado.getNombre() + "?");

        if (confirmacion.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            String idRepartidor = repartidorSeleccionado.getIdRepartidor();
            boolean exito = EmpresaController.getInstance().getEmpresaLogistica().eliminarRepartidor(idRepartidor);
            if (exito) {
                cargarDatos();
                mostrarMensaje("Repartidor eliminado correctamente.");
            } else {
                mostrarError("No se pudo eliminar el repartidor.");
            }
        }
    }
}