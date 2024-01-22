package com.coveiot.android.weathersdk.server.network.forecastmodel;

import com.coveiot.android.weathersdk.server.network.model.SCoord;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SCity {
    @SerializedName("id")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    public Integer f6222a;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    @Expose
    public String b;
    @SerializedName("coord")
    @Expose
    public SCoord c;
    @SerializedName("country")
    @Expose
    public String d;
    @SerializedName("population")
    @Expose
    public Double e;
    @SerializedName("timezone")
    @Expose
    public Integer f;
    @SerializedName("sunrise")
    @Expose
    public Long g;
    @SerializedName("sunset")
    @Expose
    public Long h;

    public SCoord getCoord() {
        return this.c;
    }

    public String getCountry() {
        return this.d;
    }

    public Integer getId() {
        return this.f6222a;
    }

    public String getName() {
        return this.b;
    }

    public Double getPopulation() {
        return this.e;
    }

    public Long getSunrise() {
        return this.g;
    }

    public Long getSunset() {
        return this.h;
    }

    public Integer getTimezone() {
        return this.f;
    }

    public void setCoord(SCoord sCoord) {
        this.c = sCoord;
    }

    public void setCountry(String str) {
        this.d = str;
    }

    public void setId(Integer num) {
        this.f6222a = num;
    }

    public void setName(String str) {
        this.b = str;
    }

    public void setPopulation(Double d) {
        this.e = d;
    }

    public void setSunrise(Long l) {
        this.g = l;
    }

    public void setSunset(Long l) {
        this.h = l;
    }

    public void setTimezone(Integer num) {
        this.f = num;
    }
}
