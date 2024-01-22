package com.airbnb.lottie.utils;
/* loaded from: classes.dex */
public class MeanCalculator {

    /* renamed from: a  reason: collision with root package name */
    public float f2100a;
    public int b;

    public void add(float f) {
        float f2 = this.f2100a + f;
        this.f2100a = f2;
        int i = this.b + 1;
        this.b = i;
        if (i == Integer.MAX_VALUE) {
            this.f2100a = f2 / 2.0f;
            this.b = i / 2;
        }
    }

    public float getMean() {
        int i = this.b;
        if (i == 0) {
            return 0.0f;
        }
        return this.f2100a / i;
    }
}
