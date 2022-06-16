package com.example.edpapp.controllers;

import com.example.edpapp.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GameController {
    @FXML
    public Button saveGameButton;
    @FXML
    public Button backToMenuButton;
    @FXML
    AnchorPane gamePane;
    @FXML
    ImageView castle;


    public void onActionSaveGame(ActionEvent event){
        //navigate to save game screen
        saveGame();
    }
    public void saveGame(){
        //save game
    }
    public void onActionBackToMenu(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Are you sure you want to leave?");
        if(alert.showAndWait().get() == ButtonType.YES){
            Stage stage = (Stage)gamePane.getScene().getWindow();
            stage.close();
        }


        saveGame();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainMenu.fxml"));

        Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }







}
