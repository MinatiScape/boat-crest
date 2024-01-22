package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceRemoteConfig;
/* loaded from: classes3.dex */
public abstract class ListItemDeviceLayoutBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clMain;
    @NonNull
    public final Guideline glVertical;
    @NonNull
    public final ImageView ivDevice;
    @Bindable
    public DeviceRemoteConfig.DeviceModelsBean mDeviceData;
    @NonNull
    public final TextView tvDeviceName;

    public ListItemDeviceLayoutBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, Guideline guideline, ImageView imageView, TextView textView) {
        super(obj, view, i);
        this.clMain = constraintLayout;
        this.glVertical = guideline;
        this.ivDevice = imageView;
        this.tvDeviceName = textView;
    }

    public static ListItemDeviceLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ListItemDeviceLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public DeviceRemoteConfig.DeviceModelsBean getDeviceData() {
        return this.mDeviceData;
    }

    public abstract void setDeviceData(@Nullable DeviceRemoteConfig.DeviceModelsBean deviceModelsBean);

    @Deprecated
    public static ListItemDeviceLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ListItemDeviceLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.list_item_device_layout);
    }

    @NonNull
    @Deprecated
    public static ListItemDeviceLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ListItemDeviceLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_device_layout, viewGroup, z, obj);
    }

    @NonNull
    public static ListItemDeviceLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ListItemDeviceLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ListItemDeviceLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_device_layout, null, false, obj);
    }
}
