package com.example.edpapp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewGameSettingsController implements Initializable {
    @FXML
    private AnchorPane settingsAnchorPane;
    @FXML
    private ChoiceBox<String> levelChoiceBox;

    @FXML
    private Button romeButton;
    @FXML
    private Button carthageButton;
    @FXML
    private Button greeksButton;


    private String[] levels = {"sandbox", "easy", "medium", "hard"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        levelChoiceBox.setValue("Choose difficulty level");
        levelChoiceBox.getItems().addAll(levels);

        try {
            loadFactions();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadFactions() throws FileNotFoundException {
        Image rome = new Image(new FileInputStream("src/main/resources/icons/rome.png"));
        ImageView romeImageView = new ImageView(rome);

        romeImageView.setFitHeight(80);
        romeImageView.setPreserveRatio(true);

        romeButton.setGraphic(romeImageView);

        Image carthage = new Image(new FileInputStream("src/main/resources/icons/carthage.png"));
        ImageView carthageImageView = new ImageView(carthage);

        carthageImageView.setFitHeight(80);
        carthageImageView.setPreserveRatio(true);

        carthageButton.setGraphic(carthageImageView);

        Image greece = new Image(new FileInputStream("src/main/resources/icons/greece.png"));
        ImageView greeceImageView = new ImageView(greece);

        greeceImageView.setFitHeight(80);
        greeceImageView.setPreserveRatio(true);

        greeksButton.setGraphic(greeceImageView);

    }
}
