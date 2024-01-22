package com.htsmart.wristband2.bean.data;
/* loaded from: classes11.dex */
public class TodayTotalData {

    /* renamed from: a  reason: collision with root package name */
    public int f11981a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public long j;

    public int getCalorie() {
        return this.c;
    }

    public int getDeepSleep() {
        return this.d;
    }

    public int getDeltaCalorie() {
        return this.i;
    }

    public int getDeltaDistance() {
        return this.h;
    }

    public int getDeltaStep() {
        return this.g;
    }

    public int getDistance() {
        return this.b;
    }

    public int getHeartRate() {
        return this.f;
    }

    public int getLightSleep() {
        return this.e;
    }

    public int getStep() {
        return this.f11981a;
    }

    public long getTimeStamp() {
        return this.j;
    }

    public void setCalorie(int i) {
        this.c = i;
    }

    public void setDeepSleep(int i) {
        this.d = i;
    }

    public void setDeltaCalorie(int i) {
        this.i = i;
    }

    public void setDeltaDistance(int i) {
        this.h = i;
    }

    public void setDeltaStep(int i) {
        this.g = i;
    }

    public void setDistance(int i) {
        this.b = i;
    }

    public void setHeartRate(int i) {
        this.f = i;
    }

    public void setLightSleep(int i) {
        this.e = i;
    }

    public void setStep(int i) {
        this.f11981a = i;
    }

    public void setTimeStamp(long j) {
        this.j = j;
    }

    public String toString() {
        return "step:" + this.f11981a + "    distance:" + this.b + "    calorie:" + this.c + "    deepSleep:" + this.d + "    lightSleep:" + this.e + "    heartRate:" + this.f + "    deltaStep:" + this.g + "    deltaDistance:" + this.h + "    deltaCalorie:" + this.i;
    }
}
