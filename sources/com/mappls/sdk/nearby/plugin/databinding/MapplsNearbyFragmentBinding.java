package com.mappls.sdk.nearby.plugin.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.nearby.plugin.R;
/* loaded from: classes10.dex */
public abstract class MapplsNearbyFragmentBinding extends ViewDataBinding {
    @NonNull
    public final FrameLayout mapplsNearbyView;
    @NonNull
    public final ConstraintLayout nearbyRoot;

    public MapplsNearbyFragmentBinding(Object obj, View view, int i, FrameLayout frameLayout, ConstraintLayout constraintLayout) {
        super(obj, view, i);
        this.mapplsNearbyView = frameLayout;
        this.nearbyRoot = constraintLayout;
    }

    public static MapplsNearbyFragmentBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsNearbyFragmentBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsNearbyFragmentBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_nearby_fragment);
    }

    @NonNull
    public static MapplsNearbyFragmentBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsNearbyFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsNearbyFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsNearbyFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_nearby_fragment, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsNearbyFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsNearbyFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_nearby_fragment, null, false, obj);
    }
}
