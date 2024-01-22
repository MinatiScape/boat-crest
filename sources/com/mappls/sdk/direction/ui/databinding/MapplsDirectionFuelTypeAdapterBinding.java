package com.mappls.sdk.direction.ui.databinding;

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
import com.mappls.sdk.direction.ui.R;
/* loaded from: classes11.dex */
public abstract class MapplsDirectionFuelTypeAdapterBinding extends ViewDataBinding {
    @NonNull
    public final ImageView imageViewFuelType;
    @NonNull
    public final ConstraintLayout parentLayout;
    @NonNull
    public final TextView textViewTypeFuel;

    public MapplsDirectionFuelTypeAdapterBinding(Object obj, View view, int i, ImageView imageView, ConstraintLayout constraintLayout, TextView textView) {
        super(obj, view, i);
        this.imageViewFuelType = imageView;
        this.parentLayout = constraintLayout;
        this.textViewTypeFuel = textView;
    }

    public static MapplsDirectionFuelTypeAdapterBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsDirectionFuelTypeAdapterBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsDirectionFuelTypeAdapterBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_direction_fuel_type_adapter);
    }

    @NonNull
    public static MapplsDirectionFuelTypeAdapterBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsDirectionFuelTypeAdapterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionFuelTypeAdapterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsDirectionFuelTypeAdapterBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_fuel_type_adapter, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionFuelTypeAdapterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsDirectionFuelTypeAdapterBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_fuel_type_adapter, null, false, obj);
    }
}
