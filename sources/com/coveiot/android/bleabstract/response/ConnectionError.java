package com.coveiot.android.bleabstract.response;
/* loaded from: classes2.dex */
public class ConnectionError {

    /* renamed from: a  reason: collision with root package name */
    public int f3588a;
    public long b;

    public ConnectionError(int i, long j) {
        this.f3588a = i;
        this.b = j;
    }

    public int getGattError() {
        return this.f3588a;
    }

    public long getTimestamp() {
        return this.b;
    }
}
