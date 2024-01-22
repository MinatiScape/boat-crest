package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.coveiot.android.boat.R;
import com.github.mikephil.charting.charts.LineChart;
/* loaded from: classes3.dex */
public class FragmentOverallStatsBowlingBindingImpl extends FragmentOverallStatsBowlingBinding {
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
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(26);
        k = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"sens_ai_details"}, new int[]{2}, new int[]{R.layout.sens_ai_details});
        SparseIntArray sparseIntArray = new SparseIntArray();
        l = sparseIntArray;
        sparseIntArray.put(R.id.cl_hit_analysis, 3);
        sparseIntArray.put(R.id.cl_hit_analysis_details, 4);
        sparseIntArray.put(R.id.tv_total_balls, 5);
        sparseIntArray.put(R.id.cl_hit_analysis_perc, 6);
        sparseIntArray.put(R.id.tv_avg_hit, 7);
        sparseIntArray.put(R.id.view2, 8);
        sparseIntArray.put(R.id.cl_hand_speed_graph, 9);
        sparseIntArray.put(R.id.tv_hand_speed, 10);
        sparseIntArray.put(R.id.lineChartArmSpeed, 11);
        sparseIntArray.put(R.id.tvNoDataFoundHandSpeed, 12);
        sparseIntArray.put(R.id.barrierSpeed, 13);
        sparseIntArray.put(R.id.cl_arm_speed_details, 14);
        sparseIntArray.put(R.id.tv_speed_summary, 15);
        sparseIntArray.put(R.id.view3, 16);
        sparseIntArray.put(R.id.cl_avg, 17);
        sparseIntArray.put(R.id.tv_avg, 18);
        sparseIntArray.put(R.id.tv_avg_value, 19);
        sparseIntArray.put(R.id.cl_min, 20);
        sparseIntArray.put(R.id.tv_min, 21);
        sparseIntArray.put(R.id.tv_min_value, 22);
        sparseIntArray.put(R.id.cl_max, 23);
        sparseIntArray.put(R.id.tv_max, 24);
        sparseIntArray.put(R.id.tv_max_value, 25);
    }

    public FragmentOverallStatsBowlingBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 26, k, l));
    }

    public final boolean a(SensAiDetailsBinding sensAiDetailsBinding, int i) {
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
        ViewDataBinding.executeBindingsOn(this.sensAiDetails);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.j != 0) {
                return true;
            }
            return this.sensAiDetails.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.j = 2L;
        }
        this.sensAiDetails.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return a((SensAiDetailsBinding) obj, i2);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.sensAiDetails.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public FragmentOverallStatsBowlingBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (Barrier) objArr[13], (ConstraintLayout) objArr[14], (ConstraintLayout) objArr[17], (ConstraintLayout) objArr[9], (ConstraintLayout) objArr[3], (ConstraintLayout) objArr[4], (ConstraintLayout) objArr[6], (ConstraintLayout) objArr[23], (ConstraintLayout) objArr[20], (LineChart) objArr[11], (SensAiDetailsBinding) objArr[2], (TextView) objArr[18], (TextView) objArr[7], (TextView) objArr[19], (TextView) objArr[10], (TextView) objArr[24], (TextView) objArr[25], (TextView) objArr[21], (TextView) objArr[22], (TextView) objArr[12], (TextView) objArr[15], (TextView) objArr[5], (View) objArr[8], (View) objArr[16]);
        this.j = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) objArr[1];
        this.i = constraintLayout2;
        constraintLayout2.setTag(null);
        setContainedBinding(this.sensAiDetails);
        setRootTag(view);
        invalidateAll();
    }
}
