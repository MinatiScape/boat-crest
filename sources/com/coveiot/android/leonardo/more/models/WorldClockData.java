package com.coveiot.android.leonardo.more.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class WorldClockData implements Serializable {
    @NotNull
    private final String countryName;
    @NotNull
    private final String hoursDifference;
    @NotNull
    private final String time;

    public WorldClockData(@NotNull String countryName, @NotNull String hoursDifference, @NotNull String time) {
        Intrinsics.checkNotNullParameter(countryName, "countryName");
        Intrinsics.checkNotNullParameter(hoursDifference, "hoursDifference");
        Intrinsics.checkNotNullParameter(time, "time");
        this.countryName = countryName;
        this.hoursDifference = hoursDifference;
        this.time = time;
    }

    public static /* synthetic */ WorldClockData copy$default(WorldClockData worldClockData, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = worldClockData.countryName;
        }
        if ((i & 2) != 0) {
            str2 = worldClockData.hoursDifference;
        }
        if ((i & 4) != 0) {
            str3 = worldClockData.time;
        }
        return worldClockData.copy(str, str2, str3);
    }

    @NotNull
    public final String component1() {
        return this.countryName;
    }

    @NotNull
    public final String component2() {
        return this.hoursDifference;
    }

    @NotNull
    public final String component3() {
        return this.time;
    }

    @NotNull
    public final WorldClockData copy(@NotNull String countryName, @NotNull String hoursDifference, @NotNull String time) {
        Intrinsics.checkNotNullParameter(countryName, "countryName");
        Intrinsics.checkNotNullParameter(hoursDifference, "hoursDifference");
        Intrinsics.checkNotNullParameter(time, "time");
        return new WorldClockData(countryName, hoursDifference, time);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WorldClockData) {
            WorldClockData worldClockData = (WorldClockData) obj;
            return Intrinsics.areEqual(this.countryName, worldClockData.countryName) && Intrinsics.areEqual(this.hoursDifference, worldClockData.hoursDifference) && Intrinsics.areEqual(this.time, worldClockData.time);
        }
        return false;
    }

    @NotNull
    public final String getCountryName() {
        return this.countryName;
    }

    @NotNull
    public final String getHoursDifference() {
        return this.hoursDifference;
    }

    @NotNull
    public final String getTime() {
        return this.time;
    }

    public int hashCode() {
        return (((this.countryName.hashCode() * 31) + this.hoursDifference.hashCode()) * 31) + this.time.hashCode();
    }

    @NotNull
    public String toString() {
        return "WorldClockData(countryName=" + this.countryName + ", hoursDifference=" + this.hoursDifference + ", time=" + this.time + HexStringBuilder.COMMENT_END_CHAR;
    }
}
