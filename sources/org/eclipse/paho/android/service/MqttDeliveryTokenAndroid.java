package org.eclipse.paho.android.service;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;
/* loaded from: classes13.dex */
public class MqttDeliveryTokenAndroid extends MqttTokenAndroid implements IMqttDeliveryToken {
    public MqttMessage j;

    public MqttDeliveryTokenAndroid(MqttAndroidClient mqttAndroidClient, Object obj, IMqttActionListener iMqttActionListener, MqttMessage mqttMessage) {
        super(mqttAndroidClient, obj, iMqttActionListener);
        this.j = mqttMessage;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
    public MqttMessage getMessage() {
        return this.j;
    }

    public void notifyDelivery(MqttMessage mqttMessage) {
        this.j = mqttMessage;
        super.notifyComplete();
    }

    public void setMessage(MqttMessage mqttMessage) {
        this.j = mqttMessage;
    }
}
