package com.coveiot.android.leonardo.googlefit.model;

import com.coveiot.covedb.spo2.entity.EntityHourlySpo2;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class DailySPO2Data {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public List<EntityHourlySpo2> f4843a;
    @Nullable
    public Calendar b;

    public DailySPO2Data() {
        this(null, null, 3, null);
    }

    public DailySPO2Data(@Nullable List<EntityHourlySpo2> list, @Nullable Calendar calendar) {
        this.f4843a = list;
        this.b = calendar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DailySPO2Data copy$default(DailySPO2Data dailySPO2Data, List list, Calendar calendar, int i, Object obj) {
        if ((i & 1) != 0) {
            list = dailySPO2Data.f4843a;
        }
        if ((i & 2) != 0) {
            calendar = dailySPO2Data.b;
        }
        return dailySPO2Data.copy(list, calendar);
    }

    @Nullable
    public final List<EntityHourlySpo2> component1() {
        return this.f4843a;
    }

    @Nullable
    public final Calendar component2() {
        return this.b;
    }

    @NotNull
    public final DailySPO2Data copy(@Nullable List<EntityHourlySpo2> list, @Nullable Calendar calendar) {
        return new DailySPO2Data(list, calendar);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DailySPO2Data) {
            DailySPO2Data dailySPO2Data = (DailySPO2Data) obj;
            return Intrinsics.areEqual(this.f4843a, dailySPO2Data.f4843a) && Intrinsics.areEqual(this.b, dailySPO2Data.b);
        }
        return false;
    }

    @Nullable
    public final Calendar getDate() {
        return this.b;
    }

    @Nullable
    public final List<EntityHourlySpo2> getHourlySPO2DataList() {
        return this.f4843a;
    }

    public int hashCode() {
        List<EntityHourlySpo2> list = this.f4843a;
        int hashCode = (list == null ? 0 : list.hashCode()) * 31;
        Calendar calendar = this.b;
        return hashCode + (calendar != null ? calendar.hashCode() : 0);
    }

    public final void setDate(@Nullable Calendar calendar) {
        this.b = calendar;
    }

    public final void setHourlySPO2DataList(@Nullable List<EntityHourlySpo2> list) {
        this.f4843a = list;
    }

    @NotNull
    public String toString() {
        return "DailySPO2Data(hourlySPO2DataList=" + this.f4843a + ", date=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ DailySPO2Data(List list, Calendar calendar, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list, (i & 2) != 0 ? null : calendar);
    }
}
