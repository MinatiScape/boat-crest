package com.mappls.sdk.services.hmac.strategies;

import com.jstyle.blesdk1860.constant.BleConst;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
/* loaded from: classes8.dex */
public class MD5HashingStrategy implements HashingStrategy {
    @Override // com.mappls.sdk.services.hmac.strategies.HashingStrategy
    public String generateHash(String str) {
        try {
            StringBuilder sb = new StringBuilder(new BigInteger(1, MessageDigest.getInstance(MessageDigestAlgorithms.MD5).digest(str.getBytes())).toString(16));
            while (sb.length() < 32) {
                sb.insert(0, BleConst.GetDeviceTime);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }
}
