package com.crrepa.v;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/* loaded from: classes9.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    public BluetoothGattCharacteristic f7849a;
    public BluetoothGattCharacteristic b;
    public BluetoothGattCharacteristic c;
    public BluetoothGattCharacteristic d;
    public BluetoothGattCharacteristic e;
    public BluetoothGattCharacteristic f;

    public f(List<BluetoothGattService> list) {
        for (BluetoothGattService bluetoothGattService : list) {
            for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
                UUID uuid = bluetoothGattCharacteristic.getUuid();
                com.crrepa.i0.c.c("uuid: " + uuid.toString());
                if (com.crrepa.c.a.w.equals(uuid)) {
                    this.f7849a = bluetoothGattCharacteristic;
                } else if (com.crrepa.c.a.x.equals(uuid)) {
                    this.b = bluetoothGattCharacteristic;
                } else if (com.crrepa.c.a.v.equals(uuid)) {
                    this.c = bluetoothGattCharacteristic;
                } else if (com.crrepa.c.a.s.equals(uuid)) {
                    this.d = bluetoothGattCharacteristic;
                } else if (com.crrepa.c.a.t.equals(uuid)) {
                    this.e = bluetoothGattCharacteristic;
                } else if (com.crrepa.c.a.u.equals(uuid)) {
                    this.f = bluetoothGattCharacteristic;
                }
            }
        }
    }

    public List<BluetoothGattCharacteristic> a() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.f7849a);
        arrayList.add(this.b);
        return arrayList;
    }

    public BluetoothGattCharacteristic b() {
        return this.f7849a;
    }

    public BluetoothGattCharacteristic c() {
        return this.b;
    }

    public BluetoothGattCharacteristic d() {
        return this.c;
    }

    public BluetoothGattCharacteristic e() {
        return this.d;
    }

    public BluetoothGattCharacteristic f() {
        return this.e;
    }

    public BluetoothGattCharacteristic g() {
        return this.f;
    }

    public boolean h() {
        return (this.f7849a == null || this.b == null || this.d == null || this.e == null) ? false : true;
    }
}
