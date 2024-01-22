package com.mappls.sdk.maps.geometry;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import kotlin.time.DurationKt;
/* loaded from: classes11.dex */
public class VisibleRegion implements Parcelable {
    public static final Parcelable.Creator<VisibleRegion> CREATOR = new a();
    public final LatLng farLeft;
    public final LatLng farRight;
    public final LatLngBounds latLngBounds;
    public final LatLng nearLeft;
    public final LatLng nearRight;

    /* loaded from: classes11.dex */
    public class a implements Parcelable.Creator<VisibleRegion> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public VisibleRegion createFromParcel(@NonNull Parcel parcel) {
            return new VisibleRegion(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public VisibleRegion[] newArray(int i) {
            return new VisibleRegion[i];
        }
    }

    public /* synthetic */ VisibleRegion(Parcel parcel, a aVar) {
        this(parcel);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof VisibleRegion) {
            if (obj == this) {
                return true;
            }
            VisibleRegion visibleRegion = (VisibleRegion) obj;
            return this.farLeft.equals(visibleRegion.farLeft) && this.farRight.equals(visibleRegion.farRight) && this.nearLeft.equals(visibleRegion.nearLeft) && this.nearRight.equals(visibleRegion.nearRight) && this.latLngBounds.equals(visibleRegion.latLngBounds);
        }
        return false;
    }

    public int hashCode() {
        return this.farLeft.hashCode() + 90 + ((this.farRight.hashCode() + 90) * 1000) + ((this.nearLeft.hashCode() + 180) * DurationKt.NANOS_IN_MILLIS) + ((this.nearRight.hashCode() + 180) * 1000000000);
    }

    @NonNull
    public String toString() {
        return "[farLeft [" + this.farLeft + "], farRight [" + this.farRight + "], nearLeft [" + this.nearLeft + "], nearRight [" + this.nearRight + "], latLngBounds [" + this.latLngBounds + "]]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeParcelable(this.farLeft, i);
        parcel.writeParcelable(this.farRight, i);
        parcel.writeParcelable(this.nearLeft, i);
        parcel.writeParcelable(this.nearRight, i);
        parcel.writeParcelable(this.latLngBounds, i);
    }

    public VisibleRegion(Parcel parcel) {
        this.farLeft = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.farRight = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.nearLeft = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.nearRight = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.latLngBounds = (LatLngBounds) parcel.readParcelable(LatLngBounds.class.getClassLoader());
    }

    public VisibleRegion(LatLng latLng, LatLng latLng2, LatLng latLng3, LatLng latLng4, LatLngBounds latLngBounds) {
        this.farLeft = latLng;
        this.farRight = latLng2;
        this.nearLeft = latLng3;
        this.nearRight = latLng4;
        this.latLngBounds = latLngBounds;
    }
}
