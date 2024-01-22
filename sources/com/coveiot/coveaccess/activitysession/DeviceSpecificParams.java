package com.coveiot.coveaccess.activitysession;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class DeviceSpecificParams {
    @SerializedName("magicCode")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private long f6386a;
    @SerializedName("avgHrCount")
    @Expose
    private Integer b;
    @SerializedName("avgHrTime")
    @Expose
    private Integer c;
    @SerializedName("avgSpeedCount")
    @Expose
    private Integer d;
    @SerializedName("avgSpeedTime")
    @Expose
    private Integer e;
    @SerializedName("avgCadenceTime")
    @Expose
    private Integer f;
    @SerializedName("avgCadenceCount")
    @Expose
    private Integer g;
    @SerializedName("avgPowerCount")
    @Expose
    private Integer h;
    @SerializedName("avgPowerTime")
    @Expose
    private Integer i;
    @SerializedName("version")
    @Expose
    private Integer j;
    @SerializedName("activityDataItemCount")
    @Expose
    private Integer k;
    @SerializedName("dataLength")
    @Expose
    private Integer l;
    @SerializedName("packetCount")
    @Expose
    private Integer m;
    @SerializedName("frequencyCount")
    @Expose
    private Integer n;
    @SerializedName("hrItemCount")
    @Expose
    private Integer o;
    @SerializedName("kmSpeedCount")
    @Expose
    private Integer p;
    @SerializedName("itemCount")
    @Expose
    private Integer q;

    public Integer getActivityDataItemCount() {
        return this.k;
    }

    public Integer getAvgCadenceCount() {
        return this.g;
    }

    public Integer getAvgCadenceTime() {
        return this.f;
    }

    public Integer getAvgHrCount() {
        return this.b;
    }

    public Integer getAvgHrTime() {
        return this.c;
    }

    public Integer getAvgPowerCount() {
        return this.h;
    }

    public Integer getAvgPowerTime() {
        return this.i;
    }

    public Integer getAvgSpeedCount() {
        return this.d;
    }

    public Integer getAvgSpeedTime() {
        return this.e;
    }

    public Integer getDataLength() {
        return this.l;
    }

    public Integer getFrequencyCount() {
        return this.n;
    }

    public Integer getHrItemCount() {
        return this.o;
    }

    public Integer getItemCount() {
        return this.q;
    }

    public Integer getKmSpeedCount() {
        return this.p;
    }

    public long getMagicCode() {
        return this.f6386a;
    }

    public Integer getPacketCount() {
        return this.m;
    }

    public Integer getVersion() {
        return this.j;
    }

    public void setActivityDataItemCount(Integer num) {
        this.k = num;
    }

    public void setAvgCadenceCount(Integer num) {
        this.g = num;
    }

    public void setAvgCadenceTime(Integer num) {
        this.f = num;
    }

    public void setAvgHrCount(Integer num) {
        this.b = num;
    }

    public void setAvgHrTime(Integer num) {
        this.c = num;
    }

    public void setAvgPowerCount(Integer num) {
        this.h = num;
    }

    public void setAvgPowerTime(Integer num) {
        this.i = num;
    }

    public void setAvgSpeedCount(Integer num) {
        this.d = num;
    }

    public void setAvgSpeedTime(Integer num) {
        this.e = num;
    }

    public void setDataLength(Integer num) {
        this.l = num;
    }

    public void setFrequencyCount(Integer num) {
        this.n = num;
    }

    public void setHrItemCount(Integer num) {
        this.o = num;
    }

    public void setItemCount(Integer num) {
        this.q = num;
    }

    public void setKmSpeedCount(Integer num) {
        this.p = num;
    }

    public void setMagicCode(long j) {
        this.f6386a = j;
    }

    public void setPacketCount(Integer num) {
        this.m = num;
    }

    public void setVersion(Integer num) {
        this.j = num;
    }
}
