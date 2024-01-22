package com.coveiot.android.sleepenergyscore.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.sleepenergyscore.BR;
import com.coveiot.android.theme.R;
import com.coveiot.android.theme.databinding.VitalsDetailedLayoutBinding;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.viewpagerindicator.CirclePageIndicator;
/* loaded from: classes6.dex */
public class FragmentVitalEnergyMeterBindingImpl extends FragmentVitalEnergyMeterBinding {
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
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(37);
        k = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"vitals_detailed_layout"}, new int[]{2}, new int[]{R.layout.vitals_detailed_layout});
        SparseIntArray sparseIntArray = new SparseIntArray();
        l = sparseIntArray;
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.nestedScrollEnergyMeter, 3);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.graphBg, 4);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.lineChartEnergyMeter, 5);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.tvNoDataFound, 6);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.clMinMax, 7);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.minMaxDevider, 8);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.guideline2, 9);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.tvStartScoreHeader, 10);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.tvStartScore, 11);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.tvEndScoreHeader, 12);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.tvEndScore, 13);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.clEnergyInsight, 14);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.tvEnergyPercentage, 15);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.ivEnergyIncreaseDecrease, 16);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.tvEnergyIncreasedDecreased, 17);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.guideline, 18);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.tvEnergyInsightInfo, 19);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.tvEnergyBreakdownHeader, 20);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.tvEnergyBreakdownInfo, 21);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.clDrainGain, 22);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.guideline1, 23);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.tvDrainTab, 24);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.tvGainTab, 25);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.pieChartNoDataTv, 26);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.pieChart, 27);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.gridView, 28);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.tvTipsToBoostEnergy, 29);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.emTipsRecycler, 30);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.divider, 31);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.tv_what_is_em, 32);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.tvEmDescription, 33);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.view_pager_layout, 34);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.energy_feedback_pager, 35);
        sparseIntArray.put(com.coveiot.android.sleepenergyscore.R.id.circlePageIndicator_feedback, 36);
    }

    public FragmentVitalEnergyMeterBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 37, k, l));
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

    public FragmentVitalEnergyMeterBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (CirclePageIndicator) objArr[36], (ConstraintLayout) objArr[22], (ConstraintLayout) objArr[14], (ConstraintLayout) objArr[7], (View) objArr[31], (RecyclerView) objArr[30], (ViewPager) objArr[35], (ConstraintLayout) objArr[4], (RecyclerView) objArr[28], (Guideline) objArr[18], (Guideline) objArr[23], (Guideline) objArr[9], (ImageView) objArr[16], (LineChart) objArr[5], (View) objArr[8], (NestedScrollView) objArr[3], (PieChart) objArr[27], (TextView) objArr[26], (TextView) objArr[24], (TextView) objArr[33], (TextView) objArr[13], (TextView) objArr[12], (TextView) objArr[20], (TextView) objArr[21], (TextView) objArr[17], (TextView) objArr[19], (TextView) objArr[15], (TextView) objArr[25], (TextView) objArr[6], (TextView) objArr[11], (TextView) objArr[10], (TextView) objArr[29], (TextView) objArr[32], (LinearLayout) objArr[34], (VitalsDetailedLayoutBinding) objArr[2]);
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
