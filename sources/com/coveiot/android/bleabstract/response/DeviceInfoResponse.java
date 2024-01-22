package com.coveiot.android.bleabstract.response;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class DeviceInfoResponse {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public DeviceInfoData f3593a;
    public boolean b;

    @Nullable
    public final DeviceInfoData getDeviceInfo() {
        return this.f3593a;
    }

    public final boolean isComplete() {
        return this.b;
    }

    public final void setComplete(boolean z) {
        this.b = z;
    }

    public final void setDeviceInfo(@Nullable DeviceInfoData deviceInfoData) {
        this.f3593a = deviceInfoData;
    }
}
