package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.bleabstract.models.BleDevice;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ListItemScannedBleDeviceLayoutBinding extends ViewDataBinding {
    @Bindable
    public BleDevice mDeviceInfo;
    @NonNull
    public final TextView tvDeviceName;

    public ListItemScannedBleDeviceLayoutBinding(Object obj, View view, int i, TextView textView) {
        super(obj, view, i);
        this.tvDeviceName = textView;
    }

    public static ListItemScannedBleDeviceLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ListItemScannedBleDeviceLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public BleDevice getDeviceInfo() {
        return this.mDeviceInfo;
    }

    public abstract void setDeviceInfo(@Nullable BleDevice bleDevice);

    @Deprecated
    public static ListItemScannedBleDeviceLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ListItemScannedBleDeviceLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.list_item_scanned_ble_device_layout);
    }

    @NonNull
    @Deprecated
    public static ListItemScannedBleDeviceLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ListItemScannedBleDeviceLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_scanned_ble_device_layout, viewGroup, z, obj);
    }

    @NonNull
    public static ListItemScannedBleDeviceLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ListItemScannedBleDeviceLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ListItemScannedBleDeviceLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_scanned_ble_device_layout, null, false, obj);
    }
}
