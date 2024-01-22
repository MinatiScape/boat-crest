package com.coveiot.android.fitnesschallenges.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.fitnesschallenges.R;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeRes;
/* loaded from: classes2.dex */
public abstract class FitnessChallengeItemBinding extends ViewDataBinding {
    @NonNull
    public final Barrier barrier;
    @NonNull
    public final Barrier barrierParticipants;
    @NonNull
    public final Button btnJoin;
    @NonNull
    public final ProgressBar challengeProgress;
    @NonNull
    public final ConstraintLayout clCompletedChallenge;
    @NonNull
    public final ConstraintLayout clMain;
    @NonNull
    public final ConstraintLayout clRoot;
    @NonNull
    public final ConstraintLayout clTitle;
    @NonNull
    public final ConstraintLayout clTopParticipant;
    @NonNull
    public final CardView cvImage;
    @NonNull
    public final ImageView ivChallengeBg;
    @NonNull
    public final ImageView ivChallengeImg;
    @NonNull
    public final ImageView ivTopParticipant1;
    @NonNull
    public final ImageView ivTopParticipant2;
    @NonNull
    public final ImageView ivTopParticipant3;
    @Bindable
    public BuddiesChallengeRes.Item mChallengeItem;
    @Bindable
    public Boolean mChallengeJoined;
    @Bindable
    public String mDaysLeft;
    @Bindable
    public String mTotalParticipantsCount;
    @NonNull
    public final TextView tvChallengeDesc;
    @NonNull
    public final TextView tvChallengeProgress;
    @NonNull
    public final TextView tvChallengeTitle;
    @NonNull
    public final TextView tvDaysLeft;
    @NonNull
    public final TextView tvNewChallenge;
    @NonNull
    public final TextView tvNoParticipants;
    @NonNull
    public final TextView tvTotalParticipants;

    public FitnessChallengeItemBinding(Object obj, View view, int i, Barrier barrier, Barrier barrier2, Button button, ProgressBar progressBar, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, CardView cardView, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7) {
        super(obj, view, i);
        this.barrier = barrier;
        this.barrierParticipants = barrier2;
        this.btnJoin = button;
        this.challengeProgress = progressBar;
        this.clCompletedChallenge = constraintLayout;
        this.clMain = constraintLayout2;
        this.clRoot = constraintLayout3;
        this.clTitle = constraintLayout4;
        this.clTopParticipant = constraintLayout5;
        this.cvImage = cardView;
        this.ivChallengeBg = imageView;
        this.ivChallengeImg = imageView2;
        this.ivTopParticipant1 = imageView3;
        this.ivTopParticipant2 = imageView4;
        this.ivTopParticipant3 = imageView5;
        this.tvChallengeDesc = textView;
        this.tvChallengeProgress = textView2;
        this.tvChallengeTitle = textView3;
        this.tvDaysLeft = textView4;
        this.tvNewChallenge = textView5;
        this.tvNoParticipants = textView6;
        this.tvTotalParticipants = textView7;
    }

    public static FitnessChallengeItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FitnessChallengeItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public BuddiesChallengeRes.Item getChallengeItem() {
        return this.mChallengeItem;
    }

    @Nullable
    public Boolean getChallengeJoined() {
        return this.mChallengeJoined;
    }

    @Nullable
    public String getDaysLeft() {
        return this.mDaysLeft;
    }

    @Nullable
    public String getTotalParticipantsCount() {
        return this.mTotalParticipantsCount;
    }

    public abstract void setChallengeItem(@Nullable BuddiesChallengeRes.Item item);

    public abstract void setChallengeJoined(@Nullable Boolean bool);

    public abstract void setDaysLeft(@Nullable String str);

    public abstract void setTotalParticipantsCount(@Nullable String str);

    @Deprecated
    public static FitnessChallengeItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FitnessChallengeItemBinding) ViewDataBinding.bind(obj, view, R.layout.fitness_challenge_item);
    }

    @NonNull
    @Deprecated
    public static FitnessChallengeItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FitnessChallengeItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fitness_challenge_item, viewGroup, z, obj);
    }

    @NonNull
    public static FitnessChallengeItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FitnessChallengeItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FitnessChallengeItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fitness_challenge_item, null, false, obj);
    }
}
