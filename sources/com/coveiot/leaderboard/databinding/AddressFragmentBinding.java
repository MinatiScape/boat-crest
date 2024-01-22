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
public abstract class AddressFragmentBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clPlaceAutocomplete;
    @NonNull
    public final Guideline glButtonOnly;
    @NonNull
    public final CurrentLocationDetailsBinding location;
    @NonNull
    public final Button selectAddress;

    public AddressFragmentBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, Guideline guideline, CurrentLocationDetailsBinding currentLocationDetailsBinding, Button button) {
        super(obj, view, i);
        this.clPlaceAutocomplete = constraintLayout;
        this.glButtonOnly = guideline;
        this.location = currentLocationDetailsBinding;
        this.selectAddress = button;
    }

    public static AddressFragmentBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AddressFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AddressFragmentBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AddressFragmentBinding) ViewDataBinding.bind(obj, view, R.layout.address_fragment);
    }

    @NonNull
    @Deprecated
    public static AddressFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AddressFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.address_fragment, viewGroup, z, obj);
    }

    @NonNull
    public static AddressFragmentBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AddressFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AddressFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.address_fragment, null, false, obj);
    }
}
