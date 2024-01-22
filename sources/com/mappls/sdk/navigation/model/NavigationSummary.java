package com.mappls.sdk.navigation.model;
/* loaded from: classes11.dex */
public class NavigationSummary {

    /* renamed from: a  reason: collision with root package name */
    public float f12922a;
    public float b;
    public float c;

    public NavigationSummary(float f, float f2, float f3) {
        this.f12922a = f;
        this.b = f2;
        this.c = f3;
    }

    public float getAverageSpeed() {
        return this.f12922a;
    }

    public float getTotalDistance() {
        return this.b;
    }

    public float getTotalTimeTaken() {
        return this.c;
    }

    public void setAverageSpeed(float f) {
        this.f12922a = f;
    }

    public void setTotalDistance(float f) {
        this.b = f;
    }

    public void setTotalTimeTaken(float f) {
        this.c = f;
    }
}
