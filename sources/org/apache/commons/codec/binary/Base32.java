package org.apache.commons.codec.binary;

import com.crrepa.c.a;
import org.apache.commons.codec.CodecPolicy;
import org.apache.commons.codec.binary.BaseNCodec;
/* loaded from: classes12.dex */
public class Base32 extends BaseNCodec {
    public static final byte[] k = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
    public static final byte[] l = {65, 66, 67, 68, a.E0, com.htsmart.wristband2.a.a.a.U0, 71, com.htsmart.wristband2.a.a.a.W0, 73, com.htsmart.wristband2.a.a.a.Y0, 75, com.htsmart.wristband2.a.a.a.a1, 77, com.htsmart.wristband2.a.a.a.c1, 79, com.htsmart.wristband2.a.a.a.d1, 81, 82, 83, 84, 85, 86, 87, com.htsmart.wristband2.a.a.a.o1, 89, 90, 50, 51, 52, 53, 54, 55};
    public static final byte[] m = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
    public static final byte[] n = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, a.E0, com.htsmart.wristband2.a.a.a.U0, 71, com.htsmart.wristband2.a.a.a.W0, 73, com.htsmart.wristband2.a.a.a.Y0, 75, com.htsmart.wristband2.a.a.a.a1, 77, com.htsmart.wristband2.a.a.a.c1, 79, com.htsmart.wristband2.a.a.a.d1, 81, 82, 83, 84, 85, 86};
    public final int f;
    public final byte[] g;
    public final int h;
    public final byte[] i;
    public final byte[] j;

    public Base32() {
        this(false);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v2, types: [boolean, int] */
    @Override // org.apache.commons.codec.binary.BaseNCodec
    public void d(byte[] bArr, int i, int i2, BaseNCodec.a aVar) {
        long j;
        long j2;
        long j3;
        long j4;
        byte b;
        long j5;
        if (aVar.f) {
            return;
        }
        ?? r3 = 1;
        if (i2 < 0) {
            aVar.f = true;
        }
        int i3 = 0;
        int i4 = i;
        while (true) {
            if (i3 >= i2) {
                break;
            }
            int i5 = i4 + 1;
            byte b2 = bArr[i4];
            if (b2 == this.pad) {
                aVar.f = r3;
                break;
            }
            byte[] ensureBufferSize = ensureBufferSize(this.f, aVar);
            if (b2 >= 0) {
                byte[] bArr2 = this.g;
                if (b2 < bArr2.length && (b = bArr2[b2]) >= 0) {
                    int i6 = (aVar.h + r3) % 8;
                    aVar.h = i6;
                    aVar.b = (aVar.b << 5) + b;
                    if (i6 == 0) {
                        int i7 = aVar.d;
                        int i8 = i7 + 1;
                        aVar.d = i8;
                        ensureBufferSize[i7] = (byte) ((j5 >> 32) & 255);
                        int i9 = i8 + 1;
                        aVar.d = i9;
                        ensureBufferSize[i8] = (byte) ((j5 >> 24) & 255);
                        int i10 = i9 + 1;
                        aVar.d = i10;
                        ensureBufferSize[i9] = (byte) ((j5 >> 16) & 255);
                        int i11 = i10 + 1;
                        aVar.d = i11;
                        ensureBufferSize[i10] = (byte) ((j5 >> 8) & 255);
                        aVar.d = i11 + 1;
                        ensureBufferSize[i11] = (byte) (j5 & 255);
                    }
                }
            }
            i3++;
            i4 = i5;
            r3 = 1;
        }
        if (!aVar.f || aVar.h <= 0) {
            return;
        }
        byte[] ensureBufferSize2 = ensureBufferSize(this.f, aVar);
        switch (aVar.h) {
            case 1:
                j();
                break;
            case 2:
                break;
            case 3:
                j();
                int i12 = aVar.d;
                aVar.d = i12 + 1;
                ensureBufferSize2[i12] = (byte) ((aVar.b >> 7) & 255);
                return;
            case 4:
                i(15L, aVar);
                aVar.b = aVar.b >> 4;
                int i13 = aVar.d;
                int i14 = i13 + 1;
                aVar.d = i14;
                ensureBufferSize2[i13] = (byte) ((j >> 8) & 255);
                aVar.d = i14 + 1;
                ensureBufferSize2[i14] = (byte) (j & 255);
                return;
            case 5:
                i(1L, aVar);
                aVar.b = aVar.b >> 1;
                int i15 = aVar.d;
                int i16 = i15 + 1;
                aVar.d = i16;
                ensureBufferSize2[i15] = (byte) ((j2 >> 16) & 255);
                int i17 = i16 + 1;
                aVar.d = i17;
                ensureBufferSize2[i16] = (byte) ((j2 >> 8) & 255);
                aVar.d = i17 + 1;
                ensureBufferSize2[i17] = (byte) (j2 & 255);
                return;
            case 6:
                j();
                aVar.b = aVar.b >> 6;
                int i18 = aVar.d;
                int i19 = i18 + 1;
                aVar.d = i19;
                ensureBufferSize2[i18] = (byte) ((j3 >> 16) & 255);
                int i20 = i19 + 1;
                aVar.d = i20;
                ensureBufferSize2[i19] = (byte) ((j3 >> 8) & 255);
                aVar.d = i20 + 1;
                ensureBufferSize2[i20] = (byte) (j3 & 255);
                return;
            case 7:
                i(7L, aVar);
                aVar.b = aVar.b >> 3;
                int i21 = aVar.d;
                int i22 = i21 + 1;
                aVar.d = i22;
                ensureBufferSize2[i21] = (byte) ((j4 >> 24) & 255);
                int i23 = i22 + 1;
                aVar.d = i23;
                ensureBufferSize2[i22] = (byte) ((j4 >> 16) & 255);
                int i24 = i23 + 1;
                aVar.d = i24;
                ensureBufferSize2[i23] = (byte) ((j4 >> 8) & 255);
                aVar.d = i24 + 1;
                ensureBufferSize2[i24] = (byte) (j4 & 255);
                return;
            default:
                throw new IllegalStateException("Impossible modulus " + aVar.h);
        }
        i(3L, aVar);
        int i25 = aVar.d;
        aVar.d = i25 + 1;
        ensureBufferSize2[i25] = (byte) ((aVar.b >> 2) & 255);
    }

    @Override // org.apache.commons.codec.binary.BaseNCodec
    public void e(byte[] bArr, int i, int i2, BaseNCodec.a aVar) {
        boolean z;
        int i3;
        if (aVar.f) {
            return;
        }
        boolean z2 = false;
        int i4 = 1;
        if (i2 >= 0) {
            int i5 = i;
            int i6 = 0;
            while (i6 < i2) {
                byte[] ensureBufferSize = ensureBufferSize(this.h, aVar);
                int i7 = (aVar.h + i4) % 5;
                aVar.h = i7;
                int i8 = i5 + 1;
                int i9 = bArr[i5];
                if (i9 < 0) {
                    i9 += 256;
                }
                long j = (aVar.b << 8) + i9;
                aVar.b = j;
                if (i7 == 0) {
                    int i10 = aVar.d;
                    int i11 = i10 + 1;
                    aVar.d = i11;
                    byte[] bArr2 = this.i;
                    ensureBufferSize[i10] = bArr2[((int) (j >> 35)) & 31];
                    int i12 = i11 + 1;
                    aVar.d = i12;
                    ensureBufferSize[i11] = bArr2[((int) (j >> 30)) & 31];
                    int i13 = i12 + 1;
                    aVar.d = i13;
                    i3 = i8;
                    ensureBufferSize[i12] = bArr2[((int) (j >> 25)) & 31];
                    int i14 = i13 + 1;
                    aVar.d = i14;
                    ensureBufferSize[i13] = bArr2[((int) (j >> 20)) & 31];
                    int i15 = i14 + 1;
                    aVar.d = i15;
                    ensureBufferSize[i14] = bArr2[((int) (j >> 15)) & 31];
                    int i16 = i15 + 1;
                    aVar.d = i16;
                    ensureBufferSize[i15] = bArr2[((int) (j >> 10)) & 31];
                    int i17 = i16 + 1;
                    aVar.d = i17;
                    ensureBufferSize[i16] = bArr2[((int) (j >> 5)) & 31];
                    int i18 = i17 + 1;
                    aVar.d = i18;
                    ensureBufferSize[i17] = bArr2[((int) j) & 31];
                    int i19 = aVar.g + 8;
                    aVar.g = i19;
                    int i20 = this.lineLength;
                    if (i20 <= 0 || i20 > i19) {
                        z = false;
                    } else {
                        byte[] bArr3 = this.j;
                        z = false;
                        System.arraycopy(bArr3, 0, ensureBufferSize, i18, bArr3.length);
                        aVar.d += this.j.length;
                        aVar.g = 0;
                    }
                } else {
                    z = z2;
                    i3 = i8;
                }
                i6++;
                i5 = i3;
                z2 = z;
                i4 = 1;
            }
            return;
        }
        aVar.f = true;
        if (aVar.h == 0 && this.lineLength == 0) {
            return;
        }
        byte[] ensureBufferSize2 = ensureBufferSize(this.h, aVar);
        int i21 = aVar.d;
        int i22 = aVar.h;
        if (i22 != 0) {
            if (i22 == 1) {
                int i23 = i21 + 1;
                aVar.d = i23;
                byte[] bArr4 = this.i;
                long j2 = aVar.b;
                ensureBufferSize2[i21] = bArr4[((int) (j2 >> 3)) & 31];
                int i24 = i23 + 1;
                aVar.d = i24;
                ensureBufferSize2[i23] = bArr4[((int) (j2 << 2)) & 31];
                int i25 = i24 + 1;
                aVar.d = i25;
                byte b = this.pad;
                ensureBufferSize2[i24] = b;
                int i26 = i25 + 1;
                aVar.d = i26;
                ensureBufferSize2[i25] = b;
                int i27 = i26 + 1;
                aVar.d = i27;
                ensureBufferSize2[i26] = b;
                int i28 = i27 + 1;
                aVar.d = i28;
                ensureBufferSize2[i27] = b;
                int i29 = i28 + 1;
                aVar.d = i29;
                ensureBufferSize2[i28] = b;
                aVar.d = i29 + 1;
                ensureBufferSize2[i29] = b;
            } else if (i22 == 2) {
                int i30 = i21 + 1;
                aVar.d = i30;
                byte[] bArr5 = this.i;
                long j3 = aVar.b;
                ensureBufferSize2[i21] = bArr5[((int) (j3 >> 11)) & 31];
                int i31 = i30 + 1;
                aVar.d = i31;
                ensureBufferSize2[i30] = bArr5[((int) (j3 >> 6)) & 31];
                int i32 = i31 + 1;
                aVar.d = i32;
                ensureBufferSize2[i31] = bArr5[((int) (j3 >> 1)) & 31];
                int i33 = i32 + 1;
                aVar.d = i33;
                ensureBufferSize2[i32] = bArr5[((int) (j3 << 4)) & 31];
                int i34 = i33 + 1;
                aVar.d = i34;
                byte b2 = this.pad;
                ensureBufferSize2[i33] = b2;
                int i35 = i34 + 1;
                aVar.d = i35;
                ensureBufferSize2[i34] = b2;
                int i36 = i35 + 1;
                aVar.d = i36;
                ensureBufferSize2[i35] = b2;
                aVar.d = i36 + 1;
                ensureBufferSize2[i36] = b2;
            } else if (i22 == 3) {
                int i37 = i21 + 1;
                aVar.d = i37;
                byte[] bArr6 = this.i;
                long j4 = aVar.b;
                ensureBufferSize2[i21] = bArr6[((int) (j4 >> 19)) & 31];
                int i38 = i37 + 1;
                aVar.d = i38;
                ensureBufferSize2[i37] = bArr6[((int) (j4 >> 14)) & 31];
                int i39 = i38 + 1;
                aVar.d = i39;
                ensureBufferSize2[i38] = bArr6[((int) (j4 >> 9)) & 31];
                int i40 = i39 + 1;
                aVar.d = i40;
                ensureBufferSize2[i39] = bArr6[((int) (j4 >> 4)) & 31];
                int i41 = i40 + 1;
                aVar.d = i41;
                ensureBufferSize2[i40] = bArr6[((int) (j4 << 1)) & 31];
                int i42 = i41 + 1;
                aVar.d = i42;
                byte b3 = this.pad;
                ensureBufferSize2[i41] = b3;
                int i43 = i42 + 1;
                aVar.d = i43;
                ensureBufferSize2[i42] = b3;
                aVar.d = i43 + 1;
                ensureBufferSize2[i43] = b3;
            } else if (i22 == 4) {
                int i44 = i21 + 1;
                aVar.d = i44;
                byte[] bArr7 = this.i;
                long j5 = aVar.b;
                ensureBufferSize2[i21] = bArr7[((int) (j5 >> 27)) & 31];
                int i45 = i44 + 1;
                aVar.d = i45;
                ensureBufferSize2[i44] = bArr7[((int) (j5 >> 22)) & 31];
                int i46 = i45 + 1;
                aVar.d = i46;
                ensureBufferSize2[i45] = bArr7[((int) (j5 >> 17)) & 31];
                int i47 = i46 + 1;
                aVar.d = i47;
                ensureBufferSize2[i46] = bArr7[((int) (j5 >> 12)) & 31];
                int i48 = i47 + 1;
                aVar.d = i48;
                ensureBufferSize2[i47] = bArr7[((int) (j5 >> 7)) & 31];
                int i49 = i48 + 1;
                aVar.d = i49;
                ensureBufferSize2[i48] = bArr7[((int) (j5 >> 2)) & 31];
                int i50 = i49 + 1;
                aVar.d = i50;
                ensureBufferSize2[i49] = bArr7[((int) (j5 << 3)) & 31];
                aVar.d = i50 + 1;
                ensureBufferSize2[i50] = this.pad;
            } else {
                throw new IllegalStateException("Impossible modulus " + aVar.h);
            }
        }
        int i51 = aVar.g;
        int i52 = aVar.d;
        int i53 = i51 + (i52 - i21);
        aVar.g = i53;
        if (this.lineLength <= 0 || i53 <= 0) {
            return;
        }
        byte[] bArr8 = this.j;
        System.arraycopy(bArr8, 0, ensureBufferSize2, i52, bArr8.length);
        aVar.d += this.j.length;
    }

    public final void i(long j, BaseNCodec.a aVar) {
        if (isStrictDecoding() && (j & aVar.b) != 0) {
            throw new IllegalArgumentException("Strict decoding: Last encoded character (before the paddings if any) is a valid base 32 alphabet but not a possible encoding. Expected the discarded bits from the character to be zero.");
        }
    }

    @Override // org.apache.commons.codec.binary.BaseNCodec
    public boolean isInAlphabet(byte b) {
        if (b >= 0) {
            byte[] bArr = this.g;
            if (b < bArr.length && bArr[b] != -1) {
                return true;
            }
        }
        return false;
    }

    public final void j() {
        if (isStrictDecoding()) {
            throw new IllegalArgumentException("Strict decoding: Last encoded character(s) (before the paddings if any) are valid base 32 alphabet but not a possible encoding. Decoding requires either 2, 4, 5, or 7 trailing 5-bit characters to create bytes.");
        }
    }

    public Base32(boolean z) {
        this(0, null, z, (byte) 61);
    }

    public Base32(boolean z, byte b) {
        this(0, null, z, b);
    }

    public Base32(byte b) {
        this(false, b);
    }

    public Base32(int i) {
        this(i, BaseNCodec.e);
    }

    public Base32(int i, byte[] bArr) {
        this(i, bArr, false, (byte) 61);
    }

    public Base32(int i, byte[] bArr, boolean z) {
        this(i, bArr, z, (byte) 61);
    }

    public Base32(int i, byte[] bArr, boolean z, byte b) {
        this(i, bArr, z, b, BaseNCodec.DECODING_POLICY_DEFAULT);
    }

    public Base32(int i, byte[] bArr, boolean z, byte b, CodecPolicy codecPolicy) {
        super(5, 8, i, bArr == null ? 0 : bArr.length, b, codecPolicy);
        if (z) {
            this.i = n;
            this.g = m;
        } else {
            this.i = l;
            this.g = k;
        }
        if (i <= 0) {
            this.h = 8;
            this.j = null;
        } else if (bArr != null) {
            if (!containsAlphabetOrPad(bArr)) {
                this.h = bArr.length + 8;
                byte[] bArr2 = new byte[bArr.length];
                this.j = bArr2;
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            } else {
                String newStringUtf8 = StringUtils.newStringUtf8(bArr);
                throw new IllegalArgumentException("lineSeparator must not contain Base32 characters: [" + newStringUtf8 + "]");
            }
        } else {
            throw new IllegalArgumentException("lineLength " + i + " > 0, but lineSeparator is null");
        }
        this.f = this.h - 1;
        if (isInAlphabet(b) || BaseNCodec.isWhiteSpace(b)) {
            throw new IllegalArgumentException("pad must not be in alphabet or whitespace");
        }
    }
}
