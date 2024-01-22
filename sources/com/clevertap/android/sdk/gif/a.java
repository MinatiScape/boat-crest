package com.clevertap.android.sdk.gif;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Logger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;
/* loaded from: classes2.dex */
public class a {
    public static final String y = "a";

    /* renamed from: a  reason: collision with root package name */
    public int[] f2613a;
    public final InterfaceC0226a b;
    public byte[] c;
    public int d;
    public int e;
    public int f;
    public c g;
    public boolean h;
    public int i;
    public byte[] j;
    public int[] k;
    public d l;
    public final int[] m;
    public byte[] n;
    public short[] o;
    public Bitmap p;
    public ByteBuffer q;
    public int r;
    public boolean s;
    public int t;
    public byte[] u;
    @Nullable
    public byte[] v;
    public int w;
    public int x;

    /* renamed from: com.clevertap.android.sdk.gif.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public interface InterfaceC0226a {
        @NonNull
        Bitmap obtain(int i, int i2, Bitmap.Config config);

        byte[] obtainByteArray(int i);

        int[] obtainIntArray(int i);
    }

    public a(InterfaceC0226a interfaceC0226a) {
        this.m = new int[256];
        this.w = 0;
        this.x = 0;
        this.b = interfaceC0226a;
        this.g = new c();
    }

    @TargetApi(12)
    public static void s(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 12) {
            bitmap.setHasAlpha(true);
        }
    }

    public boolean a() {
        if (this.g.d <= 0) {
            return false;
        }
        if (this.f == g() - 1) {
            this.i++;
        }
        c cVar = this.g;
        int i = cVar.j;
        if (i == -1 || this.i <= i) {
            this.f = (this.f + 1) % cVar.d;
            return true;
        }
        return false;
    }

    public final int b(int i, int i2, int i3) {
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        for (int i9 = i; i9 < this.r + i; i9++) {
            byte[] bArr = this.j;
            if (i9 >= bArr.length || i9 >= i2) {
                break;
            }
            int i10 = this.f2613a[bArr[i9] & 255];
            if (i10 != 0) {
                i4 += (i10 >> 24) & 255;
                i5 += (i10 >> 16) & 255;
                i6 += (i10 >> 8) & 255;
                i7 += i10 & 255;
                i8++;
            }
        }
        int i11 = i + i3;
        for (int i12 = i11; i12 < this.r + i11; i12++) {
            byte[] bArr2 = this.j;
            if (i12 >= bArr2.length || i12 >= i2) {
                break;
            }
            int i13 = this.f2613a[bArr2[i12] & 255];
            if (i13 != 0) {
                i4 += (i13 >> 24) & 255;
                i5 += (i13 >> 16) & 255;
                i6 += (i13 >> 8) & 255;
                i7 += i13 & 255;
                i8++;
            }
        }
        if (i8 == 0) {
            return 0;
        }
        return ((i4 / i8) << 24) | ((i5 / i8) << 16) | ((i6 / i8) << 8) | (i7 / i8);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void c(b bVar) {
        int i;
        int i2;
        int i3;
        int i4;
        short s;
        this.x = 0;
        this.w = 0;
        if (bVar != null) {
            this.q.position(bVar.f2614a);
        }
        if (bVar == null) {
            c cVar = this.g;
            i = cVar.m;
            i2 = cVar.i;
        } else {
            i = bVar.g;
            i2 = bVar.h;
        }
        int i5 = i * i2;
        byte[] bArr = this.j;
        if (bArr == null || bArr.length < i5) {
            this.j = this.b.obtainByteArray(i5);
        }
        if (this.o == null) {
            this.o = new short[4096];
        }
        if (this.u == null) {
            this.u = new byte[4096];
        }
        if (this.n == null) {
            this.n = new byte[4097];
        }
        int p = p();
        int i6 = 1;
        int i7 = 1 << p;
        int i8 = i7 + 1;
        int i9 = i7 + 2;
        int i10 = p + 1;
        int i11 = (1 << i10) - 1;
        for (int i12 = 0; i12 < i7; i12++) {
            this.o[i12] = 0;
            this.u[i12] = (byte) i12;
        }
        int i13 = -1;
        int i14 = i10;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        int i21 = 0;
        int i22 = 0;
        int i23 = i9;
        int i24 = i11;
        int i25 = -1;
        while (true) {
            if (i15 >= i5) {
                break;
            }
            if (i16 == 0) {
                i16 = o();
                if (i16 <= 0) {
                    this.t = 3;
                    break;
                }
                i17 = 0;
            }
            i19 += (this.c[i17] & 255) << i18;
            i18 += 8;
            i17 += i6;
            i16 += i13;
            int i26 = i23;
            int i27 = i14;
            int i28 = i25;
            int i29 = i21;
            while (i18 >= i27) {
                int i30 = i19 & i24;
                i19 >>= i27;
                i18 -= i27;
                if (i30 != i7) {
                    if (i30 > i26) {
                        i3 = i10;
                        this.t = 3;
                    } else {
                        i3 = i10;
                        if (i30 != i8) {
                            if (i28 == -1) {
                                this.n[i22] = this.u[i30];
                                i28 = i30;
                                i29 = i28;
                                i22++;
                                i10 = i3;
                            } else {
                                if (i30 >= i26) {
                                    i4 = i30;
                                    this.n[i22] = (byte) i29;
                                    s = i28;
                                    i22++;
                                } else {
                                    i4 = i30;
                                    s = i4;
                                }
                                while (s >= i7) {
                                    this.n[i22] = this.u[s];
                                    s = this.o[s];
                                    i22++;
                                    i7 = i7;
                                }
                                int i31 = i7;
                                byte[] bArr2 = this.u;
                                int i32 = bArr2[s] & 255;
                                int i33 = i22 + 1;
                                int i34 = i8;
                                byte b = (byte) i32;
                                this.n[i22] = b;
                                if (i26 < 4096) {
                                    this.o[i26] = (short) i28;
                                    bArr2[i26] = b;
                                    i26++;
                                    if ((i26 & i24) == 0 && i26 < 4096) {
                                        i27++;
                                        i24 += i26;
                                    }
                                }
                                i22 = i33;
                                while (i22 > 0) {
                                    i22--;
                                    this.j[i20] = this.n[i22];
                                    i15++;
                                    i20++;
                                }
                                i7 = i31;
                                i28 = i4;
                                i8 = i34;
                                i29 = i32;
                                i10 = i3;
                            }
                        }
                    }
                    i23 = i26;
                    i14 = i27;
                    i25 = i28;
                    i10 = i3;
                    i21 = i29;
                    break;
                }
                i27 = i10;
                i26 = i9;
                i24 = i11;
                i28 = -1;
            }
            i21 = i29;
            i23 = i26;
            i14 = i27;
            i25 = i28;
            i6 = 1;
            i13 = -1;
        }
        for (int i35 = i20; i35 < i5; i35++) {
            this.j[i35] = 0;
        }
    }

    public final void d(int[] iArr, b bVar, int i) {
        int i2 = bVar.h;
        int i3 = this.r;
        int i4 = i2 / i3;
        int i5 = bVar.f / i3;
        int i6 = bVar.g / i3;
        int i7 = bVar.e / i3;
        int i8 = this.e;
        int i9 = (i5 * i8) + i7;
        int i10 = (i4 * i8) + i9;
        while (i9 < i10) {
            int i11 = i9 + i6;
            for (int i12 = i9; i12 < i11; i12++) {
                iArr[i12] = i;
            }
            i9 += this.e;
        }
    }

    public int e() {
        return this.f;
    }

    public int f(int i) {
        if (i >= 0) {
            c cVar = this.g;
            if (i < cVar.d) {
                return cVar.e.get(i).b;
            }
        }
        return -1;
    }

    public int g() {
        return this.g.d;
    }

    public final d h() {
        if (this.l == null) {
            this.l = new d();
        }
        return this.l;
    }

    public int i() {
        return this.g.i;
    }

    public final Bitmap j() {
        Bitmap obtain = this.b.obtain(this.e, this.d, this.h ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        s(obtain);
        return obtain;
    }

    public int k() {
        int i;
        if (this.g.d <= 0 || (i = this.f) < 0) {
            return 0;
        }
        return f(i);
    }

    public synchronized Bitmap l() {
        if (this.g.d <= 0 || this.f < 0) {
            String str = y;
            Logger.d(str, "unable to decode frame, frameCount=" + this.g.d + " framePointer=" + this.f);
            this.t = 1;
        }
        int i = this.t;
        if (i != 1 && i != 2) {
            this.t = 0;
            b bVar = this.g.e.get(this.f);
            int i2 = this.f - 1;
            b bVar2 = i2 >= 0 ? this.g.e.get(i2) : null;
            int[] iArr = bVar.i;
            if (iArr == null) {
                iArr = this.g.f;
            }
            this.f2613a = iArr;
            if (iArr == null) {
                String str2 = y;
                Logger.d(str2, "No Valid Color Table for frame #" + this.f);
                this.t = 1;
                return null;
            }
            if (bVar.k) {
                System.arraycopy(iArr, 0, this.m, 0, iArr.length);
                int[] iArr2 = this.m;
                this.f2613a = iArr2;
                iArr2[bVar.j] = 0;
            }
            return x(bVar, bVar2);
        }
        String str3 = y;
        Logger.d(str3, "Unable to decode frame, status=" + this.t);
        return null;
    }

    public int m() {
        return this.g.m;
    }

    public synchronized int n(byte[] bArr) {
        c b = h().p(bArr).b();
        this.g = b;
        if (bArr != null) {
            v(b, bArr);
        }
        return this.t;
    }

    public final int o() {
        int p = p();
        if (p > 0) {
            try {
                if (this.c == null) {
                    this.c = this.b.obtainByteArray(255);
                }
                int i = this.x;
                int i2 = this.w;
                int i3 = i - i2;
                if (i3 >= p) {
                    System.arraycopy(this.v, i2, this.c, 0, p);
                    this.w += p;
                } else if (this.q.remaining() + i3 >= p) {
                    System.arraycopy(this.v, this.w, this.c, 0, i3);
                    this.w = this.x;
                    q();
                    int i4 = p - i3;
                    System.arraycopy(this.v, 0, this.c, i3, i4);
                    this.w += i4;
                } else {
                    this.t = 1;
                }
            } catch (Exception e) {
                Logger.d(y, "Error Reading Block", e);
                this.t = 1;
            }
        }
        return p;
    }

    public final int p() {
        try {
            q();
            byte[] bArr = this.v;
            int i = this.w;
            this.w = i + 1;
            return bArr[i] & 255;
        } catch (Exception unused) {
            this.t = 1;
            return 0;
        }
    }

    public final void q() {
        if (this.x > this.w) {
            return;
        }
        if (this.v == null) {
            this.v = this.b.obtainByteArray(16384);
        }
        this.w = 0;
        int min = Math.min(this.q.remaining(), 16384);
        this.x = min;
        this.q.get(this.v, 0, min);
    }

    public void r() {
        this.i = 0;
    }

    public synchronized void t(c cVar, ByteBuffer byteBuffer) {
        u(cVar, byteBuffer, 1);
    }

    public synchronized void u(c cVar, ByteBuffer byteBuffer, int i) {
        if (i > 0) {
            int highestOneBit = Integer.highestOneBit(i);
            this.t = 0;
            this.g = cVar;
            this.h = false;
            this.f = -1;
            r();
            ByteBuffer asReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
            this.q = asReadOnlyBuffer;
            asReadOnlyBuffer.position(0);
            this.q.order(ByteOrder.LITTLE_ENDIAN);
            this.s = false;
            Iterator<b> it = cVar.e.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (it.next().c == 3) {
                    this.s = true;
                    break;
                }
            }
            this.r = highestOneBit;
            int i2 = cVar.m;
            this.e = i2 / highestOneBit;
            int i3 = cVar.i;
            this.d = i3 / highestOneBit;
            this.j = this.b.obtainByteArray(i2 * i3);
            this.k = this.b.obtainIntArray(this.e * this.d);
        } else {
            throw new IllegalArgumentException("Sample size must be >=0, not: " + i);
        }
    }

    public synchronized void v(c cVar, byte[] bArr) {
        t(cVar, ByteBuffer.wrap(bArr));
    }

    public boolean w(int i) {
        if (i < -1 || i >= g()) {
            return false;
        }
        this.f = i;
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0029, code lost:
        if (r3.b == r18.j) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.graphics.Bitmap x(com.clevertap.android.sdk.gif.b r18, com.clevertap.android.sdk.gif.b r19) {
        /*
            Method dump skipped, instructions count: 303
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.gif.a.x(com.clevertap.android.sdk.gif.b, com.clevertap.android.sdk.gif.b):android.graphics.Bitmap");
    }

    public a() {
        this(new SimpleBitmapProvider());
    }
}
