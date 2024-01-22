package com.coveiot.android.weathersdk.server.network.hourlyforecastmodel;

import com.coveiot.android.femalewellness.wellnesscalendar.Constants;
import com.coveiot.android.weathersdk.server.network.model.SClouds;
import com.coveiot.android.weathersdk.server.network.model.SMain;
import com.coveiot.android.weathersdk.server.network.model.SWeather;
import com.coveiot.android.weathersdk.server.network.model.SWind;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SHourlyWeatherDetailList {
    @SerializedName("dt")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    public Long f6228a;
    @SerializedName(Constants.MAIN_TAG)
    @Expose
    public SMain b;
    @SerializedName("weather")
    @Expose
    public List<SWeather> c = null;
    @SerializedName("clouds")
    @Expose
    public SClouds d;
    @SerializedName("wind")
    @Expose
    public SWind e;
    @SerializedName("pop")
    @Expose
    public Double f;
    @SerializedName("dt_txt")
    @Expose
    public String g;

    public SClouds getClouds() {
        return this.d;
    }

    public String getDateAndTimeText() {
        return this.g;
    }

    public Long getDt() {
        return this.f6228a;
    }

    public SMain getMain() {
        return this.b;
    }

    public Double getPop() {
        return this.f;
    }

    public List<SWeather> getWeather() {
        return this.c;
    }

    public SWind getWind() {
        return this.e;
    }

    public void setClouds(SClouds sClouds) {
        this.d = sClouds;
    }

    public void setDateAndTimeText(String str) {
        this.g = str;
    }

    public void setDt(Long l) {
        this.f6228a = l;
    }

    public void setMain(SMain sMain) {
        this.b = sMain;
    }

    public void setPop(Double d) {
        this.f = d;
    }

    public void setWeather(List<SWeather> list) {
        this.c = list;
    }

    public void setWind(SWind sWind) {
        this.e = sWind;
    }
}
