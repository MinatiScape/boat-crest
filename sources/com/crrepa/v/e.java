package com.crrepa.v;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.UUID;
/* loaded from: classes9.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    public i f7848a;

    public e(i iVar) {
        this.f7848a = iVar;
    }

    public void a(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        UUID uuid = bluetoothGattCharacteristic.getUuid();
        com.crrepa.i0.c.c("onCharacteristicChanged: " + uuid.toString());
        this.f7848a.a(bluetoothGattCharacteristic.getValue(), com.crrepa.c.a.w.equals(uuid) ? 1 : 2);
    }
}
