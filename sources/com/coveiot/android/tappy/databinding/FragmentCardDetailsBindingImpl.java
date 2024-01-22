package com.coveiot.android.tappy.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public class FragmentCardDetailsBindingImpl extends FragmentCardDetailsBinding {
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
        sparseIntArray.put(R.id.tv_add_card_instruction, 1);
        sparseIntArray.put(R.id.tv_card_name, 2);
        sparseIntArray.put(R.id.cl_card_name, 3);
        sparseIntArray.put(R.id.edt_card_name, 4);
        sparseIntArray.put(R.id.tv_card_number, 5);
        sparseIntArray.put(R.id.cl_card_number, 6);
        sparseIntArray.put(R.id.edt_card_number, 7);
        sparseIntArray.put(R.id.viewCard, 8);
        sparseIntArray.put(R.id.img_card_type_logo, 9);
        sparseIntArray.put(R.id.v_middle, 10);
        sparseIntArray.put(R.id.tv_card_expiry, 11);
        sparseIntArray.put(R.id.cl_card_expiry, 12);
        sparseIntArray.put(R.id.view1, 13);
        sparseIntArray.put(R.id.edt_expiry_mm, 14);
        sparseIntArray.put(R.id.edt_expiry_yy, 15);
        sparseIntArray.put(R.id.tv_card_cvv, 16);
        sparseIntArray.put(R.id.cl_card_cvv, 17);
        sparseIntArray.put(R.id.edt_cvv, 18);
        sparseIntArray.put(R.id.btn_save_card, 19);
    }

    public FragmentCardDetailsBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 20, j, k));
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

    public FragmentCardDetailsBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Button) objArr[19], (ConstraintLayout) objArr[17], (ConstraintLayout) objArr[12], (ConstraintLayout) objArr[3], (ConstraintLayout) objArr[6], (AppCompatEditText) objArr[4], (AppCompatEditText) objArr[7], (AppCompatEditText) objArr[18], (AppCompatEditText) objArr[14], (AppCompatEditText) objArr[15], (ImageView) objArr[9], (TextView) objArr[1], (TextView) objArr[16], (TextView) objArr[11], (TextView) objArr[2], (TextView) objArr[5], (View) objArr[10], (View) objArr[13], (View) objArr[8]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
