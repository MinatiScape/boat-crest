package com.mappls.sdk.maps.location;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import androidx.annotation.NonNull;
import androidx.annotation.Size;
/* loaded from: classes11.dex */
public abstract class o<K> extends ValueAnimator implements ValueAnimator.AnimatorUpdateListener {
    public final b<K> h;
    public final K i;
    public K j;
    public final double k;
    public long l;
    public boolean m;

    /* loaded from: classes11.dex */
    public interface b<K> {
        void a(K k);
    }

    /* loaded from: classes11.dex */
    public class c extends AnimatorListenerAdapter {
        public c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            o.this.c();
        }
    }

    public o(@NonNull @Size(min = 2) K[] kArr, @NonNull b<K> bVar, int i) {
        this.k = 1.0E9d / i;
        setObjectValues(kArr);
        setEvaluator(d());
        this.h = bVar;
        this.i = kArr[kArr.length - 1];
        addUpdateListener(this);
        addListener(new c());
    }

    public K b() {
        return this.i;
    }

    public final void c() {
        if (this.m) {
            return;
        }
        this.h.a(this.j);
    }

    public abstract TypeEvaluator d();

    public void makeInvalid() {
        this.m = true;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.j = (K) valueAnimator.getAnimatedValue();
        long nanoTime = System.nanoTime();
        if (nanoTime - this.l < this.k) {
            return;
        }
        c();
        this.l = nanoTime;
    }

    public o(b<K> bVar, K k, K k2, double d, long j) {
        this.h = bVar;
        this.i = k;
        this.j = k2;
        this.k = d;
        this.l = j;
    }
}
