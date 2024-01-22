package com.crrepa.m;

import android.bluetooth.BluetoothGattCharacteristic;
import com.crrepa.ble.conn.listener.CRPStepChangeListener;
import com.crrepa.f.m;
import com.crrepa.f.n;
import com.crrepa.j.t;
/* loaded from: classes9.dex */
public class e extends c {

    /* renamed from: a  reason: collision with root package name */
    public CRPStepChangeListener f7754a;

    public void a(int i) {
        BluetoothGattCharacteristic i2;
        com.crrepa.q.a b = b();
        if (b != null) {
            switch (i) {
                case 16:
                    i2 = b.i();
                    d(i2);
                    return;
                case 17:
                    i2 = b.b();
                    d(i2);
                    return;
                case 18:
                    i2 = b.a();
                    d(i2);
                    return;
                case 19:
                    i2 = b.c();
                    if (i2 == null) {
                        com.crrepa.d.b.a().a(2);
                    }
                    d(i2);
                    return;
                case 20:
                    i2 = b.h();
                    d(i2);
                    return;
            }
        }
        com.crrepa.p.c.b().f();
    }

    public void a(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        com.crrepa.p.c.b().f();
        byte[] value = bluetoothGattCharacteristic.getValue();
        if (com.crrepa.i0.e.e(value)) {
            return;
        }
        String uuid = bluetoothGattCharacteristic.getUuid().toString();
        if (uuid.contains(com.crrepa.c.a.i)) {
            b(value);
        } else if (uuid.contains(com.crrepa.c.a.h)) {
            a(value);
        } else if (uuid.contains(com.crrepa.c.a.e)) {
            e(value);
        } else if (uuid.contains(com.crrepa.c.a.k)) {
            c(value);
        } else if (uuid.contains(com.crrepa.c.a.j)) {
            d(value);
        }
    }

    public void a(CRPStepChangeListener cRPStepChangeListener) {
        this.f7754a = cRPStepChangeListener;
    }

    public final void a(byte[] bArr) {
        com.crrepa.n.a.a().a(bArr[0]);
    }

    public final void b(byte[] bArr) {
        String str = new String(bArr);
        com.crrepa.i0.f.a(str);
        com.crrepa.d.c.a().a(str);
    }

    public void c() {
        c((byte) 18);
    }

    public final void c(byte b) {
        com.crrepa.p.c.b().a(new com.crrepa.p.a(3, new byte[]{b}));
    }

    public final void c(byte[] bArr) {
        com.crrepa.d.b.a().a(m.a(bArr));
    }

    public void d() {
        c((byte) 19);
    }

    public final void d(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic == null) {
            com.crrepa.p.c.b().f();
            return;
        }
        boolean readCharacteristic = com.crrepa.l.a.b().a().readCharacteristic(bluetoothGattCharacteristic);
        com.crrepa.i0.c.a("readCharacteristic: " + readCharacteristic);
        if (readCharacteristic) {
            return;
        }
        c.a();
    }

    public final void d(byte[] bArr) {
        com.crrepa.d.e.a().a(new String(bArr));
    }

    public void e() {
        c((byte) 17);
    }

    public final void e(byte[] bArr) {
        if (this.f7754a != null) {
            this.f7754a.onStepChange(t.a(bArr));
        }
    }

    public void f() {
        c((byte) 20);
        com.crrepa.p.c.b().a(new com.crrepa.p.a(0, n.c()));
    }

    public void g() {
        c((byte) 16);
    }
}
