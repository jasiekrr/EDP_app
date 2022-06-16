package com.example.edpapp.controllers;

import com.example.edpapp.Main;
import com.example.edpapp.dto.NewGameDTO;
import com.example.edpapp.models.NewGame;
import com.example.edpapp.repositories.NewGameRepository;
import com.example.edpapp.repositories.NewGameRepositoryGuiceModule;
import com.example.edpapp.specials.MapCoordinator;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.text.DecimalFormat;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class NewGameSettingsController implements Initializable {
    @FXML
    private AnchorPane settingsAnchorPane;
    @FXML
    private ChoiceBox<String> levelChoiceBox;
    
    @FXML
    private Circle romeChooser;
    @FXML
    private Circle carthageChooser;
    @FXML
    private Circle greeksChooser;

    @FXML
    private Button romeButton;
    @FXML
    private Button carthageButton;
    @FXML
    private Button greeksButton;
    @FXML
    private Button backToMenuButton;
    @FXML
    private TextField townNameTextField;
    @FXML
    private Button startGameButton;
    @FXML
    private TextField longitudeText;
    @FXML
    private TextField latitudeText;
    @FXML
    private ImageView map;


    private String chosenFaction;

    private NewGameDTO newGameDTO;

    NewGameRepository newGameRepository;

    private String[] levels = {"sandbox", "easy", "medium", "hard"};

    public NewGameSettingsController() {
        newGameDTO = new NewGameDTO();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        disableAllChoosers();

        Injector injector = Guice.createInjector(new NewGameRepositoryGuiceModule());
        newGameRepository = injector.getInstance(NewGameRepository.class);

        levelChoiceBox.setValue("sandbox");
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
    public void onActionBackToMenu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainMenu.fxml"));

        Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void onActionRomeClicked(ActionEvent event){
        romeChooser.setVisible(true);
        carthageChooser.setVisible(false);
        greeksChooser.setVisible(false);

        checkIfTouched();

        chosenFaction = "rome";

    }
    public void onActionCarthageClicked(ActionEvent event){
        romeChooser.setVisible(false);
        carthageChooser.setVisible(true);
        greeksChooser.setVisible(false);

        checkIfTouched();

        chosenFaction = "carthage";
    }
    public void onActionGreeksClicked(ActionEvent event){

        romeChooser.setVisible(false);
        carthageChooser.setVisible(false);
        greeksChooser.setVisible(true);

        checkIfTouched();

        chosenFaction = "greeks";
    }
    public void onActionStartGameButton(ActionEvent event) throws IOException {
        acceptChoice();

        NewGame game = new NewGame();
        game.setLevel(this.newGameDTO.difficultyLevel);
        game.setFaction(this.newGameDTO.chosenFaction);
        game.setTownName(this.newGameDTO.townName);
        game.setLocation_y(this.newGameDTO.location_y);
        game.setLocation_x(this.newGameDTO.location_x);

        newGameRepository.postNewGame(game);

        navigateToGame(event);


    }
    public void navigateToGame(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Game.fxml"));

        Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("GAME!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }
    public void onKeyPressedNameButton(KeyEvent event){
        if(event.getCode()== KeyCode.ENTER){
            this.newGameDTO.townName = townNameTextField.getText();
        }
    }
    public void disableAllChoosers(){
        romeChooser.setVisible(false);
        carthageChooser.setVisible(false);
        greeksChooser.setVisible(false);
    }
    public void acceptChoice(){
        this.newGameDTO.townName = townNameTextField.getText();
        this.newGameDTO.difficultyLevel = levelChoiceBox.getValue();
        this.newGameDTO.chosenFaction = chosenFaction;
        this.newGameDTO.location_y = Double.parseDouble(latitudeText.getText());
        this.newGameDTO.location_x = Double.parseDouble(longitudeText.getText());
    }
    public void checkIfTouched(){
        if(romeChooser.isVisible() || carthageChooser.isVisible() || greeksChooser.isVisible()){
            startGameButton.setDisable(false);
        }
    }

    public void onMouseClickedMap(MouseEvent event){
        MapCoordinator mapCoordinator = new MapCoordinator(event.getX(), event.getY());
        DecimalFormat comaSeparatorFormat = (DecimalFormat)DecimalFormat.getNumberInstance(Locale.ENGLISH);

        latitudeText.setText(comaSeparatorFormat.format(mapCoordinator.calculateY()));
        longitudeText.setText(comaSeparatorFormat.format(mapCoordinator.calculateX()));

    }

}
