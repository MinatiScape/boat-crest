package com.coveiot.android.theme.databinding;

import android.util.SparseIntArray;
import android.view.View;
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
public class DeviceNotPairedBindingImpl extends DeviceNotPairedBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.tvHeader, 1);
        sparseIntArray.put(R.id.tvContent, 2);
        sparseIntArray.put(R.id.clProgress, 3);
        sparseIntArray.put(R.id.progressBar, 4);
        sparseIntArray.put(R.id.ivProfile, 5);
        sparseIntArray.put(R.id.clCompleteProfile, 6);
        sparseIntArray.put(R.id.tvCompleteProfile, 7);
    }

    public DeviceNotPairedBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 8, i, j));
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

    public DeviceNotPairedBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[6], (ConstraintLayout) objArr[0], (ConstraintLayout) objArr[3], (ImageView) objArr[5], (ProgressBar) objArr[4], (TextView) objArr[7], (TextView) objArr[2], (TextView) objArr[1]);
        this.h = -1L;
        this.clMain.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
