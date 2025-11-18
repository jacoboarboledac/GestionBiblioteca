module co.edu.uniquindio.proyectofinal2.proyectofinal2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.proyectofinal2.proyectofinal2 to javafx.fxml;
    exports co.edu.uniquindio.proyectofinal2.proyectofinal2;
    exports co.edu.uniquindio.proyectofinal2.proyectofinal2.model;
    opens co.edu.uniquindio.proyectofinal2.proyectofinal2.model to javafx.fxml;
}