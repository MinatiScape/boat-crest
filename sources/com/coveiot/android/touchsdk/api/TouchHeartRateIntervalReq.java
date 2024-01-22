package com.coveiot.android.touchsdk.api;

import com.touchgui.sdk.bean.TGHeartRateMonitoringModeConfig;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class TouchHeartRateIntervalReq extends TouchELXBaseReq {
    @NotNull
    public final TGHeartRateMonitoringModeConfig e;

    public TouchHeartRateIntervalReq(@NotNull TGHeartRateMonitoringModeConfig hrIntervalConfig) {
        Intrinsics.checkNotNullParameter(hrIntervalConfig, "hrIntervalConfig");
        this.e = hrIntervalConfig;
    }

    @NotNull
    public final TGHeartRateMonitoringModeConfig getHrIntervalConfig() {
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
