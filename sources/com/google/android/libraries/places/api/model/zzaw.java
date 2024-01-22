package com.google.android.libraries.places.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.maps.model.LatLng;
/* loaded from: classes10.dex */
final class zzaw implements Parcelable.Creator<zzax> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzax createFromParcel(Parcel parcel) {
        return new zzax((LatLng) parcel.readParcelable(RectangularBounds.class.getClassLoader()), (LatLng) parcel.readParcelable(RectangularBounds.class.getClassLoader()));
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzax[] newArray(int i) {
        return new zzax[i];
    }
}
