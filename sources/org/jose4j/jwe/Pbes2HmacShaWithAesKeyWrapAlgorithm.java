package org.jose4j.jwe;

import android.support.v4.media.session.PlaybackStateCompat;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import org.jose4j.base64url.Base64Url;
import org.jose4j.jca.ProviderContext;
import org.jose4j.jwa.AlgorithmInfo;
import org.jose4j.jwa.CryptoPrimitive;
import org.jose4j.jwe.AesKeyWrapManagementAlgorithm;
import org.jose4j.jwe.kdf.PasswordBasedKeyDerivationFunction2;
import org.jose4j.jwx.HeaderParameterNames;
import org.jose4j.jwx.Headers;
import org.jose4j.jwx.KeyValidationSupport;
import org.jose4j.keys.AesKey;
import org.jose4j.keys.KeyPersuasion;
import org.jose4j.keys.PbkdfKey;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.InvalidKeyException;
import org.jose4j.lang.JoseException;
import org.jose4j.lang.StringUtil;
import org.jose4j.mac.MacUtil;
/* loaded from: classes13.dex */
public class Pbes2HmacShaWithAesKeyWrapAlgorithm extends AlgorithmInfo implements KeyManagementAlgorithm {
    public static final byte[] j = {0};
    public AesKeyWrapManagementAlgorithm e;
    public ContentEncryptionKeyDescriptor f;
    public PasswordBasedKeyDerivationFunction2 g;
    public long h = PlaybackStateCompat.ACTION_PLAY_FROM_URI;
    public int i = 12;

    /* loaded from: classes13.dex */
    public static class HmacSha256Aes128 extends Pbes2HmacShaWithAesKeyWrapAlgorithm {
        public HmacSha256Aes128() {
            super(KeyManagementAlgorithmIdentifiers.PBES2_HS256_A128KW, MacUtil.HMAC_SHA256, new AesKeyWrapManagementAlgorithm.Aes128().d());
        }
    }

    /* loaded from: classes13.dex */
    public static class HmacSha384Aes192 extends Pbes2HmacShaWithAesKeyWrapAlgorithm {
        public HmacSha384Aes192() {
            super(KeyManagementAlgorithmIdentifiers.PBES2_HS384_A192KW, MacUtil.HMAC_SHA384, new AesKeyWrapManagementAlgorithm.Aes192().d());
        }
    }

    /* loaded from: classes13.dex */
    public static class HmacSha512Aes256 extends Pbes2HmacShaWithAesKeyWrapAlgorithm {
        public HmacSha512Aes256() {
            super(KeyManagementAlgorithmIdentifiers.PBES2_HS512_A256KW, MacUtil.HMAC_SHA512, new AesKeyWrapManagementAlgorithm.Aes256().d());
        }
    }

    public Pbes2HmacShaWithAesKeyWrapAlgorithm(String str, String str2, AesKeyWrapManagementAlgorithm aesKeyWrapManagementAlgorithm) {
        setAlgorithmIdentifier(str);
        setJavaAlgorithm("n/a");
        this.g = new PasswordBasedKeyDerivationFunction2(str2);
        setKeyPersuasion(KeyPersuasion.SYMMETRIC);
        setKeyType(PbkdfKey.ALGORITHM);
        this.e = aesKeyWrapManagementAlgorithm;
        this.f = new ContentEncryptionKeyDescriptor(aesKeyWrapManagementAlgorithm.c(), AesKey.ALGORITHM);
    }

    public final Key a(Key key, Long l, byte[] bArr, ProviderContext providerContext) throws JoseException {
        return new SecretKeySpec(this.g.derive(key.getEncoded(), ByteUtil.concat(StringUtil.getBytesUtf8(getAlgorithmIdentifier()), j, bArr), l.intValue(), this.f.getContentEncryptionKeyByteLength(), providerContext.getSuppliedKeyProviderContext().getMacProvider()), this.f.getContentEncryptionKeyAlgorithm());
    }

    public Key deriveForEncrypt(Key key, Headers headers, ProviderContext providerContext) throws JoseException {
        byte[] base64UrlDecode;
        Long longHeaderValue = headers.getLongHeaderValue(HeaderParameterNames.PBES2_ITERATION_COUNT);
        if (longHeaderValue == null) {
            longHeaderValue = Long.valueOf(this.h);
            headers.setObjectHeaderValue(HeaderParameterNames.PBES2_ITERATION_COUNT, longHeaderValue);
        }
        String stringHeaderValue = headers.getStringHeaderValue(HeaderParameterNames.PBES2_SALT_INPUT);
        Base64Url base64Url = new Base64Url();
        if (stringHeaderValue == null) {
            base64UrlDecode = ByteUtil.randomBytes(this.i, providerContext.getSecureRandom());
            headers.setStringHeaderValue(HeaderParameterNames.PBES2_SALT_INPUT, base64Url.base64UrlEncode(base64UrlDecode));
        } else {
            base64UrlDecode = base64Url.base64UrlDecode(stringHeaderValue);
        }
        return a(key, longHeaderValue, base64UrlDecode, providerContext);
    }

    public long getDefaultIterationCount() {
        return this.h;
    }

    public int getDefaultSaltByteLength() {
        return this.i;
    }

    @Override // org.jose4j.jwa.Algorithm
    public boolean isAvailable() {
        return this.e.isAvailable();
    }

    @Override // org.jose4j.jwe.KeyManagementAlgorithm
    public Key manageForDecrypt(CryptoPrimitive cryptoPrimitive, byte[] bArr, ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor, Headers headers, ProviderContext providerContext) throws JoseException {
        return this.e.manageForDecrypt(this.e.prepareForDecrypt(cryptoPrimitive.getKey(), headers, providerContext), bArr, contentEncryptionKeyDescriptor, headers, providerContext);
    }

    @Override // org.jose4j.jwe.KeyManagementAlgorithm
    public ContentEncryptionKeys manageForEncrypt(Key key, ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor, Headers headers, byte[] bArr, ProviderContext providerContext) throws JoseException {
        return this.e.manageForEncrypt(deriveForEncrypt(key, headers, providerContext), contentEncryptionKeyDescriptor, headers, bArr, providerContext);
    }

    @Override // org.jose4j.jwe.KeyManagementAlgorithm
    public CryptoPrimitive prepareForDecrypt(Key key, Headers headers, ProviderContext providerContext) throws JoseException {
        return new CryptoPrimitive(a(key, headers.getLongHeaderValue(HeaderParameterNames.PBES2_ITERATION_COUNT), new Base64Url().base64UrlDecode(headers.getStringHeaderValue(HeaderParameterNames.PBES2_SALT_INPUT)), providerContext));
    }

    public void setDefaultIterationCount(long j2) {
        this.h = j2;
    }

    public void setDefaultSaltByteLength(int i) {
        this.i = i;
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
        KeyValidationSupport.notNull(key);
    }
}
