package com.coveiot.android.sos.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.sos.R;
/* loaded from: classes7.dex */
public class SosContactItemSelectBindingImpl extends SosContactItemSelectBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.display_pic, 1);
        sparseIntArray.put(R.id.display_name, 2);
        sparseIntArray.put(R.id.display_number, 3);
        sparseIntArray.put(R.id.clEnd, 4);
        sparseIntArray.put(R.id.tvSynced, 5);
        sparseIntArray.put(R.id.barrier, 6);
        sparseIntArray.put(R.id.tvSOS, 7);
        sparseIntArray.put(R.id.ivContactSelect, 8);
    }

    public SosContactItemSelectBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 9, i, j));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.h = 0L;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.h != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.h = 1L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i2, Object obj, int i3) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i2, @Nullable Object obj) {
        return true;
    }

    public SosContactItemSelectBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Barrier) objArr[6], (ConstraintLayout) objArr[4], (TextView) objArr[2], (TextView) objArr[3], (ImageView) objArr[1], (ImageView) objArr[8], (RelativeLayout) objArr[0], (TextView) objArr[7], (TextView) objArr[5]);
        this.h = -1L;
        this.rootLayoutGeneric.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
