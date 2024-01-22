package com.coveiot.android.tappy.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
import com.coveiot.android.theme.compundview.OTPView;
/* loaded from: classes7.dex */
public class FragmentEnterOtpBindingImpl extends FragmentEnterOtpBinding {
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
        sparseIntArray.put(R.id.enter_otp_layout, 1);
        sparseIntArray.put(R.id.enter_otp_description_txt, 2);
        sparseIntArray.put(R.id.hsOtpLayout, 3);
        sparseIntArray.put(R.id.otpEditText, 4);
        sparseIntArray.put(R.id.verification_message, 5);
        sparseIntArray.put(R.id.timer_message, 6);
        sparseIntArray.put(R.id.tvResendTimerInSeconds1, 7);
        sparseIntArray.put(R.id.tvResend, 8);
        sparseIntArray.put(R.id.btn_verify_otp, 9);
    }

    public FragmentEnterOtpBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 10, j, k));
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

    public FragmentEnterOtpBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Button) objArr[9], (TextView) objArr[2], (ConstraintLayout) objArr[1], (HorizontalScrollView) objArr[3], (OTPView) objArr[4], (TextView) objArr[6], (TextView) objArr[8], (TextView) objArr[7], (TextView) objArr[5]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
