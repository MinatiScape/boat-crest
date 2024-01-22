package com.coveiot.sdk.ble.api.response;

import java.io.Serializable;
/* loaded from: classes9.dex */
public class WatchFaceChange implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    private int f7567a;

    public WatchFaceChange(int i) {
        this.f7567a = i;
    }

    public int getWatchFaceId() {
        return this.f7567a;
    }
}
