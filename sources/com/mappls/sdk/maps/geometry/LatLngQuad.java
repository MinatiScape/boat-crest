package com.mappls.sdk.maps.geometry;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public class LatLngQuad implements Parcelable {
    public static final Parcelable.Creator<LatLngQuad> CREATOR = new a();
    @Keep
    private final LatLng bottomLeft;
    @Keep
    private final LatLng bottomRight;
    @Keep
    private final LatLng topLeft;
    @Keep
    private final LatLng topRight;

    /* loaded from: classes11.dex */
    public class a implements Parcelable.Creator<LatLngQuad> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public LatLngQuad createFromParcel(@NonNull Parcel parcel) {
            return LatLngQuad.b(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public LatLngQuad[] newArray(int i) {
            return new LatLngQuad[i];
        }
    }

    @Keep
    public LatLngQuad(LatLng latLng, LatLng latLng2, LatLng latLng3, LatLng latLng4) {
        this.topLeft = latLng;
        this.topRight = latLng2;
        this.bottomRight = latLng3;
        this.bottomLeft = latLng4;
    }

    public static LatLngQuad b(@NonNull Parcel parcel) {
        return new LatLngQuad(new LatLng(parcel), new LatLng(parcel), new LatLng(parcel), new LatLng(parcel));
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LatLng getBottomLeft() {
        return this.bottomLeft;
    }

    public LatLng getBottomRight() {
        return this.bottomRight;
    }

    public LatLng getTopLeft() {
        return this.topLeft;
    }

    public LatLng getTopRight() {
        return this.topRight;
    }

    public int hashCode() {
        int hashCode = this.topLeft.hashCode();
        int hashCode2 = (hashCode ^ (hashCode >>> 31)) + this.topRight.hashCode();
        int hashCode3 = (hashCode2 ^ (hashCode2 >>> 31)) + this.bottomRight.hashCode();
        return (hashCode3 ^ (hashCode3 >>> 31)) + this.bottomLeft.hashCode();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        this.topLeft.writeToParcel(parcel, i);
        this.topRight.writeToParcel(parcel, i);
        this.bottomRight.writeToParcel(parcel, i);
        this.bottomLeft.writeToParcel(parcel, i);
    }
}
