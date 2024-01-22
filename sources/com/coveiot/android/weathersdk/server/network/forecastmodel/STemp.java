package com.coveiot.android.weathersdk.server.network.forecastmodel;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
/* loaded from: classes8.dex */
public class STemp {
    @SerializedName(WeatherCriteria.UNIT_TYPE_DAY)
    @Expose

    /* renamed from: a  reason: collision with root package name */
    public Double f6224a;
    @SerializedName("min")
    @Expose
    public Double b;
    @SerializedName(Constants.PRIORITY_MAX)
    @Expose
    public Double c;
    @SerializedName("night")
    @Expose
    public Double d;
    @SerializedName("eve")
    @Expose
    public Double e;
    @SerializedName("morn")
    @Expose
    public Double f;

    public Double getDay() {
        return this.f6224a;
    }

    public Double getEve() {
        return this.e;
    }

    public Double getMax() {
        return this.c;
    }

    public Double getMin() {
        return this.b;
    }

    public Double getMorn() {
        return this.f;
    }

    public Double getNight() {
        return this.d;
    }

    public void setDay(Double d) {
        this.f6224a = d;
    }

    public void setEve(Double d) {
        this.e = d;
    }

    public void setMax(Double d) {
        this.c = d;
    }

    public void setMin(Double d) {
        this.b = d;
    }

    public void setMorn(Double d) {
        this.f = d;
    }

    public void setNight(Double d) {
        this.d = d;
    }
}
