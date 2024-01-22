package org.jose4j.jwk;

import java.security.KeyPair;
import java.security.SecureRandom;
import org.jose4j.jwk.PublicJsonWebKey;
import org.jose4j.keys.OctetKeyPairUtil;
import org.jose4j.lang.JoseException;
/* loaded from: classes13.dex */
public class OkpJwkGenerator {
    public static OctetKeyPairJsonWebKey generateJwk(String str) throws JoseException {
        return generateJwk(str, null, null);
    }

    public static OctetKeyPairJsonWebKey generateJwk(String str, String str2, SecureRandom secureRandom) throws JoseException {
        OctetKeyPairUtil octetKeyPairUtil = OctetKeyPairUtil.getOctetKeyPairUtil(str, str2, secureRandom);
        if (octetKeyPairUtil != null) {
            KeyPair generateKeyPair = octetKeyPairUtil.generateKeyPair(str);
            OctetKeyPairJsonWebKey octetKeyPairJsonWebKey = (OctetKeyPairJsonWebKey) PublicJsonWebKey.Factory.newPublicJwk(generateKeyPair.getPublic());
            octetKeyPairJsonWebKey.setPrivateKey(generateKeyPair.getPrivate());
            return octetKeyPairJsonWebKey;
        }
        throw new IllegalArgumentException("Cannot create OKP JWK. The subtype/crv \"" + str + "\" is unknown or unsupported.");
    }
}
