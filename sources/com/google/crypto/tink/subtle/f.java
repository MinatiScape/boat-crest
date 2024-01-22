package com.google.crypto.tink.subtle;

import android.support.v4.media.session.PlaybackStateCompat;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Arrays;
/* loaded from: classes10.dex */
public final class f {

    /* renamed from: a  reason: collision with root package name */
    public static final a f11053a = new a(new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    public static final c b = new c(new d(new long[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}), new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    public static final byte[] c = {-19, -45, com.crrepa.c.a.N1, 92, 26, 99, 18, com.htsmart.wristband2.a.a.a.o1, -42, -100, -9, -94, -34, -7, -34, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16};

    /* loaded from: classes10.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final long[] f11054a;
        public final long[] b;
        public final long[] c;

        public a() {
            this(new long[10], new long[10], new long[10]);
        }

        public void a(a aVar, int i) {
            com.google.crypto.tink.subtle.e.a(this.f11054a, aVar.f11054a, i);
            com.google.crypto.tink.subtle.e.a(this.b, aVar.b, i);
            com.google.crypto.tink.subtle.e.a(this.c, aVar.c, i);
        }

        public void b(long[] jArr, long[] jArr2) {
            System.arraycopy(jArr2, 0, jArr, 0, 10);
        }

        public a(long[] jArr, long[] jArr2, long[] jArr3) {
            this.f11054a = jArr;
            this.b = jArr2;
            this.c = jArr3;
        }

        public a(a aVar) {
            this.f11054a = Arrays.copyOf(aVar.f11054a, 10);
            this.b = Arrays.copyOf(aVar.b, 10);
            this.c = Arrays.copyOf(aVar.c, 10);
        }
    }

    /* loaded from: classes10.dex */
    public static class b extends a {
        public final long[] d;

        public b() {
            this(new long[10], new long[10], new long[10], new long[10]);
        }

        @Override // com.google.crypto.tink.subtle.f.a
        public void b(long[] jArr, long[] jArr2) {
            h.f(jArr, jArr2, this.d);
        }

        public b(e eVar) {
            this();
            long[] jArr = this.f11054a;
            d dVar = eVar.f11057a;
            h.q(jArr, dVar.b, dVar.f11056a);
            long[] jArr2 = this.b;
            d dVar2 = eVar.f11057a;
            h.o(jArr2, dVar2.b, dVar2.f11056a);
            System.arraycopy(eVar.f11057a.c, 0, this.d, 0, 10);
            h.f(this.c, eVar.b, g.b);
        }

        public b(long[] jArr, long[] jArr2, long[] jArr3, long[] jArr4) {
            super(jArr, jArr2, jArr4);
            this.d = jArr3;
        }
    }

    /* loaded from: classes10.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public final d f11055a;
        public final long[] b;

        public c() {
            this(new d(), new long[10]);
        }

        public c(d dVar, long[] jArr) {
            this.f11055a = dVar;
            this.b = jArr;
        }

        public c(c cVar) {
            this.f11055a = new d(cVar.f11055a);
            this.b = Arrays.copyOf(cVar.b, 10);
        }
    }

    /* loaded from: classes10.dex */
    public static class d {

        /* renamed from: a  reason: collision with root package name */
        public final long[] f11056a;
        public final long[] b;
        public final long[] c;

        public d() {
            this(new long[10], new long[10], new long[10]);
        }

        public static d a(d dVar, c cVar) {
            h.f(dVar.f11056a, cVar.f11055a.f11056a, cVar.b);
            long[] jArr = dVar.b;
            d dVar2 = cVar.f11055a;
            h.f(jArr, dVar2.b, dVar2.c);
            h.f(dVar.c, cVar.f11055a.c, cVar.b);
            return dVar;
        }

        public boolean b() {
            long[] jArr = new long[10];
            h.l(jArr, this.f11056a);
            long[] jArr2 = new long[10];
            h.l(jArr2, this.b);
            long[] jArr3 = new long[10];
            h.l(jArr3, this.c);
            long[] jArr4 = new long[10];
            h.l(jArr4, jArr3);
            long[] jArr5 = new long[10];
            h.o(jArr5, jArr2, jArr);
            h.f(jArr5, jArr5, jArr3);
            long[] jArr6 = new long[10];
            h.f(jArr6, jArr, jArr2);
            h.f(jArr6, jArr6, g.f11058a);
            h.p(jArr6, jArr4);
            h.h(jArr6, jArr6);
            return Bytes.equal(h.a(jArr5), h.a(jArr6));
        }

        public byte[] c() {
            long[] jArr = new long[10];
            long[] jArr2 = new long[10];
            long[] jArr3 = new long[10];
            h.e(jArr, this.c);
            h.f(jArr2, this.f11056a, jArr);
            h.f(jArr3, this.b, jArr);
            byte[] a2 = h.a(jArr3);
            a2[31] = (byte) ((f.k(jArr2) << 7) ^ a2[31]);
            return a2;
        }

        public d(long[] jArr, long[] jArr2, long[] jArr3) {
            this.f11056a = jArr;
            this.b = jArr2;
            this.c = jArr3;
        }

        public d(d dVar) {
            this.f11056a = Arrays.copyOf(dVar.f11056a, 10);
            this.b = Arrays.copyOf(dVar.b, 10);
            this.c = Arrays.copyOf(dVar.c, 10);
        }

        public d(c cVar) {
            this();
            a(this, cVar);
        }
    }

    /* loaded from: classes10.dex */
    public static class e {

        /* renamed from: a  reason: collision with root package name */
        public final d f11057a;
        public final long[] b;

        public e() {
            this(new d(), new long[10]);
        }

        public static e c(byte[] bArr) throws GeneralSecurityException {
            long[] jArr = new long[10];
            long[] c = h.c(bArr);
            long[] jArr2 = new long[10];
            jArr2[0] = 1;
            long[] jArr3 = new long[10];
            long[] jArr4 = new long[10];
            long[] jArr5 = new long[10];
            long[] jArr6 = new long[10];
            long[] jArr7 = new long[10];
            h.l(jArr4, c);
            h.f(jArr5, jArr4, g.f11058a);
            h.o(jArr4, jArr4, jArr2);
            h.q(jArr5, jArr5, jArr2);
            long[] jArr8 = new long[10];
            h.l(jArr8, jArr5);
            h.f(jArr8, jArr8, jArr5);
            h.l(jArr, jArr8);
            h.f(jArr, jArr, jArr5);
            h.f(jArr, jArr, jArr4);
            f.r(jArr, jArr);
            h.f(jArr, jArr, jArr8);
            h.f(jArr, jArr, jArr4);
            h.l(jArr6, jArr);
            h.f(jArr6, jArr6, jArr5);
            h.o(jArr7, jArr6, jArr4);
            if (f.l(jArr7)) {
                h.q(jArr7, jArr6, jArr4);
                if (!f.l(jArr7)) {
                    h.f(jArr, jArr, g.c);
                } else {
                    throw new GeneralSecurityException("Cannot convert given bytes to extended projective coordinates. No square root exists for modulo 2^255-19");
                }
            }
            if (f.l(jArr) || ((bArr[31] & 255) >> 7) == 0) {
                if (f.k(jArr) == ((bArr[31] & 255) >> 7)) {
                    f.q(jArr, jArr);
                }
                h.f(jArr3, jArr, c);
                return new e(new d(jArr, c, jArr2), jArr3);
            }
            throw new GeneralSecurityException("Cannot convert given bytes to extended projective coordinates. Computed x is zero and encoded x's least significant bit is not zero");
        }

        public static e d(e eVar, c cVar) {
            h.f(eVar.f11057a.f11056a, cVar.f11055a.f11056a, cVar.b);
            long[] jArr = eVar.f11057a.b;
            d dVar = cVar.f11055a;
            h.f(jArr, dVar.b, dVar.c);
            h.f(eVar.f11057a.c, cVar.f11055a.c, cVar.b);
            long[] jArr2 = eVar.b;
            d dVar2 = cVar.f11055a;
            h.f(jArr2, dVar2.f11056a, dVar2.b);
            return eVar;
        }

        public e(d dVar, long[] jArr) {
            this.f11057a = dVar;
            this.b = jArr;
        }

        public e(c cVar) {
            this();
            d(this, cVar);
        }
    }

    public static void e(c cVar, e eVar, a aVar) {
        long[] jArr = new long[10];
        long[] jArr2 = cVar.f11055a.f11056a;
        d dVar = eVar.f11057a;
        h.q(jArr2, dVar.b, dVar.f11056a);
        long[] jArr3 = cVar.f11055a.b;
        d dVar2 = eVar.f11057a;
        h.o(jArr3, dVar2.b, dVar2.f11056a);
        long[] jArr4 = cVar.f11055a.b;
        h.f(jArr4, jArr4, aVar.b);
        d dVar3 = cVar.f11055a;
        h.f(dVar3.c, dVar3.f11056a, aVar.f11054a);
        h.f(cVar.b, eVar.b, aVar.c);
        aVar.b(cVar.f11055a.f11056a, eVar.f11057a.c);
        long[] jArr5 = cVar.f11055a.f11056a;
        h.q(jArr, jArr5, jArr5);
        d dVar4 = cVar.f11055a;
        h.o(dVar4.f11056a, dVar4.c, dVar4.b);
        d dVar5 = cVar.f11055a;
        long[] jArr6 = dVar5.b;
        h.q(jArr6, dVar5.c, jArr6);
        h.q(cVar.f11055a.c, jArr, cVar.b);
        long[] jArr7 = cVar.b;
        h.o(jArr7, jArr, jArr7);
    }

    public static d f(byte[] bArr, e eVar, byte[] bArr2) {
        b[] bVarArr = new b[8];
        bVarArr[0] = new b(eVar);
        c cVar = new c();
        h(cVar, eVar);
        e eVar2 = new e(cVar);
        for (int i = 1; i < 8; i++) {
            e(cVar, eVar2, bVarArr[i - 1]);
            bVarArr[i] = new b(new e(cVar));
        }
        byte[] x = x(bArr);
        byte[] x2 = x(bArr2);
        c cVar2 = new c(b);
        e eVar3 = new e();
        int i2 = 255;
        while (i2 >= 0 && x[i2] == 0 && x2[i2] == 0) {
            i2--;
        }
        while (i2 >= 0) {
            g(cVar2, new d(cVar2));
            if (x[i2] > 0) {
                e(cVar2, e.d(eVar3, cVar2), bVarArr[x[i2] / 2]);
            } else if (x[i2] < 0) {
                y(cVar2, e.d(eVar3, cVar2), bVarArr[(-x[i2]) / 2]);
            }
            if (x2[i2] > 0) {
                e(cVar2, e.d(eVar3, cVar2), g.e[x2[i2] / 2]);
            } else if (x2[i2] < 0) {
                y(cVar2, e.d(eVar3, cVar2), g.e[(-x2[i2]) / 2]);
            }
            i2--;
        }
        return new d(cVar2);
    }

    public static void g(c cVar, d dVar) {
        long[] jArr = new long[10];
        h.l(cVar.f11055a.f11056a, dVar.f11056a);
        h.l(cVar.f11055a.c, dVar.b);
        h.l(cVar.b, dVar.c);
        long[] jArr2 = cVar.b;
        h.q(jArr2, jArr2, jArr2);
        h.q(cVar.f11055a.b, dVar.f11056a, dVar.b);
        h.l(jArr, cVar.f11055a.b);
        d dVar2 = cVar.f11055a;
        h.q(dVar2.b, dVar2.c, dVar2.f11056a);
        d dVar3 = cVar.f11055a;
        long[] jArr3 = dVar3.c;
        h.o(jArr3, jArr3, dVar3.f11056a);
        d dVar4 = cVar.f11055a;
        h.o(dVar4.f11056a, jArr, dVar4.b);
        long[] jArr4 = cVar.b;
        h.o(jArr4, jArr4, cVar.f11055a.c);
    }

    public static void h(c cVar, e eVar) {
        g(cVar, eVar.f11057a);
    }

    public static int i(int i, int i2) {
        int i3 = (~(i ^ i2)) & 255;
        int i4 = i3 & (i3 << 4);
        int i5 = i4 & (i4 << 2);
        return ((i5 & (i5 << 1)) >> 7) & 1;
    }

    public static byte[] j(byte[] bArr) throws GeneralSecurityException {
        MessageDigest engineFactory = EngineFactory.MESSAGE_DIGEST.getInstance("SHA-512");
        engineFactory.update(bArr, 0, 32);
        byte[] digest = engineFactory.digest();
        digest[0] = (byte) (digest[0] & 248);
        digest[31] = (byte) (digest[31] & Byte.MAX_VALUE);
        digest[31] = (byte) (digest[31] | 64);
        return digest;
    }

    public static int k(long[] jArr) {
        return h.a(jArr)[0] & 1;
    }

    public static boolean l(long[] jArr) {
        long[] jArr2 = new long[jArr.length + 1];
        System.arraycopy(jArr, 0, jArr2, 0, jArr.length);
        h.i(jArr2);
        for (byte b2 : h.a(jArr2)) {
            if (b2 != 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean m(byte[] bArr) {
        for (int i = 31; i >= 0; i--) {
            int i2 = bArr[i] & 255;
            int i3 = c[i] & 255;
            if (i2 != i3) {
                return i2 < i3;
            }
        }
        return false;
    }

    public static long n(byte[] bArr, int i) {
        return ((bArr[i + 2] & 255) << 16) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8);
    }

    public static long o(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | n(bArr, i);
    }

    public static void p(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        long n = n(bArr2, 0) & 2097151;
        long o = (o(bArr2, 2) >> 5) & 2097151;
        long n2 = (n(bArr2, 5) >> 2) & 2097151;
        long o2 = (o(bArr2, 7) >> 7) & 2097151;
        long o3 = (o(bArr2, 10) >> 4) & 2097151;
        long n3 = (n(bArr2, 13) >> 1) & 2097151;
        long o4 = (o(bArr2, 15) >> 6) & 2097151;
        long n4 = (n(bArr2, 18) >> 3) & 2097151;
        long n5 = n(bArr2, 21) & 2097151;
        long o5 = (o(bArr2, 23) >> 5) & 2097151;
        long n6 = (n(bArr2, 26) >> 2) & 2097151;
        long o6 = o(bArr2, 28) >> 7;
        long n7 = n(bArr3, 0) & 2097151;
        long o7 = (o(bArr3, 2) >> 5) & 2097151;
        long n8 = (n(bArr3, 5) >> 2) & 2097151;
        long o8 = (o(bArr3, 7) >> 7) & 2097151;
        long o9 = (o(bArr3, 10) >> 4) & 2097151;
        long n9 = (n(bArr3, 13) >> 1) & 2097151;
        long o10 = (o(bArr3, 15) >> 6) & 2097151;
        long n10 = (n(bArr3, 18) >> 3) & 2097151;
        long n11 = n(bArr3, 21) & 2097151;
        long o11 = (o(bArr3, 23) >> 5) & 2097151;
        long n12 = (n(bArr3, 26) >> 2) & 2097151;
        long o12 = o(bArr3, 28) >> 7;
        long n13 = (n(bArr4, 26) >> 2) & 2097151;
        long n14 = (n(bArr4, 0) & 2097151) + (n * n7);
        long o13 = ((o(bArr4, 2) >> 5) & 2097151) + (n * o7) + (o * n7);
        long n15 = ((n(bArr4, 5) >> 2) & 2097151) + (n * n8) + (o * o7) + (n2 * n7);
        long o14 = ((o(bArr4, 7) >> 7) & 2097151) + (n * o8) + (o * n8) + (n2 * o7) + (o2 * n7);
        long o15 = ((o(bArr4, 10) >> 4) & 2097151) + (n * o9) + (o * o8) + (n2 * n8) + (o2 * o7) + (o3 * n7);
        long n16 = ((n(bArr4, 13) >> 1) & 2097151) + (n * n9) + (o * o9) + (n2 * o8) + (o2 * n8) + (o3 * o7) + (n3 * n7);
        long o16 = ((o(bArr4, 15) >> 6) & 2097151) + (n * o10) + (o * n9) + (n2 * o9) + (o2 * o8) + (o3 * n8) + (n3 * o7) + (o4 * n7);
        long n17 = ((n(bArr4, 18) >> 3) & 2097151) + (n * n10) + (o * o10) + (n2 * n9) + (o2 * o9) + (o3 * o8) + (n3 * n8) + (o4 * o7) + (n4 * n7);
        long n18 = (n(bArr4, 21) & 2097151) + (n * n11) + (o * n10) + (n2 * o10) + (o2 * n9) + (o3 * o9) + (n3 * o8) + (o4 * n8) + (n4 * o7) + (n5 * n7);
        long o17 = ((o(bArr4, 23) >> 5) & 2097151) + (n * o11) + (o * n11) + (n2 * n10) + (o2 * o10) + (o3 * n9) + (n3 * o9) + (o4 * o8) + (n4 * n8) + (n5 * o7) + (o5 * n7);
        long j = n13 + (n * n12) + (o * o11) + (n2 * n11) + (o2 * n10) + (o3 * o10) + (n3 * n9) + (o4 * o9) + (n4 * o8) + (n5 * n8) + (o5 * o7) + (n6 * n7);
        long j2 = (o * o12) + (n2 * n12) + (o2 * o11) + (o3 * n11) + (n3 * n10) + (o4 * o10) + (n4 * n9) + (n5 * o9) + (o5 * o8) + (n6 * n8) + (o7 * o6);
        long j3 = (o2 * o12) + (o3 * n12) + (n3 * o11) + (o4 * n11) + (n4 * n10) + (n5 * o10) + (o5 * n9) + (n6 * o9) + (o8 * o6);
        long j4 = (n3 * o12) + (o4 * n12) + (n4 * o11) + (n5 * n11) + (o5 * n10) + (n6 * o10) + (n9 * o6);
        long j5 = (n4 * o12) + (n5 * n12) + (o5 * o11) + (n6 * n11) + (n10 * o6);
        long j6 = (o5 * o12) + (n6 * n12) + (o11 * o6);
        long j7 = o6 * o12;
        long j8 = (n14 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j9 = o13 + j8;
        long j10 = n14 - (j8 << 21);
        long j11 = (n15 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j12 = o14 + j11;
        long j13 = n15 - (j11 << 21);
        long j14 = (o15 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j15 = n16 + j14;
        long j16 = o15 - (j14 << 21);
        long j17 = (o16 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j18 = n17 + j17;
        long j19 = o16 - (j17 << 21);
        long j20 = (n18 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j21 = o17 + j20;
        long j22 = n18 - (j20 << 21);
        long j23 = (j + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long o18 = (o(bArr4, 28) >> 7) + (n * o12) + (o * n12) + (n2 * o11) + (o2 * n11) + (o3 * n10) + (n3 * o10) + (o4 * n9) + (n4 * o9) + (n5 * o8) + (o5 * n8) + (n6 * o7) + (n7 * o6) + j23;
        long j24 = j - (j23 << 21);
        long j25 = (j2 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j26 = (n2 * o12) + (o2 * n12) + (o3 * o11) + (n3 * n11) + (o4 * n10) + (n4 * o10) + (n5 * n9) + (o5 * o9) + (n6 * o8) + (n8 * o6) + j25;
        long j27 = j2 - (j25 << 21);
        long j28 = (j3 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j29 = (o3 * o12) + (n3 * n12) + (o4 * o11) + (n4 * n11) + (n5 * n10) + (o5 * o10) + (n6 * n9) + (o9 * o6) + j28;
        long j30 = j3 - (j28 << 21);
        long j31 = (j4 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j32 = (o4 * o12) + (n4 * n12) + (n5 * o11) + (o5 * n11) + (n6 * n10) + (o10 * o6) + j31;
        long j33 = j4 - (j31 << 21);
        long j34 = (j5 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j35 = (n5 * o12) + (o5 * n12) + (n6 * o11) + (n11 * o6) + j34;
        long j36 = j5 - (j34 << 21);
        long j37 = (j6 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j38 = (n6 * o12) + (n12 * o6) + j37;
        long j39 = j6 - (j37 << 21);
        long j40 = (j7 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j41 = j40 + 0;
        long j42 = j7 - (j40 << 21);
        long j43 = (j9 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j44 = j13 + j43;
        long j45 = j9 - (j43 << 21);
        long j46 = (j12 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j47 = j16 + j46;
        long j48 = j12 - (j46 << 21);
        long j49 = (j15 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j50 = j19 + j49;
        long j51 = j15 - (j49 << 21);
        long j52 = (j18 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j53 = j22 + j52;
        long j54 = j18 - (j52 << 21);
        long j55 = (j21 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j56 = j24 + j55;
        long j57 = j21 - (j55 << 21);
        long j58 = (o18 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j59 = j27 + j58;
        long j60 = o18 - (j58 << 21);
        long j61 = (j26 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j62 = j30 + j61;
        long j63 = j26 - (j61 << 21);
        long j64 = (j29 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j65 = j33 + j64;
        long j66 = j29 - (j64 << 21);
        long j67 = (j32 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j68 = j36 + j67;
        long j69 = j32 - (j67 << 21);
        long j70 = (j35 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j71 = j39 + j70;
        long j72 = j35 - (j70 << 21);
        long j73 = (j38 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j74 = j42 + j73;
        long j75 = j38 - (j73 << 21);
        long j76 = j65 - (j41 * 683901);
        long j77 = ((j62 - (j41 * 997805)) + (j74 * 136657)) - (j75 * 683901);
        long j78 = ((((j59 + (j41 * 470296)) + (j74 * 654183)) - (j75 * 997805)) + (j71 * 136657)) - (j72 * 683901);
        long j79 = j50 + (j68 * 666643);
        long j80 = j54 + (j72 * 666643) + (j68 * 470296);
        long j81 = j53 + (j71 * 666643) + (j72 * 470296) + (j68 * 654183);
        long j82 = (((j57 + (j75 * 666643)) + (j71 * 470296)) + (j72 * 654183)) - (j68 * 997805);
        long j83 = ((((j56 + (j74 * 666643)) + (j75 * 470296)) + (j71 * 654183)) - (j72 * 997805)) + (j68 * 136657);
        long j84 = (((((j60 + (j41 * 666643)) + (j74 * 470296)) + (j75 * 654183)) - (j71 * 997805)) + (j72 * 136657)) - (j68 * 683901);
        long j85 = (j79 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j86 = j80 + j85;
        long j87 = j79 - (j85 << 21);
        long j88 = (j81 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j89 = j82 + j88;
        long j90 = j81 - (j88 << 21);
        long j91 = (j83 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j92 = j84 + j91;
        long j93 = j83 - (j91 << 21);
        long j94 = (j78 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j95 = ((((j63 + (j41 * 654183)) - (j74 * 997805)) + (j75 * 136657)) - (j71 * 683901)) + j94;
        long j96 = j78 - (j94 << 21);
        long j97 = (j77 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j98 = ((j66 + (j41 * 136657)) - (j74 * 683901)) + j97;
        long j99 = j77 - (j97 << 21);
        long j100 = (j76 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j101 = j69 + j100;
        long j102 = j76 - (j100 << 21);
        long j103 = (j86 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j104 = j90 + j103;
        long j105 = j86 - (j103 << 21);
        long j106 = (j89 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j107 = j93 + j106;
        long j108 = j89 - (j106 << 21);
        long j109 = (j92 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j110 = j96 + j109;
        long j111 = j92 - (j109 << 21);
        long j112 = (j95 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j113 = j99 + j112;
        long j114 = j95 - (j112 << 21);
        long j115 = (j98 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j116 = j102 + j115;
        long j117 = j98 - (j115 << 21);
        long j118 = j107 - (j101 * 683901);
        long j119 = ((j104 - (j101 * 997805)) + (j116 * 136657)) - (j117 * 683901);
        long j120 = ((((j87 + (j101 * 470296)) + (j116 * 654183)) - (j117 * 997805)) + (j113 * 136657)) - (j114 * 683901);
        long j121 = j10 + (j110 * 666643);
        long j122 = j45 + (j114 * 666643) + (j110 * 470296);
        long j123 = j44 + (j113 * 666643) + (j114 * 470296) + (j110 * 654183);
        long j124 = (((j48 + (j117 * 666643)) + (j113 * 470296)) + (j114 * 654183)) - (j110 * 997805);
        long j125 = ((((j47 + (j116 * 666643)) + (j117 * 470296)) + (j113 * 654183)) - (j114 * 997805)) + (j110 * 136657);
        long j126 = (((((j51 + (j101 * 666643)) + (j116 * 470296)) + (j117 * 654183)) - (j113 * 997805)) + (j114 * 136657)) - (j110 * 683901);
        long j127 = (j121 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j128 = j122 + j127;
        long j129 = j121 - (j127 << 21);
        long j130 = (j123 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j131 = j124 + j130;
        long j132 = j123 - (j130 << 21);
        long j133 = (j125 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j134 = j126 + j133;
        long j135 = j125 - (j133 << 21);
        long j136 = (j120 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j137 = ((((j105 + (j101 * 654183)) - (j116 * 997805)) + (j117 * 136657)) - (j113 * 683901)) + j136;
        long j138 = j120 - (j136 << 21);
        long j139 = (j119 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j140 = ((j108 + (j101 * 136657)) - (j116 * 683901)) + j139;
        long j141 = j119 - (j139 << 21);
        long j142 = (j118 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j143 = j111 + j142;
        long j144 = j118 - (j142 << 21);
        long j145 = (j128 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j146 = j132 + j145;
        long j147 = j128 - (j145 << 21);
        long j148 = (j131 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j149 = j135 + j148;
        long j150 = j131 - (j148 << 21);
        long j151 = (j134 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j152 = j138 + j151;
        long j153 = j134 - (j151 << 21);
        long j154 = (j137 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j155 = j141 + j154;
        long j156 = j137 - (j154 << 21);
        long j157 = (j140 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j158 = j144 + j157;
        long j159 = j140 - (j157 << 21);
        long j160 = (PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED + j143) >> 21;
        long j161 = 0 + j160;
        long j162 = j129 + (j161 * 666643);
        long j163 = j147 + (j161 * 470296);
        long j164 = j146 + (j161 * 654183);
        long j165 = j150 - (j161 * 997805);
        long j166 = j153 - (j161 * 683901);
        long j167 = j162 >> 21;
        long j168 = j163 + j167;
        long j169 = j162 - (j167 << 21);
        long j170 = j168 >> 21;
        long j171 = j164 + j170;
        long j172 = j168 - (j170 << 21);
        long j173 = j171 >> 21;
        long j174 = j165 + j173;
        long j175 = j171 - (j173 << 21);
        long j176 = j174 >> 21;
        long j177 = j149 + (j161 * 136657) + j176;
        long j178 = j174 - (j176 << 21);
        long j179 = j177 >> 21;
        long j180 = j166 + j179;
        long j181 = j177 - (j179 << 21);
        long j182 = j180 >> 21;
        long j183 = j152 + j182;
        long j184 = j180 - (j182 << 21);
        long j185 = j183 >> 21;
        long j186 = j156 + j185;
        long j187 = j183 - (j185 << 21);
        long j188 = j186 >> 21;
        long j189 = j155 + j188;
        long j190 = j186 - (j188 << 21);
        long j191 = j189 >> 21;
        long j192 = j159 + j191;
        long j193 = j189 - (j191 << 21);
        long j194 = j192 >> 21;
        long j195 = j158 + j194;
        long j196 = j192 - (j194 << 21);
        long j197 = j195 >> 21;
        long j198 = (j143 - (j160 << 21)) + j197;
        long j199 = j195 - (j197 << 21);
        long j200 = j198 >> 21;
        long j201 = 0 + j200;
        long j202 = j198 - (j200 << 21);
        long j203 = j169 + (666643 * j201);
        long j204 = j203 >> 21;
        long j205 = j172 + (470296 * j201) + j204;
        long j206 = j203 - (j204 << 21);
        long j207 = j205 >> 21;
        long j208 = j175 + (654183 * j201) + j207;
        long j209 = j205 - (j207 << 21);
        long j210 = j208 >> 21;
        long j211 = (j178 - (997805 * j201)) + j210;
        long j212 = j208 - (j210 << 21);
        long j213 = j211 >> 21;
        long j214 = j181 + (136657 * j201) + j213;
        long j215 = j211 - (j213 << 21);
        long j216 = j214 >> 21;
        long j217 = (j184 - (j201 * 683901)) + j216;
        long j218 = j214 - (j216 << 21);
        long j219 = j217 >> 21;
        long j220 = j187 + j219;
        long j221 = j217 - (j219 << 21);
        long j222 = j220 >> 21;
        long j223 = j190 + j222;
        long j224 = j220 - (j222 << 21);
        long j225 = j223 >> 21;
        long j226 = j193 + j225;
        long j227 = j223 - (j225 << 21);
        long j228 = j226 >> 21;
        long j229 = j196 + j228;
        long j230 = j226 - (j228 << 21);
        long j231 = j229 >> 21;
        long j232 = j199 + j231;
        long j233 = j229 - (j231 << 21);
        long j234 = j232 >> 21;
        long j235 = j202 + j234;
        long j236 = j232 - (j234 << 21);
        bArr[0] = (byte) j206;
        bArr[1] = (byte) (j206 >> 8);
        bArr[2] = (byte) ((j206 >> 16) | (j209 << 5));
        bArr[3] = (byte) (j209 >> 3);
        bArr[4] = (byte) (j209 >> 11);
        bArr[5] = (byte) ((j209 >> 19) | (j212 << 2));
        bArr[6] = (byte) (j212 >> 6);
        bArr[7] = (byte) ((j212 >> 14) | (j215 << 7));
        bArr[8] = (byte) (j215 >> 1);
        bArr[9] = (byte) (j215 >> 9);
        bArr[10] = (byte) ((j215 >> 17) | (j218 << 4));
        bArr[11] = (byte) (j218 >> 4);
        bArr[12] = (byte) (j218 >> 12);
        bArr[13] = (byte) ((j218 >> 20) | (j221 << 1));
        bArr[14] = (byte) (j221 >> 7);
        bArr[15] = (byte) ((j221 >> 15) | (j224 << 6));
        bArr[16] = (byte) (j224 >> 2);
        bArr[17] = (byte) (j224 >> 10);
        bArr[18] = (byte) ((j224 >> 18) | (j227 << 3));
        bArr[19] = (byte) (j227 >> 5);
        bArr[20] = (byte) (j227 >> 13);
        bArr[21] = (byte) j230;
        bArr[22] = (byte) (j230 >> 8);
        bArr[23] = (byte) ((j230 >> 16) | (j233 << 5));
        bArr[24] = (byte) (j233 >> 3);
        bArr[25] = (byte) (j233 >> 11);
        bArr[26] = (byte) ((j233 >> 19) | (j236 << 2));
        bArr[27] = (byte) (j236 >> 6);
        bArr[28] = (byte) ((j236 >> 14) | (j235 << 7));
        bArr[29] = (byte) (j235 >> 1);
        bArr[30] = (byte) (j235 >> 9);
        bArr[31] = (byte) (j235 >> 17);
    }

    public static void q(long[] jArr, long[] jArr2) {
        for (int i = 0; i < jArr2.length; i++) {
            jArr[i] = -jArr2[i];
        }
    }

    public static void r(long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[10];
        long[] jArr4 = new long[10];
        long[] jArr5 = new long[10];
        h.l(jArr3, jArr2);
        h.l(jArr4, jArr3);
        for (int i = 1; i < 2; i++) {
            h.l(jArr4, jArr4);
        }
        h.f(jArr4, jArr2, jArr4);
        h.f(jArr3, jArr3, jArr4);
        h.l(jArr3, jArr3);
        h.f(jArr3, jArr4, jArr3);
        h.l(jArr4, jArr3);
        for (int i2 = 1; i2 < 5; i2++) {
            h.l(jArr4, jArr4);
        }
        h.f(jArr3, jArr4, jArr3);
        h.l(jArr4, jArr3);
        for (int i3 = 1; i3 < 10; i3++) {
            h.l(jArr4, jArr4);
        }
        h.f(jArr4, jArr4, jArr3);
        h.l(jArr5, jArr4);
        for (int i4 = 1; i4 < 20; i4++) {
            h.l(jArr5, jArr5);
        }
        h.f(jArr4, jArr5, jArr4);
        h.l(jArr4, jArr4);
        for (int i5 = 1; i5 < 10; i5++) {
            h.l(jArr4, jArr4);
        }
        h.f(jArr3, jArr4, jArr3);
        h.l(jArr4, jArr3);
        for (int i6 = 1; i6 < 50; i6++) {
            h.l(jArr4, jArr4);
        }
        h.f(jArr4, jArr4, jArr3);
        h.l(jArr5, jArr4);
        for (int i7 = 1; i7 < 100; i7++) {
            h.l(jArr5, jArr5);
        }
        h.f(jArr4, jArr5, jArr4);
        h.l(jArr4, jArr4);
        for (int i8 = 1; i8 < 50; i8++) {
            h.l(jArr4, jArr4);
        }
        h.f(jArr3, jArr4, jArr3);
        h.l(jArr3, jArr3);
        for (int i9 = 1; i9 < 2; i9++) {
            h.l(jArr3, jArr3);
        }
        h.f(jArr, jArr3, jArr2);
    }

    public static void s(byte[] bArr) {
        long n = (n(bArr, 47) >> 2) & 2097151;
        long o = (o(bArr, 49) >> 7) & 2097151;
        long o2 = (o(bArr, 52) >> 4) & 2097151;
        long n2 = (n(bArr, 55) >> 1) & 2097151;
        long o3 = (o(bArr, 57) >> 6) & 2097151;
        long o4 = o(bArr, 60) >> 3;
        long n3 = (n(bArr, 42) & 2097151) - (o4 * 683901);
        long n4 = ((n(bArr, 26) >> 2) & 2097151) + (o3 * 666643);
        long o5 = ((o(bArr, 28) >> 7) & 2097151) + (o4 * 666643) + (o3 * 470296);
        long o6 = ((o(bArr, 31) >> 4) & 2097151) + (o4 * 470296) + (o3 * 654183);
        long n5 = (((n(bArr, 34) >> 1) & 2097151) + (o4 * 654183)) - (o3 * 997805);
        long n6 = (((n(bArr, 39) >> 3) & 2097151) + (o4 * 136657)) - (o3 * 683901);
        long o7 = ((o(bArr, 23) >> 5) & 2097151) + (n2 * 666643);
        long o8 = ((((o(bArr, 36) >> 6) & 2097151) - (o4 * 997805)) + (o3 * 136657)) - (n2 * 683901);
        long n7 = (n(bArr, 21) & 2097151) + (o2 * 666643);
        long n8 = ((n(bArr, 18) >> 3) & 2097151) + (o * 666643);
        long j = ((o6 - (n2 * 997805)) + (o2 * 136657)) - (o * 683901);
        long o9 = ((o(bArr, 15) >> 6) & 2097151) + (n * 666643);
        long j2 = n8 + (n * 470296);
        long j3 = n7 + (o * 470296) + (n * 654183);
        long j4 = ((o7 + (o2 * 470296)) + (o * 654183)) - (n * 997805);
        long j5 = (((n4 + (n2 * 470296)) + (o2 * 654183)) - (o * 997805)) + (n * 136657);
        long j6 = (((o5 + (n2 * 654183)) - (o2 * 997805)) + (o * 136657)) - (n * 683901);
        long j7 = (o9 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j8 = j2 + j7;
        long j9 = o9 - (j7 << 21);
        long j10 = (j3 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j11 = j4 + j10;
        long j12 = j3 - (j10 << 21);
        long j13 = (j5 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j14 = j6 + j13;
        long j15 = j5 - (j13 << 21);
        long j16 = (j + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j17 = ((n5 + (n2 * 136657)) - (o2 * 683901)) + j16;
        long j18 = j - (j16 << 21);
        long j19 = (o8 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j20 = n6 + j19;
        long j21 = o8 - (j19 << 21);
        long j22 = (n3 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long o10 = ((o(bArr, 44) >> 5) & 2097151) + j22;
        long j23 = n3 - (j22 << 21);
        long j24 = (j8 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j25 = j12 + j24;
        long j26 = j8 - (j24 << 21);
        long j27 = (j11 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j28 = j15 + j27;
        long j29 = j11 - (j27 << 21);
        long j30 = (j14 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j31 = j18 + j30;
        long j32 = j14 - (j30 << 21);
        long j33 = (j17 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j34 = j21 + j33;
        long j35 = j17 - (j33 << 21);
        long j36 = (j20 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j37 = j23 + j36;
        long j38 = j20 - (j36 << 21);
        long n9 = ((n(bArr, 13) >> 1) & 2097151) + (o10 * 666643);
        long j39 = j28 - (o10 * 683901);
        long o11 = ((o(bArr, 10) >> 4) & 2097151) + (j37 * 666643);
        long o12 = ((o(bArr, 7) >> 7) & 2097151) + (j38 * 666643);
        long j40 = ((j25 - (o10 * 997805)) + (j37 * 136657)) - (j38 * 683901);
        long n10 = ((n(bArr, 5) >> 2) & 2097151) + (j34 * 666643);
        long o13 = ((o(bArr, 2) >> 5) & 2097151) + (j35 * 666643);
        long j41 = ((((j9 + (o10 * 470296)) + (j37 * 654183)) - (j38 * 997805)) + (j34 * 136657)) - (j35 * 683901);
        long n11 = (n(bArr, 0) & 2097151) + (j31 * 666643);
        long j42 = o13 + (j31 * 470296);
        long j43 = n10 + (j35 * 470296) + (j31 * 654183);
        long j44 = ((o12 + (j34 * 470296)) + (j35 * 654183)) - (j31 * 997805);
        long j45 = (((o11 + (j38 * 470296)) + (j34 * 654183)) - (j35 * 997805)) + (j31 * 136657);
        long j46 = ((((n9 + (j37 * 470296)) + (j38 * 654183)) - (j34 * 997805)) + (j35 * 136657)) - (j31 * 683901);
        long j47 = (n11 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j48 = j42 + j47;
        long j49 = n11 - (j47 << 21);
        long j50 = (j43 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j51 = j44 + j50;
        long j52 = j43 - (j50 << 21);
        long j53 = (j45 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j54 = j46 + j53;
        long j55 = j45 - (j53 << 21);
        long j56 = (j41 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j57 = ((((j26 + (o10 * 654183)) - (j37 * 997805)) + (j38 * 136657)) - (j34 * 683901)) + j56;
        long j58 = j41 - (j56 << 21);
        long j59 = (j40 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j60 = ((j29 + (o10 * 136657)) - (j37 * 683901)) + j59;
        long j61 = j40 - (j59 << 21);
        long j62 = (j39 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j63 = j32 + j62;
        long j64 = j39 - (j62 << 21);
        long j65 = (j48 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j66 = j52 + j65;
        long j67 = j48 - (j65 << 21);
        long j68 = (j51 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j69 = j55 + j68;
        long j70 = j51 - (j68 << 21);
        long j71 = (j54 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j72 = j58 + j71;
        long j73 = j54 - (j71 << 21);
        long j74 = (j57 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j75 = j61 + j74;
        long j76 = j57 - (j74 << 21);
        long j77 = (j60 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j78 = j64 + j77;
        long j79 = j60 - (j77 << 21);
        long j80 = (j63 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j81 = j80 + 0;
        long j82 = j49 + (j81 * 666643);
        long j83 = j67 + (j81 * 470296);
        long j84 = j66 + (j81 * 654183);
        long j85 = j70 - (j81 * 997805);
        long j86 = j73 - (j81 * 683901);
        long j87 = j82 >> 21;
        long j88 = j83 + j87;
        long j89 = j82 - (j87 << 21);
        long j90 = j88 >> 21;
        long j91 = j84 + j90;
        long j92 = j88 - (j90 << 21);
        long j93 = j91 >> 21;
        long j94 = j85 + j93;
        long j95 = j91 - (j93 << 21);
        long j96 = j94 >> 21;
        long j97 = j69 + (j81 * 136657) + j96;
        long j98 = j94 - (j96 << 21);
        long j99 = j97 >> 21;
        long j100 = j86 + j99;
        long j101 = j97 - (j99 << 21);
        long j102 = j100 >> 21;
        long j103 = j72 + j102;
        long j104 = j100 - (j102 << 21);
        long j105 = j103 >> 21;
        long j106 = j76 + j105;
        long j107 = j103 - (j105 << 21);
        long j108 = j106 >> 21;
        long j109 = j75 + j108;
        long j110 = j106 - (j108 << 21);
        long j111 = j109 >> 21;
        long j112 = j79 + j111;
        long j113 = j109 - (j111 << 21);
        long j114 = j112 >> 21;
        long j115 = j78 + j114;
        long j116 = j112 - (j114 << 21);
        long j117 = j115 >> 21;
        long j118 = (j63 - (j80 << 21)) + j117;
        long j119 = j115 - (j117 << 21);
        long j120 = j118 >> 21;
        long j121 = j120 + 0;
        long j122 = j118 - (j120 << 21);
        long j123 = j89 + (666643 * j121);
        long j124 = j123 >> 21;
        long j125 = j92 + (470296 * j121) + j124;
        long j126 = j123 - (j124 << 21);
        long j127 = j125 >> 21;
        long j128 = j95 + (654183 * j121) + j127;
        long j129 = j125 - (j127 << 21);
        long j130 = j128 >> 21;
        long j131 = (j98 - (997805 * j121)) + j130;
        long j132 = j128 - (j130 << 21);
        long j133 = j131 >> 21;
        long j134 = j101 + (136657 * j121) + j133;
        long j135 = j131 - (j133 << 21);
        long j136 = j134 >> 21;
        long j137 = (j104 - (j121 * 683901)) + j136;
        long j138 = j134 - (j136 << 21);
        long j139 = j137 >> 21;
        long j140 = j107 + j139;
        long j141 = j137 - (j139 << 21);
        long j142 = j140 >> 21;
        long j143 = j110 + j142;
        long j144 = j140 - (j142 << 21);
        long j145 = j143 >> 21;
        long j146 = j113 + j145;
        long j147 = j143 - (j145 << 21);
        long j148 = j146 >> 21;
        long j149 = j116 + j148;
        long j150 = j146 - (j148 << 21);
        long j151 = j149 >> 21;
        long j152 = j119 + j151;
        long j153 = j149 - (j151 << 21);
        long j154 = j152 >> 21;
        long j155 = j122 + j154;
        long j156 = j152 - (j154 << 21);
        bArr[0] = (byte) j126;
        bArr[1] = (byte) (j126 >> 8);
        bArr[2] = (byte) ((j126 >> 16) | (j129 << 5));
        bArr[3] = (byte) (j129 >> 3);
        bArr[4] = (byte) (j129 >> 11);
        bArr[5] = (byte) ((j129 >> 19) | (j132 << 2));
        bArr[6] = (byte) (j132 >> 6);
        bArr[7] = (byte) ((j132 >> 14) | (j135 << 7));
        bArr[8] = (byte) (j135 >> 1);
        bArr[9] = (byte) (j135 >> 9);
        bArr[10] = (byte) ((j135 >> 17) | (j138 << 4));
        bArr[11] = (byte) (j138 >> 4);
        bArr[12] = (byte) (j138 >> 12);
        bArr[13] = (byte) ((j138 >> 20) | (j141 << 1));
        bArr[14] = (byte) (j141 >> 7);
        bArr[15] = (byte) ((j141 >> 15) | (j144 << 6));
        bArr[16] = (byte) (j144 >> 2);
        bArr[17] = (byte) (j144 >> 10);
        bArr[18] = (byte) ((j144 >> 18) | (j147 << 3));
        bArr[19] = (byte) (j147 >> 5);
        bArr[20] = (byte) (j147 >> 13);
        bArr[21] = (byte) j150;
        bArr[22] = (byte) (j150 >> 8);
        bArr[23] = (byte) ((j150 >> 16) | (j153 << 5));
        bArr[24] = (byte) (j153 >> 3);
        bArr[25] = (byte) (j153 >> 11);
        bArr[26] = (byte) ((j153 >> 19) | (j156 << 2));
        bArr[27] = (byte) (j156 >> 6);
        bArr[28] = (byte) ((j156 >> 14) | (j155 << 7));
        bArr[29] = (byte) (j155 >> 1);
        bArr[30] = (byte) (j155 >> 9);
        bArr[31] = (byte) (j155 >> 17);
    }

    public static d t(byte[] bArr) {
        int i;
        byte[] bArr2 = new byte[64];
        int i2 = 0;
        while (true) {
            if (i2 >= 32) {
                break;
            }
            int i3 = i2 * 2;
            bArr2[i3 + 0] = (byte) (((bArr[i2] & 255) >> 0) & 15);
            bArr2[i3 + 1] = (byte) (((bArr[i2] & 255) >> 4) & 15);
            i2++;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < 63; i5++) {
            bArr2[i5] = (byte) (bArr2[i5] + i4);
            i4 = (bArr2[i5] + 8) >> 4;
            bArr2[i5] = (byte) (bArr2[i5] - (i4 << 4));
        }
        bArr2[63] = (byte) (bArr2[63] + i4);
        c cVar = new c(b);
        e eVar = new e();
        for (i = 1; i < 64; i += 2) {
            a aVar = new a(f11053a);
            v(aVar, i / 2, bArr2[i]);
            e(cVar, e.d(eVar, cVar), aVar);
        }
        d dVar = new d();
        g(cVar, d.a(dVar, cVar));
        g(cVar, d.a(dVar, cVar));
        g(cVar, d.a(dVar, cVar));
        g(cVar, d.a(dVar, cVar));
        for (int i6 = 0; i6 < 64; i6 += 2) {
            a aVar2 = new a(f11053a);
            v(aVar2, i6 / 2, bArr2[i6]);
            e(cVar, e.d(eVar, cVar), aVar2);
        }
        d dVar2 = new d(cVar);
        if (dVar2.b()) {
            return dVar2;
        }
        throw new IllegalStateException("arithmetic error in scalar multiplication");
    }

    public static byte[] u(byte[] bArr) {
        return t(bArr).c();
    }

    public static void v(a aVar, int i, byte b2) {
        int i2 = (b2 & 255) >> 7;
        int i3 = b2 - (((-i2) & b2) << 1);
        a[][] aVarArr = g.d;
        aVar.a(aVarArr[i][0], i(i3, 1));
        aVar.a(aVarArr[i][1], i(i3, 2));
        aVar.a(aVarArr[i][2], i(i3, 3));
        aVar.a(aVarArr[i][3], i(i3, 4));
        aVar.a(aVarArr[i][4], i(i3, 5));
        aVar.a(aVarArr[i][5], i(i3, 6));
        aVar.a(aVarArr[i][6], i(i3, 7));
        aVar.a(aVarArr[i][7], i(i3, 8));
        long[] copyOf = Arrays.copyOf(aVar.b, 10);
        long[] copyOf2 = Arrays.copyOf(aVar.f11054a, 10);
        long[] copyOf3 = Arrays.copyOf(aVar.c, 10);
        q(copyOf3, copyOf3);
        aVar.a(new a(copyOf, copyOf2, copyOf3), i2);
    }

    public static byte[] w(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, bArr.length);
        MessageDigest engineFactory = EngineFactory.MESSAGE_DIGEST.getInstance("SHA-512");
        engineFactory.update(bArr3, 32, 32);
        engineFactory.update(copyOfRange);
        byte[] digest = engineFactory.digest();
        s(digest);
        byte[] copyOfRange2 = Arrays.copyOfRange(t(digest).c(), 0, 32);
        engineFactory.reset();
        engineFactory.update(copyOfRange2);
        engineFactory.update(bArr2);
        engineFactory.update(copyOfRange);
        byte[] digest2 = engineFactory.digest();
        s(digest2);
        byte[] bArr4 = new byte[32];
        p(bArr4, digest2, bArr3, digest);
        return Bytes.concat(copyOfRange2, bArr4);
    }

    public static byte[] x(byte[] bArr) {
        int i;
        byte[] bArr2 = new byte[256];
        for (int i2 = 0; i2 < 256; i2++) {
            bArr2[i2] = (byte) (1 & ((bArr[i2 >> 3] & 255) >> (i2 & 7)));
        }
        for (int i3 = 0; i3 < 256; i3++) {
            if (bArr2[i3] != 0) {
                for (int i4 = 1; i4 <= 6 && (i = i3 + i4) < 256; i4++) {
                    if (bArr2[i] != 0) {
                        if (bArr2[i3] + (bArr2[i] << i4) <= 15) {
                            bArr2[i3] = (byte) (bArr2[i3] + (bArr2[i] << i4));
                            bArr2[i] = 0;
                        } else if (bArr2[i3] - (bArr2[i] << i4) >= -15) {
                            bArr2[i3] = (byte) (bArr2[i3] - (bArr2[i] << i4));
                            while (true) {
                                if (i >= 256) {
                                    break;
                                } else if (bArr2[i] == 0) {
                                    bArr2[i] = 1;
                                    break;
                                } else {
                                    bArr2[i] = 0;
                                    i++;
                                }
                            }
                        }
                    }
                }
            }
        }
        return bArr2;
    }

    public static void y(c cVar, e eVar, a aVar) {
        long[] jArr = new long[10];
        long[] jArr2 = cVar.f11055a.f11056a;
        d dVar = eVar.f11057a;
        h.q(jArr2, dVar.b, dVar.f11056a);
        long[] jArr3 = cVar.f11055a.b;
        d dVar2 = eVar.f11057a;
        h.o(jArr3, dVar2.b, dVar2.f11056a);
        long[] jArr4 = cVar.f11055a.b;
        h.f(jArr4, jArr4, aVar.f11054a);
        d dVar3 = cVar.f11055a;
        h.f(dVar3.c, dVar3.f11056a, aVar.b);
        h.f(cVar.b, eVar.b, aVar.c);
        aVar.b(cVar.f11055a.f11056a, eVar.f11057a.c);
        long[] jArr5 = cVar.f11055a.f11056a;
        h.q(jArr, jArr5, jArr5);
        d dVar4 = cVar.f11055a;
        h.o(dVar4.f11056a, dVar4.c, dVar4.b);
        d dVar5 = cVar.f11055a;
        long[] jArr6 = dVar5.b;
        h.q(jArr6, dVar5.c, jArr6);
        h.o(cVar.f11055a.c, jArr, cVar.b);
        long[] jArr7 = cVar.b;
        h.q(jArr7, jArr, jArr7);
    }

    public static boolean z(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        if (bArr2.length != 64) {
            return false;
        }
        byte[] copyOfRange = Arrays.copyOfRange(bArr2, 32, 64);
        if (m(copyOfRange)) {
            MessageDigest engineFactory = EngineFactory.MESSAGE_DIGEST.getInstance("SHA-512");
            engineFactory.update(bArr2, 0, 32);
            engineFactory.update(bArr3);
            engineFactory.update(bArr);
            byte[] digest = engineFactory.digest();
            s(digest);
            byte[] c2 = f(digest, e.c(bArr3), copyOfRange).c();
            for (int i = 0; i < 32; i++) {
                if (c2[i] != bArr2[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
