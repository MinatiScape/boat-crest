package com.coveiot.covepreferences.data;
/* loaded from: classes8.dex */
public class HealthDataModel {

    /* renamed from: a  reason: collision with root package name */
    public int f7028a;
    public int b;
    public int c;
    public Double d;
    public long e;
    public long f;

    public HealthDataModel(int i, int i2, int i3, long j) {
        this.f7028a = i;
        this.b = i2;
        this.c = i3;
        this.e = j;
    }

    public int getDiastolicBP() {
        return this.c;
    }

    public int getHheartRate() {
        return this.f7028a;
    }

    public int getSystolicBp() {
        return this.b;
    }

    public Double getTemperature() {
        return this.d;
    }

    public long getTemperatureTimeStamp() {
        return this.f;
    }

    public long getTimeStamp() {
        return this.e;
    }

    public void setDiastolicBP(int i) {
        this.c = i;
    }

    public void setHheartRate(int i) {
        this.f7028a = i;
    }

    public void setSystolicBp(int i) {
        this.b = i;
    }

    public void setTemperature(Double d) {
        this.d = d;
    }

    public void setTemperatureTimeStamp(long j) {
        this.f = j;
    }

    public void setTimeStamp(long j) {
        this.e = j;
    }

    public HealthDataModel() {
    }
}
