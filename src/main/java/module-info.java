module com.example.edpapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.kordamp.bootstrapfx.core;
    requires org.postgresql.jdbc;
    requires org.hibernate.orm.core;
    requires javax.persistence;
    requires java.naming;
    requires jakarta.persistence;
    requires com.google.guice;

    opens com.example.edpapp to javafx.fxml;
    exports com.example.edpapp.repositories to com.google.guice;
    exports com.example.edpapp;
    exports com.example.edpapp.controllers;
    exports com.example.edpapp.models;
    opens com.example.edpapp.models;
    opens com.example.edpapp.controllers to javafx.fxml;
}