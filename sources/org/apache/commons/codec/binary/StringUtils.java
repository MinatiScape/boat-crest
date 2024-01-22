package org.apache.commons.codec.binary;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
/* loaded from: classes12.dex */
public class StringUtils {
    public static ByteBuffer a(String str, Charset charset) {
        if (str == null) {
            return null;
        }
        return ByteBuffer.wrap(str.getBytes(charset));
    }

    public static byte[] b(String str, Charset charset) {
        if (str == null) {
            return null;
        }
        return str.getBytes(charset);
    }

    public static IllegalStateException c(String str, UnsupportedEncodingException unsupportedEncodingException) {
        return new IllegalStateException(str + ": " + unsupportedEncodingException);
    }

    public static String d(byte[] bArr, Charset charset) {
        if (bArr == null) {
            return null;
        }
        return new String(bArr, charset);
    }

    public static boolean equals(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == charSequence2) {
            return true;
        }
        if (charSequence == null || charSequence2 == null) {
            return false;
        }
        if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
            return charSequence.equals(charSequence2);
        }
        return charSequence.length() == charSequence2.length() && CharSequenceUtils.a(charSequence, false, 0, charSequence2, 0, charSequence.length());
    }

    public static ByteBuffer getByteBufferUtf8(String str) {
        return a(str, StandardCharsets.UTF_8);
    }

    public static byte[] getBytesIso8859_1(String str) {
        return b(str, StandardCharsets.ISO_8859_1);
    }

    public static byte[] getBytesUnchecked(String str, String str2) {
        if (str == null) {
            return null;
        }
        try {
            return str.getBytes(str2);
        } catch (UnsupportedEncodingException e) {
            throw c(str2, e);
        }
    }

    public static byte[] getBytesUsAscii(String str) {
        return b(str, StandardCharsets.US_ASCII);
    }

    public static byte[] getBytesUtf16(String str) {
        return b(str, StandardCharsets.UTF_16);
    }

    public static byte[] getBytesUtf16Be(String str) {
        return b(str, StandardCharsets.UTF_16BE);
    }

    public static byte[] getBytesUtf16Le(String str) {
        return b(str, StandardCharsets.UTF_16LE);
    }

    public static byte[] getBytesUtf8(String str) {
        return b(str, StandardCharsets.UTF_8);
    }

    public static String newString(byte[] bArr, String str) {
        if (bArr == null) {
            return null;
        }
        try {
            return new String(bArr, str);
        } catch (UnsupportedEncodingException e) {
            throw c(str, e);
        }
    }

    public static String newStringIso8859_1(byte[] bArr) {
        return d(bArr, StandardCharsets.ISO_8859_1);
    }

    public static String newStringUsAscii(byte[] bArr) {
        return d(bArr, StandardCharsets.US_ASCII);
    }

    public static String newStringUtf16(byte[] bArr) {
        return d(bArr, StandardCharsets.UTF_16);
    }

    public static String newStringUtf16Be(byte[] bArr) {
        return d(bArr, StandardCharsets.UTF_16BE);
    }

    public static String newStringUtf16Le(byte[] bArr) {
        return d(bArr, StandardCharsets.UTF_16LE);
    }

    public static String newStringUtf8(byte[] bArr) {
        return d(bArr, StandardCharsets.UTF_8);
    }
}
