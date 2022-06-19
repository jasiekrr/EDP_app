package com.example.edpapp.Events;

import com.example.edpapp.models.GameStat;
import javafx.event.Event;
import javafx.event.EventType;

public class PauseGameEvent{
    private GameStat gameStat;

    public PauseGameEvent(GameStat gameStat) {
        this.gameStat = gameStat;
    }
}
