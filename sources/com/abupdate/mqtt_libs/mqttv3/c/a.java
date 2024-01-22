package com.abupdate.mqtt_libs.mqttv3.c;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Enumeration;
import java.util.Properties;
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1965a = System.getProperty("line.separator", "\n");

    public static String a(Properties properties, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        Enumeration<?> propertyNames = properties.propertyNames();
        StringBuilder sb = new StringBuilder();
        String str2 = f1965a;
        sb.append(str2);
        sb.append("==============");
        sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
        sb.append(str);
        sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
        sb.append("==============");
        sb.append(str2);
        stringBuffer.append(sb.toString());
        while (propertyNames.hasMoreElements()) {
            String str3 = (String) propertyNames.nextElement();
            stringBuffer.append(a(str3, 28, ' ') + ":  " + properties.get(str3) + f1965a);
        }
        stringBuffer.append("==========================================" + f1965a);
        return stringBuffer.toString();
    }

    public static String a(String str, int i, char c) {
        if (str.length() >= i) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(i);
        stringBuffer.append(str);
        int length = i - str.length();
        while (true) {
            length--;
            if (length >= 0) {
                stringBuffer.append(c);
            } else {
                return stringBuffer.toString();
            }
        }
    }
}
