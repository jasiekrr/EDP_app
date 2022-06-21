package com.example.edpapp.Events;

import com.example.edpapp.models.GameStat;

public class UpgradeEvent {
    private GameStat gameStat;

    public UpgradeEvent(GameStat gameStat) {
        this.gameStat = gameStat;
    }
}
