package org.eclipse.paho.client.mqttv3;

import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
/* loaded from: classes13.dex */
public class BufferedMessage {

    /* renamed from: a  reason: collision with root package name */
    public MqttWireMessage f15434a;
    public MqttToken b;

    public BufferedMessage(MqttWireMessage mqttWireMessage, MqttToken mqttToken) {
        this.f15434a = mqttWireMessage;
        this.b = mqttToken;
    }

    public MqttWireMessage getMessage() {
        return this.f15434a;
    }

    public MqttToken getToken() {
        return this.b;
    }
}
