package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ActivityProfileLandingPageBinding extends ViewDataBinding {
    @NonNull
    public final Button btnLogin;
    @NonNull
    public final ConstraintLayout clGetYourHands;
    @NonNull
    public final ConstraintLayout clGuestUser;
    @NonNull
    public final ConstraintLayout clLoggedInUser;
    @NonNull
    public final ConstraintLayout clMyGoals;
    @NonNull
    public final ConstraintLayout cvAchievements;
    @NonNull
    public final ConstraintLayout cvAchievementsFirst;
    @NonNull
    public final ConstraintLayout cvGoalsMore;
    @NonNull
    public final ConstraintLayout cvProfileDetails;
    @NonNull
    public final ConstraintLayout cvViewProfile;
    @NonNull
    public final TextView getYourText;
    @NonNull
    public final TextView guestName;
    @NonNull
    public final ImageView guestProfile;
    @NonNull
    public final ImageView ivAchievementsFirst;
    @NonNull
    public final ImageView ivIcon;
    @NonNull
    public final ImageView ivProfile;
    @NonNull
    public final View profileComplete;
    @NonNull
    public final RecyclerView rvAchievements;
    @NonNull
    public final RecyclerView rvFitnessBuddiess;
    @NonNull
    public final RecyclerView rvGoals;
    @NonNull
    public final RecyclerView rvMore;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvAchievementsDesc;
    @NonNull
    public final TextView tvAchievementsMore;
    @NonNull
    public final TextView tvAchievementsText;
    @NonNull
    public final TextView tvAppVersion;
    @NonNull
    public final TextView tvAppVersion1;
    @NonNull
    public final TextView tvDeleteMyaccount;
    @NonNull
    public final TextView tvFitnessBuddiess;
    @NonNull
    public final TextView tvLogout;
    @NonNull
    public final TextView tvMore;
    @NonNull
    public final TextView tvMyAchievements;
    @NonNull
    public final TextView tvMyAchievementsDesc;
    @NonNull
    public final TextView tvMyFitnessDesc;
    @NonNull
    public final TextView tvMyGoals;
    @NonNull
    public final TextView tvMyGoalsDesc;
    @NonNull
    public final TextView tvName;
    @NonNull
    public final TextView tvUserContact;
    @NonNull
    public final TextView tvViewGoals;
    @NonNull
    public final TextView tvViewMore;
    @NonNull
    public final TextView tvViewProfile;

    public ActivityProfileLandingPageBinding(Object obj, View view, int i, Button button, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, ConstraintLayout constraintLayout9, TextView textView, TextView textView2, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, View view2, RecyclerView recyclerView, RecyclerView recyclerView2, RecyclerView recyclerView3, RecyclerView recyclerView4, View view3, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17, TextView textView18, TextView textView19, TextView textView20, TextView textView21) {
        super(obj, view, i);
        this.btnLogin = button;
        this.clGetYourHands = constraintLayout;
        this.clGuestUser = constraintLayout2;
        this.clLoggedInUser = constraintLayout3;
        this.clMyGoals = constraintLayout4;
        this.cvAchievements = constraintLayout5;
        this.cvAchievementsFirst = constraintLayout6;
        this.cvGoalsMore = constraintLayout7;
        this.cvProfileDetails = constraintLayout8;
        this.cvViewProfile = constraintLayout9;
        this.getYourText = textView;
        this.guestName = textView2;
        this.guestProfile = imageView;
        this.ivAchievementsFirst = imageView2;
        this.ivIcon = imageView3;
        this.ivProfile = imageView4;
        this.profileComplete = view2;
        this.rvAchievements = recyclerView;
        this.rvFitnessBuddiess = recyclerView2;
        this.rvGoals = recyclerView3;
        this.rvMore = recyclerView4;
        this.toolbar = view3;
        this.tvAchievementsDesc = textView3;
        this.tvAchievementsMore = textView4;
        this.tvAchievementsText = textView5;
        this.tvAppVersion = textView6;
        this.tvAppVersion1 = textView7;
        this.tvDeleteMyaccount = textView8;
        this.tvFitnessBuddiess = textView9;
        this.tvLogout = textView10;
        this.tvMore = textView11;
        this.tvMyAchievements = textView12;
        this.tvMyAchievementsDesc = textView13;
        this.tvMyFitnessDesc = textView14;
        this.tvMyGoals = textView15;
        this.tvMyGoalsDesc = textView16;
        this.tvName = textView17;
        this.tvUserContact = textView18;
        this.tvViewGoals = textView19;
        this.tvViewMore = textView20;
        this.tvViewProfile = textView21;
    }

    public static ActivityProfileLandingPageBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityProfileLandingPageBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityProfileLandingPageBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityProfileLandingPageBinding) ViewDataBinding.bind(obj, view, R.layout.activity_profile_landing_page);
    }

    @NonNull
    @Deprecated
    public static ActivityProfileLandingPageBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityProfileLandingPageBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_profile_landing_page, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityProfileLandingPageBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityProfileLandingPageBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityProfileLandingPageBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_profile_landing_page, null, false, obj);
    }
}
