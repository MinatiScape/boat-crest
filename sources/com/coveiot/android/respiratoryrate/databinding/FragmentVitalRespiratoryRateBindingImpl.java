package com.coveiot.android.respiratoryrate.databinding;

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
import com.coveiot.android.respiratoryrate.BR;
import com.coveiot.android.theme.R;
import com.coveiot.android.theme.databinding.VitalsDetailedLayoutBinding;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.charts.LineChart;
/* loaded from: classes6.dex */
public class FragmentVitalRespiratoryRateBindingImpl extends FragmentVitalRespiratoryRateBinding {
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
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(12);
        k = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"vitals_detailed_layout"}, new int[]{2}, new int[]{R.layout.vitals_detailed_layout});
        SparseIntArray sparseIntArray = new SparseIntArray();
        l = sparseIntArray;
        sparseIntArray.put(com.coveiot.android.respiratoryrate.R.id.nestedScrollViewNBR, 3);
        sparseIntArray.put(com.coveiot.android.respiratoryrate.R.id.graphBg, 4);
        sparseIntArray.put(com.coveiot.android.respiratoryrate.R.id.lineChartRespiratoryRate, 5);
        sparseIntArray.put(com.coveiot.android.respiratoryrate.R.id.candleChartRespiratoryRate, 6);
        sparseIntArray.put(com.coveiot.android.respiratoryrate.R.id.tvNoDataFound, 7);
        sparseIntArray.put(com.coveiot.android.respiratoryrate.R.id.divider, 8);
        sparseIntArray.put(com.coveiot.android.respiratoryrate.R.id.tv_what_is_nbr, 9);
        sparseIntArray.put(com.coveiot.android.respiratoryrate.R.id.tvHRdescription, 10);
        sparseIntArray.put(com.coveiot.android.respiratoryrate.R.id.tvdisclaimer, 11);
    }

    public FragmentVitalRespiratoryRateBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 12, k, l));
    }

    public final boolean a(VitalsDetailedLayoutBinding vitalsDetailedLayoutBinding, int i) {
        if (i == BR._all) {
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

    public FragmentVitalRespiratoryRateBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (CandleStickChart) objArr[6], (View) objArr[8], (ConstraintLayout) objArr[4], (LineChart) objArr[5], (NestedScrollView) objArr[3], (TextView) objArr[10], (TextView) objArr[7], (TextView) objArr[9], (TextView) objArr[11], (VitalsDetailedLayoutBinding) objArr[2]);
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
