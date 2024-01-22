package com.coveiot.android.tappy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
import com.coveiot.android.theme.compundview.OTPView;
/* loaded from: classes7.dex */
public abstract class FragmentEnterOtpBinding extends ViewDataBinding {
    @NonNull
    public final Button btnVerifyOtp;
    @NonNull
    public final TextView enterOtpDescriptionTxt;
    @NonNull
    public final ConstraintLayout enterOtpLayout;
    @NonNull
    public final HorizontalScrollView hsOtpLayout;
    @NonNull
    public final OTPView otpEditText;
    @NonNull
    public final TextView timerMessage;
    @NonNull
    public final TextView tvResend;
    @NonNull
    public final TextView tvResendTimerInSeconds1;
    @NonNull
    public final TextView verificationMessage;

    public FragmentEnterOtpBinding(Object obj, View view, int i, Button button, TextView textView, ConstraintLayout constraintLayout, HorizontalScrollView horizontalScrollView, OTPView oTPView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        super(obj, view, i);
        this.btnVerifyOtp = button;
        this.enterOtpDescriptionTxt = textView;
        this.enterOtpLayout = constraintLayout;
        this.hsOtpLayout = horizontalScrollView;
        this.otpEditText = oTPView;
        this.timerMessage = textView2;
        this.tvResend = textView3;
        this.tvResendTimerInSeconds1 = textView4;
        this.verificationMessage = textView5;
    }

    public static FragmentEnterOtpBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentEnterOtpBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentEnterOtpBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentEnterOtpBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_enter_otp);
    }

    @NonNull
    @Deprecated
    public static FragmentEnterOtpBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentEnterOtpBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_enter_otp, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentEnterOtpBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentEnterOtpBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentEnterOtpBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_enter_otp, null, false, obj);
    }
}
