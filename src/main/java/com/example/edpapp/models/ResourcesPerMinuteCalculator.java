package com.example.edpapp.models;

import java.util.HashMap;
import java.util.Map;

public class ResourcesPerMinuteCalculator {
    private final int difficultyLevel;
    private final GameStat gameStat;

    public ResourcesPerMinuteCalculator(int difficultyLevel, GameStat gameStat) {
        this.difficultyLevel = difficultyLevel;
        this.gameStat = gameStat;
    }
    public GameStat getResourcesIncrement(GameStat gameStat) {
        gameStat.setFoodperminute(gameStat.getFarmlevel() * 500 + (4 - difficultyLevel) * 100 - gameStat.getHousinglevel() * 100 - gameStat.getLightInfantry()  - gameStat.getMediumInfantry() * 5 - gameStat.getHeavyInfantry() * 10 - gameStat.getSpecialUnit() * 10);
        gameStat.setWoodperminute(gameStat.getLumberjacklevel() * 100 + (4 - difficultyLevel) * 100);
        gameStat.setStoneperminute(gameStat.getMinelevel() * 100 + (4 - difficultyLevel) * 100);
        gameStat.setGoldperminute(gameStat.getPortlevel() * 100 + (4 - difficultyLevel) * 50 - gameStat.getMediumInfantry() * 2 - gameStat.getHeavyInfantry() * 3 - gameStat.getSpecialUnit() * 3);
        gameStat.setIronperminute(gameStat.getBlacksmithlevel() * 100 + (4 - difficultyLevel) * 100);
        return gameStat;
    }
}
