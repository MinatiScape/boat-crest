package com.airbnb.lottie.value;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
/* loaded from: classes.dex */
public class LottieValueCallback<T> {

    /* renamed from: a  reason: collision with root package name */
    public final LottieFrameInfo<T> f2106a;
    @Nullable
    public BaseKeyframeAnimation<?, ?> b;
    @Nullable
    public T value;

    public LottieValueCallback() {
        this.f2106a = new LottieFrameInfo<>();
        this.value = null;
    }

    @Nullable
    public T getValue(LottieFrameInfo<T> lottieFrameInfo) {
        return this.value;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public final T getValueInternal(float f, float f2, T t, T t2, float f3, float f4, float f5) {
        return getValue(this.f2106a.set(f, f2, t, t2, f3, f4, f5));
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public final void setAnimation(@Nullable BaseKeyframeAnimation<?, ?> baseKeyframeAnimation) {
        this.b = baseKeyframeAnimation;
    }

    public final void setValue(@Nullable T t) {
        this.value = t;
        BaseKeyframeAnimation<?, ?> baseKeyframeAnimation = this.b;
        if (baseKeyframeAnimation != null) {
            baseKeyframeAnimation.notifyListeners();
        }
    }

    public LottieValueCallback(@Nullable T t) {
        this.f2106a = new LottieFrameInfo<>();
        this.value = null;
        this.value = t;
    }
}
