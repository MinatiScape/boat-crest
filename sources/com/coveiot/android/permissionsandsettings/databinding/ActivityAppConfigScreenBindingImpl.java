package com.coveiot.android.permissionsandsettings.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.permissionsandsettings.R;
/* loaded from: classes5.dex */
public class ActivityAppConfigScreenBindingImpl extends ActivityAppConfigScreenBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j = null;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final LinearLayout h;
    public long i;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(R.id.toolbar, 1);
        sparseIntArray.put(R.id.clBattery, 2);
        sparseIntArray.put(R.id.settings_name, 3);
        sparseIntArray.put(R.id.tvSetting, 4);
        sparseIntArray.put(R.id.list_item_divider, 5);
        sparseIntArray.put(R.id.clAutoStart, 6);
        sparseIntArray.put(R.id.list_item_divider2, 7);
        sparseIntArray.put(R.id.clPowerOptimisation, 8);
    }

    public ActivityAppConfigScreenBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 9, j, k));
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

    public ActivityAppConfigScreenBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[6], (ConstraintLayout) objArr[2], (ConstraintLayout) objArr[8], (View) objArr[5], (View) objArr[7], (TextView) objArr[3], (View) objArr[1], (TextView) objArr[4]);
        this.i = -1L;
        LinearLayout linearLayout = (LinearLayout) objArr[0];
        this.h = linearLayout;
        linearLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
