package com.crrepa.q;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import com.crrepa.i0.c;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public BluetoothGattCharacteristic f7813a;
    public BluetoothGattCharacteristic b;
    public BluetoothGattCharacteristic c;
    public BluetoothGattCharacteristic d;
    public BluetoothGattCharacteristic e;
    public BluetoothGattCharacteristic f;
    public BluetoothGattCharacteristic g;
    public BluetoothGattCharacteristic h;
    public BluetoothGattCharacteristic i;
    public BluetoothGattCharacteristic j;
    public BluetoothGattCharacteristic k;
    public BluetoothGattCharacteristic l;

    public a(List<BluetoothGattService> list) {
        for (BluetoothGattService bluetoothGattService : list) {
            String lowerCase = bluetoothGattService.getUuid().toString().toLowerCase();
            c.c("serviceUuid: " + lowerCase);
            List<BluetoothGattCharacteristic> characteristics = bluetoothGattService.getCharacteristics();
            if (lowerCase.contains(com.crrepa.c.a.f7691a)) {
                for (BluetoothGattCharacteristic bluetoothGattCharacteristic : characteristics) {
                    String lowerCase2 = bluetoothGattCharacteristic.getUuid().toString().toLowerCase();
                    if (lowerCase2.contains(com.crrepa.c.a.e)) {
                        this.f7813a = bluetoothGattCharacteristic;
                    } else if (lowerCase2.contains(com.crrepa.c.a.f)) {
                        this.b = bluetoothGattCharacteristic;
                    } else if (lowerCase2.contains(com.crrepa.c.a.g)) {
                        this.c = bluetoothGattCharacteristic;
                    } else if (lowerCase2.contains(com.crrepa.c.a.m)) {
                        this.h = bluetoothGattCharacteristic;
                    } else if (lowerCase2.contains(com.crrepa.c.a.n)) {
                        this.i = bluetoothGattCharacteristic;
                    } else if (lowerCase2.contains(com.crrepa.c.a.o)) {
                        this.j = bluetoothGattCharacteristic;
                    } else if (lowerCase2.contains(com.crrepa.c.a.p)) {
                        this.k = bluetoothGattCharacteristic;
                    }
                }
            } else if (lowerCase.contains(com.crrepa.c.a.b)) {
                for (BluetoothGattCharacteristic bluetoothGattCharacteristic2 : characteristics) {
                    String lowerCase3 = bluetoothGattCharacteristic2.getUuid().toString().toLowerCase();
                    if (lowerCase3.contains(com.crrepa.c.a.i)) {
                        this.e = bluetoothGattCharacteristic2;
                    } else if (lowerCase3.contains(com.crrepa.c.a.j)) {
                        this.l = bluetoothGattCharacteristic2;
                    } else if (lowerCase3.contains(com.crrepa.c.a.k)) {
                        this.f = bluetoothGattCharacteristic2;
                    }
                }
            } else if (lowerCase.contains(com.crrepa.c.a.c)) {
                for (BluetoothGattCharacteristic bluetoothGattCharacteristic3 : characteristics) {
                    if (bluetoothGattCharacteristic3.getUuid().toString().toLowerCase().contains(com.crrepa.c.a.h)) {
                        this.d = bluetoothGattCharacteristic3;
                    }
                }
            } else if (lowerCase.contains(com.crrepa.c.a.d)) {
                for (BluetoothGattCharacteristic bluetoothGattCharacteristic4 : characteristics) {
                    if (bluetoothGattCharacteristic4.getUuid().toString().contains(com.crrepa.c.a.l)) {
                        this.g = bluetoothGattCharacteristic4;
                    }
                }
            }
        }
    }

    public BluetoothGattCharacteristic a() {
        return this.d;
    }

    public BluetoothGattCharacteristic b() {
        return this.e;
    }

    public BluetoothGattCharacteristic c() {
        return this.f;
    }

    public BluetoothGattCharacteristic d() {
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.j;
        return bluetoothGattCharacteristic != null ? bluetoothGattCharacteristic : this.k;
    }

    public BluetoothGattCharacteristic e() {
        return this.g;
    }

    public BluetoothGattCharacteristic f() {
        return this.c;
    }

    public List<BluetoothGattCharacteristic> g() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.c);
        arrayList.add(this.f7813a);
        arrayList.add(this.g);
        arrayList.add(this.j);
        arrayList.add(this.k);
        return arrayList;
    }

    public BluetoothGattCharacteristic h() {
        return this.l;
    }

    public BluetoothGattCharacteristic i() {
        return this.f7813a;
    }

    public BluetoothGattCharacteristic j() {
        return this.i;
    }

    public BluetoothGattCharacteristic k() {
        return this.h;
    }

    public BluetoothGattCharacteristic l() {
        return this.b;
    }

    public boolean m() {
        return this.k != null;
    }

    public boolean n() {
        return (this.f7813a == null || this.b == null || this.c == null) ? false : true;
    }
}
