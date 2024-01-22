package com.htsmart.wristband2.bean;
/* loaded from: classes11.dex */
public enum ConnectionState {
    CONNECTING("CONNECTING"),
    CONNECTED("CONNECTED"),
    DISCONNECTED("DISCONNECTED");
    
    private final String b;

    ConnectionState(String str) {
        this.b = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.b;
    }
}
