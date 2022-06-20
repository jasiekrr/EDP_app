package com.example.edpapp.repositories;


import com.example.edpapp.models.GameStat;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface IGameStatRepository {
    GameStat getLastGame();
    GameStat getGameById(long id);

    GameStat getGameBySaveGameColumns(String faction, String townName, Timestamp createdOn);

    void deleteGame(GameStat gameStat);
    void postGame(GameStat gameStat);

    ArrayList<GameStat> getAllGames();
}
