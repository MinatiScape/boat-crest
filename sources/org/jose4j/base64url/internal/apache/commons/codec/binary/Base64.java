package org.jose4j.base64url.internal.apache.commons.codec.binary;

import com.crrepa.c.a;
import java.math.BigInteger;
import java.util.Objects;
import org.jose4j.base64url.internal.apache.commons.codec.binary.BaseNCodec;
import org.jose4j.lang.StringUtil;
/* loaded from: classes13.dex */
public class Base64 extends BaseNCodec {
    public static final byte[] i = {13, 10};
    public static final byte[] j = {65, 66, 67, 68, a.E0, com.htsmart.wristband2.a.a.a.U0, 71, com.htsmart.wristband2.a.a.a.W0, 73, com.htsmart.wristband2.a.a.a.Y0, 75, com.htsmart.wristband2.a.a.a.a1, 77, com.htsmart.wristband2.a.a.a.c1, 79, com.htsmart.wristband2.a.a.a.d1, 81, 82, 83, 84, 85, 86, 87, com.htsmart.wristband2.a.a.a.o1, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, a.Z0, com.htsmart.wristband2.a.a.a.J1, 113, 114, 115, 116, 117, com.htsmart.wristband2.a.a.a.R1, 119, 120, com.htsmart.wristband2.a.a.a.U1, com.htsmart.wristband2.a.a.a.V1, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    public static final byte[] k = {65, 66, 67, 68, a.E0, com.htsmart.wristband2.a.a.a.U0, 71, com.htsmart.wristband2.a.a.a.W0, 73, com.htsmart.wristband2.a.a.a.Y0, 75, com.htsmart.wristband2.a.a.a.a1, 77, com.htsmart.wristband2.a.a.a.c1, 79, com.htsmart.wristband2.a.a.a.d1, 81, 82, 83, 84, 85, 86, 87, com.htsmart.wristband2.a.a.a.o1, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, a.Z0, com.htsmart.wristband2.a.a.a.J1, 113, 114, 115, 116, 117, com.htsmart.wristband2.a.a.a.R1, 119, 120, com.htsmart.wristband2.a.a.a.U1, com.htsmart.wristband2.a.a.a.V1, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
    public static final byte[] l = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};
    public final byte[] d;
    public final byte[] e;
    public final byte[] f;
    public final int g;
    public final int h;

    public Base64() {
        this(0);
    }

    public static byte[] decodeBase64(String str) {
        return new Base64().decode(str);
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
        return StringUtil.newStringUtf8(encodeBase64(bArr, false));
    }

    public static byte[] encodeBase64URLSafe(byte[] bArr) {
        return encodeBase64(bArr, false, true);
    }

    public static String encodeBase64URLSafeString(byte[] bArr) {
        return StringUtil.newStringUtf8(encodeBase64(bArr, false, true));
    }

    public static byte[] encodeInteger(BigInteger bigInteger) {
        Objects.requireNonNull(bigInteger, "encodeInteger called with null parameter");
        return encodeBase64(f(bigInteger), false);
    }

    public static byte[] f(BigInteger bigInteger) {
        int bitLength = ((bigInteger.bitLength() + 7) >> 3) << 3;
        byte[] byteArray = bigInteger.toByteArray();
        int i2 = 1;
        if (bigInteger.bitLength() % 8 == 0 || (bigInteger.bitLength() / 8) + 1 != bitLength / 8) {
            int length = byteArray.length;
            if (bigInteger.bitLength() % 8 == 0) {
                length--;
            } else {
                i2 = 0;
            }
            int i3 = bitLength / 8;
            int i4 = i3 - length;
            byte[] bArr = new byte[i3];
            System.arraycopy(byteArray, i2, bArr, i4, length);
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
                byte[] bArr = l;
                if (b >= bArr.length || bArr[b] == -1) {
                }
            }
            return false;
        }
        return true;
    }

    @Override // org.jose4j.base64url.internal.apache.commons.codec.binary.BaseNCodec
    public void b(byte[] bArr, int i2, int i3, BaseNCodec.a aVar) {
        byte b;
        if (aVar.f) {
            return;
        }
        if (i3 < 0) {
            aVar.f = true;
        }
        int i4 = 0;
        while (true) {
            if (i4 >= i3) {
                break;
            }
            byte[] ensureBufferSize = ensureBufferSize(this.g, aVar);
            int i5 = i2 + 1;
            byte b2 = bArr[i2];
            if (b2 == 61) {
                aVar.f = true;
                break;
            }
            if (b2 >= 0) {
                byte[] bArr2 = l;
                if (b2 < bArr2.length && (b = bArr2[b2]) >= 0) {
                    int i6 = (aVar.h + 1) % 4;
                    aVar.h = i6;
                    int i7 = (aVar.f15497a << 6) + b;
                    aVar.f15497a = i7;
                    if (i6 == 0) {
                        int i8 = aVar.d;
                        int i9 = i8 + 1;
                        aVar.d = i9;
                        ensureBufferSize[i8] = (byte) ((i7 >> 16) & 255);
                        int i10 = i9 + 1;
                        aVar.d = i10;
                        ensureBufferSize[i9] = (byte) ((i7 >> 8) & 255);
                        aVar.d = i10 + 1;
                        ensureBufferSize[i10] = (byte) (i7 & 255);
                    }
                }
            }
            i4++;
            i2 = i5;
        }
        if (!aVar.f || aVar.h == 0) {
            return;
        }
        byte[] ensureBufferSize2 = ensureBufferSize(this.g, aVar);
        int i11 = aVar.h;
        if (i11 != 1) {
            if (i11 == 2) {
                int i12 = aVar.f15497a >> 4;
                aVar.f15497a = i12;
                int i13 = aVar.d;
                aVar.d = i13 + 1;
                ensureBufferSize2[i13] = (byte) (i12 & 255);
            } else if (i11 == 3) {
                int i14 = aVar.f15497a >> 2;
                aVar.f15497a = i14;
                int i15 = aVar.d;
                int i16 = i15 + 1;
                aVar.d = i16;
                ensureBufferSize2[i15] = (byte) ((i14 >> 8) & 255);
                aVar.d = i16 + 1;
                ensureBufferSize2[i16] = (byte) (i14 & 255);
            } else {
                throw new IllegalStateException("Impossible modulus " + aVar.h);
            }
        }
    }

    @Override // org.jose4j.base64url.internal.apache.commons.codec.binary.BaseNCodec
    public void c(byte[] bArr, int i2, int i3, BaseNCodec.a aVar) {
        if (aVar.f) {
            return;
        }
        if (i3 >= 0) {
            int i4 = 0;
            while (i4 < i3) {
                byte[] ensureBufferSize = ensureBufferSize(this.h, aVar);
                int i5 = (aVar.h + 1) % 3;
                aVar.h = i5;
                int i6 = i2 + 1;
                int i7 = bArr[i2];
                if (i7 < 0) {
                    i7 += 256;
                }
                int i8 = (aVar.f15497a << 8) + i7;
                aVar.f15497a = i8;
                if (i5 == 0) {
                    int i9 = aVar.d;
                    int i10 = i9 + 1;
                    aVar.d = i10;
                    byte[] bArr2 = this.d;
                    ensureBufferSize[i9] = bArr2[(i8 >> 18) & 63];
                    int i11 = i10 + 1;
                    aVar.d = i11;
                    ensureBufferSize[i10] = bArr2[(i8 >> 12) & 63];
                    int i12 = i11 + 1;
                    aVar.d = i12;
                    ensureBufferSize[i11] = bArr2[(i8 >> 6) & 63];
                    int i13 = i12 + 1;
                    aVar.d = i13;
                    ensureBufferSize[i12] = bArr2[i8 & 63];
                    int i14 = aVar.g + 4;
                    aVar.g = i14;
                    int i15 = this.lineLength;
                    if (i15 > 0 && i15 <= i14) {
                        byte[] bArr3 = this.f;
                        System.arraycopy(bArr3, 0, ensureBufferSize, i13, bArr3.length);
                        aVar.d += this.f.length;
                        aVar.g = 0;
                    }
                }
                i4++;
                i2 = i6;
            }
            return;
        }
        aVar.f = true;
        if (aVar.h == 0 && this.lineLength == 0) {
            return;
        }
        byte[] ensureBufferSize2 = ensureBufferSize(this.h, aVar);
        int i16 = aVar.d;
        int i17 = aVar.h;
        if (i17 != 0) {
            if (i17 == 1) {
                int i18 = i16 + 1;
                aVar.d = i18;
                byte[] bArr4 = this.d;
                int i19 = aVar.f15497a;
                ensureBufferSize2[i16] = bArr4[(i19 >> 2) & 63];
                int i20 = i18 + 1;
                aVar.d = i20;
                ensureBufferSize2[i18] = bArr4[(i19 << 4) & 63];
                if (bArr4 == j) {
                    int i21 = i20 + 1;
                    aVar.d = i21;
                    ensureBufferSize2[i20] = 61;
                    aVar.d = i21 + 1;
                    ensureBufferSize2[i21] = 61;
                }
            } else if (i17 == 2) {
                int i22 = i16 + 1;
                aVar.d = i22;
                byte[] bArr5 = this.d;
                int i23 = aVar.f15497a;
                ensureBufferSize2[i16] = bArr5[(i23 >> 10) & 63];
                int i24 = i22 + 1;
                aVar.d = i24;
                ensureBufferSize2[i22] = bArr5[(i23 >> 4) & 63];
                int i25 = i24 + 1;
                aVar.d = i25;
                ensureBufferSize2[i24] = bArr5[(i23 << 2) & 63];
                if (bArr5 == j) {
                    aVar.d = i25 + 1;
                    ensureBufferSize2[i25] = 61;
                }
            } else {
                throw new IllegalStateException("Impossible modulus " + aVar.h);
            }
        }
        int i26 = aVar.g;
        int i27 = aVar.d;
        int i28 = i26 + (i27 - i16);
        aVar.g = i28;
        if (this.lineLength <= 0 || i28 <= 0) {
            return;
        }
        byte[] bArr6 = this.f;
        System.arraycopy(bArr6, 0, ensureBufferSize2, i27, bArr6.length);
        aVar.d += this.f.length;
    }

    @Override // org.jose4j.base64url.internal.apache.commons.codec.binary.BaseNCodec
    public boolean isInAlphabet(byte b) {
        if (b >= 0) {
            byte[] bArr = this.e;
            if (b < bArr.length && bArr[b] != -1) {
                return true;
            }
        }
        return false;
    }

    public boolean isUrlSafe() {
        return this.d == k;
    }

    public Base64(boolean z) {
        this(76, i, z);
    }

    public static byte[] decodeBase64(byte[] bArr) {
        return new Base64().decode(bArr);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z) {
        return encodeBase64(bArr, z, false);
    }

    public static boolean isBase64(String str) {
        return isBase64(StringUtil.getBytesUtf8(str));
    }

    public Base64(int i2) {
        this(i2, i);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z, boolean z2) {
        return encodeBase64(bArr, z, z2, Integer.MAX_VALUE);
    }

    public static boolean isBase64(byte[] bArr) {
        for (int i2 = 0; i2 < bArr.length; i2++) {
            if (!isBase64(bArr[i2]) && !BaseNCodec.isWhiteSpace(bArr[i2])) {
                return false;
            }
        }
        return true;
    }

    public Base64(int i2, byte[] bArr) {
        this(i2, bArr, false);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z, boolean z2, int i2) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        Base64 base64 = z ? new Base64(z2) : new Base64(0, i, z2);
        long encodedLength = base64.getEncodedLength(bArr);
        if (encodedLength <= i2) {
            return base64.encode(bArr);
        }
        throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + encodedLength + ") than the specified maximum size of " + i2);
    }

    public Base64(int i2, byte[] bArr, boolean z) {
        super(3, 4, i2, bArr == null ? 0 : bArr.length);
        this.e = l;
        if (bArr != null) {
            if (containsAlphabetOrPad(bArr)) {
                String newStringUtf8 = StringUtil.newStringUtf8(bArr);
                throw new IllegalArgumentException("lineSeparator must not contain base64 characters: [" + newStringUtf8 + "]");
            } else if (i2 > 0) {
                this.h = bArr.length + 4;
                byte[] bArr2 = new byte[bArr.length];
                this.f = bArr2;
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            } else {
                this.h = 4;
                this.f = null;
            }
        } else {
            this.h = 4;
            this.f = null;
        }
        this.g = this.h - 1;
        this.d = z ? k : j;
    }
}
