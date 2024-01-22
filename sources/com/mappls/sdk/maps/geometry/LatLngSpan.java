package com.mappls.sdk.maps.geometry;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public class LatLngSpan implements Parcelable {
    public static final Parcelable.Creator<LatLngSpan> CREATOR = new a();
    public double h;
    public double i;

    /* loaded from: classes11.dex */
    public class a implements Parcelable.Creator<LatLngSpan> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public LatLngSpan createFromParcel(@NonNull Parcel parcel) {
            return new LatLngSpan(parcel, (a) null);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public LatLngSpan[] newArray(int i) {
            return new LatLngSpan[i];
        }
    }

    public /* synthetic */ LatLngSpan(Parcel parcel, a aVar) {
        this(parcel);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LatLngSpan) {
            LatLngSpan latLngSpan = (LatLngSpan) obj;
            return this.i == latLngSpan.getLongitudeSpan() && this.h == latLngSpan.getLatitudeSpan();
        }
        return false;
    }

    public double getLatitudeSpan() {
        return this.h;
    }

    public double getLongitudeSpan() {
        return this.i;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.h);
        long doubleToLongBits2 = Double.doubleToLongBits(this.i);
        return (((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + ((int) ((doubleToLongBits2 >>> 32) ^ doubleToLongBits2));
    }

    public void setLatitudeSpan(double d) {
        this.h = d;
    }

    public void setLongitudeSpan(double d) {
        this.i = d;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeDouble(this.h);
        parcel.writeDouble(this.i);
    }

    public LatLngSpan(@NonNull Parcel parcel) {
        this.h = parcel.readDouble();
        this.i = parcel.readDouble();
    }

    public LatLngSpan(double d, double d2) {
        this.h = d;
        this.i = d2;
    }
}
