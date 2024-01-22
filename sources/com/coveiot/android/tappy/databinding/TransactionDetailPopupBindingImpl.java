package com.coveiot.android.tappy.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public class TransactionDetailPopupBindingImpl extends TransactionDetailPopupBinding {
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
        sparseIntArray.put(R.id.imgv_close, 2);
        sparseIntArray.put(R.id.tv_merchantName, 3);
        sparseIntArray.put(R.id.cl_Amount, 4);
        sparseIntArray.put(R.id.tv_transactionAmount, 5);
        sparseIntArray.put(R.id.tv_transactionStatus, 6);
        sparseIntArray.put(R.id.tv_merchantCity, 7);
        sparseIntArray.put(R.id.tv_transactionDt, 8);
        sparseIntArray.put(R.id.cl_transactionType, 9);
        sparseIntArray.put(R.id.tv_transactionType, 10);
        sparseIntArray.put(R.id.cl_industryCategoryName, 11);
        sparseIntArray.put(R.id.tv_industryCategoryName, 12);
        sparseIntArray.put(R.id.cl_industryName, 13);
        sparseIntArray.put(R.id.tv_industryName, 14);
    }

    public TransactionDetailPopupBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
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

    public TransactionDetailPopupBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[4], (ConstraintLayout) objArr[11], (ConstraintLayout) objArr[13], (ConstraintLayout) objArr[9], (Guideline) objArr[1], (ImageButton) objArr[2], (TextView) objArr[12], (TextView) objArr[14], (TextView) objArr[7], (TextView) objArr[3], (TextView) objArr[5], (TextView) objArr[8], (TextView) objArr[6], (TextView) objArr[10]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
