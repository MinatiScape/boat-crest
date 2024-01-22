package org.jose4j.jwe;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import org.jose4j.base64url.Base64Url;
import org.jose4j.jca.ProviderContext;
import org.jose4j.jwa.AlgorithmInfo;
import org.jose4j.jwx.Headers;
import org.jose4j.keys.AesKey;
import org.jose4j.keys.HmacKey;
import org.jose4j.keys.KeyPersuasion;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.IntegrityException;
import org.jose4j.lang.JoseException;
import org.jose4j.mac.MacUtil;
/* loaded from: classes13.dex */
public class AesCbcHmacSha2ContentEncryptionAlgorithm extends AlgorithmInfo implements ContentEncryptionAlgorithm {
    public static final int IV_BYTE_LENGTH = 16;
    public final String e;
    public final int f;
    public final ContentEncryptionKeyDescriptor g;

    /* loaded from: classes13.dex */
    public static class Aes128CbcHmacSha256 extends AesCbcHmacSha2ContentEncryptionAlgorithm {
        public Aes128CbcHmacSha256() {
            super(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256, 32, MacUtil.HMAC_SHA256, 16);
        }
    }

    /* loaded from: classes13.dex */
    public static class Aes192CbcHmacSha384 extends AesCbcHmacSha2ContentEncryptionAlgorithm {
        public Aes192CbcHmacSha384() {
            super(ContentEncryptionAlgorithmIdentifiers.AES_192_CBC_HMAC_SHA_384, 48, MacUtil.HMAC_SHA384, 24);
        }
    }

    /* loaded from: classes13.dex */
    public static class Aes256CbcHmacSha512 extends AesCbcHmacSha2ContentEncryptionAlgorithm {
        public Aes256CbcHmacSha512() {
            super(ContentEncryptionAlgorithmIdentifiers.AES_256_CBC_HMAC_SHA_512, 64, MacUtil.HMAC_SHA512, 32);
        }
    }

    public AesCbcHmacSha2ContentEncryptionAlgorithm(String str, int i, String str2, int i2) {
        setAlgorithmIdentifier(str);
        this.g = new ContentEncryptionKeyDescriptor(i, AesKey.ALGORITHM);
        this.e = str2;
        this.f = i2;
        setJavaAlgorithm("AES/CBC/PKCS5Padding");
        setKeyPersuasion(KeyPersuasion.SYMMETRIC);
        setKeyType(AesKey.ALGORITHM);
    }

    public ContentEncryptionParts a(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, Headers headers, ProviderContext providerContext) throws JoseException {
        HmacKey hmacKey = new HmacKey(ByteUtil.leftHalf(bArr3));
        AesKey aesKey = new AesKey(ByteUtil.rightHalf(bArr3));
        Cipher a2 = CipherUtil.a(getJavaAlgorithm(), a.b(headers, providerContext));
        try {
            a2.init(1, aesKey, new IvParameterSpec(bArr4));
            try {
                byte[] doFinal = a2.doFinal(bArr);
                return new ContentEncryptionParts(bArr4, doFinal, ByteUtil.subArray(MacUtil.getInitializedMac(getHmacJavaAlgorithm(), hmacKey, a.c(headers, providerContext)).doFinal(ByteUtil.concat(bArr2, bArr4, doFinal, b(bArr2))), 0, getTagTruncationLength()));
            } catch (BadPaddingException | IllegalBlockSizeException e) {
                throw new JoseException(e.toString(), e);
            }
        } catch (InvalidAlgorithmParameterException e2) {
            throw new JoseException(e2.toString(), e2);
        } catch (InvalidKeyException e3) {
            throw new JoseException("Invalid key for " + getJavaAlgorithm(), e3);
        }
    }

    public final byte[] b(byte[] bArr) {
        return ByteUtil.getBytes(ByteUtil.bitLength(bArr));
    }

    @Override // org.jose4j.jwe.ContentEncryptionAlgorithm
    public byte[] decrypt(ContentEncryptionParts contentEncryptionParts, byte[] bArr, byte[] bArr2, Headers headers, ProviderContext providerContext) throws JoseException {
        String b = a.b(headers, providerContext);
        String c = a.c(headers, providerContext);
        byte[] iv = contentEncryptionParts.getIv();
        byte[] ciphertext = contentEncryptionParts.getCiphertext();
        byte[] authenticationTag = contentEncryptionParts.getAuthenticationTag();
        if (ByteUtil.secureEquals(authenticationTag, ByteUtil.subArray(MacUtil.getInitializedMac(getHmacJavaAlgorithm(), new HmacKey(ByteUtil.leftHalf(bArr2)), c).doFinal(ByteUtil.concat(bArr, iv, ciphertext, b(bArr))), 0, getTagTruncationLength()))) {
            AesKey aesKey = new AesKey(ByteUtil.rightHalf(bArr2));
            Cipher a2 = CipherUtil.a(getJavaAlgorithm(), b);
            try {
                a2.init(2, aesKey, new IvParameterSpec(iv));
                try {
                    return a2.doFinal(ciphertext);
                } catch (BadPaddingException | IllegalBlockSizeException e) {
                    throw new JoseException(e.toString(), e);
                }
            } catch (InvalidAlgorithmParameterException e2) {
                throw new JoseException(e2.toString(), e2);
            } catch (InvalidKeyException e3) {
                throw new JoseException("Invalid key for " + getJavaAlgorithm(), e3);
            }
        }
        String base64UrlEncode = new Base64Url().base64UrlEncode(authenticationTag);
        throw new IntegrityException("Authentication tag check failed. Message=" + base64UrlEncode);
    }

    @Override // org.jose4j.jwe.ContentEncryptionAlgorithm
    public ContentEncryptionParts encrypt(byte[] bArr, byte[] bArr2, byte[] bArr3, Headers headers, byte[] bArr4, ProviderContext providerContext) throws JoseException {
        return a(bArr, bArr2, bArr3, InitializationVectorHelp.a(16, bArr4, providerContext.getSecureRandom()), headers, providerContext);
    }

    @Override // org.jose4j.jwe.ContentEncryptionAlgorithm
    public ContentEncryptionKeyDescriptor getContentEncryptionKeyDescriptor() {
        return this.g;
    }

    public String getHmacJavaAlgorithm() {
        return this.e;
    }

    public int getTagTruncationLength() {
        return this.f;
    }

    @Override // org.jose4j.jwa.Algorithm
    public boolean isAvailable() {
        return CipherStrengthSupport.isAvailable(getJavaAlgorithm(), getContentEncryptionKeyDescriptor().getContentEncryptionKeyByteLength() / 2);
    }
}
