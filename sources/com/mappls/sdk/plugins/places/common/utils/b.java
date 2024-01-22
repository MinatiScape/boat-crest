package com.mappls.sdk.plugins.places.common.utils;

import com.mappls.sdk.plugins.places.autocomplete.model.MapplsFavoritePlace;
import com.mappls.sdk.services.api.autosuggest.model.ELocation;
/* loaded from: classes10.dex */
public final class b {
    public static String a(MapplsFavoritePlace mapplsFavoritePlace) {
        StringBuilder sb = new StringBuilder();
        if (mapplsFavoritePlace.getPlaceName() != null) {
            sb.append(mapplsFavoritePlace.getPlaceName());
        }
        if (mapplsFavoritePlace.getPlaceName() != null && mapplsFavoritePlace.getPlaceAddress() != null) {
            sb.append(", ");
        }
        if (mapplsFavoritePlace.getPlaceAddress() != null) {
            sb.append(mapplsFavoritePlace.getPlaceAddress());
        }
        return sb.toString();
    }

    public static String a(ELocation eLocation) {
        StringBuilder sb = new StringBuilder();
        String str = eLocation.placeName;
        if (str != null) {
            sb.append(str);
        }
        if (eLocation.placeName != null && eLocation.placeAddress != null) {
            sb.append(", ");
        }
        String str2 = eLocation.placeAddress;
        if (str2 != null) {
            sb.append(str2);
        }
        return sb.toString();
    }
}
