package com.example.edpapp.Events;

import com.example.edpapp.api.Weather;
import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class UpdateWeatherDisplayListener {
    private final Label location;
    private final Label temperature;

    public UpdateWeatherDisplayListener(Label location, Label temperature) {
        this.location = location;
        this.temperature = temperature;
    }
    @Subscribe
    public void onUpdateWeatherDisplay(UpdateWeatherDisplayEvent event) {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                location.setText(event.getWeather().getName() + " " + event.getWeather().getSys().getCountry());
                temperature.setText(event.getWeather().getMain().getTemp() + "°C");
            }
        });
    }

}
