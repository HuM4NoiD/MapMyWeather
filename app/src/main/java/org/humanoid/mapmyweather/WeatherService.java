package org.humanoid.mapmyweather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Jugal Mistry on 10/8/2019.
 */
public interface WeatherService {
    @GET("data/2.5/weather?")
    Call<WeatherResponse> getCurrentWeatherData(@Query("lat") String lat, @Query("lon") String lon,
            @Query("APPID") String app_id);
}
