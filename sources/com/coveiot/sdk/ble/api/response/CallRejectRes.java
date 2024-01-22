package com.coveiot.sdk.ble.api.response;

import java.io.Serializable;
/* loaded from: classes9.dex */
public class CallRejectRes implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    public boolean f7552a;

    public CallRejectRes(boolean z) {
        this.f7552a = z;
    }

    public boolean isReject() {
        return this.f7552a;
    }
}
