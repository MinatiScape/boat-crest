package com.abupdate.iot_libs.constant;
/* loaded from: classes.dex */
public class SDKConfig {
    public static String HTTP_BASE_URL = null;
    public static final byte[] KEY = {49, 50, 51, 52, 53, 54, 98};
    public static String MQTT_HOST = null;
    public static final int MQTT_SSL_PORT = 1884;
    public static final int MQTT_TCP_PORT = 1883;

    /* renamed from: a  reason: collision with root package name */
    public static boolean f1888a = false;

    public static void gen() {
        boolean z = f1888a;
        HTTP_BASE_URL = z ? "https://iottest.adups.com" : "https://iotapi.adups.com";
        MQTT_HOST = z ? "iottest.adups.com" : "iotmqtt.adups.com";
    }
}
