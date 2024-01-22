package org.jose4j.jws;

import java.security.Key;
import org.jose4j.jca.ProviderContext;
import org.jose4j.jwa.Algorithm;
import org.jose4j.jwa.CryptoPrimitive;
import org.jose4j.lang.InvalidKeyException;
import org.jose4j.lang.JoseException;
/* loaded from: classes13.dex */
public interface JsonWebSignatureAlgorithm extends Algorithm {
    CryptoPrimitive prepareForSign(Key key, ProviderContext providerContext) throws JoseException;

    byte[] sign(CryptoPrimitive cryptoPrimitive, byte[] bArr) throws JoseException;

    void validateSigningKey(Key key) throws InvalidKeyException;

    void validateVerificationKey(Key key) throws InvalidKeyException;

    boolean verifySignature(byte[] bArr, Key key, byte[] bArr2, ProviderContext providerContext) throws JoseException;
}
