package com.coveiot.android.fitnesschallenges.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.fitnesschallenges.R;
/* loaded from: classes2.dex */
public abstract class FitnessChallengeParticipantsBinding extends ViewDataBinding {
    @NonNull
    public final Barrier barrier;
    @NonNull
    public final ImageView topParticipant1;
    @NonNull
    public final ImageView topParticipant2;
    @NonNull
    public final ImageView topParticipant3;
    @NonNull
    public final ConstraintLayout topParticipantLayout;
    @NonNull
    public final TextView tvParticipantCount;

    public FitnessChallengeParticipantsBinding(Object obj, View view, int i, Barrier barrier, ImageView imageView, ImageView imageView2, ImageView imageView3, ConstraintLayout constraintLayout, TextView textView) {
        super(obj, view, i);
        this.barrier = barrier;
        this.topParticipant1 = imageView;
        this.topParticipant2 = imageView2;
        this.topParticipant3 = imageView3;
        this.topParticipantLayout = constraintLayout;
        this.tvParticipantCount = textView;
    }

    public static FitnessChallengeParticipantsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FitnessChallengeParticipantsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FitnessChallengeParticipantsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FitnessChallengeParticipantsBinding) ViewDataBinding.bind(obj, view, R.layout.fitness_challenge_participants);
    }

    @NonNull
    @Deprecated
    public static FitnessChallengeParticipantsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FitnessChallengeParticipantsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fitness_challenge_participants, viewGroup, z, obj);
    }

    @NonNull
    public static FitnessChallengeParticipantsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FitnessChallengeParticipantsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FitnessChallengeParticipantsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fitness_challenge_participants, null, false, obj);
    }
}
