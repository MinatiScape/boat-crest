package com.coveiot.leaderboard.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.theme.databinding.InfoDetailsBinding;
import com.coveiot.leaderboard.R;
/* loaded from: classes9.dex */
public abstract class ActivityRankFilterBinding extends ViewDataBinding {
    @NonNull
    public final Button btnNext;
    @NonNull
    public final ConstraintLayout clDays;
    @NonNull
    public final ConstraintLayout clLocation;
    @NonNull
    public final InfoDetailsBinding infoDetails;
    @NonNull
    public final RecyclerView rvDayFilter;
    @NonNull
    public final RecyclerView rvLocalityFilter;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvDailyBadges;
    @NonNull
    public final TextView tvLocation;
    @NonNull
    public final View view;

    public ActivityRankFilterBinding(Object obj, View view, int i, Button button, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, InfoDetailsBinding infoDetailsBinding, RecyclerView recyclerView, RecyclerView recyclerView2, View view2, TextView textView, TextView textView2, View view3) {
        super(obj, view, i);
        this.btnNext = button;
        this.clDays = constraintLayout;
        this.clLocation = constraintLayout2;
        this.infoDetails = infoDetailsBinding;
        this.rvDayFilter = recyclerView;
        this.rvLocalityFilter = recyclerView2;
        this.toolbar = view2;
        this.tvDailyBadges = textView;
        this.tvLocation = textView2;
        this.view = view3;
    }

    public static ActivityRankFilterBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityRankFilterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRankFilterBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityRankFilterBinding) ViewDataBinding.bind(obj, view, R.layout.activity_rank_filter);
    }

    @NonNull
    @Deprecated
    public static ActivityRankFilterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityRankFilterBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_rank_filter, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityRankFilterBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityRankFilterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityRankFilterBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_rank_filter, null, false, obj);
    }
}
