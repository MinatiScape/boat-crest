package com.coveiot.android.sportsnotification.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.sportsnotification.R;
/* loaded from: classes7.dex */
public abstract class FragmentFilterOptionsBinding extends ViewDataBinding {
    @NonNull
    public final RecyclerView rcvFilterOptions;

    public FragmentFilterOptionsBinding(Object obj, View view, int i, RecyclerView recyclerView) {
        super(obj, view, i);
        this.rcvFilterOptions = recyclerView;
    }

    public static FragmentFilterOptionsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentFilterOptionsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentFilterOptionsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentFilterOptionsBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_filter_options);
    }

    @NonNull
    @Deprecated
    public static FragmentFilterOptionsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentFilterOptionsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_filter_options, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentFilterOptionsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentFilterOptionsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentFilterOptionsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_filter_options, null, false, obj);
    }
}
