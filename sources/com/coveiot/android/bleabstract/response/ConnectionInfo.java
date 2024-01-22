package com.coveiot.android.bleabstract.response;

import com.coveiot.android.bleabstract.models.ConnectionStatus;
/* loaded from: classes2.dex */
public class ConnectionInfo {

    /* renamed from: a  reason: collision with root package name */
    public ConnectionStatus f3589a;
    public ConnectionError b;
    public long c;

    public ConnectionInfo(ConnectionStatus connectionStatus, ConnectionError connectionError, long j) {
        this.f3589a = connectionStatus;
        this.b = connectionError;
        this.c = j;
    }

    public ConnectionError getConnectionError() {
        return this.b;
    }

    public ConnectionStatus getConnectionStatus() {
        return this.f3589a;
    }

    public long getLastUpdateTimeStamp() {
        return this.c;
    }
}
