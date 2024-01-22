package com.mappls.sdk.maps.annotations;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.maps.geometry.LatLng;
import java.util.ArrayList;
import java.util.List;
@Deprecated
/* loaded from: classes11.dex */
public final class PolylineOptions implements Parcelable {
    public static final Parcelable.Creator<PolylineOptions> CREATOR = new a();
    public Polyline h;

    /* loaded from: classes11.dex */
    public class a implements Parcelable.Creator<PolylineOptions> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public PolylineOptions createFromParcel(@NonNull Parcel parcel) {
            return new PolylineOptions(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public PolylineOptions[] newArray(int i) {
            return new PolylineOptions[i];
        }
    }

    public /* synthetic */ PolylineOptions(Parcel parcel, a aVar) {
        this(parcel);
    }

    @NonNull
    public PolylineOptions add(LatLng latLng) {
        this.h.addPoint(latLng);
        return this;
    }

    @NonNull
    public PolylineOptions addAll(Iterable<LatLng> iterable) {
        for (LatLng latLng : iterable) {
            add(latLng);
        }
        return this;
    }

    @NonNull
    public PolylineOptions alpha(float f) {
        this.h.setAlpha(f);
        return this;
    }

    @NonNull
    public PolylineOptions color(int i) {
        this.h.setColor(i);
        return this;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PolylineOptions.class != obj.getClass()) {
            return false;
        }
        PolylineOptions polylineOptions = (PolylineOptions) obj;
        if (Float.compare(polylineOptions.getAlpha(), getAlpha()) == 0 && getColor() == polylineOptions.getColor() && Float.compare(polylineOptions.getWidth(), getWidth()) == 0) {
            if (getPoints() != null) {
                if (getPoints().equals(polylineOptions.getPoints())) {
                    return true;
                }
            } else if (polylineOptions.getPoints() == null) {
                return true;
            }
            return false;
        }
        return false;
    }

    public float getAlpha() {
        return this.h.getAlpha();
    }

    public int getColor() {
        return this.h.getColor();
    }

    public List<LatLng> getPoints() {
        return this.h.getPoints();
    }

    public Polyline getPolyline() {
        return this.h;
    }

    public float getWidth() {
        return this.h.getWidth();
    }

    public int hashCode() {
        return (((((((getAlpha() != 0.0f ? Float.floatToIntBits(getAlpha()) : 0) + 31) * 31) + getColor()) * 31) + (getWidth() != 0.0f ? Float.floatToIntBits(getWidth()) : 0)) * 31) + (getPoints() != null ? getPoints().hashCode() : 0);
    }

    @NonNull
    public PolylineOptions width(float f) {
        this.h.setWidth(f);
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(getPoints());
        parcel.writeFloat(getAlpha());
        parcel.writeInt(getColor());
        parcel.writeFloat(getWidth());
    }

    public PolylineOptions(Parcel parcel) {
        this.h = new Polyline();
        ArrayList arrayList = new ArrayList();
        parcel.readList(arrayList, LatLng.class.getClassLoader());
        addAll(arrayList);
        alpha(parcel.readFloat());
        color(parcel.readInt());
        width(parcel.readFloat());
    }

    @NonNull
    public PolylineOptions add(LatLng... latLngArr) {
        for (LatLng latLng : latLngArr) {
            add(latLng);
        }
        return this;
    }

    public PolylineOptions() {
        this.h = new Polyline();
    }
}
