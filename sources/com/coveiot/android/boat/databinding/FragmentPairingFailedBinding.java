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
public abstract class FragmentPairingFailedBinding extends ViewDataBinding {
    @NonNull
    public final LottieAnimationView animPairingFailed;
    @NonNull
    public final ConstraintLayout clPairingFailed;
    @NonNull
    public final ImageView ivWatch;
    @NonNull
    public final TextView tvBle;
    @NonNull
    public final TextView tvInfo;
    @NonNull
    public final Button tvTryAgain;
    @NonNull
    public final TextView tvUnable;
    @NonNull
    public final TextView tvUnableInfo;

    public FragmentPairingFailedBinding(Object obj, View view, int i, LottieAnimationView lottieAnimationView, ConstraintLayout constraintLayout, ImageView imageView, TextView textView, TextView textView2, Button button, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.animPairingFailed = lottieAnimationView;
        this.clPairingFailed = constraintLayout;
        this.ivWatch = imageView;
        this.tvBle = textView;
        this.tvInfo = textView2;
        this.tvTryAgain = button;
        this.tvUnable = textView3;
        this.tvUnableInfo = textView4;
    }

    public static FragmentPairingFailedBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentPairingFailedBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentPairingFailedBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentPairingFailedBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_pairing_failed);
    }

    @NonNull
    @Deprecated
    public static FragmentPairingFailedBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentPairingFailedBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_pairing_failed, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentPairingFailedBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentPairingFailedBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentPairingFailedBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_pairing_failed, null, false, obj);
    }
}
