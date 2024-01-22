package com.coveiot.android.touchsdk.api;

import com.touchgui.sdk.bean.TGPhysiologicalCycle;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class TouchFemaleWellnessConfigReq extends TouchELXBaseReq {
    @NotNull
    public TGPhysiologicalCycle e;

    public TouchFemaleWellnessConfigReq(@NotNull TGPhysiologicalCycle tgPhysiologicalCycle) {
        Intrinsics.checkNotNullParameter(tgPhysiologicalCycle, "tgPhysiologicalCycle");
        this.e = tgPhysiologicalCycle;
    }

    @NotNull
    public final TGPhysiologicalCycle getTgPhysiologicalCycle() {
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

    public final void setTgPhysiologicalCycle(@NotNull TGPhysiologicalCycle tGPhysiologicalCycle) {
        Intrinsics.checkNotNullParameter(tGPhysiologicalCycle, "<set-?>");
        this.e = tGPhysiologicalCycle;
    }
}
