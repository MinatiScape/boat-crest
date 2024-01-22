package com.coveiot.android.touchsdk.api;

import com.touchgui.sdk.bean.TGWorkoutMode;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class TouchSetWorkoutModesReq extends TouchELXBaseReq {
    @NotNull
    public final TGWorkoutMode e;

    public TouchSetWorkoutModesReq(@NotNull TGWorkoutMode tgWorkoutMode) {
        Intrinsics.checkNotNullParameter(tgWorkoutMode, "tgWorkoutMode");
        this.e = tgWorkoutMode;
    }

    @NotNull
    public final TGWorkoutMode getTgWorkoutMode() {
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
