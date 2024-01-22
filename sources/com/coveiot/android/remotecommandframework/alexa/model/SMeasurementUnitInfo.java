package com.coveiot.android.remotecommandframework.alexa.model;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SMeasurementUnitInfo extends SCommandInfo {
    @SerializedName("unit")
    @Nullable
    private Integer unit;

    @Nullable
    public final Integer getUnit() {
        return this.unit;
    }

    public final void setUnit(@Nullable Integer num) {
        this.unit = num;
    }
}
