package com.coveiot.android.qrtray.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.qrtray.R;
import com.coveiot.android.qrtray.model.QRCodeDataApp;
/* loaded from: classes5.dex */
public abstract class ListItemAddedQrTrayLayoutBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivMenu;
    @NonNull
    public final ImageView ivQRCode;
    @Bindable
    public QRCodeDataApp mQrCodeData;
    @NonNull
    public final TextView tvName;
    @NonNull
    public final TextView tvStatus;
    @NonNull
    public final TextView tvTag;

    public ListItemAddedQrTrayLayoutBinding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.ivMenu = imageView;
        this.ivQRCode = imageView2;
        this.tvName = textView;
        this.tvStatus = textView2;
        this.tvTag = textView3;
    }

    public static ListItemAddedQrTrayLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ListItemAddedQrTrayLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public QRCodeDataApp getQrCodeData() {
        return this.mQrCodeData;
    }

    public abstract void setQrCodeData(@Nullable QRCodeDataApp qRCodeDataApp);

    @Deprecated
    public static ListItemAddedQrTrayLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ListItemAddedQrTrayLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.list_item_added_qr_tray_layout);
    }

    @NonNull
    @Deprecated
    public static ListItemAddedQrTrayLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ListItemAddedQrTrayLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_added_qr_tray_layout, viewGroup, z, obj);
    }

    @NonNull
    public static ListItemAddedQrTrayLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ListItemAddedQrTrayLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ListItemAddedQrTrayLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_added_qr_tray_layout, null, false, obj);
    }
}
