package com.airbnb.lottie.value;

import androidx.annotation.RestrictTo;
/* loaded from: classes.dex */
public class LottieFrameInfo<T> {

    /* renamed from: a  reason: collision with root package name */
    public float f2105a;
    public float b;
    public T c;
    public T d;
    public float e;
    public float f;
    public float g;

    public float getEndFrame() {
        return this.b;
    }

    public T getEndValue() {
        return this.d;
    }

    public float getInterpolatedKeyframeProgress() {
        return this.f;
    }

    public float getLinearKeyframeProgress() {
        return this.e;
    }

    public float getOverallProgress() {
        return this.g;
    }

    public float getStartFrame() {
        return this.f2105a;
    }

    public T getStartValue() {
        return this.c;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public LottieFrameInfo<T> set(float f, float f2, T t, T t2, float f3, float f4, float f5) {
        this.f2105a = f;
        this.b = f2;
        this.c = t;
        this.d = t2;
        this.e = f3;
        this.f = f4;
        this.g = f5;
        return this;
    }
}
