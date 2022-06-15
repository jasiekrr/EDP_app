package com.example.edpapp;

import com.example.edpapp.repositories.NewGameRepositoryGuiceModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;


public class Main extends Application {


    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {

        Injector injector = Guice.createInjector(new NewGameRepositoryGuiceModule());

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 920, 720);
        stage.setTitle("Sieam");

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();


        DbConnection connector = new DbConnection();
        connector.connect();


    }


    public static void main(String[] args) {
        launch();
    }
}