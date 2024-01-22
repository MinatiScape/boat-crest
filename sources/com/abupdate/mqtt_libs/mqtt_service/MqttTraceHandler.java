package com.abupdate.mqtt_libs.mqtt_service;
/* loaded from: classes.dex */
public interface MqttTraceHandler {
    void traceDebug(String str, String str2);

    void traceError(String str, String str2);

    void traceException(String str, String str2, Exception exc);
}
