package org.jose4j.jwe;

import java.security.Key;
import org.jose4j.jca.ProviderContext;
import org.jose4j.jwa.AlgorithmInfo;
import org.jose4j.jwa.CryptoPrimitive;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.jwx.Headers;
import org.jose4j.jwx.KeyValidationSupport;
import org.jose4j.keys.KeyPersuasion;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.InvalidKeyException;
import org.jose4j.lang.JoseException;
/* loaded from: classes13.dex */
public class DirectKeyManagementAlgorithm extends AlgorithmInfo implements KeyManagementAlgorithm {
    public DirectKeyManagementAlgorithm() {
        setAlgorithmIdentifier(KeyManagementAlgorithmIdentifiers.DIRECT);
        setKeyPersuasion(KeyPersuasion.SYMMETRIC);
        setKeyType(OctetSequenceJsonWebKey.KEY_TYPE);
    }

    public final void a(Key key, ContentEncryptionAlgorithm contentEncryptionAlgorithm) throws InvalidKeyException {
        int length;
        int contentEncryptionKeyByteLength;
        KeyValidationSupport.notNull(key);
        if (key.getEncoded() == null || (contentEncryptionKeyByteLength = contentEncryptionAlgorithm.getContentEncryptionKeyDescriptor().getContentEncryptionKeyByteLength()) == (length = key.getEncoded().length)) {
            return;
        }
        throw new InvalidKeyException("Invalid key for " + getAlgorithmIdentifier() + " with " + contentEncryptionAlgorithm.getAlgorithmIdentifier() + ", expected a " + ByteUtil.bitLength(contentEncryptionKeyByteLength) + " bit key but a " + ByteUtil.bitLength(length) + " bit key was provided.");
    }

    @Override // org.jose4j.jwa.Algorithm
    public boolean isAvailable() {
        return true;
    }

    @Override // org.jose4j.jwe.KeyManagementAlgorithm
    public Key manageForDecrypt(CryptoPrimitive cryptoPrimitive, byte[] bArr, ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor, Headers headers, ProviderContext providerContext) throws JoseException {
        Key key = cryptoPrimitive.getKey();
        if (bArr.length == 0) {
            return key;
        }
        throw new InvalidKeyException("An empty octet sequence is to be used as the JWE Encrypted Key value when utilizing direct encryption but this JWE has " + bArr.length + " octets in the encrypted key part.");
    }

    @Override // org.jose4j.jwe.KeyManagementAlgorithm
    public ContentEncryptionKeys manageForEncrypt(Key key, ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor, Headers headers, byte[] bArr, ProviderContext providerContext) throws JoseException {
        KeyValidationSupport.cekNotAllowed(bArr, getAlgorithmIdentifier());
        return new ContentEncryptionKeys(key.getEncoded(), ByteUtil.EMPTY_BYTES);
    }

    @Override // org.jose4j.jwe.KeyManagementAlgorithm
    public CryptoPrimitive prepareForDecrypt(Key key, Headers headers, ProviderContext providerContext) {
        return new CryptoPrimitive(key);
    }

    @Override // org.jose4j.jwe.KeyManagementAlgorithm
    public void validateDecryptionKey(Key key, ContentEncryptionAlgorithm contentEncryptionAlgorithm) throws InvalidKeyException {
        a(key, contentEncryptionAlgorithm);
    }

    @Override // org.jose4j.jwe.KeyManagementAlgorithm
    public void validateEncryptionKey(Key key, ContentEncryptionAlgorithm contentEncryptionAlgorithm) throws InvalidKeyException {
        a(key, contentEncryptionAlgorithm);
    }
}
