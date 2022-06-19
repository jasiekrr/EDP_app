package com.example.edpapp.controllers;

import com.example.edpapp.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainMenuController {

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