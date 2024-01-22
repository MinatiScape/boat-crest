package com.coveiot.android.touchsdk.api;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class TouchCloudWatchFaceReq extends TouchELXBaseReq {
    public int e;
    @NotNull
    public String f;

    public TouchCloudWatchFaceReq(int i, @NotNull String watchFaceFilePath) {
        Intrinsics.checkNotNullParameter(watchFaceFilePath, "watchFaceFilePath");
        this.e = i;
        this.f = watchFaceFilePath;
    }

    @NotNull
    public final String getWatchFaceFilePath() {
        return this.f;
    }

    public final int getWatchfacePosition() {
        return this.e;
    }

    @Override // com.coveiot.android.touchsdk.api.TouchELXBaseReq
    public boolean isMultiPacket() {
        return true;
    }

    @Override // com.coveiot.android.touchsdk.api.TouchELXBaseReq
    public boolean isPriority() {
        return false;
    }

    public final void setWatchFaceFilePath(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f = str;
    }

    public final void setWatchfacePosition(int i) {
        this.e = i;
    }
}
