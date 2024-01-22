package com.coveiot.android.qrtray.databinding;

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
import com.coveiot.android.qrtray.R;
/* loaded from: classes5.dex */
public abstract class FragmentQrTrayUploadBinding extends ViewDataBinding {
    @NonNull
    public final Button btnUpload;
    @NonNull
    public final ImageView ivQRCode;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvUploadNew;
    @NonNull
    public final TextView tvWatchVideo;

    public FragmentQrTrayUploadBinding(Object obj, View view, int i, Button button, ImageView imageView, View view2, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.btnUpload = button;
        this.ivQRCode = imageView;
        this.toolbar = view2;
        this.tvUploadNew = textView;
        this.tvWatchVideo = textView2;
    }

    public static FragmentQrTrayUploadBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentQrTrayUploadBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentQrTrayUploadBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentQrTrayUploadBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_qr_tray_upload);
    }

    @NonNull
    @Deprecated
    public static FragmentQrTrayUploadBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentQrTrayUploadBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_qr_tray_upload, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentQrTrayUploadBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentQrTrayUploadBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentQrTrayUploadBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_qr_tray_upload, null, false, obj);
    }
}
