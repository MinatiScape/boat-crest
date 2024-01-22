package com.coveiot.leaderboard.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.theme.CircularArcProgressBar;
import com.coveiot.leaderboard.R;
/* loaded from: classes9.dex */
public abstract class FragmentRankShareBinding extends ViewDataBinding {
    @NonNull
    public final CircularArcProgressBar circularArcProgressBar;
    @NonNull
    public final ConstraintLayout constraint;
    @NonNull
    public final CardView cvRsSteps;
    @NonNull
    public final FrameLayout flRs;
    @NonNull
    public final ImageView ivRankShareAppLogo;
    @NonNull
    public final ImageView ivRankSharePoweredCove;
    @NonNull
    public final ImageView ivRsBestRank;
    @NonNull
    public final ImageView ivRsUserPic;
    @NonNull
    public final CircularArcProgressBar pbStepsRs;
    @NonNull
    public final TextView progressStatusTv;
    @NonNull
    public final RecyclerView recyclerviewRs;
    @NonNull
    public final ConstraintLayout rootLayout;
    @NonNull
    public final View rsView2Steps;
    @NonNull
    public final TextView tvRsBestRank;
    @NonNull
    public final TextView tvRsCount;
    @NonNull
    public final TextView tvRsDate;
    @NonNull
    public final TextView tvRsDesc;
    @NonNull
    public final TextView tvRsLastBadges;
    @NonNull
    public final TextView tvRsRankScore;
    @NonNull
    public final TextView tvRsStepsCount;
    @NonNull
    public final TextView tvRsTodayDate;
    @NonNull
    public final TextView tvRsUserName;
    @NonNull
    public final TextView tvStepsLabel;
    @NonNull
    public final View view;
    @NonNull
    public final ConstraintLayout viewConstraint;

    public FragmentRankShareBinding(Object obj, View view, int i, CircularArcProgressBar circularArcProgressBar, ConstraintLayout constraintLayout, CardView cardView, FrameLayout frameLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, CircularArcProgressBar circularArcProgressBar2, TextView textView, RecyclerView recyclerView, ConstraintLayout constraintLayout2, View view2, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, View view3, ConstraintLayout constraintLayout3) {
        super(obj, view, i);
        this.circularArcProgressBar = circularArcProgressBar;
        this.constraint = constraintLayout;
        this.cvRsSteps = cardView;
        this.flRs = frameLayout;
        this.ivRankShareAppLogo = imageView;
        this.ivRankSharePoweredCove = imageView2;
        this.ivRsBestRank = imageView3;
        this.ivRsUserPic = imageView4;
        this.pbStepsRs = circularArcProgressBar2;
        this.progressStatusTv = textView;
        this.recyclerviewRs = recyclerView;
        this.rootLayout = constraintLayout2;
        this.rsView2Steps = view2;
        this.tvRsBestRank = textView2;
        this.tvRsCount = textView3;
        this.tvRsDate = textView4;
        this.tvRsDesc = textView5;
        this.tvRsLastBadges = textView6;
        this.tvRsRankScore = textView7;
        this.tvRsStepsCount = textView8;
        this.tvRsTodayDate = textView9;
        this.tvRsUserName = textView10;
        this.tvStepsLabel = textView11;
        this.view = view3;
        this.viewConstraint = constraintLayout3;
    }

    public static FragmentRankShareBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentRankShareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentRankShareBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentRankShareBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_rank_share);
    }

    @NonNull
    @Deprecated
    public static FragmentRankShareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentRankShareBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_rank_share, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentRankShareBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentRankShareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentRankShareBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_rank_share, null, false, obj);
    }
}
