module com.example.demo_javafx03 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.demo_javafx03 to javafx.fxml;
    opens com.example.demo_javafx03.controller to javafx.fxml;
    exports com.example.demo_javafx03;
}