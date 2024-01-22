package com.mappls.sdk.services.api.weather.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.List;
@Keep
/* loaded from: classes8.dex */
public class WeatherData {
    @SerializedName("airQuality")
    @Expose
    private AirQuality airQuality;
    @SerializedName("forecastData")
    @Expose
    private List<ForecastData> forecastData;
    @SerializedName("humidity")
    @Expose
    private Humidity humidity;
    @SerializedName(DeviceKey.TempData)
    @Expose
    private WeatherTemperature temperature;
    @SerializedName("visibility")
    @Expose
    private WeatherVisibility visibility;
    @SerializedName("weatherCondition")
    @Expose
    private WeatherCondition weatherCondition;
    @SerializedName("wind")
    @Expose
    private Wind wind;

    public AirQuality getAirQuality() {
        return this.airQuality;
    }

    public List<ForecastData> getForecastData() {
        return this.forecastData;
    }

    public Humidity getHumidity() {
        return this.humidity;
    }

    public WeatherTemperature getTemperature() {
        return this.temperature;
    }

    public WeatherVisibility getVisibility() {
        return this.visibility;
    }

    public WeatherCondition getWeatherCondition() {
        return this.weatherCondition;
    }

    public Wind getWind() {
        return this.wind;
    }

    public void setAirQuality(AirQuality airQuality) {
        this.airQuality = airQuality;
    }

    public void setForecastData(List<ForecastData> list) {
        this.forecastData = list;
    }

    public void setHumidity(Humidity humidity) {
        this.humidity = humidity;
    }

    public void setTemperature(WeatherTemperature weatherTemperature) {
        this.temperature = weatherTemperature;
    }

    public void setVisibility(WeatherVisibility weatherVisibility) {
        this.visibility = weatherVisibility;
    }

    public void setWeatherCondition(WeatherCondition weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }
}
