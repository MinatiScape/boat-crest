package com.abupdate.mqtt_libs.connect;

import com.abupdate.mqtt_libs.mqttv3.IMqttActionListener;
import com.abupdate.mqtt_libs.mqttv3.MqttException;
/* loaded from: classes.dex */
public class SubCommand implements Command {

    /* renamed from: a  reason: collision with root package name */
    public String f1924a;
    public int b;

    @Override // com.abupdate.mqtt_libs.connect.Command
    public void execute(IMqttActionListener iMqttActionListener) throws MqttException {
        MqttManager.getInstance().getConnect().getClient().subscribe(this.f1924a, this.b, (Object) null, iMqttActionListener);
    }

    public SubCommand setQos(int i) {
        this.b = i;
        return this;
    }

    public SubCommand setTopic(String str) {
        this.f1924a = str;
        return this;
    }
}
