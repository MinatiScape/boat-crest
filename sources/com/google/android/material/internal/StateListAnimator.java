package com.google.android.material.internal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.StateSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes10.dex */
public final class StateListAnimator {

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList<b> f10319a = new ArrayList<>();
    @Nullable
    public b b = null;
    @Nullable
    public ValueAnimator c = null;
    public final Animator.AnimatorListener d = new a();

    /* loaded from: classes10.dex */
    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            StateListAnimator stateListAnimator = StateListAnimator.this;
            if (stateListAnimator.c == animator) {
                stateListAnimator.c = null;
            }
        }
    }

    /* loaded from: classes10.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public final int[] f10320a;
        public final ValueAnimator b;

        public b(int[] iArr, ValueAnimator valueAnimator) {
            this.f10320a = iArr;
            this.b = valueAnimator;
        }
    }

    public final void a() {
        ValueAnimator valueAnimator = this.c;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.c = null;
        }
    }

    public void addState(int[] iArr, ValueAnimator valueAnimator) {
        b bVar = new b(iArr, valueAnimator);
        valueAnimator.addListener(this.d);
        this.f10319a.add(bVar);
    }

    public final void b(@NonNull b bVar) {
        ValueAnimator valueAnimator = bVar.b;
        this.c = valueAnimator;
        valueAnimator.start();
    }

    public void jumpToCurrentState() {
        ValueAnimator valueAnimator = this.c;
        if (valueAnimator != null) {
            valueAnimator.end();
            this.c = null;
        }
    }

    public void setState(int[] iArr) {
        b bVar;
        int size = this.f10319a.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                bVar = null;
                break;
            }
            bVar = this.f10319a.get(i);
            if (StateSet.stateSetMatches(bVar.f10320a, iArr)) {
                break;
            }
            i++;
        }
        b bVar2 = this.b;
        if (bVar == bVar2) {
            return;
        }
        if (bVar2 != null) {
            a();
        }
        this.b = bVar;
        if (bVar != null) {
            b(bVar);
        }
    }
}
