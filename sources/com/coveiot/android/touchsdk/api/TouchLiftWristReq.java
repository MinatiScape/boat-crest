package com.coveiot.android.touchsdk.api;

import com.touchgui.sdk.bean.TGRaiseWristConfig;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class TouchLiftWristReq extends TouchELXBaseReq {
    @NotNull
    public final TGRaiseWristConfig e;

    public TouchLiftWristReq(@NotNull TGRaiseWristConfig raiseWristConfig) {
        Intrinsics.checkNotNullParameter(raiseWristConfig, "raiseWristConfig");
        this.e = raiseWristConfig;
    }

    @NotNull
    public final TGRaiseWristConfig getRaiseWristConfig() {
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
