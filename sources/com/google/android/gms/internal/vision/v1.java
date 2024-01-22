package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.Arrays;
/* loaded from: classes10.dex */
public final class v1 extends zzft {
    public final byte[] d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;

    public v1(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.j = Integer.MAX_VALUE;
        this.d = bArr;
        this.e = i2 + i;
        this.g = i;
        this.h = i;
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final long b() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte i2 = i();
            j |= (i2 & Byte.MAX_VALUE) << i;
            if ((i2 & 128) == 0) {
                return j;
            }
        }
        throw zzhc.zzgo();
    }

    public final void c(int i) throws IOException {
        if (i >= 0) {
            int i2 = this.e;
            int i3 = this.g;
            if (i <= i2 - i3) {
                this.g = i3 + i;
                return;
            }
        }
        if (i < 0) {
            throw zzhc.zzgn();
        }
        throw zzhc.zzgm();
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0066, code lost:
        if (r2[r3] >= 0) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int d() throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.g
            int r1 = r5.e
            if (r1 == r0) goto L6b
            byte[] r2 = r5.d
            int r3 = r0 + 1
            r0 = r2[r0]
            if (r0 < 0) goto L11
            r5.g = r3
            return r0
        L11:
            int r1 = r1 - r3
            r4 = 9
            if (r1 < r4) goto L6b
            int r1 = r3 + 1
            r3 = r2[r3]
            int r3 = r3 << 7
            r0 = r0 ^ r3
            if (r0 >= 0) goto L22
            r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
            goto L68
        L22:
            int r3 = r1 + 1
            r1 = r2[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L2f
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
        L2d:
            r1 = r3
            goto L68
        L2f:
            int r1 = r3 + 1
            r3 = r2[r3]
            int r3 = r3 << 21
            r0 = r0 ^ r3
            if (r0 >= 0) goto L3d
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L68
        L3d:
            int r3 = r1 + 1
            r1 = r2[r1]
            int r4 = r1 << 28
            r0 = r0 ^ r4
            r4 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r4
            if (r1 >= 0) goto L2d
            int r1 = r3 + 1
            r3 = r2[r3]
            if (r3 >= 0) goto L68
            int r3 = r1 + 1
            r1 = r2[r1]
            if (r1 >= 0) goto L2d
            int r1 = r3 + 1
            r3 = r2[r3]
            if (r3 >= 0) goto L68
            int r3 = r1 + 1
            r1 = r2[r1]
            if (r1 >= 0) goto L2d
            int r1 = r3 + 1
            r2 = r2[r3]
            if (r2 < 0) goto L6b
        L68:
            r5.g = r1
            return r0
        L6b:
            long r0 = r5.b()
            int r0 = (int) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.v1.d():int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b0, code lost:
        if (r2[r0] >= 0) goto L40;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long e() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 189
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.v1.e():long");
    }

    public final int f() throws IOException {
        int i = this.g;
        if (this.e - i >= 4) {
            byte[] bArr = this.d;
            this.g = i + 4;
            return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
        }
        throw zzhc.zzgm();
    }

    public final long g() throws IOException {
        int i = this.g;
        if (this.e - i >= 8) {
            byte[] bArr = this.d;
            this.g = i + 8;
            return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
        }
        throw zzhc.zzgm();
    }

    public final void h() {
        int i = this.e + this.f;
        this.e = i;
        int i2 = i - this.h;
        int i3 = this.j;
        if (i2 > i3) {
            int i4 = i2 - i3;
            this.f = i4;
            this.e = i - i4;
            return;
        }
        this.f = 0;
    }

    public final byte i() throws IOException {
        int i = this.g;
        if (i != this.e) {
            byte[] bArr = this.d;
            this.g = i + 1;
            return bArr[i];
        }
        throw zzhc.zzgm();
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(g());
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(f());
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final String readString() throws IOException {
        int d = d();
        if (d > 0) {
            int i = this.e;
            int i2 = this.g;
            if (d <= i - i2) {
                String str = new String(this.d, i2, d, zzgt.f10024a);
                this.g += d;
                return str;
            }
        }
        if (d == 0) {
            return "";
        }
        if (d < 0) {
            throw zzhc.zzgn();
        }
        throw zzhc.zzgm();
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final void zzar(int i) throws zzhc {
        if (this.i != i) {
            throw zzhc.zzgq();
        }
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final boolean zzas(int i) throws IOException {
        int zzex;
        int i2 = i & 7;
        int i3 = 0;
        if (i2 == 0) {
            if (this.e - this.g >= 10) {
                while (i3 < 10) {
                    byte[] bArr = this.d;
                    int i4 = this.g;
                    this.g = i4 + 1;
                    if (bArr[i4] < 0) {
                        i3++;
                    }
                }
                throw zzhc.zzgo();
            }
            while (i3 < 10) {
                if (i() < 0) {
                    i3++;
                }
            }
            throw zzhc.zzgo();
            return true;
        } else if (i2 == 1) {
            c(8);
            return true;
        } else if (i2 == 2) {
            c(d());
            return true;
        } else if (i2 != 3) {
            if (i2 != 4) {
                if (i2 == 5) {
                    c(4);
                    return true;
                }
                throw zzhc.zzgr();
            }
            return false;
        } else {
            do {
                zzex = zzex();
                if (zzex == 0) {
                    break;
                }
            } while (zzas(zzex));
            zzar(((i >>> 3) << 3) | 4);
            return true;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final int zzat(int i) throws zzhc {
        if (i >= 0) {
            int zzez = i + zzez();
            int i2 = this.j;
            if (zzez <= i2) {
                this.j = zzez;
                h();
                return i2;
            }
            throw zzhc.zzgm();
        }
        throw zzhc.zzgn();
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final void zzau(int i) {
        this.j = i;
        h();
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final boolean zzdt() throws IOException {
        return this.g == this.e;
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final long zzdw() throws IOException {
        return e();
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final long zzdx() throws IOException {
        return e();
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final int zzdy() throws IOException {
        return d();
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final long zzdz() throws IOException {
        return g();
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final int zzea() throws IOException {
        return f();
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final boolean zzeb() throws IOException {
        return e() != 0;
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final String zzec() throws IOException {
        int d = d();
        if (d > 0) {
            int i = this.e;
            int i2 = this.g;
            if (d <= i - i2) {
                String j = m4.j(this.d, i2, d);
                this.g += d;
                return j;
            }
        }
        if (d == 0) {
            return "";
        }
        if (d <= 0) {
            throw zzhc.zzgn();
        }
        throw zzhc.zzgm();
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final zzfh zzed() throws IOException {
        byte[] bArr;
        int d = d();
        if (d > 0) {
            int i = this.e;
            int i2 = this.g;
            if (d <= i - i2) {
                zzfh zza = zzfh.zza(this.d, i2, d);
                this.g += d;
                return zza;
            }
        }
        if (d == 0) {
            return zzfh.zzsd;
        }
        if (d > 0) {
            int i3 = this.e;
            int i4 = this.g;
            if (d <= i3 - i4) {
                int i5 = d + i4;
                this.g = i5;
                bArr = Arrays.copyOfRange(this.d, i4, i5);
                return zzfh.zzd(bArr);
            }
        }
        if (d <= 0) {
            if (d == 0) {
                bArr = zzgt.zzxi;
                return zzfh.zzd(bArr);
            }
            throw zzhc.zzgn();
        }
        throw zzhc.zzgm();
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final int zzee() throws IOException {
        return d();
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final int zzef() throws IOException {
        return d();
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final int zzeg() throws IOException {
        return f();
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final long zzeh() throws IOException {
        return g();
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final int zzei() throws IOException {
        return zzft.zzav(d());
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final long zzej() throws IOException {
        return zzft.zzr(e());
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final int zzex() throws IOException {
        if (zzdt()) {
            this.i = 0;
            return 0;
        }
        int d = d();
        this.i = d;
        if ((d >>> 3) != 0) {
            return d;
        }
        throw zzhc.zzgp();
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final int zzez() {
        return this.g - this.h;
    }
}
