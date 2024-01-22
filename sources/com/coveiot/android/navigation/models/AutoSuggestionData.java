package com.coveiot.android.navigation.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class AutoSuggestionData {
    private final double distance;
    @NotNull
    private final String mapplsPin;
    private final long orderIndex;
    @NotNull
    private final String placeAddress;
    @NotNull
    private final String placeName;
    @NotNull
    private final String type;

    public AutoSuggestionData(@NotNull String placeName, @NotNull String placeAddress, double d, long j, @NotNull String type, @NotNull String mapplsPin) {
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

    @NotNull
    public final AutoSuggestionData copy(@NotNull String placeName, @NotNull String placeAddress, double d, long j, @NotNull String type, @NotNull String mapplsPin) {
        Intrinsics.checkNotNullParameter(placeName, "placeName");
        Intrinsics.checkNotNullParameter(placeAddress, "placeAddress");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(mapplsPin, "mapplsPin");
        return new AutoSuggestionData(placeName, placeAddress, d, j, type, mapplsPin);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AutoSuggestionData) {
            AutoSuggestionData autoSuggestionData = (AutoSuggestionData) obj;
            return Intrinsics.areEqual(this.placeName, autoSuggestionData.placeName) && Intrinsics.areEqual(this.placeAddress, autoSuggestionData.placeAddress) && Double.compare(this.distance, autoSuggestionData.distance) == 0 && this.orderIndex == autoSuggestionData.orderIndex && Intrinsics.areEqual(this.type, autoSuggestionData.type) && Intrinsics.areEqual(this.mapplsPin, autoSuggestionData.mapplsPin);
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

    @NotNull
    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        return (((((((((this.placeName.hashCode() * 31) + this.placeAddress.hashCode()) * 31) + Double.hashCode(this.distance)) * 31) + Long.hashCode(this.orderIndex)) * 31) + this.type.hashCode()) * 31) + this.mapplsPin.hashCode();
    }

    @NotNull
    public String toString() {
        return "AutoSuggestionData(placeName=" + this.placeName + ", placeAddress=" + this.placeAddress + ", distance=" + this.distance + ", orderIndex=" + this.orderIndex + ", type=" + this.type + ", mapplsPin=" + this.mapplsPin + HexStringBuilder.COMMENT_END_CHAR;
    }
}
