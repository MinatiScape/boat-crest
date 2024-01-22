package com.coveiot.android.permissionsandsettings.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.permissionsandsettings.R;
/* loaded from: classes5.dex */
public class ActivityConfigScreenBindingImpl extends ActivityConfigScreenBinding {
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
        sparseIntArray.put(R.id.tvHeader, 1);
        sparseIntArray.put(R.id.tvHeaderInfo, 2);
        sparseIntArray.put(R.id.clBattery, 3);
        sparseIntArray.put(R.id.tvBatteryHeader, 4);
        sparseIntArray.put(R.id.tvBatteryInfo, 5);
        sparseIntArray.put(R.id.ivBattery, 6);
        sparseIntArray.put(R.id.tvDisableBatteryOptimisation, 7);
        sparseIntArray.put(R.id.tvSetting, 8);
        sparseIntArray.put(R.id.clAutoStart, 9);
        sparseIntArray.put(R.id.batteryView, 10);
        sparseIntArray.put(R.id.tvAutoStart, 11);
        sparseIntArray.put(R.id.tvAutoStartInfo, 12);
        sparseIntArray.put(R.id.ivAutoStart, 13);
        sparseIntArray.put(R.id.tvEnableAutoStart, 14);
        sparseIntArray.put(R.id.clPowerOptimisation, 15);
        sparseIntArray.put(R.id.autoView, 16);
        sparseIntArray.put(R.id.tvPower, 17);
        sparseIntArray.put(R.id.tvPowerInfo, 18);
        sparseIntArray.put(R.id.ivPowerStart, 19);
        sparseIntArray.put(R.id.tvDisablePowerOptimisation, 20);
        sparseIntArray.put(R.id.gl_bottom, 21);
        sparseIntArray.put(R.id.btnContinueToHomepage, 22);
    }

    public ActivityConfigScreenBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 23, j, k));
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

    public ActivityConfigScreenBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (View) objArr[16], (View) objArr[10], (Button) objArr[22], (ConstraintLayout) objArr[9], (ConstraintLayout) objArr[3], (ConstraintLayout) objArr[15], (Guideline) objArr[21], (ImageView) objArr[13], (ImageView) objArr[6], (ImageView) objArr[19], (TextView) objArr[11], (TextView) objArr[12], (TextView) objArr[4], (TextView) objArr[5], (TextView) objArr[7], (TextView) objArr[20], (TextView) objArr[14], (TextView) objArr[1], (TextView) objArr[2], (TextView) objArr[17], (TextView) objArr[18], (TextView) objArr[8]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
