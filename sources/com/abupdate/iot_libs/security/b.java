package com.abupdate.iot_libs.security;

import com.abupdate.iot_libs.info.DeviceInfo;
import com.jstyle.blesdk1860.constant.BleConst;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.jose4j.keys.AesKey;
/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public static final byte[] f1915a = {77, 68, 53};

    public static String a() {
        try {
            StringBuilder sb = new StringBuilder();
            DeviceInfo deviceInfo = DeviceInfo.getInstance();
            sb.append(deviceInfo.models);
            sb.append(deviceInfo.deviceType);
            sb.append(deviceInfo.oem);
            sb.append(deviceInfo.platform);
            return a(a(new String(sb.toString().getBytes())));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static byte[] b(byte[] bArr, byte[] bArr2, String str) {
        if (bArr != null && bArr.length != 0 && bArr2 != null && bArr2.length != 0) {
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, str);
                Cipher cipher = Cipher.getInstance(AesKey.ALGORITHM);
                cipher.init(2, secretKeySpec);
                return cipher.doFinal(bArr);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return null;
    }

    public static byte[] c(String str) {
        int length = str.length();
        if (length % 2 != 0) {
            str = BleConst.GetDeviceTime + str;
            length++;
        }
        char[] charArray = str.toUpperCase().toCharArray();
        byte[] bArr = new byte[length >> 1];
        for (int i = 0; i < length; i += 2) {
            bArr[i >> 1] = (byte) ((a(charArray[i]) << 4) | a(charArray[i + 1]));
        }
        return bArr;
    }

    public static String a(String str, String str2) {
        return new String(b(c(str2), str.getBytes(), AesKey.ALGORITHM));
    }

    public static int a(char c) {
        if (c < '0' || c > '9') {
            if (c < 'A' || c > 'F') {
                throw new IllegalArgumentException();
            }
            return (c - 'A') + 10;
        }
        return c - '0';
    }

    public static String a(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(new String(f1915a));
            messageDigest.update(str.getBytes());
            byte[] digest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer("");
            for (int i = 0; i < digest.length; i++) {
                int i2 = digest[i];
                if (i2 < 0) {
                    i2 += 256;
                }
                if (i2 < 16) {
                    stringBuffer.append(BleConst.GetDeviceTime);
                }
                stringBuffer.append(Integer.toHexString(i2));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
