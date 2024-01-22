package com.abupdate.mqtt_libs.mqttv3.a;

import com.abupdate.mqtt_libs.mqttv3.MqttException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes.dex */
public interface n {
    void a() throws IOException, MqttException;

    InputStream b() throws IOException;

    OutputStream c() throws IOException;

    void d() throws IOException;

    String e();
}
