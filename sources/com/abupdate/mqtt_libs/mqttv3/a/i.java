package com.abupdate.mqtt_libs.mqttv3.a;

import com.abupdate.mqtt_libs.mqttv3.MqttException;
/* loaded from: classes.dex */
public class i {
    public static MqttException a(int i) {
        if (i != 4 && i != 5) {
            return new MqttException(i);
        }
        return new com.abupdate.mqtt_libs.mqttv3.m(i);
    }

    public static MqttException a(Throwable th) {
        if ("java.security.GeneralSecurityException".equals(th.getClass().getName())) {
            return new com.abupdate.mqtt_libs.mqttv3.m(th);
        }
        return new MqttException(th);
    }

    public static boolean a(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}
