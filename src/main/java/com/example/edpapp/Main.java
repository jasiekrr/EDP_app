package com.example.edpapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 920, 720);
        stage.setTitle("Sieam");

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        testConnection();
    }

    public void testConnection() throws ClassNotFoundException {
        String connectionString;
        Connection connection;
        try{
            Class.forName("org.postgresql.Driver");
            connectionString = "jdbc:postgresql://localhost:5432/gamesDb";
            connection = DriverManager.getConnection(connectionString, "postgres", "password");
            System.out.println("connection created!");
        } catch (SQLException e) {
            System.out.println("wystapil blad");
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        launch();
    }
}