package com.coveiot.android.bleabstract.response;

import com.coveiot.android.bleabstract.models.ActivityTypeModel;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class GetActivityListResponse {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final List<ActivityTypeModel> f3600a;
    @NotNull
    public final List<ActivityTypeModel> b;
    public boolean c;

    public GetActivityListResponse(@NotNull List<ActivityTypeModel> supportedActivityList, @NotNull List<ActivityTypeModel> configuredActivityList) {
        Intrinsics.checkNotNullParameter(supportedActivityList, "supportedActivityList");
        Intrinsics.checkNotNullParameter(configuredActivityList, "configuredActivityList");
        this.f3600a = supportedActivityList;
        this.b = configuredActivityList;
    }

    @NotNull
    public final List<ActivityTypeModel> getConfiguredActivityList() {
        return this.b;
    }

    @NotNull
    public final List<ActivityTypeModel> getSupportedActivityList() {
        return this.f3600a;
    }

    public final boolean isComplete() {
        return this.c;
    }

    public final void setComplete(boolean z) {
        this.c = z;
    }

    @NotNull
    public String toString() {
        return "GetActivityListResponse(supportedActivityList=" + this.f3600a + " configuredActivityList=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
