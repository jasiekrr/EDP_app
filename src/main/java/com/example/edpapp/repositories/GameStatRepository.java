package com.example.edpapp.repositories;

import com.example.edpapp.IDbConnection;
import com.example.edpapp.models.GameStat;
import com.google.inject.Inject;

import java.sql.Timestamp;
import java.util.ArrayList;

public class GameStatRepository implements IGameStatRepository {
    private IDbConnection connection;

    @Inject
    public GameStatRepository(IDbConnection iDbConnection){
        this.connection = iDbConnection;
    }
    @Override
    public GameStat getLastGame() {
        return null;
    }

    @Override
    public GameStat getGameById(long id) {
        return null;
    }
    @Override
    public GameStat getGameBySaveGameColumns(String faction, String townName, Timestamp createdOn) {
        return connection.getGameBySaveGameColumns(faction, townName, createdOn);
    }
    @Override
    public void deleteGame(GameStat gameStat) {

    }
    @Override
    public void postGame(GameStat gameStat) {
        connection.saveGame(gameStat);
    }

    @Override
    public ArrayList<GameStat> getAllGames() {
        return (ArrayList<GameStat>) connection.getAllGames();
    }
}
