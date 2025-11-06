package co.edu.uniquindio.proyectofinal.proyectofinal.viewController;

import co.edu.uniquindio.proyectofinal.proyectofinal.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginViewController {
    App app;

    @FXML
    private Button btn_login;

    @FXML
    private TextField txt_contraseña;

    @FXML
    private TextField txt_usuario;

    @FXML
    void onLogin(ActionEvent event) {
      login();
    }
    //Metodo para verificar usuario y contrasenia para mostrar la ventana principal
    public void login() {
      app.openViewGestor();
    }
    public void setApp(App app) {
        this.app = app;
    }

}

