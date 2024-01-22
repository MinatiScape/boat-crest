package com.abupdate.mqtt_libs.connect;

import com.abupdate.mqtt_libs.mqttv3.IMqttActionListener;
import com.abupdate.mqtt_libs.mqttv3.MqttException;
/* loaded from: classes.dex */
public class DisconnectCommand implements Command {

    /* renamed from: a  reason: collision with root package name */
    public long f1921a;

    @Override // com.abupdate.mqtt_libs.connect.Command
    public void execute(IMqttActionListener iMqttActionListener) throws MqttException {
        MqttManager.getInstance().getConnect().getClient().disconnect(this.f1921a);
    }

    public DisconnectCommand setQuiesceTimeout(long j) {
        this.f1921a = j;
        return this;
    }
}
