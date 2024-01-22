package com.coveiot.leaderboard.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.leaderboard.R;
/* loaded from: classes9.dex */
public abstract class AllBadgesFragmentBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clDailyBadges;
    @NonNull
    public final ConstraintLayout clSpecialBadges;
    @NonNull
    public final RecyclerView rvDailyBadgeList;
    @NonNull
    public final RecyclerView rvSpecialBadgeList;
    @NonNull
    public final TextView tvDailyBadges;
    @NonNull
    public final TextView tvSpecialBadges;

    public AllBadgesFragmentBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, RecyclerView recyclerView, RecyclerView recyclerView2, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.clDailyBadges = constraintLayout;
        this.clSpecialBadges = constraintLayout2;
        this.rvDailyBadgeList = recyclerView;
        this.rvSpecialBadgeList = recyclerView2;
        this.tvDailyBadges = textView;
        this.tvSpecialBadges = textView2;
    }

    public static AllBadgesFragmentBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AllBadgesFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AllBadgesFragmentBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AllBadgesFragmentBinding) ViewDataBinding.bind(obj, view, R.layout.all_badges_fragment);
    }

    @NonNull
    @Deprecated
    public static AllBadgesFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AllBadgesFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.all_badges_fragment, viewGroup, z, obj);
    }

    @NonNull
    public static AllBadgesFragmentBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AllBadgesFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AllBadgesFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.all_badges_fragment, null, false, obj);
    }
}
