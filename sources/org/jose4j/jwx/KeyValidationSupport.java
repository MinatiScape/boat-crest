package org.jose4j.jwx;

import java.security.Key;
import java.security.interfaces.RSAKey;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.InvalidKeyException;
/* loaded from: classes13.dex */
public class KeyValidationSupport {
    public static final int MIN_RSA_KEY_LENGTH = 2048;

    public static <K extends Key> K castKey(Key key, Class<K> cls) throws InvalidKeyException {
        notNull(key);
        try {
            return cls.cast(key);
        } catch (ClassCastException e) {
            throw new InvalidKeyException("Invalid key " + e);
        }
    }

    public static void cekNotAllowed(byte[] bArr, String str) throws InvalidKeyException {
        if (bArr == null) {
            return;
        }
        throw new InvalidKeyException("An explicit content encryption key cannot be used with " + str);
    }

    public static void checkRsaKeySize(Key key) throws InvalidKeyException {
        int bitLength;
        if (key != null) {
            if (!(key instanceof RSAKey) || (bitLength = ((RSAKey) key).getModulus().bitLength()) >= 2048) {
                return;
            }
            throw new InvalidKeyException("An RSA key of size 2048 bits or larger MUST be used with the all JOSE RSA algorithms (given key was only " + bitLength + " bits).");
        }
        throw new InvalidKeyException("The RSA key must not be null.");
    }

    public static void notNull(Key key) throws InvalidKeyException {
        if (key == null) {
            throw new InvalidKeyException("The key must not be null.");
        }
    }

    public static void validateAesWrappingKey(Key key, String str, int i) throws InvalidKeyException {
        int length;
        notNull(key);
        String algorithm = key.getAlgorithm();
        if (AesKey.ALGORITHM.equals(algorithm)) {
            if (key.getEncoded() == null || (length = key.getEncoded().length) == i) {
                return;
            }
            throw new InvalidKeyException("Invalid key for JWE " + str + ", expected a " + ByteUtil.bitLength(i) + " bit key but a " + ByteUtil.bitLength(length) + " bit key was provided.");
        }
        throw new InvalidKeyException("Invalid key for JWE " + str + ", expected an " + AesKey.ALGORITHM + " key but an " + algorithm + " key was provided.");
    }
}
