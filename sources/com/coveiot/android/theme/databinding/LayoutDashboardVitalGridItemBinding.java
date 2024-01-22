package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class LayoutDashboardVitalGridItemBinding extends ViewDataBinding {
    @NonNull
    public final ItemListFitnessVitalsLayoutBinding fitnessVitalWithData;
    @Bindable
    public String mFitnessType;

    public LayoutDashboardVitalGridItemBinding(Object obj, View view, int i, ItemListFitnessVitalsLayoutBinding itemListFitnessVitalsLayoutBinding) {
        super(obj, view, i);
        this.fitnessVitalWithData = itemListFitnessVitalsLayoutBinding;
    }

    public static LayoutDashboardVitalGridItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutDashboardVitalGridItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public String getFitnessType() {
        return this.mFitnessType;
    }

    public abstract void setFitnessType(@Nullable String str);

    @Deprecated
    public static LayoutDashboardVitalGridItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (LayoutDashboardVitalGridItemBinding) ViewDataBinding.bind(obj, view, R.layout.layout_dashboard_vital_grid_item);
    }

    @NonNull
    @Deprecated
    public static LayoutDashboardVitalGridItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (LayoutDashboardVitalGridItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_dashboard_vital_grid_item, viewGroup, z, obj);
    }

    @NonNull
    public static LayoutDashboardVitalGridItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static LayoutDashboardVitalGridItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (LayoutDashboardVitalGridItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_dashboard_vital_grid_item, null, false, obj);
    }
}
