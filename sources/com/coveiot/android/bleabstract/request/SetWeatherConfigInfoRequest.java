package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.MeasurementUnitType;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class SetWeatherConfigInfoRequest extends BleBaseRequest {
    @NotNull
    public final MeasurementUnitType f;
    public boolean g;

    public /* synthetic */ SetWeatherConfigInfoRequest(MeasurementUnitType measurementUnitType, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(measurementUnitType, (i & 2) != 0 ? true : z);
    }

    @NotNull
    public final MeasurementUnitType getMeasurementUnitType() {
        return this.f;
    }

    public final boolean isOn() {
        return this.g;
    }

    public final void setOn(boolean z) {
        this.g = z;
    }

    public SetWeatherConfigInfoRequest(@NotNull MeasurementUnitType measurementUnitType, boolean z) {
        Intrinsics.checkNotNullParameter(measurementUnitType, "measurementUnitType");
        this.f = measurementUnitType;
        this.g = z;
    }
}
