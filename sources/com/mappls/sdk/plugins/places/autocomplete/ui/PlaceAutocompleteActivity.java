package com.mappls.sdk.plugins.places.autocomplete.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.mappls.sdk.plugins.places.R;
import com.mappls.sdk.plugins.places.autocomplete.model.MapplsFavoritePlace;
import com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions;
import com.mappls.sdk.plugins.places.common.PlaceConstants;
import com.mappls.sdk.services.api.autosuggest.model.ELocation;
import com.mappls.sdk.services.api.autosuggest.model.SuggestedSearchAtlas;
@Keep
/* loaded from: classes10.dex */
public class PlaceAutocompleteActivity extends AppCompatActivity implements PlaceSelectionListener, SuggestedSearchSelectionListener {
    @Override // com.mappls.sdk.plugins.places.autocomplete.ui.PlaceSelectionListener
    public void onCancel() {
        setResult(0);
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.mappls_search_activity_autocomplete);
        if (bundle == null) {
            PlaceOptions placeOptions = (PlaceOptions) getIntent().getParcelableExtra(PlaceConstants.PLACE_OPTIONS);
            PlaceAutocompleteFragment newInstance = placeOptions != null ? PlaceAutocompleteFragment.newInstance(placeOptions) : PlaceAutocompleteFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, newInstance, PlaceAutocompleteFragment.TAG).commit();
            newInstance.setOnPlaceSelectedListener(this);
            newInstance.setSuggestedSearchSelectionListener(this);
        }
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.ui.PlaceSelectionListener
    public void onFavoritePlaceSelected(MapplsFavoritePlace mapplsFavoritePlace) {
        String json = new Gson().toJson(mapplsFavoritePlace);
        Intent intent = new Intent();
        intent.putExtra(PlaceConstants.RETURNING_FAVORITE_DATA, json);
        setResult(-1, intent);
        finish();
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.ui.PlaceSelectionListener
    public void onPlaceSelected(ELocation eLocation) {
        String json = new Gson().toJson(eLocation);
        Intent intent = new Intent();
        intent.putExtra(PlaceConstants.RETURNING_ELOCATION_DATA, json);
        setResult(-1, intent);
        finish();
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.ui.SuggestedSearchSelectionListener
    public void onSuggestedSearchSelected(SuggestedSearchAtlas suggestedSearchAtlas) {
        String json = new Gson().toJson(suggestedSearchAtlas);
        Intent intent = new Intent();
        intent.putExtra(PlaceConstants.RETURNING_SUGGESTED_DATA, json);
        setResult(-1, intent);
        finish();
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.ui.PlaceSelectionListener
    public void requestForCurrentLocation() {
        Intent intent = new Intent();
        intent.putExtra(PlaceConstants.REQUEST_CURRENT_LOCATION, "SUCCESS");
        setResult(-1, intent);
        finish();
    }
}
