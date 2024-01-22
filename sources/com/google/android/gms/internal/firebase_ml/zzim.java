package com.google.android.gms.internal.firebase_ml;
/* loaded from: classes7.dex */
public final class zzim extends zzip {
    public static final byte[] h = {13, 10};
    public static final byte[] i = {65, 66, 67, 68, com.crrepa.c.a.E0, com.htsmart.wristband2.a.a.a.U0, 71, com.htsmart.wristband2.a.a.a.W0, 73, com.htsmart.wristband2.a.a.a.Y0, 75, com.htsmart.wristband2.a.a.a.a1, 77, com.htsmart.wristband2.a.a.a.c1, 79, com.htsmart.wristband2.a.a.a.d1, 81, 82, 83, 84, 85, 86, 87, com.htsmart.wristband2.a.a.a.o1, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, com.crrepa.c.a.Z0, com.htsmart.wristband2.a.a.a.J1, 113, 114, 115, 116, 117, com.htsmart.wristband2.a.a.a.R1, 119, 120, com.htsmart.wristband2.a.a.a.U1, com.htsmart.wristband2.a.a.a.V1, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    public static final byte[] j = {65, 66, 67, 68, com.crrepa.c.a.E0, com.htsmart.wristband2.a.a.a.U0, 71, com.htsmart.wristband2.a.a.a.W0, 73, com.htsmart.wristband2.a.a.a.Y0, 75, com.htsmart.wristband2.a.a.a.a1, 77, com.htsmart.wristband2.a.a.a.c1, 79, com.htsmart.wristband2.a.a.a.d1, 81, 82, 83, 84, 85, 86, 87, com.htsmart.wristband2.a.a.a.o1, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, com.crrepa.c.a.Z0, com.htsmart.wristband2.a.a.a.J1, 113, 114, 115, 116, 117, com.htsmart.wristband2.a.a.a.R1, 119, 120, com.htsmart.wristband2.a.a.a.U1, com.htsmart.wristband2.a.a.a.V1, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
    public static final byte[] k = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};
    public final byte[] d;
    public final byte[] e;
    public final byte[] f;
    public final int g;

    public zzim() {
        this(0);
    }

    public static String zzb(byte[] bArr) {
        if (bArr != null && bArr.length != 0) {
            zzim zzimVar = new zzim(0, h, true);
            long zzc = zzimVar.zzc(bArr);
            if (zzc <= 2147483647L) {
                if (bArr.length != 0) {
                    x0 x0Var = new x0();
                    zzimVar.a(bArr, 0, bArr.length, x0Var);
                    zzimVar.a(bArr, 0, -1, x0Var);
                    int i2 = x0Var.c;
                    int i3 = x0Var.d;
                    int i4 = i2 - i3;
                    byte[] bArr2 = new byte[i4];
                    byte[] bArr3 = x0Var.b;
                    if (bArr3 != null) {
                        int min = Math.min(bArr3 != null ? i2 - i3 : 0, i4);
                        System.arraycopy(x0Var.b, x0Var.d, bArr2, 0, min);
                        int i5 = x0Var.d + min;
                        x0Var.d = i5;
                        if (i5 >= x0Var.c) {
                            x0Var.b = null;
                        }
                    }
                    bArr = bArr2;
                }
            } else {
                throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + zzc + ") than the specified maximum size of 2147483647");
            }
        }
        return zzir.zzd(bArr);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzip
    public final void a(byte[] bArr, int i2, int i3, x0 x0Var) {
        if (x0Var.e) {
            return;
        }
        if (i3 >= 0) {
            int i4 = 0;
            while (i4 < i3) {
                byte[] zza = zza(this.g, x0Var);
                int i5 = (x0Var.g + 1) % 3;
                x0Var.g = i5;
                int i6 = i2 + 1;
                int i7 = bArr[i2];
                if (i7 < 0) {
                    i7 += 256;
                }
                int i8 = (x0Var.f8749a << 8) + i7;
                x0Var.f8749a = i8;
                if (i5 == 0) {
                    int i9 = x0Var.c;
                    int i10 = i9 + 1;
                    x0Var.c = i10;
                    byte[] bArr2 = this.d;
                    zza[i9] = bArr2[(i8 >> 18) & 63];
                    int i11 = i10 + 1;
                    x0Var.c = i11;
                    zza[i10] = bArr2[(i8 >> 12) & 63];
                    int i12 = i11 + 1;
                    x0Var.c = i12;
                    zza[i11] = bArr2[(i8 >> 6) & 63];
                    int i13 = i12 + 1;
                    x0Var.c = i13;
                    zza[i12] = bArr2[i8 & 63];
                    int i14 = x0Var.f + 4;
                    x0Var.f = i14;
                    int i15 = this.zzafz;
                    if (i15 > 0 && i15 <= i14) {
                        byte[] bArr3 = this.f;
                        System.arraycopy(bArr3, 0, zza, i13, bArr3.length);
                        x0Var.c += this.f.length;
                        x0Var.f = 0;
                    }
                }
                i4++;
                i2 = i6;
            }
            return;
        }
        x0Var.e = true;
        if (x0Var.g == 0 && this.zzafz == 0) {
            return;
        }
        byte[] zza2 = zza(this.g, x0Var);
        int i16 = x0Var.c;
        int i17 = x0Var.g;
        if (i17 != 0) {
            if (i17 == 1) {
                int i18 = i16 + 1;
                x0Var.c = i18;
                byte[] bArr4 = this.d;
                int i19 = x0Var.f8749a;
                zza2[i16] = bArr4[(i19 >> 2) & 63];
                int i20 = i18 + 1;
                x0Var.c = i20;
                zza2[i18] = bArr4[(i19 << 4) & 63];
                if (bArr4 == i) {
                    int i21 = i20 + 1;
                    x0Var.c = i21;
                    byte b = this.zzafw;
                    zza2[i20] = b;
                    x0Var.c = i21 + 1;
                    zza2[i21] = b;
                }
            } else if (i17 == 2) {
                int i22 = i16 + 1;
                x0Var.c = i22;
                byte[] bArr5 = this.d;
                int i23 = x0Var.f8749a;
                zza2[i16] = bArr5[(i23 >> 10) & 63];
                int i24 = i22 + 1;
                x0Var.c = i24;
                zza2[i22] = bArr5[(i23 >> 4) & 63];
                int i25 = i24 + 1;
                x0Var.c = i25;
                zza2[i24] = bArr5[(i23 << 2) & 63];
                if (bArr5 == i) {
                    x0Var.c = i25 + 1;
                    zza2[i25] = this.zzafw;
                }
            } else {
                throw new IllegalStateException("Impossible modulus " + x0Var.g);
            }
        }
        int i26 = x0Var.f;
        int i27 = x0Var.c;
        int i28 = i26 + (i27 - i16);
        x0Var.f = i28;
        if (this.zzafz <= 0 || i28 <= 0) {
            return;
        }
        byte[] bArr6 = this.f;
        System.arraycopy(bArr6, 0, zza2, i27, bArr6.length);
        x0Var.c += this.f.length;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzip
    public final boolean zza(byte b) {
        if (b >= 0) {
            byte[] bArr = this.e;
            return b < bArr.length && bArr[b] != -1;
        }
        return false;
    }

    public zzim(int i2) {
        this(0, h);
    }

    public zzim(int i2, byte[] bArr) {
        this(0, bArr, false);
    }

    public zzim(int i2, byte[] bArr, boolean z) {
        super(3, 4, i2, bArr == null ? 0 : bArr.length);
        boolean z2;
        this.e = k;
        if (bArr != null) {
            for (byte b : bArr) {
                if (this.zzafw == b || zza(b)) {
                    z2 = true;
                    break;
                }
            }
            z2 = false;
            if (z2) {
                throw new IllegalArgumentException("lineSeparator must not contain base64 characters: [" + zzir.zzd(bArr) + "]");
            } else if (i2 > 0) {
                this.g = bArr.length + 4;
                byte[] bArr2 = new byte[bArr.length];
                this.f = bArr2;
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            } else {
                this.g = 4;
                this.f = null;
            }
        } else {
            this.g = 4;
            this.f = null;
        }
        this.d = z ? j : i;
    }
}
