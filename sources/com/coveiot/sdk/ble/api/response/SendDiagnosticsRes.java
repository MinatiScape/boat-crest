package com.coveiot.sdk.ble.api.response;

import java.io.Serializable;
/* loaded from: classes9.dex */
public class SendDiagnosticsRes implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    public int f7559a;

    public SendDiagnosticsRes(int i) {
        this.f7559a = i;
    }

    public int getStatus() {
        return this.f7559a;
    }

    public void setStatus(int i) {
        this.f7559a = i;
    }

    public String toString() {
        return "SendDiagnosticsRes{status=" + this.f7559a + '}';
    }
}
