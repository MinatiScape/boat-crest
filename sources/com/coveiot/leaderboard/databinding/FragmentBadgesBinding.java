package com.coveiot.leaderboard.databinding;

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
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.leaderboard.R;
/* loaded from: classes9.dex */
public abstract class FragmentBadgesBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clBadge;
    @NonNull
    public final ConstraintLayout clBadges;
    @NonNull
    public final ConstraintLayout clBadgesCard;
    @NonNull
    public final ConstraintLayout clDailyBadges;
    @NonNull
    public final ConstraintLayout clNoBadge;
    @NonNull
    public final ConstraintLayout clSpecialBadges;
    @NonNull
    public final ImageView ivBadges;
    @NonNull
    public final ImageView ivBadgesBanner;
    @NonNull
    public final ImageView ivNoBadge;
    @NonNull
    public final RecyclerView rvDailyBadgeList;
    @NonNull
    public final RecyclerView rvSpecialBadgeList;
    @NonNull
    public final TextView tvDailyBadges;
    @NonNull
    public final TextView tvSpecialBadges;
    @NonNull
    public final TextView tvSpeedRunner;
    @NonNull
    public final TextView tvSpeedRunnerDesc;

    public FragmentBadgesBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ImageView imageView, ImageView imageView2, ImageView imageView3, RecyclerView recyclerView, RecyclerView recyclerView2, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.clBadge = constraintLayout;
        this.clBadges = constraintLayout2;
        this.clBadgesCard = constraintLayout3;
        this.clDailyBadges = constraintLayout4;
        this.clNoBadge = constraintLayout5;
        this.clSpecialBadges = constraintLayout6;
        this.ivBadges = imageView;
        this.ivBadgesBanner = imageView2;
        this.ivNoBadge = imageView3;
        this.rvDailyBadgeList = recyclerView;
        this.rvSpecialBadgeList = recyclerView2;
        this.tvDailyBadges = textView;
        this.tvSpecialBadges = textView2;
        this.tvSpeedRunner = textView3;
        this.tvSpeedRunnerDesc = textView4;
    }

    public static FragmentBadgesBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentBadgesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentBadgesBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentBadgesBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_badges);
    }

    @NonNull
    @Deprecated
    public static FragmentBadgesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentBadgesBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_badges, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentBadgesBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentBadgesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentBadgesBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_badges, null, false, obj);
    }
}
