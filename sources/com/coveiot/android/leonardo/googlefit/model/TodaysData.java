package com.coveiot.android.leonardo.googlefit.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class TodaysData {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public List<DailyWalkData> f4848a;
    @Nullable
    public List<DailyHeartRateData> b;
    @Nullable
    public List<DailySleepData> c;
    @Nullable
    public List<DailySPO2Data> d;
    @Nullable
    public List<DailyTemperatureData> e;

    public TodaysData() {
        this(null, null, null, null, null, 31, null);
    }

    public TodaysData(@Nullable List<DailyWalkData> list, @Nullable List<DailyHeartRateData> list2, @Nullable List<DailySleepData> list3, @Nullable List<DailySPO2Data> list4, @Nullable List<DailyTemperatureData> list5) {
        this.f4848a = list;
        this.b = list2;
        this.c = list3;
        this.d = list4;
        this.e = list5;
    }

    public static /* synthetic */ TodaysData copy$default(TodaysData todaysData, List list, List list2, List list3, List list4, List list5, int i, Object obj) {
        List<DailyWalkData> list6 = list;
        if ((i & 1) != 0) {
            list6 = todaysData.f4848a;
        }
        List<DailyHeartRateData> list7 = list2;
        if ((i & 2) != 0) {
            list7 = todaysData.b;
        }
        List list8 = list7;
        List<DailySleepData> list9 = list3;
        if ((i & 4) != 0) {
            list9 = todaysData.c;
        }
        List list10 = list9;
        List<DailySPO2Data> list11 = list4;
        if ((i & 8) != 0) {
            list11 = todaysData.d;
        }
        List list12 = list11;
        List<DailyTemperatureData> list13 = list5;
        if ((i & 16) != 0) {
            list13 = todaysData.e;
        }
        return todaysData.copy(list6, list8, list10, list12, list13);
    }

    @Nullable
    public final List<DailyWalkData> component1() {
        return this.f4848a;
    }

    @Nullable
    public final List<DailyHeartRateData> component2() {
        return this.b;
    }

    @Nullable
    public final List<DailySleepData> component3() {
        return this.c;
    }

    @Nullable
    public final List<DailySPO2Data> component4() {
        return this.d;
    }

    @Nullable
    public final List<DailyTemperatureData> component5() {
        return this.e;
    }

    @NotNull
    public final TodaysData copy(@Nullable List<DailyWalkData> list, @Nullable List<DailyHeartRateData> list2, @Nullable List<DailySleepData> list3, @Nullable List<DailySPO2Data> list4, @Nullable List<DailyTemperatureData> list5) {
        return new TodaysData(list, list2, list3, list4, list5);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TodaysData) {
            TodaysData todaysData = (TodaysData) obj;
            return Intrinsics.areEqual(this.f4848a, todaysData.f4848a) && Intrinsics.areEqual(this.b, todaysData.b) && Intrinsics.areEqual(this.c, todaysData.c) && Intrinsics.areEqual(this.d, todaysData.d) && Intrinsics.areEqual(this.e, todaysData.e);
        }
        return false;
    }

    @Nullable
    public final List<DailyHeartRateData> getDailyHeartRateDataList() {
        return this.b;
    }

    @Nullable
    public final List<DailySPO2Data> getDailySPO2DataList() {
        return this.d;
    }

    @Nullable
    public final List<DailySleepData> getDailySleepDataList() {
        return this.c;
    }

    @Nullable
    public final List<DailyTemperatureData> getDailyTemperatureDataList() {
        return this.e;
    }

    @Nullable
    public final List<DailyWalkData> getDailyWalkDataList() {
        return this.f4848a;
    }

    public int hashCode() {
        List<DailyWalkData> list = this.f4848a;
        int hashCode = (list == null ? 0 : list.hashCode()) * 31;
        List<DailyHeartRateData> list2 = this.b;
        int hashCode2 = (hashCode + (list2 == null ? 0 : list2.hashCode())) * 31;
        List<DailySleepData> list3 = this.c;
        int hashCode3 = (hashCode2 + (list3 == null ? 0 : list3.hashCode())) * 31;
        List<DailySPO2Data> list4 = this.d;
        int hashCode4 = (hashCode3 + (list4 == null ? 0 : list4.hashCode())) * 31;
        List<DailyTemperatureData> list5 = this.e;
        return hashCode4 + (list5 != null ? list5.hashCode() : 0);
    }

    public final void setDailyHeartRateDataList(@Nullable List<DailyHeartRateData> list) {
        this.b = list;
    }

    public final void setDailySPO2DataList(@Nullable List<DailySPO2Data> list) {
        this.d = list;
    }

    public final void setDailySleepDataList(@Nullable List<DailySleepData> list) {
        this.c = list;
    }

    public final void setDailyTemperatureDataList(@Nullable List<DailyTemperatureData> list) {
        this.e = list;
    }

    public final void setDailyWalkDataList(@Nullable List<DailyWalkData> list) {
        this.f4848a = list;
    }

    @NotNull
    public String toString() {
        return "TodaysData(dailyWalkDataList=" + this.f4848a + ", dailyHeartRateDataList=" + this.b + ", dailySleepDataList=" + this.c + ", dailySPO2DataList=" + this.d + ", dailyTemperatureDataList=" + this.e + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ TodaysData(List list, List list2, List list3, List list4, List list5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list, (i & 2) != 0 ? new ArrayList() : list2, (i & 4) != 0 ? null : list3, (i & 8) != 0 ? null : list4, (i & 16) != 0 ? null : list5);
    }
}
