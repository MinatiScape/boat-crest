package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class FragmentSensaiFilterOptionsBinding extends ViewDataBinding {
    @NonNull
    public final RecyclerView rcvFilterOptions;

    public FragmentSensaiFilterOptionsBinding(Object obj, View view, int i, RecyclerView recyclerView) {
        super(obj, view, i);
        this.rcvFilterOptions = recyclerView;
    }

    public static FragmentSensaiFilterOptionsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentSensaiFilterOptionsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSensaiFilterOptionsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentSensaiFilterOptionsBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_sensai_filter_options);
    }

    @NonNull
    @Deprecated
    public static FragmentSensaiFilterOptionsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentSensaiFilterOptionsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_sensai_filter_options, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentSensaiFilterOptionsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentSensaiFilterOptionsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentSensaiFilterOptionsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_sensai_filter_options, null, false, obj);
    }
}
