package com.airbnb.lottie.value;
/* loaded from: classes.dex */
public class ScaleXY {

    /* renamed from: a  reason: collision with root package name */
    public float f2107a;
    public float b;

    public ScaleXY(float f, float f2) {
        this.f2107a = f;
        this.b = f2;
    }

    public boolean equals(float f, float f2) {
        return this.f2107a == f && this.b == f2;
    }

    public float getScaleX() {
        return this.f2107a;
    }

    public float getScaleY() {
        return this.b;
    }

    public void set(float f, float f2) {
        this.f2107a = f;
        this.b = f2;
    }

    public String toString() {
        return getScaleX() + "x" + getScaleY();
    }

    public ScaleXY() {
        this(1.0f, 1.0f);
    }
}
