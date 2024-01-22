package com.coveiot.android.bleabstract.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class ActivityStepsSample {

    /* renamed from: a  reason: collision with root package name */
    public int f3394a;
    public long b;
    public double c;
    public double d;
    public float e;
    public float f;

    public final double getCalories() {
        return this.d;
    }

    public final double getDistance() {
        return this.c;
    }

    public final float getPaceInSecond() {
        return this.f;
    }

    public final float getSpeed() {
        return this.e;
    }

    public final long getStepsTimeStamp() {
        return this.b;
    }

    public final int getStepsValue() {
        return this.f3394a;
    }

    public final void setCalories(double d) {
        this.d = d;
    }

    public final void setDistance(double d) {
        this.c = d;
    }

    public final void setPaceInSecond(float f) {
        this.f = f;
    }

    public final void setSpeed(float f) {
        this.e = f;
    }

    public final void setStepsTimeStamp(long j) {
        this.b = j;
    }

    public final void setStepsValue(int i) {
        this.f3394a = i;
    }

    @NotNull
    public String toString() {
        return "ActivityStepsSample(stepsValue=" + this.f3394a + ", stepsTimeStamp=" + this.b + ", distance=" + this.c + ", calories=" + this.d + HexStringBuilder.COMMENT_END_CHAR;
    }
}
