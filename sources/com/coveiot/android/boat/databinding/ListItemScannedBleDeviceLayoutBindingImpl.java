package com.coveiot.android.boat.databinding;

import android.bluetooth.BluetoothDevice;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.coveiot.android.bleabstract.models.BleDevice;
/* loaded from: classes3.dex */
public class ListItemScannedBleDeviceLayoutBindingImpl extends ListItemScannedBleDeviceLayoutBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j = null;
    @Nullable
    public static final SparseIntArray k = null;
    @NonNull
    public final ConstraintLayout h;
    public long i;

    public ListItemScannedBleDeviceLayoutBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 2, j, k));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        synchronized (this) {
            j2 = this.i;
            this.i = 0L;
        }
        BleDevice bleDevice = this.mDeviceInfo;
        int i = ((j2 & 3) > 0L ? 1 : ((j2 & 3) == 0L ? 0 : -1));
        String str = null;
        if (i != 0) {
            BluetoothDevice bluetoothDevice = bleDevice != null ? bleDevice.getmDevice() : null;
            if (bluetoothDevice != null) {
                str = bluetoothDevice.getName();
            }
        }
        if (i != 0) {
            TextViewBindingAdapter.setText(this.tvDeviceName, str);
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.i != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.i = 2L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // com.coveiot.android.boat.databinding.ListItemScannedBleDeviceLayoutBinding
    public void setDeviceInfo(@Nullable BleDevice bleDevice) {
        this.mDeviceInfo = bleDevice;
        synchronized (this) {
            this.i |= 1;
        }
        notifyPropertyChanged(28);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (28 == i) {
            setDeviceInfo((BleDevice) obj);
            return true;
        }
        return false;
    }

    public ListItemScannedBleDeviceLayoutBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (TextView) objArr[1]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        this.tvDeviceName.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
