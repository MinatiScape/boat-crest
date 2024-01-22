package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
/* loaded from: classes13.dex */
public class MqttConnect extends MqttWireMessage {
    public static final String KEY = "Con";
    public String d;
    public boolean e;
    public MqttMessage f;
    public String g;
    public char[] h;
    public int i;
    public String j;
    public int k;

    public MqttConnect(byte b, byte[] bArr) throws IOException, MqttException {
        super((byte) 1);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        MqttWireMessage.decodeUTF8(dataInputStream);
        dataInputStream.readByte();
        dataInputStream.readByte();
        this.i = dataInputStream.readUnsignedShort();
        this.d = MqttWireMessage.decodeUTF8(dataInputStream);
        dataInputStream.close();
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public String getKey() {
        return "Con";
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public byte getMessageInfo() {
        return (byte) 0;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public byte[] getPayload() throws MqttException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            MqttWireMessage.encodeUTF8(dataOutputStream, this.d);
            if (this.f != null) {
                MqttWireMessage.encodeUTF8(dataOutputStream, this.j);
                dataOutputStream.writeShort(this.f.getPayload().length);
                dataOutputStream.write(this.f.getPayload());
            }
            String str = this.g;
            if (str != null) {
                MqttWireMessage.encodeUTF8(dataOutputStream, str);
                char[] cArr = this.h;
                if (cArr != null) {
                    MqttWireMessage.encodeUTF8(dataOutputStream, new String(cArr));
                }
            }
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new MqttException(e);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public byte[] getVariableHeader() throws MqttException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            int i = this.k;
            if (i == 3) {
                MqttWireMessage.encodeUTF8(dataOutputStream, "MQIsdp");
            } else if (i == 4) {
                MqttWireMessage.encodeUTF8(dataOutputStream, "MQTT");
            }
            dataOutputStream.write(this.k);
            byte b = this.e ? (byte) 2 : (byte) 0;
            MqttMessage mqttMessage = this.f;
            if (mqttMessage != null) {
                b = (byte) (((byte) (b | 4)) | (mqttMessage.getQos() << 3));
                if (this.f.isRetained()) {
                    b = (byte) (b | 32);
                }
            }
            if (this.g != null) {
                b = (byte) (b | 128);
                if (this.h != null) {
                    b = (byte) (b | 64);
                }
            }
            dataOutputStream.write(b);
            dataOutputStream.writeShort(this.i);
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new MqttException(e);
        }
    }

    public boolean isCleanSession() {
        return this.e;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public boolean isMessageIdRequired() {
        return false;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public String toString() {
        return String.valueOf(super.toString()) + " clientId " + this.d + " keepAliveInterval " + this.i;
    }

    public MqttConnect(String str, int i, boolean z, int i2, String str2, char[] cArr, MqttMessage mqttMessage, String str3) {
        super((byte) 1);
        this.d = str;
        this.e = z;
        this.i = i2;
        this.g = str2;
        if (cArr != null) {
            this.h = (char[]) cArr.clone();
        }
        this.f = mqttMessage;
        this.j = str3;
        this.k = i;
    }
}
