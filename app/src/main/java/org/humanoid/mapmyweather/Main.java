package org.humanoid.mapmyweather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jugal Mistry on 10/8/2019.
 */
public class Main {
    @SerializedName("temp")
    private float temp;
    @SerializedName("humidity")
    private float humidity;
    @SerializedName("pressure")
    private float pressure;
    @SerializedName("temp_min")
    private float temp_min;
    @SerializedName("temp_max")
    private float temp_max;

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(float temp_min) {
        this.temp_min = temp_min;
    }

    public float getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(float temp_max) {
        this.temp_max = temp_max;
    }
}
