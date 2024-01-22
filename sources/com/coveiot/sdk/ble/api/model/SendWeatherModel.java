package com.coveiot.sdk.ble.api.model;
/* loaded from: classes9.dex */
public class SendWeatherModel {
    private int humidity;
    private int iconId;
    private int pressure;
    private int sunRise;
    private int sunSet;
    private int temp;
    private int tempMax;
    private int tempMin;
    private Long time;
    private int visibility;
    private String weatherText;
    private int weatherTextLen;
    private int windSpeed;

    public SendWeatherModel(Long l, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, String str) {
        this.time = l;
        this.iconId = i;
        this.temp = i2;
        this.tempMin = i3;
        this.tempMax = i4;
        this.sunRise = i5;
        this.sunSet = i6;
        this.humidity = i7;
        this.windSpeed = i8;
        this.pressure = i9;
        this.weatherTextLen = i10;
        this.weatherText = str;
    }

    public int getHumidity() {
        return this.humidity;
    }

    public int getIconId() {
        return this.iconId;
    }

    public int getPressure() {
        return this.pressure;
    }

    public int getSunRise() {
        return this.sunRise;
    }

    public int getSunSet() {
        return this.sunSet;
    }

    public int getTemp() {
        return this.temp;
    }

    public int getTempMax() {
        return this.tempMax;
    }

    public int getTempMin() {
        return this.tempMin;
    }

    public Long getTime() {
        return this.time;
    }

    public int getVisibility() {
        return this.visibility;
    }

    public String getWeatherText() {
        return this.weatherText;
    }

    public int getWeatherTextLen() {
        return this.weatherTextLen;
    }

    public int getWindSpeed() {
        return this.windSpeed;
    }

    public void setHumidity(int i) {
        this.humidity = i;
    }

    public void setIconId(int i) {
        this.iconId = i;
    }

    public void setPressure(int i) {
        this.pressure = i;
    }

    public void setSunRise(int i) {
        this.sunRise = i;
    }

    public void setSunSet(int i) {
        this.sunSet = i;
    }

    public void setTemp(int i) {
        this.temp = i;
    }

    public void setTempMax(int i) {
        this.tempMax = i;
    }

    public void setTempMin(int i) {
        this.tempMin = i;
    }

    public void setTime(Long l) {
        this.time = l;
    }

    public void setVisibility(int i) {
        this.visibility = i;
    }

    public void setWeatherText(String str) {
        this.weatherText = str;
    }

    public void setWeatherTextLen(int i) {
        this.weatherTextLen = i;
    }

    public void setWindSpeed(int i) {
        this.windSpeed = i;
    }

    public SendWeatherModel(Long l, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, String str, int i11) {
        this.time = l;
        this.iconId = i;
        this.temp = i2;
        this.tempMin = i3;
        this.tempMax = i4;
        this.sunRise = i5;
        this.sunSet = i6;
        this.humidity = i7;
        this.windSpeed = i8;
        this.pressure = i9;
        this.weatherTextLen = i10;
        this.weatherText = str;
        this.visibility = i11;
    }
}
