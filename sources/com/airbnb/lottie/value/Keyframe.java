package com.airbnb.lottie.value;

import android.graphics.PointF;
import android.view.animation.Interpolator;
import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieComposition;
/* loaded from: classes.dex */
public class Keyframe<T> {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final LottieComposition f2104a;
    public float b;
    public float c;
    public int d;
    public int e;
    @Nullable
    public Float endFrame;
    @Nullable
    public T endValue;
    public float f;
    public float g;
    @Nullable
    public final Interpolator interpolator;
    public PointF pathCp1;
    public PointF pathCp2;
    public final float startFrame;
    @Nullable
    public final T startValue;
    @Nullable
    public final Interpolator xInterpolator;
    @Nullable
    public final Interpolator yInterpolator;

    public Keyframe(LottieComposition lottieComposition, @Nullable T t, @Nullable T t2, @Nullable Interpolator interpolator, float f, @Nullable Float f2) {
        this.b = -3987645.8f;
        this.c = -3987645.8f;
        this.d = 784923401;
        this.e = 784923401;
        this.f = Float.MIN_VALUE;
        this.g = Float.MIN_VALUE;
        this.pathCp1 = null;
        this.pathCp2 = null;
        this.f2104a = lottieComposition;
        this.startValue = t;
        this.endValue = t2;
        this.interpolator = interpolator;
        this.xInterpolator = null;
        this.yInterpolator = null;
        this.startFrame = f;
        this.endFrame = f2;
    }

    public boolean containsProgress(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        return f >= getStartProgress() && f < getEndProgress();
    }

    public Keyframe<T> copyWith(T t, T t2) {
        return new Keyframe<>(t, t2);
    }

    public float getEndProgress() {
        if (this.f2104a == null) {
            return 1.0f;
        }
        if (this.g == Float.MIN_VALUE) {
            if (this.endFrame == null) {
                this.g = 1.0f;
            } else {
                this.g = getStartProgress() + ((this.endFrame.floatValue() - this.startFrame) / this.f2104a.getDurationFrames());
            }
        }
        return this.g;
    }

    public float getEndValueFloat() {
        if (this.c == -3987645.8f) {
            this.c = ((Float) this.endValue).floatValue();
        }
        return this.c;
    }

    public int getEndValueInt() {
        if (this.e == 784923401) {
            this.e = ((Integer) this.endValue).intValue();
        }
        return this.e;
    }

    public float getStartProgress() {
        LottieComposition lottieComposition = this.f2104a;
        if (lottieComposition == null) {
            return 0.0f;
        }
        if (this.f == Float.MIN_VALUE) {
            this.f = (this.startFrame - lottieComposition.getStartFrame()) / this.f2104a.getDurationFrames();
        }
        return this.f;
    }

    public float getStartValueFloat() {
        if (this.b == -3987645.8f) {
            this.b = ((Float) this.startValue).floatValue();
        }
        return this.b;
    }

    public int getStartValueInt() {
        if (this.d == 784923401) {
            this.d = ((Integer) this.startValue).intValue();
        }
        return this.d;
    }

    public boolean isStatic() {
        return this.interpolator == null && this.xInterpolator == null && this.yInterpolator == null;
    }

    public String toString() {
        return "Keyframe{startValue=" + this.startValue + ", endValue=" + this.endValue + ", startFrame=" + this.startFrame + ", endFrame=" + this.endFrame + ", interpolator=" + this.interpolator + '}';
    }

    public Keyframe(LottieComposition lottieComposition, @Nullable T t, @Nullable T t2, @Nullable Interpolator interpolator, @Nullable Interpolator interpolator2, float f, @Nullable Float f2) {
        this.b = -3987645.8f;
        this.c = -3987645.8f;
        this.d = 784923401;
        this.e = 784923401;
        this.f = Float.MIN_VALUE;
        this.g = Float.MIN_VALUE;
        this.pathCp1 = null;
        this.pathCp2 = null;
        this.f2104a = lottieComposition;
        this.startValue = t;
        this.endValue = t2;
        this.interpolator = null;
        this.xInterpolator = interpolator;
        this.yInterpolator = interpolator2;
        this.startFrame = f;
        this.endFrame = f2;
    }

    public Keyframe(LottieComposition lottieComposition, @Nullable T t, @Nullable T t2, @Nullable Interpolator interpolator, @Nullable Interpolator interpolator2, @Nullable Interpolator interpolator3, float f, @Nullable Float f2) {
        this.b = -3987645.8f;
        this.c = -3987645.8f;
        this.d = 784923401;
        this.e = 784923401;
        this.f = Float.MIN_VALUE;
        this.g = Float.MIN_VALUE;
        this.pathCp1 = null;
        this.pathCp2 = null;
        this.f2104a = lottieComposition;
        this.startValue = t;
        this.endValue = t2;
        this.interpolator = interpolator;
        this.xInterpolator = interpolator2;
        this.yInterpolator = interpolator3;
        this.startFrame = f;
        this.endFrame = f2;
    }

    public Keyframe(T t) {
        this.b = -3987645.8f;
        this.c = -3987645.8f;
        this.d = 784923401;
        this.e = 784923401;
        this.f = Float.MIN_VALUE;
        this.g = Float.MIN_VALUE;
        this.pathCp1 = null;
        this.pathCp2 = null;
        this.f2104a = null;
        this.startValue = t;
        this.endValue = t;
        this.interpolator = null;
        this.xInterpolator = null;
        this.yInterpolator = null;
        this.startFrame = Float.MIN_VALUE;
        this.endFrame = Float.valueOf(Float.MAX_VALUE);
    }

    public Keyframe(T t, T t2) {
        this.b = -3987645.8f;
        this.c = -3987645.8f;
        this.d = 784923401;
        this.e = 784923401;
        this.f = Float.MIN_VALUE;
        this.g = Float.MIN_VALUE;
        this.pathCp1 = null;
        this.pathCp2 = null;
        this.f2104a = null;
        this.startValue = t;
        this.endValue = t2;
        this.interpolator = null;
        this.xInterpolator = null;
        this.yInterpolator = null;
        this.startFrame = Float.MIN_VALUE;
        this.endFrame = Float.valueOf(Float.MAX_VALUE);
    }
}
