package com.htsmart.wristband2.bean;
/* loaded from: classes11.dex */
public class HealthyDataResult {

    /* renamed from: a  reason: collision with root package name */
    public int f11962a;
    public int b;
    public int c;
    public int d;
    public int e;
    public float f;
    public float g;
    public int h;

    public int getDiastolicPressure() {
        return this.c;
    }

    public int getHeartRate() {
        return this.f11962a;
    }

    public int getOxygen() {
        return this.b;
    }

    public int getPressure() {
        return this.h;
    }

    public int getRespiratoryRate() {
        return this.e;
    }

    public int getSystolicPressure() {
        return this.d;
    }

    public float getTemperatureBody() {
        return this.g;
    }

    public float getTemperatureWrist() {
        return this.f;
    }

    public void setDiastolicPressure(int i) {
        this.c = i;
    }

    public void setHeartRate(int i) {
        this.f11962a = i;
    }

    public void setOxygen(int i) {
        this.b = i;
    }

    public void setPressure(int i) {
        this.h = i;
    }

    public void setRespiratoryRate(int i) {
        this.e = i;
    }

    public void setSystolicPressure(int i) {
        this.d = i;
    }

    public void setTemperatureBody(float f) {
        this.g = f;
    }

    public void setTemperatureWrist(float f) {
        this.f = f;
    }

    public String toString() {
        return "heartRate(" + this.f11962a + ")   oxygen(" + this.b + ")   diastolicPressure(" + this.c + ")   systolicPressure(" + this.d + ")   respiratoryRate(" + this.e + ")";
    }
}
