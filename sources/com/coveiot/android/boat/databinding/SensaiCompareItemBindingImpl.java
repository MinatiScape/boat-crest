package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public class SensaiCompareItemBindingImpl extends SensaiCompareItemBinding {
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
        sparseIntArray.put(R.id.guideline1, 1);
        sparseIntArray.put(R.id.guideline2, 2);
        sparseIntArray.put(R.id.cl_compare_1, 3);
        sparseIntArray.put(R.id.iv_compare_1, 4);
        sparseIntArray.put(R.id.tv_compare_title_1, 5);
        sparseIntArray.put(R.id.tv_compare_details_1, 6);
        sparseIntArray.put(R.id.view6, 7);
        sparseIntArray.put(R.id.cl_compare_2, 8);
        sparseIntArray.put(R.id.iv_compare_2, 9);
        sparseIntArray.put(R.id.tv_compare_title_2, 10);
        sparseIntArray.put(R.id.tv_compare_details_2, 11);
    }

    public SensaiCompareItemBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 12, j, k));
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

    public SensaiCompareItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[3], (ConstraintLayout) objArr[8], (Guideline) objArr[1], (Guideline) objArr[2], (ImageView) objArr[4], (ImageView) objArr[9], (TextView) objArr[6], (TextView) objArr[11], (TextView) objArr[5], (TextView) objArr[10], (View) objArr[7]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
