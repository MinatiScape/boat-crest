package com.clevertap.android.sdk.gif;

import androidx.core.view.ViewCompat;
import com.clevertap.android.sdk.Logger;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
/* loaded from: classes2.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f2616a = new byte[256];
    public int b = 0;
    public c c;
    public ByteBuffer d;

    public final boolean a() {
        return this.c.l != 0;
    }

    public c b() {
        if (this.d != null) {
            if (a()) {
                return this.c;
            }
            j();
            if (!a()) {
                g();
                c cVar = this.c;
                if (cVar.d < 0) {
                    cVar.l = 1;
                }
            }
            return this.c;
        }
        throw new IllegalStateException("You must call setData() before parseHeader()");
    }

    public final int c() {
        try {
            return this.d.get() & 255;
        } catch (Exception unused) {
            this.c.l = 1;
            return 0;
        }
    }

    public final void d() {
        this.c.c.e = m();
        this.c.c.f = m();
        this.c.c.g = m();
        this.c.c.h = m();
        int c = c();
        boolean z = (c & 128) != 0;
        int pow = (int) Math.pow(2.0d, (c & 7) + 1);
        b bVar = this.c.c;
        bVar.d = (c & 64) != 0;
        if (z) {
            bVar.i = f(pow);
        } else {
            bVar.i = null;
        }
        this.c.c.f2614a = this.d.position();
        r();
        if (a()) {
            return;
        }
        c cVar = this.c;
        cVar.d++;
        cVar.e.add(cVar.c);
    }

    public final int e() {
        int c = c();
        this.b = c;
        int i = 0;
        if (c > 0) {
            while (true) {
                try {
                    int i2 = this.b;
                    if (i >= i2) {
                        break;
                    }
                    int i3 = i2 - i;
                    this.d.get(this.f2616a, i, i3);
                    i += i3;
                } catch (Exception unused) {
                    this.c.l = 1;
                }
            }
        }
        return i;
    }

    public final int[] f(int i) {
        byte[] bArr = new byte[i * 3];
        int[] iArr = null;
        try {
            this.d.get(bArr);
            iArr = new int[256];
            int i2 = 0;
            int i3 = 0;
            while (i2 < i) {
                int i4 = i3 + 1;
                int i5 = i4 + 1;
                int i6 = i5 + 1;
                int i7 = i2 + 1;
                iArr[i2] = ((bArr[i3] & 255) << 16) | ViewCompat.MEASURED_STATE_MASK | ((bArr[i4] & 255) << 8) | (bArr[i5] & 255);
                i3 = i6;
                i2 = i7;
            }
        } catch (BufferUnderflowException e) {
            Logger.d("GifHeaderParser", "Format Error Reading Color Table", e);
            this.c.l = 1;
        }
        return iArr;
    }

    public final void g() {
        h(Integer.MAX_VALUE);
    }

    public final void h(int i) {
        boolean z = false;
        while (!z && !a() && this.c.d <= i) {
            int c = c();
            if (c == 33) {
                int c2 = c();
                if (c2 == 1) {
                    q();
                } else if (c2 == 249) {
                    this.c.c = new b();
                    i();
                } else if (c2 == 254) {
                    q();
                } else if (c2 != 255) {
                    q();
                } else {
                    e();
                    String str = "";
                    for (int i2 = 0; i2 < 11; i2++) {
                        str = str + ((char) this.f2616a[i2]);
                    }
                    if (str.equals("NETSCAPE2.0")) {
                        l();
                    } else {
                        q();
                    }
                }
            } else if (c == 44) {
                c cVar = this.c;
                if (cVar.c == null) {
                    cVar.c = new b();
                }
                d();
            } else if (c != 59) {
                this.c.l = 1;
            } else {
                z = true;
            }
        }
    }

    public final void i() {
        c();
        int c = c();
        b bVar = this.c.c;
        int i = (c & 28) >> 2;
        bVar.c = i;
        if (i == 0) {
            bVar.c = 1;
        }
        bVar.k = (c & 1) != 0;
        int m = m();
        if (m < 2) {
            m = 10;
        }
        b bVar2 = this.c.c;
        bVar2.b = m * 10;
        bVar2.j = c();
        c();
    }

    public final void j() {
        String str = "";
        for (int i = 0; i < 6; i++) {
            str = str + ((char) c());
        }
        if (!str.startsWith("GIF")) {
            this.c.l = 1;
            return;
        }
        k();
        if (!this.c.g || a()) {
            return;
        }
        c cVar = this.c;
        cVar.f = f(cVar.h);
        c cVar2 = this.c;
        cVar2.f2615a = cVar2.f[cVar2.b];
    }

    public final void k() {
        this.c.m = m();
        this.c.i = m();
        int c = c();
        c cVar = this.c;
        cVar.g = (c & 128) != 0;
        cVar.h = 2 << (c & 7);
        cVar.b = c();
        this.c.k = c();
    }

    public final void l() {
        do {
            e();
            byte[] bArr = this.f2616a;
            if (bArr[0] == 1) {
                c cVar = this.c;
                int i = ((bArr[2] & 255) << 8) | (bArr[1] & 255);
                cVar.j = i;
                if (i == 0) {
                    cVar.j = -1;
                }
            }
            if (this.b <= 0) {
                return;
            }
        } while (!a());
    }

    public final int m() {
        return this.d.getShort();
    }

    public final void n() {
        this.d = null;
        Arrays.fill(this.f2616a, (byte) 0);
        this.c = new c();
        this.b = 0;
    }

    public d o(ByteBuffer byteBuffer) {
        n();
        ByteBuffer asReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        this.d = asReadOnlyBuffer;
        asReadOnlyBuffer.position(0);
        this.d.order(ByteOrder.LITTLE_ENDIAN);
        return this;
    }

    public d p(byte[] bArr) {
        if (bArr != null) {
            o(ByteBuffer.wrap(bArr));
        } else {
            this.d = null;
            this.c.l = 2;
        }
        return this;
    }

    public final void q() {
        int c;
        do {
            try {
                c = c();
                ByteBuffer byteBuffer = this.d;
                byteBuffer.position(byteBuffer.position() + c);
            } catch (IllegalArgumentException unused) {
                return;
            }
        } while (c > 0);
    }

    public final void r() {
        c();
        q();
    }
}
