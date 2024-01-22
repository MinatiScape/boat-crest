package com.coveiot.leaderboard.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.leaderboard.R;
/* loaded from: classes9.dex */
public abstract class ActivityStepsBadgeScreenBinding extends ViewDataBinding {
    @NonNull
    public final ImageView badgeIv;
    @NonNull
    public final TextView badgeName;
    @NonNull
    public final TextView description;
    @NonNull
    public final Button done;
    @NonNull
    public final TextView earnedBadge;
    @NonNull
    public final Guideline glButtonOnly;
    @NonNull
    public final ImageView icImageShare;
    @NonNull
    public final ConstraintLayout rootLayout;
    @NonNull
    public final TextView unLock;

    public ActivityStepsBadgeScreenBinding(Object obj, View view, int i, ImageView imageView, TextView textView, TextView textView2, Button button, TextView textView3, Guideline guideline, ImageView imageView2, ConstraintLayout constraintLayout, TextView textView4) {
        super(obj, view, i);
        this.badgeIv = imageView;
        this.badgeName = textView;
        this.description = textView2;
        this.done = button;
        this.earnedBadge = textView3;
        this.glButtonOnly = guideline;
        this.icImageShare = imageView2;
        this.rootLayout = constraintLayout;
        this.unLock = textView4;
    }

    public static ActivityStepsBadgeScreenBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityStepsBadgeScreenBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityStepsBadgeScreenBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityStepsBadgeScreenBinding) ViewDataBinding.bind(obj, view, R.layout.activity_steps_badge_screen);
    }

    @NonNull
    @Deprecated
    public static ActivityStepsBadgeScreenBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityStepsBadgeScreenBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_steps_badge_screen, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityStepsBadgeScreenBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityStepsBadgeScreenBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityStepsBadgeScreenBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_steps_badge_screen, null, false, obj);
    }
}
