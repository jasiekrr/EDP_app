package com.example.edpapp.controllers;

import com.example.edpapp.Main;
import com.example.edpapp.models.GameStat;
import com.example.edpapp.models.NewGame;
import com.example.edpapp.repositories.NewGameRepository;
import com.example.edpapp.repositories.NewGameRepositoryGuiceModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    private GameStat gameStat;
    private NewGame newGame;
    @FXML
    private Button saveGameButton;
    @FXML
    private Button backToMenuButton;
    @FXML
    private AnchorPane gamePane;
    @FXML
    private ImageView castle;
    @FXML
    private Label specialUnitName;
    @FXML
    private ImageView specialUnitIcon;


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
        if(alert.showAndWait().get() == ButtonType.OK){
            saveGame();
            Stage stage = (Stage)gamePane.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainMenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Main Menu");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
    }
    public NewGame uploadNewGame(){
        NewGameRepository newGameRepository;
        Injector injector = Guice.createInjector(new NewGameRepositoryGuiceModule());
        newGameRepository = injector.getInstance(NewGameRepository.class);
        return newGameRepository.getLastNewGame();
    }
    public void loadSpecialUnitLabel(){
        switch (newGame.getFaction()) {
            case "rome" -> specialUnitName.setText("Legions");
            case "carthage" -> specialUnitName.setText("Sacred Band");
            case "greeks" -> specialUnitName.setText("Hoplite Phalanx");
        }
    }
    public void loadSpecialUnitImage(){
        System.out.println(newGame.getFaction());
        switch (newGame.getFaction()) {
            case "rome" -> specialUnitIcon.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("src/main/resources/icons/legionary.png"))));
            case "carthage" -> specialUnitIcon.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("src/main/resources/icons/sacredBand.png"))));
            case "greeks" -> specialUnitIcon.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("src/main/resources/icons/hoplitePhalanx.png"))));
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newGame = uploadNewGame();
        loadSpecialUnitLabel();
        loadSpecialUnitImage();
    }
}
