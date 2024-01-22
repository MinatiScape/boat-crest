package com.mappls.sdk.nearby.plugin.model;

import androidx.annotation.Keep;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.nearby.plugin.CategoryCode;
import com.mappls.sdk.services.api.nearby.model.NearbyAtlasResponse;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Keep
/* loaded from: classes10.dex */
public final class MapplsNearbyResponse {
    @NotNull
    private final NearbyAtlasResponse nearbyAtlasResponse;
    @Nullable
    private final LatLng refLocation;
    @Nullable
    private final String refMapplsPin;
    @NotNull
    private final List<CategoryCode> selectedCategory;

    /* JADX WARN: Multi-variable type inference failed */
    public MapplsNearbyResponse(@NotNull NearbyAtlasResponse nearbyAtlasResponse, @NotNull List<? extends CategoryCode> selectedCategory, @Nullable LatLng latLng, @Nullable String str) {
        Intrinsics.checkNotNullParameter(nearbyAtlasResponse, "nearbyAtlasResponse");
        Intrinsics.checkNotNullParameter(selectedCategory, "selectedCategory");
        this.nearbyAtlasResponse = nearbyAtlasResponse;
        this.selectedCategory = selectedCategory;
        this.refLocation = latLng;
        this.refMapplsPin = str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MapplsNearbyResponse copy$default(MapplsNearbyResponse mapplsNearbyResponse, NearbyAtlasResponse nearbyAtlasResponse, List list, LatLng latLng, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            nearbyAtlasResponse = mapplsNearbyResponse.nearbyAtlasResponse;
        }
        if ((i & 2) != 0) {
            list = mapplsNearbyResponse.selectedCategory;
        }
        if ((i & 4) != 0) {
            latLng = mapplsNearbyResponse.refLocation;
        }
        if ((i & 8) != 0) {
            str = mapplsNearbyResponse.refMapplsPin;
        }
        return mapplsNearbyResponse.copy(nearbyAtlasResponse, list, latLng, str);
    }

    @NotNull
    public final NearbyAtlasResponse component1() {
        return this.nearbyAtlasResponse;
    }

    @NotNull
    public final List<CategoryCode> component2() {
        return this.selectedCategory;
    }

    @Nullable
    public final LatLng component3() {
        return this.refLocation;
    }

    @Nullable
    public final String component4() {
        return this.refMapplsPin;
    }

    @NotNull
    public final MapplsNearbyResponse copy(@NotNull NearbyAtlasResponse nearbyAtlasResponse, @NotNull List<? extends CategoryCode> selectedCategory, @Nullable LatLng latLng, @Nullable String str) {
        Intrinsics.checkNotNullParameter(nearbyAtlasResponse, "nearbyAtlasResponse");
        Intrinsics.checkNotNullParameter(selectedCategory, "selectedCategory");
        return new MapplsNearbyResponse(nearbyAtlasResponse, selectedCategory, latLng, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MapplsNearbyResponse) {
            MapplsNearbyResponse mapplsNearbyResponse = (MapplsNearbyResponse) obj;
            return Intrinsics.areEqual(this.nearbyAtlasResponse, mapplsNearbyResponse.nearbyAtlasResponse) && Intrinsics.areEqual(this.selectedCategory, mapplsNearbyResponse.selectedCategory) && Intrinsics.areEqual(this.refLocation, mapplsNearbyResponse.refLocation) && Intrinsics.areEqual(this.refMapplsPin, mapplsNearbyResponse.refMapplsPin);
        }
        return false;
    }

    @NotNull
    public final NearbyAtlasResponse getNearbyAtlasResponse() {
        return this.nearbyAtlasResponse;
    }

    @Nullable
    public final LatLng getRefLocation() {
        return this.refLocation;
    }

    @Nullable
    public final String getRefMapplsPin() {
        return this.refMapplsPin;
    }

    @NotNull
    public final List<CategoryCode> getSelectedCategory() {
        return this.selectedCategory;
    }

    public int hashCode() {
        int hashCode = (this.selectedCategory.hashCode() + (this.nearbyAtlasResponse.hashCode() * 31)) * 31;
        LatLng latLng = this.refLocation;
        int hashCode2 = (hashCode + (latLng == null ? 0 : latLng.hashCode())) * 31;
        String str = this.refMapplsPin;
        return hashCode2 + (str != null ? str.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "MapplsNearbyResponse(nearbyAtlasResponse=" + this.nearbyAtlasResponse + ", selectedCategory=" + this.selectedCategory + ", refLocation=" + this.refLocation + ", refMapplsPin=" + this.refMapplsPin + HexStringBuilder.COMMENT_END_CHAR;
    }
}
