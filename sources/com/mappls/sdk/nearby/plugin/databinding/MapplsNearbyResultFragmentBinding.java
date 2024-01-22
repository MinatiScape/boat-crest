package com.mappls.sdk.nearby.plugin.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.nearby.plugin.R;
/* loaded from: classes10.dex */
public abstract class MapplsNearbyResultFragmentBinding extends ViewDataBinding {
    @NonNull
    public final FrameLayout mapplsNearbyResultView;

    public MapplsNearbyResultFragmentBinding(Object obj, View view, int i, FrameLayout frameLayout) {
        super(obj, view, i);
        this.mapplsNearbyResultView = frameLayout;
    }

    public static MapplsNearbyResultFragmentBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsNearbyResultFragmentBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsNearbyResultFragmentBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_nearby_result_fragment);
    }

    @NonNull
    public static MapplsNearbyResultFragmentBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsNearbyResultFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsNearbyResultFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsNearbyResultFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_nearby_result_fragment, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsNearbyResultFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsNearbyResultFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_nearby_result_fragment, null, false, obj);
    }
}
