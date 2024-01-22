package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.coveiot.android.boat.R;
import com.coveiot.android.theme.CircularArcProgressBar;
import com.coveiot.android.theme.databinding.VitalsDetailedLayoutBinding;
import com.github.mikephil.charting.charts.LineChart;
/* loaded from: classes3.dex */
public class FragmentVitalHrvBindingImpl extends FragmentVitalHrvBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts k;
    @Nullable
    public static final SparseIntArray l;
    @NonNull
    public final ConstraintLayout h;
    @NonNull
    public final ConstraintLayout i;
    public long j;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(22);
        k = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"vitals_detailed_layout"}, new int[]{2}, new int[]{R.layout.vitals_detailed_layout});
        SparseIntArray sparseIntArray = new SparseIntArray();
        l = sparseIntArray;
        sparseIntArray.put(R.id.nestedScrollStress, 3);
        sparseIntArray.put(R.id.tv_enable_hrv_settings, 4);
        sparseIntArray.put(R.id.graphBg, 5);
        sparseIntArray.put(R.id.lineChartHRV, 6);
        sparseIntArray.put(R.id.tvNoDataFound, 7);
        sparseIntArray.put(R.id.legendLayout, 8);
        sparseIntArray.put(R.id.hrvValue, 9);
        sparseIntArray.put(R.id.baseline, 10);
        sparseIntArray.put(R.id.readiness_layout, 11);
        sparseIntArray.put(R.id.textView50, 12);
        sparseIntArray.put(R.id.tv_morning_readiness_dynamic_txt, 13);
        sparseIntArray.put(R.id.cl_progress, 14);
        sparseIntArray.put(R.id.readiness_score_bar, 15);
        sparseIntArray.put(R.id.tv_readiness_value, 16);
        sparseIntArray.put(R.id.tv_readiness_total, 17);
        sparseIntArray.put(R.id.divider, 18);
        sparseIntArray.put(R.id.tv_what_is_hrv, 19);
        sparseIntArray.put(R.id.tvhrvdescription, 20);
        sparseIntArray.put(R.id.tvdisclaimer, 21);
    }

    public FragmentVitalHrvBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 22, k, l));
    }

    public final boolean a(VitalsDetailedLayoutBinding vitalsDetailedLayoutBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.j |= 1;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.j = 0L;
        }
        ViewDataBinding.executeBindingsOn(this.vitalsMainData);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.j != 0) {
                return true;
            }
            return this.vitalsMainData.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.j = 2L;
        }
        this.vitalsMainData.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return a((VitalsDetailedLayoutBinding) obj, i2);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.vitalsMainData.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public FragmentVitalHrvBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (TextView) objArr[10], (ConstraintLayout) objArr[14], (View) objArr[18], (ConstraintLayout) objArr[5], (TextView) objArr[9], (ConstraintLayout) objArr[8], (LineChart) objArr[6], (NestedScrollView) objArr[3], (ConstraintLayout) objArr[11], (CircularArcProgressBar) objArr[15], (TextView) objArr[12], (TextView) objArr[4], (TextView) objArr[13], (TextView) objArr[7], (TextView) objArr[17], (TextView) objArr[16], (TextView) objArr[19], (TextView) objArr[21], (TextView) objArr[20], (VitalsDetailedLayoutBinding) objArr[2]);
        this.j = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) objArr[1];
        this.i = constraintLayout2;
        constraintLayout2.setTag(null);
        setContainedBinding(this.vitalsMainData);
        setRootTag(view);
        invalidateAll();
    }
}
