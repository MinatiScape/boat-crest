package com.mappls.sdk.navigation.util;

import androidx.core.view.ViewCompat;
import com.jstyle.blesdk1860.constant.BleConst;
import com.mappls.sdk.navigation.h;
import com.mappls.sdk.navigation.o;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes11.dex */
public final class a {
    public static String a(int i) {
        if ((i & ViewCompat.MEASURED_STATE_MASK) == -16777216) {
            StringBuilder a2 = h.a(MqttTopic.MULTI_LEVEL_WILDCARD);
            String hexString = Integer.toHexString(i & 16777215);
            while (hexString.length() < 6) {
                hexString = o.a(BleConst.GetDeviceTime, hexString);
            }
            a2.append(hexString);
            return a2.toString();
        }
        StringBuilder a3 = h.a(MqttTopic.MULTI_LEVEL_WILDCARD);
        String hexString2 = Integer.toHexString(i);
        while (hexString2.length() < 8) {
            hexString2 = o.a(BleConst.GetDeviceTime, hexString2);
        }
        a3.append(hexString2);
        return a3.toString();
    }

    public static String a(int i, boolean z) {
        StringBuilder sb;
        String str;
        int i2 = i % 60;
        if (i2 < 10) {
            sb = new StringBuilder();
            sb.append(BleConst.GetDeviceTime);
            sb.append(i2);
        } else {
            sb = new StringBuilder();
            sb.append(i2);
            sb.append("");
        }
        String sb2 = sb.toString();
        int i3 = i / 60;
        if (!z && i3 < 60) {
            return i3 + ":" + sb2;
        }
        int i4 = i3 % 60;
        if (i4 < 10) {
            str = BleConst.GetDeviceTime + i4;
        } else {
            str = i4 + "";
        }
        return (i3 / 60) + ":" + str + ":" + sb2;
    }

    public static boolean a(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    public static boolean a(String str) {
        return str == null || str.length() == 0;
    }

    public static int b(String str) {
        if (str.charAt(0) == '#') {
            if (str.length() == 4) {
                StringBuilder a2 = h.a(MqttTopic.MULTI_LEVEL_WILDCARD);
                a2.append(str.charAt(1));
                a2.append(str.charAt(1));
                a2.append(str.charAt(2));
                a2.append(str.charAt(2));
                a2.append(str.charAt(3));
                a2.append(str.charAt(3));
                str = a2.toString();
            }
            long parseLong = Long.parseLong(str.substring(1), 16);
            if (str.length() == 7) {
                parseLong |= -16777216;
            } else if (str.length() != 9) {
                throw new IllegalArgumentException(o.a("Unknown color ", str));
            }
            return (int) parseLong;
        }
        throw new IllegalArgumentException(o.a("Unknown color ", str));
    }
}
