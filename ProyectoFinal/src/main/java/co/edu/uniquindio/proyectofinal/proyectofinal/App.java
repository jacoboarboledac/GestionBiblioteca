package co.edu.uniquindio.proyectofinal.proyectofinal;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.EmpresaLogistica;
import co.edu.uniquindio.proyectofinal.proyectofinal.viewController.GestorAplicacionViewController;
import co.edu.uniquindio.proyectofinal.proyectofinal.viewController.LoginViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private Stage primaryStage;
    public static EmpresaLogistica empresaLogistica = new EmpresaLogistica("EmpresaLogistica", "1234");

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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
    public void openViewGestor() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("gestor-aplicacion.fxml"));
            AnchorPane rootLayout= (AnchorPane) loader.load();
            GestorAplicacionViewController gestorAplicacionController = loader.getController();
            gestorAplicacionController.setApp(this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
