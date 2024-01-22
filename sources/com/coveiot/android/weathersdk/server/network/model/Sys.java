package com.coveiot.android.weathersdk.server.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class Sys {
    @SerializedName("type")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    public Integer f6235a;
    @SerializedName("id")
    @Expose
    public Integer b;
    @SerializedName("country")
    @Expose
    public String c;
    @SerializedName("sunrise")
    @Expose
    public Long d;
    @SerializedName("sunset")
    @Expose
    public Long e;

    public String getCountry() {
        return this.c;
    }

    public Integer getId() {
        return this.b;
    }

    public Long getSunrise() {
        return this.d;
    }

    public Long getSunset() {
        return this.e;
    }

    public Integer getType() {
        return this.f6235a;
    }

    public void setCountry(String str) {
        this.c = str;
    }

    public void setId(Integer num) {
        this.b = num;
    }

    public void setSunrise(Long l) {
        this.d = l;
    }

    public void setSunset(Long l) {
        this.e = l;
    }

    public void setType(Integer num) {
        this.f6235a = num;
    }
}
