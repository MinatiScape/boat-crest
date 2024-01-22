package com.coveiot.android.weathersdk.server.network.forecastmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
/* loaded from: classes8.dex */
public class SFeelsLike {
    @SerializedName(WeatherCriteria.UNIT_TYPE_DAY)
    @Expose

    /* renamed from: a  reason: collision with root package name */
    public Double f6223a;
    @SerializedName("night")
    @Expose
    public Double b;
    @SerializedName("eve")
    @Expose
    public Double c;
    @SerializedName("morn")
    @Expose
    public Double d;

    public Double getDay() {
        return this.f6223a;
    }

    public Double getEve() {
        return this.c;
    }

    public Double getMorn() {
        return this.d;
    }

    public Double getNight() {
        return this.b;
    }

    public void setDay(Double d) {
        this.f6223a = d;
    }

    public void setEve(Double d) {
        this.c = d;
    }

    public void setMorn(Double d) {
        this.d = d;
    }

    public void setNight(Double d) {
        this.b = d;
    }
}
