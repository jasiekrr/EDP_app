package com.example.edpapp.repositories;

import com.example.edpapp.models.NewGame;

public interface INewGameRepository {
    NewGame getLastNewGame();
    NewGame getNewGameById(long id);
    void deleteNewGame(NewGame newGame);
    void postNewGame(NewGame newGame);
}
