package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.GoalType;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class GetCalorieDistanceGoalRequest extends BleBaseRequest {
    @NotNull
    public GoalType f;

    public GetCalorieDistanceGoalRequest() {
        this(null, 1, null);
    }

    public GetCalorieDistanceGoalRequest(@NotNull GoalType goalType) {
        Intrinsics.checkNotNullParameter(goalType, "goalType");
        this.f = goalType;
    }

    @NotNull
    public final GoalType getGoalType() {
        return this.f;
    }

    public final void setGoalType(@NotNull GoalType goalType) {
        Intrinsics.checkNotNullParameter(goalType, "<set-?>");
        this.f = goalType;
    }

    public /* synthetic */ GetCalorieDistanceGoalRequest(GoalType goalType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? GoalType.ALL : goalType);
    }
}
