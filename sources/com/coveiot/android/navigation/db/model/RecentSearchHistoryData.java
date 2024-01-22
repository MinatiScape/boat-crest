package com.coveiot.android.navigation.db.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class RecentSearchHistoryData implements Serializable {
    private final double distance;
    @NotNull
    private final String mapplsPin;
    private final long orderIndex;
    @NotNull
    private final String placeAddress;
    @NotNull
    private final String placeName;
    @Nullable
    private final Long timeStamp;
    @NotNull
    private final String type;

    public RecentSearchHistoryData(@NotNull String placeName, @NotNull String placeAddress, double d, long j, @NotNull String type, @NotNull String mapplsPin, @Nullable Long l) {
        Intrinsics.checkNotNullParameter(placeName, "placeName");
        Intrinsics.checkNotNullParameter(placeAddress, "placeAddress");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(mapplsPin, "mapplsPin");
        this.placeName = placeName;
        this.placeAddress = placeAddress;
        this.distance = d;
        this.orderIndex = j;
        this.type = type;
        this.mapplsPin = mapplsPin;
        this.timeStamp = l;
    }

    @NotNull
    public final String component1() {
        return this.placeName;
    }

    @NotNull
    public final String component2() {
        return this.placeAddress;
    }

    public final double component3() {
        return this.distance;
    }

    public final long component4() {
        return this.orderIndex;
    }

    @NotNull
    public final String component5() {
        return this.type;
    }

    @NotNull
    public final String component6() {
        return this.mapplsPin;
    }

    @Nullable
    public final Long component7() {
        return this.timeStamp;
    }

    @NotNull
    public final RecentSearchHistoryData copy(@NotNull String placeName, @NotNull String placeAddress, double d, long j, @NotNull String type, @NotNull String mapplsPin, @Nullable Long l) {
        Intrinsics.checkNotNullParameter(placeName, "placeName");
        Intrinsics.checkNotNullParameter(placeAddress, "placeAddress");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(mapplsPin, "mapplsPin");
        return new RecentSearchHistoryData(placeName, placeAddress, d, j, type, mapplsPin, l);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RecentSearchHistoryData) {
            RecentSearchHistoryData recentSearchHistoryData = (RecentSearchHistoryData) obj;
            return Intrinsics.areEqual(this.placeName, recentSearchHistoryData.placeName) && Intrinsics.areEqual(this.placeAddress, recentSearchHistoryData.placeAddress) && Double.compare(this.distance, recentSearchHistoryData.distance) == 0 && this.orderIndex == recentSearchHistoryData.orderIndex && Intrinsics.areEqual(this.type, recentSearchHistoryData.type) && Intrinsics.areEqual(this.mapplsPin, recentSearchHistoryData.mapplsPin) && Intrinsics.areEqual(this.timeStamp, recentSearchHistoryData.timeStamp);
        }
        return false;
    }

    public final double getDistance() {
        return this.distance;
    }

    @NotNull
    public final String getMapplsPin() {
        return this.mapplsPin;
    }

    public final long getOrderIndex() {
        return this.orderIndex;
    }

    @NotNull
    public final String getPlaceAddress() {
        return this.placeAddress;
    }

    @NotNull
    public final String getPlaceName() {
        return this.placeName;
    }

    @Nullable
    public final Long getTimeStamp() {
        return this.timeStamp;
    }

    @NotNull
    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        int hashCode = ((((((((((this.placeName.hashCode() * 31) + this.placeAddress.hashCode()) * 31) + Double.hashCode(this.distance)) * 31) + Long.hashCode(this.orderIndex)) * 31) + this.type.hashCode()) * 31) + this.mapplsPin.hashCode()) * 31;
        Long l = this.timeStamp;
        return hashCode + (l == null ? 0 : l.hashCode());
    }

    @NotNull
    public String toString() {
        return "RecentSearchHistoryData(placeName=" + this.placeName + ", placeAddress=" + this.placeAddress + ", distance=" + this.distance + ", orderIndex=" + this.orderIndex + ", type=" + this.type + ", mapplsPin=" + this.mapplsPin + ", timeStamp=" + this.timeStamp + HexStringBuilder.COMMENT_END_CHAR;
    }
}
