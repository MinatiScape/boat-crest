package com.abupdate.mqtt_libs.mqtt_service;

import android.os.Binder;
/* loaded from: classes.dex */
public class f extends Binder {

    /* renamed from: a  reason: collision with root package name */
    public MqttService f1937a;

    public f(MqttService mqttService) {
        this.f1937a = mqttService;
    }

    public MqttService a() {
        return this.f1937a;
    }

    public void b(String str) {
    }
}
