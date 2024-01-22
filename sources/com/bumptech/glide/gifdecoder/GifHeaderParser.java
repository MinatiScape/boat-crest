package com.bumptech.glide.gifdecoder;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
/* loaded from: classes.dex */
public class GifHeaderParser {
    public ByteBuffer b;
    public GifHeader c;

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f2319a = new byte[256];
    public int d = 0;

    public final boolean a() {
        return this.c.b != 0;
    }

    public final int b() {
        try {
            return this.b.get() & 255;
        } catch (Exception unused) {
            this.c.b = 1;
            return 0;
        }
    }

    public final void c() {
        this.c.d.f2321a = l();
        this.c.d.b = l();
        this.c.d.c = l();
        this.c.d.d = l();
        int b = b();
        boolean z = (b & 128) != 0;
        int pow = (int) Math.pow(2.0d, (b & 7) + 1);
        a aVar = this.c.d;
        aVar.e = (b & 64) != 0;
        if (z) {
            aVar.k = e(pow);
        } else {
            aVar.k = null;
        }
        this.c.d.j = this.b.position();
        o();
        if (a()) {
            return;
        }
        GifHeader gifHeader = this.c;
        gifHeader.c++;
        gifHeader.e.add(gifHeader.d);
    }

    public void clear() {
        this.b = null;
        this.c = null;
    }

    public final void d() {
        int b = b();
        this.d = b;
        if (b <= 0) {
            return;
        }
        int i = 0;
        int i2 = 0;
        while (true) {
            try {
                i2 = this.d;
                if (i >= i2) {
                    return;
                }
                i2 -= i;
                this.b.get(this.f2319a, i, i2);
                i += i2;
            } catch (Exception e) {
                if (Log.isLoggable("GifHeaderParser", 3)) {
                    Log.d("GifHeaderParser", "Error Reading Block n: " + i + " count: " + i2 + " blockSize: " + this.d, e);
                }
                this.c.b = 1;
                return;
            }
        }
    }

    @Nullable
    public final int[] e(int i) {
        byte[] bArr = new byte[i * 3];
        int[] iArr = null;
        try {
            this.b.get(bArr);
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
            if (Log.isLoggable("GifHeaderParser", 3)) {
                Log.d("GifHeaderParser", "Format Error Reading Color Table", e);
            }
            this.c.b = 1;
        }
        return iArr;
    }

    public final void f() {
        g(Integer.MAX_VALUE);
    }

    public final void g(int i) {
        boolean z = false;
        while (!z && !a() && this.c.c <= i) {
            int b = b();
            if (b == 33) {
                int b2 = b();
                if (b2 == 1) {
                    n();
                } else if (b2 == 249) {
                    this.c.d = new a();
                    h();
                } else if (b2 == 254) {
                    n();
                } else if (b2 != 255) {
                    n();
                } else {
                    d();
                    StringBuilder sb = new StringBuilder();
                    for (int i2 = 0; i2 < 11; i2++) {
                        sb.append((char) this.f2319a[i2]);
                    }
                    if (sb.toString().equals("NETSCAPE2.0")) {
                        k();
                    } else {
                        n();
                    }
                }
            } else if (b == 44) {
                GifHeader gifHeader = this.c;
                if (gifHeader.d == null) {
                    gifHeader.d = new a();
                }
                c();
            } else if (b != 59) {
                this.c.b = 1;
            } else {
                z = true;
            }
        }
    }

    public final void h() {
        b();
        int b = b();
        a aVar = this.c.d;
        int i = (b & 28) >> 2;
        aVar.g = i;
        if (i == 0) {
            aVar.g = 1;
        }
        aVar.f = (b & 1) != 0;
        int l = l();
        if (l < 2) {
            l = 10;
        }
        a aVar2 = this.c.d;
        aVar2.i = l * 10;
        aVar2.h = b();
        b();
    }

    public final void i() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append((char) b());
        }
        if (!sb.toString().startsWith("GIF")) {
            this.c.b = 1;
            return;
        }
        j();
        if (!this.c.h || a()) {
            return;
        }
        GifHeader gifHeader = this.c;
        gifHeader.f2318a = e(gifHeader.i);
        GifHeader gifHeader2 = this.c;
        gifHeader2.l = gifHeader2.f2318a[gifHeader2.j];
    }

    public boolean isAnimated() {
        i();
        if (!a()) {
            g(2);
        }
        return this.c.c > 1;
    }

    public final void j() {
        this.c.f = l();
        this.c.g = l();
        int b = b();
        GifHeader gifHeader = this.c;
        gifHeader.h = (b & 128) != 0;
        gifHeader.i = (int) Math.pow(2.0d, (b & 7) + 1);
        this.c.j = b();
        this.c.k = b();
    }

    public final void k() {
        do {
            d();
            byte[] bArr = this.f2319a;
            if (bArr[0] == 1) {
                this.c.m = ((bArr[2] & 255) << 8) | (bArr[1] & 255);
            }
            if (this.d <= 0) {
                return;
            }
        } while (!a());
    }

    public final int l() {
        return this.b.getShort();
    }

    public final void m() {
        this.b = null;
        Arrays.fill(this.f2319a, (byte) 0);
        this.c = new GifHeader();
        this.d = 0;
    }

    public final void n() {
        int b;
        do {
            b = b();
            this.b.position(Math.min(this.b.position() + b, this.b.limit()));
        } while (b > 0);
    }

    public final void o() {
        b();
        n();
    }

    @NonNull
    public GifHeader parseHeader() {
        if (this.b != null) {
            if (a()) {
                return this.c;
            }
            i();
            if (!a()) {
                f();
                GifHeader gifHeader = this.c;
                if (gifHeader.c < 0) {
                    gifHeader.b = 1;
                }
            }
            return this.c;
        }
        throw new IllegalStateException("You must call setData() before parseHeader()");
    }

    public GifHeaderParser setData(@NonNull ByteBuffer byteBuffer) {
        m();
        ByteBuffer asReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        this.b = asReadOnlyBuffer;
        asReadOnlyBuffer.position(0);
        this.b.order(ByteOrder.LITTLE_ENDIAN);
        return this;
    }

    public GifHeaderParser setData(@Nullable byte[] bArr) {
        if (bArr != null) {
            setData(ByteBuffer.wrap(bArr));
        } else {
            this.b = null;
            this.c.b = 2;
        }
        return this;
    }
}
