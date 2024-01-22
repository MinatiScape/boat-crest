package com.coveiot.android.navigation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.navigation.R;
/* loaded from: classes5.dex */
public abstract class PlacesItemLayoutBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clGetStarted;
    @NonNull
    public final TextView tvTagName;

    public PlacesItemLayoutBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, TextView textView) {
        super(obj, view, i);
        this.clGetStarted = constraintLayout;
        this.tvTagName = textView;
    }

    public static PlacesItemLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static PlacesItemLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PlacesItemLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (PlacesItemLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.places_item_layout);
    }

    @NonNull
    @Deprecated
    public static PlacesItemLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (PlacesItemLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.places_item_layout, viewGroup, z, obj);
    }

    @NonNull
    public static PlacesItemLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static PlacesItemLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (PlacesItemLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.places_item_layout, null, false, obj);
    }
}
