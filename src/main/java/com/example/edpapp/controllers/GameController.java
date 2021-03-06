package com.example.edpapp.controllers;

import com.example.edpapp.Events.*;
import com.example.edpapp.Main;
import com.example.edpapp.api.*;
import com.example.edpapp.api.Weather;
import com.example.edpapp.customControls.UpgradeControl;
import com.example.edpapp.models.BuildingsCostsCalculator;
import com.example.edpapp.models.GameStat;
import com.example.edpapp.models.NewGame;
import com.example.edpapp.models.ResourcesPerMinuteCalculator;
import com.example.edpapp.repositories.GameStatRepository;
import com.example.edpapp.repositories.NewGameRepository;
import com.example.edpapp.repositories.NewGameRepositoryGuiceModule;
import com.example.edpapp.specials.PropertiesManager;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import retrofit2.Call;
import retrofit2.Callback;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import com.google.common.eventbus.EventBus;

public class GameController implements Initializable {
    @FXML
    public Button buildCastleButton;
    @FXML
    public Button buildHousingButton;
    @FXML
    public Button buildFarmButton;
    @FXML
    public ProgressBar farmProgressBar;
    @FXML
    public Button pauseButton;
    @FXML
    public Label woodGainLabel;
    @FXML
    public Label stoneGainLabel;
    @FXML
    public Label ironGainLabel;
    @FXML
    public Label goldGainLabel;
    @FXML
    public Label workersGainLabel;
    @FXML
    public Label ironLabel;
    @FXML
    public Label foodGainLabel;
    @FXML
    public ProgressBar mineProgressBar;
    @FXML
    public ProgressBar blacksmithProgressBar;
    @FXML
    public ProgressBar castleProgressBar;
    @FXML
    public ProgressBar housingProgressBar;
    @FXML
    public ProgressBar towerProgressBar;
    @FXML
    public ProgressBar barracksProgressBar;
    @FXML
    public ProgressBar portProgressBar;
    @FXML
    public ProgressBar lumberjackProgressBar;
    @FXML
    public Label ironCostsLabel;
    @FXML
    public Label foodCostsLabel;
    @FXML
    public Label woodCostsLabel;
    @FXML
    public Label stoneCostsLabel;
    @FXML
    public Label goldCostsLabel;
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
    @FXML
    public Label lightLabel;
    @FXML
    public Label mediumLabel;
    @FXML
    public Label heavyLabel;
    @FXML
    public Label specialUnitLabel;
    @FXML
    public Button buildLumberjackButton;
    @FXML
    public Button buildBlacksmithButton;
    @FXML
    public Button buildMineButton;
    @FXML
    public Button buildBarracksButton;
    @FXML
    public Button buildTowerButton;
    @FXML
    public Button buildPortButton;
    @FXML
    public Label LocationLabel;
    @FXML
    public AnchorPane gameplayPane;
    @FXML
    public ImageView tower;
    @FXML
    public ImageView farm;
    public Button playButton;
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
    @FXML
    private Label temperatureLabel;

    @FXML
    private Label workersLabel;

    private EventBus eventBus;
    private BuildingsCostsCalculator buildingsCostsCalculator;
    private Map<String, Double> resourcesMap;
    private int numericDifficultyLevel;
    private GameStat gameStat;
    private NewGame newGame;
    private ResourcesPerMinuteCalculator resourcesPerMinuteCalculator;
    private PauseGameEvent pauseGameEvent;
    private PauseEventListener pauseEventListener;
    private Timeline timeline;
    private Timeline timelineWeather;
    private NewGameRepository newGameRepository;
    private GameStatRepository gameStatRepository;
    private UpdateWeatherDisplayListener updateWeatherDisplayListener;
    private PropertiesManager propertiesManager;
    private Weather weather;

    public GameController() {
    }

    public void onActionSaveGame(ActionEvent event){
        //navigate to save game screen
        saveGame();
        //show alert that game is saved
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game saved");
        alert.setHeaderText("Game saved");
        alert.setContentText("Game saved");
        alert.showAndWait();
    }
    public void saveGame(){
        gameStat.setCreatedon(new Timestamp(System.currentTimeMillis()));
        gameStatRepository.postGame(gameStat);
    }
    public void onActionBackToMenu(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Are you sure you want to leave?");
        if(alert.showAndWait().get() == ButtonType.OK){
            saveGame();
            timeline.stop();
            Stage stage = (Stage)gamePane.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("View/MainMenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("WeatherMain Menu");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
    }
    public NewGame uploadNewGame(){

        return newGameRepository.getLastNewGame();
    }
    public void unleashWeatherTimer(){
        timelineWeather = new Timeline(new KeyFrame(Duration.seconds(600), event -> {
            updateWeather();
        }));
        timelineWeather.setCycleCount(Animation.INDEFINITE);
        timelineWeather.play();
    }

    private void updateWeather() {
        WeatherApiService weatherApiService = WeatherApiManager.getClient().create(WeatherApiService.class);
        System.out.println("updateWeather URL : " + propertiesManager.getProperty("URL"));
        Call<Weather> call = weatherApiService.requestWeather(propertiesManager.getProperty("URL"),gameStat.getNewgame().getLocation_y(), gameStat.getNewgame().getLocation_x());
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Weather> call, retrofit2.Response<Weather> response) {
                if (response.isSuccessful()) {
                    weather = response.body();
                    assert weather != null;

                    eventBus.post(new UpdateWeatherDisplayEvent(weather));
                    gameStat.setBuildingvelocity((Math.pow((weather.getMain().getTemp()-20),2) + 100));


                } else {
                    System.out.println("nie odebrano");
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable throwable) {
                System.out.println(throwable.getMessage());
            }
        });
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
        propertiesManager = PropertiesManager.getInstance();

        Injector injector = Guice.createInjector(new NewGameRepositoryGuiceModule());
        newGameRepository = injector.getInstance(NewGameRepository.class);
        gameStatRepository = injector.getInstance(GameStatRepository.class);

        newGame = uploadNewGame();
        eventBus = new EventBus();

        loadSpecialUnitLabel();
        try {
            loadSpecialUnitImage();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void setGame(GameStat loadNewGame) {
        this.gameStat = loadNewGame;
        resourcesPerMinuteCalculator = new ResourcesPerMinuteCalculator(numericDifficultyLevel, gameStat);
        this.gameStat = resourcesPerMinuteCalculator.getResourcesIncrement(gameStat);

        initBuildings();
        updateUnitsDisplay();
        displayBuildingButtons();
        unleashTimer();
        unleashWeatherTimer();
        this.numericDifficultyLevel = getNumericLevel(gameStat.getNewgame().getLevel());
        this.buildingsCostsCalculator = new BuildingsCostsCalculator(numericDifficultyLevel);
        this.resourcesMap = new HashMap<>();

        updateResourcesGain();

        updateWeather();

        pauseEventListener = new PauseEventListener(timeline, timelineWeather, playButton);
        updateWeatherDisplayListener = new UpdateWeatherDisplayListener(LocationLabel, temperatureLabel);
        eventBus.register(pauseEventListener);
        eventBus.register(updateWeatherDisplayListener);
        pauseGameEvent = new PauseGameEvent(gameStat);
    }
    public int getNumericLevel(String stringLevel){
        int level = 0;
        switch(gameStat.getNewgame().getLevel()){
            case "sandbox" ->{
                level = 1;
            }
            case "easy" ->{
                level = 2;
            }
            case "medium" ->{
                level = 3;
            }
            case "hard" ->{
                level = 4;
            }
        }
        return level;
    }
    public void unleashTimer(){
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {
            updateResources();
            updateResourcesDisplay();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    public void updateResourcesGain(){
        foodGainLabel.setText(String.valueOf(gameStat.getFoodperminute()));
        woodGainLabel.setText(String.valueOf(gameStat.getWoodperminute()));
        stoneGainLabel.setText(String.valueOf(gameStat.getStoneperminute()));
        goldGainLabel.setText(String.valueOf(gameStat.getGoldperminute()));
        ironGainLabel.setText(String.valueOf(gameStat.getIronperminute()));
    }
    public void updateResourcesDisplay(){
        foodLabel.setText(String.valueOf((int)gameStat.getFood()));
        woodLabel.setText(String.valueOf((int)gameStat.getWood()));
        stoneLabel.setText(String.valueOf((int)gameStat.getStone()));
        goldLabel.setText(String.valueOf((int)gameStat.getGold()));
        ironLabel.setText(String.valueOf((int)gameStat.getIron()));
        workersLabel.setText(String.valueOf(gameStat.getBuildingvelocity()));
    }
    public void updateUnitsDisplay(){
        lightLabel.setText(String.valueOf(gameStat.getLightInfantry()));
        mediumLabel.setText(String.valueOf(gameStat.getMediumInfantry()));
        heavyLabel.setText(String.valueOf(gameStat.getHeavyInfantry()));
        specialUnitLabel.setText(String.valueOf(gameStat.getSpecialUnit()));
    }
    public void updateResources() {
        this.gameStat = resourcesPerMinuteCalculator.getResourcesIncrement(gameStat);

        if(gameStat.getFood() + gameStat.getFoodperminute() / 600 <= 0){
            gameStat.setFood(0);
        }
        else{
            gameStat.setFood(gameStat.getFood() + gameStat.getFoodperminute() / 600);
        }
        if(gameStat.getWood() + gameStat.getWoodperminute() / 600 <= 0){
            gameStat.setWood(0);
        }
        else{
            gameStat.setWood(gameStat.getWood() + gameStat.getWoodperminute() / 600);
        }
        if(gameStat.getStone() + gameStat.getStoneperminute() / 600 <= 0){
            gameStat.setStone(0);
        }
        else{
            gameStat.setStone(gameStat.getStone() + gameStat.getStoneperminute() / 600);
        }
        if(gameStat.getGold() + gameStat.getGoldperminute() / 600 <= 0){
            gameStat.setGold(0);
        }
        else{
            gameStat.setGold(gameStat.getGold() + gameStat.getGoldperminute() / 600);
        }
        if(gameStat.getIron() + gameStat.getIronperminute() / 600 <= 0){
            gameStat.setIron(0);
        }
        else{
            gameStat.setIron(gameStat.getIron() + gameStat.getIronperminute() / 600);
        }

        this.resourcesMap.put("food", gameStat.getFood());
        this.resourcesMap.put("wood", gameStat.getWood());
        this.resourcesMap.put("stone", gameStat.getStone());
        this.resourcesMap.put("gold", gameStat.getGold());
        this.resourcesMap.put("iron", gameStat.getIron());


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
    public void displayBuildingButtons(){
        buildBlacksmithButton.setVisible(true);
        buildLumberjackButton.setVisible(true);
        buildMineButton.setVisible(true);
        buildBarracksButton.setVisible(true);
        buildTowerButton.setVisible(true);
        buildPortButton.setVisible(true);
        buildCastleButton.setVisible(true);
        buildHousingButton.setVisible(true);
        buildFarmButton.setVisible(true);

        if(gameStat.getBlacksmithlevel() != 0){
            buildBlacksmithButton.setText("upgrade Blacksmith");
        }
        if(gameStat.getLumberjacklevel() != 0){
            buildLumberjackButton.setText("upgrade Lumberjack");
        }
        if(gameStat.getMinelevel() != 0){
            buildMineButton.setText("upgrade Mine");
        }
        if(gameStat.getBarrackslevel() != 0){
            buildBarracksButton.setText("upgrade Barracks");
        }
        if(gameStat.getWatchtowerlevel() != 0){
            buildTowerButton.setText("upgrade Watchtower");
        }
        if(gameStat.getPortlevel() != 0){
            buildPortButton.setText("upgrade Port");
        }
        if(gameStat.getCastlelevel() != 0){
            buildCastleButton.setText("upgrade Castle");
        }
        if(gameStat.getHousinglevel() != 0){
            buildHousingButton.setText("upgrade Housing");
        }
        if(gameStat.getFarmlevel() != 0){
            buildFarmButton.setText("upgrade Farm");
        }

    }
    public void onBuildLumberjackButton() throws FileNotFoundException {
        buildOrUpgradeBuilding("lumberjack", lumberjackProgressBar, buildLumberjackButton);
    }
    public void updateLumberjack(){
        gameStat.setFood(gameStat.getFood() - buildingsCostsCalculator.getLumberjackCosts((int) gameStat.getLumberjacklevel()).get("food"));
        gameStat.setWood(gameStat.getWood() - buildingsCostsCalculator.getLumberjackCosts((int) gameStat.getLumberjacklevel()).get("wood"));
        gameStat.setStone(gameStat.getStone() - buildingsCostsCalculator.getLumberjackCosts((int) gameStat.getLumberjacklevel()).get("stone"));
        gameStat.setGold(gameStat.getGold() - buildingsCostsCalculator.getLumberjackCosts((int) gameStat.getLumberjacklevel()).get("gold"));
        gameStat.setIron(gameStat.getIron() - buildingsCostsCalculator.getLumberjackCosts((int) gameStat.getLumberjacklevel()).get("iron"));
        gameStat.setLumberjacklevel(gameStat.getLumberjacklevel() + 1);
    }
    public void onBuildBlacksmithButton() throws FileNotFoundException {
        buildOrUpgradeBuilding("blacksmith", blacksmithProgressBar, buildBlacksmithButton);
    }
    public void updateBlacksmith(){
        gameStat.setFood(gameStat.getFood() - buildingsCostsCalculator.getBlacksmithCosts((int)gameStat.getBlacksmithlevel()).get("food"));
        gameStat.setWood(gameStat.getWood() - buildingsCostsCalculator.getBlacksmithCosts((int)gameStat.getBlacksmithlevel()).get("wood"));
        gameStat.setStone(gameStat.getStone() - buildingsCostsCalculator.getBlacksmithCosts((int)gameStat.getBlacksmithlevel()).get("stone"));
        gameStat.setGold(gameStat.getGold() - buildingsCostsCalculator.getBlacksmithCosts((int)gameStat.getBlacksmithlevel()).get("gold"));
        gameStat.setIron(gameStat.getIron() - buildingsCostsCalculator.getBlacksmithCosts((int)gameStat.getBlacksmithlevel()).get("iron"));
        gameStat.setBlacksmithlevel(gameStat.getBlacksmithlevel() + 1);
    }
    public void onBuildPortButton() throws FileNotFoundException {
        buildOrUpgradeBuilding("port", portProgressBar, buildPortButton);
    }
    public void updatePort(){
        gameStat.setFood(gameStat.getFood() - buildingsCostsCalculator.getPortCosts((int)gameStat.getPortlevel()).get("food"));
        gameStat.setWood(gameStat.getWood() - buildingsCostsCalculator.getPortCosts((int)gameStat.getPortlevel()).get("wood"));
        gameStat.setStone(gameStat.getStone() - buildingsCostsCalculator.getPortCosts((int)gameStat.getPortlevel()).get("stone"));
        gameStat.setGold(gameStat.getGold() - buildingsCostsCalculator.getPortCosts((int)gameStat.getPortlevel()).get("gold"));
        gameStat.setIron(gameStat.getIron() - buildingsCostsCalculator.getPortCosts((int)gameStat.getPortlevel()).get("iron"));
        gameStat.setPortlevel(gameStat.getPortlevel() + 1);
    }
    public void onBuildTowerButton() throws FileNotFoundException {
        buildOrUpgradeBuilding("watchtower", towerProgressBar, buildTowerButton);
    }
    public void updateTower(){
        gameStat.setFood(gameStat.getFood() - buildingsCostsCalculator.getWatchtowerCosts((int)gameStat.getWatchtowerlevel()).get("food"));
        gameStat.setWood(gameStat.getWood() - buildingsCostsCalculator.getWatchtowerCosts((int)gameStat.getWatchtowerlevel()).get("wood"));
        gameStat.setStone(gameStat.getStone() - buildingsCostsCalculator.getWatchtowerCosts((int)gameStat.getWatchtowerlevel()).get("stone"));
        gameStat.setGold(gameStat.getGold() - buildingsCostsCalculator.getWatchtowerCosts((int)gameStat.getWatchtowerlevel()).get("gold"));
        gameStat.setIron(gameStat.getIron() - buildingsCostsCalculator.getWatchtowerCosts((int)gameStat.getWatchtowerlevel()).get("iron"));
        gameStat.setWatchtowerlevel(gameStat.getWatchtowerlevel() + 1);
    }
    public void onBuildBarracksButton() throws FileNotFoundException {
        buildOrUpgradeBuilding("barracks", barracksProgressBar, buildBarracksButton);
    }
    public void upDateBarracks(){
        gameStat.setFood(gameStat.getFood() - buildingsCostsCalculator.getBarracksCosts((int)gameStat.getBarrackslevel()).get("food"));
        gameStat.setWood(gameStat.getWood() - buildingsCostsCalculator.getBarracksCosts((int)gameStat.getBarrackslevel()).get("wood"));
        gameStat.setStone(gameStat.getStone() - buildingsCostsCalculator.getBarracksCosts((int)gameStat.getBarrackslevel()).get("stone"));
        gameStat.setGold(gameStat.getGold() - buildingsCostsCalculator.getBarracksCosts((int)gameStat.getBarrackslevel()).get("gold"));
        gameStat.setIron(gameStat.getIron() - buildingsCostsCalculator.getBarracksCosts((int)gameStat.getBarrackslevel()).get("iron"));

        gameStat.setBarrackslevel(gameStat.getBarrackslevel() + 1);
    }
    public void onBuildMineButton() throws FileNotFoundException {
        buildOrUpgradeBuilding("mine", mineProgressBar, buildMineButton);
    }
    public void updateMine(){
        gameStat.setFood(gameStat.getFood() - buildingsCostsCalculator.getMineCosts((int)gameStat.getMinelevel()).get("food"));
        gameStat.setWood(gameStat.getWood() - buildingsCostsCalculator.getMineCosts((int)gameStat.getMinelevel()).get("wood"));
        gameStat.setStone(gameStat.getStone() - buildingsCostsCalculator.getMineCosts((int)gameStat.getMinelevel()).get("stone"));
        gameStat.setGold(gameStat.getGold() - buildingsCostsCalculator.getMineCosts((int)gameStat.getMinelevel()).get("gold"));
        gameStat.setIron(gameStat.getIron() - buildingsCostsCalculator.getMineCosts((int)gameStat.getMinelevel()).get("iron"));
        gameStat.setMinelevel(gameStat.getMinelevel() + 1);
    }
    public void setLabelsToDefault(MouseEvent event, Pane pane) {
        Button button = (Button) event.getSource();
        button.onMouseExitedProperty().set(event1 -> {
            foodCostsLabel.setText("");
            woodCostsLabel.setText("");
            stoneCostsLabel.setText("");
            goldCostsLabel.setText("");
            ironCostsLabel.setText("");
            workersLabel.setText("");
            gameplayPane.getChildren().remove(pane);
        });
    }
    public ImageView makeHousesVisible() throws FileNotFoundException {
        ImageView houses = new ImageView();
        for(int i = 0; i < gameStat.getHousinglevel(); i++){
            houses = (ImageView) housingGroup.getChildren().get(i);
        }

        return houses;
    }
    public void buildOrUpgradeBuilding(String buildingName,ProgressBar progressBar, Button buildButton) throws FileNotFoundException {
        UpdateGainsListener updateGainsListener = new UpdateGainsListener(woodGainLabel, stoneGainLabel, foodGainLabel, goldGainLabel, ironGainLabel);
        eventBus.register(updateGainsListener);

        Map<String, Double> costs = new HashMap<>();
        ImageView imageView = new ImageView();
        switch (buildingName) {
            case "farm" -> {
                costs = buildingsCostsCalculator.getFarmCosts((int) gameStat.getFarmlevel());
                imageView = makeFarmVisible();
            }
            case "blacksmith" -> {
                costs = buildingsCostsCalculator.getBlacksmithCosts((int) gameStat.getBlacksmithlevel());
                imageView = blacksmith;
            }
            case "port" -> {
                costs = buildingsCostsCalculator.getPortCosts((int) gameStat.getPortlevel());
                imageView = port;
            }
            case "watchtower" -> {
                costs = buildingsCostsCalculator.getWatchtowerCosts((int) gameStat.getWatchtowerlevel());
                imageView = makeTowersVisible();
            }
            case "barracks" -> {
                costs = buildingsCostsCalculator.getBarracksCosts((int) gameStat.getBarrackslevel());
                imageView = barracks;
            }
            case "mine" -> {
                costs = buildingsCostsCalculator.getMineCosts((int) gameStat.getMinelevel());
                imageView = mine;
            }
            case "castle" -> {
                costs = buildingsCostsCalculator.getCastleCosts((int) gameStat.getCastlelevel());
                imageView = castle;
            }
            case "housing" -> {
                costs = buildingsCostsCalculator.getHousesCosts((int) gameStat.getHousinglevel());
                imageView = makeHousesVisible();
            }
            case "lumberjack" -> {
                costs = buildingsCostsCalculator.getLumberjackCosts((int) gameStat.getLumberjacklevel());
                imageView = lumberjack;
            }
        }


        for (String resource : resourcesMap.keySet()) {
            if (resourcesMap.get(resource) < costs.get(resource)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Not enough resources");
                alert.setHeaderText("Not enough resources");
                alert.setContentText("You need more resources to build");
                alert.showAndWait();
                return;
            }
        }

        progressBar.setVisible(true);
        progressBar.setProgress(0);

        buildButton.setVisible(false);

        ImageView finalImageView = imageView;
        new Thread(() -> {
            for (int i = 0; i < gameStat.getBuildingvelocity(); i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progressBar.setProgress(progressBar.getProgress() + 0.01);
                if (progressBar.getProgress() >= 1) {
                    progressBar.setVisible(false);
                    buildButton.setVisible(true);
                    finalImageView.setVisible(true);

                    Platform.runLater(
                            () -> {
                                switch (buildingName) {
                                    case "farm" -> {
                                        updateFarm();
                                    }
                                    case "blacksmith" -> {
                                        updateBlacksmith();
                                    }
                                    case "port" -> {
                                        updatePort();
                                    }
                                    case "watchtower" -> {
                                        updateTower();
                                    }
                                    case "barracks" -> {
                                        upDateBarracks();
                                    }
                                    case "mine" -> {
                                        updateMine();
                                    }
                                    case "castle" -> {
                                        updateCastle();
                                    }
                                    case "housing" -> {
                                        try {
                                            updateHousing();
                                        } catch (FileNotFoundException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                    case "lumberjack" -> {
                                        updateLumberjack();
                                    }
                                }
                                eventBus.post(new BuildingBuiltEvent(gameStat));
                            }
                    );
                    return;
                }
            }
        }).start();
        buildButton.setText("upgrade "+buildingName);
    }
    public Pane showArrow(ImageView imageView, int level) throws FileNotFoundException {
        UpgradeControl customControl = new UpgradeControl(true, level);
        Pane pane = customControl.getControl();
        pane.setLayoutX(imageView.getLayoutX());
        pane.setLayoutY(imageView.getLayoutY());

        gameplayPane.getChildren().add(pane);

        return pane;
    }
    public void onMouseHoveredViewLumberjackCost(MouseEvent event) throws FileNotFoundException {
        if(gameStat.getLumberjacklevel() == (int)gameStat.getLumberjacklevel()){
            foodCostsLabel.setText(" - " + buildingsCostsCalculator.getLumberjackCosts((int)gameStat.getLumberjacklevel()).get("food"));
            woodCostsLabel.setText(" - " + buildingsCostsCalculator.getLumberjackCosts((int)gameStat.getLumberjacklevel()).get("wood"));
            stoneCostsLabel.setText(" - " + buildingsCostsCalculator.getLumberjackCosts((int)gameStat.getLumberjacklevel()).get("stone"));
            goldCostsLabel.setText(" - " + buildingsCostsCalculator.getLumberjackCosts((int)gameStat.getLumberjacklevel()).get("gold"));
            ironCostsLabel.setText(" - " + buildingsCostsCalculator.getLumberjackCosts((int)gameStat.getLumberjacklevel()).get("iron"));
        }
        Pane pane = showArrow(lumberjack, (int)gameStat.getLumberjacklevel());
        setLabelsToDefault(event, pane);
    }
    public ImageView makeTowersVisible() {
        ImageView imageView = new ImageView();
        switch ((int)gameStat.getWatchtowerlevel()) {
            case 1 -> imageView = (ImageView) towersgroup.getChildren().get(0);
            case 2 -> imageView = (ImageView) towersgroup.getChildren().get(1);
            default -> imageView = (ImageView) towersgroup.getChildren().get(2);
        }
        return imageView;
    }
    public void onMouseHoveredViewPortCost(MouseEvent event) throws FileNotFoundException {
        if(gameStat.getPortlevel() ==(int)gameStat.getPortlevel()){
            foodCostsLabel.setText(" - " + buildingsCostsCalculator.getPortCosts((int)gameStat.getPortlevel()).get("food"));
            woodCostsLabel.setText(" - " + buildingsCostsCalculator.getPortCosts((int)gameStat.getPortlevel()).get("wood"));
            stoneCostsLabel.setText(" - " + buildingsCostsCalculator.getPortCosts((int)gameStat.getPortlevel()).get("stone"));
            goldCostsLabel.setText(" - " + buildingsCostsCalculator.getPortCosts((int)gameStat.getPortlevel()).get("gold"));
            ironCostsLabel.setText(" - " + buildingsCostsCalculator.getPortCosts((int)gameStat.getPortlevel()).get("iron"));
        }
        Pane pane = showArrow(port, (int)gameStat.getPortlevel());

        setLabelsToDefault(event, pane);
    }
    public void onMouseHoveredViewBlacksmithCost(MouseEvent event) throws FileNotFoundException {
        if(gameStat.getBlacksmithlevel() == (int)gameStat.getBlacksmithlevel()){
            foodCostsLabel.setText(" - " + buildingsCostsCalculator.getBlacksmithCosts((int)gameStat.getBlacksmithlevel()).get("food"));
            woodCostsLabel.setText(" - " + buildingsCostsCalculator.getBlacksmithCosts((int)gameStat.getBlacksmithlevel()).get("wood"));
            stoneCostsLabel.setText(" - " + buildingsCostsCalculator.getBlacksmithCosts((int)gameStat.getBlacksmithlevel()).get("stone"));
            goldCostsLabel.setText(" - " + buildingsCostsCalculator.getBlacksmithCosts((int)gameStat.getBlacksmithlevel()).get("gold"));
            ironCostsLabel.setText(" - " + buildingsCostsCalculator.getBlacksmithCosts((int)gameStat.getBlacksmithlevel()).get("iron"));
        }

        Pane pane = showArrow(blacksmith, (int)gameStat.getBlacksmithlevel());

        setLabelsToDefault(event, pane);
    }
    public void onMouseHoveredViewMineCost(MouseEvent event) throws FileNotFoundException {
        if(gameStat.getMinelevel() == (int)gameStat.getMinelevel()){
            foodCostsLabel.setText(" - " + buildingsCostsCalculator.getMineCosts((int)gameStat.getMinelevel()).get("food"));
            woodCostsLabel.setText(" - " + buildingsCostsCalculator.getMineCosts((int)gameStat.getMinelevel()).get("wood"));
            stoneCostsLabel.setText(" - " + buildingsCostsCalculator.getMineCosts((int)gameStat.getMinelevel()).get("stone"));
            goldCostsLabel.setText(" - " + buildingsCostsCalculator.getMineCosts((int)gameStat.getMinelevel()).get("gold"));
            ironCostsLabel.setText(" - " + buildingsCostsCalculator.getMineCosts((int)gameStat.getMinelevel()).get("iron"));
        }
        Pane pane = showArrow(mine, (int)gameStat.getMinelevel());
        setLabelsToDefault(event,pane);
    }
    public void onMouseHoveredViewWatchtowerCost(MouseEvent event) throws FileNotFoundException {
        if(gameStat.getWatchtowerlevel() == (int)gameStat.getWatchtowerlevel()){
            foodCostsLabel.setText(" - " + buildingsCostsCalculator.getWatchtowerCosts((int)gameStat.getWatchtowerlevel()).get("food"));
            woodCostsLabel.setText(" - " + buildingsCostsCalculator.getWatchtowerCosts((int)gameStat.getWatchtowerlevel()).get("wood"));
            stoneCostsLabel.setText(" - " + buildingsCostsCalculator.getWatchtowerCosts((int)gameStat.getWatchtowerlevel()).get("stone"));
            goldCostsLabel.setText(" - " + buildingsCostsCalculator.getWatchtowerCosts((int)gameStat.getWatchtowerlevel()).get("gold"));
            ironCostsLabel.setText(" - " + buildingsCostsCalculator.getWatchtowerCosts((int)gameStat.getWatchtowerlevel()).get("iron"));
        }
        Pane pane = showArrow(castle, (int)gameStat.getWatchtowerlevel());
        setLabelsToDefault(event, pane);
    }
    public void onMouseHoveredViewBarracksCost(MouseEvent event) throws FileNotFoundException {
        if(gameStat.getBarrackslevel() == (int)gameStat.getBarrackslevel()){
            foodCostsLabel.setText(" - " + buildingsCostsCalculator.getBarracksCosts((int)gameStat.getBarrackslevel()).get("food"));
            woodCostsLabel.setText(" - " + buildingsCostsCalculator.getBarracksCosts((int)gameStat.getBarrackslevel()).get("wood"));
            stoneCostsLabel.setText(" - " + buildingsCostsCalculator.getBarracksCosts((int)gameStat.getBarrackslevel()).get("stone"));
            goldCostsLabel.setText(" - " + buildingsCostsCalculator.getBarracksCosts((int)gameStat.getBarrackslevel()).get("gold"));
            ironCostsLabel.setText(" - " + buildingsCostsCalculator.getBarracksCosts((int)gameStat.getBarrackslevel()).get("iron"));
        }
        Pane pane = showArrow(barracks, (int)gameStat.getBarrackslevel());
        setLabelsToDefault(event, pane);
    }
    public void onBuildCastleButton() throws FileNotFoundException {
        buildOrUpgradeBuilding("castle", castleProgressBar, buildCastleButton);
    }
    public void updateCastle(){
        gameStat.setFood(gameStat.getFood() - buildingsCostsCalculator.getCastleCosts((int)gameStat.getCastlelevel()).get("food"));
        gameStat.setWood(gameStat.getWood() - buildingsCostsCalculator.getCastleCosts((int)gameStat.getCastlelevel()).get("wood"));
        gameStat.setStone(gameStat.getStone() - buildingsCostsCalculator.getCastleCosts((int)gameStat.getCastlelevel()).get("stone"));
        gameStat.setGold(gameStat.getGold() - buildingsCostsCalculator.getCastleCosts((int)gameStat.getCastlelevel()).get("gold"));
        gameStat.setIron(gameStat.getIron() - buildingsCostsCalculator.getCastleCosts((int)gameStat.getCastlelevel()).get("iron"));
        gameStat.setCastlelevel(gameStat.getCastlelevel() + 1);
    }
    public void onMouseHoveredViewCastleCost(MouseEvent event) throws FileNotFoundException {
        if(gameStat.getCastlelevel() == (int)gameStat.getCastlelevel()){
            foodCostsLabel.setText(" - " + buildingsCostsCalculator.getCastleCosts((int)gameStat.getCastlelevel()).get("food"));
            woodCostsLabel.setText(" - " + buildingsCostsCalculator.getCastleCosts((int)gameStat.getCastlelevel()).get("wood"));
            stoneCostsLabel.setText(" - " + buildingsCostsCalculator.getCastleCosts((int)gameStat.getCastlelevel()).get("stone"));
            goldCostsLabel.setText(" - " + buildingsCostsCalculator.getCastleCosts((int)gameStat.getCastlelevel()).get("gold"));
            ironCostsLabel.setText(" - " + buildingsCostsCalculator.getCastleCosts((int)gameStat.getCastlelevel()).get("iron"));

        }
        Pane pane = showArrow(castle, (int)gameStat.getCastlelevel());
        setLabelsToDefault(event, pane);
    }
    public void onBuildHousingButton() throws FileNotFoundException {
        buildOrUpgradeBuilding("housing", housingProgressBar, buildHousingButton);
    }
    public void updateHousing() throws FileNotFoundException {
        gameStat.setFood(gameStat.getFood() - buildingsCostsCalculator.getHousesCosts((int)gameStat.getHousinglevel()).get("food"));
        gameStat.setWood(gameStat.getWood() - buildingsCostsCalculator.getHousesCosts((int)gameStat.getHousinglevel()).get("wood"));
        gameStat.setStone(gameStat.getStone() - buildingsCostsCalculator.getHousesCosts((int)gameStat.getHousinglevel()).get("stone"));
        gameStat.setGold(gameStat.getGold() - buildingsCostsCalculator.getHousesCosts((int)gameStat.getHousinglevel()).get("gold"));
        gameStat.setIron(gameStat.getIron() - buildingsCostsCalculator.getHousesCosts((int)gameStat.getHousinglevel()).get("iron"));
        gameStat.setHousinglevel(gameStat.getHousinglevel() + 1);
        makeHousesVisible();
    }
    public void onMouseHoveredViewHousingCost(MouseEvent event) throws FileNotFoundException {
        if(gameStat.getHousinglevel()==(int)gameStat.getHousinglevel()){
            foodCostsLabel.setText(" - " + buildingsCostsCalculator.getHousesCosts((int)gameStat.getHousinglevel()).get("food"));
            woodCostsLabel.setText(" - " + buildingsCostsCalculator.getHousesCosts((int)gameStat.getHousinglevel()).get("wood"));
            stoneCostsLabel.setText(" - " + buildingsCostsCalculator.getHousesCosts((int)gameStat.getHousinglevel()).get("stone"));
            goldCostsLabel.setText(" - " + buildingsCostsCalculator.getHousesCosts((int)gameStat.getHousinglevel()).get("gold"));
            ironCostsLabel.setText(" - " + buildingsCostsCalculator.getHousesCosts((int)gameStat.getHousinglevel()).get("iron"));

        }
        Pane pane = showArrow(house1, (int)gameStat.getHousinglevel());
        setLabelsToDefault(event,pane);
    }
    public void onBuildFarmButton() throws FileNotFoundException {
        buildOrUpgradeBuilding("farm", farmProgressBar, buildFarmButton);
    }
    public void updateFarm(){
        gameStat.setFood(gameStat.getFood() - buildingsCostsCalculator.getFarmCosts((int)gameStat.getFarmlevel()).get("food"));
        gameStat.setWood(gameStat.getWood() - buildingsCostsCalculator.getFarmCosts((int)gameStat.getFarmlevel()).get("wood"));
        gameStat.setStone(gameStat.getStone() - buildingsCostsCalculator.getFarmCosts((int)gameStat.getFarmlevel()).get("stone"));
        gameStat.setGold(gameStat.getGold() - buildingsCostsCalculator.getFarmCosts((int)gameStat.getFarmlevel()).get("gold"));
        gameStat.setIron(gameStat.getIron() - buildingsCostsCalculator.getFarmCosts((int)gameStat.getFarmlevel()).get("iron"));
        gameStat.setFarmlevel(gameStat.getFarmlevel() + 1);
        makeFarmVisible();
    }
    private ImageView makeFarmVisible() {
        ImageView farms = new ImageView();
        for(int i = 0; i < gameStat.getFarmlevel() + 1; i++){
            farms = (ImageView) farmGroup.getChildren().get(i);
        }
        return farms;
    }
    public void onMouseHoveredViewFarmCost(MouseEvent event) throws FileNotFoundException {
        if(gameStat.getFarmlevel()  == (int)gameStat.getFarmlevel()){
            foodCostsLabel.setText(" - " + buildingsCostsCalculator.getFarmCosts((int)gameStat.getFarmlevel()).get("food"));
            woodCostsLabel.setText(" - " + buildingsCostsCalculator.getFarmCosts((int)gameStat.getFarmlevel()).get("wood"));
            stoneCostsLabel.setText(" - " + buildingsCostsCalculator.getFarmCosts((int)gameStat.getFarmlevel()).get("stone"));
            goldCostsLabel.setText(" - " + buildingsCostsCalculator.getFarmCosts((int)gameStat.getFarmlevel()).get("gold"));
            ironCostsLabel.setText(" - " + buildingsCostsCalculator.getFarmCosts((int)gameStat.getFarmlevel()).get("iron"));
        }
        Pane pane = showArrow(farm, (int)gameStat.getFarmlevel());
        setLabelsToDefault(event,pane);
    }
    public void onActionPause(ActionEvent event) {
        eventBus.post(pauseGameEvent);
    }

    public void onClicked(MouseEvent event) throws FileNotFoundException {
        //get source of event
        Node source = (Node) event.getSource();
        //generate and show custom UpgradeControl
        //get id of source
        String id = source.getId();

        if(id.length()> 4){
            if(id.substring(0, 5).equals("house")){
                id ="house";
            }
        }
        switch (id) {
            case "castle" -> onBuildCastleButton();
            case "house" -> onBuildHousingButton();
            case "farm" -> onBuildFarmButton();
            case "lumberjack" -> onBuildLumberjackButton();
            case "mine" -> onBuildMineButton();
            case "barracks" -> onBuildBarracksButton();
            case "port" -> onBuildPortButton();
            case "watchtower" -> onBuildTowerButton();
            case "blacksmith" -> onBuildBlacksmithButton();
        }
    }

    public void onActionPlay(ActionEvent event) {
        if(timeline.getStatus()== Animation.Status.PAUSED && timelineWeather.getStatus()== Animation.Status.PAUSED){
            timeline.play();
            timelineWeather.play();
        }
    }
}
