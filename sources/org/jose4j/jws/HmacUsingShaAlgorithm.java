package org.jose4j.jws;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import org.jose4j.jca.ProviderContext;
import org.jose4j.jwa.AlgorithmInfo;
import org.jose4j.jwa.CryptoPrimitive;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.keys.KeyPersuasion;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.InvalidKeyException;
import org.jose4j.lang.JoseException;
import org.jose4j.mac.MacUtil;
/* loaded from: classes13.dex */
public class HmacUsingShaAlgorithm extends AlgorithmInfo implements JsonWebSignatureAlgorithm {
    public int e;

    /* loaded from: classes13.dex */
    public static class HmacSha256 extends HmacUsingShaAlgorithm {
        public HmacSha256() {
            super(AlgorithmIdentifiers.HMAC_SHA256, MacUtil.HMAC_SHA256, 256);
        }
    }

    /* loaded from: classes13.dex */
    public static class HmacSha384 extends HmacUsingShaAlgorithm {
        public HmacSha384() {
            super(AlgorithmIdentifiers.HMAC_SHA384, MacUtil.HMAC_SHA384, 384);
        }
    }

    /* loaded from: classes13.dex */
    public static class HmacSha512 extends HmacUsingShaAlgorithm {
        public HmacSha512() {
            super(AlgorithmIdentifiers.HMAC_SHA512, MacUtil.HMAC_SHA512, 512);
        }
    }

    public HmacUsingShaAlgorithm(String str, String str2, int i) {
        setAlgorithmIdentifier(str);
        setJavaAlgorithm(str2);
        setKeyPersuasion(KeyPersuasion.SYMMETRIC);
        setKeyType(OctetSequenceJsonWebKey.KEY_TYPE);
        this.e = i;
    }

    public final Mac a(Key key, ProviderContext providerContext) throws JoseException {
        return MacUtil.getInitializedMac(getJavaAlgorithm(), key, providerContext.getSuppliedKeyProviderContext().getMacProvider());
    }

    @Override // org.jose4j.jwa.Algorithm
    public boolean isAvailable() {
        try {
            Mac.getInstance(getJavaAlgorithm());
            return true;
        } catch (NoSuchAlgorithmException unused) {
            return false;
        }
    }

    @Override // org.jose4j.jws.JsonWebSignatureAlgorithm
    public CryptoPrimitive prepareForSign(Key key, ProviderContext providerContext) throws JoseException {
        return new CryptoPrimitive(a(key, providerContext));
    }

    @Override // org.jose4j.jws.JsonWebSignatureAlgorithm
    public byte[] sign(CryptoPrimitive cryptoPrimitive, byte[] bArr) throws JoseException {
        return cryptoPrimitive.getMac().doFinal(bArr);
    }

    public void validateKey(Key key) throws InvalidKeyException {
        int bitLength;
        if (key != null) {
            if (key.getEncoded() == null || (bitLength = ByteUtil.bitLength(key.getEncoded())) >= this.e) {
                return;
            }
            throw new InvalidKeyException("A key of the same size as the hash output (i.e. " + this.e + " bits for " + getAlgorithmIdentifier() + ") or larger MUST be used with the HMAC SHA algorithms but this key is only " + bitLength + " bits");
        }
        throw new InvalidKeyException("key is null");
    }

    @Override // org.jose4j.jws.JsonWebSignatureAlgorithm
    public void validateSigningKey(Key key) throws InvalidKeyException {
        validateKey(key);
    }

    @Override // org.jose4j.jws.JsonWebSignatureAlgorithm
    public void validateVerificationKey(Key key) throws InvalidKeyException {
        validateKey(key);
    }

    @Override // org.jose4j.jws.JsonWebSignatureAlgorithm
    public boolean verifySignature(byte[] bArr, Key key, byte[] bArr2, ProviderContext providerContext) throws JoseException {
        if (key instanceof SecretKey) {
            return ByteUtil.secureEquals(bArr, a(key, providerContext).doFinal(bArr2));
        }
        throw new InvalidKeyException(key.getClass() + " cannot be used for HMAC verification.");
    }
}
