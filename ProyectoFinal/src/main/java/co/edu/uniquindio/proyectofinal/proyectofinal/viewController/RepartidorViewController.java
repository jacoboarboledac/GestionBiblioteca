package co.edu.uniquindio.proyectofinal.proyectofinal.viewController;

import co.edu.uniquindio.proyectofinal.proyectofinal.App;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.DisponibilidadRepartidor;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Envio;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Repartidor;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class RepartidorViewController implements Initializable {

    @FXML
    private App app;


    @FXML private TextField txtIdRepartidor;
    @FXML private TextField txtNombreRepartidor;
    @FXML private TextField txtVehiculo;
    @FXML private ComboBox<String> comboDisponibilidad;
    @FXML private Button btnCrearRepartidor;
    @FXML private Button btnActualizarRepartidor;
    @FXML private Button btnEliminarRepartidor;
    @FXML private TableView<Repartidor> tablaRepartidores;
    @FXML private TableColumn<Repartidor, String> colIdRepartidor;
    @FXML private TableColumn<Repartidor, String> colNombreRepartidor;
    @FXML private TableColumn<Repartidor, String> colVehiculo;
    @FXML private TableColumn<Repartidor, String> colDisponibilidad;


    @FXML private Button btnCargarEnvios;
    @FXML private TableView<Envio> tablaEnviosAsignados;
    @FXML private TableColumn<Envio, String> colIdEnvioAsignado;
    @FXML private TableColumn<Envio, String> colClienteAsignado;
    @FXML private TableColumn<Envio, String> colEstadoAsignado;
    @FXML private TableColumn<Envio, String> colDireccionAsignado;



    public void setApp(App app) {
        this.app = app;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        comboDisponibilidad.setItems(FXCollections.observableArrayList(
                "Activo", "Inactivo", "En ruta"
        ));



        colIdRepartidor.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getIdRepartidor()));
        colNombreRepartidor.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getNombre()));
        colVehiculo.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getZonaCobertura())); // zona como vehículo
        colDisponibilidad.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getDisponibilidadRepartidor().name()));



        colIdEnvioAsignado.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getIdEnvio()));
        colClienteAsignado.setCellValueFactory(cell ->
                new SimpleStringProperty("Cliente: " + cell.getValue().getIdCliente()));
        colEstadoAsignado.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getEstadoEnvio().name()));
        colDireccionAsignado.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getDireccionDestino()));


        tablaRepartidores.setItems(FXCollections.observableArrayList(new ArrayList<>()));
        tablaEnviosAsignados.setItems(FXCollections.observableArrayList(new ArrayList<>()));

        tablaRepartidores.getSelectionModel().selectedItemProperty().addListener((obs, old, nuevo) -> {
            if (nuevo != null) {
                txtIdRepartidor.setText(nuevo.getIdRepartidor());
                txtNombreRepartidor.setText(nuevo.getNombre());
                txtVehiculo.setText(nuevo.getZonaCobertura());

                String disponibilidad = nuevo.getDisponibilidadRepartidor().name();
                switch (disponibilidad) {
                    case "ACTIVO" -> comboDisponibilidad.setValue("Activo");
                    case "INACTIVO" -> comboDisponibilidad.setValue("Inactivo");
                    case "EN_RUTA" -> comboDisponibilidad.setValue("En ruta");
                }
            }
        });


        cargarDatos();
    }


    public void cargarDatos() {
        if (app == null) {
            System.err.println("App es null en cargarDatos()");
            return;
        }

        try {
            List<Repartidor> repartidores = app.getEmpresaLogistica().getListaRepartidores();
            ObservableList<Repartidor> obs = FXCollections.observableArrayList(repartidores);
            tablaRepartidores.setItems(obs);
            // ... resto de la lógica
        } catch (Exception e) {
            e.printStackTrace(); // ← Verás el error real aquí
        }
    }


    @FXML
    private void crearRepartidor() {
        try {
            String id = txtIdRepartidor.getText().trim();
            String nombre = txtNombreRepartidor.getText().trim();
            String vehiculo = txtVehiculo.getText().trim(); // zonaCobertura
            String telefono = "123456789"; // temporal, o añade campo
            String contrasenia = "12345";  // temporal
            String disponibilidadStr = comboDisponibilidad.getValue();

            if (id.isEmpty() || nombre.isEmpty() || vehiculo.isEmpty() || disponibilidadStr == null) {
                new Alert(Alert.AlertType.ERROR, "Complete todos los campos.").showAndWait();
                return;
            }

            DisponibilidadRepartidor disponibilidad = parseDisponibilidad(disponibilidadStr);
            Repartidor nuevo = new Repartidor(nombre, telefono, id, "DOC-" + id, vehiculo, disponibilidad, contrasenia);

            boolean exito = app.getEmpresaLogistica().agregarRepartidor(nuevo, id);
            if (exito) {
                limpiarFormularioRepartidor();
                cargarDatos();
                new Alert(Alert.AlertType.INFORMATION, "Repartidor creado con éxito.").showAndWait();
            } else {
                new Alert(Alert.AlertType.ERROR, "Error al crear el repartidor.").showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).showAndWait();
        }
    }

    @FXML
    private void actualizarRepartidor() {
        Repartidor seleccionado = tablaRepartidores.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            new Alert(Alert.AlertType.WARNING, "Seleccione un repartidor.").showAndWait();
            return;
        }

        String nombre = txtNombreRepartidor.getText().trim();
        String vehiculo = txtVehiculo.getText().trim();
        String disponibilidadStr = comboDisponibilidad.getValue();

        if (nombre.isEmpty() || vehiculo.isEmpty() || disponibilidadStr == null) {
            new Alert(Alert.AlertType.ERROR, "Complete los campos.").showAndWait();
            return;
        }

        DisponibilidadRepartidor disponibilidad = parseDisponibilidad(disponibilidadStr);
        boolean exito = app.getEmpresaLogistica().actualizarRepartidor(
                seleccionado.getIdRepartidor(),
                nombre,
                seleccionado.getNumTelefono(),
                vehiculo,
                disponibilidad
        );

        if (exito) {
            limpiarFormularioRepartidor();


            tablaRepartidores.getColumns().forEach(col -> {
                col.setVisible(false);
                col.setVisible(true);
            });

            new Alert(Alert.AlertType.INFORMATION, "Repartidor actualizado.").showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR, "No se pudo actualizar.").showAndWait();
        }
    }

    @FXML
    private void eliminarRepartidor() {
        Repartidor seleccionado = tablaRepartidores.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            new Alert(Alert.AlertType.WARNING, "Seleccione un repartidor.").showAndWait();
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION,
                "¿Eliminar al repartidor " + seleccionado.getNombre() + "?",
                ButtonType.YES, ButtonType.NO);
        confirmacion.setHeaderText("Confirmar eliminación");

        confirmacion.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                boolean exito = app.getEmpresaLogistica().eliminarRepartidor(seleccionado.getIdRepartidor());
                if (exito) {
                    limpiarFormularioRepartidor();
                    cargarDatos();
                    new Alert(Alert.AlertType.INFORMATION, "Repartidor eliminado.").showAndWait();
                } else {
                    new Alert(Alert.AlertType.ERROR, "No se pudo eliminar.").showAndWait();
                }
            }
        });
    }


    private DisponibilidadRepartidor parseDisponibilidad(String str) {
        return switch (str.toLowerCase()) {
            case "activo" -> DisponibilidadRepartidor.ACTIVO;
            case "inactivo" -> DisponibilidadRepartidor.INACTIVO;
            case "en ruta" -> DisponibilidadRepartidor.EN_RUTA;
            default -> DisponibilidadRepartidor.ACTIVO;
        };
    }

    private void limpiarFormularioRepartidor() {
        txtIdRepartidor.clear();
        txtNombreRepartidor.clear();
        txtVehiculo.clear();
        comboDisponibilidad.getSelectionModel().clearSelection();
    }

    
    @FXML
    private void cargarEnviosAsignados() {
        Repartidor rep = app.getRepartidorActual();
        List<Envio> envios = rep.consultarEnviosAsignados(); // Devuelve copia de la lista

        // ✅ Asignar directamente a la tabla
        tablaEnviosAsignados.setItems(FXCollections.observableArrayList(envios));

        new Alert(Alert.AlertType.INFORMATION, "Cargados " + envios.size() + " envíos.").showAndWait();
    }

}