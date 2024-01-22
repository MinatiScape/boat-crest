package org.apache.commons.codec.binary;

import com.crrepa.c.a;
import java.math.BigInteger;
import java.util.Objects;
import org.apache.commons.codec.CodecPolicy;
import org.apache.commons.codec.binary.BaseNCodec;
/* loaded from: classes12.dex */
public class Base64 extends BaseNCodec {
    public static final byte[] k = {65, 66, 67, 68, a.E0, com.htsmart.wristband2.a.a.a.U0, 71, com.htsmart.wristband2.a.a.a.W0, 73, com.htsmart.wristband2.a.a.a.Y0, 75, com.htsmart.wristband2.a.a.a.a1, 77, com.htsmart.wristband2.a.a.a.c1, 79, com.htsmart.wristband2.a.a.a.d1, 81, 82, 83, 84, 85, 86, 87, com.htsmart.wristband2.a.a.a.o1, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, a.Z0, com.htsmart.wristband2.a.a.a.J1, 113, 114, 115, 116, 117, com.htsmart.wristband2.a.a.a.R1, 119, 120, com.htsmart.wristband2.a.a.a.U1, com.htsmart.wristband2.a.a.a.V1, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    public static final byte[] l = {65, 66, 67, 68, a.E0, com.htsmart.wristband2.a.a.a.U0, 71, com.htsmart.wristband2.a.a.a.W0, 73, com.htsmart.wristband2.a.a.a.Y0, 75, com.htsmart.wristband2.a.a.a.a1, 77, com.htsmart.wristband2.a.a.a.c1, 79, com.htsmart.wristband2.a.a.a.d1, 81, 82, 83, 84, 85, 86, 87, com.htsmart.wristband2.a.a.a.o1, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, a.Z0, com.htsmart.wristband2.a.a.a.J1, 113, 114, 115, 116, 117, com.htsmart.wristband2.a.a.a.R1, 119, 120, com.htsmart.wristband2.a.a.a.U1, com.htsmart.wristband2.a.a.a.V1, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
    public static final byte[] m = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};
    public final byte[] f;
    public final byte[] g;
    public final byte[] h;
    public final int i;
    public final int j;

    public Base64() {
        this(0);
    }

    public static byte[] decodeBase64(byte[] bArr) {
        return new Base64().decode(bArr);
    }

    public static BigInteger decodeInteger(byte[] bArr) {
        return new BigInteger(1, decodeBase64(bArr));
    }

    public static byte[] encodeBase64(byte[] bArr) {
        return encodeBase64(bArr, false);
    }

    public static byte[] encodeBase64Chunked(byte[] bArr) {
        return encodeBase64(bArr, true);
    }

    public static String encodeBase64String(byte[] bArr) {
        return StringUtils.newStringUsAscii(encodeBase64(bArr, false));
    }

    public static byte[] encodeBase64URLSafe(byte[] bArr) {
        return encodeBase64(bArr, false, true);
    }

    public static String encodeBase64URLSafeString(byte[] bArr) {
        return StringUtils.newStringUsAscii(encodeBase64(bArr, false, true));
    }

    public static byte[] encodeInteger(BigInteger bigInteger) {
        Objects.requireNonNull(bigInteger, "bigInteger");
        return encodeBase64(i(bigInteger), false);
    }

    public static byte[] i(BigInteger bigInteger) {
        int bitLength = ((bigInteger.bitLength() + 7) >> 3) << 3;
        byte[] byteArray = bigInteger.toByteArray();
        int i = 1;
        if (bigInteger.bitLength() % 8 == 0 || (bigInteger.bitLength() / 8) + 1 != bitLength / 8) {
            int length = byteArray.length;
            if (bigInteger.bitLength() % 8 == 0) {
                length--;
            } else {
                i = 0;
            }
            int i2 = bitLength / 8;
            int i3 = i2 - length;
            byte[] bArr = new byte[i2];
            System.arraycopy(byteArray, i, bArr, i3, length);
            return bArr;
        }
        return byteArray;
    }

    @Deprecated
    public static boolean isArrayByteBase64(byte[] bArr) {
        return isBase64(bArr);
    }

    public static boolean isBase64(byte b) {
        if (b != 61) {
            if (b >= 0) {
                byte[] bArr = m;
                if (b >= bArr.length || bArr[b] == -1) {
                }
            }
            return false;
        }
        return true;
    }

    @Override // org.apache.commons.codec.binary.BaseNCodec
    public void d(byte[] bArr, int i, int i2, BaseNCodec.a aVar) {
        byte b;
        if (aVar.f) {
            return;
        }
        if (i2 < 0) {
            aVar.f = true;
        }
        int i3 = 0;
        while (true) {
            if (i3 >= i2) {
                break;
            }
            byte[] ensureBufferSize = ensureBufferSize(this.i, aVar);
            int i4 = i + 1;
            byte b2 = bArr[i];
            if (b2 == this.pad) {
                aVar.f = true;
                break;
            }
            if (b2 >= 0) {
                byte[] bArr2 = m;
                if (b2 < bArr2.length && (b = bArr2[b2]) >= 0) {
                    int i5 = (aVar.h + 1) % 4;
                    aVar.h = i5;
                    int i6 = (aVar.f14333a << 6) + b;
                    aVar.f14333a = i6;
                    if (i5 == 0) {
                        int i7 = aVar.d;
                        int i8 = i7 + 1;
                        aVar.d = i8;
                        ensureBufferSize[i7] = (byte) ((i6 >> 16) & 255);
                        int i9 = i8 + 1;
                        aVar.d = i9;
                        ensureBufferSize[i8] = (byte) ((i6 >> 8) & 255);
                        aVar.d = i9 + 1;
                        ensureBufferSize[i9] = (byte) (i6 & 255);
                    }
                }
            }
            i3++;
            i = i4;
        }
        if (!aVar.f || aVar.h == 0) {
            return;
        }
        byte[] ensureBufferSize2 = ensureBufferSize(this.i, aVar);
        int i10 = aVar.h;
        if (i10 == 1) {
            k();
        } else if (i10 == 2) {
            j(15, aVar);
            int i11 = aVar.f14333a >> 4;
            aVar.f14333a = i11;
            int i12 = aVar.d;
            aVar.d = i12 + 1;
            ensureBufferSize2[i12] = (byte) (i11 & 255);
        } else if (i10 == 3) {
            j(3, aVar);
            int i13 = aVar.f14333a >> 2;
            aVar.f14333a = i13;
            int i14 = aVar.d;
            int i15 = i14 + 1;
            aVar.d = i15;
            ensureBufferSize2[i14] = (byte) ((i13 >> 8) & 255);
            aVar.d = i15 + 1;
            ensureBufferSize2[i15] = (byte) (i13 & 255);
        } else {
            throw new IllegalStateException("Impossible modulus " + aVar.h);
        }
    }

    @Override // org.apache.commons.codec.binary.BaseNCodec
    public void e(byte[] bArr, int i, int i2, BaseNCodec.a aVar) {
        if (aVar.f) {
            return;
        }
        if (i2 >= 0) {
            int i3 = 0;
            while (i3 < i2) {
                byte[] ensureBufferSize = ensureBufferSize(this.j, aVar);
                int i4 = (aVar.h + 1) % 3;
                aVar.h = i4;
                int i5 = i + 1;
                int i6 = bArr[i];
                if (i6 < 0) {
                    i6 += 256;
                }
                int i7 = (aVar.f14333a << 8) + i6;
                aVar.f14333a = i7;
                if (i4 == 0) {
                    int i8 = aVar.d;
                    int i9 = i8 + 1;
                    aVar.d = i9;
                    byte[] bArr2 = this.f;
                    ensureBufferSize[i8] = bArr2[(i7 >> 18) & 63];
                    int i10 = i9 + 1;
                    aVar.d = i10;
                    ensureBufferSize[i9] = bArr2[(i7 >> 12) & 63];
                    int i11 = i10 + 1;
                    aVar.d = i11;
                    ensureBufferSize[i10] = bArr2[(i7 >> 6) & 63];
                    int i12 = i11 + 1;
                    aVar.d = i12;
                    ensureBufferSize[i11] = bArr2[i7 & 63];
                    int i13 = aVar.g + 4;
                    aVar.g = i13;
                    int i14 = this.lineLength;
                    if (i14 > 0 && i14 <= i13) {
                        byte[] bArr3 = this.h;
                        System.arraycopy(bArr3, 0, ensureBufferSize, i12, bArr3.length);
                        aVar.d += this.h.length;
                        aVar.g = 0;
                    }
                }
                i3++;
                i = i5;
            }
            return;
        }
        aVar.f = true;
        if (aVar.h == 0 && this.lineLength == 0) {
            return;
        }
        byte[] ensureBufferSize2 = ensureBufferSize(this.j, aVar);
        int i15 = aVar.d;
        int i16 = aVar.h;
        if (i16 != 0) {
            if (i16 == 1) {
                int i17 = i15 + 1;
                aVar.d = i17;
                byte[] bArr4 = this.f;
                int i18 = aVar.f14333a;
                ensureBufferSize2[i15] = bArr4[(i18 >> 2) & 63];
                int i19 = i17 + 1;
                aVar.d = i19;
                ensureBufferSize2[i17] = bArr4[(i18 << 4) & 63];
                if (bArr4 == k) {
                    int i20 = i19 + 1;
                    aVar.d = i20;
                    byte b = this.pad;
                    ensureBufferSize2[i19] = b;
                    aVar.d = i20 + 1;
                    ensureBufferSize2[i20] = b;
                }
            } else if (i16 == 2) {
                int i21 = i15 + 1;
                aVar.d = i21;
                byte[] bArr5 = this.f;
                int i22 = aVar.f14333a;
                ensureBufferSize2[i15] = bArr5[(i22 >> 10) & 63];
                int i23 = i21 + 1;
                aVar.d = i23;
                ensureBufferSize2[i21] = bArr5[(i22 >> 4) & 63];
                int i24 = i23 + 1;
                aVar.d = i24;
                ensureBufferSize2[i23] = bArr5[(i22 << 2) & 63];
                if (bArr5 == k) {
                    aVar.d = i24 + 1;
                    ensureBufferSize2[i24] = this.pad;
                }
            } else {
                throw new IllegalStateException("Impossible modulus " + aVar.h);
            }
        }
        int i25 = aVar.g;
        int i26 = aVar.d;
        int i27 = i25 + (i26 - i15);
        aVar.g = i27;
        if (this.lineLength <= 0 || i27 <= 0) {
            return;
        }
        byte[] bArr6 = this.h;
        System.arraycopy(bArr6, 0, ensureBufferSize2, i26, bArr6.length);
        aVar.d += this.h.length;
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

    public boolean isUrlSafe() {
        return this.f == l;
    }

    public final void j(int i, BaseNCodec.a aVar) {
        if (isStrictDecoding() && (i & aVar.f14333a) != 0) {
            throw new IllegalArgumentException("Strict decoding: Last encoded character (before the paddings if any) is a valid base 64 alphabet but not a possible encoding. Expected the discarded bits from the character to be zero.");
        }
    }

    public final void k() {
        if (isStrictDecoding()) {
            throw new IllegalArgumentException("Strict decoding: Last encoded character (before the paddings if any) is a valid base 64 alphabet but not a possible encoding. Decoding requires at least two trailing 6-bit characters to create bytes.");
        }
    }

    public Base64(boolean z) {
        this(76, BaseNCodec.e, z);
    }

    public static byte[] decodeBase64(String str) {
        return new Base64().decode(str);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z) {
        return encodeBase64(bArr, z, false);
    }

    public static boolean isBase64(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            if (!isBase64(bArr[i]) && !BaseNCodec.isWhiteSpace(bArr[i])) {
                return false;
            }
        }
        return true;
    }

    public Base64(int i) {
        this(i, BaseNCodec.e);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z, boolean z2) {
        return encodeBase64(bArr, z, z2, Integer.MAX_VALUE);
    }

    public Base64(int i, byte[] bArr) {
        this(i, bArr, false);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z, boolean z2, int i) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        Base64 base64 = z ? new Base64(z2) : new Base64(0, BaseNCodec.e, z2);
        long encodedLength = base64.getEncodedLength(bArr);
        if (encodedLength <= i) {
            return base64.encode(bArr);
        }
        throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + encodedLength + ") than the specified maximum size of " + i);
    }

    public static boolean isBase64(String str) {
        return isBase64(StringUtils.getBytesUtf8(str));
    }

    public Base64(int i, byte[] bArr, boolean z) {
        this(i, bArr, z, BaseNCodec.DECODING_POLICY_DEFAULT);
    }

    public Base64(int i, byte[] bArr, boolean z, CodecPolicy codecPolicy) {
        super(3, 4, i, bArr == null ? 0 : bArr.length, (byte) 61, codecPolicy);
        this.g = m;
        if (bArr != null) {
            if (containsAlphabetOrPad(bArr)) {
                String newStringUtf8 = StringUtils.newStringUtf8(bArr);
                throw new IllegalArgumentException("lineSeparator must not contain base64 characters: [" + newStringUtf8 + "]");
            } else if (i > 0) {
                this.j = bArr.length + 4;
                byte[] bArr2 = new byte[bArr.length];
                this.h = bArr2;
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            } else {
                this.j = 4;
                this.h = null;
            }
        } else {
            this.j = 4;
            this.h = null;
        }
        this.i = this.j - 1;
        this.f = z ? l : k;
    }
}
