package com.coveiot.android.bleabstract.request;
/* loaded from: classes2.dex */
public class ActivityDetailsDataRequest extends BleBaseRequest {
    public long f;

    public ActivityDetailsDataRequest(long j) {
        this.f = j;
    }

    public long getSessionId() {
        return this.f;
    }
}
