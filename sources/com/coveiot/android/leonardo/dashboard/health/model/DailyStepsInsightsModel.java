package com.coveiot.android.leonardo.dashboard.health.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class DailyStepsInsightsModel implements Serializable {
    private final int currentDaySteps;
    private final int previousDaySteps;

    public DailyStepsInsightsModel(int i, int i2) {
        this.currentDaySteps = i;
        this.previousDaySteps = i2;
    }

    public static /* synthetic */ DailyStepsInsightsModel copy$default(DailyStepsInsightsModel dailyStepsInsightsModel, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = dailyStepsInsightsModel.currentDaySteps;
        }
        if ((i3 & 2) != 0) {
            i2 = dailyStepsInsightsModel.previousDaySteps;
        }
        return dailyStepsInsightsModel.copy(i, i2);
    }

    public final int component1() {
        return this.currentDaySteps;
    }

    public final int component2() {
        return this.previousDaySteps;
    }

    @NotNull
    public final DailyStepsInsightsModel copy(int i, int i2) {
        return new DailyStepsInsightsModel(i, i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DailyStepsInsightsModel) {
            DailyStepsInsightsModel dailyStepsInsightsModel = (DailyStepsInsightsModel) obj;
            return this.currentDaySteps == dailyStepsInsightsModel.currentDaySteps && this.previousDaySteps == dailyStepsInsightsModel.previousDaySteps;
        }
        return false;
    }

    public final int getCurrentDaySteps() {
        return this.currentDaySteps;
    }

    public final int getPreviousDaySteps() {
        return this.previousDaySteps;
    }

    public int hashCode() {
        return (Integer.hashCode(this.currentDaySteps) * 31) + Integer.hashCode(this.previousDaySteps);
    }

    @NotNull
    public String toString() {
        return "DailyStepsInsightsModel(currentDaySteps=" + this.currentDaySteps + ", previousDaySteps=" + this.previousDaySteps + HexStringBuilder.COMMENT_END_CHAR;
    }
}
