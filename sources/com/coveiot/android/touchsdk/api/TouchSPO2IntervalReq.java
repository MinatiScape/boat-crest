package com.coveiot.android.touchsdk.api;

import com.touchgui.sdk.bean.TGSpo2Config;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class TouchSPO2IntervalReq extends TouchELXBaseReq {
    @NotNull
    public final TGSpo2Config e;

    public TouchSPO2IntervalReq(@NotNull TGSpo2Config spO2IntervalConfig) {
        Intrinsics.checkNotNullParameter(spO2IntervalConfig, "spO2IntervalConfig");
        this.e = spO2IntervalConfig;
    }

    @NotNull
    public final TGSpo2Config getSpO2IntervalConfig() {
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
