package com.mappls.sdk.nearby.plugin.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.nearby.plugin.R;
/* loaded from: classes10.dex */
public abstract class MapplsNearbyResultListAdapterBinding extends ViewDataBinding {
    @NonNull
    public final TextView mapplsNearbyAddressTv;
    @NonNull
    public final TextView mapplsNearbyDistanceTv;
    @NonNull
    public final TextView mapplsNearbyPlaceNameTv;
    @NonNull
    public final View mapplsNearbyResultSeperator;

    public MapplsNearbyResultListAdapterBinding(Object obj, View view, int i, TextView textView, TextView textView2, TextView textView3, View view2) {
        super(obj, view, i);
        this.mapplsNearbyAddressTv = textView;
        this.mapplsNearbyDistanceTv = textView2;
        this.mapplsNearbyPlaceNameTv = textView3;
        this.mapplsNearbyResultSeperator = view2;
    }

    public static MapplsNearbyResultListAdapterBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsNearbyResultListAdapterBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsNearbyResultListAdapterBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_nearby_result_list_adapter);
    }

    @NonNull
    public static MapplsNearbyResultListAdapterBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsNearbyResultListAdapterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsNearbyResultListAdapterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsNearbyResultListAdapterBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_nearby_result_list_adapter, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsNearbyResultListAdapterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsNearbyResultListAdapterBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_nearby_result_list_adapter, null, false, obj);
    }
}
