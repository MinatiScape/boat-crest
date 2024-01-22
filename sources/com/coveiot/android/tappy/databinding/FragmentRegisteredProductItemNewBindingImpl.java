package com.coveiot.android.tappy.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
public class FragmentRegisteredProductItemNewBindingImpl extends FragmentRegisteredProductItemNewBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.guideline3, 1);
        sparseIntArray.put(R.id.guideline4, 2);
        sparseIntArray.put(R.id.cv_strapDetail, 3);
        sparseIntArray.put(R.id.clStrapImage, 4);
        sparseIntArray.put(R.id.imgvStrap, 5);
        sparseIntArray.put(R.id.cl_friendlyName, 6);
        sparseIntArray.put(R.id.tvStrapName, 7);
        sparseIntArray.put(R.id.imgv_edtFriendlyName, 8);
        sparseIntArray.put(R.id.tvStrapAddDetails, 9);
        sparseIntArray.put(R.id.cv_virtualCard, 10);
        sparseIntArray.put(R.id.imgv_virtualCard, 11);
        sparseIntArray.put(R.id.tv_virtualCard, 12);
        sparseIntArray.put(R.id.cl_cardNumber, 13);
        sparseIntArray.put(R.id.tv_staticAccountNumber, 14);
        sparseIntArray.put(R.id.tv_dynamicAccountNumber, 15);
        sparseIntArray.put(R.id.tv_virtualCardStatus, 16);
        sparseIntArray.put(R.id.cv_cardDetail, 17);
        sparseIntArray.put(R.id.imageViewHolder, 18);
        sparseIntArray.put(R.id.cl_last4Number, 19);
        sparseIntArray.put(R.id.tv_last4StaticNumber, 20);
        sparseIntArray.put(R.id.tv_last4DynamicNumber, 21);
        sparseIntArray.put(R.id.btn_addCard, 22);
    }

    public FragmentRegisteredProductItemNewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 23, i, j));
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

    public FragmentRegisteredProductItemNewBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Button) objArr[22], (ConstraintLayout) objArr[13], (ConstraintLayout) objArr[6], (ConstraintLayout) objArr[19], (ConstraintLayout) objArr[4], (ConstraintLayout) objArr[17], (ConstraintLayout) objArr[0], (CardView) objArr[3], (CardView) objArr[10], (Guideline) objArr[1], (Guideline) objArr[2], (ImageView) objArr[18], (ImageButton) objArr[8], (ImageView) objArr[5], (ImageView) objArr[11], (TextView) objArr[15], (TextView) objArr[21], (TextView) objArr[20], (TextView) objArr[14], (TextView) objArr[9], (TextView) objArr[7], (TextView) objArr[12], (TextView) objArr[16]);
        this.h = -1L;
        this.cvProductDetail.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
