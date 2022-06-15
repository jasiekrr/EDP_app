package com.example.edpapp.repositories;

import com.example.edpapp.IDbConnection;
import com.example.edpapp.models.NewGame;
import com.google.inject.Inject;

public class NewGameRepository implements INewGameRepository{

    private IDbConnection connection;

    @Inject
    public NewGameRepository(IDbConnection iDbConnection){
        this.connection = iDbConnection;
    }

    @Override
    public NewGame getLastNewGame() {
        return null;
    }

    @Override
    public NewGame getNewGameById(long id) {
        return null;
    }

    @Override
    public void deleteNewGame(NewGame newGame) {

    }

    @Override
    public void postNewGame(NewGame newGame) {

    }
}
