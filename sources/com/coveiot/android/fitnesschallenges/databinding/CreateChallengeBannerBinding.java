package com.coveiot.android.fitnesschallenges.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.fitnesschallenges.R;
/* loaded from: classes2.dex */
public abstract class CreateChallengeBannerBinding extends ViewDataBinding {
    @NonNull
    public final TextView challengeDescTv;
    @NonNull
    public final TextView challengeTitleTv;
    @NonNull
    public final TextView tvChangeYourWatchFace;

    public CreateChallengeBannerBinding(Object obj, View view, int i, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.challengeDescTv = textView;
        this.challengeTitleTv = textView2;
        this.tvChangeYourWatchFace = textView3;
    }

    public static CreateChallengeBannerBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static CreateChallengeBannerBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CreateChallengeBannerBinding bind(@NonNull View view, @Nullable Object obj) {
        return (CreateChallengeBannerBinding) ViewDataBinding.bind(obj, view, R.layout.create_challenge_banner);
    }

    @NonNull
    @Deprecated
    public static CreateChallengeBannerBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (CreateChallengeBannerBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.create_challenge_banner, viewGroup, z, obj);
    }

    @NonNull
    public static CreateChallengeBannerBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static CreateChallengeBannerBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (CreateChallengeBannerBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.create_challenge_banner, null, false, obj);
    }
}
