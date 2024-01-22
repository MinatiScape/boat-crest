package com.mappls.sdk.plugins.places.placepicker.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.mappls.sdk.maps.camera.CameraPosition;
import com.mappls.sdk.maps.geometry.LatLngBounds;
import com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions;
import java.util.List;
/* loaded from: classes11.dex */
public final class b extends com.mappls.sdk.plugins.places.placepicker.model.a {
    public static final Parcelable.Creator<b> CREATOR = new a();

    /* loaded from: classes11.dex */
    public class a implements Parcelable.Creator<b> {
        @Override // android.os.Parcelable.Creator
        public final b createFromParcel(Parcel parcel) {
            return new b(parcel.readInt() == 0 ? Integer.valueOf(parcel.readInt()) : null, (LatLngBounds) parcel.readParcelable(PlacePickerOptions.class.getClassLoader()), (CameraPosition) parcel.readParcelable(PlacePickerOptions.class.getClassLoader()), parcel.readArrayList(PlacePickerOptions.class.getClassLoader()), parcel.readInt() == 0 ? parcel.readString() : null, Boolean.valueOf(parcel.readInt() == 1), Double.valueOf(parcel.readDouble()), Double.valueOf(parcel.readDouble()), Boolean.valueOf(parcel.readInt() == 1), (PlaceOptions) parcel.readParcelable(PlacePickerOptions.class.getClassLoader()), (Bitmap) parcel.readParcelable(PlacePickerOptions.class.getClassLoader()), Integer.valueOf(parcel.readInt()), Integer.valueOf(parcel.readInt()), Integer.valueOf(parcel.readInt()), Boolean.valueOf(parcel.readInt() == 1), parcel.readInt() == 0 ? Integer.valueOf(parcel.readInt()) : null, Integer.valueOf(parcel.readInt()), Integer.valueOf(parcel.readInt()), parcel.readString(), Integer.valueOf(parcel.readInt()));
        }

        @Override // android.os.Parcelable.Creator
        public final b[] newArray(int i) {
            return new b[i];
        }
    }

    public b(@Nullable Integer num, @Nullable LatLngBounds latLngBounds, @Nullable CameraPosition cameraPosition, @Nullable List<String> list, @Nullable String str, Boolean bool, Double d, Double d2, Boolean bool2, PlaceOptions placeOptions, @Nullable Bitmap bitmap, Integer num2, Integer num3, Integer num4, Boolean bool3, @Nullable Integer num5, Integer num6, Integer num7, String str2, Integer num8) {
        super(num, latLngBounds, cameraPosition, list, str, bool, d, d2, bool2, placeOptions, bitmap, num2, num3, num4, bool3, num5, num6, num7, str2, num8);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        if (toolbarColor() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeInt(toolbarColor().intValue());
        }
        parcel.writeParcelable(startingBounds(), i);
        parcel.writeParcelable(statingCameraPosition(), i);
        parcel.writeList(startingMapplsPinBounds());
        if (startingMapplsPinPosition() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(startingMapplsPinPosition());
        }
        parcel.writeInt(includeDeviceLocationButton().booleanValue() ? 1 : 0);
        parcel.writeDouble(mapMaxZoom().doubleValue());
        parcel.writeDouble(mapMinZoom().doubleValue());
        parcel.writeInt(includeSearch().booleanValue() ? 1 : 0);
        parcel.writeParcelable(searchPlaceOption(), i);
        parcel.writeParcelable(markerBitmap(), i);
        parcel.writeInt(toolbarTintColor().intValue());
        parcel.writeInt(placeNameTextColor().intValue());
        parcel.writeInt(addressTextColor().intValue());
        parcel.writeInt(showMarkerShadow().booleanValue() ? 1 : 0);
        if (pickerButtonBackgroundColor() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeInt(pickerButtonBackgroundColor().intValue());
        }
        parcel.writeInt(pickerButtonBackgroundResource().intValue());
        parcel.writeInt(pickerButtonTextColor().intValue());
        parcel.writeString(pickerButtonText());
        parcel.writeInt(marker().intValue());
    }
}
