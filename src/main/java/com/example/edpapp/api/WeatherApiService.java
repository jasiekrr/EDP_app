package com.example.edpapp.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface WeatherApiService {
    //@Headers("X-API-KEY: 947036533d519185ee295cc435beca63")
    //@GET("data/2.5/weather")
    @GET("?APPID=947036533d519185ee295cc435beca63&units=metric")
    Call<Weather> requestWeather(@Query("lat") double lat, @Query("lon") double lon);
}
