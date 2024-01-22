package com.coveiot.android.touchsdk.api;

import com.touchgui.sdk.bean.TGUnitConfig;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class TouchSetWeatherUnitReq extends TouchELXBaseReq {
    public boolean e;
    @NotNull
    public TGUnitConfig f;

    public TouchSetWeatherUnitReq(boolean z, @NotNull TGUnitConfig unitConfig) {
        Intrinsics.checkNotNullParameter(unitConfig, "unitConfig");
        this.e = z;
        this.f = unitConfig;
    }

    @NotNull
    public final TGUnitConfig getUnitConfig() {
        return this.f;
    }

    public final boolean isEnabled() {
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

    public final void setEnabled(boolean z) {
        this.e = z;
    }

    public final void setUnitConfig(@NotNull TGUnitConfig tGUnitConfig) {
        Intrinsics.checkNotNullParameter(tGUnitConfig, "<set-?>");
        this.f = tGUnitConfig;
    }
}
