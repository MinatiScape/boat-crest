package com.coveiot.coveaccess.dailyfitnessdata.aggregatedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class BaseUnits {
    @SerializedName("strokeLength")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6449a;
    @SerializedName("distance")
    @Expose
    private String b;
    @SerializedName("activeTime")
    @Expose
    private String c;
    @SerializedName("hrZoneDuration")
    @Expose
    private String d;
    @SerializedName("userAge")
    @Expose
    private String e;
    @SerializedName("userWeight")
    @Expose
    private String f;
    @SerializedName("userHeight")
    @Expose
    private String g;
    @SerializedName("strideLength")
    @Expose
    private String h;
    @SerializedName("lapDistance")
    @Expose
    private String i;
    @SerializedName("handSpeed")
    @Expose
    private String j;

    public String getActiveTime() {
        return this.c;
    }

    public String getDistance() {
        return this.b;
    }

    public String getHandSpeed() {
        return this.j;
    }

    public String getHrZoneDuration() {
        return this.d;
    }

    public String getLapDistance() {
        return this.i;
    }

    public String getStrideLength() {
        return this.h;
    }

    public String getStrokeLength() {
        return this.f6449a;
    }

    public String getUserAge() {
        return this.e;
    }

    public String getUserHeight() {
        return this.g;
    }

    public String getUserWeight() {
        return this.f;
    }

    public void setActiveTime(String str) {
        this.c = str;
    }

    public void setDistance(String str) {
        this.b = str;
    }

    public void setHandSpeed(String str) {
        this.j = str;
    }

    public void setHrZoneDuration(String str) {
        this.d = str;
    }

    public void setLapDistance(String str) {
        this.i = str;
    }

    public void setStrideLength(String str) {
        this.h = str;
    }

    public void setStrokeLength(String str) {
        this.f6449a = str;
    }

    public void setUserAge(String str) {
        this.e = str;
    }

    public void setUserHeight(String str) {
        this.g = str;
    }

    public void setUserWeight(String str) {
        this.f = str;
    }
}
