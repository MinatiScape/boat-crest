package org.jose4j.jwe;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.jwx.KeyValidationSupport;
import org.jose4j.keys.KeyPersuasion;
import org.jose4j.lang.ExceptionHelp;
import org.jose4j.lang.InvalidKeyException;
/* loaded from: classes13.dex */
public class AesKeyWrapManagementAlgorithm extends WrappingKeyManagementAlgorithm {
    public int f;

    /* loaded from: classes13.dex */
    public static class Aes128 extends AesKeyWrapManagementAlgorithm {
        public Aes128() {
            super(KeyManagementAlgorithmIdentifiers.A128KW, 16);
        }
    }

    /* loaded from: classes13.dex */
    public static class Aes192 extends AesKeyWrapManagementAlgorithm {
        public Aes192() {
            super(KeyManagementAlgorithmIdentifiers.A192KW, 24);
        }
    }

    /* loaded from: classes13.dex */
    public static class Aes256 extends AesKeyWrapManagementAlgorithm {
        public Aes256() {
            super(KeyManagementAlgorithmIdentifiers.A256KW, 32);
        }
    }

    public AesKeyWrapManagementAlgorithm(String str, int i) {
        super("AESWrap", str);
        setKeyType(OctetSequenceJsonWebKey.KEY_TYPE);
        setKeyPersuasion(KeyPersuasion.SYMMETRIC);
        this.f = i;
    }

    public int c() {
        return this.f;
    }

    public AesKeyWrapManagementAlgorithm d() {
        this.useSuppliedKeyProviderContext = false;
        return this;
    }

    @Override // org.jose4j.jwa.Algorithm
    public boolean isAvailable() {
        int c = c();
        String javaAlgorithm = getJavaAlgorithm();
        try {
            Cipher.getInstance(javaAlgorithm);
            return CipherStrengthSupport.isAvailable(javaAlgorithm, c);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            ((WrappingKeyManagementAlgorithm) this).log.debug("{} for {} is not available ({}).", javaAlgorithm, getAlgorithmIdentifier(), ExceptionHelp.toStringWithCauses(e));
            return false;
        }
    }

    @Override // org.jose4j.jwe.KeyManagementAlgorithm
    public void validateDecryptionKey(Key key, ContentEncryptionAlgorithm contentEncryptionAlgorithm) throws InvalidKeyException {
        validateKey(key);
    }

    @Override // org.jose4j.jwe.KeyManagementAlgorithm
    public void validateEncryptionKey(Key key, ContentEncryptionAlgorithm contentEncryptionAlgorithm) throws InvalidKeyException {
        validateKey(key);
    }

    public void validateKey(Key key) throws InvalidKeyException {
        KeyValidationSupport.validateAesWrappingKey(key, getAlgorithmIdentifier(), c());
    }
}
