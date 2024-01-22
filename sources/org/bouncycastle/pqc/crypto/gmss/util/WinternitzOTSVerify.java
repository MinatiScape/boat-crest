package org.bouncycastle.pqc.crypto.gmss.util;

import org.bouncycastle.crypto.Digest;
/* loaded from: classes13.dex */
public class WinternitzOTSVerify {

    /* renamed from: a  reason: collision with root package name */
    public Digest f15297a;
    public int b;

    public WinternitzOTSVerify(Digest digest, int i) {
        this.b = i;
        this.f15297a = digest;
    }

    public byte[] Verify(byte[] bArr, byte[] bArr2) {
        int i;
        int i2;
        int i3;
        byte[] bArr3 = bArr2;
        int digestSize = this.f15297a.getDigestSize();
        byte[] bArr4 = new byte[digestSize];
        int i4 = 0;
        this.f15297a.update(bArr, 0, bArr.length);
        int digestSize2 = this.f15297a.getDigestSize();
        byte[] bArr5 = new byte[digestSize2];
        this.f15297a.doFinal(bArr5, 0);
        int i5 = digestSize << 3;
        int i6 = this.b;
        int i7 = ((i6 - 1) + i5) / i6;
        int log = getLog((i7 << i6) + 1);
        int i8 = this.b;
        int i9 = ((((log + i8) - 1) / i8) + i7) * digestSize;
        if (i9 != bArr3.length) {
            return null;
        }
        byte[] bArr6 = new byte[i9];
        int i10 = 8;
        if (8 % i8 == 0) {
            int i11 = 8 / i8;
            int i12 = (1 << i8) - 1;
            byte[] bArr7 = new byte[digestSize];
            int i13 = 0;
            int i14 = 0;
            int i15 = 0;
            while (i13 < digestSize2) {
                while (i4 < i11) {
                    int i16 = bArr5[i13] & i12;
                    i14 += i16;
                    int i17 = digestSize2;
                    int i18 = i15 * digestSize;
                    int i19 = i11;
                    System.arraycopy(bArr3, i18, bArr7, 0, digestSize);
                    int i20 = i16;
                    while (i20 < i12) {
                        this.f15297a.update(bArr7, 0, bArr7.length);
                        bArr7 = new byte[this.f15297a.getDigestSize()];
                        this.f15297a.doFinal(bArr7, 0);
                        i20++;
                        i14 = i14;
                        i9 = i9;
                    }
                    System.arraycopy(bArr7, 0, bArr6, i18, digestSize);
                    bArr5[i13] = (byte) (bArr5[i13] >>> this.b);
                    i15++;
                    i4++;
                    digestSize2 = i17;
                    bArr3 = bArr2;
                    i11 = i19;
                }
                i13++;
                bArr3 = bArr2;
                i4 = 0;
            }
            i = i9;
            int i21 = (i7 << this.b) - i14;
            int i22 = 0;
            while (i22 < log) {
                int i23 = i15 * digestSize;
                System.arraycopy(bArr2, i23, bArr7, 0, digestSize);
                for (int i24 = i21 & i12; i24 < i12; i24++) {
                    this.f15297a.update(bArr7, 0, bArr7.length);
                    bArr7 = new byte[this.f15297a.getDigestSize()];
                    this.f15297a.doFinal(bArr7, 0);
                }
                System.arraycopy(bArr7, 0, bArr6, i23, digestSize);
                int i25 = this.b;
                i21 >>>= i25;
                i15++;
                i22 += i25;
            }
        } else {
            i = i9;
            if (i8 < 8) {
                int i26 = digestSize / i8;
                int i27 = (1 << i8) - 1;
                byte[] bArr8 = new byte[digestSize];
                int i28 = 0;
                int i29 = 0;
                int i30 = 0;
                int i31 = 0;
                while (i28 < i26) {
                    int i32 = 0;
                    long j = 0;
                    while (i32 < this.b) {
                        j ^= (bArr5[i29] & 255) << (i32 << 3);
                        i29++;
                        i32++;
                        bArr8 = bArr8;
                    }
                    int i33 = 0;
                    while (i33 < i10) {
                        int i34 = i28;
                        int i35 = (int) (j & i27);
                        i30 += i35;
                        int i36 = i31 * digestSize;
                        System.arraycopy(bArr3, i36, bArr8, 0, digestSize);
                        while (true) {
                            int i37 = i26;
                            if (i35 < i27) {
                                this.f15297a.update(bArr8, 0, bArr8.length);
                                bArr8 = new byte[this.f15297a.getDigestSize()];
                                this.f15297a.doFinal(bArr8, 0);
                                i35++;
                                i26 = i37;
                                i29 = i29;
                            }
                        }
                        System.arraycopy(bArr8, 0, bArr6, i36, digestSize);
                        j >>>= this.b;
                        i31++;
                        i33++;
                        i28 = i34;
                        i10 = 8;
                    }
                    i28++;
                    i10 = 8;
                }
                byte[] bArr9 = bArr8;
                int i38 = digestSize % this.b;
                long j2 = 0;
                for (int i39 = 0; i39 < i38; i39++) {
                    j2 ^= (bArr5[i29] & 255) << (i39 << 3);
                    i29++;
                }
                int i40 = i38 << 3;
                byte[] bArr10 = bArr9;
                int i41 = 0;
                while (i41 < i40) {
                    int i42 = (int) (j2 & i27);
                    i30 += i42;
                    int i43 = i31 * digestSize;
                    System.arraycopy(bArr3, i43, bArr10, 0, digestSize);
                    while (i42 < i27) {
                        this.f15297a.update(bArr10, 0, bArr10.length);
                        bArr10 = new byte[this.f15297a.getDigestSize()];
                        this.f15297a.doFinal(bArr10, 0);
                        i42++;
                    }
                    System.arraycopy(bArr10, 0, bArr6, i43, digestSize);
                    int i44 = this.b;
                    j2 >>>= i44;
                    i31++;
                    i41 += i44;
                }
                int i45 = (i7 << this.b) - i30;
                int i46 = 0;
                while (i46 < log) {
                    int i47 = i31 * digestSize;
                    System.arraycopy(bArr3, i47, bArr10, 0, digestSize);
                    for (int i48 = i45 & i27; i48 < i27; i48++) {
                        this.f15297a.update(bArr10, 0, bArr10.length);
                        bArr10 = new byte[this.f15297a.getDigestSize()];
                        this.f15297a.doFinal(bArr10, 0);
                    }
                    System.arraycopy(bArr10, 0, bArr6, i47, digestSize);
                    int i49 = this.b;
                    i45 >>>= i49;
                    i31++;
                    i46 += i49;
                }
            } else if (i8 < 57) {
                int i50 = i5 - i8;
                int i51 = (1 << i8) - 1;
                byte[] bArr11 = new byte[digestSize];
                int i52 = 0;
                int i53 = 0;
                int i54 = 0;
                while (i53 <= i50) {
                    int i55 = i53 >>> 3;
                    int i56 = i53 % 8;
                    int i57 = i53 + this.b;
                    int i58 = 0;
                    long j3 = 0;
                    while (true) {
                        i2 = i50;
                        if (i55 >= ((i57 + 7) >>> 3)) {
                            break;
                        }
                        j3 ^= (bArr5[i55] & 255) << (i58 << 3);
                        i58++;
                        i55++;
                        i50 = i2;
                        log = log;
                        i7 = i7;
                    }
                    int i59 = log;
                    int i60 = i7;
                    long j4 = i51;
                    long j5 = (j3 >>> i56) & j4;
                    int i61 = i57;
                    i54 = (int) (i54 + j5);
                    int i62 = i52 * digestSize;
                    System.arraycopy(bArr3, i62, bArr11, 0, digestSize);
                    while (true) {
                        i3 = i61;
                        if (j5 < j4) {
                            this.f15297a.update(bArr11, 0, bArr11.length);
                            bArr11 = new byte[this.f15297a.getDigestSize()];
                            this.f15297a.doFinal(bArr11, 0);
                            j5++;
                            i61 = i3;
                            i54 = i54;
                        }
                    }
                    System.arraycopy(bArr11, 0, bArr6, i62, digestSize);
                    i52++;
                    i53 = i3;
                    i50 = i2;
                    log = i59;
                    i7 = i60;
                }
                int i63 = log;
                int i64 = i7;
                int i65 = i53 >>> 3;
                if (i65 < digestSize) {
                    int i66 = i53 % 8;
                    int i67 = 0;
                    long j6 = 0;
                    while (i65 < digestSize) {
                        j6 ^= (bArr5[i65] & 255) << (i67 << 3);
                        i67++;
                        i65++;
                    }
                    long j7 = i51;
                    long j8 = (j6 >>> i66) & j7;
                    i54 = (int) (i54 + j8);
                    int i68 = i52 * digestSize;
                    System.arraycopy(bArr3, i68, bArr11, 0, digestSize);
                    while (j8 < j7) {
                        this.f15297a.update(bArr11, 0, bArr11.length);
                        bArr11 = new byte[this.f15297a.getDigestSize()];
                        this.f15297a.doFinal(bArr11, 0);
                        j8++;
                    }
                    System.arraycopy(bArr11, 0, bArr6, i68, digestSize);
                    i52++;
                }
                int i69 = (i64 << this.b) - i54;
                int i70 = 0;
                while (i70 < i63) {
                    int i71 = i52 * digestSize;
                    System.arraycopy(bArr3, i71, bArr11, 0, digestSize);
                    for (long j9 = i69 & i51; j9 < i51; j9++) {
                        this.f15297a.update(bArr11, 0, bArr11.length);
                        bArr11 = new byte[this.f15297a.getDigestSize()];
                        this.f15297a.doFinal(bArr11, 0);
                    }
                    System.arraycopy(bArr11, 0, bArr6, i71, digestSize);
                    int i72 = this.b;
                    i69 >>>= i72;
                    i52++;
                    i70 += i72;
                }
            }
        }
        byte[] bArr12 = new byte[digestSize];
        this.f15297a.update(bArr6, 0, i);
        byte[] bArr13 = new byte[this.f15297a.getDigestSize()];
        this.f15297a.doFinal(bArr13, 0);
        return bArr13;
    }

    public int getLog(int i) {
        int i2 = 1;
        int i3 = 2;
        while (i3 < i) {
            i3 <<= 1;
            i2++;
        }
        return i2;
    }

    public int getSignatureLength() {
        int digestSize = this.f15297a.getDigestSize();
        int i = this.b;
        int i2 = ((digestSize << 3) + (i - 1)) / i;
        int log = getLog((i2 << i) + 1);
        int i3 = this.b;
        return digestSize * (i2 + (((log + i3) - 1) / i3));
    }
}
