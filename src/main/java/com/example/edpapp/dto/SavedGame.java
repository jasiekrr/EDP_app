package com.example.edpapp.dto;

import java.sql.Timestamp;

public class SavedGame {
    private Timestamp createdOn;
    private String faction;
    private String townName;

    public SavedGame(Timestamp createdOn, String faction, String townName) {
        this.createdOn = createdOn;
        this.faction = faction;
        this.townName = townName;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
