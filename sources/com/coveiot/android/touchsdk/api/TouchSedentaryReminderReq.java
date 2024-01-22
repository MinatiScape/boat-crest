package com.coveiot.android.touchsdk.api;

import com.touchgui.sdk.bean.TGSedentaryConfig;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class TouchSedentaryReminderReq extends TouchELXBaseReq {
    @NotNull
    public final TGSedentaryConfig e;

    public TouchSedentaryReminderReq(@NotNull TGSedentaryConfig sedentaryConfig) {
        Intrinsics.checkNotNullParameter(sedentaryConfig, "sedentaryConfig");
        this.e = sedentaryConfig;
    }

    @NotNull
    public final TGSedentaryConfig getSedentaryConfig() {
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
