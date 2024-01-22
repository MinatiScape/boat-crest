package com.coveiot.android.respiratoryrate.databinding;

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
import com.coveiot.android.respiratoryrate.R;
import com.coveiot.android.theme.databinding.VitalsDetailedLayoutBinding;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.charts.LineChart;
/* loaded from: classes6.dex */
public abstract class FragmentVitalRespiratoryRateBinding extends ViewDataBinding {
    @NonNull
    public final CandleStickChart candleChartRespiratoryRate;
    @NonNull
    public final View divider;
    @NonNull
    public final ConstraintLayout graphBg;
    @NonNull
    public final LineChart lineChartRespiratoryRate;
    @NonNull
    public final NestedScrollView nestedScrollViewNBR;
    @NonNull
    public final TextView tvHRdescription;
    @NonNull
    public final TextView tvNoDataFound;
    @NonNull
    public final TextView tvWhatIsNbr;
    @NonNull
    public final TextView tvdisclaimer;
    @NonNull
    public final VitalsDetailedLayoutBinding vitalsMainData;

    public FragmentVitalRespiratoryRateBinding(Object obj, View view, int i, CandleStickChart candleStickChart, View view2, ConstraintLayout constraintLayout, LineChart lineChart, NestedScrollView nestedScrollView, TextView textView, TextView textView2, TextView textView3, TextView textView4, VitalsDetailedLayoutBinding vitalsDetailedLayoutBinding) {
        super(obj, view, i);
        this.candleChartRespiratoryRate = candleStickChart;
        this.divider = view2;
        this.graphBg = constraintLayout;
        this.lineChartRespiratoryRate = lineChart;
        this.nestedScrollViewNBR = nestedScrollView;
        this.tvHRdescription = textView;
        this.tvNoDataFound = textView2;
        this.tvWhatIsNbr = textView3;
        this.tvdisclaimer = textView4;
        this.vitalsMainData = vitalsDetailedLayoutBinding;
    }

    public static FragmentVitalRespiratoryRateBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentVitalRespiratoryRateBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVitalRespiratoryRateBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentVitalRespiratoryRateBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_vital_respiratory_rate);
    }

    @NonNull
    @Deprecated
    public static FragmentVitalRespiratoryRateBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentVitalRespiratoryRateBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_vital_respiratory_rate, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentVitalRespiratoryRateBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentVitalRespiratoryRateBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentVitalRespiratoryRateBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_vital_respiratory_rate, null, false, obj);
    }
}
