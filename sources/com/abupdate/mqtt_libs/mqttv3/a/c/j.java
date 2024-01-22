package com.abupdate.mqtt_libs.mqttv3.a.c;

import com.abupdate.mqtt_libs.mqttv3.MqttException;
/* loaded from: classes.dex */
public class j extends b {
    public j(byte b, byte[] bArr) {
        super((byte) 13);
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
}
