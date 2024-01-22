package com.polidea.rxandroidble2.internal.connection;

import android.bluetooth.BluetoothGatt;
import androidx.annotation.NonNull;
import java.util.concurrent.atomic.AtomicReference;
@ConnectionScope
/* loaded from: classes12.dex */
public class BluetoothGattProvider {

    /* renamed from: a  reason: collision with root package name */
    public final AtomicReference<BluetoothGatt> f13398a = new AtomicReference<>();

    public BluetoothGatt getBluetoothGatt() {
        return this.f13398a.get();
    }

    public void updateBluetoothGatt(@NonNull BluetoothGatt bluetoothGatt) {
        this.f13398a.compareAndSet(null, bluetoothGatt);
    }
}
