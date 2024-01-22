package com.ido.ble.bluetooth.f;

import android.text.TextUtils;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.logs.LogTool;
import com.jstyle.blesdk1860.constant.BleConst;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import java.util.Locale;
/* loaded from: classes11.dex */
public class d {
    private static byte a(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static String a(String str) {
        String upperCase = str.substring(str.length() - 1).toUpperCase(Locale.getDefault());
        String upperCase2 = str.substring(str.length() - 2, str.length() - 1).toUpperCase(Locale.getDefault());
        String substring = str.substring(0, str.length() - 2);
        boolean equals = WeatherCriteria.UNIT_FARENHEIT.equals(upperCase);
        String str2 = BleConst.GetDeviceTime;
        if (equals) {
            upperCase2 = WeatherCriteria.UNIT_FARENHEIT.equals(upperCase2) ? BleConst.GetDeviceTime : Integer.toHexString(Integer.parseInt(upperCase2, 16) + 1).toUpperCase(Locale.getDefault());
        } else {
            str2 = Integer.toHexString(Integer.parseInt(upperCase, 16) + 1).toUpperCase(Locale.getDefault());
        }
        String str3 = b.f12116a;
        LogTool.d(str3, "macAddressAdd1:" + substring + upperCase2 + str2);
        return substring + upperCase2 + str2;
    }

    public static boolean a(String str, String str2, BLEDevice bLEDevice) {
        return (TextUtils.isEmpty(str) || TextUtils.isEmpty(bLEDevice.mDeviceAddress) || (!bLEDevice.mDeviceAddress.equalsIgnoreCase(str) && !bLEDevice.mDeviceAddress.equalsIgnoreCase(str2))) ? false : true;
    }

    public static String b(String str) {
        if (str.isEmpty()) {
            return "00:00:00:00:00:00";
        }
        String[] split = str.split(":");
        if (split.length != 6) {
            return "00:00:00:00:00:00";
        }
        byte[] bArr = new byte[split.length];
        for (int i = 0; i < split.length; i++) {
            char[] charArray = split[i].toCharArray();
            if (charArray.length == 1) {
                bArr[5 - i] = a(charArray[0]);
            } else {
                bArr[5 - i] = (byte) (a(charArray[1]) | (a(charArray[0]) << 4));
            }
            System.out.println(String.format("%02x", Byte.valueOf(bArr[5 - i])));
        }
        long j = ((bArr[5] << 16) & 16711680) + ((bArr[4] << 8) & 65280) + (bArr[3] & 255);
        long j2 = (16711680 & (bArr[2] << 16)) + (65280 & (bArr[1] << 8)) + (bArr[0] & 255);
        System.out.println(j2);
        long j3 = j2 + 1;
        System.out.println(j3);
        if (j3 > 16777215) {
            j++;
        }
        bArr[5] = (byte) ((j >> 16) & 255);
        bArr[4] = (byte) ((j >> 8) & 255);
        bArr[3] = (byte) (j & 255);
        System.out.println(j3);
        bArr[2] = (byte) ((j3 >> 16) & 255);
        bArr[1] = (byte) ((j3 >> 8) & 255);
        bArr[0] = (byte) (j3 & 255);
        return String.format("%02X:%02X:%02X:%02X:%02X:%02X", Byte.valueOf(bArr[5]), Byte.valueOf(bArr[4]), Byte.valueOf(bArr[3]), Byte.valueOf(bArr[2]), Byte.valueOf(bArr[1]), Byte.valueOf(bArr[0]));
    }
}
