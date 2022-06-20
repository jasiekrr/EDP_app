package com.example.edpapp.controllers;

import com.example.edpapp.Main;
import com.example.edpapp.dto.SavedGame;
import com.example.edpapp.models.GameStat;
import com.example.edpapp.repositories.GameStatRepository;
import com.example.edpapp.repositories.NewGameRepositoryGuiceModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoadGameController implements Initializable {
    @FXML
    private Button toMenuButton;
    @FXML
    public TableColumn<SavedGame, Timestamp> columnDate;
    @FXML
    public TableColumn<SavedGame, String> columnFaction;
    @FXML
    public TableColumn<SavedGame, String> columnTownName;
    @FXML
    public TableView<SavedGame> tableView;

    private ObservableList<SavedGame> savedGames = FXCollections.observableArrayList();
    private ArrayList<GameStat> gameStats;
    private GameStatRepository gameStatRepository;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        Injector injector = Guice.createInjector(new NewGameRepositoryGuiceModule());
        gameStatRepository = injector.getInstance(GameStatRepository.class);

        gameStats = gameStatRepository.getAllGames();

        columnDate.setCellValueFactory(new PropertyValueFactory<SavedGame,Timestamp>("createdOn"));
        columnFaction.setCellValueFactory(new PropertyValueFactory<SavedGame,String>("faction"));
        columnTownName.setCellValueFactory(new PropertyValueFactory<SavedGame,String>("townName"));

        savedGames = getSavedGameColumns();

        tableView.setItems(savedGames);

        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                SavedGame selectedGame = tableView.getSelectionModel().getSelectedItem();
                if (selectedGame != null) {
                    try {
                        loadGame(selectedGame, event);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    public ObservableList<SavedGame> getSavedGameColumns() {
        for(GameStat gameStat : gameStats) {
            savedGames.add(new SavedGame(gameStat.getCreatedon(), gameStat.getNewgame().getFaction(), gameStat.getNewgame().getTownName()));
        }
        return savedGames;
    }

    public void onActionBackToMenu(ActionEvent event) throws IOException {
        navigateToMenu(event);
    }
    //load game
    public void loadGame(SavedGame savedGame, MouseEvent event) throws IOException {
        GameStat gameStat = gameStatRepository.getGameBySaveGameColumns(savedGame.getFaction(), savedGame.getTownName(), savedGame.getCreatedOn());

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("View/Game.fxml"));

        Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        GameController controller = fxmlLoader.getController();
        controller.setGame(gameStat);
        stage.setTitle("GAME!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    static void navigateToMenu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("View/mainMenu.fxml"));

        Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("WeatherMain Menu");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}

