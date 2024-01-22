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
public abstract class FragmentSystemSettingsBinding extends ViewDataBinding {
    @NonNull
    public final RecyclerView rcvSystemSettings1;
    @NonNull
    public final RecyclerView rcvSystemSettings2;

    public FragmentSystemSettingsBinding(Object obj, View view, int i, RecyclerView recyclerView, RecyclerView recyclerView2) {
        super(obj, view, i);
        this.rcvSystemSettings1 = recyclerView;
        this.rcvSystemSettings2 = recyclerView2;
    }

    public static FragmentSystemSettingsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentSystemSettingsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSystemSettingsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentSystemSettingsBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_system_settings);
    }

    @NonNull
    @Deprecated
    public static FragmentSystemSettingsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentSystemSettingsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_system_settings, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentSystemSettingsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentSystemSettingsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentSystemSettingsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_system_settings, null, false, obj);
    }
}
