package com.example.edpapp.api;

import com.example.edpapp.specials.PropertiesManager;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

import java.net.URL;

public interface WeatherApiService {
    @GET
    Call<Weather> requestWeather(@Url String URL, @Query("lat") double lat, @Query("lon") double lon);
}
