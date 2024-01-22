package org.eclipse.paho.client.mqttv3.internal.wire;
/* loaded from: classes13.dex */
public abstract class MqttAck extends MqttWireMessage {
    public MqttAck(byte b) {
        super(b);
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public byte getMessageInfo() {
        return (byte) 0;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public String toString() {
        return String.valueOf(super.toString()) + " msgId " + this.msgId;
    }
}
