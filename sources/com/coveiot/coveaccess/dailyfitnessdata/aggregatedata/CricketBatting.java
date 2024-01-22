package com.coveiot.coveaccess.dailyfitnessdata.aggregatedata;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class CricketBatting {
    @SerializedName("totalDuration")

    /* renamed from: a  reason: collision with root package name */
    private Integer f6450a;
    @SerializedName("totalSessions")
    private Integer b;
    @SerializedName("totalTargetAchieved")
    private Integer c;
    @SerializedName("totalSwings")
    private Integer d;
    @SerializedName("totalHits")
    private Integer e;
    @SerializedName("hitPercentage")
    private Float f;
    @SerializedName("avgHandSpeed")
    private Float g;
    @SerializedName("maxHandSpeed")
    private Float h;

    public Float getAvgHandSpeed() {
        return this.g;
    }

    public Float getHitPercentage() {
        return this.f;
    }

    public Float getMaxHandSpeed() {
        return this.h;
    }

    public Integer getTotalDuration() {
        return this.f6450a;
    }

    public Integer getTotalHits() {
        return this.e;
    }

    public Integer getTotalSessions() {
        return this.b;
    }

    public Integer getTotalSwings() {
        return this.d;
    }

    public Integer getTotalTargetAchieved() {
        return this.c;
    }

    public void setAvgHandSpeed(Float f) {
        this.g = f;
    }

    public void setHitPercentage(Float f) {
        this.f = f;
    }

    public void setMaxHandSpeed(Float f) {
        this.h = f;
    }

    public void setTotalDuration(Integer num) {
        this.f6450a = num;
    }

    public void setTotalHits(Integer num) {
        this.e = num;
    }

    public void setTotalSessions(Integer num) {
        this.b = num;
    }

    public void setTotalSwings(Integer num) {
        this.d = num;
    }

    public void setTotalTargetAchieved(Integer num) {
        this.c = num;
    }
}
