package com.coveiot.android.activitymodes.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class CurrentWeekAndDay implements Serializable {
    @NotNull
    private String currentDate;
    private int currentDay;
    private int currentWeek;

    public CurrentWeekAndDay(int i, int i2, @NotNull String currentDate) {
        Intrinsics.checkNotNullParameter(currentDate, "currentDate");
        this.currentWeek = i;
        this.currentDay = i2;
        this.currentDate = currentDate;
    }

    public static /* synthetic */ CurrentWeekAndDay copy$default(CurrentWeekAndDay currentWeekAndDay, int i, int i2, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = currentWeekAndDay.currentWeek;
        }
        if ((i3 & 2) != 0) {
            i2 = currentWeekAndDay.currentDay;
        }
        if ((i3 & 4) != 0) {
            str = currentWeekAndDay.currentDate;
        }
        return currentWeekAndDay.copy(i, i2, str);
    }

    public final int component1() {
        return this.currentWeek;
    }

    public final int component2() {
        return this.currentDay;
    }

    @NotNull
    public final String component3() {
        return this.currentDate;
    }

    @NotNull
    public final CurrentWeekAndDay copy(int i, int i2, @NotNull String currentDate) {
        Intrinsics.checkNotNullParameter(currentDate, "currentDate");
        return new CurrentWeekAndDay(i, i2, currentDate);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CurrentWeekAndDay) {
            CurrentWeekAndDay currentWeekAndDay = (CurrentWeekAndDay) obj;
            return this.currentWeek == currentWeekAndDay.currentWeek && this.currentDay == currentWeekAndDay.currentDay && Intrinsics.areEqual(this.currentDate, currentWeekAndDay.currentDate);
        }
        return false;
    }

    @NotNull
    public final String getCurrentDate() {
        return this.currentDate;
    }

    public final int getCurrentDay() {
        return this.currentDay;
    }

    public final int getCurrentWeek() {
        return this.currentWeek;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.currentWeek) * 31) + Integer.hashCode(this.currentDay)) * 31) + this.currentDate.hashCode();
    }

    public final void setCurrentDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.currentDate = str;
    }

    public final void setCurrentDay(int i) {
        this.currentDay = i;
    }

    public final void setCurrentWeek(int i) {
        this.currentWeek = i;
    }

    @NotNull
    public String toString() {
        return "CurrentWeekAndDay(currentWeek=" + this.currentWeek + ", currentDay=" + this.currentDay + ", currentDate=" + this.currentDate + HexStringBuilder.COMMENT_END_CHAR;
    }
}
