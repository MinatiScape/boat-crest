package com.coveiot.android.bleabstract.response;
/* loaded from: classes2.dex */
public class HeartRateBpIntervalData {

    /* renamed from: a  reason: collision with root package name */
    public int f3633a;
    public int b;
    public int c;

    public int getDiastolicBp() {
        return this.c;
    }

    public int getHeartRate() {
        return this.f3633a;
    }

    public int getSystolicBp() {
        return this.b;
    }

    public void setDiastolicBp(int i) {
        this.c = i;
    }

    public void setHeartRate(int i) {
        this.f3633a = i;
    }

    public void setSystolicBp(int i) {
        this.b = i;
    }
}
