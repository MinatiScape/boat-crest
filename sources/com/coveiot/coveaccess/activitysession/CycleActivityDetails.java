package com.coveiot.coveaccess.activitysession;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class CycleActivityDetails extends ActivitiesSampleDetails {
    @SerializedName("avgPower")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private Integer f6385a;
    @SerializedName("maxPower")
    @Expose
    private Integer b;
    @SerializedName("avgCadence")
    @Expose
    private Integer c;
    @SerializedName("maxCadence")
    @Expose
    private Integer d;
    @SerializedName("totalLaps")
    @Expose
    private Integer e;
    @SerializedName("lapDistance")
    @Expose
    private Integer f;

    public Integer getAvgCadence() {
        return this.c;
    }

    public Integer getAvgPower() {
        return this.f6385a;
    }

    public Integer getLapDistance() {
        return this.f;
    }

    public Integer getMaxCadence() {
        return this.d;
    }

    public Integer getMaxPower() {
        return this.b;
    }

    public Integer getTotalLaps() {
        return this.e;
    }

    public void setAvgCadence(Integer num) {
        this.c = num;
    }

    public void setAvgPower(Integer num) {
        this.f6385a = num;
    }

    public void setLapDistance(Integer num) {
        this.f = num;
    }

    public void setMaxCadence(Integer num) {
        this.d = num;
    }

    public void setMaxPower(Integer num) {
        this.b = num;
    }

    public void setTotalLaps(Integer num) {
        this.e = num;
    }
}
