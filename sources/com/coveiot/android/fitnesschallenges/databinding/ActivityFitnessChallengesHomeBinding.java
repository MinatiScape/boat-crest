package com.coveiot.android.fitnesschallenges.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.fitnesschallenges.R;
import com.google.android.material.tabs.TabLayout;
/* loaded from: classes2.dex */
public abstract class ActivityFitnessChallengesHomeBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout challengeTabLayout;
    @NonNull
    public final CreateChallengeBannerBinding creteChallengeLayout;
    @NonNull
    public final TabLayout tabLayout;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvCreateChallengeTitle;
    @NonNull
    public final ViewPager viewPager;

    public ActivityFitnessChallengesHomeBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, CreateChallengeBannerBinding createChallengeBannerBinding, TabLayout tabLayout, View view2, TextView textView, ViewPager viewPager) {
        super(obj, view, i);
        this.challengeTabLayout = constraintLayout;
        this.creteChallengeLayout = createChallengeBannerBinding;
        this.tabLayout = tabLayout;
        this.toolbar = view2;
        this.tvCreateChallengeTitle = textView;
        this.viewPager = viewPager;
    }

    public static ActivityFitnessChallengesHomeBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityFitnessChallengesHomeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityFitnessChallengesHomeBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityFitnessChallengesHomeBinding) ViewDataBinding.bind(obj, view, R.layout.activity_fitness_challenges_home);
    }

    @NonNull
    @Deprecated
    public static ActivityFitnessChallengesHomeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityFitnessChallengesHomeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_fitness_challenges_home, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityFitnessChallengesHomeBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityFitnessChallengesHomeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityFitnessChallengesHomeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_fitness_challenges_home, null, false, obj);
    }
}
