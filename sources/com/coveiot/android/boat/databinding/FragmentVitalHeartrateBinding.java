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
import com.coveiot.android.theme.databinding.VitalsDetailedLayoutBinding;
import com.github.mikephil.charting.charts.CandleStickChart;
/* loaded from: classes3.dex */
public abstract class FragmentVitalHeartrateBinding extends ViewDataBinding {
    @NonNull
    public final View divider;
    @NonNull
    public final ConstraintLayout graphBg;
    @NonNull
    public final CandleStickChart hrGraph;
    @NonNull
    public final TextView tvHRdescription;
    @NonNull
    public final TextView tvNoDataFound;
    @NonNull
    public final TextView tvWhatIsHr;
    @NonNull
    public final TextView tvdisclaimer;
    @NonNull
    public final VitalsDetailedLayoutBinding vitalsMainData;

    public FragmentVitalHeartrateBinding(Object obj, View view, int i, View view2, ConstraintLayout constraintLayout, CandleStickChart candleStickChart, TextView textView, TextView textView2, TextView textView3, TextView textView4, VitalsDetailedLayoutBinding vitalsDetailedLayoutBinding) {
        super(obj, view, i);
        this.divider = view2;
        this.graphBg = constraintLayout;
        this.hrGraph = candleStickChart;
        this.tvHRdescription = textView;
        this.tvNoDataFound = textView2;
        this.tvWhatIsHr = textView3;
        this.tvdisclaimer = textView4;
        this.vitalsMainData = vitalsDetailedLayoutBinding;
    }

    public static FragmentVitalHeartrateBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentVitalHeartrateBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVitalHeartrateBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentVitalHeartrateBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_vital_heartrate);
    }

    @NonNull
    @Deprecated
    public static FragmentVitalHeartrateBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentVitalHeartrateBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_vital_heartrate, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentVitalHeartrateBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentVitalHeartrateBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentVitalHeartrateBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_vital_heartrate, null, false, obj);
    }
}
