package org.humanoid.mapmyweather;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Jugal Mistry on 10/8/2019.
 */
public class WeatherResponse {


    @SerializedName("weather")
    private ArrayList<Weather> weather = new ArrayList<Weather>();
    @SerializedName("main")
    private Main main;
    @SerializedName("dt")
    private float dt;
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("cod")
    private float cod;

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public float getDt() {
        return dt;
    }

    public void setDt(float dt) {
        this.dt = dt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCod() {
        return cod;
    }

    public void setCod(float cod) {
        this.cod = cod;
    }
}
