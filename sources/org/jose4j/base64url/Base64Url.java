package org.jose4j.base64url;

import org.jose4j.lang.StringUtil;
/* loaded from: classes13.dex */
public class Base64Url {

    /* renamed from: a  reason: collision with root package name */
    public org.jose4j.base64url.internal.apache.commons.codec.binary.Base64 f15495a = new org.jose4j.base64url.internal.apache.commons.codec.binary.Base64(-1, null, true);

    public static Base64Url a() {
        return new Base64Url();
    }

    public static byte[] decode(String str) {
        return a().base64UrlDecode(str);
    }

    public static String decodeToString(String str, String str2) {
        return a().base64UrlDecodeToString(str, str2);
    }

    public static String decodeToUtf8String(String str) {
        return a().base64UrlDecodeToString(str, "UTF-8");
    }

    public static String encode(String str, String str2) {
        return a().base64UrlEncode(str, str2);
    }

    public static String encodeUtf8ByteRepresentation(String str) {
        return a().base64UrlEncodeUtf8ByteRepresentation(str);
    }

    public byte[] base64UrlDecode(String str) {
        return this.f15495a.decode(str);
    }

    public String base64UrlDecodeToString(String str, String str2) {
        return StringUtil.newString(base64UrlDecode(str), str2);
    }

    public String base64UrlDecodeToUtf8String(String str) {
        return base64UrlDecodeToString(str, "UTF-8");
    }

    public String base64UrlEncode(String str, String str2) {
        return base64UrlEncode(StringUtil.getBytesUnchecked(str, str2));
    }

    public String base64UrlEncodeUtf8ByteRepresentation(String str) {
        return base64UrlEncode(str, "UTF-8");
    }

    public static String encode(byte[] bArr) {
        return a().base64UrlEncode(bArr);
    }

    public String base64UrlEncode(byte[] bArr) {
        return this.f15495a.encodeToString(bArr);
    }
}
