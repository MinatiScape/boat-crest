package com.abupdate.mqtt_libs.mqttv3;
/* loaded from: classes.dex */
public class i extends n implements IMqttDeliveryToken {
    public i() {
    }

    public void a(MqttMessage mqttMessage) {
        this.f1970a.a(mqttMessage);
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttDeliveryToken
    public MqttMessage getMessage() throws MqttException {
        return this.f1970a.k();
    }

    public i(String str) {
        super(str);
    }
}
