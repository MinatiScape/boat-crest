package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.HorizontalScrollView;
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
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.utils.RoundedBarChart;
import com.coveiot.android.theme.databinding.VitalsDetailedLayoutBinding;
import com.viewpagerindicator.CirclePageIndicator;
/* loaded from: classes3.dex */
public class FragmentVitalPeriodicStressBindingImpl extends FragmentVitalPeriodicStressBinding {
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
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(32);
        k = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"vitals_detailed_layout"}, new int[]{2}, new int[]{R.layout.vitals_detailed_layout});
        SparseIntArray sparseIntArray = new SparseIntArray();
        l = sparseIntArray;
        sparseIntArray.put(R.id.nestedScrollStress, 3);
        sparseIntArray.put(R.id.tv_enable_hrv_settings, 4);
        sparseIntArray.put(R.id.graphBg, 5);
        sparseIntArray.put(R.id.stressGraph, 6);
        sparseIntArray.put(R.id.tvNoDataFound, 7);
        sparseIntArray.put(R.id.hlLegendLayout, 8);
        sparseIntArray.put(R.id.legendLayout, 9);
        sparseIntArray.put(R.id.relaxed, 10);
        sparseIntArray.put(R.id.mild, 11);
        sparseIntArray.put(R.id.moderate, 12);
        sparseIntArray.put(R.id.high, 13);
        sparseIntArray.put(R.id.divider1, 14);
        sparseIntArray.put(R.id.clStressInsight, 15);
        sparseIntArray.put(R.id.guideline, 16);
        sparseIntArray.put(R.id.tvStressPercentage, 17);
        sparseIntArray.put(R.id.ivStressIncreaseDecrease, 18);
        sparseIntArray.put(R.id.tvStressIncreasedDecreased, 19);
        sparseIntArray.put(R.id.tvStressInsightInfo, 20);
        sparseIntArray.put(R.id.tvTipsToReduce, 21);
        sparseIntArray.put(R.id.stressTipsRecycler, 22);
        sparseIntArray.put(R.id.divider, 23);
        sparseIntArray.put(R.id.tv_what_is_stress, 24);
        sparseIntArray.put(R.id.tvstressdescription, 25);
        sparseIntArray.put(R.id.stress_range_bar, 26);
        sparseIntArray.put(R.id.divider2, 27);
        sparseIntArray.put(R.id.view_pager_layout, 28);
        sparseIntArray.put(R.id.feedback_pager, 29);
        sparseIntArray.put(R.id.circlePageIndicator_feedback, 30);
        sparseIntArray.put(R.id.tvdisclaimer, 31);
    }

    public FragmentVitalPeriodicStressBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 32, k, l));
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

    public FragmentVitalPeriodicStressBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (CirclePageIndicator) objArr[30], (ConstraintLayout) objArr[15], (View) objArr[23], (View) objArr[14], (View) objArr[27], (ViewPager) objArr[29], (ConstraintLayout) objArr[5], (Guideline) objArr[16], (TextView) objArr[13], (HorizontalScrollView) objArr[8], (ImageView) objArr[18], (ConstraintLayout) objArr[9], (TextView) objArr[11], (TextView) objArr[12], (NestedScrollView) objArr[3], (TextView) objArr[10], (RoundedBarChart) objArr[6], (ImageView) objArr[26], (RecyclerView) objArr[22], (TextView) objArr[4], (TextView) objArr[7], (TextView) objArr[19], (TextView) objArr[20], (TextView) objArr[17], (TextView) objArr[21], (TextView) objArr[24], (TextView) objArr[31], (TextView) objArr[25], (LinearLayout) objArr[28], (VitalsDetailedLayoutBinding) objArr[2]);
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
