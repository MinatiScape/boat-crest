package com.coveiot.android.fitnesschallenges.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.fitnesschallenges.R;
import com.coveiot.coveaccess.fitnesschallenge.model.GetAllParticipantsFitnessChallengeRes;
/* loaded from: classes2.dex */
public abstract class ListItemFitnessChallengeLeaderboardLayoutBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout badgeBaseLayout;
    @NonNull
    public final ConstraintLayout clMainData;
    @NonNull
    public final ConstraintLayout clRank;
    @Bindable
    public GetAllParticipantsFitnessChallengeRes.ParticipantsDetails mParticipantData;
    @NonNull
    public final TextView tvAchievedDate;
    @NonNull
    public final TextView tvUserCalories;
    @NonNull
    public final TextView tvUserName;
    @NonNull
    public final TextView tvUserPreviousRank;
    @NonNull
    public final ImageView tvUserProfilePic;
    @NonNull
    public final TextView tvUserRank;
    @NonNull
    public final View view;

    public ListItemFitnessChallengeLeaderboardLayoutBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, TextView textView, TextView textView2, TextView textView3, TextView textView4, ImageView imageView, TextView textView5, View view2) {
        super(obj, view, i);
        this.badgeBaseLayout = constraintLayout;
        this.clMainData = constraintLayout2;
        this.clRank = constraintLayout3;
        this.tvAchievedDate = textView;
        this.tvUserCalories = textView2;
        this.tvUserName = textView3;
        this.tvUserPreviousRank = textView4;
        this.tvUserProfilePic = imageView;
        this.tvUserRank = textView5;
        this.view = view2;
    }

    public static ListItemFitnessChallengeLeaderboardLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ListItemFitnessChallengeLeaderboardLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public GetAllParticipantsFitnessChallengeRes.ParticipantsDetails getParticipantData() {
        return this.mParticipantData;
    }

    public abstract void setParticipantData(@Nullable GetAllParticipantsFitnessChallengeRes.ParticipantsDetails participantsDetails);

    @Deprecated
    public static ListItemFitnessChallengeLeaderboardLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ListItemFitnessChallengeLeaderboardLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.list_item_fitness_challenge_leaderboard_layout);
    }

    @NonNull
    @Deprecated
    public static ListItemFitnessChallengeLeaderboardLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ListItemFitnessChallengeLeaderboardLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_fitness_challenge_leaderboard_layout, viewGroup, z, obj);
    }

    @NonNull
    public static ListItemFitnessChallengeLeaderboardLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ListItemFitnessChallengeLeaderboardLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ListItemFitnessChallengeLeaderboardLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_fitness_challenge_leaderboard_layout, null, false, obj);
    }
}
