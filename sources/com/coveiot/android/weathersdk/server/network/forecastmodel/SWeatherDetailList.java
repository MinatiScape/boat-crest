package com.coveiot.android.weathersdk.server.network.forecastmodel;

import com.coveiot.android.weathersdk.server.network.model.SWeather;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SWeatherDetailList {
    @SerializedName("dt")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    public Long f6225a;
    @SerializedName("sunrise")
    @Expose
    public Long b;
    @SerializedName("sunset")
    @Expose
    public Long c;
    @SerializedName("temp")
    @Expose
    public STemp d;
    @SerializedName("feels_like")
    @Expose
    public SFeelsLike e;
    @SerializedName("pressure")
    @Expose
    public Double f;
    @SerializedName("humidity")
    @Expose
    public Double g;
    @SerializedName("weather")
    @Expose
    public List<SWeather> h = null;
    @SerializedName("speed")
    @Expose
    public Double i;
    @SerializedName("deg")
    @Expose
    public Double j;
    @SerializedName("gust")
    @Expose
    public Double k;
    @SerializedName("clouds")
    @Expose
    public Integer l;
    @SerializedName("pop")
    @Expose
    public Double m;
    @SerializedName("rain")
    @Expose
    public Double n;
    @SerializedName("snow")
    @Expose
    public Double o;

    public Integer getClouds() {
        return this.l;
    }

    public Double getDeg() {
        return this.j;
    }

    public Long getDt() {
        return this.f6225a;
    }

    public SFeelsLike getFeelsLike() {
        return this.e;
    }

    public Double getGust() {
        return this.k;
    }

    public Double getHumidity() {
        return this.g;
    }

    public Double getPop() {
        return this.m;
    }

    public Double getPressure() {
        return this.f;
    }

    public Double getRain() {
        return this.n;
    }

    public SFeelsLike getSFeelsLike() {
        return this.e;
    }

    public STemp getSTemp() {
        return this.d;
    }

    public Double getSnow() {
        return this.o;
    }

    public Double getSpeed() {
        return this.i;
    }

    public Long getSunrise() {
        return this.b;
    }

    public Long getSunset() {
        return this.c;
    }

    public STemp getTemp() {
        return this.d;
    }

    public List<SWeather> getWeather() {
        return this.h;
    }

    public void setClouds(Integer num) {
        this.l = num;
    }

    public void setDeg(Double d) {
        this.j = d;
    }

    public void setDt(Long l) {
        this.f6225a = l;
    }

    public void setFeelsLike(SFeelsLike sFeelsLike) {
        this.e = sFeelsLike;
    }

    public void setGust(Double d) {
        this.k = d;
    }

    public void setHumidity(Double d) {
        this.g = d;
    }

    public void setPop(Double d) {
        this.m = d;
    }

    public void setPressure(Double d) {
        this.f = d;
    }

    public void setRain(Double d) {
        this.n = d;
    }

    public void setSFeelsLike(SFeelsLike sFeelsLike) {
        this.e = sFeelsLike;
    }

    public void setSTemp(STemp sTemp) {
        this.d = sTemp;
    }

    public void setSnow(Double d) {
        this.o = d;
    }

    public void setSpeed(Double d) {
        this.i = d;
    }

    public void setSunrise(Long l) {
        this.b = l;
    }

    public void setSunset(Long l) {
        this.c = l;
    }

    public void setTemp(STemp sTemp) {
        this.d = sTemp;
    }

    public void setWeather(List<SWeather> list) {
        this.h = list;
    }
}
