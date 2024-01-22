package com.coveiot.android.tappy.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public class ContactPhoneNfcStrapBindingImpl extends ContactPhoneNfcStrapBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.add_strap_progress_layout, 1);
        sparseIntArray.put(R.id.ivStrapNfcNearby, 2);
        sparseIntArray.put(R.id.clStrapStatus, 3);
        sparseIntArray.put(R.id.tvStrapAddStatus, 4);
        sparseIntArray.put(R.id.tvStrapAddSuccess, 5);
        sparseIntArray.put(R.id.clDataTransferStatus, 6);
        sparseIntArray.put(R.id.tvTitle, 7);
        sparseIntArray.put(R.id.tvDataTransferStatusSucess, 8);
        sparseIntArray.put(R.id.add_strap_progress, 9);
        sparseIntArray.put(R.id.add_strap_status_layout, 10);
        sparseIntArray.put(R.id.failure_image, 11);
        sparseIntArray.put(R.id.update_status_tv, 12);
        sparseIntArray.put(R.id.btn_try_again, 13);
    }

    public ContactPhoneNfcStrapBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 14, i, j));
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

    public ContactPhoneNfcStrapBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ImageView) objArr[9], (ConstraintLayout) objArr[1], (ConstraintLayout) objArr[10], (Button) objArr[13], (ConstraintLayout) objArr[6], (ConstraintLayout) objArr[3], (ConstraintLayout) objArr[0], (ImageView) objArr[11], (ImageView) objArr[2], (TextView) objArr[8], (TextView) objArr[4], (TextView) objArr[5], (TextView) objArr[7], (TextView) objArr[12]);
        this.h = -1L;
        this.contactPhoneNfcStrap.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
