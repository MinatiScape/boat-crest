package com.mappls.sdk.services.api.weather.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
@Keep
/* loaded from: classes8.dex */
public class ForecastData {
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName(WeatherCriteria.UNIT_TYPE_DAY)
    @Expose
    private String day;
    @SerializedName(WeatherCriteria.UNIT_TYPE_HOUR)
    @Expose
    private String hour;
    @SerializedName(DeviceKey.TempData)
    @Expose
    private WeatherTemperature temperature;
    @SerializedName("weatherCondition")
    @Expose
    private WeatherCondition weatherCondition;

    public String getDate() {
        return this.date;
    }

    public String getDay() {
        return this.day;
    }

    public String getHour() {
        return this.hour;
    }

    public WeatherTemperature getTemperature() {
        return this.temperature;
    }

    public WeatherCondition getWeatherCondition() {
        return this.weatherCondition;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public void setDay(String str) {
        this.day = str;
    }

    public void setHour(String str) {
        this.hour = str;
    }

    public void setTemperature(WeatherTemperature weatherTemperature) {
        this.temperature = weatherTemperature;
    }

    public void setWeatherCondition(WeatherCondition weatherCondition) {
        this.weatherCondition = weatherCondition;
    }
}
