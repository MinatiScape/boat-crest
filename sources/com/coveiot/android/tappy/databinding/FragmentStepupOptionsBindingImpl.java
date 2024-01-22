package com.coveiot.android.tappy.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public class FragmentStepupOptionsBindingImpl extends FragmentStepupOptionsBinding {
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
        sparseIntArray.put(R.id.tvOtpInstruction, 1);
        sparseIntArray.put(R.id.tvInfo, 2);
        sparseIntArray.put(R.id.cl_setupOptionses, 3);
        sparseIntArray.put(R.id.RadioGroup, 4);
        sparseIntArray.put(R.id.rbCallMe, 5);
        sparseIntArray.put(R.id.rbSendCodeThroughSms, 6);
        sparseIntArray.put(R.id.rbSendCodeThroughEmail, 7);
        sparseIntArray.put(R.id.rbCallIssuer, 8);
        sparseIntArray.put(R.id.rb_app_to_app, 9);
        sparseIntArray.put(R.id.btn_get_otp, 10);
    }

    public FragmentStepupOptionsBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 11, j, k));
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

    public FragmentStepupOptionsBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (RadioGroup) objArr[4], (Button) objArr[10], (ConstraintLayout) objArr[3], (AppCompatRadioButton) objArr[9], (AppCompatRadioButton) objArr[8], (AppCompatRadioButton) objArr[5], (AppCompatRadioButton) objArr[7], (AppCompatRadioButton) objArr[6], (TextView) objArr[2], (TextView) objArr[1]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
