package com.coveiot.utils.eventmodels;
/* loaded from: classes9.dex */
public final class LiveSteps {

    /* renamed from: a  reason: collision with root package name */
    public int f7615a;
    public float b;
    public int c;
    public final int d;
    public final float e;
    public final int f;

    public LiveSteps(int i, float f, int i2) {
        this.d = i;
        this.e = f;
        this.f = i2;
        this.f7615a = i;
        this.b = f;
        this.c = i2;
    }

    public final float getCal() {
        return this.b;
    }

    public final float getCalories() {
        return this.e;
    }

    public final int getDist() {
        return this.c;
    }

    public final int getDistance() {
        return this.f;
    }

    public final int getLiveSteps() {
        return this.f7615a;
    }

    public final int getValue() {
        return this.d;
    }

    public final void setCal(float f) {
        this.b = f;
    }

    public final void setDist(int i) {
        this.c = i;
    }

    public final void setLiveSteps(int i) {
        this.f7615a = i;
    }
}
