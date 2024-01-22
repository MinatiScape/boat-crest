package com.mappls.sdk.maps.geometry;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.FloatRange;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.turf.TurfConstants;
import com.mappls.sdk.turf.TurfMeasurement;
/* loaded from: classes11.dex */
public class LatLng implements Parcelable {
    public static final Parcelable.Creator<LatLng> CREATOR = new a();
    public double h;
    @Keep
    private double latitude;
    @Keep
    private double longitude;

    /* loaded from: classes11.dex */
    public class a implements Parcelable.Creator<LatLng> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public LatLng createFromParcel(@NonNull Parcel parcel) {
            return new LatLng(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public LatLng[] newArray(int i) {
            return new LatLng[i];
        }
    }

    public LatLng() {
        this.h = 0.0d;
        this.latitude = 0.0d;
        this.longitude = 0.0d;
    }

    public static double a(double d, double d2, double d3) {
        double d4 = d3 - d2;
        double d5 = (((d - d2) % d4) + d4) % d4;
        return (d < d3 || d5 != 0.0d) ? d5 + d2 : d3;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public double distanceTo(@NonNull LatLng latLng) {
        return TurfMeasurement.distance(Point.fromLngLat(this.longitude, this.latitude), Point.fromLngLat(latLng.getLongitude(), latLng.getLatitude()), TurfConstants.UNIT_METRES);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LatLng latLng = (LatLng) obj;
        return Double.compare(latLng.h, this.h) == 0 && Double.compare(latLng.latitude, this.latitude) == 0 && Double.compare(latLng.longitude, this.longitude) == 0;
    }

    public double getAltitude() {
        return this.h;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.latitude);
        long doubleToLongBits2 = Double.doubleToLongBits(this.longitude);
        long doubleToLongBits3 = Double.doubleToLongBits(this.h);
        return (((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + ((int) ((doubleToLongBits3 >>> 32) ^ doubleToLongBits3));
    }

    public void setAltitude(double d) {
        this.h = d;
    }

    public void setLatitude(@FloatRange(from = -90.0d, to = 90.0d) double d) {
        if (!Double.isNaN(d)) {
            if (Math.abs(d) <= 90.0d) {
                this.latitude = d;
                return;
            }
            throw new IllegalArgumentException("latitude must be between -90 and 90");
        }
        throw new IllegalArgumentException("latitude must not be NaN");
    }

    public void setLongitude(@FloatRange(from = -1.7976931348623157E308d, to = Double.MAX_VALUE) double d) {
        if (!Double.isNaN(d)) {
            if (!Double.isInfinite(d)) {
                this.longitude = d;
                return;
            }
            throw new IllegalArgumentException("longitude must not be infinite");
        }
        throw new IllegalArgumentException("longitude must not be NaN");
    }

    @NonNull
    public String toString() {
        return "LatLng [latitude=" + this.latitude + ", longitude=" + this.longitude + ", altitude=" + this.h + "]";
    }

    @NonNull
    public LatLng wrap() {
        return new LatLng(this.latitude, a(this.longitude, -180.0d, 180.0d));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeDouble(this.latitude);
        parcel.writeDouble(this.longitude);
        parcel.writeDouble(this.h);
    }

    @Keep
    public LatLng(double d, double d2) {
        this.h = 0.0d;
        setLatitude(d);
        setLongitude(d2);
    }

    public LatLng(double d, double d2, double d3) {
        this.h = 0.0d;
        setLatitude(d);
        setLongitude(d2);
        setAltitude(d3);
    }

    public LatLng(Location location) {
        this(location.getLatitude(), location.getLongitude(), location.getAltitude());
    }

    public LatLng(LatLng latLng) {
        this.h = 0.0d;
        this.latitude = latLng.latitude;
        this.longitude = latLng.longitude;
        this.h = latLng.h;
    }

    public LatLng(Parcel parcel) {
        this.h = 0.0d;
        setLatitude(parcel.readDouble());
        setLongitude(parcel.readDouble());
        setAltitude(parcel.readDouble());
    }
}
