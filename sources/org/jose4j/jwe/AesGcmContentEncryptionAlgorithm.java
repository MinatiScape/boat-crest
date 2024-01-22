package org.jose4j.jwe;

import org.jose4j.jca.ProviderContext;
import org.jose4j.jwa.AlgorithmInfo;
import org.jose4j.jwe.SimpleAeadCipher;
import org.jose4j.jwx.Headers;
import org.jose4j.keys.AesKey;
import org.jose4j.keys.KeyPersuasion;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.JoseException;
/* loaded from: classes13.dex */
public class AesGcmContentEncryptionAlgorithm extends AlgorithmInfo implements ContentEncryptionAlgorithm {
    public ContentEncryptionKeyDescriptor e;
    public SimpleAeadCipher f;

    /* loaded from: classes13.dex */
    public static class Aes128Gcm extends AesGcmContentEncryptionAlgorithm {
        public Aes128Gcm() {
            super(ContentEncryptionAlgorithmIdentifiers.AES_128_GCM, 128);
        }
    }

    /* loaded from: classes13.dex */
    public static class Aes192Gcm extends AesGcmContentEncryptionAlgorithm {
        public Aes192Gcm() {
            super(ContentEncryptionAlgorithmIdentifiers.AES_192_GCM, 192);
        }
    }

    /* loaded from: classes13.dex */
    public static class Aes256Gcm extends AesGcmContentEncryptionAlgorithm {
        public Aes256Gcm() {
            super(ContentEncryptionAlgorithmIdentifiers.AES_256_GCM, 256);
        }
    }

    public AesGcmContentEncryptionAlgorithm(String str, int i) {
        setAlgorithmIdentifier(str);
        setJavaAlgorithm(SimpleAeadCipher.GCM_TRANSFORMATION_NAME);
        setKeyPersuasion(KeyPersuasion.SYMMETRIC);
        setKeyType(AesKey.ALGORITHM);
        this.e = new ContentEncryptionKeyDescriptor(ByteUtil.byteLength(i), AesKey.ALGORITHM);
        this.f = new SimpleAeadCipher(getJavaAlgorithm(), 16);
    }

    @Override // org.jose4j.jwe.ContentEncryptionAlgorithm
    public byte[] decrypt(ContentEncryptionParts contentEncryptionParts, byte[] bArr, byte[] bArr2, Headers headers, ProviderContext providerContext) throws JoseException {
        byte[] iv = contentEncryptionParts.getIv();
        return this.f.decrypt(new AesKey(bArr2), iv, contentEncryptionParts.getCiphertext(), contentEncryptionParts.getAuthenticationTag(), bArr, a.b(headers, providerContext));
    }

    @Override // org.jose4j.jwe.ContentEncryptionAlgorithm
    public ContentEncryptionParts encrypt(byte[] bArr, byte[] bArr2, byte[] bArr3, Headers headers, byte[] bArr4, ProviderContext providerContext) throws JoseException {
        return encrypt(bArr, bArr2, bArr3, InitializationVectorHelp.a(12, bArr4, providerContext.getSecureRandom()), a.b(headers, providerContext));
    }

    @Override // org.jose4j.jwe.ContentEncryptionAlgorithm
    public ContentEncryptionKeyDescriptor getContentEncryptionKeyDescriptor() {
        return this.e;
    }

    @Override // org.jose4j.jwa.Algorithm
    public boolean isAvailable() {
        return this.f.isAvailable(this.log, getContentEncryptionKeyDescriptor().getContentEncryptionKeyByteLength(), 12, getAlgorithmIdentifier());
    }

    public ContentEncryptionParts encrypt(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, String str) throws JoseException {
        SimpleAeadCipher.CipherOutput encrypt = this.f.encrypt(new AesKey(bArr3), bArr4, bArr, bArr2, str);
        return new ContentEncryptionParts(bArr4, encrypt.getCiphertext(), encrypt.getTag());
    }
}
