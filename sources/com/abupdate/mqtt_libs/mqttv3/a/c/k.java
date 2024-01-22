package com.abupdate.mqtt_libs.mqttv3.a.c;

import com.abupdate.mqtt_libs.mqttv3.MqttException;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
/* loaded from: classes.dex */
public class k extends b {
    public k(byte b, byte[] bArr) throws IOException {
        super((byte) 4);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.f1951a = dataInputStream.readUnsignedShort();
        dataInputStream.close();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public byte[] e_() throws MqttException {
        return l();
    }

    public k(o oVar) {
        super((byte) 4);
        this.f1951a = oVar.j();
    }
}
