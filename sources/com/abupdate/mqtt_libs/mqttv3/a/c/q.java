package com.abupdate.mqtt_libs.mqttv3.a.c;

import com.abupdate.mqtt_libs.mqttv3.MqttException;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
/* loaded from: classes.dex */
public class q extends b {
    public int[] e;

    public q(byte b, byte[] bArr) throws IOException {
        super((byte) 9);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.f1951a = dataInputStream.readUnsignedShort();
        this.e = new int[bArr.length - 2];
        int i = 0;
        for (int read = dataInputStream.read(); read != -1; read = dataInputStream.read()) {
            this.e[i] = read;
            i++;
        }
        dataInputStream.close();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.u
    public byte[] e_() throws MqttException {
        return new byte[0];
    }

    public int[] f() {
        return this.e;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.c.b, com.abupdate.mqtt_libs.mqttv3.a.c.u
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(super.toString());
        stringBuffer.append(" granted Qos");
        for (int i = 0; i < this.e.length; i++) {
            stringBuffer.append(HexStringBuilder.DEFAULT_SEPARATOR);
            stringBuffer.append(this.e[i]);
        }
        return stringBuffer.toString();
    }
}
