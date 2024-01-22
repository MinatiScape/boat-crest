package com.coveiot.android.fitnessbuddies.models;

import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class FitnessBuddiesWalkData implements Serializable {
    private int calories;
    @NotNull
    private String date;
    @NotNull
    private String day;
    private int meters;
    private int steps;

    public FitnessBuddiesWalkData(@NotNull String date, int i, int i2, int i3, @NotNull String day) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(day, "day");
        this.date = date;
        this.steps = i;
        this.calories = i2;
        this.meters = i3;
        this.day = day;
    }

    public static /* synthetic */ FitnessBuddiesWalkData copy$default(FitnessBuddiesWalkData fitnessBuddiesWalkData, String str, int i, int i2, int i3, String str2, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            str = fitnessBuddiesWalkData.date;
        }
        if ((i4 & 2) != 0) {
            i = fitnessBuddiesWalkData.steps;
        }
        int i5 = i;
        if ((i4 & 4) != 0) {
            i2 = fitnessBuddiesWalkData.calories;
        }
        int i6 = i2;
        if ((i4 & 8) != 0) {
            i3 = fitnessBuddiesWalkData.meters;
        }
        int i7 = i3;
        if ((i4 & 16) != 0) {
            str2 = fitnessBuddiesWalkData.day;
        }
        return fitnessBuddiesWalkData.copy(str, i5, i6, i7, str2);
    }

    @NotNull
    public final String component1() {
        return this.date;
    }

    public final int component2() {
        return this.steps;
    }

    public final int component3() {
        return this.calories;
    }

    public final int component4() {
        return this.meters;
    }

    @NotNull
    public final String component5() {
        return this.day;
    }

    @NotNull
    public final FitnessBuddiesWalkData copy(@NotNull String date, int i, int i2, int i3, @NotNull String day) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(day, "day");
        return new FitnessBuddiesWalkData(date, i, i2, i3, day);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FitnessBuddiesWalkData) {
            FitnessBuddiesWalkData fitnessBuddiesWalkData = (FitnessBuddiesWalkData) obj;
            return Intrinsics.areEqual(this.date, fitnessBuddiesWalkData.date) && this.steps == fitnessBuddiesWalkData.steps && this.calories == fitnessBuddiesWalkData.calories && this.meters == fitnessBuddiesWalkData.meters && Intrinsics.areEqual(this.day, fitnessBuddiesWalkData.day);
        }
        return false;
    }

    public final int getCalories() {
        return this.calories;
    }

    @NotNull
    public final String getDate() {
        return this.date;
    }

    @NotNull
    public final String getDay() {
        return this.day;
    }

    public final int getMeters() {
        return this.meters;
    }

    public final int getSteps() {
        return this.steps;
    }

    public int hashCode() {
        return (((((((this.date.hashCode() * 31) + Integer.hashCode(this.steps)) * 31) + Integer.hashCode(this.calories)) * 31) + Integer.hashCode(this.meters)) * 31) + this.day.hashCode();
    }

    public final void setCalories(int i) {
        this.calories = i;
    }

    public final void setDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.date = str;
    }

    public final void setDay(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.day = str;
    }

    public final void setMeters(int i) {
        this.meters = i;
    }

    public final void setSteps(int i) {
        this.steps = i;
    }

    @NotNull
    public String toString() {
        return "FitnessBuddiesWalkData(date='" + this.date + "', steps=" + this.steps + ", calories=" + this.calories + ", meters=" + this.meters + ", day='" + this.day + "')";
    }
}
