package com.crrepa.ble.conn.bean;
/* loaded from: classes9.dex */
public class CRPStepInfo {

    /* renamed from: a  reason: collision with root package name */
    public int f7667a;
    public int b;
    public int c;
    public int d;

    public CRPStepInfo() {
    }

    public CRPStepInfo(int i, int i2, int i3) {
        this.f7667a = i;
        this.b = i2;
        this.c = i3;
    }

    public CRPStepInfo(int i, int i2, int i3, int i4) {
        this.f7667a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    public int getCalories() {
        return this.c;
    }

    public int getDistance() {
        return this.b;
    }

    public int getSteps() {
        return this.f7667a;
    }

    public int getTime() {
        return this.d;
    }

    public void setCalories(int i) {
        this.c = i;
    }

    public void setDistance(int i) {
        this.b = i;
    }

    public void setSteps(int i) {
        this.f7667a = i;
    }

    public void setTime(int i) {
        this.d = i;
    }
}
