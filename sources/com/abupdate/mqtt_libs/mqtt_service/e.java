package com.abupdate.mqtt_libs.mqtt_service;

import com.abupdate.mqtt_libs.mqttv3.IMqttActionListener;
import com.abupdate.mqtt_libs.mqttv3.IMqttDeliveryToken;
import com.abupdate.mqtt_libs.mqttv3.MqttException;
import com.abupdate.mqtt_libs.mqttv3.MqttMessage;
/* loaded from: classes.dex */
public class e extends g implements IMqttDeliveryToken {
    public MqttMessage j;

    public e(MqttAndroidClient mqttAndroidClient, Object obj, IMqttActionListener iMqttActionListener, MqttMessage mqttMessage) {
        super(mqttAndroidClient, obj, iMqttActionListener);
        this.j = mqttMessage;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttDeliveryToken
    public MqttMessage getMessage() throws MqttException {
        return this.j;
    }
}
