package org.bouncycastle.cert.selector;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.util.Pack;
/* loaded from: classes12.dex */
public class a {

    /* renamed from: org.bouncycastle.cert.selector.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public static abstract class AbstractC0900a {

        /* renamed from: a  reason: collision with root package name */
        public byte[] f14516a = new byte[4];
        public int b = 0;
        public long c;

        public void a() {
            long j = this.c << 3;
            byte b = Byte.MIN_VALUE;
            while (true) {
                f(b);
                if (this.b == 0) {
                    c(j);
                    b();
                    return;
                }
                b = 0;
            }
        }

        public abstract void b();

        public abstract void c(long j);

        public abstract void d(byte[] bArr, int i);

        public void e() {
            this.c = 0L;
            this.b = 0;
            int i = 0;
            while (true) {
                byte[] bArr = this.f14516a;
                if (i >= bArr.length) {
                    return;
                }
                bArr[i] = 0;
                i++;
            }
        }

        public void f(byte b) {
            byte[] bArr = this.f14516a;
            int i = this.b;
            int i2 = i + 1;
            this.b = i2;
            bArr[i] = b;
            if (i2 == bArr.length) {
                d(bArr, 0);
                this.b = 0;
            }
            this.c++;
        }

        public void g(byte[] bArr, int i, int i2) {
            while (this.b != 0 && i2 > 0) {
                f(bArr[i]);
                i++;
                i2--;
            }
            while (i2 > this.f14516a.length) {
                d(bArr, i);
                byte[] bArr2 = this.f14516a;
                i += bArr2.length;
                i2 -= bArr2.length;
                this.c += bArr2.length;
            }
            while (i2 > 0) {
                f(bArr[i]);
                i++;
                i2--;
            }
        }
    }

    /* loaded from: classes12.dex */
    public static class b extends AbstractC0900a {
        public int d;
        public int e;
        public int f;
        public int g;
        public int h;
        public int[] i = new int[80];
        public int j;

        public b() {
            e();
        }

        @Override // org.bouncycastle.cert.selector.a.AbstractC0900a
        public void b() {
            int i;
            int i2;
            int i3;
            int i4;
            int i5;
            int i6;
            int i7;
            int i8;
            int i9;
            int i10;
            int i11;
            int i12;
            for (int i13 = 16; i13 < 80; i13++) {
                int[] iArr = this.i;
                int i14 = ((iArr[i13 - 3] ^ iArr[i13 - 8]) ^ iArr[i13 - 14]) ^ iArr[i13 - 16];
                iArr[i13] = (i14 >>> 31) | (i14 << 1);
            }
            int i15 = this.d;
            int i16 = this.e;
            int i17 = this.f;
            int i18 = this.g;
            int i19 = this.h;
            int i20 = 0;
            int i21 = 0;
            while (i20 < 4) {
                int i22 = i21 + 1;
                int i23 = i19 + ((i15 << 5) | (i15 >>> 27)) + i(i16, i17, i18) + this.i[i21] + 1518500249;
                int i24 = (i16 >>> 2) | (i16 << 30);
                int i25 = i22 + 1;
                int i26 = i18 + ((i23 << 5) | (i23 >>> 27)) + i(i15, i24, i17) + this.i[i22] + 1518500249;
                int i27 = (i15 >>> 2) | (i15 << 30);
                int i28 = i25 + 1;
                int i29 = i17 + ((i26 << 5) | (i26 >>> 27)) + i(i23, i27, i24) + this.i[i25] + 1518500249;
                i19 = (i23 >>> 2) | (i23 << 30);
                int i30 = i28 + 1;
                i16 = i24 + ((i29 << 5) | (i29 >>> 27)) + i(i26, i19, i27) + this.i[i28] + 1518500249;
                i18 = (i26 >>> 2) | (i26 << 30);
                i15 = i27 + ((i16 << 5) | (i16 >>> 27)) + i(i29, i18, i19) + this.i[i30] + 1518500249;
                i17 = (i29 >>> 2) | (i29 << 30);
                i20++;
                i21 = i30 + 1;
            }
            int i31 = 0;
            while (i31 < 4) {
                int i32 = i21 + 1;
                int l = i19 + ((i15 << 5) | (i15 >>> 27)) + l(i16, i17, i18) + this.i[i21] + 1859775393;
                int i33 = (i16 >>> 2) | (i16 << 30);
                int i34 = i32 + 1;
                int l2 = i18 + ((l << 5) | (l >>> 27)) + l(i15, i33, i17) + this.i[i32] + 1859775393;
                int i35 = (i15 >>> 2) | (i15 << 30);
                int i36 = i34 + 1;
                int l3 = i17 + ((l2 << 5) | (l2 >>> 27)) + l(l, i35, i33) + this.i[i34] + 1859775393;
                i19 = (l >>> 2) | (l << 30);
                int i37 = i36 + 1;
                i16 = i33 + ((l3 << 5) | (l3 >>> 27)) + l(l2, i19, i35) + this.i[i36] + 1859775393;
                i18 = (l2 >>> 2) | (l2 << 30);
                i15 = i35 + ((i16 << 5) | (i16 >>> 27)) + l(l3, i18, i19) + this.i[i37] + 1859775393;
                i17 = (l3 >>> 2) | (l3 << 30);
                i31++;
                i21 = i37 + 1;
            }
            int i38 = 0;
            while (i38 < 4) {
                int j = i19 + (((((i15 << 5) | (i15 >>> 27)) + j(i16, i17, i18)) + this.i[i21]) - 1894007588);
                int j2 = i18 + (((((j << 5) | (j >>> 27)) + j(i15, i8, i17)) + this.i[i7]) - 1894007588);
                int j3 = i17 + (((((j2 << 5) | (j2 >>> 27)) + j(j, i10, i8)) + this.i[i9]) - 1894007588);
                i19 = (j >>> 2) | (j << 30);
                i16 = ((i16 >>> 2) | (i16 << 30)) + (((((j3 << 5) | (j3 >>> 27)) + j(j2, i19, i10)) + this.i[i11]) - 1894007588);
                i18 = (j2 >>> 2) | (j2 << 30);
                i15 = ((i15 >>> 2) | (i15 << 30)) + (((((i16 << 5) | (i16 >>> 27)) + j(j3, i18, i19)) + this.i[i12]) - 1894007588);
                i17 = (j3 >>> 2) | (j3 << 30);
                i38++;
                i21 = i21 + 1 + 1 + 1 + 1 + 1;
            }
            int i39 = 0;
            while (i39 <= 3) {
                int l4 = i19 + (((((i15 << 5) | (i15 >>> 27)) + l(i16, i17, i18)) + this.i[i21]) - 899497514);
                int l5 = i18 + (((((l4 << 5) | (l4 >>> 27)) + l(i15, i2, i17)) + this.i[i]) - 899497514);
                int l6 = i17 + (((((l5 << 5) | (l5 >>> 27)) + l(l4, i4, i2)) + this.i[i3]) - 899497514);
                i19 = (l4 >>> 2) | (l4 << 30);
                i16 = ((i16 >>> 2) | (i16 << 30)) + (((((l6 << 5) | (l6 >>> 27)) + l(l5, i19, i4)) + this.i[i5]) - 899497514);
                i18 = (l5 >>> 2) | (l5 << 30);
                i15 = ((i15 >>> 2) | (i15 << 30)) + (((((i16 << 5) | (i16 >>> 27)) + l(l6, i18, i19)) + this.i[i6]) - 899497514);
                i17 = (l6 >>> 2) | (l6 << 30);
                i39++;
                i21 = i21 + 1 + 1 + 1 + 1 + 1;
            }
            this.d += i15;
            this.e += i16;
            this.f += i17;
            this.g += i18;
            this.h += i19;
            this.j = 0;
            for (int i40 = 0; i40 < 16; i40++) {
                this.i[i40] = 0;
            }
        }

        @Override // org.bouncycastle.cert.selector.a.AbstractC0900a
        public void c(long j) {
            if (this.j > 14) {
                b();
            }
            int[] iArr = this.i;
            iArr[14] = (int) (j >>> 32);
            iArr[15] = (int) (j & (-1));
        }

        @Override // org.bouncycastle.cert.selector.a.AbstractC0900a
        public void d(byte[] bArr, int i) {
            int i2 = i + 1;
            int i3 = i2 + 1;
            int i4 = (bArr[i3 + 1] & 255) | (bArr[i] << 24) | ((bArr[i2] & 255) << 16) | ((bArr[i3] & 255) << 8);
            int[] iArr = this.i;
            int i5 = this.j;
            iArr[i5] = i4;
            int i6 = i5 + 1;
            this.j = i6;
            if (i6 == 16) {
                b();
            }
        }

        @Override // org.bouncycastle.cert.selector.a.AbstractC0900a
        public void e() {
            super.e();
            this.d = 1732584193;
            this.e = -271733879;
            this.f = -1732584194;
            this.g = 271733878;
            this.h = -1009589776;
            this.j = 0;
            int i = 0;
            while (true) {
                int[] iArr = this.i;
                if (i == iArr.length) {
                    return;
                }
                iArr[i] = 0;
                i++;
            }
        }

        public int h(byte[] bArr, int i) {
            a();
            Pack.intToBigEndian(this.d, bArr, i);
            Pack.intToBigEndian(this.e, bArr, i + 4);
            Pack.intToBigEndian(this.f, bArr, i + 8);
            Pack.intToBigEndian(this.g, bArr, i + 12);
            Pack.intToBigEndian(this.h, bArr, i + 16);
            e();
            return 20;
        }

        public final int i(int i, int i2, int i3) {
            return ((~i) & i3) | (i2 & i);
        }

        public final int j(int i, int i2, int i3) {
            return (i & i3) | (i & i2) | (i2 & i3);
        }

        public int k() {
            return 20;
        }

        public final int l(int i, int i2, int i3) {
            return (i ^ i2) ^ i3;
        }
    }

    public static byte[] a(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        b bVar = new b();
        byte[] bArr = new byte[bVar.k()];
        try {
            byte[] encoded = subjectPublicKeyInfo.getEncoded(ASN1Encoding.DER);
            bVar.g(encoded, 0, encoded.length);
            bVar.h(bArr, 0);
            return bArr;
        } catch (IOException unused) {
            return new byte[0];
        }
    }
}
