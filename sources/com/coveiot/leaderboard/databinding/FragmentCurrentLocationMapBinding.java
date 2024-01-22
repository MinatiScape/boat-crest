package com.coveiot.leaderboard.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.leaderboard.R;
/* loaded from: classes9.dex */
public abstract class FragmentCurrentLocationMapBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clMap;
    @NonNull
    public final Guideline glButtonOnly;
    @NonNull
    public final CurrentLocationDetailsBinding location;
    @NonNull
    public final Button selectAddress;

    public FragmentCurrentLocationMapBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, Guideline guideline, CurrentLocationDetailsBinding currentLocationDetailsBinding, Button button) {
        super(obj, view, i);
        this.clMap = constraintLayout;
        this.glButtonOnly = guideline;
        this.location = currentLocationDetailsBinding;
        this.selectAddress = button;
    }

    public static FragmentCurrentLocationMapBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentCurrentLocationMapBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCurrentLocationMapBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentCurrentLocationMapBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_current_location_map);
    }

    @NonNull
    @Deprecated
    public static FragmentCurrentLocationMapBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentCurrentLocationMapBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_current_location_map, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentCurrentLocationMapBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentCurrentLocationMapBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentCurrentLocationMapBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_current_location_map, null, false, obj);
    }
}
