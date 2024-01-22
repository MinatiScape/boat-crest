package com.abupdate.mqtt_libs.mqttv3.a.c;

import com.abupdate.mqtt_libs.mqttv3.MqttException;
/* loaded from: classes.dex */
public abstract class h extends u implements com.abupdate.mqtt_libs.mqttv3.j {
    public h(byte b) {
        super(b);
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.j
    public byte[] a() throws com.abupdate.mqtt_libs.mqttv3.k {
        try {
            return k();
        } catch (MqttException e) {
            throw new com.abupdate.mqtt_libs.mqttv3.k(e.getCause());
        }
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.j
    public int b() throws com.abupdate.mqtt_libs.mqttv3.k {
        return a().length;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.j
    public int b_() throws com.abupdate.mqtt_libs.mqttv3.k {
        return 0;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.j
    public int c() throws com.abupdate.mqtt_libs.mqttv3.k {
        return 0;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.j
    public byte[] d() throws com.abupdate.mqtt_libs.mqttv3.k {
        try {
            return d_();
        } catch (MqttException e) {
            throw new com.abupdate.mqtt_libs.mqttv3.k(e.getCause());
        }
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.j
    public int f() throws com.abupdate.mqtt_libs.mqttv3.k {
        return 0;
    }
}
