package com.coveiot.android.fitnesschallenges.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.fitnesschallenges.R;
/* loaded from: classes2.dex */
public abstract class ActivityFitnessChallengeCardShareBinding extends ViewDataBinding {
    @NonNull
    public final RelativeLayout containerLayout;

    public ActivityFitnessChallengeCardShareBinding(Object obj, View view, int i, RelativeLayout relativeLayout) {
        super(obj, view, i);
        this.containerLayout = relativeLayout;
    }

    public static ActivityFitnessChallengeCardShareBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityFitnessChallengeCardShareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityFitnessChallengeCardShareBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityFitnessChallengeCardShareBinding) ViewDataBinding.bind(obj, view, R.layout.activity_fitness_challenge_card_share);
    }

    @NonNull
    @Deprecated
    public static ActivityFitnessChallengeCardShareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityFitnessChallengeCardShareBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_fitness_challenge_card_share, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityFitnessChallengeCardShareBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityFitnessChallengeCardShareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityFitnessChallengeCardShareBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_fitness_challenge_card_share, null, false, obj);
    }
}
