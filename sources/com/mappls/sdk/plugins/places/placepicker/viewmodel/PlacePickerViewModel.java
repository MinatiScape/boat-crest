package com.mappls.sdk.plugins.places.placepicker.viewmodel;

import androidx.annotation.Keep;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.plugins.places.common.utils.d;
import com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.Place;
import com.mappls.sdk.services.api.PlaceResponse;
import com.mappls.sdk.services.api.reversegeocode.MapplsReverseGeoCode;
import com.mappls.sdk.services.api.reversegeocode.MapplsReverseGeoCodeManager;
import timber.log.Timber;
@Keep
/* loaded from: classes11.dex */
public class PlacePickerViewModel extends ViewModel implements OnResponseCallback<PlaceResponse> {
    public Place place;
    private MutableLiveData<d<Place>> results = new MutableLiveData<>();

    public MutableLiveData<d<Place>> getResults() {
        return this.results;
    }

    @Override // com.mappls.sdk.services.api.OnResponseCallback
    public void onError(int i, String str) {
        Timber.e(str, new Object[0]);
        this.results.setValue(d.a("Something went wrong"));
    }

    @Override // com.mappls.sdk.services.api.OnResponseCallback
    public void onSuccess(PlaceResponse placeResponse) {
        if (placeResponse == null || placeResponse.getPlaces() == null || placeResponse.getPlaces().size() <= 0) {
            this.results.setValue(d.a("Something went wrong"));
        } else {
            this.results.setValue(d.a(placeResponse.getPlaces().get(0)));
        }
    }

    public void reverseGeocode(Point point, PlacePickerOptions placePickerOptions) {
        MapplsReverseGeoCode.Builder builder = MapplsReverseGeoCode.builder();
        builder.setLocation(point.latitude(), point.longitude());
        MapplsReverseGeoCodeManager.newInstance(builder.build()).call(this);
    }
}
