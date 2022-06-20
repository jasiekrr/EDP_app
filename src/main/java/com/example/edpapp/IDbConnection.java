package com.example.edpapp;

import com.example.edpapp.models.GameStat;
import com.example.edpapp.models.NewGame;

import java.sql.Timestamp;
import java.util.List;

public interface IDbConnection {
    public void setUp();
    public void makeConnection() throws ClassNotFoundException;
    public void saveNewGame(NewGame newGame);
    NewGame getLastNewGame();
    void saveGame(GameStat gameStat);
    public List<GameStat> getAllGames();
    public GameStat getGameBySaveGameColumns(String faction, String townName, Timestamp createdOn);
}
