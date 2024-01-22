package com.coveiot.android.smasdk.api;

import com.szabh.smable3.entity.BleGirlCareSettings;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class SmaGirlCareSettingsReq extends SmaBaseReq {
    @NotNull
    public BleGirlCareSettings d;

    public SmaGirlCareSettingsReq(@NotNull BleGirlCareSettings bleGirlCare) {
        Intrinsics.checkNotNullParameter(bleGirlCare, "bleGirlCare");
        this.d = bleGirlCare;
    }

    @NotNull
    public final BleGirlCareSettings getBleGirlCare() {
        return this.d;
    }

    @Override // com.coveiot.android.smasdk.api.SmaBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.smasdk.api.SmaBaseReq
    public boolean isPriority() {
        return false;
    }

    public final void setBleGirlCare(@NotNull BleGirlCareSettings bleGirlCareSettings) {
        Intrinsics.checkNotNullParameter(bleGirlCareSettings, "<set-?>");
        this.d = bleGirlCareSettings;
    }
}
