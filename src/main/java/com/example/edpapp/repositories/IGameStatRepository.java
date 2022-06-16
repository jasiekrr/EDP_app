package com.example.edpapp.repositories;


import com.example.edpapp.models.GameStat;

public interface IGameStatRepository {
    GameStat getLastGame();
    GameStat getGameById(long id);
    void deleteGame(GameStat gameStat);
    void postGame(GameStat gameStat);
}
