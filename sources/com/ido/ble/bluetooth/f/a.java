package com.ido.ble.bluetooth.f;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import java.util.Objects;
import java.util.UUID;
/* loaded from: classes11.dex */
public class a {
    public static BluetoothGattCharacteristic a(BluetoothGatt bluetoothGatt) {
        return a(bluetoothGatt, f.h, f.k);
    }

    private static BluetoothGattCharacteristic a(BluetoothGatt bluetoothGatt, UUID uuid, UUID uuid2) {
        BluetoothGattService service;
        if (bluetoothGatt == null || (service = bluetoothGatt.getService(uuid)) == null) {
            return null;
        }
        return service.getCharacteristic(uuid2);
    }

    private boolean a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGatt == null || bluetoothGattCharacteristic == null || (bluetoothGattCharacteristic.getProperties() & 32) == 0) {
            return false;
        }
        bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, true);
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(f.c);
        if (descriptor != null) {
            descriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
            return bluetoothGatt.writeDescriptor(descriptor);
        }
        return false;
    }

    private static boolean a(BluetoothGatt bluetoothGatt, UUID uuid, boolean z) {
        BluetoothGattDescriptor descriptor;
        BluetoothGattCharacteristic a2 = a(bluetoothGatt, f.h, uuid);
        if (a2 == null || (a2.getProperties() | 16) <= 0) {
            return false;
        }
        bluetoothGatt.setCharacteristicNotification(a2, z);
        Objects.toString(uuid);
        if (!uuid.equals(a2.getUuid()) || (descriptor = a2.getDescriptor(f.c)) == null) {
            return false;
        }
        descriptor.setValue(z ? BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE : BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
        return bluetoothGatt.writeDescriptor(descriptor);
    }

    public static boolean a(BluetoothGatt bluetoothGatt, boolean z) {
        return a(bluetoothGatt, f.l, z);
    }

    public static BluetoothGattCharacteristic b(BluetoothGatt bluetoothGatt) {
        return a(bluetoothGatt, f.h, f.i);
    }

    private boolean b(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGatt == null || bluetoothGattCharacteristic == null || (bluetoothGattCharacteristic.getProperties() & 16) == 0) {
            return false;
        }
        bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, true);
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(f.c);
        if (descriptor != null) {
            descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            return bluetoothGatt.writeDescriptor(descriptor);
        }
        return false;
    }

    public static boolean b(BluetoothGatt bluetoothGatt, boolean z) {
        return a(bluetoothGatt, f.j, z);
    }

    public static BluetoothGattCharacteristic c(BluetoothGatt bluetoothGatt) {
        return a(bluetoothGatt, f.h, f.b);
    }

    public static boolean c(BluetoothGatt bluetoothGatt, boolean z) {
        return a(bluetoothGatt, f.b, z);
    }
}
