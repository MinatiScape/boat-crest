package com.coveiot.android.activitymodes.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.R;
/* loaded from: classes2.dex */
public abstract class FragmentFitnessPlanWeekDayInfoBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clActivities;
    @NonNull
    public final ConstraintLayout clCalories;
    @NonNull
    public final ConstraintLayout clDistance;
    @NonNull
    public final ConstraintLayout clGoalDetails;
    @NonNull
    public final ConstraintLayout clPlanCompletedDetail;
    @NonNull
    public final ConstraintLayout clPlanDetail;
    @NonNull
    public final ConstraintLayout clProgress;
    @NonNull
    public final ConstraintLayout clRest;
    @NonNull
    public final ConstraintLayout clTopPlanDetail;
    @NonNull
    public final ConstraintLayout clTopPlanInfo;
    @NonNull
    public final ConstraintLayout clUnsubscribePlan;
    @NonNull
    public final ImageView ivCalories;
    @NonNull
    public final ImageView ivDistance;
    @NonNull
    public final ImageView ivPlanBgImg;
    @NonNull
    public final ImageView ivTodayGoal;
    @Bindable
    public String mActivitiesCompleted;
    @Bindable
    public String mCompletedCalories;
    @Bindable
    public String mCompletedDistance;
    @Bindable
    public Integer mDayProgress;
    @Bindable
    public Integer mDayProgressMax;
    @Bindable
    public String mDistanceCovered;
    @Bindable
    public String mFutureDayInfo;
    @Bindable
    public String mGoalCategory;
    @Bindable
    public Boolean mIsFutureDay;
    @Bindable
    public Boolean mIsHistoryPlan;
    @Bindable
    public Boolean mIsPlanCompleted;
    @Bindable
    public Boolean mIsPlanStartsTomorrow;
    @Bindable
    public Boolean mIsRestDay;
    @Bindable
    public String mPlanBg;
    @Bindable
    public String mPlanHeader;
    @Bindable
    public String mPlanTitle;
    @Bindable
    public String mProgressText;
    @Bindable
    public String mTotalActivities;
    @Bindable
    public String mTotalActivitiesAndDistance;
    @NonNull
    public final View middleView;
    @NonNull
    public final ProgressBar planProgressBar;
    @NonNull
    public final RecyclerView rvDays;
    @NonNull
    public final RecyclerView rvTodayGoal;
    @NonNull
    public final RecyclerView rvWeeks;
    @NonNull
    public final ProgressBar todayGoalProgress;
    @NonNull
    public final View topView;
    @NonNull
    public final TextView tvCalorieValue;
    @NonNull
    public final TextView tvCalories;
    @NonNull
    public final TextView tvDistance;
    @NonNull
    public final TextView tvDistanceValue;
    @NonNull
    public final TextView tvGoalInfo;
    @NonNull
    public final TextView tvGoalSetup;
    @NonNull
    public final TextView tvPlanActivityCompleted;
    @NonNull
    public final TextView tvPlanDistanceCovered;
    @NonNull
    public final TextView tvPlanHeader;
    @NonNull
    public final TextView tvPlanProgressValue;
    @NonNull
    public final TextView tvPlanStartsTomorrow;
    @NonNull
    public final TextView tvPlanTitle;
    @NonNull
    public final TextView tvSelectedDay;
    @NonNull
    public final TextView tvTodayGoalName;
    @NonNull
    public final TextView tvTodayGoalTotalValue;
    @NonNull
    public final TextView tvTotalActivitiesAndDistance;
    @NonNull
    public final TextView tvUnsubscribePlan;

    public FragmentFitnessPlanWeekDayInfoBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, ConstraintLayout constraintLayout9, ConstraintLayout constraintLayout10, ConstraintLayout constraintLayout11, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, View view2, ProgressBar progressBar, RecyclerView recyclerView, RecyclerView recyclerView2, RecyclerView recyclerView3, ProgressBar progressBar2, View view3, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17) {
        super(obj, view, i);
        this.clActivities = constraintLayout;
        this.clCalories = constraintLayout2;
        this.clDistance = constraintLayout3;
        this.clGoalDetails = constraintLayout4;
        this.clPlanCompletedDetail = constraintLayout5;
        this.clPlanDetail = constraintLayout6;
        this.clProgress = constraintLayout7;
        this.clRest = constraintLayout8;
        this.clTopPlanDetail = constraintLayout9;
        this.clTopPlanInfo = constraintLayout10;
        this.clUnsubscribePlan = constraintLayout11;
        this.ivCalories = imageView;
        this.ivDistance = imageView2;
        this.ivPlanBgImg = imageView3;
        this.ivTodayGoal = imageView4;
        this.middleView = view2;
        this.planProgressBar = progressBar;
        this.rvDays = recyclerView;
        this.rvTodayGoal = recyclerView2;
        this.rvWeeks = recyclerView3;
        this.todayGoalProgress = progressBar2;
        this.topView = view3;
        this.tvCalorieValue = textView;
        this.tvCalories = textView2;
        this.tvDistance = textView3;
        this.tvDistanceValue = textView4;
        this.tvGoalInfo = textView5;
        this.tvGoalSetup = textView6;
        this.tvPlanActivityCompleted = textView7;
        this.tvPlanDistanceCovered = textView8;
        this.tvPlanHeader = textView9;
        this.tvPlanProgressValue = textView10;
        this.tvPlanStartsTomorrow = textView11;
        this.tvPlanTitle = textView12;
        this.tvSelectedDay = textView13;
        this.tvTodayGoalName = textView14;
        this.tvTodayGoalTotalValue = textView15;
        this.tvTotalActivitiesAndDistance = textView16;
        this.tvUnsubscribePlan = textView17;
    }

    public static FragmentFitnessPlanWeekDayInfoBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentFitnessPlanWeekDayInfoBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public String getActivitiesCompleted() {
        return this.mActivitiesCompleted;
    }

    @Nullable
    public String getCompletedCalories() {
        return this.mCompletedCalories;
    }

    @Nullable
    public String getCompletedDistance() {
        return this.mCompletedDistance;
    }

    @Nullable
    public Integer getDayProgress() {
        return this.mDayProgress;
    }

    @Nullable
    public Integer getDayProgressMax() {
        return this.mDayProgressMax;
    }

    @Nullable
    public String getDistanceCovered() {
        return this.mDistanceCovered;
    }

    @Nullable
    public String getFutureDayInfo() {
        return this.mFutureDayInfo;
    }

    @Nullable
    public String getGoalCategory() {
        return this.mGoalCategory;
    }

    @Nullable
    public Boolean getIsFutureDay() {
        return this.mIsFutureDay;
    }

    @Nullable
    public Boolean getIsHistoryPlan() {
        return this.mIsHistoryPlan;
    }

    @Nullable
    public Boolean getIsPlanCompleted() {
        return this.mIsPlanCompleted;
    }

    @Nullable
    public Boolean getIsPlanStartsTomorrow() {
        return this.mIsPlanStartsTomorrow;
    }

    @Nullable
    public Boolean getIsRestDay() {
        return this.mIsRestDay;
    }

    @Nullable
    public String getPlanBg() {
        return this.mPlanBg;
    }

    @Nullable
    public String getPlanHeader() {
        return this.mPlanHeader;
    }

    @Nullable
    public String getPlanTitle() {
        return this.mPlanTitle;
    }

    @Nullable
    public String getProgressText() {
        return this.mProgressText;
    }

    @Nullable
    public String getTotalActivities() {
        return this.mTotalActivities;
    }

    @Nullable
    public String getTotalActivitiesAndDistance() {
        return this.mTotalActivitiesAndDistance;
    }

    public abstract void setActivitiesCompleted(@Nullable String str);

    public abstract void setCompletedCalories(@Nullable String str);

    public abstract void setCompletedDistance(@Nullable String str);

    public abstract void setDayProgress(@Nullable Integer num);

    public abstract void setDayProgressMax(@Nullable Integer num);

    public abstract void setDistanceCovered(@Nullable String str);

    public abstract void setFutureDayInfo(@Nullable String str);

    public abstract void setGoalCategory(@Nullable String str);

    public abstract void setIsFutureDay(@Nullable Boolean bool);

    public abstract void setIsHistoryPlan(@Nullable Boolean bool);

    public abstract void setIsPlanCompleted(@Nullable Boolean bool);

    public abstract void setIsPlanStartsTomorrow(@Nullable Boolean bool);

    public abstract void setIsRestDay(@Nullable Boolean bool);

    public abstract void setPlanBg(@Nullable String str);

    public abstract void setPlanHeader(@Nullable String str);

    public abstract void setPlanTitle(@Nullable String str);

    public abstract void setProgressText(@Nullable String str);

    public abstract void setTotalActivities(@Nullable String str);

    public abstract void setTotalActivitiesAndDistance(@Nullable String str);

    @Deprecated
    public static FragmentFitnessPlanWeekDayInfoBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentFitnessPlanWeekDayInfoBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_fitness_plan_week_day_info);
    }

    @NonNull
    @Deprecated
    public static FragmentFitnessPlanWeekDayInfoBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentFitnessPlanWeekDayInfoBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_fitness_plan_week_day_info, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentFitnessPlanWeekDayInfoBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentFitnessPlanWeekDayInfoBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentFitnessPlanWeekDayInfoBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_fitness_plan_week_day_info, null, false, obj);
    }
}
