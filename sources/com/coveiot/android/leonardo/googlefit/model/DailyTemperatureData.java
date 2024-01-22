package com.coveiot.android.leonardo.googlefit.model;

import com.coveiot.covedb.temperature.entity.HourlyTemperature;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class DailyTemperatureData {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public List<HourlyTemperature> f4845a;
    @Nullable
    public Calendar b;

    public DailyTemperatureData() {
        this(null, null, 3, null);
    }

    public DailyTemperatureData(@Nullable List<HourlyTemperature> list, @Nullable Calendar calendar) {
        this.f4845a = list;
        this.b = calendar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DailyTemperatureData copy$default(DailyTemperatureData dailyTemperatureData, List list, Calendar calendar, int i, Object obj) {
        if ((i & 1) != 0) {
            list = dailyTemperatureData.f4845a;
        }
        if ((i & 2) != 0) {
            calendar = dailyTemperatureData.b;
        }
        return dailyTemperatureData.copy(list, calendar);
    }

    @Nullable
    public final List<HourlyTemperature> component1() {
        return this.f4845a;
    }

    @Nullable
    public final Calendar component2() {
        return this.b;
    }

    @NotNull
    public final DailyTemperatureData copy(@Nullable List<HourlyTemperature> list, @Nullable Calendar calendar) {
        return new DailyTemperatureData(list, calendar);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DailyTemperatureData) {
            DailyTemperatureData dailyTemperatureData = (DailyTemperatureData) obj;
            return Intrinsics.areEqual(this.f4845a, dailyTemperatureData.f4845a) && Intrinsics.areEqual(this.b, dailyTemperatureData.b);
        }
        return false;
    }

    @Nullable
    public final Calendar getDate() {
        return this.b;
    }

    @Nullable
    public final List<HourlyTemperature> getHourlyTemperatureDataList() {
        return this.f4845a;
    }

    public int hashCode() {
        List<HourlyTemperature> list = this.f4845a;
        int hashCode = (list == null ? 0 : list.hashCode()) * 31;
        Calendar calendar = this.b;
        return hashCode + (calendar != null ? calendar.hashCode() : 0);
    }

    public final void setDate(@Nullable Calendar calendar) {
        this.b = calendar;
    }

    public final void setHourlyTemperatureDataList(@Nullable List<HourlyTemperature> list) {
        this.f4845a = list;
    }

    @NotNull
    public String toString() {
        return "DailyTemperatureData(hourlyTemperatureDataList=" + this.f4845a + ", date=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ DailyTemperatureData(List list, Calendar calendar, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list, (i & 2) != 0 ? null : calendar);
    }
}
