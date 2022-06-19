package com.example.edpapp.models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "newgame", schema = "public", catalog = "gamesDb")
public class NewgameEntity {
    @Basic
    @Column(name = "createdon", nullable = true)
    private Date createdon;
    @Basic
    @Column(name = "faction", nullable = false, length = -1)
    private String faction;
    @Basic
    @Column(name = "level", nullable = false, length = -1)
    private String level;
    @Basic
    @Column(name = "townname", nullable = false, length = -1)
    private String townname;
    @Id
    @Basic
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "location_x", nullable = false, precision = 0)
    private double locationX;
    @Basic
    @Column(name = "location_y", nullable = false, precision = 0)
    private double locationY;

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
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

    public String getTownname() {
        return townname;
    }

    public void setTownname(String townname) {
        this.townname = townname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLocationX() {
        return locationX;
    }

    public void setLocationX(double locationX) {
        this.locationX = locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public void setLocationY(double locationY) {
        this.locationY = locationY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewgameEntity that = (NewgameEntity) o;
        return id == that.id && Double.compare(that.locationX, locationX) == 0 && Double.compare(that.locationY, locationY) == 0 && Objects.equals(createdon, that.createdon) && Objects.equals(faction, that.faction) && Objects.equals(level, that.level) && Objects.equals(townname, that.townname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdon, faction, level, townname, id, locationX, locationY);
    }
}
