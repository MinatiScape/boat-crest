package org.jose4j.jwe;

import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.spec.SecretKeySpec;
import org.jose4j.base64url.Base64Url;
import org.jose4j.jca.ProviderContext;
import org.jose4j.jwa.AlgorithmInfo;
import org.jose4j.jwa.CryptoPrimitive;
import org.jose4j.jwe.SimpleAeadCipher;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.jwx.HeaderParameterNames;
import org.jose4j.jwx.Headers;
import org.jose4j.jwx.KeyValidationSupport;
import org.jose4j.keys.KeyPersuasion;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.InvalidKeyException;
import org.jose4j.lang.JoseException;
/* loaded from: classes13.dex */
public class AesGcmKeyEncryptionAlgorithm extends AlgorithmInfo implements KeyManagementAlgorithm {
    public SimpleAeadCipher e;
    public int f;

    /* loaded from: classes13.dex */
    public static class Aes128Gcm extends AesGcmKeyEncryptionAlgorithm {
        public Aes128Gcm() {
            super(KeyManagementAlgorithmIdentifiers.A128GCMKW, ByteUtil.byteLength(128));
        }
    }

    /* loaded from: classes13.dex */
    public static class Aes192Gcm extends AesGcmKeyEncryptionAlgorithm {
        public Aes192Gcm() {
            super(KeyManagementAlgorithmIdentifiers.A192GCMKW, ByteUtil.byteLength(192));
        }
    }

    /* loaded from: classes13.dex */
    public static class Aes256Gcm extends AesGcmKeyEncryptionAlgorithm {
        public Aes256Gcm() {
            super(KeyManagementAlgorithmIdentifiers.A256GCMKW, ByteUtil.byteLength(256));
        }
    }

    public AesGcmKeyEncryptionAlgorithm(String str, int i) {
        setAlgorithmIdentifier(str);
        setJavaAlgorithm(SimpleAeadCipher.GCM_TRANSFORMATION_NAME);
        setKeyPersuasion(KeyPersuasion.SYMMETRIC);
        setKeyType(OctetSequenceJsonWebKey.KEY_TYPE);
        this.e = new SimpleAeadCipher(getJavaAlgorithm(), 16);
        this.f = i;
    }

    @Override // org.jose4j.jwa.Algorithm
    public boolean isAvailable() {
        return this.e.isAvailable(this.log, this.f, 12, getAlgorithmIdentifier());
    }

    @Override // org.jose4j.jwe.KeyManagementAlgorithm
    public Key manageForDecrypt(CryptoPrimitive cryptoPrimitive, byte[] bArr, ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor, Headers headers, ProviderContext providerContext) throws JoseException {
        return new SecretKeySpec(this.e.decrypt(bArr, new Base64Url().base64UrlDecode(headers.getStringHeaderValue(HeaderParameterNames.AUTHENTICATION_TAG)), null, cryptoPrimitive.getCipher()), contentEncryptionKeyDescriptor.getContentEncryptionKeyAlgorithm());
    }

    @Override // org.jose4j.jwe.KeyManagementAlgorithm
    public ContentEncryptionKeys manageForEncrypt(Key key, ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor, Headers headers, byte[] bArr, ProviderContext providerContext) throws JoseException {
        byte[] base64UrlDecode;
        SecureRandom secureRandom = providerContext.getSecureRandom();
        if (bArr == null) {
            bArr = ByteUtil.randomBytes(contentEncryptionKeyDescriptor.getContentEncryptionKeyByteLength(), secureRandom);
        }
        Base64Url base64Url = new Base64Url();
        String stringHeaderValue = headers.getStringHeaderValue(HeaderParameterNames.INITIALIZATION_VECTOR);
        if (stringHeaderValue == null) {
            base64UrlDecode = ByteUtil.randomBytes(12, secureRandom);
            headers.setStringHeaderValue(HeaderParameterNames.INITIALIZATION_VECTOR, base64Url.base64UrlEncode(base64UrlDecode));
        } else {
            base64UrlDecode = base64Url.base64UrlDecode(stringHeaderValue);
        }
        String cipherProvider = providerContext.getSuppliedKeyProviderContext().getCipherProvider();
        SimpleAeadCipher.CipherOutput encrypt = this.e.encrypt(key, base64UrlDecode, bArr, null, cipherProvider);
        byte[] ciphertext = encrypt.getCiphertext();
        headers.setStringHeaderValue(HeaderParameterNames.AUTHENTICATION_TAG, base64Url.base64UrlEncode(encrypt.getTag()));
        return new ContentEncryptionKeys(bArr, ciphertext);
    }

    @Override // org.jose4j.jwe.KeyManagementAlgorithm
    public CryptoPrimitive prepareForDecrypt(Key key, Headers headers, ProviderContext providerContext) throws JoseException {
        return new CryptoPrimitive(this.e.getInitialisedCipher(key, new Base64Url().base64UrlDecode(headers.getStringHeaderValue(HeaderParameterNames.INITIALIZATION_VECTOR)), 2, providerContext.getSuppliedKeyProviderContext().getCipherProvider()));
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
        KeyValidationSupport.validateAesWrappingKey(key, getAlgorithmIdentifier(), this.f);
    }
}
