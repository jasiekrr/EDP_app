package com.example.edpapp;

import com.example.edpapp.models.NewGame;

public interface IDbConnection {
    public void setUp();
    public void makeConnection() throws ClassNotFoundException;
    public void saveNewGame(NewGame newGame);
}
