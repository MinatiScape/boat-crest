package com.abupdate.mqtt_libs.mqttv3.a.c;

import com.abupdate.mqtt_libs.mqttv3.MqttException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
/* loaded from: classes.dex */
public class t extends u {
    public String[] e;
    public int f;

    public t(String[] strArr) {
        super((byte) 10);
        this.e = strArr;
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
        for (int i = 0; i < this.f; i++) {
            if (i > 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append("\"" + this.e[i] + "\"");
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public t(byte b, byte[] bArr) throws IOException {
        super((byte) 10);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.f1951a = dataInputStream.readUnsignedShort();
        boolean z = false;
        this.f = 0;
        this.e = new String[10];
        while (!z) {
            try {
                this.e[this.f] = b(dataInputStream);
            } catch (Exception unused) {
                z = true;
            }
        }
        dataInputStream.close();
    }
}
