package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.ActivityTypeModel;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class SetActivityListRequest extends BleBaseRequest {
    @NotNull
    public final List<ActivityTypeModel> f;
    public final int g;

    public SetActivityListRequest(@NotNull List<ActivityTypeModel> activityTypeModelList, int i) {
        Intrinsics.checkNotNullParameter(activityTypeModelList, "activityTypeModelList");
        this.f = activityTypeModelList;
        this.g = i;
    }

    @NotNull
    public final List<ActivityTypeModel> getActivityTypeModelList() {
        return this.f;
    }

    public final int getShowListSize() {
        return this.g;
    }
}
