package com.coveiot.android.leonardo.googlefit.model;

import com.coveiot.covedb.heartrate.EntityHourlyHeartRateData;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class DailyHeartRateData {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public List<EntityHourlyHeartRateData> f4842a;
    @Nullable
    public Calendar b;

    public DailyHeartRateData() {
        this(null, null, 3, null);
    }

    public DailyHeartRateData(@Nullable List<EntityHourlyHeartRateData> list, @Nullable Calendar calendar) {
        this.f4842a = list;
        this.b = calendar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DailyHeartRateData copy$default(DailyHeartRateData dailyHeartRateData, List list, Calendar calendar, int i, Object obj) {
        if ((i & 1) != 0) {
            list = dailyHeartRateData.f4842a;
        }
        if ((i & 2) != 0) {
            calendar = dailyHeartRateData.b;
        }
        return dailyHeartRateData.copy(list, calendar);
    }

    @Nullable
    public final List<EntityHourlyHeartRateData> component1() {
        return this.f4842a;
    }

    @Nullable
    public final Calendar component2() {
        return this.b;
    }

    @NotNull
    public final DailyHeartRateData copy(@Nullable List<EntityHourlyHeartRateData> list, @Nullable Calendar calendar) {
        return new DailyHeartRateData(list, calendar);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DailyHeartRateData) {
            DailyHeartRateData dailyHeartRateData = (DailyHeartRateData) obj;
            return Intrinsics.areEqual(this.f4842a, dailyHeartRateData.f4842a) && Intrinsics.areEqual(this.b, dailyHeartRateData.b);
        }
        return false;
    }

    @Nullable
    public final Calendar getDate() {
        return this.b;
    }

    @Nullable
    public final List<EntityHourlyHeartRateData> getHourlyHeartRateDataList() {
        return this.f4842a;
    }

    public int hashCode() {
        List<EntityHourlyHeartRateData> list = this.f4842a;
        int hashCode = (list == null ? 0 : list.hashCode()) * 31;
        Calendar calendar = this.b;
        return hashCode + (calendar != null ? calendar.hashCode() : 0);
    }

    public final void setDate(@Nullable Calendar calendar) {
        this.b = calendar;
    }

    public final void setHourlyHeartRateDataList(@Nullable List<EntityHourlyHeartRateData> list) {
        this.f4842a = list;
    }

    @NotNull
    public String toString() {
        return "DailyHeartRateData(hourlyHeartRateDataList=" + this.f4842a + ", date=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ DailyHeartRateData(List list, Calendar calendar, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list, (i & 2) != 0 ? null : calendar);
    }
}
