package com.mappls.sdk.plugins.places.placepicker;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.google.gson.Gson;
import com.mappls.sdk.maps.camera.CameraPosition;
import com.mappls.sdk.plugins.places.common.PlaceConstants;
import com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions;
import com.mappls.sdk.plugins.places.placepicker.ui.PlacePickerActivity;
import com.mappls.sdk.services.api.Place;
@Keep
/* loaded from: classes11.dex */
public final class PlacePicker {

    @Keep
    /* loaded from: classes11.dex */
    public static class IntentBuilder {
        private Intent intent = new Intent();

        public Intent build(Activity activity) {
            this.intent.setClass(activity, PlacePickerActivity.class);
            return this.intent;
        }

        public IntentBuilder placeOptions(PlacePickerOptions placePickerOptions) {
            this.intent.putExtra(PlaceConstants.PICKER_OPTIONS, placePickerOptions);
            return this;
        }
    }

    private PlacePicker() {
    }

    public static CameraPosition getLastCameraPosition(Intent intent) {
        return (CameraPosition) intent.getParcelableExtra(PlaceConstants.MAP_CAMERA_POSITION);
    }

    @Nullable
    public static Place getPlace(Intent intent) {
        String stringExtra = intent.getStringExtra(PlaceConstants.RETURNING_PLACE_DATA);
        if (stringExtra == null) {
            return null;
        }
        return (Place) new Gson().fromJson(stringExtra, (Class<Object>) Place.class);
    }
}
