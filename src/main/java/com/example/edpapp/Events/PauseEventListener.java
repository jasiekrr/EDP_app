package com.example.edpapp.Events;

import com.google.common.eventbus.Subscribe;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.control.Button;

public class PauseEventListener {
    private Timeline timeline;
    private Timeline timelineWeather;
    private Button playButton;

    public PauseEventListener(Timeline timeline, Timeline timelineWeather, Button playButton) {
        this.timeline = timeline;
        this.timelineWeather = timelineWeather;
        this.playButton = playButton;
    }
    @Subscribe
    public void onPause(PauseGameEvent event) {
        if(timeline.getStatus() == Animation.Status.RUNNING && timelineWeather.getStatus() == Animation.Status.RUNNING){
            timeline.pause();
            timelineWeather.pause();
            playButton.setDisable(false);
        }

    }
}
