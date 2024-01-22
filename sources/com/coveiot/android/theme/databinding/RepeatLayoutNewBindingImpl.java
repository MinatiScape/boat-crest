package com.coveiot.android.theme.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public class RepeatLayoutNewBindingImpl extends RepeatLayoutNewBinding {
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
        sparseIntArray.put(R.id.cl_repeat, 1);
        sparseIntArray.put(R.id.tv_repeat_label, 2);
        sparseIntArray.put(R.id.switch_repeat, 3);
        sparseIntArray.put(R.id.tv_select_days_info, 4);
        sparseIntArray.put(R.id.sunday, 5);
        sparseIntArray.put(R.id.monday, 6);
        sparseIntArray.put(R.id.tuesday, 7);
        sparseIntArray.put(R.id.wednesday, 8);
        sparseIntArray.put(R.id.thursday, 9);
        sparseIntArray.put(R.id.friday, 10);
        sparseIntArray.put(R.id.saturday, 11);
        sparseIntArray.put(R.id.cb_select_all, 12);
        sparseIntArray.put(R.id.divider, 13);
    }

    public RepeatLayoutNewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
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

    public RepeatLayoutNewBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (CheckBox) objArr[12], (ConstraintLayout) objArr[1], (View) objArr[13], (TextView) objArr[10], (TextView) objArr[6], (TextView) objArr[11], (TextView) objArr[5], (SwitchCompat) objArr[3], (TextView) objArr[9], (TextView) objArr[7], (TextView) objArr[2], (TextView) objArr[4], (TextView) objArr[8]);
        this.i = -1L;
        LinearLayout linearLayout = (LinearLayout) objArr[0];
        this.h = linearLayout;
        linearLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
