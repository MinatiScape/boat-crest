package com.coveiot.android.watchfaceui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.watchfaceui.R;
import com.github.siyamed.shapeimageview.RoundedImageView;
/* loaded from: classes8.dex */
public abstract class WatchFaceHolderWaveSmartCallBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clMainData;
    @NonNull
    public final ImageView ivWatch;
    @NonNull
    public final ImageView ivWaveBg;
    @NonNull
    public final RoundedImageView watchfacePlaceholderImgV;

    public WatchFaceHolderWaveSmartCallBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ImageView imageView, ImageView imageView2, RoundedImageView roundedImageView) {
        super(obj, view, i);
        this.clMainData = constraintLayout;
        this.ivWatch = imageView;
        this.ivWaveBg = imageView2;
        this.watchfacePlaceholderImgV = roundedImageView;
    }

    public static WatchFaceHolderWaveSmartCallBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static WatchFaceHolderWaveSmartCallBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WatchFaceHolderWaveSmartCallBinding bind(@NonNull View view, @Nullable Object obj) {
        return (WatchFaceHolderWaveSmartCallBinding) ViewDataBinding.bind(obj, view, R.layout.watch_face_holder_wave_smart_call);
    }

    @NonNull
    @Deprecated
    public static WatchFaceHolderWaveSmartCallBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (WatchFaceHolderWaveSmartCallBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.watch_face_holder_wave_smart_call, viewGroup, z, obj);
    }

    @NonNull
    public static WatchFaceHolderWaveSmartCallBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static WatchFaceHolderWaveSmartCallBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (WatchFaceHolderWaveSmartCallBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.watch_face_holder_wave_smart_call, null, false, obj);
    }
}
