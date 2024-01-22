package com.coveiot.android.activitymodes.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class DayInfo implements Serializable {
    @Nullable
    private ArrayList<ActivityInfo> activitylist;
    @Nullable
    private String date;
    private int dayNumber;
    @Nullable
    private String name;

    public DayInfo(int i, @Nullable String str, @Nullable String str2, @Nullable ArrayList<ActivityInfo> arrayList) {
        this.dayNumber = i;
        this.date = str;
        this.name = str2;
        this.activitylist = arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DayInfo copy$default(DayInfo dayInfo, int i, String str, String str2, ArrayList arrayList, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = dayInfo.dayNumber;
        }
        if ((i2 & 2) != 0) {
            str = dayInfo.date;
        }
        if ((i2 & 4) != 0) {
            str2 = dayInfo.name;
        }
        if ((i2 & 8) != 0) {
            arrayList = dayInfo.activitylist;
        }
        return dayInfo.copy(i, str, str2, arrayList);
    }

    public final int component1() {
        return this.dayNumber;
    }

    @Nullable
    public final String component2() {
        return this.date;
    }

    @Nullable
    public final String component3() {
        return this.name;
    }

    @Nullable
    public final ArrayList<ActivityInfo> component4() {
        return this.activitylist;
    }

    @NotNull
    public final DayInfo copy(int i, @Nullable String str, @Nullable String str2, @Nullable ArrayList<ActivityInfo> arrayList) {
        return new DayInfo(i, str, str2, arrayList);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DayInfo) {
            DayInfo dayInfo = (DayInfo) obj;
            return this.dayNumber == dayInfo.dayNumber && Intrinsics.areEqual(this.date, dayInfo.date) && Intrinsics.areEqual(this.name, dayInfo.name) && Intrinsics.areEqual(this.activitylist, dayInfo.activitylist);
        }
        return false;
    }

    @Nullable
    public final ArrayList<ActivityInfo> getActivitylist() {
        return this.activitylist;
    }

    @Nullable
    public final String getDate() {
        return this.date;
    }

    public final int getDayNumber() {
        return this.dayNumber;
    }

    @Nullable
    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.dayNumber) * 31;
        String str = this.date;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.name;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        ArrayList<ActivityInfo> arrayList = this.activitylist;
        return hashCode3 + (arrayList != null ? arrayList.hashCode() : 0);
    }

    public final void setActivitylist(@Nullable ArrayList<ActivityInfo> arrayList) {
        this.activitylist = arrayList;
    }

    public final void setDate(@Nullable String str) {
        this.date = str;
    }

    public final void setDayNumber(int i) {
        this.dayNumber = i;
    }

    public final void setName(@Nullable String str) {
        this.name = str;
    }

    @NotNull
    public String toString() {
        return "DayInfo(dayNumber=" + this.dayNumber + ", date=" + this.date + ", name=" + this.name + ", activitylist=" + this.activitylist + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ DayInfo(int i, String str, String str2, ArrayList arrayList, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? null : str, (i2 & 4) != 0 ? null : str2, (i2 & 8) != 0 ? null : arrayList);
    }
}
