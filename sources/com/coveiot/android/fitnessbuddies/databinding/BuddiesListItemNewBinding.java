package com.coveiot.android.fitnessbuddies.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.fitnessbuddies.R;
/* loaded from: classes4.dex */
public abstract class BuddiesListItemNewBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clCalorie;
    @NonNull
    public final ConstraintLayout clGoals;
    @NonNull
    public final ConstraintLayout clLabel;
    @NonNull
    public final ConstraintLayout clMain;
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
    public final ImageView ivCalorie;
    @NonNull
    public final ImageView ivProfile;
    @NonNull
    public final ImageView ivSleepGoal;
    @NonNull
    public final ImageView ivStepsGoal;
    @NonNull
    public final ProgressBar progressBarSleep;
    @NonNull
    public final ProgressBar progressBarSteps;
    @NonNull
    public final TextView tvCalorie;
    @NonNull
    public final TextView tvCalorieValue;
    @NonNull
    public final TextView tvGoalCompletion;
    @NonNull
    public final TextView tvLabel;
    @NonNull
    public final TextView tvName;
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

    public BuddiesListItemNewBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, View view2, View view3, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ProgressBar progressBar, ProgressBar progressBar2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, View view4) {
        super(obj, view, i);
        this.clCalorie = constraintLayout;
        this.clGoals = constraintLayout2;
        this.clLabel = constraintLayout3;
        this.clMain = constraintLayout4;
        this.clProfileDetails = constraintLayout5;
        this.clSleepGoal = constraintLayout6;
        this.clStepsGoal = constraintLayout7;
        this.divider1 = view2;
        this.divider2 = view3;
        this.ivCalorie = imageView;
        this.ivProfile = imageView2;
        this.ivSleepGoal = imageView3;
        this.ivStepsGoal = imageView4;
        this.progressBarSleep = progressBar;
        this.progressBarSteps = progressBar2;
        this.tvCalorie = textView;
        this.tvCalorieValue = textView2;
        this.tvGoalCompletion = textView3;
        this.tvLabel = textView4;
        this.tvName = textView5;
        this.tvSleepGoalTarget = textView6;
        this.tvSleepGoalValue = textView7;
        this.tvStepsGoalTarget = textView8;
        this.tvStepsGoalValue = textView9;
        this.view1 = view4;
    }

    public static BuddiesListItemNewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static BuddiesListItemNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BuddiesListItemNewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (BuddiesListItemNewBinding) ViewDataBinding.bind(obj, view, R.layout.buddies_list_item_new);
    }

    @NonNull
    @Deprecated
    public static BuddiesListItemNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (BuddiesListItemNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.buddies_list_item_new, viewGroup, z, obj);
    }

    @NonNull
    public static BuddiesListItemNewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static BuddiesListItemNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (BuddiesListItemNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.buddies_list_item_new, null, false, obj);
    }
}
