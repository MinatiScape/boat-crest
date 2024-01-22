package org.apache.commons.codec.digest;

import org.jose4j.mac.MacUtil;
/* loaded from: classes12.dex */
public enum HmacAlgorithms {
    HMAC_MD5("HmacMD5"),
    HMAC_SHA_1("HmacSHA1"),
    HMAC_SHA_224("HmacSHA224"),
    HMAC_SHA_256(MacUtil.HMAC_SHA256),
    HMAC_SHA_384(MacUtil.HMAC_SHA384),
    HMAC_SHA_512(MacUtil.HMAC_SHA512);
    
    private final String name;

    HmacAlgorithms(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.name;
    }
}
