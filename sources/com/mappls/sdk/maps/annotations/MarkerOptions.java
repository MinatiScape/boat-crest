package com.mappls.sdk.maps.annotations;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.maps.exceptions.InvalidMarkerPositionException;
import com.mappls.sdk.maps.geometry.LatLng;
@Deprecated
/* loaded from: classes11.dex */
public final class MarkerOptions extends BaseMarkerOptions<Marker, MarkerOptions> {
    public static final Parcelable.Creator<MarkerOptions> CREATOR = new a();

    /* loaded from: classes11.dex */
    public class a implements Parcelable.Creator<MarkerOptions> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public MarkerOptions createFromParcel(@NonNull Parcel parcel) {
            return new MarkerOptions(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public MarkerOptions[] newArray(int i) {
            return new MarkerOptions[i];
        }
    }

    public MarkerOptions() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || MarkerOptions.class != obj.getClass()) {
            return false;
        }
        MarkerOptions markerOptions = (MarkerOptions) obj;
        if (getPosition() == null ? markerOptions.getPosition() == null : getPosition().equals(markerOptions.getPosition())) {
            if (getSnippet() == null ? markerOptions.getSnippet() == null : getSnippet().equals(markerOptions.getSnippet())) {
                if (getMapplsPin() == null ? markerOptions.getMapplsPin() == null : getMapplsPin().equals(markerOptions.getMapplsPin())) {
                    if (getIcon() == null ? markerOptions.getIcon() == null : getIcon().equals(markerOptions.getIcon())) {
                        if (getTitle() != null) {
                            if (getTitle().equals(markerOptions.getTitle())) {
                                return true;
                            }
                        } else if (markerOptions.getTitle() == null) {
                            return true;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public Icon getIcon() {
        return this.icon;
    }

    public String getMapplsPin() {
        return this.mapplsPin;
    }

    @Override // com.mappls.sdk.maps.annotations.BaseMarkerOptions
    public Marker getMarker() {
        LatLng latLng = this.position;
        if (latLng == null && this.mapplsPin == null) {
            throw new InvalidMarkerPositionException();
        }
        return new Marker(latLng, this.icon, this.title, this.snippet, this.mapplsPin);
    }

    public LatLng getPosition() {
        return this.position;
    }

    public String getSnippet() {
        return this.snippet;
    }

    @Override // com.mappls.sdk.maps.annotations.BaseMarkerOptions
    @NonNull
    public MarkerOptions getThis() {
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return (((((((((getPosition() != null ? getPosition().hashCode() : 0) + 31) * 31) + (getMapplsPin() != null ? getMapplsPin().hashCode() : 0)) * 31) + (getSnippet() != null ? getSnippet().hashCode() : 0)) * 31) + (getIcon() != null ? getIcon().hashCode() : 0)) * 31) + (getTitle() != null ? getTitle().hashCode() : 0);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(getPosition(), i);
        parcel.writeString(getSnippet());
        parcel.writeString(getTitle());
        Icon icon = getIcon();
        parcel.writeByte((byte) (icon != null ? 1 : 0));
        if (icon != null) {
            parcel.writeString(getIcon().getId());
            parcel.writeParcelable(getIcon().getBitmap(), i);
        }
        parcel.writeString(getMapplsPin());
    }

    public MarkerOptions(Parcel parcel) {
        position((LatLng) parcel.readParcelable(LatLng.class.getClassLoader()));
        snippet(parcel.readString());
        title(parcel.readString());
        if (parcel.readByte() != 0) {
            icon(new Icon(parcel.readString(), (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader())));
        }
        mapplsPin(parcel.readString());
    }
}
