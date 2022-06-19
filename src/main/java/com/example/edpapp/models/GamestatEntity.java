package com.example.edpapp.models;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "gamestat", schema = "public", catalog = "gamesDb")
public class GamestatEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "createdon", nullable = false)
    private Timestamp createdon;
    @Basic
    @Column(name = "newgameid", nullable = false)
    private int newgameid;
    @Basic
    @Column(name = "castlelevel", nullable = false, precision = 0)
    private double castlelevel;
    @Basic
    @Column(name = "housinglevel", nullable = false, precision = 0)
    private double housinglevel;
    @Basic
    @Column(name = "farmlevel", nullable = false, precision = 0)
    private double farmlevel;
    @Basic
    @Column(name = "lumberjacklevel", nullable = false, precision = 0)
    private double lumberjacklevel;
    @Basic
    @Column(name = "portlevel", nullable = false, precision = 0)
    private double portlevel;
    @Basic
    @Column(name = "watchtowerlevel", nullable = false, precision = 0)
    private double watchtowerlevel;
    @Basic
    @Column(name = "barrackslevel", nullable = false, precision = 0)
    private double barrackslevel;
    @Basic
    @Column(name = "blacksmithlevel", nullable = false, precision = 0)
    private double blacksmithlevel;
    @Basic
    @Column(name = "buildingvelocity", nullable = false, precision = 0)
    private double buildingvelocity;
    @Basic
    @Column(name = "wood", nullable = false, precision = 0)
    private double wood;
    @Basic
    @Column(name = "stone", nullable = false, precision = 0)
    private double stone;
    @Basic
    @Column(name = "food", nullable = false, precision = 0)
    private double food;
    @Basic
    @Column(name = "iron", nullable = false, precision = 0)
    private double iron;
    @Basic
    @Column(name = "minelevel", nullable = false, precision = 0)
    private double minelevel;
    @Basic
    @Column(name = "gold", nullable = false, precision = 0)
    private double gold;
    @Basic
    @Column(name = "lightinfantry", nullable = false)
    private int lightinfantry;
    @Basic
    @Column(name = "mediuminfantry", nullable = false)
    private int mediuminfantry;
    @Basic
    @Column(name = "heavyinfantry", nullable = false)
    private int heavyinfantry;
    @Basic
    @Column(name = "specialunit", nullable = false)
    private int specialunit;
    @Basic
    @Column(name = "lightinfantryrecruitmenttime", nullable = false)
    private int lightinfantryrecruitmenttime;
    @Basic
    @Column(name = "mediuminfantryrecruitmenttime", nullable = false)
    private int mediuminfantryrecruitmenttime;
    @Basic
    @Column(name = "heavyinfantryrecruitmenttime", nullable = false)
    private int heavyinfantryrecruitmenttime;
    @Basic
    @Column(name = "specialunitrecruitmenttime", nullable = false)
    private int specialunitrecruitmenttime;
    @Basic
    @Column(name = "woodperminute", nullable = false, precision = 0)
    private double woodperminute;
    @Basic
    @Column(name = "stoneperminute", nullable = false, precision = 0)
    private double stoneperminute;
    @Basic
    @Column(name = "ironperminute", nullable = false, precision = 0)
    private double ironperminute;
    @Basic
    @Column(name = "foodperminute", nullable = false, precision = 0)
    private double foodperminute;
    @Basic
    @Column(name = "goldperminute", nullable = false, precision = 0)
    private double goldperminute;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Timestamp createdon) {
        this.createdon = createdon;
    }

    public int getNewgameid() {
        return newgameid;
    }

    public void setNewgameid(int newgameid) {
        this.newgameid = newgameid;
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

    public int getLightinfantry() {
        return lightinfantry;
    }

    public void setLightinfantry(int lightinfantry) {
        this.lightinfantry = lightinfantry;
    }

    public int getMediuminfantry() {
        return mediuminfantry;
    }

    public void setMediuminfantry(int mediuminfantry) {
        this.mediuminfantry = mediuminfantry;
    }

    public int getHeavyinfantry() {
        return heavyinfantry;
    }

    public void setHeavyinfantry(int heavyinfantry) {
        this.heavyinfantry = heavyinfantry;
    }

    public int getSpecialunit() {
        return specialunit;
    }

    public void setSpecialunit(int specialunit) {
        this.specialunit = specialunit;
    }

    public int getLightinfantryrecruitmenttime() {
        return lightinfantryrecruitmenttime;
    }

    public void setLightinfantryrecruitmenttime(int lightinfantryrecruitmenttime) {
        this.lightinfantryrecruitmenttime = lightinfantryrecruitmenttime;
    }

    public int getMediuminfantryrecruitmenttime() {
        return mediuminfantryrecruitmenttime;
    }

    public void setMediuminfantryrecruitmenttime(int mediuminfantryrecruitmenttime) {
        this.mediuminfantryrecruitmenttime = mediuminfantryrecruitmenttime;
    }

    public int getHeavyinfantryrecruitmenttime() {
        return heavyinfantryrecruitmenttime;
    }

    public void setHeavyinfantryrecruitmenttime(int heavyinfantryrecruitmenttime) {
        this.heavyinfantryrecruitmenttime = heavyinfantryrecruitmenttime;
    }

    public int getSpecialunitrecruitmenttime() {
        return specialunitrecruitmenttime;
    }

    public void setSpecialunitrecruitmenttime(int specialunitrecruitmenttime) {
        this.specialunitrecruitmenttime = specialunitrecruitmenttime;
    }

    public double getWoodperminute() {
        return woodperminute;
    }

    public void setWoodperminute(double woodperminute) {
        this.woodperminute = woodperminute;
    }

    public double getStoneperminute() {
        return stoneperminute;
    }

    public void setStoneperminute(double stoneperminute) {
        this.stoneperminute = stoneperminute;
    }

    public double getIronperminute() {
        return ironperminute;
    }

    public void setIronperminute(double ironperminute) {
        this.ironperminute = ironperminute;
    }

    public double getFoodperminute() {
        return foodperminute;
    }

    public void setFoodperminute(double foodperminute) {
        this.foodperminute = foodperminute;
    }

    public double getGoldperminute() {
        return goldperminute;
    }

    public void setGoldperminute(double goldperminute) {
        this.goldperminute = goldperminute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GamestatEntity that = (GamestatEntity) o;
        return id == that.id && newgameid == that.newgameid && Double.compare(that.castlelevel, castlelevel) == 0 && Double.compare(that.housinglevel, housinglevel) == 0 && Double.compare(that.farmlevel, farmlevel) == 0 && Double.compare(that.lumberjacklevel, lumberjacklevel) == 0 && Double.compare(that.portlevel, portlevel) == 0 && Double.compare(that.watchtowerlevel, watchtowerlevel) == 0 && Double.compare(that.barrackslevel, barrackslevel) == 0 && Double.compare(that.blacksmithlevel, blacksmithlevel) == 0 && Double.compare(that.buildingvelocity, buildingvelocity) == 0 && Double.compare(that.wood, wood) == 0 && Double.compare(that.stone, stone) == 0 && Double.compare(that.food, food) == 0 && Double.compare(that.iron, iron) == 0 && Double.compare(that.minelevel, minelevel) == 0 && Double.compare(that.gold, gold) == 0 && lightinfantry == that.lightinfantry && mediuminfantry == that.mediuminfantry && heavyinfantry == that.heavyinfantry && specialunit == that.specialunit && lightinfantryrecruitmenttime == that.lightinfantryrecruitmenttime && mediuminfantryrecruitmenttime == that.mediuminfantryrecruitmenttime && heavyinfantryrecruitmenttime == that.heavyinfantryrecruitmenttime && specialunitrecruitmenttime == that.specialunitrecruitmenttime && Double.compare(that.woodperminute, woodperminute) == 0 && Double.compare(that.stoneperminute, stoneperminute) == 0 && Double.compare(that.ironperminute, ironperminute) == 0 && Double.compare(that.foodperminute, foodperminute) == 0 && Double.compare(that.goldperminute, goldperminute) == 0 && Objects.equals(createdon, that.createdon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdon, newgameid, castlelevel, housinglevel, farmlevel, lumberjacklevel, portlevel, watchtowerlevel, barrackslevel, blacksmithlevel, buildingvelocity, wood, stone, food, iron, minelevel, gold, lightinfantry, mediuminfantry, heavyinfantry, specialunit, lightinfantryrecruitmenttime, mediuminfantryrecruitmenttime, heavyinfantryrecruitmenttime, specialunitrecruitmenttime, woodperminute, stoneperminute, ironperminute, foodperminute, goldperminute);
    }
}
