package com.coveiot.android.weathersdk.server.network.model;

import androidx.core.app.NotificationCompat;
import com.coveiot.android.femalewellness.wellnesscalendar.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SCurrentForecastModel {
    @SerializedName("coord")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    public SCoord f6231a;
    @SerializedName("weather")
    @Expose
    public List<SWeather> b = null;
    @SerializedName("base")
    @Expose
    public String c;
    @SerializedName(Constants.MAIN_TAG)
    @Expose
    public SMain d;
    @SerializedName("visibility")
    @Expose
    public Integer e;
    @SerializedName("wind")
    @Expose
    public SWind f;
    @SerializedName("clouds")
    @Expose
    public SClouds g;
    @SerializedName("dt")
    @Expose
    public Long h;
    @SerializedName(NotificationCompat.CATEGORY_SYSTEM)
    @Expose
    public Sys i;
    @SerializedName("timezone")
    @Expose
    public Integer j;
    @SerializedName("id")
    @Expose
    public Integer k;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    @Expose
    public String l;
    @SerializedName("cod")
    @Expose
    public Integer m;

    public String getBase() {
        return this.c;
    }

    public SClouds getClouds() {
        return this.g;
    }

    public Integer getCod() {
        return this.m;
    }

    public SCoord getCoord() {
        return this.f6231a;
    }

    public Long getDt() {
        return this.h;
    }

    public Integer getId() {
        return this.k;
    }

    public SMain getMain() {
        return this.d;
    }

    public String getName() {
        return this.l;
    }

    public Sys getSys() {
        return this.i;
    }

    public Integer getTimezone() {
        return this.j;
    }

    public Integer getVisibility() {
        return this.e;
    }

    public List<SWeather> getWeather() {
        return this.b;
    }

    public SWind getWind() {
        return this.f;
    }

    public void setBase(String str) {
        this.c = str;
    }

    public void setClouds(SClouds sClouds) {
        this.g = sClouds;
    }

    public void setCod(Integer num) {
        this.m = num;
    }

    public void setCoord(SCoord sCoord) {
        this.f6231a = sCoord;
    }

    public void setDt(Long l) {
        this.h = l;
    }

    public void setId(Integer num) {
        this.k = num;
    }

    public void setMain(SMain sMain) {
        this.d = sMain;
    }

    public void setName(String str) {
        this.l = str;
    }

    public void setSys(Sys sys) {
        this.i = sys;
    }

    public void setTimezone(Integer num) {
        this.j = num;
    }

    public void setVisibility(Integer num) {
        this.e = num;
    }

    public void setWeather(List<SWeather> list) {
        this.b = list;
    }

    public void setWind(SWind sWind) {
        this.f = sWind;
    }
}
