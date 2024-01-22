package com.coveiot.android.smasdk.api;

import com.szabh.smable3.entity.BleSleepQuality;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class SmaSleepQualityReq extends SmaBaseReq {
    @NotNull
    public final BleSleepQuality d;

    public SmaSleepQualityReq(@NotNull BleSleepQuality bleSleepQuality) {
        Intrinsics.checkNotNullParameter(bleSleepQuality, "bleSleepQuality");
        this.d = bleSleepQuality;
    }

    @NotNull
    public final BleSleepQuality getBleSleepQuality() {
        return this.d;
    }

    @Override // com.coveiot.android.smasdk.api.SmaBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.smasdk.api.SmaBaseReq
    public boolean isPriority() {
        return true;
    }
}
