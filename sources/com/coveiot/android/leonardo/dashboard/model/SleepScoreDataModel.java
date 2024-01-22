package com.coveiot.android.leonardo.dashboard.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class SleepScoreDataModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f4793a;
    public int b;
    @NotNull
    public String c;
    public int d;

    public SleepScoreDataModel(@NotNull String minDate, int i, @NotNull String maxDate, int i2) {
        Intrinsics.checkNotNullParameter(minDate, "minDate");
        Intrinsics.checkNotNullParameter(maxDate, "maxDate");
        this.f4793a = minDate;
        this.b = i;
        this.c = maxDate;
        this.d = i2;
    }

    public static /* synthetic */ SleepScoreDataModel copy$default(SleepScoreDataModel sleepScoreDataModel, String str, int i, String str2, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = sleepScoreDataModel.f4793a;
        }
        if ((i3 & 2) != 0) {
            i = sleepScoreDataModel.b;
        }
        if ((i3 & 4) != 0) {
            str2 = sleepScoreDataModel.c;
        }
        if ((i3 & 8) != 0) {
            i2 = sleepScoreDataModel.d;
        }
        return sleepScoreDataModel.copy(str, i, str2, i2);
    }

    @NotNull
    public final String component1() {
        return this.f4793a;
    }

    public final int component2() {
        return this.b;
    }

    @NotNull
    public final String component3() {
        return this.c;
    }

    public final int component4() {
        return this.d;
    }

    @NotNull
    public final SleepScoreDataModel copy(@NotNull String minDate, int i, @NotNull String maxDate, int i2) {
        Intrinsics.checkNotNullParameter(minDate, "minDate");
        Intrinsics.checkNotNullParameter(maxDate, "maxDate");
        return new SleepScoreDataModel(minDate, i, maxDate, i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SleepScoreDataModel) {
            SleepScoreDataModel sleepScoreDataModel = (SleepScoreDataModel) obj;
            return Intrinsics.areEqual(this.f4793a, sleepScoreDataModel.f4793a) && this.b == sleepScoreDataModel.b && Intrinsics.areEqual(this.c, sleepScoreDataModel.c) && this.d == sleepScoreDataModel.d;
        }
        return false;
    }

    @NotNull
    public final String getMaxDate() {
        return this.c;
    }

    public final int getMaxSleepScore() {
        return this.d;
    }

    @NotNull
    public final String getMinDate() {
        return this.f4793a;
    }

    public final int getMinSleepScore() {
        return this.b;
    }

    public int hashCode() {
        return (((((this.f4793a.hashCode() * 31) + Integer.hashCode(this.b)) * 31) + this.c.hashCode()) * 31) + Integer.hashCode(this.d);
    }

    public final void setMaxDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.c = str;
    }

    public final void setMaxSleepScore(int i) {
        this.d = i;
    }

    public final void setMinDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f4793a = str;
    }

    public final void setMinSleepScore(int i) {
        this.b = i;
    }

    @NotNull
    public String toString() {
        return "SleepScoreDataModel(minDate=" + this.f4793a + ", minSleepScore=" + this.b + ", maxDate=" + this.c + ", maxSleepScore=" + this.d + HexStringBuilder.COMMENT_END_CHAR;
    }
}
