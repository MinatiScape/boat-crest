package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
import com.coveiot.android.theme.databinding.VitalsDetailedLayoutBinding;
import com.github.mikephil.charting.charts.LineChart;
/* loaded from: classes3.dex */
public abstract class FragmentVitalTemperatureBinding extends ViewDataBinding {
    @NonNull
    public final View divider;
    @NonNull
    public final ConstraintLayout graphBg;
    @NonNull
    public final LineChart lineChartTemperature;
    @NonNull
    public final NestedScrollView nestedScrollViewTemperature;
    @NonNull
    public final TextView tvNoDataFound;
    @NonNull
    public final TextView tvTempdescription;
    @NonNull
    public final TextView tvTemperaturePlotInfo;
    @NonNull
    public final TextView tvWhatIsTemp;
    @NonNull
    public final TextView tvdisclaimer;
    @NonNull
    public final VitalsDetailedLayoutBinding vitalsMainData;

    public FragmentVitalTemperatureBinding(Object obj, View view, int i, View view2, ConstraintLayout constraintLayout, LineChart lineChart, NestedScrollView nestedScrollView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, VitalsDetailedLayoutBinding vitalsDetailedLayoutBinding) {
        super(obj, view, i);
        this.divider = view2;
        this.graphBg = constraintLayout;
        this.lineChartTemperature = lineChart;
        this.nestedScrollViewTemperature = nestedScrollView;
        this.tvNoDataFound = textView;
        this.tvTempdescription = textView2;
        this.tvTemperaturePlotInfo = textView3;
        this.tvWhatIsTemp = textView4;
        this.tvdisclaimer = textView5;
        this.vitalsMainData = vitalsDetailedLayoutBinding;
    }

    public static FragmentVitalTemperatureBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentVitalTemperatureBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVitalTemperatureBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentVitalTemperatureBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_vital_temperature);
    }

    @NonNull
    @Deprecated
    public static FragmentVitalTemperatureBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentVitalTemperatureBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_vital_temperature, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentVitalTemperatureBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentVitalTemperatureBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentVitalTemperatureBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_vital_temperature, null, false, obj);
    }
}
