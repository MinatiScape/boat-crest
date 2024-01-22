package com.crrepa.v;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
/* loaded from: classes9.dex */
public class a extends g {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f7844a;
    public int b;
    public boolean c;
    public boolean d;

    /* renamed from: com.crrepa.v.a$a  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public class RunnableC0358a implements Runnable {
        public RunnableC0358a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.i();
            com.crrepa.p.c.b().f();
        }
    }

    /* loaded from: classes9.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final a f7845a = new a(null);
    }

    public a() {
        this.f7844a = null;
        this.b = 0;
        this.c = true;
        this.d = false;
    }

    public /* synthetic */ a(RunnableC0358a runnableC0358a) {
        this();
    }

    public static a d() {
        return b.f7845a;
    }

    public void a(byte[] bArr) {
        e(bArr, 6);
    }

    public void b(byte[] bArr) {
        e(bArr, 7);
    }

    public final BluetoothGattCharacteristic c(boolean z) {
        f b2 = b();
        if (b2 == null) {
            return null;
        }
        return z ? b2.e() : b2.f();
    }

    public void c(byte[] bArr) {
        f(bArr, true);
    }

    public void d(byte[] bArr) {
        f(bArr, false);
    }

    public final void e(byte[] bArr, int i) {
        if (bArr == null) {
            return;
        }
        com.crrepa.p.c.b().a(new com.crrepa.p.a(i, bArr));
    }

    public void f() {
        j();
    }

    public final void f(byte[] bArr, boolean z) {
        com.crrepa.i0.c.c("sendBleMessage: " + this.c);
        if (this.c) {
            this.f7844a = bArr;
            this.c = false;
            this.d = z;
            j();
        }
    }

    public void g() {
        i();
    }

    public final void h() {
        com.crrepa.g.a.a(new RunnableC0358a(), 50L);
    }

    public final void i() {
        this.b = 0;
        this.c = true;
    }

    public final synchronized void j() {
        int length = this.f7844a.length - this.b;
        if (length > 20) {
            length = 20;
        } else if (length <= 0) {
            h();
            return;
        }
        BluetoothGattCharacteristic c = c(this.d);
        com.crrepa.i0.c.c("characteristic uuid: " + c.getUuid().toString());
        BluetoothGatt d = com.crrepa.l.a.b().d();
        if (d == null) {
            g.a();
            return;
        }
        byte[] bArr = new byte[length];
        System.arraycopy(this.f7844a, this.b, bArr, 0, length);
        c.setValue(bArr);
        c.setWriteType(1);
        com.crrepa.i0.c.c("characteristic write data: " + com.crrepa.i0.e.c(bArr));
        boolean writeCharacteristic = d.writeCharacteristic(c);
        com.crrepa.i0.c.c("characteristic write success: " + writeCharacteristic);
        if (writeCharacteristic) {
            this.b += length;
        }
    }
}
