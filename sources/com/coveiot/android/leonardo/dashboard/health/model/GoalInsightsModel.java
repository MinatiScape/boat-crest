package com.coveiot.android.leonardo.dashboard.health.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class GoalInsightsModel implements Serializable {
    @NotNull
    private final String calendarType;

    public GoalInsightsModel(@NotNull String calendarType) {
        Intrinsics.checkNotNullParameter(calendarType, "calendarType");
        this.calendarType = calendarType;
    }

    public static /* synthetic */ GoalInsightsModel copy$default(GoalInsightsModel goalInsightsModel, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = goalInsightsModel.calendarType;
        }
        return goalInsightsModel.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.calendarType;
    }

    @NotNull
    public final GoalInsightsModel copy(@NotNull String calendarType) {
        Intrinsics.checkNotNullParameter(calendarType, "calendarType");
        return new GoalInsightsModel(calendarType);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof GoalInsightsModel) && Intrinsics.areEqual(this.calendarType, ((GoalInsightsModel) obj).calendarType);
    }

    @NotNull
    public final String getCalendarType() {
        return this.calendarType;
    }

    public int hashCode() {
        return this.calendarType.hashCode();
    }

    @NotNull
    public String toString() {
        return "GoalInsightsModel(calendarType=" + this.calendarType + HexStringBuilder.COMMENT_END_CHAR;
    }
}
