package com.coveiot.android.leonardo.googlefit.model;

import com.coveiot.android.sleepalgorithm.filtering.SleepDataModel;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Calendar;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class DailySleepData {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public SleepDataModel f4844a;
    @Nullable
    public Calendar b;

    public DailySleepData() {
        this(null, null, 3, null);
    }

    public DailySleepData(@Nullable SleepDataModel sleepDataModel, @Nullable Calendar calendar) {
        this.f4844a = sleepDataModel;
        this.b = calendar;
    }

    public static /* synthetic */ DailySleepData copy$default(DailySleepData dailySleepData, SleepDataModel sleepDataModel, Calendar calendar, int i, Object obj) {
        if ((i & 1) != 0) {
            sleepDataModel = dailySleepData.f4844a;
        }
        if ((i & 2) != 0) {
            calendar = dailySleepData.b;
        }
        return dailySleepData.copy(sleepDataModel, calendar);
    }

    @Nullable
    public final SleepDataModel component1() {
        return this.f4844a;
    }

    @Nullable
    public final Calendar component2() {
        return this.b;
    }

    @NotNull
    public final DailySleepData copy(@Nullable SleepDataModel sleepDataModel, @Nullable Calendar calendar) {
        return new DailySleepData(sleepDataModel, calendar);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DailySleepData) {
            DailySleepData dailySleepData = (DailySleepData) obj;
            return Intrinsics.areEqual(this.f4844a, dailySleepData.f4844a) && Intrinsics.areEqual(this.b, dailySleepData.b);
        }
        return false;
    }

    @Nullable
    public final Calendar getDate() {
        return this.b;
    }

    @Nullable
    public final SleepDataModel getSleepDataModel() {
        return this.f4844a;
    }

    public int hashCode() {
        SleepDataModel sleepDataModel = this.f4844a;
        int hashCode = (sleepDataModel == null ? 0 : sleepDataModel.hashCode()) * 31;
        Calendar calendar = this.b;
        return hashCode + (calendar != null ? calendar.hashCode() : 0);
    }

    public final void setDate(@Nullable Calendar calendar) {
        this.b = calendar;
    }

    public final void setSleepDataModel(@Nullable SleepDataModel sleepDataModel) {
        this.f4844a = sleepDataModel;
    }

    @NotNull
    public String toString() {
        return "DailySleepData(sleepDataModel=" + this.f4844a + ", date=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ DailySleepData(SleepDataModel sleepDataModel, Calendar calendar, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : sleepDataModel, (i & 2) != 0 ? null : calendar);
    }
}
