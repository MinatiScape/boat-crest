package org.jose4j.jwe;

import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import org.jose4j.lang.ByteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes13.dex */
public class CipherStrengthSupport {

    /* renamed from: a  reason: collision with root package name */
    public static final Logger f15514a = LoggerFactory.getLogger(CipherStrengthSupport.class);

    public static boolean isAvailable(String str, int i) {
        int bitLength = ByteUtil.bitLength(i);
        try {
            int maxAllowedKeyLength = Cipher.getMaxAllowedKeyLength(str);
            boolean z = bitLength <= maxAllowedKeyLength;
            if (!z) {
                f15514a.debug("max allowed key length for {} is {}", str, Integer.valueOf(maxAllowedKeyLength));
            }
            return z;
        } catch (NoSuchAlgorithmException e) {
            f15514a.debug("Unknown/unsupported algorithm, {} {}", str, e);
            return false;
        }
    }
}
