package org.jose4j.jwe;

import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import org.jose4j.jca.ProviderContext;
import org.jose4j.jwa.AlgorithmInfo;
import org.jose4j.jwa.CryptoPrimitive;
import org.jose4j.jwe.AesKeyWrapManagementAlgorithm;
import org.jose4j.jwx.Headers;
import org.jose4j.keys.AesKey;
import org.jose4j.keys.KeyPersuasion;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.InvalidKeyException;
import org.jose4j.lang.JoseException;
/* loaded from: classes13.dex */
public class EcdhKeyAgreementWithAesKeyWrapAlgorithm extends AlgorithmInfo implements KeyManagementAlgorithm {
    public AesKeyWrapManagementAlgorithm e;
    public ContentEncryptionKeyDescriptor f;
    public EcdhKeyAgreementAlgorithm g;

    /* loaded from: classes13.dex */
    public static class EcdhKeyAgreementWithAes128KeyWrapAlgorithm extends EcdhKeyAgreementWithAesKeyWrapAlgorithm {
        public EcdhKeyAgreementWithAes128KeyWrapAlgorithm() {
            super(KeyManagementAlgorithmIdentifiers.ECDH_ES_A128KW, new AesKeyWrapManagementAlgorithm.Aes128().d());
        }
    }

    /* loaded from: classes13.dex */
    public static class EcdhKeyAgreementWithAes192KeyWrapAlgorithm extends EcdhKeyAgreementWithAesKeyWrapAlgorithm {
        public EcdhKeyAgreementWithAes192KeyWrapAlgorithm() {
            super(KeyManagementAlgorithmIdentifiers.ECDH_ES_A192KW, new AesKeyWrapManagementAlgorithm.Aes192().d());
        }
    }

    /* loaded from: classes13.dex */
    public static class EcdhKeyAgreementWithAes256KeyWrapAlgorithm extends EcdhKeyAgreementWithAesKeyWrapAlgorithm {
        public EcdhKeyAgreementWithAes256KeyWrapAlgorithm() {
            super(KeyManagementAlgorithmIdentifiers.ECDH_ES_A256KW, new AesKeyWrapManagementAlgorithm.Aes256().d());
        }
    }

    public EcdhKeyAgreementWithAesKeyWrapAlgorithm(String str, AesKeyWrapManagementAlgorithm aesKeyWrapManagementAlgorithm) {
        setAlgorithmIdentifier(str);
        setJavaAlgorithm("N/A");
        setKeyType("EC");
        setKeyPersuasion(KeyPersuasion.ASYMMETRIC);
        this.e = aesKeyWrapManagementAlgorithm;
        this.g = new EcdhKeyAgreementAlgorithm("alg");
        this.f = new ContentEncryptionKeyDescriptor(aesKeyWrapManagementAlgorithm.c(), AesKey.ALGORITHM);
    }

    @Override // org.jose4j.jwa.Algorithm
    public boolean isAvailable() {
        return this.g.isAvailable() && this.e.isAvailable();
    }

    @Override // org.jose4j.jwe.KeyManagementAlgorithm
    public Key manageForDecrypt(CryptoPrimitive cryptoPrimitive, byte[] bArr, ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor, Headers headers, ProviderContext providerContext) throws JoseException {
        return this.e.manageForDecrypt(this.e.prepareForDecrypt(this.g.manageForDecrypt(cryptoPrimitive, ByteUtil.EMPTY_BYTES, this.f, headers, providerContext), headers, providerContext), bArr, contentEncryptionKeyDescriptor, headers, providerContext);
    }

    @Override // org.jose4j.jwe.KeyManagementAlgorithm
    public ContentEncryptionKeys manageForEncrypt(Key key, ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor, Headers headers, byte[] bArr, ProviderContext providerContext) throws JoseException {
        ContentEncryptionKeys manageForEncrypt = this.g.manageForEncrypt(key, this.f, headers, null, providerContext);
        return this.e.manageForEncrypt(new SecretKeySpec(manageForEncrypt.getContentEncryptionKey(), this.f.getContentEncryptionKeyAlgorithm()), contentEncryptionKeyDescriptor, headers, bArr, providerContext);
    }

    @Override // org.jose4j.jwe.KeyManagementAlgorithm
    public CryptoPrimitive prepareForDecrypt(Key key, Headers headers, ProviderContext providerContext) throws JoseException {
        return this.g.prepareForDecrypt(key, headers, providerContext);
    }

    @Override // org.jose4j.jwe.KeyManagementAlgorithm
    public void validateDecryptionKey(Key key, ContentEncryptionAlgorithm contentEncryptionAlgorithm) throws InvalidKeyException {
        this.g.validateDecryptionKey(key, contentEncryptionAlgorithm);
    }

    @Override // org.jose4j.jwe.KeyManagementAlgorithm
    public void validateEncryptionKey(Key key, ContentEncryptionAlgorithm contentEncryptionAlgorithm) throws InvalidKeyException {
        this.g.validateEncryptionKey(key, contentEncryptionAlgorithm);
    }
}
