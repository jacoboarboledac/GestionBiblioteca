module co.edu.uniquindio.cafe.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.cafe.demo to javafx.fxml;
    exports co.edu.uniquindio.cafe.demo;
}