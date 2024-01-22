package com.coveiot.android.bleabstract.request;
/* loaded from: classes2.dex */
public final class CameraControlRequest extends BleBaseRequest {
    public final boolean f;

    public CameraControlRequest(boolean z) {
        this.f = z;
    }

    public final boolean isEnabled() {
        return this.f;
    }
}
