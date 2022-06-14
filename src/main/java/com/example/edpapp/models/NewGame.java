package com.example.edpapp.models;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.sql.Date;
import java.text.DateFormat;

@Entity
@Table(name = "NewGame")
public class NewGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    private Date createdOn;
    private String faction;
    private String level;
    private String townName;

    public NewGame() {

    }
    public NewGame(int id, String faction, String level, String townName) {
        this.faction = faction;
        this.level = level;
        this.townName = townName;
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return createdOn;
    }

    public void setCreationDate(Date creationDate) {
        this.createdOn = creationDate;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }


}
