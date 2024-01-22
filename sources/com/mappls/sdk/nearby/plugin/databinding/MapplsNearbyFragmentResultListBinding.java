package com.mappls.sdk.nearby.plugin.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.nearby.plugin.R;
/* loaded from: classes10.dex */
public abstract class MapplsNearbyFragmentResultListBinding extends ViewDataBinding {
    @NonNull
    public final RecyclerView mapplsNearbyRecyclerView;

    public MapplsNearbyFragmentResultListBinding(Object obj, View view, int i, RecyclerView recyclerView) {
        super(obj, view, i);
        this.mapplsNearbyRecyclerView = recyclerView;
    }

    public static MapplsNearbyFragmentResultListBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsNearbyFragmentResultListBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsNearbyFragmentResultListBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_nearby_fragment_result_list);
    }

    @NonNull
    public static MapplsNearbyFragmentResultListBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsNearbyFragmentResultListBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsNearbyFragmentResultListBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsNearbyFragmentResultListBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_nearby_fragment_result_list, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsNearbyFragmentResultListBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsNearbyFragmentResultListBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_nearby_fragment_result_list, null, false, obj);
    }
}
