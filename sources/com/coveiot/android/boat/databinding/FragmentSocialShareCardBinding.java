package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class FragmentSocialShareCardBinding extends ViewDataBinding {
    @NonNull
    public final ImageView calImage;
    @NonNull
    public final ConstraintLayout clSteps;
    @NonNull
    public final CardView cvStepsCard;
    @NonNull
    public final ImageView disImage;
    @NonNull
    public final FrameLayout flSs;
    @NonNull
    public final ConstraintLayout fsshareconstraintlayout;
    @NonNull
    public final ScrollView fssharescrollview;
    @NonNull
    public final ImageView icStepsImage;
    @NonNull
    public final ImageView ivSocialShareAppLogo;
    @NonNull
    public final ImageView ivSocialShareArrow;
    @NonNull
    public final ImageView ivsocialSharePoweredCove;
    @NonNull
    public final ProgressBar pbSteps;
    @NonNull
    public final RecyclerView recyclerViewSs;
    @NonNull
    public final TextView tvCalories;
    @NonNull
    public final TextView tvCaloriesCardUnit;
    @NonNull
    public final TextView tvDistance;
    @NonNull
    public final TextView tvDistanceCard;
    @NonNull
    public final TextView tvDistanceCardUnitDesc;
    @NonNull
    public final TextView tvFitness;
    @NonNull
    public final TextView tvSsDisclaimer;
    @NonNull
    public final TextView tvSsGoal;
    @NonNull
    public final TextView tvSsYesterdayGoal;
    @NonNull
    public final TextView tvSteps;
    @NonNull
    public final TextView tvStepsCalculate;
    @NonNull
    public final TextView tvStepsDay;
    @NonNull
    public final TextView tvStepsLabel;
    @NonNull
    public final TextView tvUsername;
    @NonNull
    public final TextView tvYesterday;
    @NonNull
    public final View view4;

    public FragmentSocialShareCardBinding(Object obj, View view, int i, ImageView imageView, ConstraintLayout constraintLayout, CardView cardView, ImageView imageView2, FrameLayout frameLayout, ConstraintLayout constraintLayout2, ScrollView scrollView, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ProgressBar progressBar, RecyclerView recyclerView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, View view2) {
        super(obj, view, i);
        this.calImage = imageView;
        this.clSteps = constraintLayout;
        this.cvStepsCard = cardView;
        this.disImage = imageView2;
        this.flSs = frameLayout;
        this.fsshareconstraintlayout = constraintLayout2;
        this.fssharescrollview = scrollView;
        this.icStepsImage = imageView3;
        this.ivSocialShareAppLogo = imageView4;
        this.ivSocialShareArrow = imageView5;
        this.ivsocialSharePoweredCove = imageView6;
        this.pbSteps = progressBar;
        this.recyclerViewSs = recyclerView;
        this.tvCalories = textView;
        this.tvCaloriesCardUnit = textView2;
        this.tvDistance = textView3;
        this.tvDistanceCard = textView4;
        this.tvDistanceCardUnitDesc = textView5;
        this.tvFitness = textView6;
        this.tvSsDisclaimer = textView7;
        this.tvSsGoal = textView8;
        this.tvSsYesterdayGoal = textView9;
        this.tvSteps = textView10;
        this.tvStepsCalculate = textView11;
        this.tvStepsDay = textView12;
        this.tvStepsLabel = textView13;
        this.tvUsername = textView14;
        this.tvYesterday = textView15;
        this.view4 = view2;
    }

    public static FragmentSocialShareCardBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentSocialShareCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSocialShareCardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentSocialShareCardBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_social_share_card);
    }

    @NonNull
    @Deprecated
    public static FragmentSocialShareCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentSocialShareCardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_social_share_card, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentSocialShareCardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentSocialShareCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentSocialShareCardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_social_share_card, null, false, obj);
    }
}
