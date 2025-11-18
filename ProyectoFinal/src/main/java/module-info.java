module co.edu.uniquindio.proyectofinal.proyectofinal {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires layout;
    requires kernel;


    opens co.edu.uniquindio.proyectofinal.proyectofinal to javafx.fxml;
    exports co.edu.uniquindio.proyectofinal.proyectofinal;
    opens co.edu.uniquindio.proyectofinal.proyectofinal.viewController to javafx.fxml;
    exports co.edu.uniquindio.proyectofinal.proyectofinal.viewController;

}