package com.coveiot.android.theme.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public class RoundedLeftTopBorderColorBackgroundCardHeaderInfoImageBindingImpl extends RoundedLeftTopBorderColorBackgroundCardHeaderInfoImageBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.card_background, 1);
        sparseIntArray.put(R.id.ivIconBackground, 2);
        sparseIntArray.put(R.id.ivIcon, 3);
        sparseIntArray.put(R.id.tvHeader, 4);
        sparseIntArray.put(R.id.view, 5);
        sparseIntArray.put(R.id.tvInfo, 6);
    }

    public RoundedLeftTopBorderColorBackgroundCardHeaderInfoImageBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 7, i, j));
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

    public RoundedLeftTopBorderColorBackgroundCardHeaderInfoImageBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[1], (ConstraintLayout) objArr[0], (ImageView) objArr[3], (ImageView) objArr[2], (TextView) objArr[4], (TextView) objArr[6], (View) objArr[5]);
        this.h = -1L;
        this.clMain.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
