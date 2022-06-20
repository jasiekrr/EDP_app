package com.example.edpapp.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {
   @GET("?APPID=&units=metric")
    Call<Weather> requestWeather(@Query("lat") double lat, @Query("lon") double lon);
}
