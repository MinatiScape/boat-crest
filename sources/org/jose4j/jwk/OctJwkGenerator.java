package org.jose4j.jwk;

import java.security.SecureRandom;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.ByteUtil;
/* loaded from: classes13.dex */
public class OctJwkGenerator {
    public static OctetSequenceJsonWebKey generateJwk(int i) {
        return generateJwk(i, null);
    }

    public static OctetSequenceJsonWebKey generateJwk(int i, SecureRandom secureRandom) {
        return new OctetSequenceJsonWebKey(new AesKey(ByteUtil.randomBytes(ByteUtil.byteLength(i), secureRandom)));
    }
}
