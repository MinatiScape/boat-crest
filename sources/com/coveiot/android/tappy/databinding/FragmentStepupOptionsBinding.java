package com.coveiot.android.tappy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public abstract class FragmentStepupOptionsBinding extends ViewDataBinding {
    @NonNull
    public final RadioGroup RadioGroup;
    @NonNull
    public final Button btnGetOtp;
    @NonNull
    public final ConstraintLayout clSetupOptionses;
    @NonNull
    public final AppCompatRadioButton rbAppToApp;
    @NonNull
    public final AppCompatRadioButton rbCallIssuer;
    @NonNull
    public final AppCompatRadioButton rbCallMe;
    @NonNull
    public final AppCompatRadioButton rbSendCodeThroughEmail;
    @NonNull
    public final AppCompatRadioButton rbSendCodeThroughSms;
    @NonNull
    public final TextView tvInfo;
    @NonNull
    public final TextView tvOtpInstruction;

    public FragmentStepupOptionsBinding(Object obj, View view, int i, RadioGroup radioGroup, Button button, ConstraintLayout constraintLayout, AppCompatRadioButton appCompatRadioButton, AppCompatRadioButton appCompatRadioButton2, AppCompatRadioButton appCompatRadioButton3, AppCompatRadioButton appCompatRadioButton4, AppCompatRadioButton appCompatRadioButton5, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.RadioGroup = radioGroup;
        this.btnGetOtp = button;
        this.clSetupOptionses = constraintLayout;
        this.rbAppToApp = appCompatRadioButton;
        this.rbCallIssuer = appCompatRadioButton2;
        this.rbCallMe = appCompatRadioButton3;
        this.rbSendCodeThroughEmail = appCompatRadioButton4;
        this.rbSendCodeThroughSms = appCompatRadioButton5;
        this.tvInfo = textView;
        this.tvOtpInstruction = textView2;
    }

    public static FragmentStepupOptionsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentStepupOptionsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentStepupOptionsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentStepupOptionsBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_stepup_options);
    }

    @NonNull
    @Deprecated
    public static FragmentStepupOptionsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentStepupOptionsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_stepup_options, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentStepupOptionsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentStepupOptionsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentStepupOptionsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_stepup_options, null, false, obj);
    }
}
