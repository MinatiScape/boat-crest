package com.coveiot.android.navigation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.navigation.R;
/* loaded from: classes5.dex */
public abstract class ItemSearchPlacesBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivItemSearchPlacesLocationIcon;
    @NonNull
    public final ImageView ivItemSearchPlacesRightIcon;
    @NonNull
    public final TextView tvSearchPlaceDistance;
    @NonNull
    public final TextView tvSearchPlacesAddress1;
    @NonNull
    public final TextView tvSearchPlacesAddress2;

    public ItemSearchPlacesBinding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.ivItemSearchPlacesLocationIcon = imageView;
        this.ivItemSearchPlacesRightIcon = imageView2;
        this.tvSearchPlaceDistance = textView;
        this.tvSearchPlacesAddress1 = textView2;
        this.tvSearchPlacesAddress2 = textView3;
    }

    public static ItemSearchPlacesBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ItemSearchPlacesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSearchPlacesBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ItemSearchPlacesBinding) ViewDataBinding.bind(obj, view, R.layout.item_search_places);
    }

    @NonNull
    @Deprecated
    public static ItemSearchPlacesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ItemSearchPlacesBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_search_places, viewGroup, z, obj);
    }

    @NonNull
    public static ItemSearchPlacesBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ItemSearchPlacesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ItemSearchPlacesBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_search_places, null, false, obj);
    }
}
