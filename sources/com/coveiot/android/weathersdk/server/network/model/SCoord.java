package com.coveiot.android.weathersdk.server.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SCoord {
    @SerializedName("lon")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    public Double f6230a;
    @SerializedName("lat")
    @Expose
    public Double b;

    public Double getLat() {
        return this.b;
    }

    public Double getLon() {
        return this.f6230a;
    }

    public void setLat(Double d) {
        this.b = d;
    }

    public void setLon(Double d) {
        this.f6230a = d;
    }
}
