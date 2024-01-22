package com.h6ah4i.android.widget.advrecyclerview.draggable;

import android.view.animation.Interpolator;
/* loaded from: classes11.dex */
public class BasicSwapTargetTranslationInterpolator implements Interpolator {

    /* renamed from: a  reason: collision with root package name */
    public final float f11899a;
    public final float b;
    public final float c;

    public BasicSwapTargetTranslationInterpolator() {
        this(0.3f);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        if (Math.abs(f - 0.5f) < this.b) {
            return (f - this.f11899a) * this.c;
        }
        return f < 0.5f ? 0.0f : 1.0f;
    }

    public BasicSwapTargetTranslationInterpolator(float f) {
        if (f >= 0.0f && f < 0.5f) {
            float f2 = 1.0f - (2.0f * f);
            this.f11899a = f;
            this.b = 0.5f * f2;
            this.c = 1.0f / f2;
            return;
        }
        throw new IllegalArgumentException("Invalid threshold range: " + f);
    }
}
