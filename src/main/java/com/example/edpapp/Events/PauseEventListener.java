package com.example.edpapp.Events;

import com.google.common.eventbus.Subscribe;

public class PauseEventListener {
    @Subscribe
    public void onPause(PauseGameEvent event) {
        System.out.println("Pause event received");
    }
}
