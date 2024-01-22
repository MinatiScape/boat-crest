package com.mappls.sdk.plugins.places.autocomplete.ui;

import androidx.annotation.Keep;
import com.mappls.sdk.plugins.places.autocomplete.model.MapplsFavoritePlace;
import com.mappls.sdk.services.api.autosuggest.model.ELocation;
@Keep
/* loaded from: classes10.dex */
public interface PlaceSelectionListener {
    void onCancel();

    void onFavoritePlaceSelected(MapplsFavoritePlace mapplsFavoritePlace);

    void onPlaceSelected(ELocation eLocation);

    void requestForCurrentLocation();
}
