package com.coveiot.android.tappy.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public class StrapListItemBindingImpl extends StrapListItemBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.cl_item, 1);
        sparseIntArray.put(R.id.imgv_strap, 2);
        sparseIntArray.put(R.id.tv_friendlyName, 3);
        sparseIntArray.put(R.id.imgv_expand, 4);
        sparseIntArray.put(R.id.cl_updateStrap, 5);
        sparseIntArray.put(R.id.guideline1, 6);
        sparseIntArray.put(R.id.tv_deRegister, 7);
        sparseIntArray.put(R.id.tv_updateFriendlyName, 8);
    }

    public StrapListItemBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
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

    public StrapListItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[1], (ConstraintLayout) objArr[5], (Guideline) objArr[6], (ImageView) objArr[4], (ImageView) objArr[2], (TextView) objArr[7], (TextView) objArr[3], (TextView) objArr[8], (CardView) objArr[0]);
        this.h = -1L;
        this.vitalsCardview.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
