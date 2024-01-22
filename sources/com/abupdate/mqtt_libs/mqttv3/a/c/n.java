package com.abupdate.mqtt_libs.mqttv3.a.c;

import com.abupdate.mqtt_libs.mqttv3.MqttException;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
/* loaded from: classes.dex */
public class n extends h {
    public n(m mVar) {
        super((byte) 6);
        a(mVar.j());
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public byte c_() {
        return (byte) ((this.b ? 8 : 0) | 2);
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public byte[] e_() throws MqttException {
        return l();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public String toString() {
        return super.toString() + " msgId " + this.f1951a;
    }

    public n(byte b, byte[] bArr) throws IOException {
        super((byte) 6);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.f1951a = dataInputStream.readUnsignedShort();
        dataInputStream.close();
    }
}
