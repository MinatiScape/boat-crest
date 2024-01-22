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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.fitnesschallenges.R;
/* loaded from: classes2.dex */
public abstract class FragmentShareChallengeBinding extends ViewDataBinding {
    @NonNull
    public final TextView challengeDescTv;
    @NonNull
    public final ConstraintLayout challengeInfoLayout;
    @NonNull
    public final ProgressBar challengePgBar;
    @NonNull
    public final TextView challengeProgressTv;
    @NonNull
    public final TextView challengeTitleTv;
    @NonNull
    public final ConstraintLayout clPic;
    @NonNull
    public final ConstraintLayout clProgress;
    @NonNull
    public final ConstraintLayout cldefaultuserdetails;
    @NonNull
    public final ImageView defaultPic;
    @NonNull
    public final View divider1;
    @NonNull
    public final View divider2;
    @NonNull
    public final ShareInfoLayoutBinding duration;
    @NonNull
    public final ChallengeFirstRankLayoutBinding firstRankHolderPic;
    @NonNull
    public final ShareInfoLayoutBinding goal;
    @NonNull
    public final ImageView ivAppLogo;
    @NonNull
    public final ImageView ivClose;
    @NonNull
    public final ImageView ivPoweredCove;
    @NonNull
    public final ShareInfoLayoutBinding participants;
    @NonNull
    public final ImageView profileUserPic;
    @NonNull
    public final Button shareBtn;
    @NonNull
    public final ConstraintLayout shareFitnessChallengeCard;
    @NonNull
    public final TextView tvUserDesc;
    @NonNull
    public final TextView tvUserName;
    @NonNull
    public final TextView userName;
    @NonNull
    public final ImageView userPic;
    @NonNull
    public final ConstraintLayout userPicLayout;
    @NonNull
    public final TextView week;

    public FragmentShareChallengeBinding(Object obj, View view, int i, TextView textView, ConstraintLayout constraintLayout, ProgressBar progressBar, TextView textView2, TextView textView3, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ImageView imageView, View view2, View view3, ShareInfoLayoutBinding shareInfoLayoutBinding, ChallengeFirstRankLayoutBinding challengeFirstRankLayoutBinding, ShareInfoLayoutBinding shareInfoLayoutBinding2, ImageView imageView2, ImageView imageView3, ImageView imageView4, ShareInfoLayoutBinding shareInfoLayoutBinding3, ImageView imageView5, Button button, ConstraintLayout constraintLayout5, TextView textView4, TextView textView5, TextView textView6, ImageView imageView6, ConstraintLayout constraintLayout6, TextView textView7) {
        super(obj, view, i);
        this.challengeDescTv = textView;
        this.challengeInfoLayout = constraintLayout;
        this.challengePgBar = progressBar;
        this.challengeProgressTv = textView2;
        this.challengeTitleTv = textView3;
        this.clPic = constraintLayout2;
        this.clProgress = constraintLayout3;
        this.cldefaultuserdetails = constraintLayout4;
        this.defaultPic = imageView;
        this.divider1 = view2;
        this.divider2 = view3;
        this.duration = shareInfoLayoutBinding;
        this.firstRankHolderPic = challengeFirstRankLayoutBinding;
        this.goal = shareInfoLayoutBinding2;
        this.ivAppLogo = imageView2;
        this.ivClose = imageView3;
        this.ivPoweredCove = imageView4;
        this.participants = shareInfoLayoutBinding3;
        this.profileUserPic = imageView5;
        this.shareBtn = button;
        this.shareFitnessChallengeCard = constraintLayout5;
        this.tvUserDesc = textView4;
        this.tvUserName = textView5;
        this.userName = textView6;
        this.userPic = imageView6;
        this.userPicLayout = constraintLayout6;
        this.week = textView7;
    }

    public static FragmentShareChallengeBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentShareChallengeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentShareChallengeBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentShareChallengeBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_share_challenge);
    }

    @NonNull
    @Deprecated
    public static FragmentShareChallengeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentShareChallengeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_share_challenge, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentShareChallengeBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentShareChallengeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentShareChallengeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_share_challenge, null, false, obj);
    }
}
