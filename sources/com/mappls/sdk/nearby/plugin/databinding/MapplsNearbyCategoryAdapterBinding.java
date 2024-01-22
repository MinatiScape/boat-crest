package com.mappls.sdk.nearby.plugin.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.nearby.plugin.R;
/* loaded from: classes10.dex */
public abstract class MapplsNearbyCategoryAdapterBinding extends ViewDataBinding {
    @NonNull
    public final CardView mapplsNearbyCategoryBackground;
    @NonNull
    public final ImageView mapplsNearbyCategoryIcon;
    @NonNull
    public final TextView mapplsNearbyKeyword;

    public MapplsNearbyCategoryAdapterBinding(Object obj, View view, int i, CardView cardView, ImageView imageView, TextView textView) {
        super(obj, view, i);
        this.mapplsNearbyCategoryBackground = cardView;
        this.mapplsNearbyCategoryIcon = imageView;
        this.mapplsNearbyKeyword = textView;
    }

    public static MapplsNearbyCategoryAdapterBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsNearbyCategoryAdapterBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsNearbyCategoryAdapterBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_nearby_category_adapter);
    }

    @NonNull
    public static MapplsNearbyCategoryAdapterBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsNearbyCategoryAdapterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsNearbyCategoryAdapterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsNearbyCategoryAdapterBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_nearby_category_adapter, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsNearbyCategoryAdapterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsNearbyCategoryAdapterBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_nearby_category_adapter, null, false, obj);
    }
}
