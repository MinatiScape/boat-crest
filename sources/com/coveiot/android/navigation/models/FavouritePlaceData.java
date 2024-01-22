package com.coveiot.android.navigation.models;

import com.coveiot.coveaccess.navigation.model.FavouritePlace;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FavouritePlaceData implements Serializable {
    @Nullable
    private String fullAddress;
    @Nullable
    private String id;
    @Nullable
    private String label;
    @Nullable
    private FavouritePlace.Location location;
    @Nullable
    private String mapApi;
    @Nullable
    private String mapplsPin;
    @Nullable
    private String name;
    @Nullable
    private Integer sortIndex;

    public FavouritePlaceData() {
        this(null, null, null, null, null, null, null, null, 255, null);
    }

    public FavouritePlaceData(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Integer num, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable FavouritePlace.Location location) {
        this.id = str;
        this.mapApi = str2;
        this.mapplsPin = str3;
        this.sortIndex = num;
        this.label = str4;
        this.name = str5;
        this.fullAddress = str6;
        this.location = location;
    }

    @Nullable
    public final String component1() {
        return this.id;
    }

    @Nullable
    public final String component2() {
        return this.mapApi;
    }

    @Nullable
    public final String component3() {
        return this.mapplsPin;
    }

    @Nullable
    public final Integer component4() {
        return this.sortIndex;
    }

    @Nullable
    public final String component5() {
        return this.label;
    }

    @Nullable
    public final String component6() {
        return this.name;
    }

    @Nullable
    public final String component7() {
        return this.fullAddress;
    }

    @Nullable
    public final FavouritePlace.Location component8() {
        return this.location;
    }

    @NotNull
    public final FavouritePlaceData copy(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Integer num, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable FavouritePlace.Location location) {
        return new FavouritePlaceData(str, str2, str3, num, str4, str5, str6, location);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FavouritePlaceData) {
            FavouritePlaceData favouritePlaceData = (FavouritePlaceData) obj;
            return Intrinsics.areEqual(this.id, favouritePlaceData.id) && Intrinsics.areEqual(this.mapApi, favouritePlaceData.mapApi) && Intrinsics.areEqual(this.mapplsPin, favouritePlaceData.mapplsPin) && Intrinsics.areEqual(this.sortIndex, favouritePlaceData.sortIndex) && Intrinsics.areEqual(this.label, favouritePlaceData.label) && Intrinsics.areEqual(this.name, favouritePlaceData.name) && Intrinsics.areEqual(this.fullAddress, favouritePlaceData.fullAddress) && Intrinsics.areEqual(this.location, favouritePlaceData.location);
        }
        return false;
    }

    @Nullable
    public final String getFullAddress() {
        return this.fullAddress;
    }

    @Nullable
    public final String getId() {
        return this.id;
    }

    @Nullable
    public final String getLabel() {
        return this.label;
    }

    @Nullable
    public final FavouritePlace.Location getLocation() {
        return this.location;
    }

    @Nullable
    public final String getMapApi() {
        return this.mapApi;
    }

    @Nullable
    public final String getMapplsPin() {
        return this.mapplsPin;
    }

    @Nullable
    public final String getName() {
        return this.name;
    }

    @Nullable
    public final Integer getSortIndex() {
        return this.sortIndex;
    }

    public int hashCode() {
        String str = this.id;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.mapApi;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.mapplsPin;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Integer num = this.sortIndex;
        int hashCode4 = (hashCode3 + (num == null ? 0 : num.hashCode())) * 31;
        String str4 = this.label;
        int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.name;
        int hashCode6 = (hashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.fullAddress;
        int hashCode7 = (hashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
        FavouritePlace.Location location = this.location;
        return hashCode7 + (location != null ? location.hashCode() : 0);
    }

    public final void setFullAddress(@Nullable String str) {
        this.fullAddress = str;
    }

    public final void setId(@Nullable String str) {
        this.id = str;
    }

    public final void setLabel(@Nullable String str) {
        this.label = str;
    }

    public final void setLocation(@Nullable FavouritePlace.Location location) {
        this.location = location;
    }

    public final void setMapApi(@Nullable String str) {
        this.mapApi = str;
    }

    public final void setMapplsPin(@Nullable String str) {
        this.mapplsPin = str;
    }

    public final void setName(@Nullable String str) {
        this.name = str;
    }

    public final void setSortIndex(@Nullable Integer num) {
        this.sortIndex = num;
    }

    @NotNull
    public String toString() {
        return "FavouritePlaceData(id=" + this.id + ", mapApi=" + this.mapApi + ", mapplsPin=" + this.mapplsPin + ", sortIndex=" + this.sortIndex + ", label=" + this.label + ", name=" + this.name + ", fullAddress=" + this.fullAddress + ", location=" + this.location + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ FavouritePlaceData(String str, String str2, String str3, Integer num, String str4, String str5, String str6, FavouritePlace.Location location, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : num, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? null : str5, (i & 64) != 0 ? null : str6, (i & 128) == 0 ? location : null);
    }
}
