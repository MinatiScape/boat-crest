package org.jose4j.lang;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
/* loaded from: classes13.dex */
public class StringUtil {
    public static final String US_ASCII = "US-ASCII";
    public static final String UTF_8 = "UTF-8";

    public static IllegalStateException a(String str) {
        return new IllegalStateException("Unknown or unsupported character set name: " + str);
    }

    public static byte[] getBytes(String str, Charset charset) {
        if (str == null) {
            return null;
        }
        return str.getBytes(charset);
    }

    public static byte[] getBytesAscii(String str) {
        return getBytesUnchecked(str, "US-ASCII");
    }

    public static byte[] getBytesUnchecked(String str, String str2) {
        if (str == null) {
            return null;
        }
        try {
            return str.getBytes(str2);
        } catch (UnsupportedEncodingException unused) {
            throw a(str2);
        }
    }

    public static byte[] getBytesUtf8(String str) {
        return getBytesUnchecked(str, "UTF-8");
    }

    public static String newString(byte[] bArr, String str) {
        if (bArr == null) {
            return null;
        }
        try {
            return new String(bArr, str);
        } catch (UnsupportedEncodingException unused) {
            throw a(str);
        }
    }

    public static String newStringUsAscii(byte[] bArr) {
        return newString(bArr, "US-ASCII");
    }

    public static String newStringUtf8(byte[] bArr) {
        return newString(bArr, "UTF-8");
    }

    public static String newString(byte[] bArr, Charset charset) {
        if (bArr == null) {
            return null;
        }
        return new String(bArr, charset);
    }
}
