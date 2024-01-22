package org.apache.commons.codec.binary;

import com.crrepa.c.a;
import org.apache.commons.codec.CodecPolicy;
import org.apache.commons.codec.binary.BaseNCodec;
/* loaded from: classes12.dex */
public class Base16 extends BaseNCodec {
    public static final byte[] h = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15};
    public static final byte[] i = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, a.E0, com.htsmart.wristband2.a.a.a.U0};
    public static final byte[] j = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15};
    public static final byte[] k = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    public final byte[] f;
    public final byte[] g;

    public Base16() {
        this(false);
    }

    @Override // org.apache.commons.codec.binary.BaseNCodec
    public void d(byte[] bArr, int i2, int i3, BaseNCodec.a aVar) {
        if (!aVar.f && i3 >= 0) {
            int min = Math.min(bArr.length - i2, i3);
            int i4 = 0;
            int i5 = (aVar.f14333a != 0 ? 1 : 0) + min;
            if (i5 == 1 && i5 == min) {
                aVar.f14333a = i(bArr[i2]) + 1;
                return;
            }
            int i6 = i5 % 2 == 0 ? i5 : i5 - 1;
            byte[] ensureBufferSize = ensureBufferSize(i6 / 2, aVar);
            if (min < i5) {
                int i7 = i2 + 1;
                int i8 = i(bArr[i2]) | ((aVar.f14333a - 1) << 4);
                int i9 = aVar.d;
                aVar.d = i9 + 1;
                ensureBufferSize[i9] = (byte) i8;
                aVar.f14333a = 0;
                i4 = 2;
                i2 = i7;
            }
            while (i4 < i6) {
                int i10 = i2 + 1;
                int i11 = i10 + 1;
                int i12 = (i(bArr[i2]) << 4) | i(bArr[i10]);
                i4 += 2;
                int i13 = aVar.d;
                aVar.d = i13 + 1;
                ensureBufferSize[i13] = (byte) i12;
                i2 = i11;
            }
            if (i4 < min) {
                aVar.f14333a = i(bArr[i4]) + 1;
                return;
            }
            return;
        }
        aVar.f = true;
        if (aVar.f14333a != 0) {
            j();
        }
    }

    @Override // org.apache.commons.codec.binary.BaseNCodec
    public void e(byte[] bArr, int i2, int i3, BaseNCodec.a aVar) {
        if (aVar.f) {
            return;
        }
        if (i3 < 0) {
            aVar.f = true;
            return;
        }
        int i4 = i3 * 2;
        if (i4 >= 0) {
            byte[] ensureBufferSize = ensureBufferSize(i4, aVar);
            int i5 = i3 + i2;
            while (i2 < i5) {
                byte b = bArr[i2];
                int i6 = aVar.d;
                int i7 = i6 + 1;
                aVar.d = i7;
                byte[] bArr2 = this.g;
                ensureBufferSize[i6] = bArr2[(b >> 4) & 15];
                aVar.d = i7 + 1;
                ensureBufferSize[i7] = bArr2[b & 15];
                i2++;
            }
            return;
        }
        throw new IllegalArgumentException("Input length exceeds maximum size for encoded data: " + i3);
    }

    public final int i(byte b) {
        int i2 = b & 255;
        byte[] bArr = this.f;
        byte b2 = i2 < bArr.length ? bArr[b] : (byte) -1;
        if (b2 != -1) {
            return b2;
        }
        throw new IllegalArgumentException("Invalid octet in encoded value: " + ((int) b));
    }

    @Override // org.apache.commons.codec.binary.BaseNCodec
    public boolean isInAlphabet(byte b) {
        int i2 = b & 255;
        byte[] bArr = this.f;
        return i2 < bArr.length && bArr[b] != -1;
    }

    public final void j() {
        if (isStrictDecoding()) {
            throw new IllegalArgumentException("Strict decoding: Last encoded character is a valid base 16 alphabetcharacter but not a possible encoding. Decoding requires at least two characters to create one byte.");
        }
    }

    public Base16(boolean z) {
        this(z, BaseNCodec.DECODING_POLICY_DEFAULT);
    }

    public Base16(boolean z, CodecPolicy codecPolicy) {
        super(1, 2, 0, 0, (byte) 61, codecPolicy);
        if (z) {
            this.g = k;
            this.f = j;
            return;
        }
        this.g = i;
        this.f = h;
    }
}
