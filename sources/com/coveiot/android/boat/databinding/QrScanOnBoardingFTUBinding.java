package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class QrScanOnBoardingFTUBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivImages;

    public QrScanOnBoardingFTUBinding(Object obj, View view, int i, ImageView imageView) {
        super(obj, view, i);
        this.ivImages = imageView;
    }

    public static QrScanOnBoardingFTUBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static QrScanOnBoardingFTUBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static QrScanOnBoardingFTUBinding bind(@NonNull View view, @Nullable Object obj) {
        return (QrScanOnBoardingFTUBinding) ViewDataBinding.bind(obj, view, R.layout.qr_scan_on_boarding_f_t_u);
    }

    @NonNull
    @Deprecated
    public static QrScanOnBoardingFTUBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (QrScanOnBoardingFTUBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.qr_scan_on_boarding_f_t_u, viewGroup, z, obj);
    }

    @NonNull
    public static QrScanOnBoardingFTUBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static QrScanOnBoardingFTUBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (QrScanOnBoardingFTUBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.qr_scan_on_boarding_f_t_u, null, false, obj);
    }
}
