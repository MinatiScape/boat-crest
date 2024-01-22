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
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.fitnesschallenges.R;
import com.coveiot.android.theme.databinding.InfoDetailsBinding;
/* loaded from: classes2.dex */
public abstract class FragmentFCLeaderboardBinding extends ViewDataBinding {
    @NonNull
    public final FragmentChallengesLeaderboardBinding challengeRankData;
    @NonNull
    public final ConstraintLayout clHeaders;
    @NonNull
    public final InfoDetailsBinding clInfo;
    @NonNull
    public final ConstraintLayout clMainData;
    @NonNull
    public final ConstraintLayout clMyRank;
    @NonNull
    public final ConstraintLayout clRootLayout;
    @NonNull
    public final ConstraintLayout clTopRanks;
    @NonNull
    public final ImageView ivMyProfilePic;
    @NonNull
    public final TextView leaderboardText;
    @NonNull
    public final TextView rank;
    @NonNull
    public final TextView rankerName;
    @NonNull
    public final TextView rankerSteps;
    @NonNull
    public final TextView rankingText;
    @NonNull
    public final RecyclerView rvParticipantList;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvMyCalories;
    @NonNull
    public final TextView tvMyName;
    @NonNull
    public final TextView tvMyRank;
    @NonNull
    public final TextView tvRankAchieveDate;
    @NonNull
    public final TextView tvViewMore;

    public FragmentFCLeaderboardBinding(Object obj, View view, int i, FragmentChallengesLeaderboardBinding fragmentChallengesLeaderboardBinding, ConstraintLayout constraintLayout, InfoDetailsBinding infoDetailsBinding, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ImageView imageView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, RecyclerView recyclerView, View view2, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10) {
        super(obj, view, i);
        this.challengeRankData = fragmentChallengesLeaderboardBinding;
        this.clHeaders = constraintLayout;
        this.clInfo = infoDetailsBinding;
        this.clMainData = constraintLayout2;
        this.clMyRank = constraintLayout3;
        this.clRootLayout = constraintLayout4;
        this.clTopRanks = constraintLayout5;
        this.ivMyProfilePic = imageView;
        this.leaderboardText = textView;
        this.rank = textView2;
        this.rankerName = textView3;
        this.rankerSteps = textView4;
        this.rankingText = textView5;
        this.rvParticipantList = recyclerView;
        this.toolbar = view2;
        this.tvMyCalories = textView6;
        this.tvMyName = textView7;
        this.tvMyRank = textView8;
        this.tvRankAchieveDate = textView9;
        this.tvViewMore = textView10;
    }

    public static FragmentFCLeaderboardBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentFCLeaderboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentFCLeaderboardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentFCLeaderboardBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_f_c_leaderboard);
    }

    @NonNull
    @Deprecated
    public static FragmentFCLeaderboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentFCLeaderboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_f_c_leaderboard, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentFCLeaderboardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentFCLeaderboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentFCLeaderboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_f_c_leaderboard, null, false, obj);
    }
}
