package com.coveiot.android.tappy.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public class TransactionDetailPopupnewBindingImpl extends TransactionDetailPopupnewBinding {
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
        sparseIntArray.put(R.id.tvPaidStatus, 1);
        sparseIntArray.put(R.id.tv_transactionAmount, 2);
        sparseIntArray.put(R.id.tv_transactionAmountInWords, 3);
        sparseIntArray.put(R.id.iv_transactionStatus, 4);
        sparseIntArray.put(R.id.view1, 5);
        sparseIntArray.put(R.id.tvPaidToTitle, 6);
        sparseIntArray.put(R.id.tv_merchantName, 7);
        sparseIntArray.put(R.id.view2, 8);
        sparseIntArray.put(R.id.tvPaidFromTitle, 9);
        sparseIntArray.put(R.id.tvPaidFromStrap, 10);
        sparseIntArray.put(R.id.tvLast4, 11);
        sparseIntArray.put(R.id.view3, 12);
        sparseIntArray.put(R.id.tv_transactionDt, 13);
        sparseIntArray.put(R.id.tvPaidTime, 14);
        sparseIntArray.put(R.id.tv_merchantCity, 15);
        sparseIntArray.put(R.id.view4, 16);
        sparseIntArray.put(R.id.tvTransactionId, 17);
    }

    public TransactionDetailPopupnewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 18, j, k));
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

    public TransactionDetailPopupnewBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ImageView) objArr[4], (TextView) objArr[11], (TextView) objArr[15], (TextView) objArr[7], (TextView) objArr[10], (TextView) objArr[9], (TextView) objArr[1], (TextView) objArr[14], (TextView) objArr[6], (TextView) objArr[2], (TextView) objArr[3], (TextView) objArr[13], (TextView) objArr[17], (View) objArr[5], (View) objArr[8], (View) objArr[12], (View) objArr[16]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
