package com.abupdate.mqtt_libs.connect;

import com.abupdate.mqtt_libs.mqttv3.IMqttActionListener;
import com.abupdate.mqtt_libs.mqttv3.MqttException;
/* loaded from: classes.dex */
public class PubCommand implements Command {

    /* renamed from: a  reason: collision with root package name */
    public String f1923a;
    public int b;
    public String c;
    public boolean d;

    @Override // com.abupdate.mqtt_libs.connect.Command
    public void execute(IMqttActionListener iMqttActionListener) throws MqttException {
        MqttManager.getInstance().getConnect().getClient().publish(this.f1923a, this.c.getBytes(), this.b, this.d, null, iMqttActionListener);
    }

    public PubCommand setMessage(String str) {
        this.c = str;
        return this;
    }

    public PubCommand setQos(int i) {
        this.b = i;
        return this;
    }

    public PubCommand setRetained(boolean z) {
        this.d = z;
        return this;
    }

    public PubCommand setTopic(String str) {
        this.f1923a = str;
        return this;
    }
}
