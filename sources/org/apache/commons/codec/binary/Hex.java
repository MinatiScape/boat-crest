package org.apache.commons.codec.binary;

import com.clevertap.android.sdk.Constants;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
/* loaded from: classes12.dex */
public class Hex implements BinaryEncoder, BinaryDecoder {
    public static final String DEFAULT_CHARSET_NAME = "UTF-8";

    /* renamed from: a  reason: collision with root package name */
    public final Charset f14335a;
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    public static final char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', Constants.INAPP_POSITION_BOTTOM, Constants.INAPP_POSITION_CENTER, 'd', 'e', 'f'};
    public static final char[] c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public Hex() {
        this.f14335a = DEFAULT_CHARSET;
    }

    public static void a(byte[] bArr, int i, int i2, char[] cArr, char[] cArr2, int i3) {
        for (int i4 = i; i4 < i + i2; i4++) {
            int i5 = i3 + 1;
            cArr2[i3] = cArr[(bArr[i4] & 240) >>> 4];
            i3 = i5 + 1;
            cArr2[i5] = cArr[bArr[i4] & 15];
        }
    }

    public static byte[] b(ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining();
        if (byteBuffer.hasArray()) {
            byte[] array = byteBuffer.array();
            if (remaining == array.length) {
                byteBuffer.position(remaining);
                return array;
            }
        }
        byte[] bArr = new byte[remaining];
        byteBuffer.get(bArr);
        return bArr;
    }

    public static byte[] decodeHex(char[] cArr) throws DecoderException {
        byte[] bArr = new byte[cArr.length >> 1];
        decodeHex(cArr, bArr, 0);
        return bArr;
    }

    public static char[] encodeHex(byte[] bArr) {
        return encodeHex(bArr, true);
    }

    public static String encodeHexString(byte[] bArr) {
        return new String(encodeHex(bArr));
    }

    public static int toDigit(char c2, int i) throws DecoderException {
        int digit = Character.digit(c2, 16);
        if (digit != -1) {
            return digit;
        }
        throw new DecoderException("Illegal hexadecimal character " + c2 + " at index " + i);
    }

    @Override // org.apache.commons.codec.BinaryDecoder
    public byte[] decode(byte[] bArr) throws DecoderException {
        return decodeHex(new String(bArr, getCharset()).toCharArray());
    }

    @Override // org.apache.commons.codec.BinaryEncoder
    public byte[] encode(byte[] bArr) {
        return encodeHexString(bArr).getBytes(getCharset());
    }

    public Charset getCharset() {
        return this.f14335a;
    }

    public String getCharsetName() {
        return this.f14335a.name();
    }

    public String toString() {
        return super.toString() + "[charsetName=" + this.f14335a + "]";
    }

    public static char[] encodeHex(byte[] bArr, boolean z) {
        return encodeHex(bArr, z ? b : c);
    }

    public static String encodeHexString(byte[] bArr, boolean z) {
        return new String(encodeHex(bArr, z));
    }

    public byte[] decode(ByteBuffer byteBuffer) throws DecoderException {
        return decodeHex(new String(b(byteBuffer), getCharset()).toCharArray());
    }

    public byte[] encode(ByteBuffer byteBuffer) {
        return encodeHexString(byteBuffer).getBytes(getCharset());
    }

    public Hex(Charset charset) {
        this.f14335a = charset;
    }

    public static int decodeHex(char[] cArr, byte[] bArr, int i) throws DecoderException {
        int length = cArr.length;
        if ((length & 1) == 0) {
            int i2 = length >> 1;
            if (bArr.length - i >= i2) {
                int i3 = 0;
                while (i3 < length) {
                    int i4 = i3 + 1;
                    i3 = i4 + 1;
                    bArr[i] = (byte) (((toDigit(cArr[i3], i3) << 4) | toDigit(cArr[i4], i4)) & 255);
                    i++;
                }
                return i2;
            }
            throw new DecoderException("Output array is not large enough to accommodate decoded data.");
        }
        throw new DecoderException("Odd number of characters.");
    }

    public static char[] encodeHex(byte[] bArr, char[] cArr) {
        char[] cArr2 = new char[bArr.length << 1];
        a(bArr, 0, bArr.length, cArr, cArr2, 0);
        return cArr2;
    }

    public static String encodeHexString(ByteBuffer byteBuffer) {
        return new String(encodeHex(byteBuffer));
    }

    @Override // org.apache.commons.codec.Decoder
    public Object decode(Object obj) throws DecoderException {
        if (obj instanceof String) {
            return decode(((String) obj).toCharArray());
        }
        if (obj instanceof byte[]) {
            return decode((byte[]) obj);
        }
        if (obj instanceof ByteBuffer) {
            return decode((ByteBuffer) obj);
        }
        try {
            return decodeHex((char[]) obj);
        } catch (ClassCastException e) {
            throw new DecoderException(e.getMessage(), e);
        }
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        byte[] bArr;
        if (obj instanceof String) {
            bArr = ((String) obj).getBytes(getCharset());
        } else if (obj instanceof ByteBuffer) {
            bArr = b((ByteBuffer) obj);
        } else {
            try {
                bArr = (byte[]) obj;
            } catch (ClassCastException e) {
                throw new EncoderException(e.getMessage(), e);
            }
        }
        return encodeHex(bArr);
    }

    public static String encodeHexString(ByteBuffer byteBuffer, boolean z) {
        return new String(encodeHex(byteBuffer, z));
    }

    public Hex(String str) {
        this(Charset.forName(str));
    }

    public static char[] encodeHex(byte[] bArr, int i, int i2, boolean z) {
        char[] cArr = new char[i2 << 1];
        a(bArr, i, i2, z ? b : c, cArr, 0);
        return cArr;
    }

    public static void encodeHex(byte[] bArr, int i, int i2, boolean z, char[] cArr, int i3) {
        a(bArr, i, i2, z ? b : c, cArr, i3);
    }

    public static char[] encodeHex(ByteBuffer byteBuffer) {
        return encodeHex(byteBuffer, true);
    }

    public static byte[] decodeHex(String str) throws DecoderException {
        return decodeHex(str.toCharArray());
    }

    public static char[] encodeHex(ByteBuffer byteBuffer, boolean z) {
        return encodeHex(byteBuffer, z ? b : c);
    }

    public static char[] encodeHex(ByteBuffer byteBuffer, char[] cArr) {
        return encodeHex(b(byteBuffer), cArr);
    }
}
