package com.goodix.ble.libble.v2.impl.data;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes5.dex */
public class BleValue {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public BluetoothGatt f8021a;
    @NonNull
    public BluetoothGattCharacteristic b;
    @Nullable
    public byte[] c;
    public int d;

    public BleValue(@NonNull BluetoothGatt bluetoothGatt, @NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        this.f8021a = bluetoothGatt;
        this.b = bluetoothGattCharacteristic;
        this.c = bluetoothGattCharacteristic.getValue();
    }

    public BleValue(@NonNull BluetoothGatt bluetoothGatt, @NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        this.f8021a = bluetoothGatt;
        this.b = bluetoothGattCharacteristic;
        this.c = bluetoothGattCharacteristic.getValue();
        this.d = i;
    }

    public BleValue(@NonNull BluetoothGatt bluetoothGatt, @NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic, @Nullable byte[] bArr, int i) {
        this.f8021a = bluetoothGatt;
        this.b = bluetoothGattCharacteristic;
        this.c = bArr;
        this.d = i;
    }

    @NonNull
    public BluetoothGattCharacteristic getCharacteristic() {
        return this.b;
    }

    @Nullable
    public byte[] getData() {
        return this.c;
    }

    @NonNull
    public BluetoothGatt getGatt() {
        return this.f8021a;
    }

    public int getStatus() {
        return this.d;
    }
}
