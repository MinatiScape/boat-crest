package com.abupdate.mqtt_libs.mqttv3.a;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
/* loaded from: classes.dex */
public class o extends l {
    public ResourceBundle b = ResourceBundle.getBundle("com.abupdate.mqtt_libs.mqttv3.internal.nls.messages");

    @Override // com.abupdate.mqtt_libs.mqttv3.a.l
    public String b(int i) {
        try {
            return this.b.getString(Integer.toString(i));
        } catch (MissingResourceException unused) {
            return "MqttException";
        }
    }
}
