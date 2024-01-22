package org.apache.commons.codec.binary;

import com.coveiot.android.leonardo.performance.Constants;
import java.util.Arrays;
import java.util.Objects;
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.CodecPolicy;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
/* loaded from: classes12.dex */
public abstract class BaseNCodec implements BinaryEncoder, BinaryDecoder {
    public static final int MASK_8BITS = 255;
    public static final int MIME_CHUNK_SIZE = 76;
    public static final byte PAD_DEFAULT = 61;
    public static final int PEM_CHUNK_SIZE = 64;
    @Deprecated
    public final byte PAD;

    /* renamed from: a  reason: collision with root package name */
    public final int f14332a;
    public final int b;
    public final int c;
    public final CodecPolicy d;
    public final int lineLength;
    public final byte pad;
    public static final CodecPolicy DECODING_POLICY_DEFAULT = CodecPolicy.LENIENT;
    public static final byte[] e = {13, 10};

    /* loaded from: classes12.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public int f14333a;
        public long b;
        public byte[] c;
        public int d;
        public int e;
        public boolean f;
        public int g;
        public int h;

        public String toString() {
            return String.format("%s[buffer=%s, currentLinePos=%s, eof=%s, ibitWorkArea=%s, lbitWorkArea=%s, modulus=%s, pos=%s, readPos=%s]", a.class.getSimpleName(), Arrays.toString(this.c), Integer.valueOf(this.g), Boolean.valueOf(this.f), Integer.valueOf(this.f14333a), Long.valueOf(this.b), Integer.valueOf(this.h), Integer.valueOf(this.d), Integer.valueOf(this.e));
        }
    }

    public BaseNCodec(int i, int i2, int i3, int i4) {
        this(i, i2, i3, i4, (byte) 61);
    }

    public static int b(int i, int i2) {
        return Integer.compare(i - 2147483648, i2 - 2147483648);
    }

    public static int c(int i) {
        if (i >= 0) {
            return i > 2147483639 ? i : Constants.SINGLE_BEST_ACTIVITY_NOTIFICATION_ID;
        }
        throw new OutOfMemoryError("Unable to allocate array size: " + (i & 4294967295L));
    }

    public static byte[] getChunkSeparator() {
        return (byte[]) e.clone();
    }

    public static byte[] h(a aVar, int i) {
        int length = aVar.c.length * 2;
        if (b(length, i) < 0) {
            length = i;
        }
        if (b(length, Constants.SINGLE_BEST_ACTIVITY_NOTIFICATION_ID) > 0) {
            length = c(i);
        }
        byte[] bArr = new byte[length];
        byte[] bArr2 = aVar.c;
        System.arraycopy(bArr2, 0, bArr, 0, bArr2.length);
        aVar.c = bArr;
        return bArr;
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

    public boolean containsAlphabetOrPad(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        for (byte b : bArr) {
            if (this.pad == b || isInAlphabet(b)) {
                return true;
            }
        }
        return false;
    }

    public abstract void d(byte[] bArr, int i, int i2, a aVar);

    @Override // org.apache.commons.codec.BinaryDecoder
    public byte[] decode(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        a aVar = new a();
        d(bArr, 0, bArr.length, aVar);
        d(bArr, 0, -1, aVar);
        int i = aVar.d;
        byte[] bArr2 = new byte[i];
        g(bArr2, 0, i, aVar);
        return bArr2;
    }

    public abstract void e(byte[] bArr, int i, int i2, a aVar);

    @Override // org.apache.commons.codec.BinaryEncoder
    public byte[] encode(byte[] bArr) {
        return (bArr == null || bArr.length == 0) ? bArr : encode(bArr, 0, bArr.length);
    }

    public String encodeAsString(byte[] bArr) {
        return StringUtils.newStringUtf8(encode(bArr));
    }

    public String encodeToString(byte[] bArr) {
        return StringUtils.newStringUtf8(encode(bArr));
    }

    public byte[] ensureBufferSize(int i, a aVar) {
        byte[] bArr = aVar.c;
        if (bArr == null) {
            aVar.c = new byte[Math.max(i, getDefaultBufferSize())];
            aVar.d = 0;
            aVar.e = 0;
        } else {
            int i2 = aVar.d;
            if ((i2 + i) - bArr.length > 0) {
                return h(aVar, i2 + i);
            }
        }
        return aVar.c;
    }

    public boolean f(a aVar) {
        return aVar.c != null;
    }

    public int g(byte[] bArr, int i, int i2, a aVar) {
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

    public CodecPolicy getCodecPolicy() {
        return this.d;
    }

    public int getDefaultBufferSize() {
        return 8192;
    }

    public long getEncodedLength(byte[] bArr) {
        int length = bArr.length;
        int i = this.f14332a;
        long j = (((length + i) - 1) / i) * this.b;
        int i2 = this.lineLength;
        return i2 > 0 ? j + ((((i2 + j) - 1) / i2) * this.c) : j;
    }

    public abstract boolean isInAlphabet(byte b);

    public boolean isInAlphabet(byte[] bArr, boolean z) {
        for (byte b : bArr) {
            if (!isInAlphabet(b) && (!z || (b != this.pad && !isWhiteSpace(b)))) {
                return false;
            }
        }
        return true;
    }

    public boolean isStrictDecoding() {
        return this.d == CodecPolicy.STRICT;
    }

    public BaseNCodec(int i, int i2, int i3, int i4, byte b) {
        this(i, i2, i3, i4, b, DECODING_POLICY_DEFAULT);
    }

    public BaseNCodec(int i, int i2, int i3, int i4, byte b, CodecPolicy codecPolicy) {
        this.PAD = (byte) 61;
        this.f14332a = i;
        this.b = i2;
        this.lineLength = i3 > 0 && i4 > 0 ? (i3 / i2) * i2 : 0;
        this.c = i4;
        this.pad = b;
        Objects.requireNonNull(codecPolicy, "codecPolicy");
        this.d = codecPolicy;
    }

    public byte[] encode(byte[] bArr, int i, int i2) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        a aVar = new a();
        e(bArr, i, i2, aVar);
        e(bArr, i, -1, aVar);
        int i3 = aVar.d - aVar.e;
        byte[] bArr2 = new byte[i3];
        g(bArr2, 0, i3, aVar);
        return bArr2;
    }

    public boolean isInAlphabet(String str) {
        return isInAlphabet(StringUtils.getBytesUtf8(str), true);
    }

    @Override // org.apache.commons.codec.Decoder
    public Object decode(Object obj) throws DecoderException {
        if (obj instanceof byte[]) {
            return decode((byte[]) obj);
        }
        if (obj instanceof String) {
            return decode((String) obj);
        }
        throw new DecoderException("Parameter supplied to Base-N decode is not a byte[] or a String");
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (obj instanceof byte[]) {
            return encode((byte[]) obj);
        }
        throw new EncoderException("Parameter supplied to Base-N encode is not a byte[]");
    }

    public byte[] decode(String str) {
        return decode(StringUtils.getBytesUtf8(str));
    }
}
