package com.coveiot.android.touchsdk.api;

import com.touchgui.sdk.bean.TGNotDisturbConfig;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class TouchSetDNDReq extends TouchELXBaseReq {
    @NotNull
    public final TGNotDisturbConfig e;

    public TouchSetDNDReq(@NotNull TGNotDisturbConfig dndConfig) {
        Intrinsics.checkNotNullParameter(dndConfig, "dndConfig");
        this.e = dndConfig;
    }

    @NotNull
    public final TGNotDisturbConfig getDndConfig() {
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
