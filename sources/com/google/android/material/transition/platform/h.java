package com.google.android.material.transition.platform;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.transition.platform.VisibilityAnimatorProvider;
import java.util.ArrayList;
import java.util.List;
@RequiresApi(21)
/* loaded from: classes10.dex */
public abstract class h<P extends VisibilityAnimatorProvider> extends Visibility {
    public final P h;
    @Nullable
    public VisibilityAnimatorProvider i;
    public final List<VisibilityAnimatorProvider> j = new ArrayList();

    public h(P p, @Nullable VisibilityAnimatorProvider visibilityAnimatorProvider) {
        this.h = p;
        this.i = visibilityAnimatorProvider;
    }

    public static void a(List<Animator> list, @Nullable VisibilityAnimatorProvider visibilityAnimatorProvider, ViewGroup viewGroup, View view, boolean z) {
        Animator createDisappear;
        if (visibilityAnimatorProvider == null) {
            return;
        }
        if (z) {
            createDisappear = visibilityAnimatorProvider.createAppear(viewGroup, view);
        } else {
            createDisappear = visibilityAnimatorProvider.createDisappear(viewGroup, view);
        }
        if (createDisappear != null) {
            list.add(createDisappear);
        }
    }

    public void addAdditionalAnimatorProvider(@NonNull VisibilityAnimatorProvider visibilityAnimatorProvider) {
        this.j.add(visibilityAnimatorProvider);
    }

    public final Animator b(@NonNull ViewGroup viewGroup, @NonNull View view, boolean z) {
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        a(arrayList, this.h, viewGroup, view, z);
        a(arrayList, this.i, viewGroup, view, z);
        for (VisibilityAnimatorProvider visibilityAnimatorProvider : this.j) {
            a(arrayList, visibilityAnimatorProvider, viewGroup, view, z);
        }
        f(viewGroup.getContext(), z);
        AnimatorSetCompat.playTogether(animatorSet, arrayList);
        return animatorSet;
    }

    @NonNull
    public TimeInterpolator c(boolean z) {
        return AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
    }

    public void clearAdditionalAnimatorProvider() {
        this.j.clear();
    }

    @AttrRes
    public int d(boolean z) {
        return 0;
    }

    @AttrRes
    public int e(boolean z) {
        return 0;
    }

    public final void f(@NonNull Context context, boolean z) {
        j.p(this, context, d(z));
        j.q(this, context, e(z), c(z));
    }

    @NonNull
    public P getPrimaryAnimatorProvider() {
        return this.h;
    }

    @Nullable
    public VisibilityAnimatorProvider getSecondaryAnimatorProvider() {
        return this.i;
    }

    @Override // android.transition.Visibility
    public Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return b(viewGroup, view, true);
    }

    @Override // android.transition.Visibility
    public Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return b(viewGroup, view, false);
    }

    public boolean removeAdditionalAnimatorProvider(@NonNull VisibilityAnimatorProvider visibilityAnimatorProvider) {
        return this.j.remove(visibilityAnimatorProvider);
    }

    public void setSecondaryAnimatorProvider(@Nullable VisibilityAnimatorProvider visibilityAnimatorProvider) {
        this.i = visibilityAnimatorProvider;
    }
}
