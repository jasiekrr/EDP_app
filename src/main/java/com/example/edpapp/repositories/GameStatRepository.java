package com.example.edpapp.repositories;

import com.example.edpapp.IDbConnection;
import com.example.edpapp.models.GameStat;
import com.google.inject.Inject;

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
    public void deleteGame(GameStat gameStat) {

    }
    @Override
    public void postGame(GameStat gameStat) {
        connection.saveGame(gameStat);
    }
}
