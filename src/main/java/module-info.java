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
    requires com.google.common;
    requires retrofit2;
    requires javax.annotation;
    requires com.google.gson;
    requires retrofit2.converter.gson;

    opens com.example.edpapp to javafx.fxml;
    exports com.example.edpapp.repositories to com.google.guice;
    exports com.example.edpapp;
    exports com.example.edpapp.controllers;
    exports com.example.edpapp.models;
    exports com.example.edpapp.specials to com.google.guice;
    exports com.example.edpapp.Events to com.google.common;
    exports com.example.edpapp.dto;
    opens com.example.edpapp.models;
    opens com.example.edpapp.controllers to javafx.fxml;
    exports com.example.edpapp.api;
    opens com.example.edpapp.api to javafx.fxml;
}