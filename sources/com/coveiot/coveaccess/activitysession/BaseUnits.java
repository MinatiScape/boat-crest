package com.coveiot.coveaccess.activitysession;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class BaseUnits {
    @SerializedName("activityDuration")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6383a;
    @SerializedName("userAge")
    @Expose
    private String b;
    @SerializedName("userWeight")
    @Expose
    private String c;
    @SerializedName("userHeight")
    @Expose
    private String d;
    @SerializedName("strideLength")
    @Expose
    private String e;
    @SerializedName("speed")
    @Expose
    private String f;
    @SerializedName("sampleRate")
    @Expose
    private String g;
    @SerializedName("distance")
    @Expose
    private String h;
    @SerializedName("hrZoneDuration")
    @Expose
    private String i;
    @SerializedName("power")
    @Expose
    private String j;
    @SerializedName("cadence")
    @Expose
    private String k;
    @SerializedName("strokeLength")
    @Expose
    private String l;
    @SerializedName("pace")
    @Expose
    private String m;
    @SerializedName("lapDistance")
    @Expose
    private String n;
    @SerializedName("stepFreq")
    @Expose
    private String o;
    @SerializedName("handSpeed")
    @Expose
    private String p;
    @SerializedName("poolLength")
    @Expose
    private String q;
    @SerializedName("strokeFreq")
    @Expose
    private String r;
    @SerializedName("vo2Max")
    @Expose
    private String s;
    @SerializedName("jumpFreq")
    @Expose
    private String t;

    public String getActivityDuration() {
        return this.f6383a;
    }

    public String getCadence() {
        return this.k;
    }

    public String getDistance() {
        return this.h;
    }

    public String getHandSpeed() {
        return this.p;
    }

    public String getHrZoneDuration() {
        return this.i;
    }

    public String getJumpFreq() {
        return this.t;
    }

    public String getLapDistance() {
        return this.n;
    }

    public String getPace() {
        return this.m;
    }

    public String getPoolLength() {
        return this.q;
    }

    public String getPower() {
        return this.j;
    }

    public String getSampleRate() {
        return this.g;
    }

    public String getSpeed() {
        return this.f;
    }

    public String getStepFreq() {
        return this.o;
    }

    public String getStrideLength() {
        return this.e;
    }

    public String getStrokeFreq() {
        return this.r;
    }

    public String getStrokeLength() {
        return this.l;
    }

    public String getUserAge() {
        return this.b;
    }

    public String getUserHeight() {
        return this.d;
    }

    public String getUserWeight() {
        return this.c;
    }

    public String getVo2Max() {
        return this.s;
    }

    public void setActivityDuration(String str) {
        this.f6383a = str;
    }

    public void setCadence(String str) {
        this.k = str;
    }

    public void setDistance(String str) {
        this.h = str;
    }

    public void setHandSpeed(String str) {
        this.p = str;
    }

    public void setHrZoneDuration(String str) {
        this.i = str;
    }

    public void setJumpFreq(String str) {
        this.t = str;
    }

    public void setLapDistance(String str) {
        this.n = str;
    }

    public void setPace(String str) {
        this.m = str;
    }

    public void setPoolLength(String str) {
        this.q = str;
    }

    public void setPower(String str) {
        this.j = str;
    }

    public void setSampleRate(String str) {
        this.g = str;
    }

    public void setSpeed(String str) {
        this.f = str;
    }

    public void setStepFreq(String str) {
        this.o = str;
    }

    public void setStrideLength(String str) {
        this.e = str;
    }

    public void setStrokeFreq(String str) {
        this.r = str;
    }

    public void setStrokeLength(String str) {
        this.l = str;
    }

    public void setUserAge(String str) {
        this.b = str;
    }

    public void setUserHeight(String str) {
        this.d = str;
    }

    public void setUserWeight(String str) {
        this.c = str;
    }

    public void setVo2Max(String str) {
        this.s = str;
    }
}
