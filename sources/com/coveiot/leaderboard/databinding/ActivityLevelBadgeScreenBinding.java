package com.coveiot.leaderboard.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.leaderboard.R;
/* loaded from: classes9.dex */
public abstract class ActivityLevelBadgeScreenBinding extends ViewDataBinding {
    @NonNull
    public final ImageView badgeIv;
    @NonNull
    public final LinearLayout badgeLayout;
    @NonNull
    public final TextView badgeName;
    @NonNull
    public final Button done;
    @NonNull
    public final Guideline glButtonOnly;
    @NonNull
    public final ImageView icImageShare;
    @NonNull
    public final RecyclerView levelRecyclerview;

    public ActivityLevelBadgeScreenBinding(Object obj, View view, int i, ImageView imageView, LinearLayout linearLayout, TextView textView, Button button, Guideline guideline, ImageView imageView2, RecyclerView recyclerView) {
        super(obj, view, i);
        this.badgeIv = imageView;
        this.badgeLayout = linearLayout;
        this.badgeName = textView;
        this.done = button;
        this.glButtonOnly = guideline;
        this.icImageShare = imageView2;
        this.levelRecyclerview = recyclerView;
    }

    public static ActivityLevelBadgeScreenBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityLevelBadgeScreenBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLevelBadgeScreenBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityLevelBadgeScreenBinding) ViewDataBinding.bind(obj, view, R.layout.activity_level_badge_screen);
    }

    @NonNull
    @Deprecated
    public static ActivityLevelBadgeScreenBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityLevelBadgeScreenBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_level_badge_screen, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityLevelBadgeScreenBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityLevelBadgeScreenBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityLevelBadgeScreenBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_level_badge_screen, null, false, obj);
    }
}
