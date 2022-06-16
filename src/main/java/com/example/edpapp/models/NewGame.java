package com.example.edpapp.models;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "newgame")
public class NewGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    private Date createdOn;
    private String faction;
    private String level;
    private String townName;
    private double location_y;
    private double location_x;

    public NewGame() {

    }
    public NewGame(int id, String faction, String level, String townName) {
        this.faction = faction;
        this.level = level;
        this.townName = townName;
        this.id = id;
    }


    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public double getLocation_y() {
        return location_y;
    }

    public void setLocation_y(double latitude) {
        this.location_y = latitude;
    }

    public double getLocation_x() {
        return location_x;
    }

    public void setLocation_x(double longitude) {
        this.location_x = longitude;
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
