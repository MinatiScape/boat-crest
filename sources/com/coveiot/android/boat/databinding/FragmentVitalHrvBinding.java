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
import com.coveiot.android.theme.CircularArcProgressBar;
import com.coveiot.android.theme.databinding.VitalsDetailedLayoutBinding;
import com.github.mikephil.charting.charts.LineChart;
/* loaded from: classes3.dex */
public abstract class FragmentVitalHrvBinding extends ViewDataBinding {
    @NonNull
    public final TextView baseline;
    @NonNull
    public final ConstraintLayout clProgress;
    @NonNull
    public final View divider;
    @NonNull
    public final ConstraintLayout graphBg;
    @NonNull
    public final TextView hrvValue;
    @NonNull
    public final ConstraintLayout legendLayout;
    @NonNull
    public final LineChart lineChartHRV;
    @NonNull
    public final NestedScrollView nestedScrollStress;
    @NonNull
    public final ConstraintLayout readinessLayout;
    @NonNull
    public final CircularArcProgressBar readinessScoreBar;
    @NonNull
    public final TextView textView50;
    @NonNull
    public final TextView tvEnableHrvSettings;
    @NonNull
    public final TextView tvMorningReadinessDynamicTxt;
    @NonNull
    public final TextView tvNoDataFound;
    @NonNull
    public final TextView tvReadinessTotal;
    @NonNull
    public final TextView tvReadinessValue;
    @NonNull
    public final TextView tvWhatIsHrv;
    @NonNull
    public final TextView tvdisclaimer;
    @NonNull
    public final TextView tvhrvdescription;
    @NonNull
    public final VitalsDetailedLayoutBinding vitalsMainData;

    public FragmentVitalHrvBinding(Object obj, View view, int i, TextView textView, ConstraintLayout constraintLayout, View view2, ConstraintLayout constraintLayout2, TextView textView2, ConstraintLayout constraintLayout3, LineChart lineChart, NestedScrollView nestedScrollView, ConstraintLayout constraintLayout4, CircularArcProgressBar circularArcProgressBar, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, VitalsDetailedLayoutBinding vitalsDetailedLayoutBinding) {
        super(obj, view, i);
        this.baseline = textView;
        this.clProgress = constraintLayout;
        this.divider = view2;
        this.graphBg = constraintLayout2;
        this.hrvValue = textView2;
        this.legendLayout = constraintLayout3;
        this.lineChartHRV = lineChart;
        this.nestedScrollStress = nestedScrollView;
        this.readinessLayout = constraintLayout4;
        this.readinessScoreBar = circularArcProgressBar;
        this.textView50 = textView3;
        this.tvEnableHrvSettings = textView4;
        this.tvMorningReadinessDynamicTxt = textView5;
        this.tvNoDataFound = textView6;
        this.tvReadinessTotal = textView7;
        this.tvReadinessValue = textView8;
        this.tvWhatIsHrv = textView9;
        this.tvdisclaimer = textView10;
        this.tvhrvdescription = textView11;
        this.vitalsMainData = vitalsDetailedLayoutBinding;
    }

    public static FragmentVitalHrvBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentVitalHrvBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVitalHrvBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentVitalHrvBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_vital_hrv);
    }

    @NonNull
    @Deprecated
    public static FragmentVitalHrvBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentVitalHrvBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_vital_hrv, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentVitalHrvBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentVitalHrvBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentVitalHrvBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_vital_hrv, null, false, obj);
    }
}
