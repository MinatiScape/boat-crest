package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
public abstract class FragmentVitalManualStressBinding extends ViewDataBinding {
    @NonNull
    public final LinearLayout cvStress;
    @NonNull
    public final View divider2;
    @NonNull
    public final View divider3;
    @NonNull
    public final ConstraintLayout graphBg;
    @NonNull
    public final NestedScrollView nestedScrollManualStress;
    @NonNull
    public final NestedScrollView nestedScrollView;
    @NonNull
    public final RecyclerView rvStressList;
    @NonNull
    public final ImageView stressRangeBar;
    @NonNull
    public final RecyclerView stressTipsRecycler;
    @NonNull
    public final RelativeLayout timeValLay;
    @NonNull
    public final TextView tvNoDataFound;
    @NonNull
    public final TextView tvTipsToReduce;
    @NonNull
    public final TextView tvWhatIsStress;
    @NonNull
    public final TextView tvstressdescription;
    @NonNull
    public final VitalsDetailedLayoutBinding vitalsMainData;

    public FragmentVitalManualStressBinding(Object obj, View view, int i, LinearLayout linearLayout, View view2, View view3, ConstraintLayout constraintLayout, NestedScrollView nestedScrollView, NestedScrollView nestedScrollView2, RecyclerView recyclerView, ImageView imageView, RecyclerView recyclerView2, RelativeLayout relativeLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4, VitalsDetailedLayoutBinding vitalsDetailedLayoutBinding) {
        super(obj, view, i);
        this.cvStress = linearLayout;
        this.divider2 = view2;
        this.divider3 = view3;
        this.graphBg = constraintLayout;
        this.nestedScrollManualStress = nestedScrollView;
        this.nestedScrollView = nestedScrollView2;
        this.rvStressList = recyclerView;
        this.stressRangeBar = imageView;
        this.stressTipsRecycler = recyclerView2;
        this.timeValLay = relativeLayout;
        this.tvNoDataFound = textView;
        this.tvTipsToReduce = textView2;
        this.tvWhatIsStress = textView3;
        this.tvstressdescription = textView4;
        this.vitalsMainData = vitalsDetailedLayoutBinding;
    }

    public static FragmentVitalManualStressBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentVitalManualStressBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVitalManualStressBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentVitalManualStressBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_vital_manual_stress);
    }

    @NonNull
    @Deprecated
    public static FragmentVitalManualStressBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentVitalManualStressBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_vital_manual_stress, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentVitalManualStressBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentVitalManualStressBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentVitalManualStressBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_vital_manual_stress, null, false, obj);
    }
}
