package com.coveiot.android.fitnesschallenges.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.fitnesschallenges.R;
/* loaded from: classes2.dex */
public abstract class FragmentChallengesLeaderboardBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clRankDetails;
    @NonNull
    public final ConstraintLayout clRankOne;
    @NonNull
    public final ConstraintLayout clRankOneDetails;
    @NonNull
    public final ConstraintLayout clRankThree;
    @NonNull
    public final ConstraintLayout clRankThreeDetails;
    @NonNull
    public final ConstraintLayout clRankTwo;
    @NonNull
    public final ConstraintLayout clRankTwoDetails;
    @NonNull
    public final ImageView ivRankBg;
    @NonNull
    public final ImageView ivRankOneBg;
    @NonNull
    public final ImageView ivRankOneUserProfilePic;
    @NonNull
    public final ImageView ivRankThreeBg;
    @NonNull
    public final ImageView ivRankThreeUserProfilePic;
    @NonNull
    public final ImageView ivRankTwoBg;
    @NonNull
    public final ImageView ivRankTwoUserProfilePic;
    @NonNull
    public final ImageView rankOneValue;
    @NonNull
    public final ImageView rankThreeValue;
    @NonNull
    public final ImageView rankTwoValue;
    @NonNull
    public final TextView tvRankOne;
    @NonNull
    public final TextView tvRankOneUserAchievedDate;
    @NonNull
    public final TextView tvRankOneUserCalories;
    @NonNull
    public final TextView tvRankOneUserName;
    @NonNull
    public final TextView tvRankThree;
    @NonNull
    public final TextView tvRankThreeUserAchievedDate;
    @NonNull
    public final TextView tvRankThreeUserCalories;
    @NonNull
    public final TextView tvRankThreeUserName;
    @NonNull
    public final TextView tvRankTwo;
    @NonNull
    public final TextView tvRankTwoUserAchievedDate;
    @NonNull
    public final TextView tvRankTwoUserCalories;
    @NonNull
    public final TextView tvRankTwoUserName;

    public FragmentChallengesLeaderboardBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, ImageView imageView8, ImageView imageView9, ImageView imageView10, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12) {
        super(obj, view, i);
        this.clRankDetails = constraintLayout;
        this.clRankOne = constraintLayout2;
        this.clRankOneDetails = constraintLayout3;
        this.clRankThree = constraintLayout4;
        this.clRankThreeDetails = constraintLayout5;
        this.clRankTwo = constraintLayout6;
        this.clRankTwoDetails = constraintLayout7;
        this.ivRankBg = imageView;
        this.ivRankOneBg = imageView2;
        this.ivRankOneUserProfilePic = imageView3;
        this.ivRankThreeBg = imageView4;
        this.ivRankThreeUserProfilePic = imageView5;
        this.ivRankTwoBg = imageView6;
        this.ivRankTwoUserProfilePic = imageView7;
        this.rankOneValue = imageView8;
        this.rankThreeValue = imageView9;
        this.rankTwoValue = imageView10;
        this.tvRankOne = textView;
        this.tvRankOneUserAchievedDate = textView2;
        this.tvRankOneUserCalories = textView3;
        this.tvRankOneUserName = textView4;
        this.tvRankThree = textView5;
        this.tvRankThreeUserAchievedDate = textView6;
        this.tvRankThreeUserCalories = textView7;
        this.tvRankThreeUserName = textView8;
        this.tvRankTwo = textView9;
        this.tvRankTwoUserAchievedDate = textView10;
        this.tvRankTwoUserCalories = textView11;
        this.tvRankTwoUserName = textView12;
    }

    public static FragmentChallengesLeaderboardBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentChallengesLeaderboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentChallengesLeaderboardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentChallengesLeaderboardBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_challenges_leaderboard);
    }

    @NonNull
    @Deprecated
    public static FragmentChallengesLeaderboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentChallengesLeaderboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_challenges_leaderboard, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentChallengesLeaderboardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentChallengesLeaderboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentChallengesLeaderboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_challenges_leaderboard, null, false, obj);
    }
}
