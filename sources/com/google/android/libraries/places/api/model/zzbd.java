package com.google.android.libraries.places.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.libraries.places.api.model.Place;
/* loaded from: classes10.dex */
final class zzbd implements Parcelable.Creator<Place.BusinessStatus> {
    @Override // android.os.Parcelable.Creator
    @NonNull
    public final /* synthetic */ Place.BusinessStatus createFromParcel(Parcel parcel) {
        return Place.BusinessStatus.valueOf(parcel.readString());
    }

    @Override // android.os.Parcelable.Creator
    @NonNull
    public final /* synthetic */ Place.BusinessStatus[] newArray(int i) {
        return new Place.BusinessStatus[i];
    }
}
