package com.crrepa.a0;

import android.graphics.Bitmap;
import com.crrepa.ble.conn.listener.CRPFileTransListener;
import com.crrepa.f.d1;
import com.crrepa.m.f;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
/* loaded from: classes9.dex */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    public CRPFileTransListener f7633a;
    public byte[] b;
    public int d;
    public Bitmap[] i;
    public int c = 0;
    public int e = 30;
    public Timer f = new Timer();
    public int g = 0;
    public boolean h = false;
    public boolean j = false;

    /* renamed from: com.crrepa.a0.a$a  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public class RunnableC0335a implements Runnable {
        public RunnableC0335a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a aVar = a.this;
            byte[] b = aVar.b(aVar.h, a.this.i);
            if (b == null) {
                a.this.l(1);
                return;
            }
            a.this.v();
            a.this.o(b);
            a.this.d(b.length);
            a.this.y();
            a.this.x();
            a.this.n(true);
        }
    }

    /* loaded from: classes9.dex */
    public class b extends TimerTask {
        public b() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            com.crrepa.i0.c.a("timer run--------------");
            a.this.p();
        }
    }

    public a() {
        this.d = com.crrepa.l.a.b().g() ? com.crrepa.l.a.b().c() : 256;
    }

    public void a() {
        h();
        a(false);
    }

    public void a(CRPFileTransListener cRPFileTransListener) {
        this.f7633a = cRPFileTransListener;
    }

    public void a(com.crrepa.e0.a aVar) {
        if (!t()) {
            a();
            return;
        }
        int b2 = aVar.b();
        com.crrepa.i0.c.c("watch face trans offset: " + b2);
        if (b2 < 0) {
            return;
        }
        w();
        if (b2 == 65535) {
            b(aVar.a());
            return;
        }
        r(b2);
        g(b2);
    }

    public void a(boolean z) {
        byte[] bArr = new byte[4];
        if (z) {
            f();
        } else {
            Arrays.fill(bArr, (byte) -1);
        }
        a(d1.a(110, bArr));
    }

    public void a(byte[] bArr) {
        f.d().a(bArr);
    }

    public void a(Bitmap... bitmapArr) {
        this.i = bitmapArr;
    }

    public byte[] a(boolean z, Bitmap[] bitmapArr) {
        ArrayList<byte[]> arrayList = new ArrayList();
        int i = 0;
        for (Bitmap bitmap : bitmapArr) {
            if (bitmap != null) {
                byte[] f = com.crrepa.h0.a.a(bitmap, z).f();
                i += f.length;
                arrayList.add(f);
            }
        }
        if (i == 0) {
            return null;
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        for (byte[] bArr2 : arrayList) {
            int length = bArr2.length;
            System.arraycopy(bArr2, 0, bArr, i2, length);
            i2 = length;
        }
        return bArr;
    }

    public final void b(int i) {
        int k = k();
        com.crrepa.i0.c.c("transFileCrc: " + i);
        com.crrepa.i0.c.c("calcFileCrc: " + k);
        boolean z = i == k;
        a(z);
        if (z) {
            return;
        }
        l(3);
    }

    public void b(boolean z) {
        this.h = z;
    }

    public byte[] b() {
        return this.b;
    }

    public abstract byte[] b(boolean z, Bitmap[] bitmapArr);

    public void d(int i) {
        a(d1.a(110, com.crrepa.i0.e.a(i)));
    }

    public void f() {
        CRPFileTransListener cRPFileTransListener = this.f7633a;
        if (cRPFileTransListener != null) {
            cRPFileTransListener.onTransCompleted();
        }
        h();
    }

    public void f(int i) {
        this.e = i;
    }

    public final void g(int i) {
        int i2;
        CRPFileTransListener cRPFileTransListener = this.f7633a;
        if (cRPFileTransListener == null || (i2 = this.c) == 0) {
            return;
        }
        cRPFileTransListener.onTransProgressChanged((i * 100) / i2);
    }

    public void h() {
        this.b = null;
        y();
        n(false);
    }

    public final void h(byte[] bArr) {
        f.d().d(bArr);
    }

    public void j() {
        new Thread(new RunnableC0335a()).start();
    }

    public final int k() {
        byte[] a2 = com.crrepa.a0.b.a(b(), com.crrepa.a0.b.f7634a);
        return com.crrepa.i0.e.b(a2[0], a2[1]);
    }

    public final void l(int i) {
        CRPFileTransListener cRPFileTransListener = this.f7633a;
        if (cRPFileTransListener != null) {
            cRPFileTransListener.onError(i);
        }
        h();
    }

    public final void n(boolean z) {
        this.j = z;
    }

    public final void o(byte[] bArr) {
        this.b = bArr;
        this.c = (bArr.length / this.d) + 1;
    }

    public final synchronized void p() {
        int i = this.g;
        if (i < this.e) {
            this.g = i + 1;
        } else {
            l(2);
        }
    }

    public final void r(int i) {
        int i2 = this.d;
        int i3 = i * i2;
        int i4 = i2 + i3;
        byte[] b2 = b();
        if (b2 == null) {
            return;
        }
        int length = b2.length;
        if (length < i4) {
            i4 = length;
        }
        int i5 = i4 - i3;
        byte[] bArr = new byte[i5];
        System.arraycopy(b2, i3, bArr, 0, i5);
        h(d.a(bArr, this.d));
    }

    public final boolean t() {
        return this.j;
    }

    public final void v() {
        CRPFileTransListener cRPFileTransListener = this.f7633a;
        if (cRPFileTransListener != null) {
            cRPFileTransListener.onTransProgressStarting();
        }
    }

    public final synchronized void w() {
        this.g = 0;
    }

    public final void x() {
        w();
        if (this.f == null) {
            this.f = new Timer();
        }
        this.f.schedule(new b(), 1000L, 1000L);
    }

    public final void y() {
        Timer timer = this.f;
        if (timer != null) {
            timer.cancel();
            this.f = null;
        }
    }
}
