package com.google.android.material.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.transition.TransitionValues;
import androidx.transition.Visibility;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.transition.VisibilityAnimatorProvider;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes10.dex */
public abstract class h<P extends VisibilityAnimatorProvider> extends Visibility {
    public final P S;
    @Nullable
    public VisibilityAnimatorProvider T;
    public final List<VisibilityAnimatorProvider> U = new ArrayList();

    public h(P p, @Nullable VisibilityAnimatorProvider visibilityAnimatorProvider) {
        this.S = p;
        this.T = visibilityAnimatorProvider;
    }

    public static void E(List<Animator> list, @Nullable VisibilityAnimatorProvider visibilityAnimatorProvider, ViewGroup viewGroup, View view, boolean z) {
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

    public final Animator F(@NonNull ViewGroup viewGroup, @NonNull View view, boolean z) {
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        E(arrayList, this.S, viewGroup, view, z);
        E(arrayList, this.T, viewGroup, view, z);
        for (VisibilityAnimatorProvider visibilityAnimatorProvider : this.U) {
            E(arrayList, visibilityAnimatorProvider, viewGroup, view, z);
        }
        J(viewGroup.getContext(), z);
        AnimatorSetCompat.playTogether(animatorSet, arrayList);
        return animatorSet;
    }

    @NonNull
    public TimeInterpolator G(boolean z) {
        return AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
    }

    @AttrRes
    public int H(boolean z) {
        return 0;
    }

    @AttrRes
    public int I(boolean z) {
        return 0;
    }

    public final void J(@NonNull Context context, boolean z) {
        j.o(this, context, H(z));
        j.p(this, context, I(z), G(z));
    }

    public void addAdditionalAnimatorProvider(@NonNull VisibilityAnimatorProvider visibilityAnimatorProvider) {
        this.U.add(visibilityAnimatorProvider);
    }

    public void clearAdditionalAnimatorProvider() {
        this.U.clear();
    }

    @NonNull
    public P getPrimaryAnimatorProvider() {
        return this.S;
    }

    @Nullable
    public VisibilityAnimatorProvider getSecondaryAnimatorProvider() {
        return this.T;
    }

    @Override // androidx.transition.Visibility
    public Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return F(viewGroup, view, true);
    }

    @Override // androidx.transition.Visibility
    public Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return F(viewGroup, view, false);
    }

    public boolean removeAdditionalAnimatorProvider(@NonNull VisibilityAnimatorProvider visibilityAnimatorProvider) {
        return this.U.remove(visibilityAnimatorProvider);
    }

    public void setSecondaryAnimatorProvider(@Nullable VisibilityAnimatorProvider visibilityAnimatorProvider) {
        this.T = visibilityAnimatorProvider;
    }
}
