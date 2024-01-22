package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.theme.databinding.VitalsDetailedLayoutBinding;
/* loaded from: classes3.dex */
public abstract class FragmentVitalManualSpo2Binding extends ViewDataBinding {
    @NonNull
    public final LinearLayout cvSpo2;
    @NonNull
    public final View divider;
    @NonNull
    public final ConstraintLayout graphBg;
    @NonNull
    public final NestedScrollView nestedScrollView;
    @NonNull
    public final RecyclerView rvSpo2List;
    @NonNull
    public final RelativeLayout timeValLay;
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

    public FragmentVitalManualSpo2Binding(Object obj, View view, int i, LinearLayout linearLayout, View view2, ConstraintLayout constraintLayout, NestedScrollView nestedScrollView, RecyclerView recyclerView, RelativeLayout relativeLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4, VitalsDetailedLayoutBinding vitalsDetailedLayoutBinding) {
        super(obj, view, i);
        this.cvSpo2 = linearLayout;
        this.divider = view2;
        this.graphBg = constraintLayout;
        this.nestedScrollView = nestedScrollView;
        this.rvSpo2List = recyclerView;
        this.timeValLay = relativeLayout;
        this.tvNoDataFound = textView;
        this.tvWhatIsSpo2 = textView2;
        this.tvdisclaimer = textView3;
        this.tvspo2description = textView4;
        this.vitalsMainData = vitalsDetailedLayoutBinding;
    }

    public static FragmentVitalManualSpo2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentVitalManualSpo2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVitalManualSpo2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentVitalManualSpo2Binding) ViewDataBinding.bind(obj, view, R.layout.fragment_vital_manual_spo2);
    }

    @NonNull
    @Deprecated
    public static FragmentVitalManualSpo2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentVitalManualSpo2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_vital_manual_spo2, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentVitalManualSpo2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentVitalManualSpo2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentVitalManualSpo2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_vital_manual_spo2, null, false, obj);
    }
}
