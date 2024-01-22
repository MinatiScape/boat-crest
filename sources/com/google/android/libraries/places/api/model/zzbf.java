package com.google.android.libraries.places.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.libraries.places.api.model.Place;
/* loaded from: classes10.dex */
final class zzbf implements Parcelable.Creator<Place.Type> {
    @Override // android.os.Parcelable.Creator
    @NonNull
    public final /* synthetic */ Place.Type createFromParcel(Parcel parcel) {
        return Place.Type.valueOf(parcel.readString());
    }

    @Override // android.os.Parcelable.Creator
    @NonNull
    public final /* synthetic */ Place.Type[] newArray(int i) {
        return new Place.Type[i];
    }
}
