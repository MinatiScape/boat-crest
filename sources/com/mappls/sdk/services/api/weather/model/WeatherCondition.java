package com.mappls.sdk.services.api.weather.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes8.dex */
public class WeatherCondition {
    @SerializedName("realFeelWeatherText")
    @Expose
    private String realFeelWeatherText;
    @SerializedName("weatherDesc")
    @Expose
    private String weatherDescription;
    @SerializedName("weatherIcon")
    @Expose
    private String weatherIcon;
    @SerializedName("weatherIconDay")
    @Expose
    private String weatherIconDay;
    @SerializedName("weatherIconNight")
    @Expose
    private String weatherIconNight;
    @SerializedName("weatherId")
    @Expose
    private Integer weatherId;
    @SerializedName("weatherIdDay")
    @Expose
    private Integer weatherIdDay;
    @SerializedName("weatherIdNight")
    @Expose
    private Integer weatherIdNight;
    @SerializedName("weatherText")
    @Expose
    private String weatherText;
    @SerializedName("weatherTextDay")
    @Expose
    private String weatherTextDay;
    @SerializedName("weatherTextNight")
    @Expose
    private String weatherTextNight;

    public String getRealFeelWeatherText() {
        return this.realFeelWeatherText;
    }

    public String getWeatherDescription() {
        return this.weatherDescription;
    }

    public String getWeatherIcon() {
        return this.weatherIcon;
    }

    public String getWeatherIconDay() {
        return this.weatherIconDay;
    }

    public String getWeatherIconNight() {
        return this.weatherIconNight;
    }

    public Integer getWeatherId() {
        return this.weatherId;
    }

    public Integer getWeatherIdDay() {
        return this.weatherIdDay;
    }

    public Integer getWeatherIdNight() {
        return this.weatherIdNight;
    }

    public String getWeatherText() {
        return this.weatherText;
    }

    public String getWeatherTextDay() {
        return this.weatherTextDay;
    }

    public String getWeatherTextNight() {
        return this.weatherTextNight;
    }

    public void setRealFeelWeatherText(String str) {
        this.realFeelWeatherText = str;
    }

    public void setWeatherDescription(String str) {
        this.weatherDescription = str;
    }

    public void setWeatherIcon(String str) {
        this.weatherIcon = str;
    }

    public void setWeatherIconDay(String str) {
        this.weatherIconDay = str;
    }

    public void setWeatherIconNight(String str) {
        this.weatherIconNight = str;
    }

    public void setWeatherId(Integer num) {
        this.weatherId = num;
    }

    public void setWeatherIdDay(Integer num) {
        this.weatherIdDay = num;
    }

    public void setWeatherIdNight(Integer num) {
        this.weatherIdNight = num;
    }

    public void setWeatherText(String str) {
        this.weatherText = str;
    }

    public void setWeatherTextDay(String str) {
        this.weatherTextDay = str;
    }

    public void setWeatherTextNight(String str) {
        this.weatherTextNight = str;
    }
}
