package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public class SleepInsightsDataBindingImpl extends SleepInsightsDataBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j = null;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final ConstraintLayout h;
    public long i;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(R.id.tv_insights, 1);
        sparseIntArray.put(R.id.clAwakeTime, 2);
        sparseIntArray.put(R.id.tv_awake_perc, 3);
        sparseIntArray.put(R.id.tv_awake_time, 4);
        sparseIntArray.put(R.id.tv_awake_desc, 5);
        sparseIntArray.put(R.id.clTotalSleep, 6);
        sparseIntArray.put(R.id.tv_sleep_perc, 7);
        sparseIntArray.put(R.id.tv_sleep_time, 8);
        sparseIntArray.put(R.id.tv_sleep_desc, 9);
        sparseIntArray.put(R.id.clDeepSleep, 10);
        sparseIntArray.put(R.id.tv_deep_perc, 11);
        sparseIntArray.put(R.id.tv_deep_time, 12);
        sparseIntArray.put(R.id.tv_deep_desc, 13);
    }

    public SleepInsightsDataBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 14, j, k));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.i = 0L;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.i != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.i = 1L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public SleepInsightsDataBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[2], (ConstraintLayout) objArr[10], (ConstraintLayout) objArr[6], (TextView) objArr[5], (TextView) objArr[3], (TextView) objArr[4], (TextView) objArr[13], (TextView) objArr[11], (TextView) objArr[12], (TextView) objArr[1], (TextView) objArr[9], (TextView) objArr[7], (TextView) objArr[8]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
