package com.coveiot.android.touchsdk.api;

import com.touchgui.sdk.bean.TGUnitConfig;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class TouchSetUnitReq extends TouchELXBaseReq {
    @NotNull
    public TGUnitConfig e;

    public TouchSetUnitReq(@NotNull TGUnitConfig unitConfig) {
        Intrinsics.checkNotNullParameter(unitConfig, "unitConfig");
        this.e = unitConfig;
    }

    @NotNull
    public final TGUnitConfig getUnitConfig() {
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

    public final void setUnitConfig(@NotNull TGUnitConfig tGUnitConfig) {
        Intrinsics.checkNotNullParameter(tGUnitConfig, "<set-?>");
        this.e = tGUnitConfig;
    }
}
