package com.coveiot.android.navigation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.navigation.R;
/* loaded from: classes5.dex */
public abstract class ItemYourPlacesBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clYourPlaces;
    @NonNull
    public final ImageView ivYourPlacesHomeIcon;
    @NonNull
    public final ImageView ivYourPlacesHomeRightArrowIcon;
    @NonNull
    public final TextView tvYourPlacesHome;

    public ItemYourPlacesBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ImageView imageView, ImageView imageView2, TextView textView) {
        super(obj, view, i);
        this.clYourPlaces = constraintLayout;
        this.ivYourPlacesHomeIcon = imageView;
        this.ivYourPlacesHomeRightArrowIcon = imageView2;
        this.tvYourPlacesHome = textView;
    }

    public static ItemYourPlacesBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ItemYourPlacesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemYourPlacesBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ItemYourPlacesBinding) ViewDataBinding.bind(obj, view, R.layout.item_your_places);
    }

    @NonNull
    @Deprecated
    public static ItemYourPlacesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ItemYourPlacesBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_your_places, viewGroup, z, obj);
    }

    @NonNull
    public static ItemYourPlacesBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ItemYourPlacesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ItemYourPlacesBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_your_places, null, false, obj);
    }
}
