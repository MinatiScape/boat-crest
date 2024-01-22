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
public abstract class MapplsNearbyResultCategoryAdapterBinding extends ViewDataBinding {
    @NonNull
    public final TextView mapplsNearbyCategoryTv;

    public MapplsNearbyResultCategoryAdapterBinding(Object obj, View view, int i, TextView textView) {
        super(obj, view, i);
        this.mapplsNearbyCategoryTv = textView;
    }

    public static MapplsNearbyResultCategoryAdapterBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsNearbyResultCategoryAdapterBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsNearbyResultCategoryAdapterBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_nearby_result_category_adapter);
    }

    @NonNull
    public static MapplsNearbyResultCategoryAdapterBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsNearbyResultCategoryAdapterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsNearbyResultCategoryAdapterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsNearbyResultCategoryAdapterBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_nearby_result_category_adapter, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsNearbyResultCategoryAdapterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsNearbyResultCategoryAdapterBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_nearby_result_category_adapter, null, false, obj);
    }
}
