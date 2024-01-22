package com.coveiot.android.bleabstract.request;
/* loaded from: classes2.dex */
public final class BTCallControlRequest extends BleBaseRequest {
    public final boolean f;
    public final boolean g;
    public final short h;

    public BTCallControlRequest(boolean z, boolean z2, short s) {
        this.f = z;
        this.g = z2;
        this.h = s;
    }

    public final short getDuration() {
        return this.h;
    }

    public final boolean isEnabled() {
        return this.g;
    }

    public final boolean isValidFlag() {
        return this.f;
    }
}
