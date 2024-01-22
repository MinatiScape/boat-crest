package org.jose4j.lang;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
/* loaded from: classes13.dex */
public class HashUtil {
    public static final String SHA_256 = "SHA-256";

    public static MessageDigest getMessageDigest(String str) {
        return getMessageDigest(str, null);
    }

    public static MessageDigest getMessageDigest(String str, String str2) {
        try {
            return str2 == null ? MessageDigest.getInstance(str) : MessageDigest.getInstance(str, str2);
        } catch (NoSuchAlgorithmException unused) {
            throw new UncheckedJoseException("Unable to get MessageDigest instance with " + str);
        } catch (NoSuchProviderException e) {
            throw new UncheckedJoseException("Unable to get a MessageDigest implementation of algorithm name: " + str + " using provider " + str2, e);
        }
    }
}
