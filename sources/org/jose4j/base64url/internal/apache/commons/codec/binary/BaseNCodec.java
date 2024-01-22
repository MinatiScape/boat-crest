package org.jose4j.base64url.internal.apache.commons.codec.binary;

import java.util.Arrays;
import org.jose4j.lang.StringUtil;
/* loaded from: classes13.dex */
public abstract class BaseNCodec {
    public static final int MASK_8BITS = 255;
    public static final int MIME_CHUNK_SIZE = 76;
    public static final byte PAD_DEFAULT = 61;
    public static final int PEM_CHUNK_SIZE = 64;
    public final byte PAD = 61;

    /* renamed from: a  reason: collision with root package name */
    public final int f15496a;
    public final int b;
    public final int c;
    public final int lineLength;

    /* loaded from: classes13.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public int f15497a;
        public long b;
        public byte[] c;
        public int d;
        public int e;
        public boolean f;
        public int g;
        public int h;

        public String toString() {
            return String.format("%s[buffer=%s, currentLinePos=%s, eof=%s, ibitWorkArea=%s, lbitWorkArea=%s, modulus=%s, pos=%s, readPos=%s]", a.class.getSimpleName(), Arrays.toString(this.c), Integer.valueOf(this.g), Boolean.valueOf(this.f), Integer.valueOf(this.f15497a), Long.valueOf(this.b), Integer.valueOf(this.h), Integer.valueOf(this.d), Integer.valueOf(this.e));
        }
    }

    public BaseNCodec(int i, int i2, int i3, int i4) {
        this.f15496a = i;
        this.b = i2;
        this.lineLength = i3 > 0 && i4 > 0 ? (i3 / i2) * i2 : 0;
        this.c = i4;
    }

    public static boolean isWhiteSpace(byte b) {
        return b == 9 || b == 10 || b == 13 || b == 32;
    }

    public int a(a aVar) {
        if (aVar.c != null) {
            return aVar.d - aVar.e;
        }
        return 0;
    }

    public abstract void b(byte[] bArr, int i, int i2, a aVar);

    public abstract void c(byte[] bArr, int i, int i2, a aVar);

    public boolean containsAlphabetOrPad(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        for (byte b : bArr) {
            if (61 == b || isInAlphabet(b)) {
                return true;
            }
        }
        return false;
    }

    public int d(byte[] bArr, int i, int i2, a aVar) {
        if (aVar.c == null) {
            return aVar.f ? -1 : 0;
        }
        int min = Math.min(a(aVar), i2);
        System.arraycopy(aVar.c, aVar.e, bArr, i, min);
        int i3 = aVar.e + min;
        aVar.e = i3;
        if (i3 >= aVar.d) {
            aVar.c = null;
        }
        return min;
    }

    public byte[] decode(String str) {
        return decode(StringUtil.getBytesUtf8(str));
    }

    public final byte[] e(a aVar) {
        byte[] bArr = aVar.c;
        if (bArr == null) {
            aVar.c = new byte[getDefaultBufferSize()];
            aVar.d = 0;
            aVar.e = 0;
        } else {
            byte[] bArr2 = new byte[bArr.length * 2];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            aVar.c = bArr2;
        }
        return aVar.c;
    }

    public byte[] encode(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        a aVar = new a();
        c(bArr, 0, bArr.length, aVar);
        c(bArr, 0, -1, aVar);
        int i = aVar.d - aVar.e;
        byte[] bArr2 = new byte[i];
        d(bArr2, 0, i, aVar);
        return bArr2;
    }

    public String encodeAsString(byte[] bArr) {
        return StringUtil.newStringUtf8(encode(bArr));
    }

    public String encodeToString(byte[] bArr) {
        return StringUtil.newStringUtf8(encode(bArr));
    }

    public byte[] ensureBufferSize(int i, a aVar) {
        byte[] bArr = aVar.c;
        return (bArr == null || bArr.length < aVar.d + i) ? e(aVar) : bArr;
    }

    public int getDefaultBufferSize() {
        return 8192;
    }

    public long getEncodedLength(byte[] bArr) {
        int length = bArr.length;
        int i = this.f15496a;
        long j = (((length + i) - 1) / i) * this.b;
        int i2 = this.lineLength;
        return i2 > 0 ? j + ((((i2 + j) - 1) / i2) * this.c) : j;
    }

    public abstract boolean isInAlphabet(byte b);

    public boolean isInAlphabet(byte[] bArr, boolean z) {
        for (int i = 0; i < bArr.length; i++) {
            if (!isInAlphabet(bArr[i]) && (!z || (bArr[i] != 61 && !isWhiteSpace(bArr[i])))) {
                return false;
            }
        }
        return true;
    }

    public byte[] decode(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        a aVar = new a();
        b(bArr, 0, bArr.length, aVar);
        b(bArr, 0, -1, aVar);
        int i = aVar.d;
        byte[] bArr2 = new byte[i];
        d(bArr2, 0, i, aVar);
        return bArr2;
    }

    public boolean isInAlphabet(String str) {
        return isInAlphabet(StringUtil.getBytesUtf8(str), true);
    }
}
