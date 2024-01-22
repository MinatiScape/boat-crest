package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class FragmentResendOtpInfoDialogBinding extends ViewDataBinding {
    @NonNull
    public final Button btnResendOtpInfoDialogRateNow;
    @NonNull
    public final ImageView ivResendOtpInfoDialogClose;
    @NonNull
    public final TextView tvResendOtpInfoDialogMessage;
    @NonNull
    public final TextView tvResendOtpInfoDialogTitle;

    public FragmentResendOtpInfoDialogBinding(Object obj, View view, int i, Button button, ImageView imageView, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.btnResendOtpInfoDialogRateNow = button;
        this.ivResendOtpInfoDialogClose = imageView;
        this.tvResendOtpInfoDialogMessage = textView;
        this.tvResendOtpInfoDialogTitle = textView2;
    }

    public static FragmentResendOtpInfoDialogBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentResendOtpInfoDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentResendOtpInfoDialogBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentResendOtpInfoDialogBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_resend_otp_info_dialog);
    }

    @NonNull
    @Deprecated
    public static FragmentResendOtpInfoDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentResendOtpInfoDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_resend_otp_info_dialog, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentResendOtpInfoDialogBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentResendOtpInfoDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentResendOtpInfoDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_resend_otp_info_dialog, null, false, obj);
    }
}
