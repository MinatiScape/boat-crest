package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.utils.RoundedBarChart;
import com.coveiot.android.theme.databinding.VitalsDetailedLayoutBinding;
/* loaded from: classes3.dex */
public class FragmentVitalStepsBindingImpl extends FragmentVitalStepsBinding {
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
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(23);
        k = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"vitals_detailed_layout"}, new int[]{2}, new int[]{R.layout.vitals_detailed_layout});
        SparseIntArray sparseIntArray = new SparseIntArray();
        l = sparseIntArray;
        sparseIntArray.put(R.id.graphBg, 3);
        sparseIntArray.put(R.id.stepsGraph, 4);
        sparseIntArray.put(R.id.tvNoDataFound, 5);
        sparseIntArray.put(R.id.tvEditVitalNameGoal, 6);
        sparseIntArray.put(R.id.clSedentaryTimes, 7);
        sparseIntArray.put(R.id.tvSedentaryTimes, 8);
        sparseIntArray.put(R.id.tvSedentaryTimesValue, 9);
        sparseIntArray.put(R.id.clActiveTimes, 10);
        sparseIntArray.put(R.id.tvActiveTimes, 11);
        sparseIntArray.put(R.id.tvActiveTimesValue, 12);
        sparseIntArray.put(R.id.clInsights, 13);
        sparseIntArray.put(R.id.tvInsightsHeader, 14);
        sparseIntArray.put(R.id.rvInsights, 15);
        sparseIntArray.put(R.id.view_insights, 16);
        sparseIntArray.put(R.id.cl_achievements, 17);
        sparseIntArray.put(R.id.divider, 18);
        sparseIntArray.put(R.id.tvYourAchievements, 19);
        sparseIntArray.put(R.id.tv_achievements_view_more, 20);
        sparseIntArray.put(R.id.tvAchievementInfo, 21);
        sparseIntArray.put(R.id.rvAchievements, 22);
    }

    public FragmentVitalStepsBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 23, k, l));
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

    public FragmentVitalStepsBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (ConstraintLayout) objArr[17], (ConstraintLayout) objArr[10], (ConstraintLayout) objArr[13], (ConstraintLayout) objArr[7], (View) objArr[18], (ConstraintLayout) objArr[3], (RecyclerView) objArr[22], (RecyclerView) objArr[15], (RoundedBarChart) objArr[4], (TextView) objArr[21], (TextView) objArr[20], (TextView) objArr[11], (TextView) objArr[12], (TextView) objArr[6], (TextView) objArr[14], (TextView) objArr[5], (TextView) objArr[8], (TextView) objArr[9], (TextView) objArr[19], (View) objArr[16], (VitalsDetailedLayoutBinding) objArr[2]);
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
