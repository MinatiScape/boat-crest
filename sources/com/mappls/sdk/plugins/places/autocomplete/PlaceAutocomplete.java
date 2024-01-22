package com.mappls.sdk.plugins.places.autocomplete;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.Keep;
import com.google.gson.Gson;
import com.mappls.sdk.plugins.places.autocomplete.data.SearchHistoryDatabase;
import com.mappls.sdk.plugins.places.autocomplete.model.MapplsFavoritePlace;
import com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions;
import com.mappls.sdk.plugins.places.autocomplete.ui.PlaceAutocompleteActivity;
import com.mappls.sdk.plugins.places.common.PlaceConstants;
import com.mappls.sdk.services.api.autosuggest.model.ELocation;
import com.mappls.sdk.services.api.autosuggest.model.SuggestedSearchAtlas;
@Keep
/* loaded from: classes10.dex */
public final class PlaceAutocomplete {

    @Keep
    /* loaded from: classes10.dex */
    public static class IntentBuilder {
        private final Intent intent = new Intent();

        public Intent build(Activity activity) {
            this.intent.setClass(activity, PlaceAutocompleteActivity.class);
            return this.intent;
        }

        public IntentBuilder placeOptions(PlaceOptions placeOptions) {
            this.intent.putExtra(PlaceConstants.PLACE_OPTIONS, placeOptions);
            return this;
        }
    }

    private PlaceAutocomplete() {
    }

    public static void clearRecentHistory(Context context) {
        SearchHistoryDatabase.b(SearchHistoryDatabase.a(context));
    }

    public static MapplsFavoritePlace getFavoritePlace(Intent intent) {
        return (MapplsFavoritePlace) new Gson().fromJson(intent.getStringExtra(PlaceConstants.RETURNING_FAVORITE_DATA), (Class<Object>) MapplsFavoritePlace.class);
    }

    public static ELocation getPlace(Intent intent) {
        return (ELocation) new Gson().fromJson(intent.getStringExtra(PlaceConstants.RETURNING_ELOCATION_DATA), (Class<Object>) ELocation.class);
    }

    public static SuggestedSearchAtlas getSuggestedSearch(Intent intent) {
        return (SuggestedSearchAtlas) new Gson().fromJson(intent.getStringExtra(PlaceConstants.RETURNING_SUGGESTED_DATA), (Class<Object>) SuggestedSearchAtlas.class);
    }

    public static boolean isRequestForCurrentLocation(Intent intent) {
        return intent.getStringExtra(PlaceConstants.REQUEST_CURRENT_LOCATION) != null;
    }
}
