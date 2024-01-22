package com.coveiot.android.boat.databinding;

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
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.model.SelectedAppDataForQrCodePush;
import com.coveiot.android.leonardo.more.fragments.AppClickListener;
/* loaded from: classes3.dex */
public abstract class LayoutItemQrCodeAppListBinding extends ViewDataBinding {
    @NonNull
    public final ImageView imageView22;
    @NonNull
    public final ImageView ivAppIcon;
    @Bindable
    public SelectedAppDataForQrCodePush mAppData;
    @Bindable
    public AppClickListener mClickListener;
    @NonNull
    public final TextView tvAppName;

    public LayoutItemQrCodeAppListBinding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, TextView textView) {
        super(obj, view, i);
        this.imageView22 = imageView;
        this.ivAppIcon = imageView2;
        this.tvAppName = textView;
    }

    public static LayoutItemQrCodeAppListBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutItemQrCodeAppListBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public SelectedAppDataForQrCodePush getAppData() {
        return this.mAppData;
    }

    @Nullable
    public AppClickListener getClickListener() {
        return this.mClickListener;
    }

    public abstract void setAppData(@Nullable SelectedAppDataForQrCodePush selectedAppDataForQrCodePush);

    public abstract void setClickListener(@Nullable AppClickListener appClickListener);

    @Deprecated
    public static LayoutItemQrCodeAppListBinding bind(@NonNull View view, @Nullable Object obj) {
        return (LayoutItemQrCodeAppListBinding) ViewDataBinding.bind(obj, view, R.layout.layout_item_qr_code_app_list);
    }

    @NonNull
    @Deprecated
    public static LayoutItemQrCodeAppListBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (LayoutItemQrCodeAppListBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_item_qr_code_app_list, viewGroup, z, obj);
    }

    @NonNull
    public static LayoutItemQrCodeAppListBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static LayoutItemQrCodeAppListBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (LayoutItemQrCodeAppListBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_item_qr_code_app_list, null, false, obj);
    }
}
