package com.coveiot.android.fitnesschallenges.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.fitnesschallenges.R;
/* loaded from: classes2.dex */
public abstract class ActivityCreateChallengeBinding extends ViewDataBinding {
    @NonNull
    public final FrameLayout fragmentContainer;

    public ActivityCreateChallengeBinding(Object obj, View view, int i, FrameLayout frameLayout) {
        super(obj, view, i);
        this.fragmentContainer = frameLayout;
    }

    public static ActivityCreateChallengeBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityCreateChallengeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityCreateChallengeBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityCreateChallengeBinding) ViewDataBinding.bind(obj, view, R.layout.activity_create_challenge);
    }

    @NonNull
    @Deprecated
    public static ActivityCreateChallengeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityCreateChallengeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_create_challenge, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityCreateChallengeBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityCreateChallengeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityCreateChallengeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_create_challenge, null, false, obj);
    }
}
