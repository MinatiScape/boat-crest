package com.abupdate.mqtt_libs.mqttv3.a.c;

import com.abupdate.mqtt_libs.mqttv3.MqttException;
import com.abupdate.mqtt_libs.mqttv3.MqttMessage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
/* loaded from: classes.dex */
public class r extends u {
    public String[] e;
    public int[] f;
    public int g;

    public r(byte b, byte[] bArr) throws IOException {
        super((byte) 8);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.f1951a = dataInputStream.readUnsignedShort();
        boolean z = false;
        this.g = 0;
        this.e = new String[10];
        this.f = new int[10];
        while (!z) {
            try {
                this.e[this.g] = b(dataInputStream);
                int[] iArr = this.f;
                int i = this.g;
                this.g = i + 1;
                iArr[i] = dataInputStream.readByte();
            } catch (Exception unused) {
                z = true;
            }
        }
        dataInputStream.close();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public byte c_() {
        return (byte) ((this.b ? 8 : 0) | 2);
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public byte[] d_() throws MqttException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            int i = 0;
            while (true) {
                String[] strArr = this.e;
                if (i < strArr.length) {
                    a(dataOutputStream, strArr[i]);
                    dataOutputStream.writeByte(this.f[i]);
                    i++;
                } else {
                    dataOutputStream.flush();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e) {
            throw new MqttException(e);
        }
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public byte[] e_() throws MqttException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeShort(this.f1951a);
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new MqttException(e);
        }
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(super.toString());
        stringBuffer.append(" names:[");
        for (int i = 0; i < this.g; i++) {
            if (i > 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append("\"");
            stringBuffer.append(this.e[i]);
            stringBuffer.append("\"");
        }
        stringBuffer.append("] qos:[");
        for (int i2 = 0; i2 < this.g; i2++) {
            if (i2 > 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(this.f[i2]);
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public r(String[] strArr, int[] iArr) {
        super((byte) 8);
        this.e = strArr;
        this.f = iArr;
        if (strArr.length == iArr.length) {
            this.g = strArr.length;
            for (int i : iArr) {
                MqttMessage.validateQos(i);
            }
            return;
        }
        throw new IllegalArgumentException();
    }
}
