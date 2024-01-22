package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.AutoActivityDetectionModel;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SetAutoActivityDetectionSettingsRequest extends BleBaseRequest {
    @Nullable
    public AutoActivityDetectionModel f;

    @Nullable
    public final AutoActivityDetectionModel getAutoActivityDetectionModel() {
        return this.f;
    }

    public final void setAutoActivityDetectionModel(@Nullable AutoActivityDetectionModel autoActivityDetectionModel) {
        this.f = autoActivityDetectionModel;
    }
}
