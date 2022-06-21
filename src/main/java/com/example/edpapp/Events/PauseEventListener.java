package com.example.edpapp.Events;

import com.google.common.eventbus.Subscribe;
import javafx.animation.Timeline;

public class PauseEventListener {
    private Timeline timeline;
    private Timeline timelineWeather;

    public PauseEventListener(Timeline timeline, Timeline timelineWeather) {
        this.timeline = timeline;
        this.timelineWeather = timelineWeather;
    }
    @Subscribe
    public void onPause(PauseGameEvent event) {
        timeline.pause();
        timelineWeather.pause();
        System.out.println("Pause event received");
    }
}
