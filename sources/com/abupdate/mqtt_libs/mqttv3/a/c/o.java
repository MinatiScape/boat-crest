package com.abupdate.mqtt_libs.mqttv3.a.c;

import com.abupdate.mqtt_libs.mqttv3.MqttException;
import com.abupdate.mqtt_libs.mqttv3.MqttMessage;
import com.jstyle.blesdk1860.constant.BleConst;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
/* loaded from: classes.dex */
public class o extends h {
    public MqttMessage e;
    public String f;
    public byte[] g;

    public o(String str, MqttMessage mqttMessage) {
        super((byte) 3);
        this.g = null;
        this.f = str;
        this.e = mqttMessage;
    }

    public static byte[] a(MqttMessage mqttMessage) {
        return mqttMessage.getPayload();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.h, com.abupdate.mqtt_libs.mqttv3.j
    public int b_() {
        try {
            return d_().length;
        } catch (MqttException unused) {
            return 0;
        }
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public byte c_() {
        byte qos = (byte) (this.e.getQos() << 1);
        if (this.e.isRetained()) {
            qos = (byte) (qos | 1);
        }
        return (this.e.isDuplicate() || this.b) ? (byte) (qos | 8) : qos;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public byte[] d_() throws MqttException {
        if (this.g == null) {
            this.g = a(this.e);
        }
        return this.g;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public byte[] e_() throws MqttException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            a(dataOutputStream, this.f);
            if (this.e.getQos() > 0) {
                dataOutputStream.writeShort(this.f1951a);
            }
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new MqttException(e);
        }
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public boolean f_() {
        return true;
    }

    public String g() {
        return this.f;
    }

    public MqttMessage h() {
        return this.e;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public String toString() {
        String str;
        StringBuffer stringBuffer = new StringBuffer();
        byte[] payload = this.e.getPayload();
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
        stringBuffer2.append(this.e.getQos());
        if (this.e.getQos() > 0) {
            stringBuffer2.append(" msgId:");
            stringBuffer2.append(this.f1951a);
        }
        stringBuffer2.append(" retained:");
        stringBuffer2.append(this.e.isRetained());
        stringBuffer2.append(" dup:");
        stringBuffer2.append(this.b);
        stringBuffer2.append(" topic:\"");
        stringBuffer2.append(this.f);
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

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public void a(int i) {
        super.a(i);
        MqttMessage mqttMessage = this.e;
        if (mqttMessage instanceof p) {
            ((p) mqttMessage).a(i);
        }
    }

    public o(byte b, byte[] bArr) throws MqttException, IOException {
        super((byte) 3);
        this.g = null;
        p pVar = new p();
        this.e = pVar;
        pVar.setQos(3 & (b >> 1));
        if ((b & 1) == 1) {
            this.e.setRetained(true);
        }
        if ((b & 8) == 8) {
            ((p) this.e).setDuplicate(true);
        }
        a aVar = new a(new ByteArrayInputStream(bArr));
        DataInputStream dataInputStream = new DataInputStream(aVar);
        this.f = b(dataInputStream);
        if (this.e.getQos() > 0) {
            this.f1951a = dataInputStream.readUnsignedShort();
        }
        byte[] bArr2 = new byte[bArr.length - aVar.a()];
        dataInputStream.readFully(bArr2);
        dataInputStream.close();
        this.e.setPayload(bArr2);
    }
}
