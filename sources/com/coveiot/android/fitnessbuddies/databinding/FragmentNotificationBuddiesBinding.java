package com.coveiot.android.fitnessbuddies.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.fitnessbuddies.R;
/* loaded from: classes4.dex */
public abstract class FragmentNotificationBuddiesBinding extends ViewDataBinding {
    @NonNull
    public final TextView addBuddiesText;
    @NonNull
    public final Button btnNext;
    @NonNull
    public final ConstraintLayout clBuddiesMain;
    @NonNull
    public final ConstraintLayout clCalorie;
    @NonNull
    public final ConstraintLayout clGoals;
    @NonNull
    public final ConstraintLayout clGoals1;
    @NonNull
    public final ConstraintLayout clMyDetails;
    @NonNull
    public final ConstraintLayout clProfileDetails;
    @NonNull
    public final ConstraintLayout clSleepGoal;
    @NonNull
    public final ConstraintLayout clStepsGoal;
    @NonNull
    public final View divider1;
    @NonNull
    public final View divider2;
    @NonNull
    public final ImageView emptyImage;
    @NonNull
    public final ConstraintLayout emptyLayout;
    @NonNull
    public final ImageView ivCalorie;
    @NonNull
    public final ImageView ivProfile;
    @NonNull
    public final ImageView ivSleepGoal;
    @NonNull
    public final ImageView ivStepsGoal;
    @NonNull
    public final LinearLayout llAddBuddies;
    @NonNull
    public final ProgressBar progress;
    @NonNull
    public final ProgressBar progressBarSleep;
    @NonNull
    public final ProgressBar progressBarSteps;
    @NonNull
    public final RecyclerView progressList;
    @NonNull
    public final TextView tvAddBuddies;
    @NonNull
    public final TextView tvBadge;
    @NonNull
    public final TextView tvCalorie;
    @NonNull
    public final TextView tvCalorieValue;
    @NonNull
    public final TextView tvGoalCompletion;
    @NonNull
    public final TextView tvMyBuddies;
    @NonNull
    public final TextView tvName;
    @NonNull
    public final TextView tvRank;
    @NonNull
    public final TextView tvSleepGoalTarget;
    @NonNull
    public final TextView tvSleepGoalValue;
    @NonNull
    public final TextView tvStepsGoalTarget;
    @NonNull
    public final TextView tvStepsGoalValue;
    @NonNull
    public final View view1;
    @NonNull
    public final View view2;

    public FragmentNotificationBuddiesBinding(Object obj, View view, int i, TextView textView, Button button, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, View view2, View view3, ImageView imageView, ConstraintLayout constraintLayout9, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, LinearLayout linearLayout, ProgressBar progressBar, ProgressBar progressBar2, ProgressBar progressBar3, RecyclerView recyclerView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, View view4, View view5) {
        super(obj, view, i);
        this.addBuddiesText = textView;
        this.btnNext = button;
        this.clBuddiesMain = constraintLayout;
        this.clCalorie = constraintLayout2;
        this.clGoals = constraintLayout3;
        this.clGoals1 = constraintLayout4;
        this.clMyDetails = constraintLayout5;
        this.clProfileDetails = constraintLayout6;
        this.clSleepGoal = constraintLayout7;
        this.clStepsGoal = constraintLayout8;
        this.divider1 = view2;
        this.divider2 = view3;
        this.emptyImage = imageView;
        this.emptyLayout = constraintLayout9;
        this.ivCalorie = imageView2;
        this.ivProfile = imageView3;
        this.ivSleepGoal = imageView4;
        this.ivStepsGoal = imageView5;
        this.llAddBuddies = linearLayout;
        this.progress = progressBar;
        this.progressBarSleep = progressBar2;
        this.progressBarSteps = progressBar3;
        this.progressList = recyclerView;
        this.tvAddBuddies = textView2;
        this.tvBadge = textView3;
        this.tvCalorie = textView4;
        this.tvCalorieValue = textView5;
        this.tvGoalCompletion = textView6;
        this.tvMyBuddies = textView7;
        this.tvName = textView8;
        this.tvRank = textView9;
        this.tvSleepGoalTarget = textView10;
        this.tvSleepGoalValue = textView11;
        this.tvStepsGoalTarget = textView12;
        this.tvStepsGoalValue = textView13;
        this.view1 = view4;
        this.view2 = view5;
    }

    public static FragmentNotificationBuddiesBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentNotificationBuddiesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentNotificationBuddiesBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentNotificationBuddiesBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_notification_buddies);
    }

    @NonNull
    @Deprecated
    public static FragmentNotificationBuddiesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentNotificationBuddiesBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_notification_buddies, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentNotificationBuddiesBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentNotificationBuddiesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentNotificationBuddiesBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_notification_buddies, null, false, obj);
    }
}
