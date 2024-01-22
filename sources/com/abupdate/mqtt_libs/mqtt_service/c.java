package com.abupdate.mqtt_libs.mqtt_service;

import com.abupdate.mqtt_libs.mqttv3.MqttMessage;
import java.util.Iterator;
/* loaded from: classes.dex */
public interface c {

    /* loaded from: classes.dex */
    public interface a {
        String a();

        String b();

        MqttMessage c();
    }

    String a(String str, String str2, MqttMessage mqttMessage);

    Iterator<a> a(String str);

    void a();

    boolean a(String str, String str2);

    void b(String str);
}
