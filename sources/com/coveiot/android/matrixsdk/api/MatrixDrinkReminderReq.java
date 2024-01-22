package com.coveiot.android.matrixsdk.api;

import com.htsmart.wristband2.bean.config.DrinkWaterConfig;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class MatrixDrinkReminderReq extends MatrixBaseReq {
    @NotNull
    public final DrinkWaterConfig e;

    public MatrixDrinkReminderReq(@NotNull DrinkWaterConfig drinkWaterConfig) {
        Intrinsics.checkNotNullParameter(drinkWaterConfig, "drinkWaterConfig");
        this.e = drinkWaterConfig;
    }

    @NotNull
    public final DrinkWaterConfig getDrinkWaterConfig() {
        return this.e;
    }

    @Override // com.coveiot.android.matrixsdk.api.MatrixBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.matrixsdk.api.MatrixBaseReq
    public boolean isPriority() {
        return true;
    }
}
