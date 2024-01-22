package com.coveiot.android.leonardo.utils;

import android.text.TextUtils;
import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;
import java.util.Locale;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.jose4j.keys.AesKey;
/* loaded from: classes5.dex */
public class DFUUtils {
    public static String decryptMsg(String str, String str2) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidParameterSpecException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        byte[] bytes = str2.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, 0, bytes.length, AesKey.ALGORITHM);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
        cipher.init(2, secretKeySpec);
        return new String(cipher.doFinal(Base64.decode(str, 2)), "UTF-8");
    }

    public static String generateDfuTargetMacAddress(String str) {
        long longValue = (Long.valueOf(str.replaceAll(":", ""), 16).longValue() + 1) & 281474976710655L;
        Locale locale = Locale.ENGLISH;
        return TextUtils.join(":", new String[]{String.format(locale, "%02x", Long.valueOf((280375465082880L & longValue) >> 40)), String.format(locale, "%02x", Long.valueOf((1095216660480L & longValue) >> 32)), String.format(locale, "%02x", Long.valueOf((4278190080L & longValue) >> 24)), String.format(locale, "%02x", Long.valueOf((16711680 & longValue) >> 16)), String.format(locale, "%02x", Long.valueOf((65280 & longValue) >> 8)), String.format(locale, "%02x", Long.valueOf(longValue & 255))}).toUpperCase();
    }
}
