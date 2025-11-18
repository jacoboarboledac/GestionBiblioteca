package co.edu.uniquindio.proyectofinal.proyectofinal;

import co.edu.uniquindio.proyectofinal.proyectofinal.controller.EmpresaController;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Administrador;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Cliente;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.EmpresaLogistica;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Repartidor;
import co.edu.uniquindio.proyectofinal.proyectofinal.viewController.ClienteViewController;
import co.edu.uniquindio.proyectofinal.proyectofinal.viewController.GestorAplicacionViewController;
import co.edu.uniquindio.proyectofinal.proyectofinal.viewController.LoginViewController;
import co.edu.uniquindio.proyectofinal.proyectofinal.viewController.RepartidorViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private Stage primaryStage;
    public static EmpresaLogistica empresaLogistica = new EmpresaLogistica("EmpresaLogistica", "1234");
    private Cliente clienteActual;
    private Repartidor repartidorActual;
    private Administrador administradorActual;
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Login");
        openViewPrincipal();
    }

    private void openViewPrincipal() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("login.fxml"));
            AnchorPane rootLayout= (AnchorPane) loader.load();
            LoginViewController loginController = loader.getController();
            loginController.setApp(this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
    public void openViewGestor() {
        try {
            System.out.println("Cargando vista Administrador... Stage: " + primaryStage);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("gestor-aplicacion.fxml"));
            TabPane rootLayout = loader.load(); // ✅ CORREGIDO

            GestorAplicacionViewController controller = loader.getController();
            controller.setApp(this);
            controller.cargarDatos();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestor de Aplicación");
            primaryStage.show();

            System.out.println("Vista Administrador cargada correctamente.");
        } catch (IOException e) {
            System.err.println("Error al cargar vista de gestor: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void openViewCliente() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("cliente.fxml"));
            AnchorPane rootLayout = loader.load();

            ClienteViewController controller = loader.getController();
            controller.setApp(this);      // ← primero setApp

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            controller.cargarDatos();
            controller.cargarComprobantes();
            controller.cargarDirecciones();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // En App.java
    // En App.java
    public void openViewRepartidor() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("repartidor.fxml"));
            Parent root = loader.load();

            RepartidorViewController controller = loader.getController();
            controller.setApp(this);
            controller.cargarDatos(); // ✅ Carga los datos AQUÍ, no en initialize()

            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace(); // ← ¡ESTO TE MOSTRARÁ LA EXCEPCIÓN REAL!
        }
    }
    public Cliente getClienteActual() { return clienteActual; }
    public Repartidor getRepartidorActual() { return repartidorActual; }
    public Administrador getAdministradorActual() { return administradorActual; }


    public void setClienteActual(Cliente cliente) { this.clienteActual = cliente; }
    public void setRepartidorActual(Repartidor repartidor) { this.repartidorActual = repartidor; }
    public void setAdministradorActual(Administrador admin) { this.administradorActual = admin; }
    public EmpresaLogistica getEmpresaLogistica() {
        return EmpresaController.getInstance().getEmpresaLogistica();
    }
}
