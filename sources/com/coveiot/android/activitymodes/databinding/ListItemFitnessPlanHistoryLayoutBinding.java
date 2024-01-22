package com.coveiot.android.activitymodes.databinding;

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
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.models.PlanHistory;
/* loaded from: classes2.dex */
public abstract class ListItemFitnessPlanHistoryLayoutBinding extends ViewDataBinding {
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
    public final CardView cvWeekPlanImage;
    @NonNull
    public final View dashView;
    @NonNull
    public final ImageView ivPlanImg;
    @NonNull
    public final ImageView ivTodayGoal;
    @Bindable
    public Boolean mIsPlanCompleted;
    @Bindable
    public Boolean mIsProgressFull;
    @Bindable
    public PlanHistory mPlanHistoryData;
    @NonNull
    public final ProgressBar todayGoalProgress;
    @NonNull
    public final TextView tvCompletionStatus;
    @NonNull
    public final TextView tvPlanDate;
    @NonNull
    public final TextView tvPlanDesc;
    @NonNull
    public final TextView tvTodayGoal;
    @NonNull
    public final TextView tvTodayGoalName;
    @NonNull
    public final TextView tvTodayGoalTotalValue;
    @NonNull
    public final TextView tvTodayGoalValue;
    @NonNull
    public final TextView tvTotalCalorie;
    @NonNull
    public final TextView tvTotalDistance;
    @NonNull
    public final TextView tvTotalSteps;
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

    public ListItemFitnessPlanHistoryLayoutBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, CardView cardView, View view2, ImageView imageView, ImageView imageView2, ProgressBar progressBar, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, View view3, View view4) {
        super(obj, view, i);
        this.clBottom = constraintLayout;
        this.clBottomTop = constraintLayout2;
        this.clCompleted = constraintLayout3;
        this.clCompletionInfo = constraintLayout4;
        this.clMain = constraintLayout5;
        this.clMainData = constraintLayout6;
        this.clOngoing = constraintLayout7;
        this.clTop = constraintLayout8;
        this.cvWeekPlanImage = cardView;
        this.dashView = view2;
        this.ivPlanImg = imageView;
        this.ivTodayGoal = imageView2;
        this.todayGoalProgress = progressBar;
        this.tvCompletionStatus = textView;
        this.tvPlanDate = textView2;
        this.tvPlanDesc = textView3;
        this.tvTodayGoal = textView4;
        this.tvTodayGoalName = textView5;
        this.tvTodayGoalTotalValue = textView6;
        this.tvTodayGoalValue = textView7;
        this.tvTotalCalorie = textView8;
        this.tvTotalDistance = textView9;
        this.tvTotalSteps = textView10;
        this.tvViewDetails = textView11;
        this.tvWeekPlanName = textView12;
        this.tvWeekPlanStatus = textView13;
        this.view1 = view3;
        this.view2 = view4;
    }

    public static ListItemFitnessPlanHistoryLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ListItemFitnessPlanHistoryLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public Boolean getIsPlanCompleted() {
        return this.mIsPlanCompleted;
    }

    @Nullable
    public Boolean getIsProgressFull() {
        return this.mIsProgressFull;
    }

    @Nullable
    public PlanHistory getPlanHistoryData() {
        return this.mPlanHistoryData;
    }

    public abstract void setIsPlanCompleted(@Nullable Boolean bool);

    public abstract void setIsProgressFull(@Nullable Boolean bool);

    public abstract void setPlanHistoryData(@Nullable PlanHistory planHistory);

    @Deprecated
    public static ListItemFitnessPlanHistoryLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ListItemFitnessPlanHistoryLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.list_item_fitness_plan_history_layout);
    }

    @NonNull
    @Deprecated
    public static ListItemFitnessPlanHistoryLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ListItemFitnessPlanHistoryLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_fitness_plan_history_layout, viewGroup, z, obj);
    }

    @NonNull
    public static ListItemFitnessPlanHistoryLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ListItemFitnessPlanHistoryLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ListItemFitnessPlanHistoryLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_fitness_plan_history_layout, null, false, obj);
    }
}
