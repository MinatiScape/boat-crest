package com.mappls.sdk.services.hmac;

import android.util.Base64;
import com.mappls.sdk.services.hmac.Enums;
import com.mappls.sdk.services.hmac.strategies.HashingStrategy;
import com.mappls.sdk.services.hmac.strategies.MD5HashingStrategy;
import com.mappls.sdk.services.hmac.strategies.SHA1HashingStrategy;
import com.mappls.sdk.services.hmac.strategies.SHA256HashingStrategy;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
/* loaded from: classes8.dex */
public abstract class HMACMessageDigestGenerator {
    public String generateHMACMessageDigest(String str, Enums.SupportedHashingAlgorithm supportedHashingAlgorithm, String str2) throws NoSuchAlgorithmException, InvalidKeyException {
        HashingStrategy sHA1HashingStrategy;
        if (supportedHashingAlgorithm == Enums.SupportedHashingAlgorithm.MD5) {
            sHA1HashingStrategy = new MD5HashingStrategy();
        } else if (supportedHashingAlgorithm == Enums.SupportedHashingAlgorithm.SHA256) {
            sHA1HashingStrategy = new SHA256HashingStrategy();
        } else {
            sHA1HashingStrategy = new SHA1HashingStrategy();
        }
        String generateHash = sHA1HashingStrategy.generateHash(str2);
        byte[] bytes = str.getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, "Hmac" + supportedHashingAlgorithm.name());
        Mac mac = Mac.getInstance("Hmac" + supportedHashingAlgorithm.name());
        mac.init(secretKeySpec);
        return new String(Base64.encode(mac.doFinal(generateHash.getBytes()), 2));
    }
}
