package com.coveiot.android.activitymodes.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class WeekInfo implements Serializable {
    @NotNull
    private ArrayList<DayInfo> dayList;
    @Nullable
    private ArrayList<String> introText;
    @NotNull
    private String name;
    @NotNull
    private String shortDesc;
    private int weekNumber;

    public WeekInfo(int i, @NotNull String name, @NotNull String shortDesc, @Nullable ArrayList<String> arrayList, @NotNull ArrayList<DayInfo> dayList) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(shortDesc, "shortDesc");
        Intrinsics.checkNotNullParameter(dayList, "dayList");
        this.weekNumber = i;
        this.name = name;
        this.shortDesc = shortDesc;
        this.introText = arrayList;
        this.dayList = dayList;
    }

    public static /* synthetic */ WeekInfo copy$default(WeekInfo weekInfo, int i, String str, String str2, ArrayList arrayList, ArrayList arrayList2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = weekInfo.weekNumber;
        }
        if ((i2 & 2) != 0) {
            str = weekInfo.name;
        }
        String str3 = str;
        if ((i2 & 4) != 0) {
            str2 = weekInfo.shortDesc;
        }
        String str4 = str2;
        ArrayList<String> arrayList3 = arrayList;
        if ((i2 & 8) != 0) {
            arrayList3 = weekInfo.introText;
        }
        ArrayList arrayList4 = arrayList3;
        ArrayList<DayInfo> arrayList5 = arrayList2;
        if ((i2 & 16) != 0) {
            arrayList5 = weekInfo.dayList;
        }
        return weekInfo.copy(i, str3, str4, arrayList4, arrayList5);
    }

    public final int component1() {
        return this.weekNumber;
    }

    @NotNull
    public final String component2() {
        return this.name;
    }

    @NotNull
    public final String component3() {
        return this.shortDesc;
    }

    @Nullable
    public final ArrayList<String> component4() {
        return this.introText;
    }

    @NotNull
    public final ArrayList<DayInfo> component5() {
        return this.dayList;
    }

    @NotNull
    public final WeekInfo copy(int i, @NotNull String name, @NotNull String shortDesc, @Nullable ArrayList<String> arrayList, @NotNull ArrayList<DayInfo> dayList) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(shortDesc, "shortDesc");
        Intrinsics.checkNotNullParameter(dayList, "dayList");
        return new WeekInfo(i, name, shortDesc, arrayList, dayList);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WeekInfo) {
            WeekInfo weekInfo = (WeekInfo) obj;
            return this.weekNumber == weekInfo.weekNumber && Intrinsics.areEqual(this.name, weekInfo.name) && Intrinsics.areEqual(this.shortDesc, weekInfo.shortDesc) && Intrinsics.areEqual(this.introText, weekInfo.introText) && Intrinsics.areEqual(this.dayList, weekInfo.dayList);
        }
        return false;
    }

    @NotNull
    public final ArrayList<DayInfo> getDayList() {
        return this.dayList;
    }

    @Nullable
    public final ArrayList<String> getIntroText() {
        return this.introText;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getShortDesc() {
        return this.shortDesc;
    }

    public final int getWeekNumber() {
        return this.weekNumber;
    }

    public int hashCode() {
        int hashCode = ((((Integer.hashCode(this.weekNumber) * 31) + this.name.hashCode()) * 31) + this.shortDesc.hashCode()) * 31;
        ArrayList<String> arrayList = this.introText;
        return ((hashCode + (arrayList == null ? 0 : arrayList.hashCode())) * 31) + this.dayList.hashCode();
    }

    public final void setDayList(@NotNull ArrayList<DayInfo> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.dayList = arrayList;
    }

    public final void setIntroText(@Nullable ArrayList<String> arrayList) {
        this.introText = arrayList;
    }

    public final void setName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final void setShortDesc(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.shortDesc = str;
    }

    public final void setWeekNumber(int i) {
        this.weekNumber = i;
    }

    @NotNull
    public String toString() {
        return "WeekInfo(weekNumber=" + this.weekNumber + ", name=" + this.name + ", shortDesc=" + this.shortDesc + ", introText=" + this.introText + ", dayList=" + this.dayList + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ WeekInfo(int i, String str, String str2, ArrayList arrayList, ArrayList arrayList2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, str2, (i2 & 8) != 0 ? null : arrayList, (i2 & 16) != 0 ? new ArrayList() : arrayList2);
    }
}
