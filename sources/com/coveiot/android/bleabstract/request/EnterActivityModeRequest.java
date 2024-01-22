package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.ActivityState;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class EnterActivityModeRequest extends BleBaseRequest {
    @NotNull
    public final String f;
    @NotNull
    public final ActivityState g;

    public EnterActivityModeRequest(@NotNull String activityType, @NotNull ActivityState activityState) {
        Intrinsics.checkNotNullParameter(activityType, "activityType");
        Intrinsics.checkNotNullParameter(activityState, "activityState");
        this.f = activityType;
        this.g = activityState;
    }

    @NotNull
    public final ActivityState getActivityState() {
        return this.g;
    }

    @NotNull
    public final String getActivityType() {
        return this.f;
    }
}
