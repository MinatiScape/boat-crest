package com.coveiot.android.touchsdk.api;

import com.touchgui.sdk.bean.TGStressConfig;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class TouchStressIntervalReq extends TouchELXBaseReq {
    @NotNull
    public final TGStressConfig e;

    public TouchStressIntervalReq(@NotNull TGStressConfig stressIntervalConfig) {
        Intrinsics.checkNotNullParameter(stressIntervalConfig, "stressIntervalConfig");
        this.e = stressIntervalConfig;
    }

    @NotNull
    public final TGStressConfig getStressIntervalConfig() {
        return this.e;
    }

    @Override // com.coveiot.android.touchsdk.api.TouchELXBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.touchsdk.api.TouchELXBaseReq
    public boolean isPriority() {
        return true;
    }
}
