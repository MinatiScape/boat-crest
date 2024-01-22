package com.coveiot.android.fitnesschallenges.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.fitnesschallenges.R;
import com.coveiot.android.theme.databinding.InfoDetailsBinding;
/* loaded from: classes2.dex */
public abstract class ActivityFitnessDetailsBinding extends ViewDataBinding {
    @NonNull
    public final Button btnAddParticipants;
    @NonNull
    public final Button btnJoinChallenge;
    @NonNull
    public final Button btnLeaveChallenge;
    @NonNull
    public final ConstraintLayout clBottom;
    @NonNull
    public final ConstraintLayout clBottomButtons;
    @NonNull
    public final ConstraintLayout clCompletionProgress;
    @NonNull
    public final ConstraintLayout clProgressBar;
    @NonNull
    public final ConstraintLayout clTitle;
    @NonNull
    public final CardView cvImage;
    @NonNull
    public final FitnessChallengeParticipantsBinding fitnessChallengeParticipants;
    @NonNull
    public final InfoDetailsBinding infoDetails;
    @NonNull
    public final ImageView ivBuddyImage;
    @NonNull
    public final ImageView ivEdit;
    @NonNull
    public final ImageView ivGlobalImage;
    @NonNull
    public final ImageView ivShare;
    @NonNull
    public final FrameLayout leaderboardContainer;
    @NonNull
    public final FitnessDetailsEditDialogBinding myChallengeEdit;
    @NonNull
    public final NestedScrollView nestedScrollView;
    @NonNull
    public final ProgressBar pbCompletion;
    @NonNull
    public final View shareChallengeError;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvCompletionProgress;
    @NonNull
    public final TextView tvCreator;
    @NonNull
    public final TextView tvDaysLeft;
    @NonNull
    public final TextView tvDesc;
    @NonNull
    public final TextView tvDuration;
    @NonNull
    public final TextView tvFitCrew;
    @NonNull
    public final TextView tvGoalCompletion;
    @NonNull
    public final TextView tvImageTitle;
    @NonNull
    public final TextView tvLeaderboard;
    @NonNull
    public final TextView tvNoParticipant;
    @NonNull
    public final TextView tvProgressValue;
    @NonNull
    public final TextView tvRank;
    @NonNull
    public final TextView tvTargetGoal;
    @NonNull
    public final TextView tvTitle;
    @NonNull
    public final TextView tvViewAllParticipants;

    public ActivityFitnessDetailsBinding(Object obj, View view, int i, Button button, Button button2, Button button3, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, CardView cardView, FitnessChallengeParticipantsBinding fitnessChallengeParticipantsBinding, InfoDetailsBinding infoDetailsBinding, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, FrameLayout frameLayout, FitnessDetailsEditDialogBinding fitnessDetailsEditDialogBinding, NestedScrollView nestedScrollView, ProgressBar progressBar, View view2, View view3, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15) {
        super(obj, view, i);
        this.btnAddParticipants = button;
        this.btnJoinChallenge = button2;
        this.btnLeaveChallenge = button3;
        this.clBottom = constraintLayout;
        this.clBottomButtons = constraintLayout2;
        this.clCompletionProgress = constraintLayout3;
        this.clProgressBar = constraintLayout4;
        this.clTitle = constraintLayout5;
        this.cvImage = cardView;
        this.fitnessChallengeParticipants = fitnessChallengeParticipantsBinding;
        this.infoDetails = infoDetailsBinding;
        this.ivBuddyImage = imageView;
        this.ivEdit = imageView2;
        this.ivGlobalImage = imageView3;
        this.ivShare = imageView4;
        this.leaderboardContainer = frameLayout;
        this.myChallengeEdit = fitnessDetailsEditDialogBinding;
        this.nestedScrollView = nestedScrollView;
        this.pbCompletion = progressBar;
        this.shareChallengeError = view2;
        this.toolbar = view3;
        this.tvCompletionProgress = textView;
        this.tvCreator = textView2;
        this.tvDaysLeft = textView3;
        this.tvDesc = textView4;
        this.tvDuration = textView5;
        this.tvFitCrew = textView6;
        this.tvGoalCompletion = textView7;
        this.tvImageTitle = textView8;
        this.tvLeaderboard = textView9;
        this.tvNoParticipant = textView10;
        this.tvProgressValue = textView11;
        this.tvRank = textView12;
        this.tvTargetGoal = textView13;
        this.tvTitle = textView14;
        this.tvViewAllParticipants = textView15;
    }

    public static ActivityFitnessDetailsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityFitnessDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityFitnessDetailsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityFitnessDetailsBinding) ViewDataBinding.bind(obj, view, R.layout.activity_fitness_details);
    }

    @NonNull
    @Deprecated
    public static ActivityFitnessDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityFitnessDetailsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_fitness_details, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityFitnessDetailsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityFitnessDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityFitnessDetailsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_fitness_details, null, false, obj);
    }
}
