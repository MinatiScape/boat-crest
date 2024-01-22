package org.eclipse.paho.client.mqttv3.internal.wire;

import com.jstyle.blesdk1860.constant.BleConst;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
/* loaded from: classes13.dex */
public class MqttPublish extends MqttPersistableWireMessage {
    public MqttMessage d;
    public String e;
    public byte[] f;

    public MqttPublish(String str, MqttMessage mqttMessage) {
        super((byte) 3);
        this.f = null;
        this.e = str;
        this.d = mqttMessage;
    }

    public static byte[] encodePayload(MqttMessage mqttMessage) {
        return mqttMessage.getPayload();
    }

    public MqttMessage getMessage() {
        return this.d;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public byte getMessageInfo() {
        byte qos = (byte) (this.d.getQos() << 1);
        if (this.d.isRetained()) {
            qos = (byte) (qos | 1);
        }
        return (this.d.isDuplicate() || this.duplicate) ? (byte) (qos | 8) : qos;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public byte[] getPayload() throws MqttException {
        if (this.f == null) {
            this.f = encodePayload(this.d);
        }
        return this.f;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttPersistableWireMessage, org.eclipse.paho.client.mqttv3.MqttPersistable
    public int getPayloadLength() {
        try {
            return getPayload().length;
        } catch (MqttException unused) {
            return 0;
        }
    }

    public String getTopicName() {
        return this.e;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public byte[] getVariableHeader() throws MqttException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            MqttWireMessage.encodeUTF8(dataOutputStream, this.e);
            if (this.d.getQos() > 0) {
                dataOutputStream.writeShort(this.msgId);
            }
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new MqttException(e);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public boolean isMessageIdRequired() {
        return true;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public void setMessageId(int i) {
        super.setMessageId(i);
        MqttMessage mqttMessage = this.d;
        if (mqttMessage instanceof MqttReceivedMessage) {
            ((MqttReceivedMessage) mqttMessage).setMessageId(i);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public String toString() {
        String str;
        StringBuffer stringBuffer = new StringBuffer();
        byte[] payload = this.d.getPayload();
        int min = Math.min(payload.length, 20);
        for (int i = 0; i < min; i++) {
            String hexString = Integer.toHexString(payload[i]);
            if (hexString.length() == 1) {
                hexString = BleConst.GetDeviceTime + hexString;
            }
            stringBuffer.append(hexString);
        }
        try {
            str = new String(payload, 0, min, "UTF-8");
        } catch (Exception unused) {
            str = "?";
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append(super.toString());
        stringBuffer2.append(" qos:");
        stringBuffer2.append(this.d.getQos());
        if (this.d.getQos() > 0) {
            stringBuffer2.append(" msgId:");
            stringBuffer2.append(this.msgId);
        }
        stringBuffer2.append(" retained:");
        stringBuffer2.append(this.d.isRetained());
        stringBuffer2.append(" dup:");
        stringBuffer2.append(this.duplicate);
        stringBuffer2.append(" topic:\"");
        stringBuffer2.append(this.e);
        stringBuffer2.append("\"");
        stringBuffer2.append(" payload:[hex:");
        stringBuffer2.append(stringBuffer);
        stringBuffer2.append(" utf8:\"");
        stringBuffer2.append(str);
        stringBuffer2.append("\"");
        stringBuffer2.append(" length:");
        stringBuffer2.append(payload.length);
        stringBuffer2.append("]");
        return stringBuffer2.toString();
    }

    public MqttPublish(byte b, byte[] bArr) throws MqttException, IOException {
        super((byte) 3);
        this.f = null;
        MqttReceivedMessage mqttReceivedMessage = new MqttReceivedMessage();
        this.d = mqttReceivedMessage;
        mqttReceivedMessage.setQos(3 & (b >> 1));
        if ((b & 1) == 1) {
            this.d.setRetained(true);
        }
        if ((b & 8) == 8) {
            ((MqttReceivedMessage) this.d).setDuplicate(true);
        }
        CountingInputStream countingInputStream = new CountingInputStream(new ByteArrayInputStream(bArr));
        DataInputStream dataInputStream = new DataInputStream(countingInputStream);
        this.e = MqttWireMessage.decodeUTF8(dataInputStream);
        if (this.d.getQos() > 0) {
            this.msgId = dataInputStream.readUnsignedShort();
        }
        byte[] bArr2 = new byte[bArr.length - countingInputStream.getCounter()];
        dataInputStream.readFully(bArr2);
        dataInputStream.close();
        this.d.setPayload(bArr2);
    }
}
