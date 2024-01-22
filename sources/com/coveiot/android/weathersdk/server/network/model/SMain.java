package com.coveiot.android.weathersdk.server.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SMain {
    @SerializedName("temp")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    public Double f6232a;
    @SerializedName("feels_like")
    @Expose
    public Double b;
    @SerializedName("temp_min")
    @Expose
    public Double c;
    @SerializedName("temp_max")
    @Expose
    public Double d;
    @SerializedName("pressure")
    @Expose
    public Double e;
    @SerializedName("humidity")
    @Expose
    public Double f;
    @SerializedName("sea_level")
    @Expose
    public Integer g;
    @SerializedName("grnd_level")
    @Expose
    public Integer h;
    @SerializedName("temp_kf")
    @Expose
    public Double i;

    public Double getFeelsLike() {
        return this.b;
    }

    public Integer getGroundLevel() {
        return this.h;
    }

    public Double getHumidity() {
        return this.f;
    }

    public Double getPressure() {
        return this.e;
    }

    public Integer getSeaLevel() {
        return this.g;
    }

    public Double getTemp() {
        return this.f6232a;
    }

    public Double getTempKf() {
        return this.i;
    }

    public Double getTempMax() {
        return this.d;
    }

    public Double getTempMin() {
        return this.c;
    }

    public void setFeelsLike(Double d) {
        this.b = d;
    }

    public void setGroundLevel(Integer num) {
        this.h = num;
    }

    public void setHumidity(Double d) {
        this.f = d;
    }

    public void setPressure(Double d) {
        this.e = d;
    }

    public void setSeaLevel(Integer num) {
        this.g = num;
    }

    public void setTemp(Double d) {
        this.f6232a = d;
    }

    public void setTempKf(Double d) {
        this.i = d;
    }

    public void setTempMax(Double d) {
        this.d = d;
    }

    public void setTempMin(Double d) {
        this.c = d;
    }
}
