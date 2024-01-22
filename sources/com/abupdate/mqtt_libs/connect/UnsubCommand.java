package com.abupdate.mqtt_libs.connect;

import com.abupdate.mqtt_libs.mqttv3.IMqttActionListener;
import com.abupdate.mqtt_libs.mqttv3.MqttException;
/* loaded from: classes.dex */
public class UnsubCommand implements Command {

    /* renamed from: a  reason: collision with root package name */
    public String f1925a;

    @Override // com.abupdate.mqtt_libs.connect.Command
    public void execute(IMqttActionListener iMqttActionListener) throws MqttException {
        MqttManager.getInstance().getConnect().getClient().unsubscribe(this.f1925a, (Object) null, iMqttActionListener);
    }

    public UnsubCommand setTopic(String str) {
        this.f1925a = str;
        return this;
    }
}
