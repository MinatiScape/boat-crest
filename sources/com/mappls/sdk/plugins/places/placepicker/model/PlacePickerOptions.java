package com.mappls.sdk.plugins.places.placepicker.model;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Parcelable;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.maps.camera.CameraPosition;
import com.mappls.sdk.maps.geometry.LatLngBounds;
import com.mappls.sdk.plugins.places.R;
import com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions;
import com.mappls.sdk.plugins.places.placepicker.model.a;
import java.util.List;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class PlacePickerOptions implements Parcelable {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract Builder addressTextColor(@NonNull @ColorInt Integer num);

        public abstract PlacePickerOptions build();

        public abstract Builder includeDeviceLocationButton(Boolean bool);

        public abstract Builder includeSearch(@NonNull Boolean bool);

        public abstract Builder mapMaxZoom(@NonNull Double d);

        public abstract Builder mapMinZoom(@NonNull Double d);

        public abstract Builder marker(@NonNull @DrawableRes Integer num);

        public abstract Builder markerBitmap(@Nullable Bitmap bitmap);

        public abstract Builder pickerButtonBackgroundColor(@Nullable @ColorInt Integer num);

        public abstract Builder pickerButtonBackgroundResource(@NonNull @DrawableRes Integer num);

        public abstract Builder pickerButtonText(@NonNull String str);

        public abstract Builder pickerButtonTextColor(@NonNull @ColorInt Integer num);

        public abstract Builder placeNameTextColor(@NonNull @ColorInt Integer num);

        public abstract Builder searchPlaceOption(@NonNull PlaceOptions placeOptions);

        public abstract Builder showMarkerShadow(@NonNull Boolean bool);

        public abstract Builder startingBounds(@NonNull LatLngBounds latLngBounds);

        public abstract Builder startingMapplsPinBounds(@Nullable List<String> list);

        public abstract Builder startingMapplsPinPosition(@Nullable String str);

        public abstract Builder statingCameraPosition(@NonNull CameraPosition cameraPosition);

        public abstract Builder toolbarColor(@ColorInt Integer num);

        public abstract Builder toolbarTintColor(@NonNull @ColorInt Integer num);
    }

    public static Builder builder() {
        PlaceOptions.Builder backgroundColor = PlaceOptions.builder().backgroundColor(Color.parseColor(Constants.WHITE));
        Boolean bool = Boolean.TRUE;
        return new a.C0663a().includeDeviceLocationButton(bool).mapMaxZoom(Double.valueOf(18.0d)).mapMinZoom(Double.valueOf(4.0d)).includeSearch(bool).showMarkerShadow(bool).placeNameTextColor(Integer.valueOf(Color.parseColor("#263d57"))).addressTextColor(Integer.valueOf(Color.parseColor("#949fac"))).toolbarTintColor(Integer.valueOf(Color.parseColor(Constants.BLACK))).pickerButtonTextColor(Integer.valueOf(Color.parseColor(Constants.WHITE))).pickerButtonBackgroundResource(Integer.valueOf(R.drawable.mappls_search_btn_bg)).marker(Integer.valueOf(R.drawable.mappls_search_pin_red_marker)).pickerButtonText("Done").searchPlaceOption(backgroundColor.tokenizeAddress(bool).build(2));
    }

    @NonNull
    @ColorInt
    public abstract Integer addressTextColor();

    @NonNull
    public abstract Boolean includeDeviceLocationButton();

    @NonNull
    public abstract Boolean includeSearch();

    @NonNull
    public abstract Double mapMaxZoom();

    @NonNull
    public abstract Double mapMinZoom();

    @NonNull
    @DrawableRes
    public abstract Integer marker();

    @Nullable
    public abstract Bitmap markerBitmap();

    @Nullable
    @ColorInt
    public abstract Integer pickerButtonBackgroundColor();

    @NonNull
    @DrawableRes
    public abstract Integer pickerButtonBackgroundResource();

    @NonNull
    public abstract String pickerButtonText();

    @NonNull
    @ColorInt
    public abstract Integer pickerButtonTextColor();

    @NonNull
    @ColorInt
    public abstract Integer placeNameTextColor();

    @NonNull
    public abstract PlaceOptions searchPlaceOption();

    @NonNull
    public abstract Boolean showMarkerShadow();

    @Nullable
    public abstract LatLngBounds startingBounds();

    @Nullable
    public abstract List<String> startingMapplsPinBounds();

    @Nullable
    public abstract String startingMapplsPinPosition();

    @Nullable
    public abstract CameraPosition statingCameraPosition();

    @Nullable
    public abstract Integer toolbarColor();

    @NonNull
    @ColorInt
    public abstract Integer toolbarTintColor();
}
