package com.example.edpapp.models;

import jakarta.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "gamestat")
public class GameStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @CreationTimestamp
    private Timestamp timestamp;
    @ManyToOne
    @JoinColumn(name = "newgameid")
    private NewGame newgame;
    private double castlelevel;
    private double housinglevel;
    private double farmlevel;
    private double lumberjacklevel;
    private double portlevel;
    private double watchtowerlevel;
    private double barrackslevel;
    private double blacksmithlevel;
    private double buildingvelocity;
    private int wood;
    private int stone;
    private int food;
    private int iron;

    public GameStat(){

    }

    public NewGame getNewgame() {
        return newgame;
    }

    public void setNewgame(NewGame newgame) {
        this.newgame = newgame;
    }

    public double getBuildingvelocity() {
        return buildingvelocity;
    }

    public void setBuildingvelocity(double buildingvelocity) {
        this.buildingvelocity = buildingvelocity;
    }

    public int getWood() {
        return wood;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    public int getStone() {
        return stone;
    }

    public void setStone(int stone) {
        this.stone = stone;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getIron() {
        return iron;
    }

    public void setIron(int iron) {
        this.iron = iron;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public double getCastlelevel() {
        return castlelevel;
    }

    public void setCastlelevel(double castlelevel) {
        this.castlelevel = castlelevel;
    }

    public double getHousinglevel() {
        return housinglevel;
    }

    public void setHousinglevel(double housinglevel) {
        this.housinglevel = housinglevel;
    }

    public double getFarmlevel() {
        return farmlevel;
    }

    public void setFarmlevel(double farmlevel) {
        this.farmlevel = farmlevel;
    }

    public double getLumberjacklevel() {
        return lumberjacklevel;
    }

    public void setLumberjacklevel(double lumberjacklevel) {
        this.lumberjacklevel = lumberjacklevel;
    }

    public double getPortlevel() {
        return portlevel;
    }

    public void setPortlevel(double portlevel) {
        this.portlevel = portlevel;
    }

    public double getWatchtowerlevel() {
        return watchtowerlevel;
    }

    public void setWatchtowerlevel(double watchtowerlevel) {
        this.watchtowerlevel = watchtowerlevel;
    }

    public double getBarrackslevel() {
        return barrackslevel;
    }

    public void setBarrackslevel(double barrackslevel) {
        this.barrackslevel = barrackslevel;
    }

    public double getBlacksmithlevel() {
        return blacksmithlevel;
    }

    public void setBlacksmithlevel(double blacksmithlevel) {
        this.blacksmithlevel = blacksmithlevel;
    }
}
