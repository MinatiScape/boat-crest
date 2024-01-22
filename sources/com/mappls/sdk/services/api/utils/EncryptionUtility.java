package com.mappls.sdk.services.api.utils;

import android.util.Base64;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
/* loaded from: classes8.dex */
public class EncryptionUtility {
    static {
        System.loadLibrary("rest-apis-native-lib");
    }

    public static String decryptData(String str) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.decode(getKey(), 2), new String(Base64.decode(getKeyAlgorithm(), 2)));
        try {
            Cipher cipher = Cipher.getInstance(new String(Base64.decode(getAlgorithm(), 2)));
            cipher.init(2, secretKeySpec, new IvParameterSpec(Base64.decode(getIV(), 2)));
            return new String(cipher.doFinal(Base64.decode(str, 2)));
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException unused) {
            return "";
        }
    }

    public static String encryptData(String str) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.decode(getKey(), 2), new String(Base64.decode(getKeyAlgorithm(), 2)));
        try {
            Cipher cipher = Cipher.getInstance(new String(Base64.decode(getAlgorithm(), 2)));
            cipher.init(1, secretKeySpec, new IvParameterSpec(Base64.decode(getIV(), 2)));
            return Base64.encodeToString(cipher.doFinal(str.getBytes(Charset.forName("UTF-8"))), 2);
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException unused) {
            return "";
        }
    }

    public static native String getAlgorithm();

    public static native String getIV();

    public static native String getKey();

    public static native String getKeyAlgorithm();
}
