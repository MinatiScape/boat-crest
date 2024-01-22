package com.coveiot.android.navigation.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(tableName = "entity_navigation_recent_search_history")
/* loaded from: classes5.dex */
public final class EntityRecentSearchHistory {
    @ColumnInfo(name = "placeName")
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f5510a;
    @ColumnInfo(name = "placeAddress")
    @NotNull
    public String b;
    @ColumnInfo(name = "distance")
    public double c;
    @ColumnInfo(name = "orderIndex")
    public long d;
    @ColumnInfo(name = "type")
    @NotNull
    public String e;
    @PrimaryKey
    @ColumnInfo(name = "mapplsPin")
    @NotNull
    public String f;
    @ColumnInfo(name = "timeStamp")
    public long g;

    public EntityRecentSearchHistory(@NotNull String placeName, @NotNull String placeAddress, double d, long j, @NotNull String type, @NotNull String mapplsPin, long j2) {
        Intrinsics.checkNotNullParameter(placeName, "placeName");
        Intrinsics.checkNotNullParameter(placeAddress, "placeAddress");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(mapplsPin, "mapplsPin");
        this.f5510a = placeName;
        this.b = placeAddress;
        this.c = d;
        this.d = j;
        this.e = type;
        this.f = mapplsPin;
        this.g = j2;
    }

    @NotNull
    public final String component1() {
        return this.f5510a;
    }

    @NotNull
    public final String component2() {
        return this.b;
    }

    public final double component3() {
        return this.c;
    }

    public final long component4() {
        return this.d;
    }

    @NotNull
    public final String component5() {
        return this.e;
    }

    @NotNull
    public final String component6() {
        return this.f;
    }

    public final long component7() {
        return this.g;
    }

    @NotNull
    public final EntityRecentSearchHistory copy(@NotNull String placeName, @NotNull String placeAddress, double d, long j, @NotNull String type, @NotNull String mapplsPin, long j2) {
        Intrinsics.checkNotNullParameter(placeName, "placeName");
        Intrinsics.checkNotNullParameter(placeAddress, "placeAddress");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(mapplsPin, "mapplsPin");
        return new EntityRecentSearchHistory(placeName, placeAddress, d, j, type, mapplsPin, j2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EntityRecentSearchHistory) {
            EntityRecentSearchHistory entityRecentSearchHistory = (EntityRecentSearchHistory) obj;
            return Intrinsics.areEqual(this.f5510a, entityRecentSearchHistory.f5510a) && Intrinsics.areEqual(this.b, entityRecentSearchHistory.b) && Double.compare(this.c, entityRecentSearchHistory.c) == 0 && this.d == entityRecentSearchHistory.d && Intrinsics.areEqual(this.e, entityRecentSearchHistory.e) && Intrinsics.areEqual(this.f, entityRecentSearchHistory.f) && this.g == entityRecentSearchHistory.g;
        }
        return false;
    }

    public final double getDistance() {
        return this.c;
    }

    @NotNull
    public final String getMapplsPin() {
        return this.f;
    }

    public final long getOrderIndex() {
        return this.d;
    }

    @NotNull
    public final String getPlaceAddress() {
        return this.b;
    }

    @NotNull
    public final String getPlaceName() {
        return this.f5510a;
    }

    public final long getTimeStamp() {
        return this.g;
    }

    @NotNull
    public final String getType() {
        return this.e;
    }

    public int hashCode() {
        return (((((((((((this.f5510a.hashCode() * 31) + this.b.hashCode()) * 31) + Double.hashCode(this.c)) * 31) + Long.hashCode(this.d)) * 31) + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + Long.hashCode(this.g);
    }

    public final void setDistance(double d) {
        this.c = d;
    }

    public final void setMapplsPin(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f = str;
    }

    public final void setOrderIndex(long j) {
        this.d = j;
    }

    public final void setPlaceAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.b = str;
    }

    public final void setPlaceName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f5510a = str;
    }

    public final void setTimeStamp(long j) {
        this.g = j;
    }

    public final void setType(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.e = str;
    }

    @NotNull
    public String toString() {
        return "EntityRecentSearchHistory(placeName=" + this.f5510a + ", placeAddress=" + this.b + ", distance=" + this.c + ", orderIndex=" + this.d + ", type=" + this.e + ", mapplsPin=" + this.f + ", timeStamp=" + this.g + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ EntityRecentSearchHistory(String str, String str2, double d, long j, String str3, String str4, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, d, (i & 8) != 0 ? 0L : j, str3, str4, j2);
    }
}
