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
import com.airbnb.lottie.LottieAnimationView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class FragmentDevicePairedBinding extends ViewDataBinding {
    @NonNull
    public final Button btnGetStarted;
    @NonNull
    public final ConstraintLayout clGrid;
    @NonNull
    public final LottieAnimationView ivConnectionLoader;
    @NonNull
    public final ImageView ivDeviceImage;
    @NonNull
    public final TextView tvConnected;
    @NonNull
    public final TextView tvDeviceConnected;
    @NonNull
    public final TextView tvSuccess;

    public FragmentDevicePairedBinding(Object obj, View view, int i, Button button, ConstraintLayout constraintLayout, LottieAnimationView lottieAnimationView, ImageView imageView, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.btnGetStarted = button;
        this.clGrid = constraintLayout;
        this.ivConnectionLoader = lottieAnimationView;
        this.ivDeviceImage = imageView;
        this.tvConnected = textView;
        this.tvDeviceConnected = textView2;
        this.tvSuccess = textView3;
    }

    public static FragmentDevicePairedBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentDevicePairedBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDevicePairedBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentDevicePairedBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_device_paired);
    }

    @NonNull
    @Deprecated
    public static FragmentDevicePairedBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentDevicePairedBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_device_paired, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentDevicePairedBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentDevicePairedBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentDevicePairedBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_device_paired, null, false, obj);
    }
}
