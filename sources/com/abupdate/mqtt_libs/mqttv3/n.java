package com.abupdate.mqtt_libs.mqttv3;

import com.abupdate.mqtt_libs.mqttv3.a.c.u;
import com.abupdate.mqtt_libs.mqttv3.a.r;
/* loaded from: classes.dex */
public class n implements IMqttToken {

    /* renamed from: a  reason: collision with root package name */
    public r f1970a;

    public n() {
        this.f1970a = null;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttToken
    public IMqttActionListener getActionCallback() {
        return this.f1970a.f();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttToken
    public c getClient() {
        return this.f1970a.j();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttToken
    public MqttException getException() {
        return this.f1970a.c();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttToken
    public int[] getGrantedQos() {
        return this.f1970a.q();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttToken
    public int getMessageId() {
        return this.f1970a.a();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttToken
    public u getResponse() {
        return this.f1970a.s();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttToken
    public boolean getSessionPresent() {
        return this.f1970a.r();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttToken
    public String[] getTopics() {
        return this.f1970a.m();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttToken
    public Object getUserContext() {
        return this.f1970a.n();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttToken
    public boolean isComplete() {
        return this.f1970a.d();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttToken
    public void setActionCallback(IMqttActionListener iMqttActionListener) {
        this.f1970a.a(iMqttActionListener);
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttToken
    public void setUserContext(Object obj) {
        this.f1970a.a(obj);
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttToken
    public void waitForCompletion() throws MqttException {
        this.f1970a.a(-1L);
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttToken
    public void waitForCompletion(long j) throws MqttException {
        this.f1970a.a(j);
    }

    public n(String str) {
        this.f1970a = null;
        this.f1970a = new r(str);
    }
}
