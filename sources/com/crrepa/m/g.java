package com.crrepa.m;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import java.util.Collections;
import java.util.List;
/* loaded from: classes9.dex */
public class g extends c {

    /* renamed from: a  reason: collision with root package name */
    public List<BluetoothGattCharacteristic> f7756a;
    public boolean b = false;

    public static boolean a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        BluetoothGattDescriptor descriptor;
        boolean z = false;
        if (bluetoothGatt != null && bluetoothGattCharacteristic != null && (bluetoothGattCharacteristic.getProperties() | 16) > 0) {
            if (bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, true) && (descriptor = bluetoothGattCharacteristic.getDescriptor(com.crrepa.c.a.q)) != null) {
                descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                z = bluetoothGatt.writeDescriptor(descriptor);
            }
            if (!z) {
                c.a();
            }
        }
        return z;
    }

    public void a(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        List<BluetoothGattCharacteristic> list = this.f7756a;
        if (list != null) {
            list.remove(bluetoothGattCharacteristic);
        }
    }

    public void a(List<BluetoothGattCharacteristic> list) {
        this.f7756a = list;
        list.removeAll(Collections.singleton(null));
        this.b = false;
    }

    public void a(boolean z) {
        this.b = z;
    }

    public boolean a(BluetoothGatt bluetoothGatt) {
        boolean z;
        if (this.f7756a.isEmpty()) {
            z = true;
        } else {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = this.f7756a.get(0);
            com.crrepa.i0.c.a("enableNotifyCharacteristic: " + bluetoothGattCharacteristic.getUuid());
            z = a(bluetoothGatt, bluetoothGattCharacteristic);
        }
        return z && this.f7756a.isEmpty();
    }

    public boolean c() {
        return this.b;
    }
}
