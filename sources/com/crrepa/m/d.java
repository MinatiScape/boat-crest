package com.crrepa.m;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
/* loaded from: classes9.dex */
public class d extends c {

    /* loaded from: classes9.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static d f7753a = new d();
    }

    public d() {
    }

    public static d c() {
        return b.f7753a;
    }

    public void a(int i) {
        com.crrepa.q.a b2 = b();
        if (b2 != null) {
            BluetoothGattCharacteristic a2 = i == 32 ? b2.a() : null;
            if (a2 != null) {
                if (g.a(com.crrepa.l.a.b().a(), a2)) {
                    return;
                }
                com.crrepa.n.a.a().a(false);
                c.a();
                return;
            }
        }
        com.crrepa.p.c.b().f();
    }

    public void a(BluetoothGattDescriptor bluetoothGattDescriptor) {
        com.crrepa.p.c.b().f();
        if (bluetoothGattDescriptor.getCharacteristic().getUuid().toString().toLowerCase().contains(com.crrepa.c.a.h)) {
            com.crrepa.n.a.a().a(true);
        }
    }
}
