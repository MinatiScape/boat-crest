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
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.utils.RoundedBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
/* loaded from: classes3.dex */
public class FragmentOverallStatsBattingBindingImpl extends FragmentOverallStatsBattingBinding {
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
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(34);
        k = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"sens_ai_details"}, new int[]{2}, new int[]{R.layout.sens_ai_details});
        SparseIntArray sparseIntArray = new SparseIntArray();
        l = sparseIntArray;
        sparseIntArray.put(R.id.hit_graph, 3);
        sparseIntArray.put(R.id.tv_hit_rate, 4);
        sparseIntArray.put(R.id.lineChartHitPerc, 5);
        sparseIntArray.put(R.id.tvNoDataFound, 6);
        sparseIntArray.put(R.id.view, 7);
        sparseIntArray.put(R.id.cl_hit_analysis, 8);
        sparseIntArray.put(R.id.tv_hit_analysis, 9);
        sparseIntArray.put(R.id.pieChart, 10);
        sparseIntArray.put(R.id.gridView, 11);
        sparseIntArray.put(R.id.cl_hit_analysis_details, 12);
        sparseIntArray.put(R.id.tv_total_balls, 13);
        sparseIntArray.put(R.id.cl_hit_analysis_perc, 14);
        sparseIntArray.put(R.id.tv_avg_hit, 15);
        sparseIntArray.put(R.id.view2, 16);
        sparseIntArray.put(R.id.cl_hand_speed_graph, 17);
        sparseIntArray.put(R.id.tv_hand_speed, 18);
        sparseIntArray.put(R.id.lineChartArmSpeed, 19);
        sparseIntArray.put(R.id.tvNoDataFoundHandSpeed, 20);
        sparseIntArray.put(R.id.barrierSpeed, 21);
        sparseIntArray.put(R.id.cl_arm_speed_details, 22);
        sparseIntArray.put(R.id.tv_speed_summary, 23);
        sparseIntArray.put(R.id.view3, 24);
        sparseIntArray.put(R.id.cl_avg, 25);
        sparseIntArray.put(R.id.tv_avg, 26);
        sparseIntArray.put(R.id.tv_avg_value, 27);
        sparseIntArray.put(R.id.cl_min, 28);
        sparseIntArray.put(R.id.tv_min, 29);
        sparseIntArray.put(R.id.tv_min_value, 30);
        sparseIntArray.put(R.id.cl_max, 31);
        sparseIntArray.put(R.id.tv_max, 32);
        sparseIntArray.put(R.id.tv_max_value, 33);
    }

    public FragmentOverallStatsBattingBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 34, k, l));
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

    public FragmentOverallStatsBattingBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (Barrier) objArr[21], (ConstraintLayout) objArr[22], (ConstraintLayout) objArr[25], (ConstraintLayout) objArr[17], (ConstraintLayout) objArr[8], (ConstraintLayout) objArr[12], (ConstraintLayout) objArr[14], (ConstraintLayout) objArr[31], (ConstraintLayout) objArr[28], (RecyclerView) objArr[11], (ConstraintLayout) objArr[3], (LineChart) objArr[19], (RoundedBarChart) objArr[5], (PieChart) objArr[10], (SensAiDetailsBinding) objArr[2], (TextView) objArr[26], (TextView) objArr[15], (TextView) objArr[27], (TextView) objArr[18], (TextView) objArr[9], (TextView) objArr[4], (TextView) objArr[32], (TextView) objArr[33], (TextView) objArr[29], (TextView) objArr[30], (TextView) objArr[6], (TextView) objArr[20], (TextView) objArr[23], (TextView) objArr[13], (View) objArr[7], (View) objArr[16], (View) objArr[24]);
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
