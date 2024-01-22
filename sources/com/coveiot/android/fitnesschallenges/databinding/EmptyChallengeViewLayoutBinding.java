package com.coveiot.android.fitnesschallenges.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.fitnesschallenges.R;
/* loaded from: classes2.dex */
public abstract class EmptyChallengeViewLayoutBinding extends ViewDataBinding {
    @NonNull
    public final TextView btnCreateChallenge;
    @NonNull
    public final ConstraintLayout btnCreateChallengeLayout;
    @NonNull
    public final ImageView emptyViewImage;
    @NonNull
    public final TextView tvNoChallengeVieTitle;
    @NonNull
    public final TextView tvNoChallengeViewInfo;

    public EmptyChallengeViewLayoutBinding(Object obj, View view, int i, TextView textView, ConstraintLayout constraintLayout, ImageView imageView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.btnCreateChallenge = textView;
        this.btnCreateChallengeLayout = constraintLayout;
        this.emptyViewImage = imageView;
        this.tvNoChallengeVieTitle = textView2;
        this.tvNoChallengeViewInfo = textView3;
    }

    public static EmptyChallengeViewLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static EmptyChallengeViewLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static EmptyChallengeViewLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (EmptyChallengeViewLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.empty_challenge_view_layout);
    }

    @NonNull
    @Deprecated
    public static EmptyChallengeViewLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (EmptyChallengeViewLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.empty_challenge_view_layout, viewGroup, z, obj);
    }

    @NonNull
    public static EmptyChallengeViewLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static EmptyChallengeViewLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (EmptyChallengeViewLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.empty_challenge_view_layout, null, false, obj);
    }
}
