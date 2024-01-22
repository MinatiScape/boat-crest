package com.abupdate.mqtt_libs.connect;

import com.abupdate.mqtt_libs.mqttv3.IMqttActionListener;
import com.abupdate.mqtt_libs.mqttv3.MqttException;
/* loaded from: classes.dex */
public interface Command {
    void execute(IMqttActionListener iMqttActionListener) throws MqttException;
}
