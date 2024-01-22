package org.jose4j.jwk;

import java.security.KeyPair;
import java.security.SecureRandom;
import java.security.spec.ECParameterSpec;
import org.jose4j.jwk.PublicJsonWebKey;
import org.jose4j.keys.EcKeyUtil;
import org.jose4j.lang.JoseException;
/* loaded from: classes13.dex */
public class EcJwkGenerator {
    public static EllipticCurveJsonWebKey generateJwk(ECParameterSpec eCParameterSpec) throws JoseException {
        return generateJwk(eCParameterSpec, null, null);
    }

    public static EllipticCurveJsonWebKey generateJwk(ECParameterSpec eCParameterSpec, String str, SecureRandom secureRandom) throws JoseException {
        KeyPair generateKeyPair = new EcKeyUtil(str, secureRandom).generateKeyPair(eCParameterSpec);
        EllipticCurveJsonWebKey ellipticCurveJsonWebKey = (EllipticCurveJsonWebKey) PublicJsonWebKey.Factory.newPublicJwk(generateKeyPair.getPublic());
        ellipticCurveJsonWebKey.setPrivateKey(generateKeyPair.getPrivate());
        return ellipticCurveJsonWebKey;
    }
}
