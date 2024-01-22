package com.mappls.sdk.nearby.plugin.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mappls.sdk.nearby.plugin.R;
/* loaded from: classes10.dex */
public abstract class MapplsNearbyFragmentMapBinding extends ViewDataBinding {
    @NonNull
    public final FloatingActionButton mapplsNearbyUserLocationButton;

    public MapplsNearbyFragmentMapBinding(Object obj, View view, int i, FloatingActionButton floatingActionButton) {
        super(obj, view, i);
        this.mapplsNearbyUserLocationButton = floatingActionButton;
    }

    public static MapplsNearbyFragmentMapBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsNearbyFragmentMapBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsNearbyFragmentMapBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_nearby_fragment_map);
    }

    @NonNull
    public static MapplsNearbyFragmentMapBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsNearbyFragmentMapBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsNearbyFragmentMapBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsNearbyFragmentMapBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_nearby_fragment_map, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsNearbyFragmentMapBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsNearbyFragmentMapBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_nearby_fragment_map, null, false, obj);
    }
}
