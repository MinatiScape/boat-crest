package org.jose4j.mac;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import javax.crypto.Mac;
import org.jose4j.lang.InvalidKeyException;
import org.jose4j.lang.JoseException;
/* loaded from: classes13.dex */
public class MacUtil {
    public static final String HMAC_SHA256 = "HmacSHA256";
    public static final String HMAC_SHA384 = "HmacSHA384";
    public static final String HMAC_SHA512 = "HmacSHA512";

    public static Mac getInitializedMac(String str, Key key) throws JoseException {
        return getInitializedMac(str, key, null);
    }

    public static Mac getMac(String str) throws JoseException {
        return getMac(str, null);
    }

    public static void initMacWithKey(Mac mac, Key key) throws InvalidKeyException {
        try {
            mac.init(key);
        } catch (java.security.InvalidKeyException e) {
            throw new InvalidKeyException("Key is not valid for " + mac.getAlgorithm() + " - " + e, e);
        }
    }

    public static Mac getInitializedMac(String str, Key key, String str2) throws JoseException {
        Mac mac = getMac(str, str2);
        initMacWithKey(mac, key);
        return mac;
    }

    public static Mac getMac(String str, String str2) throws JoseException {
        try {
            return str2 == null ? Mac.getInstance(str) : Mac.getInstance(str, str2);
        } catch (NoSuchAlgorithmException e) {
            throw new JoseException("Unable to get a MAC implementation of algorithm name: " + str, e);
        } catch (NoSuchProviderException e2) {
            throw new JoseException("Unable to get a MAC implementation of algorithm name: " + str + " using provider " + str2, e2);
        }
    }
}
