package com.abupdate.mqtt_libs.mqttv3.a.c;

import com.abupdate.mqtt_libs.mqttv3.MqttException;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttDisconnect;
/* loaded from: classes.dex */
public class e extends u {
    public e() {
        super((byte) 14);
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public byte c_() {
        return (byte) 0;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public String e() {
        return MqttDisconnect.KEY;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public byte[] e_() throws MqttException {
        return new byte[0];
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public boolean f_() {
        return false;
    }

    public e(byte b, byte[] bArr) throws IOException {
        super((byte) 14);
    }
}
