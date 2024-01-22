package com.abupdate.mqtt_libs.mqttv3.a.c;

import com.abupdate.mqtt_libs.mqttv3.MqttException;
import com.abupdate.mqtt_libs.mqttv3.MqttMessage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
/* loaded from: classes.dex */
public class d extends u {
    public String e;
    public boolean f;
    public MqttMessage g;
    public String h;
    public char[] i;
    public int j;
    public String k;
    public int l;

    public d(byte b, byte[] bArr) throws IOException, MqttException {
        super((byte) 1);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        b(dataInputStream);
        dataInputStream.readByte();
        dataInputStream.readByte();
        this.j = dataInputStream.readUnsignedShort();
        this.e = b(dataInputStream);
        dataInputStream.close();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public byte c_() {
        return (byte) 0;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public byte[] d_() throws MqttException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            a(dataOutputStream, this.e);
            if (this.g != null) {
                a(dataOutputStream, this.k);
                dataOutputStream.writeShort(this.g.getPayload().length);
                dataOutputStream.write(this.g.getPayload());
            }
            String str = this.h;
            if (str != null) {
                a(dataOutputStream, str);
                char[] cArr = this.i;
                if (cArr != null) {
                    a(dataOutputStream, new String(cArr));
                }
            }
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new MqttException(e);
        }
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public String e() {
        return "Con";
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public byte[] e_() throws MqttException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            int i = this.l;
            if (i == 3) {
                a(dataOutputStream, "MQIsdp");
            } else if (i == 4) {
                a(dataOutputStream, "MQTT");
            }
            dataOutputStream.write(this.l);
            byte b = this.f ? (byte) 2 : (byte) 0;
            MqttMessage mqttMessage = this.g;
            if (mqttMessage != null) {
                b = (byte) (((byte) (b | 4)) | (mqttMessage.getQos() << 3));
                if (this.g.isRetained()) {
                    b = (byte) (b | 32);
                }
            }
            if (this.h != null) {
                b = (byte) (b | 128);
                if (this.i != null) {
                    b = (byte) (b | 64);
                }
            }
            dataOutputStream.write(b);
            dataOutputStream.writeShort(this.j);
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new MqttException(e);
        }
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public boolean f_() {
        return false;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public String toString() {
        String uVar = super.toString();
        return uVar + " clientId " + this.e + " keepAliveInterval " + this.j;
    }

    public d(String str, int i, boolean z, int i2, String str2, char[] cArr, MqttMessage mqttMessage, String str3) {
        super((byte) 1);
        this.e = str;
        this.f = z;
        this.j = i2;
        this.h = str2;
        this.i = cArr;
        this.g = mqttMessage;
        this.k = str3;
        this.l = i;
    }
}
