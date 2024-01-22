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
public abstract class ChallengeFirstRankLayoutBinding extends ViewDataBinding {
    @NonNull
    public final ImageView orangeBg;
    @NonNull
    public final ImageView rankOneValue;
    @NonNull
    public final TextView tvRankOne;
    @NonNull
    public final ConstraintLayout userImgLayout;
    @NonNull
    public final ImageView userPic;

    public ChallengeFirstRankLayoutBinding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, TextView textView, ConstraintLayout constraintLayout, ImageView imageView3) {
        super(obj, view, i);
        this.orangeBg = imageView;
        this.rankOneValue = imageView2;
        this.tvRankOne = textView;
        this.userImgLayout = constraintLayout;
        this.userPic = imageView3;
    }

    public static ChallengeFirstRankLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ChallengeFirstRankLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ChallengeFirstRankLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ChallengeFirstRankLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.challenge_first_rank_layout);
    }

    @NonNull
    @Deprecated
    public static ChallengeFirstRankLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ChallengeFirstRankLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.challenge_first_rank_layout, viewGroup, z, obj);
    }

    @NonNull
    public static ChallengeFirstRankLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ChallengeFirstRankLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ChallengeFirstRankLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.challenge_first_rank_layout, null, false, obj);
    }
}
