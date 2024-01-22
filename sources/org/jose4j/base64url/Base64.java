package org.jose4j.base64url;
/* loaded from: classes13.dex */
public class Base64 {
    public static org.jose4j.base64url.internal.apache.commons.codec.binary.Base64 a() {
        return new org.jose4j.base64url.internal.apache.commons.codec.binary.Base64();
    }

    public static byte[] decode(String str) {
        return a().decode(str);
    }

    public static String encode(byte[] bArr) {
        return a().encodeToString(bArr);
    }
}
