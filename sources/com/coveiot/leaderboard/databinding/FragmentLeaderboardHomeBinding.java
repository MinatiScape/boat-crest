package com.coveiot.leaderboard.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.fitnesschallenges.databinding.EmptyChallengeViewLayoutBinding;
import com.coveiot.leaderboard.R;
/* loaded from: classes9.dex */
public abstract class FragmentLeaderboardHomeBinding extends ViewDataBinding {
    @NonNull
    public final Button btnAddBuddies;
    @NonNull
    public final ConstraintLayout clEmptyBuddies;
    @NonNull
    public final ConstraintLayout clFilter;
    @NonNull
    public final ConstraintLayout clRankDetails;
    @NonNull
    public final ConstraintLayout clRankDetails1;
    @NonNull
    public final ConstraintLayout clRankList;
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
    public final ConstraintLayout clToppersRank;
    @NonNull
    public final EmptyChallengeViewLayoutBinding emptyChallengeView;
    @NonNull
    public final ImageView ivEmptyBuddies;
    @NonNull
    public final ImageView ivRankBg;
    @NonNull
    public final ImageView ivRankOneBg;
    @NonNull
    public final ImageView ivRankThreeBg;
    @NonNull
    public final ImageView ivRankTwoBg;
    @NonNull
    public final ImageView ivRankerProfile;
    @NonNull
    public final ImageView ivShare;
    @NonNull
    public final LinearLayout llList;
    @NonNull
    public final LinearLayout llMyRank;
    @NonNull
    public final LinearLayout llTab;
    @NonNull
    public final TextView myName;
    @NonNull
    public final TextView myRank;
    @NonNull
    public final TextView mySteps;
    @NonNull
    public final TextView rank;
    @NonNull
    public final NestedScrollView rankMain;
    @NonNull
    public final ImageView rankOneIv;
    @NonNull
    public final ImageView rankOneValue;
    @NonNull
    public final ImageView rankThreeIv;
    @NonNull
    public final ImageView rankThreeValue;
    @NonNull
    public final ImageView rankTwoIv;
    @NonNull
    public final ImageView rankTwoValue;
    @NonNull
    public final TextView rankerName;
    @NonNull
    public final TextView rankerSteps;
    @NonNull
    public final RecyclerView rvChallenges;
    @NonNull
    public final RecyclerView rvFilteredList;
    @NonNull
    public final RecyclerView rvRankList;
    @NonNull
    public final TextView tabBuddies;
    @NonNull
    public final TextView tabChallenge;
    @NonNull
    public final TextView tabGlobal;
    @NonNull
    public final TextView tvEmptyRankList;
    @NonNull
    public final TextView tvFilter;
    @NonNull
    public final TextView tvPreviousRank;
    @NonNull
    public final TextView tvRankDetails;
    @NonNull
    public final TextView tvRankOne;
    @NonNull
    public final TextView tvRankOneName;
    @NonNull
    public final TextView tvRankOneSteps;
    @NonNull
    public final TextView tvRankOneUserAchievedDate;
    @NonNull
    public final TextView tvRankThree;
    @NonNull
    public final TextView tvRankThreeName;
    @NonNull
    public final TextView tvRankThreeSteps;
    @NonNull
    public final TextView tvRankThreeUserAchievedDate;
    @NonNull
    public final TextView tvRankTwo;
    @NonNull
    public final TextView tvRankTwoName;
    @NonNull
    public final TextView tvRankTwoSteps;
    @NonNull
    public final TextView tvRankTwoUserAchievedDate;
    @NonNull
    public final View view;

    public FragmentLeaderboardHomeBinding(Object obj, View view, int i, Button button, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, ConstraintLayout constraintLayout9, ConstraintLayout constraintLayout10, ConstraintLayout constraintLayout11, ConstraintLayout constraintLayout12, EmptyChallengeViewLayoutBinding emptyChallengeViewLayoutBinding, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, TextView textView, TextView textView2, TextView textView3, TextView textView4, NestedScrollView nestedScrollView, ImageView imageView8, ImageView imageView9, ImageView imageView10, ImageView imageView11, ImageView imageView12, ImageView imageView13, TextView textView5, TextView textView6, RecyclerView recyclerView, RecyclerView recyclerView2, RecyclerView recyclerView3, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17, TextView textView18, TextView textView19, TextView textView20, TextView textView21, TextView textView22, TextView textView23, TextView textView24, TextView textView25, View view2) {
        super(obj, view, i);
        this.btnAddBuddies = button;
        this.clEmptyBuddies = constraintLayout;
        this.clFilter = constraintLayout2;
        this.clRankDetails = constraintLayout3;
        this.clRankDetails1 = constraintLayout4;
        this.clRankList = constraintLayout5;
        this.clRankOne = constraintLayout6;
        this.clRankOneDetails = constraintLayout7;
        this.clRankThree = constraintLayout8;
        this.clRankThreeDetails = constraintLayout9;
        this.clRankTwo = constraintLayout10;
        this.clRankTwoDetails = constraintLayout11;
        this.clToppersRank = constraintLayout12;
        this.emptyChallengeView = emptyChallengeViewLayoutBinding;
        this.ivEmptyBuddies = imageView;
        this.ivRankBg = imageView2;
        this.ivRankOneBg = imageView3;
        this.ivRankThreeBg = imageView4;
        this.ivRankTwoBg = imageView5;
        this.ivRankerProfile = imageView6;
        this.ivShare = imageView7;
        this.llList = linearLayout;
        this.llMyRank = linearLayout2;
        this.llTab = linearLayout3;
        this.myName = textView;
        this.myRank = textView2;
        this.mySteps = textView3;
        this.rank = textView4;
        this.rankMain = nestedScrollView;
        this.rankOneIv = imageView8;
        this.rankOneValue = imageView9;
        this.rankThreeIv = imageView10;
        this.rankThreeValue = imageView11;
        this.rankTwoIv = imageView12;
        this.rankTwoValue = imageView13;
        this.rankerName = textView5;
        this.rankerSteps = textView6;
        this.rvChallenges = recyclerView;
        this.rvFilteredList = recyclerView2;
        this.rvRankList = recyclerView3;
        this.tabBuddies = textView7;
        this.tabChallenge = textView8;
        this.tabGlobal = textView9;
        this.tvEmptyRankList = textView10;
        this.tvFilter = textView11;
        this.tvPreviousRank = textView12;
        this.tvRankDetails = textView13;
        this.tvRankOne = textView14;
        this.tvRankOneName = textView15;
        this.tvRankOneSteps = textView16;
        this.tvRankOneUserAchievedDate = textView17;
        this.tvRankThree = textView18;
        this.tvRankThreeName = textView19;
        this.tvRankThreeSteps = textView20;
        this.tvRankThreeUserAchievedDate = textView21;
        this.tvRankTwo = textView22;
        this.tvRankTwoName = textView23;
        this.tvRankTwoSteps = textView24;
        this.tvRankTwoUserAchievedDate = textView25;
        this.view = view2;
    }

    public static FragmentLeaderboardHomeBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentLeaderboardHomeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLeaderboardHomeBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentLeaderboardHomeBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_leaderboard_home);
    }

    @NonNull
    @Deprecated
    public static FragmentLeaderboardHomeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentLeaderboardHomeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_leaderboard_home, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentLeaderboardHomeBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentLeaderboardHomeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentLeaderboardHomeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_leaderboard_home, null, false, obj);
    }
}
