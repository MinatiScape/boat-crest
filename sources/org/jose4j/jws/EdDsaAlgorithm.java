package org.jose4j.jws;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.EdECPrivateKey;
import java.security.interfaces.EdECPublicKey;
import org.jose4j.jwk.OctetKeyPairJsonWebKey;
import org.jose4j.jwx.KeyValidationSupport;
import org.jose4j.lang.InvalidKeyException;
/* loaded from: classes13.dex */
public class EdDsaAlgorithm extends BaseSignatureAlgorithm {
    public EdDsaAlgorithm() {
        super(AlgorithmIdentifiers.EDDSA, AlgorithmIdentifiers.EDDSA, OctetKeyPairJsonWebKey.KEY_TYPE);
    }

    @Override // org.jose4j.jws.BaseSignatureAlgorithm
    public void validatePrivateKey(PrivateKey privateKey) throws InvalidKeyException {
        KeyValidationSupport.castKey(privateKey, EdECPrivateKey.class);
    }

    @Override // org.jose4j.jws.BaseSignatureAlgorithm
    public void validatePublicKey(PublicKey publicKey) throws InvalidKeyException {
        KeyValidationSupport.castKey(publicKey, EdECPublicKey.class);
    }
}
