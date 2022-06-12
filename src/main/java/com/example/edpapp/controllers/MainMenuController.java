package com.example.edpapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
        }
    }
}