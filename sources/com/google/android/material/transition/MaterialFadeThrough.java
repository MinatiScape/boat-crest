package com.google.android.material.transition;

import android.animation.Animator;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.transition.TransitionValues;
import com.google.android.material.R;
/* loaded from: classes10.dex */
public final class MaterialFadeThrough extends h<FadeThroughProvider> {
    @AttrRes
    public static final int V = R.attr.motionDurationLong1;
    @AttrRes
    public static final int W = R.attr.motionEasingStandard;

    public MaterialFadeThrough() {
        super(K(), L());
    }

    public static FadeThroughProvider K() {
        return new FadeThroughProvider();
    }

    public static VisibilityAnimatorProvider L() {
        ScaleProvider scaleProvider = new ScaleProvider();
        scaleProvider.setScaleOnDisappear(false);
        scaleProvider.setIncomingStartScale(0.92f);
        return scaleProvider;
    }

    @Override // com.google.android.material.transition.h
    @AttrRes
    public int H(boolean z) {
        return V;
    }

    @Override // com.google.android.material.transition.h
    @AttrRes
    public int I(boolean z) {
        return W;
    }

    @Override // com.google.android.material.transition.h
    public /* bridge */ /* synthetic */ void addAdditionalAnimatorProvider(@NonNull VisibilityAnimatorProvider visibilityAnimatorProvider) {
        super.addAdditionalAnimatorProvider(visibilityAnimatorProvider);
    }

    @Override // com.google.android.material.transition.h
    public /* bridge */ /* synthetic */ void clearAdditionalAnimatorProvider() {
        super.clearAdditionalAnimatorProvider();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.material.transition.FadeThroughProvider, com.google.android.material.transition.VisibilityAnimatorProvider] */
    @Override // com.google.android.material.transition.h
    @NonNull
    public /* bridge */ /* synthetic */ FadeThroughProvider getPrimaryAnimatorProvider() {
        return super.getPrimaryAnimatorProvider();
    }

    @Override // com.google.android.material.transition.h
    @Nullable
    public /* bridge */ /* synthetic */ VisibilityAnimatorProvider getSecondaryAnimatorProvider() {
        return super.getSecondaryAnimatorProvider();
    }

    @Override // com.google.android.material.transition.h, androidx.transition.Visibility
    public /* bridge */ /* synthetic */ Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return super.onAppear(viewGroup, view, transitionValues, transitionValues2);
    }

    @Override // com.google.android.material.transition.h, androidx.transition.Visibility
    public /* bridge */ /* synthetic */ Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return super.onDisappear(viewGroup, view, transitionValues, transitionValues2);
    }

    @Override // com.google.android.material.transition.h
    public /* bridge */ /* synthetic */ boolean removeAdditionalAnimatorProvider(@NonNull VisibilityAnimatorProvider visibilityAnimatorProvider) {
        return super.removeAdditionalAnimatorProvider(visibilityAnimatorProvider);
    }

    @Override // com.google.android.material.transition.h
    public /* bridge */ /* synthetic */ void setSecondaryAnimatorProvider(@Nullable VisibilityAnimatorProvider visibilityAnimatorProvider) {
        super.setSecondaryAnimatorProvider(visibilityAnimatorProvider);
    }
}