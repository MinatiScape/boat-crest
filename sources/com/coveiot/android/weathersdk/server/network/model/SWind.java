package com.coveiot.android.weathersdk.server.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SWind {
    @SerializedName("speed")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    public Double f6234a;
    @SerializedName("deg")
    @Expose
    public Double b;
    @SerializedName("gust")
    @Expose
    public Double c;

    public Double getDeg() {
        return this.b;
    }

    public Double getGust() {
        return this.c;
    }

    public Double getSpeed() {
        return this.f6234a;
    }

    public void setDeg(Double d) {
        this.b = d;
    }

    public void setGust(Double d) {
        this.c = d;
    }

    public void setSpeed(Double d) {
        this.f6234a = d;
    }
}
