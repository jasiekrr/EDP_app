package com.example.edpapp.Events;

import com.example.edpapp.models.GameStat;

public class BuildingBuiltEvent {
    GameStat gameStat;

    public BuildingBuiltEvent(GameStat gameStat) {
        this.gameStat = gameStat;
    }
}
