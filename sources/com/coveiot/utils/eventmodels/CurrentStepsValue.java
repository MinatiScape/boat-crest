package com.coveiot.utils.eventmodels;
/* loaded from: classes9.dex */
public final class CurrentStepsValue {

    /* renamed from: a  reason: collision with root package name */
    public final int f7612a;
    public final int b;
    public final float c;

    public CurrentStepsValue(int i, int i2, float f) {
        this.f7612a = i;
        this.b = i2;
        this.c = f;
    }

    public final float getCalories() {
        return this.c;
    }

    public final int getDistance() {
        return this.b;
    }

    public final int getSteps() {
        return this.f7612a;
    }
}
