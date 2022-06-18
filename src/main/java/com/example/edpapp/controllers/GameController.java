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
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class GameController implements Initializable {

    @FXML
    public ImageView house1;
    @FXML
    public Group housingGroup;
    @FXML
    public Group farmGroup;
    @FXML
    public ImageView port;
    @FXML
    public Group towersgroup;
    @FXML
    public ImageView barracks;
    @FXML
    public ImageView mine;
    @FXML
    public ImageView blacksmith;
    @FXML
    public ImageView lumberjack;
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
    @FXML
    private Label foodLabel;
    @FXML
    private Label woodLabel;
    @FXML
    private Label stoneLabel;
    @FXML
    private Label goldLabel;


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
    public void loadSpecialUnitImage() throws FileNotFoundException {

        InputStream stream;

        switch (newGame.getFaction()) {
            case "rome" -> {
                stream = new FileInputStream("src/main/resources/icons/legionary.png");
                specialUnitIcon.setImage(new Image(stream));
            }
            case "carthage" -> {
                stream = new FileInputStream("src/main/resources/icons/sacredBand.png");
                specialUnitIcon.setImage(new Image(stream));
            }
            case "greeks" -> {
                stream = new FileInputStream("src/main/resources/icons/hoplite.png");
                specialUnitIcon.setImage(new Image(stream));
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newGame = uploadNewGame();

        loadSpecialUnitLabel();
        try {
            loadSpecialUnitImage();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public void setGame(GameStat loadNewGame) {
        this.gameStat = loadNewGame;
        initBuildings();


        unleashTimer();
    }
    public void unleashTimer(){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateResources();
                updateDisplay();
            }
        }, 0, 10000);
    }
    public void updateDisplay(){
        foodLabel.setText(String.valueOf(gameStat.getFood()));
        woodLabel.setText(String.valueOf(gameStat.getWood()));
        stoneLabel.setText(String.valueOf(gameStat.getStone()));
        goldLabel.setText(String.valueOf(gameStat.getGold()));
    }
    public void updateResources() {
        gameStat.setFood(gameStat.getFood() + gameStat.getFoodperminute() / 6);
        gameStat.setWood(gameStat.getWood() + gameStat.getWoodperminute() / 6);
        gameStat.setStone(gameStat.getStone() + gameStat.getStoneperminute() / 6);
        gameStat.setGold(gameStat.getGold() + gameStat.getGoldperminute() / 6);
    }
    public void onMouseHovered(MouseEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        imageView.setEffect(new Glow(0.9));
        imageView.setOnMouseExited(event1 -> imageView.setEffect(new Glow(0.5)));
    }
    public void initBuildings(){
        for(int i = 0; i < gameStat.getHousinglevel(); i++){
            housingGroup.getChildren().get(i).setVisible(true);
        }
        castle.setVisible(true);
        for(int i = 0; i < gameStat.getFarmlevel(); i++){
            farmGroup.getChildren().get(i).setVisible(true);
        }
        port.setVisible(gameStat.getPortlevel() != 0);
        for(int i = 0; i < gameStat.getWatchtowerlevel(); i++){
            towersgroup.getChildren().get(i).setVisible(true);
        }
        barracks.setVisible(gameStat.getBarrackslevel() != 0);
        lumberjack.setVisible(gameStat.getLumberjacklevel() != 0);
        mine.setVisible(gameStat.getMinelevel() != 0);
        blacksmith.setVisible(gameStat.getBlacksmithlevel() != 0);


    }
}
