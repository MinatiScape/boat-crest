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
public abstract class FragmentTroubleshootingBinding extends ViewDataBinding {
    @NonNull
    public final RecyclerView rcvTroubleshootItems;
    @NonNull
    public final View toolbar;

    public FragmentTroubleshootingBinding(Object obj, View view, int i, RecyclerView recyclerView, View view2) {
        super(obj, view, i);
        this.rcvTroubleshootItems = recyclerView;
        this.toolbar = view2;
    }

    public static FragmentTroubleshootingBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentTroubleshootingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentTroubleshootingBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentTroubleshootingBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_troubleshooting);
    }

    @NonNull
    @Deprecated
    public static FragmentTroubleshootingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentTroubleshootingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_troubleshooting, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentTroubleshootingBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentTroubleshootingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentTroubleshootingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_troubleshooting, null, false, obj);
    }
}
