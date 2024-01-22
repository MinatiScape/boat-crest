package com.google.android.material.progressindicator;

import android.animation.Animator;
import androidx.annotation.NonNull;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
/* loaded from: classes10.dex */
public abstract class e<T extends Animator> {

    /* renamed from: a  reason: collision with root package name */
    public IndeterminateDrawable f10335a;
    public final float[] b;
    public final int[] c;

    public e(int i) {
        this.b = new float[i * 2];
        this.c = new int[i];
    }

    public abstract void a();

    public float b(int i, int i2, int i3) {
        return (i - i2) / i3;
    }

    public abstract void c();

    public abstract void d(@NonNull Animatable2Compat.AnimationCallback animationCallback);

    public void e(@NonNull IndeterminateDrawable indeterminateDrawable) {
        this.f10335a = indeterminateDrawable;
    }

    public abstract void f();

    public abstract void g();

    public abstract void h();
}
