package org.eclipse.paho.android.service;

import android.os.Binder;
/* loaded from: classes13.dex */
public class MqttServiceBinder extends Binder {

    /* renamed from: a  reason: collision with root package name */
    public MqttService f15432a;
    public String b;

    public MqttServiceBinder(MqttService mqttService) {
        this.f15432a = mqttService;
    }

    public String getActivityToken() {
        return this.b;
    }

    public MqttService getService() {
        return this.f15432a;
    }

    public void setActivityToken(String str) {
        this.b = str;
    }
}
