package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class ListItemWeekPlanLayoutBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clBottom;
    @NonNull
    public final ConstraintLayout clBottomTop;
    @NonNull
    public final ConstraintLayout clCompleted;
    @NonNull
    public final ConstraintLayout clCompletionInfo;
    @NonNull
    public final ConstraintLayout clMain;
    @NonNull
    public final ConstraintLayout clMainData;
    @NonNull
    public final ConstraintLayout clOngoing;
    @NonNull
    public final ConstraintLayout clTop;
    @NonNull
    public final ConstraintLayout clUpcoming;
    @NonNull
    public final CardView cvWeekPlanImage;
    @NonNull
    public final View dashView;
    @NonNull
    public final ImageView ivBlog;
    @NonNull
    public final ImageView ivTodayGoal;
    @NonNull
    public final TextView textView;
    @NonNull
    public final ProgressBar todayGoalProgress;
    @NonNull
    public final TextView tvCalorie;
    @NonNull
    public final TextView tvCompletionStatus;
    @NonNull
    public final TextView tvDistance;
    @NonNull
    public final TextView tvPlanStartsTomorrow;
    @NonNull
    public final TextView tvSteps;
    @NonNull
    public final TextView tvTodayGoal;
    @NonNull
    public final TextView tvTodayGoalName;
    @NonNull
    public final TextView tvTodayGoalTotalValue;
    @NonNull
    public final TextView tvTodayGoalValue;
    @NonNull
    public final TextView tvViewDetails;
    @NonNull
    public final TextView tvWeekPlanName;
    @NonNull
    public final TextView tvWeekPlanStatus;
    @NonNull
    public final View view1;
    @NonNull
    public final View view2;

    public ListItemWeekPlanLayoutBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, ConstraintLayout constraintLayout9, CardView cardView, View view2, ImageView imageView, ImageView imageView2, TextView textView, ProgressBar progressBar, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, View view3, View view4) {
        super(obj, view, i);
        this.clBottom = constraintLayout;
        this.clBottomTop = constraintLayout2;
        this.clCompleted = constraintLayout3;
        this.clCompletionInfo = constraintLayout4;
        this.clMain = constraintLayout5;
        this.clMainData = constraintLayout6;
        this.clOngoing = constraintLayout7;
        this.clTop = constraintLayout8;
        this.clUpcoming = constraintLayout9;
        this.cvWeekPlanImage = cardView;
        this.dashView = view2;
        this.ivBlog = imageView;
        this.ivTodayGoal = imageView2;
        this.textView = textView;
        this.todayGoalProgress = progressBar;
        this.tvCalorie = textView2;
        this.tvCompletionStatus = textView3;
        this.tvDistance = textView4;
        this.tvPlanStartsTomorrow = textView5;
        this.tvSteps = textView6;
        this.tvTodayGoal = textView7;
        this.tvTodayGoalName = textView8;
        this.tvTodayGoalTotalValue = textView9;
        this.tvTodayGoalValue = textView10;
        this.tvViewDetails = textView11;
        this.tvWeekPlanName = textView12;
        this.tvWeekPlanStatus = textView13;
        this.view1 = view3;
        this.view2 = view4;
    }

    public static ListItemWeekPlanLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ListItemWeekPlanLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ListItemWeekPlanLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ListItemWeekPlanLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.list_item_week_plan_layout);
    }

    @NonNull
    @Deprecated
    public static ListItemWeekPlanLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ListItemWeekPlanLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_week_plan_layout, viewGroup, z, obj);
    }

    @NonNull
    public static ListItemWeekPlanLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ListItemWeekPlanLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ListItemWeekPlanLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_week_plan_layout, null, false, obj);
    }
}
