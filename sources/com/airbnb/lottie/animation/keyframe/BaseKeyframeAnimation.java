package com.airbnb.lottie.animation.keyframe;

import android.view.animation.Interpolator;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.airbnb.lottie.L;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public abstract class BaseKeyframeAnimation<K, A> {
    public final c<K> c;
    @Nullable
    public LottieValueCallback<A> valueCallback;

    /* renamed from: a  reason: collision with root package name */
    public final List<AnimationListener> f2007a = new ArrayList(1);
    public boolean b = false;
    public float progress = 0.0f;
    @Nullable
    public A d = null;
    public float e = -1.0f;
    public float f = -1.0f;

    /* loaded from: classes.dex */
    public interface AnimationListener {
        void onValueChanged();
    }

    /* loaded from: classes.dex */
    public static final class b<T> implements c<T> {
        public b() {
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.c
        public boolean a(float f) {
            throw new IllegalStateException("not implemented");
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.c
        public Keyframe<T> b() {
            throw new IllegalStateException("not implemented");
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.c
        public boolean c(float f) {
            return false;
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.c
        public float d() {
            return 0.0f;
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.c
        public float e() {
            return 1.0f;
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.c
        public boolean isEmpty() {
            return true;
        }
    }

    /* loaded from: classes.dex */
    public interface c<T> {
        boolean a(float f);

        Keyframe<T> b();

        boolean c(float f);

        @FloatRange(from = 0.0d, to = 1.0d)
        float d();

        @FloatRange(from = 0.0d, to = 1.0d)
        float e();

        boolean isEmpty();
    }

    /* loaded from: classes.dex */
    public static final class d<T> implements c<T> {

        /* renamed from: a  reason: collision with root package name */
        public final List<? extends Keyframe<T>> f2008a;
        public Keyframe<T> c = null;
        public float d = -1.0f;
        @NonNull
        public Keyframe<T> b = f(0.0f);

        public d(List<? extends Keyframe<T>> list) {
            this.f2008a = list;
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.c
        public boolean a(float f) {
            Keyframe<T> keyframe = this.c;
            Keyframe<T> keyframe2 = this.b;
            if (keyframe == keyframe2 && this.d == f) {
                return true;
            }
            this.c = keyframe2;
            this.d = f;
            return false;
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.c
        @NonNull
        public Keyframe<T> b() {
            return this.b;
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.c
        public boolean c(float f) {
            if (this.b.containsProgress(f)) {
                return !this.b.isStatic();
            }
            this.b = f(f);
            return true;
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.c
        public float d() {
            return this.f2008a.get(0).getStartProgress();
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.c
        public float e() {
            List<? extends Keyframe<T>> list = this.f2008a;
            return list.get(list.size() - 1).getEndProgress();
        }

        public final Keyframe<T> f(float f) {
            List<? extends Keyframe<T>> list = this.f2008a;
            Keyframe<T> keyframe = list.get(list.size() - 1);
            if (f >= keyframe.getStartProgress()) {
                return keyframe;
            }
            for (int size = this.f2008a.size() - 2; size >= 1; size--) {
                Keyframe<T> keyframe2 = this.f2008a.get(size);
                if (this.b != keyframe2 && keyframe2.containsProgress(f)) {
                    return keyframe2;
                }
            }
            return this.f2008a.get(0);
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.c
        public boolean isEmpty() {
            return false;
        }
    }

    /* loaded from: classes.dex */
    public static final class e<T> implements c<T> {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final Keyframe<T> f2009a;
        public float b = -1.0f;

        public e(List<? extends Keyframe<T>> list) {
            this.f2009a = list.get(0);
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.c
        public boolean a(float f) {
            if (this.b == f) {
                return true;
            }
            this.b = f;
            return false;
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.c
        public Keyframe<T> b() {
            return this.f2009a;
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.c
        public boolean c(float f) {
            return !this.f2009a.isStatic();
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.c
        public float d() {
            return this.f2009a.getStartProgress();
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.c
        public float e() {
            return this.f2009a.getEndProgress();
        }

        @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.c
        public boolean isEmpty() {
            return false;
        }
    }

    public BaseKeyframeAnimation(List<? extends Keyframe<K>> list) {
        this.c = d(list);
    }

    public static <T> c<T> d(List<? extends Keyframe<T>> list) {
        if (list.isEmpty()) {
            return new b();
        }
        if (list.size() == 1) {
            return new e(list);
        }
        return new d(list);
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float a() {
        if (this.f == -1.0f) {
            this.f = this.c.e();
        }
        return this.f;
    }

    public void addUpdateListener(AnimationListener animationListener) {
        this.f2007a.add(animationListener);
    }

    public float b() {
        if (this.b) {
            return 0.0f;
        }
        Keyframe<K> currentKeyframe = getCurrentKeyframe();
        if (currentKeyframe.isStatic()) {
            return 0.0f;
        }
        return (this.progress - currentKeyframe.getStartProgress()) / (currentKeyframe.getEndProgress() - currentKeyframe.getStartProgress());
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public final float c() {
        if (this.e == -1.0f) {
            this.e = this.c.d();
        }
        return this.e;
    }

    public Keyframe<K> getCurrentKeyframe() {
        L.beginSection("BaseKeyframeAnimation#getCurrentKeyframe");
        Keyframe<K> b2 = this.c.b();
        L.endSection("BaseKeyframeAnimation#getCurrentKeyframe");
        return b2;
    }

    public float getInterpolatedCurrentKeyframeProgress() {
        Keyframe<K> currentKeyframe = getCurrentKeyframe();
        if (currentKeyframe == null || currentKeyframe.isStatic()) {
            return 0.0f;
        }
        return currentKeyframe.interpolator.getInterpolation(b());
    }

    public float getProgress() {
        return this.progress;
    }

    public A getValue() {
        A value;
        float b2 = b();
        if (this.valueCallback == null && this.c.a(b2)) {
            return this.d;
        }
        Keyframe<K> currentKeyframe = getCurrentKeyframe();
        Interpolator interpolator = currentKeyframe.xInterpolator;
        if (interpolator != null && currentKeyframe.yInterpolator != null) {
            value = getValue(currentKeyframe, b2, interpolator.getInterpolation(b2), currentKeyframe.yInterpolator.getInterpolation(b2));
        } else {
            value = getValue(currentKeyframe, getInterpolatedCurrentKeyframeProgress());
        }
        this.d = value;
        return value;
    }

    public abstract A getValue(Keyframe<K> keyframe, float f);

    public void notifyListeners() {
        for (int i = 0; i < this.f2007a.size(); i++) {
            this.f2007a.get(i).onValueChanged();
        }
    }

    public void setIsDiscrete() {
        this.b = true;
    }

    public void setProgress(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        if (this.c.isEmpty()) {
            return;
        }
        if (f < c()) {
            f = c();
        } else if (f > a()) {
            f = a();
        }
        if (f == this.progress) {
            return;
        }
        this.progress = f;
        if (this.c.c(f)) {
            notifyListeners();
        }
    }

    public void setValueCallback(@Nullable LottieValueCallback<A> lottieValueCallback) {
        LottieValueCallback<A> lottieValueCallback2 = this.valueCallback;
        if (lottieValueCallback2 != null) {
            lottieValueCallback2.setAnimation(null);
        }
        this.valueCallback = lottieValueCallback;
        if (lottieValueCallback != null) {
            lottieValueCallback.setAnimation(this);
        }
    }

    public A getValue(Keyframe<K> keyframe, float f, float f2, float f3) {
        throw new UnsupportedOperationException("This animation does not support split dimensions!");
    }
}
