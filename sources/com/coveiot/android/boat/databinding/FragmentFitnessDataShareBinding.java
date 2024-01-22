package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.theme.databinding.ArcCircularProgressBarBinding;
/* loaded from: classes3.dex */
public abstract class FragmentFitnessDataShareBinding extends ViewDataBinding {
    @NonNull
    public final ArcCircularProgressBarBinding arcProgressBar;
    @NonNull
    public final ConstraintLayout caloriesLayout;
    @NonNull
    public final TextView caloriesUnit;
    @NonNull
    public final TextView caloriesValue;
    @NonNull
    public final ConstraintLayout clGoals;
    @NonNull
    public final ConstraintLayout clProgress;
    @NonNull
    public final TextView disclaimerInfo;
    @NonNull
    public final ImageView distIcon;
    @NonNull
    public final ConstraintLayout distanceLayout;
    @NonNull
    public final TextView distanceUnit;
    @NonNull
    public final TextView distanceValue;
    @NonNull
    public final Guideline fiveGuideline;
    @NonNull
    public final Guideline fourGuideline;
    @NonNull
    public final Guideline glMiddle;
    @NonNull
    public final ImageView ivAppLogo;
    @NonNull
    public final ImageView ivMaximum;
    @NonNull
    public final ImageView ivMinimum;
    @NonNull
    public final ImageView ivPoweredCove;
    @NonNull
    public final ImageView minIcon;
    @NonNull
    public final ConstraintLayout rootLayout;
    @NonNull
    public final RecyclerView rvGoals;
    @NonNull
    public final TextView tvGoalAcheived;
    @NonNull
    public final TextView tvProgressValue;
    @NonNull
    public final TextView userName;
    @NonNull
    public final ImageView userPic;
    @NonNull
    public final View view;
    @NonNull
    public final TextView week;

    public FragmentFitnessDataShareBinding(Object obj, View view, int i, ArcCircularProgressBarBinding arcCircularProgressBarBinding, ConstraintLayout constraintLayout, TextView textView, TextView textView2, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, TextView textView3, ImageView imageView, ConstraintLayout constraintLayout4, TextView textView4, TextView textView5, Guideline guideline, Guideline guideline2, Guideline guideline3, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ConstraintLayout constraintLayout5, RecyclerView recyclerView, TextView textView6, TextView textView7, TextView textView8, ImageView imageView7, View view2, TextView textView9) {
        super(obj, view, i);
        this.arcProgressBar = arcCircularProgressBarBinding;
        this.caloriesLayout = constraintLayout;
        this.caloriesUnit = textView;
        this.caloriesValue = textView2;
        this.clGoals = constraintLayout2;
        this.clProgress = constraintLayout3;
        this.disclaimerInfo = textView3;
        this.distIcon = imageView;
        this.distanceLayout = constraintLayout4;
        this.distanceUnit = textView4;
        this.distanceValue = textView5;
        this.fiveGuideline = guideline;
        this.fourGuideline = guideline2;
        this.glMiddle = guideline3;
        this.ivAppLogo = imageView2;
        this.ivMaximum = imageView3;
        this.ivMinimum = imageView4;
        this.ivPoweredCove = imageView5;
        this.minIcon = imageView6;
        this.rootLayout = constraintLayout5;
        this.rvGoals = recyclerView;
        this.tvGoalAcheived = textView6;
        this.tvProgressValue = textView7;
        this.userName = textView8;
        this.userPic = imageView7;
        this.view = view2;
        this.week = textView9;
    }

    public static FragmentFitnessDataShareBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentFitnessDataShareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentFitnessDataShareBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentFitnessDataShareBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_fitness_data_share);
    }

    @NonNull
    @Deprecated
    public static FragmentFitnessDataShareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentFitnessDataShareBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_fitness_data_share, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentFitnessDataShareBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentFitnessDataShareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentFitnessDataShareBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_fitness_data_share, null, false, obj);
    }
}
