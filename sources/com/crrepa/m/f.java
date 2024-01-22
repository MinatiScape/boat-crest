package com.crrepa.m;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import com.crrepa.ble.conn.listener.CRPBleSendStateListener;
import com.crrepa.f.d1;
/* loaded from: classes9.dex */
public class f extends c {
    public static f f;

    /* renamed from: a  reason: collision with root package name */
    public byte[] f7755a = null;
    public boolean b = true;
    public int c = 0;
    public byte d;
    public CRPBleSendStateListener e;

    /* loaded from: classes9.dex */
    public class a implements Runnable {
        public a(f fVar) {
        }

        @Override // java.lang.Runnable
        public void run() {
            com.crrepa.p.c.b().f();
        }
    }

    public static f d() {
        if (f == null) {
            f = new f();
        }
        return f;
    }

    public void a(CRPBleSendStateListener cRPBleSendStateListener) {
        this.e = cRPBleSendStateListener;
    }

    public void a(byte[] bArr) {
        e(bArr, 0);
    }

    public void b(byte[] bArr) {
        e(bArr, 8);
    }

    public final void c() {
        long j;
        f();
        byte b = this.d;
        if (b == 1 || b == 2) {
            j = 0;
        } else {
            if (b != 59 && b != 60) {
                switch (b) {
                    case 50:
                    case 51:
                        com.crrepa.o.a.a().e();
                        return;
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                        break;
                    default:
                        j = 100;
                        break;
                }
            }
            j = 500;
        }
        c(1, j);
    }

    public final void c(int i, long j) {
        CRPBleSendStateListener cRPBleSendStateListener;
        if (this.d == 99 && (cRPBleSendStateListener = this.e) != null) {
            cRPBleSendStateListener.onSendStateChange(i);
        }
        com.crrepa.g.a.a(new a(this), j);
    }

    public void c(byte[] bArr) {
        e(bArr, 1);
    }

    public void d(byte[] bArr) {
        e(bArr, 2);
    }

    public final void d(byte[] bArr, byte b) {
        com.crrepa.i0.c.a("writeCompleted: " + this.b);
        if (this.b) {
            this.d = b;
            com.crrepa.i0.c.c("WriteCmd: " + ((int) this.d));
            this.f7755a = bArr;
            this.b = false;
            com.crrepa.o.a.a().d();
            i();
        }
    }

    public void e(byte[] bArr) {
        d(bArr, bArr[4]);
    }

    public final void e(byte[] bArr, int i) {
        if (bArr == null) {
            return;
        }
        com.crrepa.p.c.b().a(new com.crrepa.p.a(i, bArr));
    }

    public void f() {
        this.c = 0;
        this.b = true;
    }

    public void f(byte[] bArr) {
        d(bArr, (byte) 1);
    }

    public void g() {
        i();
    }

    public void g(byte[] bArr) {
        d(bArr, (byte) 2);
    }

    public final BluetoothGattCharacteristic h() {
        com.crrepa.q.a b = b();
        if (b == null) {
            return null;
        }
        byte b2 = this.d;
        return b2 == 1 ? b.k() : b2 == 2 ? b.j() : b.l();
    }

    public final synchronized void i() {
        int length = this.f7755a.length - this.c;
        int a2 = d1.a();
        if (length > a2) {
            length = a2;
        } else if (length <= 0) {
            c();
            return;
        }
        BluetoothGattCharacteristic h = h();
        BluetoothGatt a3 = com.crrepa.l.a.b().a();
        if (h != null && a3 != null) {
            byte[] bArr = new byte[length];
            System.arraycopy(this.f7755a, this.c, bArr, 0, length);
            h.setValue(bArr);
            if (com.crrepa.l.a.b().f()) {
                h.setWriteType(1);
            }
            com.crrepa.i0.c.c("writeCharacteristic WriteType: " + h.getWriteType());
            boolean writeCharacteristic = a3.writeCharacteristic(h);
            com.crrepa.i0.c.c("writeCharacteristic: " + writeCharacteristic);
            if (writeCharacteristic) {
                this.c += length;
            }
            return;
        }
        c.a();
    }
}
