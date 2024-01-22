package com.h6ah4i.android.widget.advrecyclerview.swipeable;

import android.view.animation.Interpolator;
/* loaded from: classes11.dex */
public class c implements Interpolator {

    /* renamed from: a  reason: collision with root package name */
    public final float f11927a;

    public c(float f) {
        this.f11927a = f;
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        float f2 = 1.0f - f;
        return this.f11927a * (1.0f - (f2 * f2));
    }
}
