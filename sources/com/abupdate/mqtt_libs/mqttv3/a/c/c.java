package com.abupdate.mqtt_libs.mqttv3.a.c;

import com.abupdate.mqtt_libs.mqttv3.MqttException;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
/* loaded from: classes.dex */
public class c extends b {
    public int e;
    public boolean f;

    public c(byte b, byte[] bArr) throws IOException {
        super((byte) 2);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.f = (dataInputStream.readUnsignedByte() & 1) == 1;
        this.e = dataInputStream.readUnsignedByte();
        dataInputStream.close();
    }

    public int a_() {
        return this.e;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public String e() {
        return "Con";
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public byte[] e_() throws MqttException {
        return new byte[0];
    }

    public boolean f() {
        return this.f;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public boolean f_() {
        return false;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.b, com.abupdate.mqtt_libs.mqttv3.a.c.u
    public String toString() {
        return super.toString() + " session present:" + this.f + " return code: " + this.e;
    }
}
