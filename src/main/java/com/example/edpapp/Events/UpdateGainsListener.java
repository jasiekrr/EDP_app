package com.example.edpapp.Events;

import com.google.common.eventbus.Subscribe;
import javafx.scene.control.Label;

public class UpdateGainsListener {
    private Label woodGainLabel;
    private Label stoneGainLabel;
    private Label foodGainLabel;
    private Label goldGainLabel;
    private Label ironGainLabel;
    public UpdateGainsListener(Label woodGain, Label stoneGain, Label foodGain, Label goldGain, Label ironGain){
        this.woodGainLabel = woodGain;
        this.stoneGainLabel = stoneGain;
        this.foodGainLabel = foodGain;
        this.goldGainLabel = goldGain;
        this.ironGainLabel = ironGain;
    }
    @Subscribe
    public void onUpdate(BuildingBuiltEvent event) {
        woodGainLabel.setText(String.valueOf(event.gameStat.getWoodperminute()));
        stoneGainLabel.setText(String.valueOf(event.gameStat.getStoneperminute()));
        foodGainLabel.setText(String.valueOf(event.gameStat.getFoodperminute()));
        goldGainLabel.setText(String.valueOf(event.gameStat.getGoldperminute()));
        ironGainLabel.setText(String.valueOf(event.gameStat.getIronperminute()));
    }
}
