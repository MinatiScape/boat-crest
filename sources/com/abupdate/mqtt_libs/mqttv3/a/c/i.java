package com.abupdate.mqtt_libs.mqttv3.a.c;

import com.abupdate.mqtt_libs.mqttv3.MqttException;
import java.io.IOException;
/* loaded from: classes.dex */
public class i extends u {
    public i() {
        super((byte) 12);
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public byte c_() {
        return (byte) 0;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public String e() {
        return "Ping";
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public byte[] e_() throws MqttException {
        return new byte[0];
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public boolean f_() {
        return false;
    }

    public i(byte b, byte[] bArr) throws IOException {
        super((byte) 12);
    }
}
