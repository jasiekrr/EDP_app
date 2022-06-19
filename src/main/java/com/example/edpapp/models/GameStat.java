package com.example.edpapp.models;

import jakarta.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;



@Entity
@Table(name = "gamestat", schema = "public", catalog = "gamesDb")
public class GameStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @CreationTimestamp
    private Timestamp createdon;
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
    private double minelevel;
    private double buildingvelocity;
    private double wood;
    private double stone;
    private double food;
    private double iron;
    private double gold;
    private int lightInfantry;
    private int mediumInfantry;
    private int heavyInfantry;
    private int specialUnit;
    private int LightInfantryRecruitmentTime;
    private int MediumInfantryRecruitmentTime;
    private int HeavyInfantryRecruitmentTime;
    private int SpecialUnitRecruitmentTime;
    private double woodperminute;
    private double stoneperminute;
    private double foodperminute;
    private double ironperminute;
    private double goldperminute;

    public GameStat(){

    }
    public int getLightInfantry() {
        return lightInfantry;
    }

    public void setLightInfantry(int lightInfantry) {
        this.lightInfantry = lightInfantry;
    }

    public int getMediumInfantry() {
        return mediumInfantry;
    }

    public void setMediumInfantry(int mediumInfantry) {
        this.mediumInfantry = mediumInfantry;
    }

    public int getHeavyInfantry() {
        return heavyInfantry;
    }

    public void setHeavyInfantry(int heavyInfantry) {
        this.heavyInfantry = heavyInfantry;
    }

    public int getSpecialUnit() {
        return specialUnit;
    }

    public void setSpecialUnit(int specialUnit) {
        this.specialUnit = specialUnit;
    }

    public int getLightInfantryRecruitmentTime() {
        return LightInfantryRecruitmentTime;
    }

    public void setLightInfantryRecruitmentTime(int lightInfantryRecruitmentTime) {
        LightInfantryRecruitmentTime = lightInfantryRecruitmentTime;
    }

    public int getMediumInfantryRecruitmentTime() {
        return MediumInfantryRecruitmentTime;
    }

    public void setMediumInfantryRecruitmentTime(int mediumInfantryRecruitmentTime) {
        MediumInfantryRecruitmentTime = mediumInfantryRecruitmentTime;
    }

    public int getHeavyInfantryRecruitmentTime() {
        return HeavyInfantryRecruitmentTime;
    }

    public void setHeavyInfantryRecruitmentTime(int heavyInfantryRecruitmentTime) {
        HeavyInfantryRecruitmentTime = heavyInfantryRecruitmentTime;
    }

    public int getSpecialUnitRecruitmentTime() {
        return SpecialUnitRecruitmentTime;
    }

    public void setSpecialUnitRecruitmentTime(int specialUnitRecruitmentTime) {
        SpecialUnitRecruitmentTime = specialUnitRecruitmentTime;
    }

    public double getMinelevel() {
        return minelevel;
    }

    public void setMinelevel(double minelevel) {
        this.minelevel = minelevel;
    }

    public double getGold() {
        return gold;
    }

    public void setGold(double gold) {
        this.gold = gold;
    }

    public void setWoodperminute(double woodperminute) {
        this.woodperminute = woodperminute;
    }

    public void setStoneperminute(double stoneperminute) {
        this.stoneperminute = stoneperminute;
    }

    public void setFoodperminute(double foodperminute) {
        this.foodperminute = foodperminute;
    }

    public double getGoldperminute() {
        return goldperminute;
    }

    public double getWoodperminute() {
        return woodperminute;
    }

    public double getStoneperminute() {
        return stoneperminute;
    }

    public double getFoodperminute() {
        return foodperminute;
    }

    public double getIronperminute() {
        return ironperminute;
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

    public double getWood() {
        return wood;
    }

    public void setWood(double wood) {
        this.wood = wood;
    }

    public double getStone() {
        return stone;
    }

    public void setStone(double stone) {
        this.stone = stone;
    }

    public double getFood() {
        return food;
    }

    public void setFood(double food) {
        this.food = food;
    }

    public double getIron() {
        return iron;
    }

    public void setIron(double iron) {
        this.iron = iron;
    }

    public double getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Timestamp timestamp) {
        this.createdon = timestamp;
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

    public void setGoldperminute(double goldperminute) {
        this.goldperminute = goldperminute;
    }
    public void setIronperminute(double ironperminute) {
        this.ironperminute = ironperminute;
    }
}
