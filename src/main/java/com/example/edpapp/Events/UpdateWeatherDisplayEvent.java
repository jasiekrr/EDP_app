package com.example.edpapp.Events;

import com.example.edpapp.api.Weather;
import com.example.edpapp.models.GameStat;
import com.google.common.eventbus.Subscribe;

public class UpdateWeatherDisplayEvent {

    private final Weather weather;

    public UpdateWeatherDisplayEvent(Weather weather) {
        this.weather = weather;
    }

    public Weather getWeather() {
        return weather;
    }
}
