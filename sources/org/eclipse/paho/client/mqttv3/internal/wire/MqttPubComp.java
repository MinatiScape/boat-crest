package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;
/* loaded from: classes13.dex */
public class MqttPubComp extends MqttAck {
    public MqttPubComp(byte b, byte[] bArr) throws IOException {
        super((byte) 7);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.msgId = dataInputStream.readUnsignedShort();
        dataInputStream.close();
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public byte[] getVariableHeader() throws MqttException {
        return encodeMessageId();
    }

    public MqttPubComp(MqttPublish mqttPublish) {
        super((byte) 7);
        this.msgId = mqttPublish.getMessageId();
    }

    public MqttPubComp(int i) {
        super((byte) 7);
        this.msgId = i;
    }
}
