package com.coveiot.android.bleabstract.models;
/* loaded from: classes2.dex */
public enum ConnectionStatus {
    CONNECTED("connected"),
    CONNECTING("connecting"),
    DISCONNECTED("disconnected"),
    SCANNING("scanning"),
    DISCOVERING_SERVICES("servicediscovery");

    ConnectionStatus(String str) {
    }
}
