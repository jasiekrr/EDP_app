module com.example.edpapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.kordamp.bootstrapfx.core;
    requires org.postgresql.jdbc;

    opens com.example.edpapp to javafx.fxml;
    exports com.example.edpapp;
    exports com.example.edpapp.controllers;
    opens com.example.edpapp.controllers to javafx.fxml;
}