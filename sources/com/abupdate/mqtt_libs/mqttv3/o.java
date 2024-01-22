package com.abupdate.mqtt_libs.mqttv3;

import java.io.UnsupportedEncodingException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes.dex */
public class o {

    /* renamed from: a  reason: collision with root package name */
    public String f1971a;

    public static void b(String str) {
        char charAt = "+".charAt(0);
        char charAt2 = MqttTopic.TOPIC_LEVEL_SEPARATOR.charAt(0);
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        int i = 0;
        while (i < length) {
            int i2 = i - 1;
            char c = i2 >= 0 ? charArray[i2] : (char) 0;
            int i3 = i + 1;
            char c2 = i3 < length ? charArray[i3] : (char) 0;
            if (charArray[i] == charAt && ((c != charAt2 && c != 0) || (c2 != charAt2 && c2 != 0))) {
                throw new IllegalArgumentException(String.format("Invalid usage of single-level wildcard in topic string '%s'!", str));
            }
            i = i3;
        }
    }

    public String a() {
        return this.f1971a;
    }

    public String toString() {
        return a();
    }

    public static void a(String str, boolean z) throws IllegalArgumentException {
        try {
            int length = str.getBytes("UTF-8").length;
            if (length < 1 || length > 65535) {
                throw new IllegalArgumentException(String.format("Invalid topic length, should be in range[%d, %d]!", new Integer(1), new Integer(65535)));
            }
            if (z) {
                if (com.abupdate.mqtt_libs.mqttv3.c.b.a(str, new String[]{MqttTopic.MULTI_LEVEL_WILDCARD, "+"})) {
                    return;
                }
                if (com.abupdate.mqtt_libs.mqttv3.c.b.b(str, MqttTopic.MULTI_LEVEL_WILDCARD) <= 1 && (!str.contains(MqttTopic.MULTI_LEVEL_WILDCARD) || str.endsWith(MqttTopic.MULTI_LEVEL_WILDCARD_PATTERN))) {
                    b(str);
                    return;
                }
                throw new IllegalArgumentException("Invalid usage of multi-level wildcard in topic string: " + str);
            } else if (com.abupdate.mqtt_libs.mqttv3.c.b.a(str, MqttTopic.TOPIC_WILDCARDS)) {
                throw new IllegalArgumentException("The topic name MUST NOT contain any wildcard characters (#+)");
            }
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public static boolean a(String str, String str2) throws IllegalArgumentException {
        int length = str2.length();
        int length2 = str.length();
        a(str, true);
        a(str2, false);
        if (str.equals(str2)) {
            return true;
        }
        int i = 0;
        int i2 = 0;
        while (i < length2 && i2 < length && ((str2.charAt(i2) != '/' || str.charAt(i) == '/') && (str.charAt(i) == '+' || str.charAt(i) == '#' || str.charAt(i) == str2.charAt(i2)))) {
            if (str.charAt(i) == '+') {
                while (true) {
                    int i3 = i2 + 1;
                    if (i3 < length && str2.charAt(i3) != '/') {
                        i2++;
                    }
                }
            } else if (str.charAt(i) == '#') {
                i2 = length - 1;
            }
            i++;
            i2++;
        }
        return i2 == length && i == length2;
    }
}
