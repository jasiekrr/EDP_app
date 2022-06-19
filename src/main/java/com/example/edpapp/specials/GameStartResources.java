package com.example.edpapp.specials;

import com.example.edpapp.models.GameStat;
import com.example.edpapp.models.NewGame;
import com.google.inject.Inject;

public class GameStartResources {
    public GameStat gameStat = new GameStat();
    public String level;
    public double multiplier;
    public String faction;

    @Inject
    public GameStartResources() {

    }

    public GameStat getStartResources(NewGame newGame) {
        this.level = newGame.getLevel();
        this.faction = newGame.getFaction();
        switch (level) {
            case "sandbox" -> multiplier = 1;
            case "easy" -> multiplier = 0.8;
            case "medium" -> multiplier = 0.6;
            case "hard" -> multiplier = 0.4;
        }
        gameStat.setNewgame(newGame);
        gameStat.setFood(1500 * multiplier);
        gameStat.setWood(1000 * multiplier);
        gameStat.setStone(500 * multiplier);
        gameStat.setGold(10 * multiplier);
        gameStat.setLightInfantry(100);
        gameStat.setHeavyInfantry(50);
        gameStat.setMediumInfantry(25);
        gameStat.setBuildingvelocity(1);
        gameStat.setBlacksmithlevel(0);
        gameStat.setBarrackslevel(0);
        gameStat.setFarmlevel(1);
        gameStat.setLumberjacklevel(0);
        gameStat.setPortlevel(0);
        gameStat.setWatchtowerlevel(0);
        gameStat.setCastlelevel(1);
        gameStat.setHousinglevel(1);

        switch (faction) {
            case "rome" -> {
                gameStat.setGold(gameStat.getGold() + 100 * multiplier);
                gameStat.setIron(gameStat.getIron() + 300 * multiplier);
                gameStat.setBarrackslevel(2);
                gameStat.setHousinglevel(2);
                gameStat.setMediumInfantry(100);
                gameStat.setHeavyInfantry(10);
                gameStat.setSpecialUnitRecruitmentTime(1);
            }
            case "carthage" -> {
                gameStat.setGold(gameStat.getGold() + 300 * multiplier);
                gameStat.setPortlevel(2);
                gameStat.setMinelevel(2);
                gameStat.setLightInfantry(200);
            }
            case "greeks" -> {
                gameStat.setFood(gameStat.getFood() + 500 * multiplier);
                gameStat.setCastlelevel(2);
                gameStat.setHousinglevel(2);
                gameStat.setBlacksmithlevel(1);
                gameStat.setBuildingvelocity(gameStat.getBuildingvelocity() + 1);
                gameStat.setMediumInfantry(100);
            }
        }

        return gameStat;
    }
}
