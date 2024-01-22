package com.coveiot.android.leonardo.googlefit.model;

import com.coveiot.covedb.walk.entities.HourlyWalkData;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class DailyWalkData {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public List<HourlyWalkData> f4846a;
    @Nullable
    public Calendar b;

    public DailyWalkData() {
        this(null, null, 3, null);
    }

    public DailyWalkData(@Nullable List<HourlyWalkData> list, @Nullable Calendar calendar) {
        this.f4846a = list;
        this.b = calendar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DailyWalkData copy$default(DailyWalkData dailyWalkData, List list, Calendar calendar, int i, Object obj) {
        if ((i & 1) != 0) {
            list = dailyWalkData.f4846a;
        }
        if ((i & 2) != 0) {
            calendar = dailyWalkData.b;
        }
        return dailyWalkData.copy(list, calendar);
    }

    @Nullable
    public final List<HourlyWalkData> component1() {
        return this.f4846a;
    }

    @Nullable
    public final Calendar component2() {
        return this.b;
    }

    @NotNull
    public final DailyWalkData copy(@Nullable List<HourlyWalkData> list, @Nullable Calendar calendar) {
        return new DailyWalkData(list, calendar);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DailyWalkData) {
            DailyWalkData dailyWalkData = (DailyWalkData) obj;
            return Intrinsics.areEqual(this.f4846a, dailyWalkData.f4846a) && Intrinsics.areEqual(this.b, dailyWalkData.b);
        }
        return false;
    }

    @Nullable
    public final Calendar getDate() {
        return this.b;
    }

    @Nullable
    public final List<HourlyWalkData> getHourlyWalkDataList() {
        return this.f4846a;
    }

    public int hashCode() {
        List<HourlyWalkData> list = this.f4846a;
        int hashCode = (list == null ? 0 : list.hashCode()) * 31;
        Calendar calendar = this.b;
        return hashCode + (calendar != null ? calendar.hashCode() : 0);
    }

    public final void setDate(@Nullable Calendar calendar) {
        this.b = calendar;
    }

    public final void setHourlyWalkDataList(@Nullable List<HourlyWalkData> list) {
        this.f4846a = list;
    }

    @NotNull
    public String toString() {
        return "DailyWalkData(hourlyWalkDataList=" + this.f4846a + ", date=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ DailyWalkData(List list, Calendar calendar, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list, (i & 2) != 0 ? null : calendar);
    }
}
