package com.ido.ble.common;

import android.os.Build;
import android.util.Base64;
import com.jstyle.blesdk1860.constant.BleConst;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.jose4j.keys.AesKey;
/* loaded from: classes11.dex */
public class a {
    public static String a() {
        KeyGenerator keyGenerator;
        SecureRandom secureRandom = null;
        try {
            keyGenerator = KeyGenerator.getInstance(AesKey.ALGORITHM);
        } catch (Exception e) {
            e = e;
            keyGenerator = null;
        }
        try {
            secureRandom = Build.VERSION.SDK_INT >= 17 ? SecureRandom.getInstance("SHA1PRNG", "Crypto") : SecureRandom.getInstance("SHA1PRNG");
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            keyGenerator.init(256, secureRandom);
            return a(keyGenerator.generateKey().getEncoded());
        }
        keyGenerator.init(256, secureRandom);
        return a(keyGenerator.generateKey().getEncoded());
    }

    public static String a(String str, String str2) {
        if (str2.length() == 0 || str.length() == 0) {
            return null;
        }
        try {
            SecretKeySpec a2 = a(str2);
            byte[] bArr = new byte[16];
            Arrays.fill(bArr, (byte) 0);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr);
            byte[] decode = Base64.decode(str, 0);
            Cipher cipher = Cipher.getInstance(AesKey.ALGORITHM);
            cipher.init(2, a2, ivParameterSpec);
            return new String(cipher.doFinal(decode), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        } catch (InvalidAlgorithmParameterException e2) {
            e2.printStackTrace();
            return "";
        } catch (InvalidKeyException e3) {
            e3.printStackTrace();
            return "";
        } catch (NoSuchAlgorithmException e4) {
            e4.printStackTrace();
            return "";
        } catch (BadPaddingException e5) {
            e5.printStackTrace();
            return "";
        } catch (IllegalBlockSizeException e6) {
            e6.printStackTrace();
            return "";
        } catch (NoSuchPaddingException e7) {
            e7.printStackTrace();
            return "";
        }
    }

    public static String a(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                str = str + BleConst.GetDeviceTime;
            }
            str = str + hexString;
        }
        return str;
    }

    private static SecretKeySpec a(String str) {
        byte[] bArr = new byte[32];
        Arrays.fill(bArr, (byte) 0);
        byte[] b = b(str);
        System.arraycopy(b, 0, bArr, 0, b.length < 32 ? b.length : 32);
        return new SecretKeySpec(bArr, AesKey.ALGORITHM);
    }

    public static String b(String str, String str2) {
        if (str2.length() == 0 || str.length() == 0) {
            return null;
        }
        try {
            SecretKeySpec a2 = a(str2);
            byte[] bytes = str.getBytes("UTF8");
            byte[] bArr = new byte[16];
            Arrays.fill(bArr, (byte) 0);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr);
            Cipher cipher = Cipher.getInstance(AesKey.ALGORITHM);
            cipher.init(1, a2, ivParameterSpec);
            return Base64.encodeToString(cipher.doFinal(bytes), 0);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        } catch (InvalidAlgorithmParameterException e2) {
            e2.printStackTrace();
            return "";
        } catch (InvalidKeyException e3) {
            e3.printStackTrace();
            return "";
        } catch (NoSuchAlgorithmException e4) {
            e4.printStackTrace();
            return "";
        } catch (BadPaddingException e5) {
            e5.printStackTrace();
            return "";
        } catch (IllegalBlockSizeException e6) {
            e6.printStackTrace();
            return "";
        } catch (NoSuchPaddingException e7) {
            e7.printStackTrace();
            return "";
        }
    }

    private static byte[] b(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = Integer.valueOf(str.substring(i2, i2 + 2), 16).byteValue();
        }
        return bArr;
    }
}
