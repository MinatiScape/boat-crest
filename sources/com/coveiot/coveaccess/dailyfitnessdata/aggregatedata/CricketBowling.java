package com.coveiot.coveaccess.dailyfitnessdata.aggregatedata;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class CricketBowling {
    @SerializedName("totalDuration")

    /* renamed from: a  reason: collision with root package name */
    private Integer f6451a;
    @SerializedName("totalSessions")
    private Integer b;
    @SerializedName("totalTargetAchieved")
    private Integer c;
    @SerializedName("totalBallsBowled")
    private Integer d;
    @SerializedName("avgHandSpeed")
    private Float e;
    @SerializedName("maxHandSpeed")
    private Float f;

    public Float getAvgHandSpeed() {
        return this.e;
    }

    public Float getMaxHandSpeed() {
        return this.f;
    }

    public Integer getTotalBallsBowled() {
        return this.d;
    }

    public Integer getTotalDuration() {
        return this.f6451a;
    }

    public Integer getTotalSessions() {
        return this.b;
    }

    public Integer getTotalTargetAchieved() {
        return this.c;
    }

    public void setAvgHandSpeed(Float f) {
        this.e = f;
    }

    public void setMaxHandSpeed(Float f) {
        this.f = f;
    }

    public void setTotalBallsBowled(Integer num) {
        this.d = num;
    }

    public void setTotalDuration(Integer num) {
        this.f6451a = num;
    }

    public void setTotalSessions(Integer num) {
        this.b = num;
    }

    public void setTotalTargetAchieved(Integer num) {
        this.c = num;
    }
}
