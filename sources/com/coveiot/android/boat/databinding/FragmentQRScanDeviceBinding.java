package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class FragmentQRScanDeviceBinding extends ViewDataBinding {
    @NonNull
    public final Button btnScan;
    @NonNull
    public final ConstraintLayout clQRDetails;
    @NonNull
    public final ImageView ivQRScanbg;
    @NonNull
    public final ImageView qrReaderDisabled;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvHowToScan;
    @NonNull
    public final TextView tvScanQRCodeText;
    @NonNull
    public final View view;

    public FragmentQRScanDeviceBinding(Object obj, View view, int i, Button button, ConstraintLayout constraintLayout, ImageView imageView, ImageView imageView2, View view2, TextView textView, TextView textView2, View view3) {
        super(obj, view, i);
        this.btnScan = button;
        this.clQRDetails = constraintLayout;
        this.ivQRScanbg = imageView;
        this.qrReaderDisabled = imageView2;
        this.toolbar = view2;
        this.tvHowToScan = textView;
        this.tvScanQRCodeText = textView2;
        this.view = view3;
    }

    public static FragmentQRScanDeviceBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentQRScanDeviceBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentQRScanDeviceBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentQRScanDeviceBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_q_r_scan_device);
    }

    @NonNull
    @Deprecated
    public static FragmentQRScanDeviceBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentQRScanDeviceBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_q_r_scan_device, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentQRScanDeviceBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentQRScanDeviceBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentQRScanDeviceBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_q_r_scan_device, null, false, obj);
    }
}
