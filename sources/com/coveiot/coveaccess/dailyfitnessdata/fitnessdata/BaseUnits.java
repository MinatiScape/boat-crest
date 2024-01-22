package com.coveiot.coveaccess.dailyfitnessdata.fitnessdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class BaseUnits {
    @SerializedName("speed")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6458a;
    @SerializedName("distance")
    @Expose
    private String b;
    @SerializedName("activeTime")
    @Expose
    private String c;
    @SerializedName("cadence")
    @Expose
    private String d;
    @SerializedName("power")
    @Expose
    private String e;
    @SerializedName("lapDistance")
    @Expose
    private String f;

    public String getActiveTime() {
        return this.c;
    }

    public String getCadence() {
        return this.d;
    }

    public String getDistance() {
        return this.b;
    }

    public String getLapDistance() {
        return this.f;
    }

    public String getPower() {
        return this.e;
    }

    public String getSpeed() {
        return this.f6458a;
    }

    public void setActiveTime(String str) {
        this.c = str;
    }

    public void setCadence(String str) {
        this.d = str;
    }

    public void setDistance(String str) {
        this.b = str;
    }

    public void setLapDistance(String str) {
        this.f = str;
    }

    public void setPower(String str) {
        this.e = str;
    }

    public void setSpeed(String str) {
        this.f6458a = str;
    }
}
