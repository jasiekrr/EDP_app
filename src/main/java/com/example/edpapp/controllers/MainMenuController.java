package com.example.edpapp.controllers;

import com.example.edpapp.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {
    @FXML
    public Button btnLoad;
    @FXML
    Button btnExit;

    @FXML
    AnchorPane mainMenuPane;

    public MainMenuController(){

    }

    @FXML
    public void onActionExit(ActionEvent event){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Are you sure you want to leave?");

        if(alert.showAndWait().get() == ButtonType.OK){
            Stage stage = (Stage)mainMenuPane.getScene().getWindow();
            stage.close();
            System.exit(0);
        }
    }
    public void onActionLoad(ActionEvent event) throws IOException {
        //navigate to load game screen
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("View/LoadGame.fxml"));

        Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("GAME!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

        @FXML
    public void onActionNewGame(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("View/NewGameSettings.fxml"));

        Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("New Game Settings");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}