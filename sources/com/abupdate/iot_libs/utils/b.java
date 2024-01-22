package com.abupdate.iot_libs.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.abupdate.iot_libs.utils.h;
import java.net.NetworkInterface;
import java.util.Collections;
/* loaded from: classes.dex */
public class b {
    public static String a(Context context) {
        String c = c(context);
        if ("02:00:00:00:00:00".equals(c)) {
            String a2 = a();
            if ("02:00:00:00:00:00".equals(a2)) {
                String b = b();
                return !"02:00:00:00:00:00".equals(b) ? b : "";
            }
            return a2;
        }
        return c;
    }

    public static String b() {
        String str;
        String str2;
        h.a a2 = h.a("getprop wifi.interface", false);
        if (a2.f1918a != 0 || (str = a2.b) == null) {
            return "02:00:00:00:00:00";
        }
        h.a a3 = h.a("cat /sys/class/net/" + str + "/address", false);
        return (a3.f1918a != 0 || (str2 = a3.b) == null) ? "02:00:00:00:00:00" : str2;
    }

    @SuppressLint({"HardwareIds"})
    public static String c(Context context) {
        WifiInfo connectionInfo;
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            return (wifiManager == null || (connectionInfo = wifiManager.getConnectionInfo()) == null) ? "02:00:00:00:00:00" : connectionInfo.getMacAddress();
        } catch (Exception e) {
            e.printStackTrace();
            return "02:00:00:00:00:00";
        }
    }

    public static String a() {
        byte[] hardwareAddress;
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if ("wlan0".equalsIgnoreCase(networkInterface.getName()) && (hardwareAddress = networkInterface.getHardwareAddress()) != null && hardwareAddress.length > 0) {
                    StringBuilder sb = new StringBuilder();
                    int length = hardwareAddress.length;
                    for (int i = 0; i < length; i++) {
                        sb.append(String.format("%02x:", Byte.valueOf(hardwareAddress[i])));
                    }
                    return sb.deleteCharAt(sb.length() - 1).toString();
                }
            }
            return "02:00:00:00:00:00";
        } catch (Exception e) {
            e.printStackTrace();
            return "02:00:00:00:00:00";
        }
    }
}
