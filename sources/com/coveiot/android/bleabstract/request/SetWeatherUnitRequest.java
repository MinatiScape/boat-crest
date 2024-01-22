package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.MeasurementUnitType;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class SetWeatherUnitRequest extends BleBaseRequest {
    @NotNull
    public final MeasurementUnitType f;

    public SetWeatherUnitRequest(@NotNull MeasurementUnitType measurementUnitType) {
        Intrinsics.checkNotNullParameter(measurementUnitType, "measurementUnitType");
        this.f = measurementUnitType;
    }

    @NotNull
    public final MeasurementUnitType getMeasurementUnitType() {
        return this.f;
    }
}
