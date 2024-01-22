package com.google.android.libraries.places.api.model;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes10.dex */
final class zzas implements Parcelable.Creator<zzat> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzat createFromParcel(Parcel parcel) {
        return new zzat((Place) parcel.readParcelable(PlaceLikelihood.class.getClassLoader()), parcel.readDouble());
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzat[] newArray(int i) {
        return new zzat[i];
    }
}
