package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public class ActivityBatterySaverModeNewBindingImpl extends ActivityBatterySaverModeNewBinding {
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
        sparseIntArray.put(R.id.toolbar, 1);
        sparseIntArray.put(R.id.battery_saver_mode_information, 2);
        sparseIntArray.put(R.id.cl_battery_saver_mode_standard, 3);
        sparseIntArray.put(R.id.tv_bsm_standard, 4);
        sparseIntArray.put(R.id.std_mode_desc1, 5);
        sparseIntArray.put(R.id.std_mode_desc2, 6);
        sparseIntArray.put(R.id.std_mode_desc3, 7);
        sparseIntArray.put(R.id.std_mode_desc4, 8);
        sparseIntArray.put(R.id.view, 9);
        sparseIntArray.put(R.id.cl_bsm_advanced, 10);
        sparseIntArray.put(R.id.tv_bsm_advanced, 11);
        sparseIntArray.put(R.id.adv_mode_desc1, 12);
        sparseIntArray.put(R.id.adv_mode_desc2, 13);
        sparseIntArray.put(R.id.view2, 14);
    }

    public ActivityBatterySaverModeNewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 15, j, k));
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

    public ActivityBatterySaverModeNewBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (LinearLayout) objArr[12], (LinearLayout) objArr[13], (TextView) objArr[2], (ConstraintLayout) objArr[3], (ConstraintLayout) objArr[10], (LinearLayout) objArr[5], (LinearLayout) objArr[6], (LinearLayout) objArr[7], (LinearLayout) objArr[8], (View) objArr[1], (TextView) objArr[11], (TextView) objArr[4], (View) objArr[9], (View) objArr[14]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
