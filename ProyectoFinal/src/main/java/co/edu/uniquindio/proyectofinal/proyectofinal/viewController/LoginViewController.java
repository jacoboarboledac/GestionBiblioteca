package co.edu.uniquindio.proyectofinal.proyectofinal.viewController;

import co.edu.uniquindio.proyectofinal.proyectofinal.App;
import co.edu.uniquindio.proyectofinal.proyectofinal.controller.EmpresaController;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class LoginViewController {

    @FXML
    private TextField txt_usuario;      // Aquí se ingresa el NOMBRE
    @FXML
    private TextField txt_contraseña;   // Aquí va la contraseña

    private App app;
    private EmpresaController empresaController = EmpresaController.getInstance();

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    private void onLogin(ActionEvent event) {
        login();
    }

    private void login() {
        String nombre = txt_usuario.getText().trim();
        String contrasenia = txt_contraseña.getText();

        if (nombre.isEmpty() || contrasenia.isEmpty()) {
            mostrarError("Por favor ingrese nombre y contraseña.");
            return;
        }

        Usuario usuario = empresaController.getEmpresaLogistica()
                .obtenerUsuarioPorCredenciales(nombre, contrasenia);

        if (usuario == null) {
            mostrarError("Nombre o contraseña incorrectos.");
            return;
        }

        // Asignar según el tipo real y abrir la vista
        if (usuario instanceof Cliente cliente) {
            app.setClienteActual(cliente);
            app.openViewCliente();
        } else if (usuario instanceof Repartidor repartidor) {
            app.setRepartidorActual(repartidor);
            app.openViewRepartidor();
        } else if (usuario instanceof Administrador administrador) {
            app.setAdministradorActual(administrador);
            app.openViewGestor();
        } else {
            mostrarError("Tipo de usuario no soportado.");
        }
    }

    private void mostrarError(String mensaje) {
        new Alert(Alert.AlertType.ERROR, mensaje).showAndWait();
    }
}