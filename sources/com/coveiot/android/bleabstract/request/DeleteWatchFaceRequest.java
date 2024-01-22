package com.coveiot.android.bleabstract.request;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class DeleteWatchFaceRequest extends BleBaseRequest {
    public final int f;
    @Nullable
    public String g;

    public DeleteWatchFaceRequest(int i) {
        this.f = i;
    }

    public final int getWatchFaceId() {
        return this.f;
    }

    @Nullable
    public final String getWatchFaceName() {
        return this.g;
    }

    public final void setWatchFaceName(@Nullable String str) {
        this.g = str;
    }
}
