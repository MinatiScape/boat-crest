package com.coveiot.android.theme.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public class VitalsDetailedLayoutBindingImpl extends VitalsDetailedLayoutBinding {
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
        sparseIntArray.put(R.id.clTopSelector, 1);
        sparseIntArray.put(R.id.tvDay, 2);
        sparseIntArray.put(R.id.tvWeek, 3);
        sparseIntArray.put(R.id.tvMonth, 4);
        sparseIntArray.put(R.id.clMainVitals, 5);
        sparseIntArray.put(R.id.clSelectedOptionValues, 6);
        sparseIntArray.put(R.id.ibPrevious, 7);
        sparseIntArray.put(R.id.tvSelectedTypeValue, 8);
        sparseIntArray.put(R.id.ibForward, 9);
        sparseIntArray.put(R.id.clVitalsData, 10);
        sparseIntArray.put(R.id.ibShareVitals, 11);
        sparseIntArray.put(R.id.ivVitalsBg, 12);
        sparseIntArray.put(R.id.clProgress, 13);
        sparseIntArray.put(R.id.progressBar, 14);
        sparseIntArray.put(R.id.ivCenterVitalBg, 15);
        sparseIntArray.put(R.id.ivCenterVital, 16);
        sparseIntArray.put(R.id.tvVitalName, 17);
        sparseIntArray.put(R.id.tvLastSyncTime, 18);
        sparseIntArray.put(R.id.clMinMaxData, 19);
        sparseIntArray.put(R.id.clMaxData, 20);
        sparseIntArray.put(R.id.ivMaxBg, 21);
        sparseIntArray.put(R.id.ivMax, 22);
        sparseIntArray.put(R.id.tvVitalMaxValue, 23);
        sparseIntArray.put(R.id.tvVitalMax, 24);
        sparseIntArray.put(R.id.clMinData, 25);
        sparseIntArray.put(R.id.ivMinBg, 26);
        sparseIntArray.put(R.id.ivMin, 27);
        sparseIntArray.put(R.id.tvVitalMinValue, 28);
        sparseIntArray.put(R.id.tvVitalMin, 29);
        sparseIntArray.put(R.id.clCenterDataEnergyAndStress, 30);
        sparseIntArray.put(R.id.tvAvgVitalValue, 31);
        sparseIntArray.put(R.id.view, 32);
        sparseIntArray.put(R.id.tvAvgValueLevel, 33);
        sparseIntArray.put(R.id.tvAvgType, 34);
        sparseIntArray.put(R.id.clCenterDataStepsSleep, 35);
        sparseIntArray.put(R.id.tvAvgStepsSleepValue, 36);
        sparseIntArray.put(R.id.tvOutOfStepsSleepValue, 37);
        sparseIntArray.put(R.id.clCenterDataHRVSpo2, 38);
        sparseIntArray.put(R.id.tvAvgHRVSpo2Value, 39);
        sparseIntArray.put(R.id.tvAvgHRVSpo2ValueUnit, 40);
        sparseIntArray.put(R.id.clCenterDataAVGStepSleep, 41);
        sparseIntArray.put(R.id.tvAvgStepSleepValue, 42);
        sparseIntArray.put(R.id.tvAvgStepSleepValueUnit, 43);
        sparseIntArray.put(R.id.tvVitalInfo, 44);
        sparseIntArray.put(R.id.clStepsSleepsTotalAvg, 45);
        sparseIntArray.put(R.id.tvAvgStepSleepTitle, 46);
        sparseIntArray.put(R.id.tvStepSleepTotalValue, 47);
    }

    public VitalsDetailedLayoutBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 48, j, k));
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

    public VitalsDetailedLayoutBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[41], (ConstraintLayout) objArr[30], (ConstraintLayout) objArr[38], (ConstraintLayout) objArr[35], (ConstraintLayout) objArr[5], (ConstraintLayout) objArr[20], (ConstraintLayout) objArr[25], (ConstraintLayout) objArr[19], (ConstraintLayout) objArr[13], (ConstraintLayout) objArr[6], (ConstraintLayout) objArr[45], (ConstraintLayout) objArr[1], (ConstraintLayout) objArr[10], (ImageButton) objArr[9], (ImageButton) objArr[7], (ImageButton) objArr[11], (ImageView) objArr[16], (ImageView) objArr[15], (ImageView) objArr[22], (ImageView) objArr[21], (ImageView) objArr[27], (ImageView) objArr[26], (ImageView) objArr[12], (ProgressBar) objArr[14], (TextView) objArr[39], (TextView) objArr[40], (TextView) objArr[46], (TextView) objArr[42], (TextView) objArr[43], (TextView) objArr[36], (TextView) objArr[34], (TextView) objArr[33], (TextView) objArr[31], (TextView) objArr[2], (TextView) objArr[18], (TextView) objArr[4], (TextView) objArr[37], (TextView) objArr[8], (TextView) objArr[47], (TextView) objArr[44], (TextView) objArr[24], (TextView) objArr[23], (TextView) objArr[29], (TextView) objArr[28], (TextView) objArr[17], (TextView) objArr[3], (View) objArr[32]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
