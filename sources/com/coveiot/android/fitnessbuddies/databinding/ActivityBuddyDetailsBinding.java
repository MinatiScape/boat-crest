package com.coveiot.android.fitnessbuddies.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.android.theme.utils.RoundedBarChart;
/* loaded from: classes4.dex */
public abstract class ActivityBuddyDetailsBinding extends ViewDataBinding {
    @NonNull
    public final Button btnLabel;
    @NonNull
    public final ConstraintLayout clInsights;
    @NonNull
    public final ConstraintLayout clLable;
    @NonNull
    public final ConstraintLayout clRankBadges;
    @NonNull
    public final BuddiesListItemNewBinding goalDetails;
    @NonNull
    public final ConstraintLayout graphBg;
    @NonNull
    public final ImageView ivProfile;
    @NonNull
    public final NestedScrollView nestedScrollView;
    @NonNull
    public final RoundedBarChart stepsGraph;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvBadge;
    @NonNull
    public final TextView tvDailyAvgSteps;
    @NonNull
    public final TextView tvInsights;
    @NonNull
    public final TextView tvInsightsDesc;
    @NonNull
    public final TextView tvName;
    @NonNull
    public final TextView tvNoDataFound;
    @NonNull
    public final TextView tvRank;
    @NonNull
    public final TextView tvRemoveBuddy;
    @NonNull
    public final TextView tvWeeklySteps;
    @NonNull
    public final View viewInsights;

    public ActivityBuddyDetailsBinding(Object obj, View view, int i, Button button, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, BuddiesListItemNewBinding buddiesListItemNewBinding, ConstraintLayout constraintLayout4, ImageView imageView, NestedScrollView nestedScrollView, RoundedBarChart roundedBarChart, View view2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, View view3) {
        super(obj, view, i);
        this.btnLabel = button;
        this.clInsights = constraintLayout;
        this.clLable = constraintLayout2;
        this.clRankBadges = constraintLayout3;
        this.goalDetails = buddiesListItemNewBinding;
        this.graphBg = constraintLayout4;
        this.ivProfile = imageView;
        this.nestedScrollView = nestedScrollView;
        this.stepsGraph = roundedBarChart;
        this.toolbar = view2;
        this.tvBadge = textView;
        this.tvDailyAvgSteps = textView2;
        this.tvInsights = textView3;
        this.tvInsightsDesc = textView4;
        this.tvName = textView5;
        this.tvNoDataFound = textView6;
        this.tvRank = textView7;
        this.tvRemoveBuddy = textView8;
        this.tvWeeklySteps = textView9;
        this.viewInsights = view3;
    }

    public static ActivityBuddyDetailsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityBuddyDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBuddyDetailsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityBuddyDetailsBinding) ViewDataBinding.bind(obj, view, R.layout.activity_buddy_details);
    }

    @NonNull
    @Deprecated
    public static ActivityBuddyDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityBuddyDetailsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_buddy_details, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityBuddyDetailsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityBuddyDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityBuddyDetailsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_buddy_details, null, false, obj);
    }
}
