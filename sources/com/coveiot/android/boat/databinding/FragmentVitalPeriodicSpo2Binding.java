package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.utils.RoundedBarChart;
import com.coveiot.android.theme.databinding.VitalsDetailedLayoutBinding;
/* loaded from: classes3.dex */
public abstract class FragmentVitalPeriodicSpo2Binding extends ViewDataBinding {
    @NonNull
    public final View divider;
    @NonNull
    public final ConstraintLayout graphBg;
    @NonNull
    public final RoundedBarChart spo2Graph;
    @NonNull
    public final TextView tvNoDataFound;
    @NonNull
    public final TextView tvWhatIsSpo2;
    @NonNull
    public final TextView tvdisclaimer;
    @NonNull
    public final TextView tvspo2description;
    @NonNull
    public final VitalsDetailedLayoutBinding vitalsMainData;

    public FragmentVitalPeriodicSpo2Binding(Object obj, View view, int i, View view2, ConstraintLayout constraintLayout, RoundedBarChart roundedBarChart, TextView textView, TextView textView2, TextView textView3, TextView textView4, VitalsDetailedLayoutBinding vitalsDetailedLayoutBinding) {
        super(obj, view, i);
        this.divider = view2;
        this.graphBg = constraintLayout;
        this.spo2Graph = roundedBarChart;
        this.tvNoDataFound = textView;
        this.tvWhatIsSpo2 = textView2;
        this.tvdisclaimer = textView3;
        this.tvspo2description = textView4;
        this.vitalsMainData = vitalsDetailedLayoutBinding;
    }

    public static FragmentVitalPeriodicSpo2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentVitalPeriodicSpo2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVitalPeriodicSpo2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentVitalPeriodicSpo2Binding) ViewDataBinding.bind(obj, view, R.layout.fragment_vital_periodic_spo2);
    }

    @NonNull
    @Deprecated
    public static FragmentVitalPeriodicSpo2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentVitalPeriodicSpo2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_vital_periodic_spo2, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentVitalPeriodicSpo2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentVitalPeriodicSpo2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentVitalPeriodicSpo2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_vital_periodic_spo2, null, false, obj);
    }
}
