package com.coveiot.android.tappy.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public class FragmentManageRegisteredProduct1BindingImpl extends FragmentManageRegisteredProduct1Binding {
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
        sparseIntArray.put(R.id.cv_manageStrap, 1);
        sparseIntArray.put(R.id.cl_manageStrap, 2);
        sparseIntArray.put(R.id.tv_strapManage, 3);
        sparseIntArray.put(R.id.imgvDownStrap, 4);
        sparseIntArray.put(R.id.imgvUpStrap, 5);
        sparseIntArray.put(R.id.tv_strapManageDetails, 6);
        sparseIntArray.put(R.id.tvDeregisterStrap, 7);
        sparseIntArray.put(R.id.cv_manageCard, 8);
        sparseIntArray.put(R.id.cl_manageCard, 9);
        sparseIntArray.put(R.id.tv_cardManage, 10);
        sparseIntArray.put(R.id.imgvDownCard, 11);
        sparseIntArray.put(R.id.imgvUpCard, 12);
        sparseIntArray.put(R.id.tv_cardManageDetails, 13);
        sparseIntArray.put(R.id.tvDeleteCard, 14);
        sparseIntArray.put(R.id.tvSuspendCard, 15);
        sparseIntArray.put(R.id.tvResumeCard, 16);
        sparseIntArray.put(R.id.tvAddCard, 17);
    }

    public FragmentManageRegisteredProduct1BindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
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

    public FragmentManageRegisteredProduct1BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[9], (ConstraintLayout) objArr[2], (CardView) objArr[8], (CardView) objArr[1], (ImageView) objArr[11], (ImageView) objArr[4], (ImageView) objArr[12], (ImageView) objArr[5], (TextView) objArr[17], (TextView) objArr[10], (TextView) objArr[13], (TextView) objArr[14], (TextView) objArr[7], (TextView) objArr[16], (TextView) objArr[3], (TextView) objArr[6], (TextView) objArr[15]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
