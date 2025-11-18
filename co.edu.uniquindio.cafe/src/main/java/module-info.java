module co.edu.uniquindio.cafe.cafe {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens co.edu.uniquindio.cafe.cafe to javafx.fxml;
    exports co.edu.uniquindio.cafe.cafe;
}