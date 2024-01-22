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
public abstract class NoChallengesBannerBinding extends ViewDataBinding {
    @NonNull
    public final TextView noChallengeDescTv;
    @NonNull
    public final TextView noChallengeTitleTv;

    public NoChallengesBannerBinding(Object obj, View view, int i, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.noChallengeDescTv = textView;
        this.noChallengeTitleTv = textView2;
    }

    public static NoChallengesBannerBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static NoChallengesBannerBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NoChallengesBannerBinding bind(@NonNull View view, @Nullable Object obj) {
        return (NoChallengesBannerBinding) ViewDataBinding.bind(obj, view, R.layout.no_challenges_banner);
    }

    @NonNull
    @Deprecated
    public static NoChallengesBannerBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (NoChallengesBannerBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.no_challenges_banner, viewGroup, z, obj);
    }

    @NonNull
    public static NoChallengesBannerBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static NoChallengesBannerBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (NoChallengesBannerBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.no_challenges_banner, null, false, obj);
    }
}
